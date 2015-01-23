package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.EncomendaEntity;
import pt.ipg.mcm.entities.EncomendaProdutoEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Stateless
public class EncomendaDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public void inserirEncomenda(EncomendaEntity encomendaEntity) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("CALL {P_ADD_ENCOMENDA(?,?,?,?,?,?,?,?,?)}");
      call.setTimestamp(1, encomendaEntity.getDataEntrega());
      call.setLong(2, encomendaEntity.getEncomendaAssociada().getIdEncomenda());
      call.setLong(3, encomendaEntity.getFatura());
      call.setLong(4, encomendaEntity.getCalendarioviagensEntity().getIdCalendario());
      call.setLong(5, encomendaEntity.getClienteEntity().getIdCliente());
      call.setLong(6, encomendaEntity.getQuantidade());
      EncomendaEntity encomendaAssociada = encomendaEntity.getEncomendaAssociada();
      if (encomendaAssociada == null) {
        call.setObject(7, null);
      } else {
        call.setLong(7, encomendaAssociada.getIdEncomenda());
      }
      call.setTimestamp(8, encomendaEntity.getDataEntrega());
      call.registerOutParameter(9, Types.NUMERIC);

      call.execute();

      encomendaEntity.setIdEncomenda(call.getLong(9));

      for (EncomendaProdutoEntity produtoEntity : encomendaEntity.getEncomendaProdutoEntityList()) {
        call = connection.prepareCall("CALL {P_ADD_PRODUTO_ENCOMENDA(?,?,?)}");

        call.setLong(1, produtoEntity.getEncomenda().getIdEncomenda());
        call.setLong(2, produtoEntity.getProduto().getIdProduto());
        call.setLong(3, produtoEntity.getQuantidade());

        call.execute();
      }

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
