package pt.ipg.mcm.controller;

import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddPadeiro;
import pt.ipg.mcm.xmodel.ResAddPadeiro;
import pt.ipg.mcm.xmodel.Retorno;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PadeiroDao {
  private static Logger LOGGER = Logger.getLogger(CategoriaDao.class.getName());

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public ResAddPadeiro addPadeiro(ReqAddPadeiro reqAddPadeiro) {
    Connection connection;
    ResAddPadeiro resAddPadeiro;
    try {
      connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_ADD_PADEIRO(?,?,?)");
      call.setLong(1, reqAddPadeiro.getIdUtilizador());
      call.setString(2, reqAddPadeiro.getNome());
      call.registerOutParameter(3, Types.NUMERIC);
      call.execute();

      resAddPadeiro = new ResAddPadeiro();

      resAddPadeiro.setId(call.getLong(3));
      resAddPadeiro.setRetorno(new Retorno(1, "Padeiro inserido com sucesso"));
      return resAddPadeiro;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "sql problem", e);
      resAddPadeiro = new ResAddPadeiro();
      resAddPadeiro.setRetorno(new Retorno(new MestradoException(Erro.TECNICO)));
      return resAddPadeiro;
    }

  }


  //RETORNO DO PADEIRO


}
