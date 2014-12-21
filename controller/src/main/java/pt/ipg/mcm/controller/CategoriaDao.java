package pt.ipg.mcm.controller;

import pt.ipg.mcm.xmodel.ReqAddCategoria;
import pt.ipg.mcm.xmodel.ResAddCategoria;
import pt.ipg.mcm.xmodel.TypeEnumResponse;
import pt.ipg.mcm.xmodel.TypeResponse;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class CategoriaDao {

  private static Logger LOGGER = Logger.getLogger(CategoriaDao.class.getName());

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;


  public ResAddCategoria addCategoria(ReqAddCategoria reqAddCategoria) {
    Connection connection;
    ResAddCategoria resAddCategoria = new ResAddCategoria();
    TypeResponse typeResponse = new TypeResponse();
    resAddCategoria.setTypeResponse(typeResponse);
    try {
      connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("SELECT NOME,DESCRICAO from CATEGORIA");
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        LOGGER.log(Level.INFO, "nome:" + rs.getString(1) + " descrição:" + rs.getString(2));
      }
      rs.close();

      call = connection.prepareCall("{call P_NOVA_CATEGORIA(?,?,?)");
      call.setString(1, reqAddCategoria.getNome());
      call.setString(2, reqAddCategoria.getDescricao());
      call.registerOutParameter(3, Types.NUMERIC);
      call.execute();

      resAddCategoria.setId(call.getLong(3));
      typeResponse.setMensagem("Categoria inserida com sucesso");
      typeResponse.setTipoResposta(TypeEnumResponse.OK);
      return resAddCategoria;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "sql problem", e);
      typeResponse.setMensagem("problema na inserção da categoria");
      return resAddCategoria;
    }

  }
}
