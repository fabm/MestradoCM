package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.LocalidadeEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by BrrF on 30-01-2015.
 */
@Stateless
@Singleton
public class LocalidadeDao {


   private static Logger LOGGER = Logger.getLogger(LocalidadeDao.class.getName());

    @Resource(lookup = "jdbc/mestrado")
    private DataSource mestradoDataSource;

  public List<LocalidadeEntity> getAll() throws MestradoException{

      try{
          java.sql.Statement call = mestradoDataSource.getConnection().createStatement();
          String sql = "SELECT ID_LOCALIDADE, LOCALIDADE FROM LOCALIDADE";
          ResultSet rs = call.executeQuery(sql);

          List<LocalidadeEntity> lista = new ArrayList<LocalidadeEntity>();
          while (rs.next()) {
              LocalidadeEntity localidadeEntity = new LocalidadeEntity();
              localidadeEntity.setIdLocalidade(rs.getLong(1));
              localidadeEntity.setLocalidade(rs.getString(2));
              lista.add(localidadeEntity);
          }
          return lista;


      }catch (SQLException  e){
          throw  new MestradoException(Erro.TECNICO);
      }
  }

}
