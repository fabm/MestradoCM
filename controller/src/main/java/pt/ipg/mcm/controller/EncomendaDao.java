package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.EncomendaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Stateless
public class EncomendaDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public void inserirEncomenda(EncomendaEntity encomendaEntity) throws MestradoException {

    try {
      Connection connection = mestradoDataSource.getConnection();

      connection.prepareStatement("");

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
