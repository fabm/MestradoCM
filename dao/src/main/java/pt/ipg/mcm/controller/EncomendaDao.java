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
import java.util.Map;

@Stateless
public class EncomendaDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public void inserirEncomenda(EncomendaEntity encomendaEntity,String login) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("{CALL P_ADD_ENCOMENDA(?,?,?,?,?)}");
      EncomendaEntity encomendaAssociada = encomendaEntity.getEncomendaAssociada();
      if (encomendaAssociada == null) {
        call.setObject(1, null);
      } else {
        call.setLong(1, encomendaAssociada.getIdEncomenda());
      }
      call.setString(2, login);
      call.setObject(3, encomendaEntity.getCalendarioEntity().getDataprevista());
      call.registerOutParameter(4, Types.NUMERIC);
      call.registerOutParameter(5, Types.NUMERIC);

      call.execute();

      encomendaEntity.getCalendarioEntity().setIdCalendario(call.getLong(4));
      encomendaEntity.setIdEncomenda(call.getLong(5));

      for (EncomendaProdutoEntity produtoEntity : encomendaEntity.getEncomendaProdutoEntityList()) {
        call = connection.prepareCall("{CALL P_ADD_PRODUTO_ENCOMENDA(?,?,?)}");

        call.setLong(1, produtoEntity.getQuantidade());
        call.setLong(3, produtoEntity.getEncomenda().getIdEncomenda());
        call.setLong(2, produtoEntity.getProduto().getIdProduto());

        call.execute();
      }

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
