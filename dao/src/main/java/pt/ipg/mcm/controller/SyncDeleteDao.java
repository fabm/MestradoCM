package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.SyncDeleteEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SyncDeleteDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public List<SyncDeleteEntity> getRowsToDelete(long versao) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();
      PreparedStatement ps = connection.prepareStatement("SELECT ID_SYNC, TABELA FROM SYNC_DELETE WHERE SYNC > ?");
      ps.setLong(1,versao);
      ResultSet rs = ps.executeQuery();
      List<SyncDeleteEntity> list = new ArrayList<SyncDeleteEntity>();
      while (rs.next()) {
        SyncDeleteEntity syncDeleteEntity = new SyncDeleteEntity();
        syncDeleteEntity.setIdSync(rs.getLong(1));
        syncDeleteEntity.setTabela(rs.getString(2));
        list.add(syncDeleteEntity);
      }
      return list;
    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }
}
