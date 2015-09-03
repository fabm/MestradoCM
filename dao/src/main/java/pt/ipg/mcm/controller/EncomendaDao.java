package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.entities.EncomendaEntity;
import pt.ipg.mcm.entities.EncomendaProdutoEntity;
import pt.ipg.mcm.entities.VEncomendasLoginEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.*;
import pt.ipg.mcm.xmodel.encomendas.ProdutoSoapIn;
import pt.ipg.mcm.xmodel.encomendas.EncomendaSoapIn;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomendas;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomenda;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class EncomendaDao {

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  @EJB
  private MappedSql mappedSql;

  public void inserirEncomenda(EncomendaEntity encomendaEntity, String login) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();

      addEncomenda(encomendaEntity, login, connection);

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  @Deprecated
  public List<Long> inserirEncomendas(List<EncomendaEntity> encomendaEntityList, String login) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();
      List<Long> ids = new ArrayList<Long>();
      for (EncomendaEntity encomendaEntity : encomendaEntityList) {
        addEncomenda(encomendaEntity, login, connection);
        ids.add(encomendaEntity.getIdEncomenda());
      }
      return ids;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  public XOutEncomendas postEncomendas(List<EncomendaSoapIn> EncomendaSoapInList, String login) throws MestradoException {
    XOutEncomendas XOutEncomendas = new XOutEncomendas();
    try {
      Connection connection = mestradoDataSource.getConnection();
      for (EncomendaSoapIn EncomendaSoapIn : EncomendaSoapInList) {
        XOutEncomenda res = addEncomenda(EncomendaSoapIn, login, connection, null);
        res.setClientId(EncomendaSoapIn.getClientId());
        XOutEncomendas.getXOutEncomendas().add(res);
      }
      return XOutEncomendas;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  private XOutEncomenda addEncomenda(EncomendaSoapIn encomendaSoapIn, String login, Connection connection, Long
      encomendaAssociada) throws MestradoException {
    XOutEncomenda xOutEncomenda = new XOutEncomenda();

    try {
      CallableStatement call = connection.prepareCall("{CALL P_ADD_ENCOMENDA(?,?,?,?,?)}");
      call.setString(1, login);
      call.setDate(2, new Date(encomendaSoapIn.getDataEntrega().getTime()));
      call.registerOutParameter(3, Types.NUMERIC);
      call.registerOutParameter(4, Types.NUMERIC);
      call.setNull(5, Types.NUMERIC);
      if (encomendaAssociada != null) {
        call.setLong(5, encomendaAssociada);
      } else {
        call.setNull(5, Types.NUMERIC);
      }

      call.execute();

      xOutEncomenda.setServerId(call.getLong(3));


      for (ProdutoSoapIn produtoSoapIn : encomendaSoapIn.getXInProdutos()) {
        call = connection.prepareCall("{CALL P_ADD_PRODUTO_ENCOMENDA(?,?,?)}");

        call.setLong(1, produtoSoapIn.getQuantidade());
        call.setLong(2, xOutEncomenda.getServerId());
        call.setLong(3, produtoSoapIn.getIdProduto());

        call.execute();
      }
      return xOutEncomenda;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }

  }

  @Deprecated
  private void addEncomenda(EncomendaEntity encomendaEntity, String login, Connection connection) throws SQLException {
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
  }


  public List<MinhaEncomenda> getMinhasEncomendas(final String login, final long idSync) throws MestradoException {
    SqlSession session = mappedSql.getSqlSession();
    try {
      return session.selectList("getMinhasEncomendas",new HashMap<String,Object>(){{
        put("login",login);
        put("sync",idSync);
      }});
    } catch (Exception e) {
      e.printStackTrace();
      throw new MestradoException(Erro.TECNICO);
    }finally {
      session.close();
    }
  }




  public ResMinhasEncomendasDetalhe getMinhasEncomendasSync(String login, long idSync) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();
      PreparedStatement ps = connection.prepareStatement("SELECT ID_ENCOMENDA, DATA_CRIACAO, ESTADO, OBSERVACOES,DATA_ENTREGA, SYNC from V_ENCOMENDAS_CLIENTE\n" +
          "WHERE LOGIN = ? and SYNC > ? ");

      ps.setString(1, login);
      ps.setLong(2, idSync);

      ResultSet rs = ps.executeQuery();

      List<EncomendaDetalheXml> minhasEncomendasDetalheXmls = new ArrayList<>();
      while (rs.next()) {
        EncomendaDetalheXml encomendaDetalheXml = new EncomendaDetalheXml();
        encomendaDetalheXml.setId(rs.getLong(1));
        encomendaDetalheXml.setDataCriacao(rs.getTimestamp(2));
        encomendaDetalheXml.setEstado(rs.getInt(3));
        encomendaDetalheXml.setObservacoes(rs.getString(4));
        encomendaDetalheXml.setDataEntrega(rs.getTimestamp(5));
        idSync = Math.max(idSync,rs.getLong(6));

        List<ProdutoEncomendadoComPreco> produtoEncomendadoList = new ArrayList<>();
        encomendaDetalheXml.setProdutosEncomendados(produtoEncomendadoList);
        for (ProdutoEncomendadoComPreco produtoEncomendadoComPreco : new ProdutoEncomendadoLoader(connection,encomendaDetalheXml.getId())) {
          produtoEncomendadoList.add(produtoEncomendadoComPreco);
        }

        minhasEncomendasDetalheXmls.add(encomendaDetalheXml);
      }
      ResMinhasEncomendasDetalhe resMinhasEncomendasDetalhe = new ResMinhasEncomendasDetalhe();
      resMinhasEncomendasDetalhe.setListaEncomendasDetalheXmls(minhasEncomendasDetalheXmls);
      resMinhasEncomendasDetalhe.setMaxSync(idSync);
      return resMinhasEncomendasDetalhe;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }


}
