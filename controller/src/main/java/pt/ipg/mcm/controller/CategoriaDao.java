package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddCategoria;
import pt.ipg.mcm.xmodel.ResAddCategoria;
import pt.ipg.mcm.xmodel.Retorno;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
      resAddCategoria.setRetorno(new Retorno(1, "Categoria inserida com sucesso"));
      return resAddCategoria;
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "sql problem", e);
      resAddCategoria.setRetorno(new Retorno(new MestradoException(Erro.TECNICO)));
      return resAddCategoria;
    }

  }


  public List<CategoriaEntity> getAll() throws MestradoException {

    try {
      Statement call = mestradoDataSource.getConnection().createStatement();
      String sql = "SELECT ID_CATEGORIA,NOME, DESCRICAO FROM CATEGORIA";
      ResultSet rs = call.executeQuery(sql);

      List<CategoriaEntity> lista = new ArrayList<CategoriaEntity>();
      while (rs.next()) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setIdCategoria(rs.getLong(1));
        categoriaEntity.setNome(rs.getString(2));
        categoriaEntity.setDescricao(rs.getString(3));
        lista.add(categoriaEntity);
      }
      return lista;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
