package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.LocalidadeEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class LocalidadeDao {


  private static Logger LOGGER = Logger.getLogger(LocalidadeDao.class.getName());

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public List<LocalidadeEntity> getAll() throws MestradoException {

    try {
      Statement call = mestradoDataSource.getConnection().createStatement();
      String sql = "SELECT ID_LOCALIDADE, LOCALIDADE, CODIGO_POSTAL_NUM FROM LOCALIDADE";

      return getLocalidadeEntities(call.executeQuery(sql));

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  public List<LocalidadeEntity> getFiltered(int page,String filter) throws MestradoException {

    try {
      String sql =
          "        SELECT ID_LOCALIDADE, LOCALIDADE, CODIGO_POSTAL FROM V_LOCALIDADE_ORD\n" +
              "        WHERE LOCALIDADE LIKE ?\n";

      sql = PagedSql.getPagedQueryString(sql,page,50);
      PreparedStatement call = mestradoDataSource.getConnection().prepareStatement(sql);

      call.setString(1,"%"+filter.toUpperCase()+"%");

      return getLocalidadeEntities(call.executeQuery());


    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  public List<LocalidadeEntity> getFiltered(int page) throws MestradoException {

    try {
      String sql ="SELECT ID_LOCALIDADE, LOCALIDADE, CODIGO_POSTAL FROM V_LOCALIDADE_ORD";
      sql = PagedSql.getPagedQueryString(sql,page,50);
      CallableStatement call = mestradoDataSource.getConnection().prepareCall(sql);

      return getLocalidadeEntities(call.executeQuery());

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }

  private List<LocalidadeEntity> getLocalidadeEntities(ResultSet rs) throws SQLException {
    List<LocalidadeEntity> lista = new ArrayList<LocalidadeEntity>();
    while (rs.next()) {
      LocalidadeEntity localidadeEntity = new LocalidadeEntity();
      localidadeEntity.setIdLocalidade(rs.getLong(1));
      localidadeEntity.setLocalidade(rs.getString(2));
      localidadeEntity.setCodPostalNum(rs.getInt(3));
      lista.add(localidadeEntity);
    }
    return lista;
  }


}
