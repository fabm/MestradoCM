package pt.ipg.mcm.controller.imp;


import pt.ipg.mcm.controller.SHAUtils;
import pt.ipg.mcm.entities.PadeiroEntity;
import pt.ipg.mcm.entities.UtilizadorPadeiroEntity;
import pt.ipg.mcm.entities.VUtilizadorClienteEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ResAddUtilizador;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UtilizadorDao {

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;


  public void addUtilizador(UtilizadorPadeiroEntity utilizadorPadeiroEntity) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();
      ResAddUtilizador resAddUtilizador = new ResAddUtilizador();

      CallableStatement call;
      //                             (?,?,?) (LOGIN, PASSWORD, NOME)
      call = connection.prepareCall("{call P_ADD_UTILIZADOR_PADEIRO(?,?,?)}");
      call.setString(1, utilizadorPadeiroEntity.getLogin());
      call.setString(2, utilizadorPadeiroEntity.getPassword());
      call.setString(3, utilizadorPadeiroEntity.getNome());

      call.executeUpdate();
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }

  }

//OBTER PADEIRO


  public PadeiroEntity getPadeiro(long idpadeiro) throws MestradoException {
    PadeiroEntity padeiroEntity = new PadeiroEntity();
    try {
      String sqlString = "SELECT PADEIRO.NOME,\n" +
          "FROM PADEIRO\n" +
          "WHERE PADEIRO.ID_PADEIRO = ?";

      Connection connection = mestradoDataSource.getConnection();
      PreparedStatement call = connection.prepareStatement(sqlString);
      call.setLong(1, idpadeiro);
      ResultSet rs = call.executeQuery();

      if (!rs.next()) {
        throw new MestradoException(Erro.PADEIRO_NAO_ENCONTRADO, idpadeiro);
      }

      padeiroEntity.setNome(rs.getString(1));


    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }

    return padeiroEntity;

  }


  public void createUserCliente(VUtilizadorClienteEntity utilizadorCliente) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();

      PreparedStatement ps = connection.prepareStatement("SELECT * FROM UTILIZADOR\n" +
          "WHERE  utilizador.LOGIN = ?");

      ps.setString(1, utilizadorCliente.getLogin());

      if (ps.executeQuery().next()) {
        throw new MestradoException(Erro.LOGIN_JA_EXISTENTE, utilizadorCliente.getLogin());
      }

      CallableStatement call = connection.prepareCall("{call P_ADD_UTILIZADOR_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");
      call.setLong(1, utilizadorCliente.getContribuinte());
      call.setString(2, utilizadorCliente.getNome());
      call.setString(3, utilizadorCliente.getMorada());
      call.setString(4, utilizadorCliente.getNporta());
      call.setDate(5, new Date(utilizadorCliente.getDatanascimento().getTime()));
      call.setString(6, utilizadorCliente.getEmail());
      call.setString(7, utilizadorCliente.getContacto());
      call.setLong(8, utilizadorCliente.getLocalidade());
      call.setString(9, utilizadorCliente.getLogin());
      call.setString(10, SHAUtils.hashingSHA256(utilizadorCliente.getPassword()));

      call.execute();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new MestradoException(Erro.TECNICO);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
