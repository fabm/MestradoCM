package pt.ipg.mcm.errors.wct.utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {

  private DatabasePropertiesReader databasePropertiesReader;
  private Map<String, Connection> connectionsMap;
  private String currentGroup;

  public DatabaseHelper() {
    databasePropertiesReader = new DatabasePropertiesReader();
    init();
  }

  private void init() {
    Map<String, ConnectionProperties> map = databasePropertiesReader.getConnectionsMap();
    connectionsMap = new HashMap<String, Connection>();
    for (Map.Entry<String, ConnectionProperties> entry : map.entrySet()) {
      String key = entry.getKey();
      ConnectionProperties properties = entry.getValue();
      try {
        connectionsMap.put(key, DriverManager.getConnection(properties.getUrl(), properties.getLogin(), properties.getPassword()));
      } catch (SQLException e) {
        throw new IllegalStateException(e);
      }
    }
  }

  public void setCurrentGroup(String currentGroup) {
    this.currentGroup = currentGroup;
  }

  public FluentResultSet createResultSet(String sqlName){
    NamedSqlQuery namedQuery = databasePropertiesReader.getQuery(currentGroup, sqlName);
    return new FluentResultSet(connectionsMap.get(namedQuery.getConnectionKey()),namedQuery.getQuery());
  }

  public void close() {
    for (Connection connection : connectionsMap.values()) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new IllegalStateException(e);
      }
    }
  }

}
