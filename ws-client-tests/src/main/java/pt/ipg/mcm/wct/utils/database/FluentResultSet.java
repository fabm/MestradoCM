package pt.ipg.mcm.wct.utils.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FluentResultSet {
  private Connection connection;
  private String sql;
  private PreparedStatement preparedStatement = null;
  private Statement statement = null;
  private ResultSet resultSet = null;

  public FluentResultSet(Connection connection, String sql) {
    this.connection = connection;
    this.sql = sql;
  }

  private PreparedStatement getPreparedStatement() throws SQLException {
    if (preparedStatement == null) {
      preparedStatement = connection.prepareStatement(sql);
    }
    return preparedStatement;

  }

  public FluentResultSet setInt(int index, int value) throws SQLException {
    getPreparedStatement().setInt(index, value);
    return this;
  }

  public FluentResultSet setLong(int index, long value) throws SQLException {
    getPreparedStatement().setLong(index, value);
    return this;
  }

  public FluentResultSet setString(int index, String value) throws SQLException {
    getPreparedStatement().setString(index, value);
    return this;
  }

  public ResultSet build() throws SQLException {
    if (preparedStatement == null) {
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);
      return resultSet;
    } else {
      resultSet = preparedStatement.executeQuery(sql);
      return resultSet;
    }
  }

  public void close() throws SQLException {
    if(resultSet==null){
        throw new IllegalStateException("no resultSet to close");
    }
    resultSet.close();
    resultSet = null;
    if (preparedStatement != null) {
      preparedStatement.close();
      preparedStatement = null;
    }
    if (statement != null) {
      statement.close();
      statement = null;
    }
  }
}
