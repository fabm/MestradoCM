package pt.ipg.mcm.errors.wct.utils.database;

public class NamedSqlQuery {
  private String connectionKey;
  private String query;

  public NamedSqlQuery(String connectionKey, String query) {
    this.connectionKey = connectionKey;
    this.query = query;
  }

  public String getConnectionKey() {
    return connectionKey;
  }

  public String getQuery() {
    return query;
  }

}
