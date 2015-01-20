package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ReqAddCategoria;
import pt.ipg.mcm.xmodel.ResAddCategoria;
import pt.ipg.mcm.xmodel.ResCategoriasDesync;
import pt.ipg.mcm.xmodel.Retorno;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Singleton
public class CategoriaDao {

  private static Logger LOGGER = Logger.getLogger(CategoriaDao.class.getName());

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;


  public Long addCategoria(CategoriaEntity categoriaEntity) throws SQLException {
    Connection connection;
      connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("SELECT NOME,DESCRICAO from CATEGORIA");
      ResultSet rs = call.executeQuery();
      while (rs.next()) {
        LOGGER.log(Level.INFO, "nome:" + rs.getString(1) + " descrição:" + rs.getString(2));
      }
      rs.close();

      call = connection.prepareCall("{call P_ADD_CATEGORIA(?,?,?)");
      call.setString(1, categoriaEntity.getNome());
      call.setString(2, categoriaEntity.getDescricao());
      call.registerOutParameter(3, Types.NUMERIC);
      call.execute();

      return call.getLong(3);


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


  public List<CategoriaEntity> getDesync(Long versao) throws MestradoException {

    try {
      String sql = "SELECT ID_CATEGORIA,NOME,DESCRICAO FROM CATEGORIA WHERE SYNC > ?";
      PreparedStatement ps = mestradoDataSource.getConnection().prepareStatement(sql);

      ps.setLong(1,versao);

      ResultSet rs = ps.executeQuery();

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
