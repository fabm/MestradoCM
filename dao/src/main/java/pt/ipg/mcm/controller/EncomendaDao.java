package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.EncomendaEntity;
import pt.ipg.mcm.entities.EncomendaProdutoEntity;
import pt.ipg.mcm.entities.VEncomendasLoginEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EncomendaDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public void inserirEncomenda(EncomendaEntity encomendaEntity, String login) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("{CALL P_ADD_ENCOMENDA(?,?,?,?,?)}");
      EncomendaEntity encomendaAssociada = encomendaEntity.getEncomendaAssociada();
      call.setString(1, login);
      call.setDate(2, new java.sql.Date(encomendaEntity.getCalendarioEntity().getDataprevista().getTime()));
      call.registerOutParameter(3, Types.NUMERIC);
      call.registerOutParameter(4, Types.NUMERIC);
      if (encomendaAssociada != null) {
        call.setLong(5, encomendaAssociada.getIdEncomenda());
      } else {
        call.setObject(5, null);
      }

      call.execute();

      encomendaEntity.setIdEncomenda(call.getLong(4));

      for (EncomendaProdutoEntity produtoEntity : encomendaEntity.getEncomendaProdutoEntityList()) {
        call = connection.prepareCall("{CALL P_ADD_PRODUTO_ENCOMENDA(?,?,?)}");

        call.setLong(1, produtoEntity.getQuantidade());
        call.setLong(2, produtoEntity.getEncomenda().getIdEncomenda());
        call.setLong(3, produtoEntity.getProduto().getIdProduto());

        call.execute();
      }

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  public List<VEncomendasLoginEntity> getMinhasEncomendas(String login, long idSync) throws MestradoException {
    List<VEncomendasLoginEntity> vEncomendaEntities = new ArrayList<VEncomendasLoginEntity>();
    try {
      Connection connection = mestradoDataSource.getConnection();

      String sqlString = "SELECT DATA_ENTREGA, ENCOMENDA_ASSOCIADA, CALENDARIO, DATA_CRIACAO, ESTADO,\n" +
          "OBSERVACOES, DATA_PREVISTA, ID_ENCOMENDA, QUANTIDADE_ENCOMENDADA, PRODUTO_ENCOMENDADO, \n" +
          "PRODUTO, PRECO_ATUAL, ID_CATEGORIA \n" +
          "FROM V_ENCOMENDAS_LOGIN\n" +
          "WHERE LOGIN = ? and SYNC > ? ";

      PreparedStatement ps = connection.prepareStatement(sqlString);

      ps.setString(1, login);
      ps.setLong(2, idSync);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        VEncomendasLoginEntity vEncomendasLoginEntity = new VEncomendasLoginEntity();
        vEncomendasLoginEntity.setDataEntrega(rs.getTimestamp(1));
        vEncomendasLoginEntity.setEncomendaAssociada(rs.getLong(2));
        vEncomendasLoginEntity.setCalendario(rs.getLong(3));
        vEncomendasLoginEntity.setDataCriacao(rs.getTimestamp(4));
        vEncomendasLoginEntity.setEstado(rs.getLong(5));
        vEncomendasLoginEntity.setObservacoes(rs.getString(6));
        vEncomendasLoginEntity.setDataPrevista(rs.getTimestamp(7));
        vEncomendasLoginEntity.setIdEncomenda(rs.getLong(8));
        vEncomendasLoginEntity.setQuantidadeEncomendada(rs.getInt(9));
        vEncomendasLoginEntity.setProdutoEncomendado(rs.getLong(10));
        vEncomendasLoginEntity.setProduto(rs.getString(11));
        vEncomendasLoginEntity.setPrecoAtual(rs.getBigDecimal(12));
        vEncomendasLoginEntity.setIdCategoria(rs.getBigDecimal(13));
        vEncomendaEntities.add(vEncomendasLoginEntity);
      }
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
    return vEncomendaEntities;
  }


  public List<EncomendaEntity> getMinhasEncomendasSync(String login, long idSync) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();
      PreparedStatement ps = connection.prepareStatement("SELECT ID_ENCOMENDA, DATA_CRIACAO, ESTADO, DATA_PREVISTA, OBSERVACOES from V_ENCOMENDAS_CLIENTE\n" +
          "WHERE LOGIN = ? and SYNC > ? ");

      ps.setString(1, login);
      ps.setLong(2, idSync);

      ResultSet rs = ps.executeQuery();

      List<EncomendaEntity> listaEncomendas = new ArrayList<EncomendaEntity>();
      while (rs.next()) {
        EncomendaEntity encomenda = new EncomendaEntity();
        long idEncomenda = rs.getLong(1);
        encomenda.setIdEncomenda(idEncomenda);
        encomenda.setDataCriacao(rs.getTimestamp(2));
        encomenda.setEstado(EncomendaEntity.Estado.getByNumber(rs.getInt(3)));
        encomenda.setDataEntrega(rs.getTimestamp(4));
        encomenda.setObservacoes(rs.getString(5));

        List<EncomendaProdutoEntity> produtoEncomendadoList = encomenda.getEncomendaProdutoEntityList();
        for (EncomendaProdutoEntity encomendaProdutoEntity : new ProdutoEncomendadoLoader(connection, idEncomenda)) {
          produtoEncomendadoList.add(encomendaProdutoEntity);
        }
        listaEncomendas.add(encomenda);
      }
      return listaEncomendas;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }


}
