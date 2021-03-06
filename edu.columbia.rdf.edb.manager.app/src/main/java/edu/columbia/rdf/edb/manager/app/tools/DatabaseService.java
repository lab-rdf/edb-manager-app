package edu.columbia.rdf.edb.manager.app.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jebtk.database.DatabaseConnection;
import org.jebtk.database.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService {
  public static Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // return
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/array_server?user=arrayserver&password=v4392LrT6Qh8fKyC");

    // return
    // DriverManager.getConnection("jdbc:postgresql://localhost/experiment_store?user=antony&password=w0tnofear");
    //return DriverManager.getConnection(
    //    "jdbc:postgresql://lab-rdf-edbw.cxtlfoxy3mpr.us-east-1.rds.amazonaws.com:5432/edbw?user=edbw&password=261t8vzBmdYy3YG2");
    return DriverManager.getConnection(
        "jdbc:postgresql://18.209.96.125:5432/edbw?user=edbw&password=261t8vzBmdYy3YG2");
  }

  public static Connection getConnection(EDBLogin login)
      throws SQLException, ClassNotFoundException {
    return getConnection(login.getServer(),
        login.getDb(),
        login.getUser(),
        login.getPassword());
  }

  public static Connection getConnection(String server,
      String db,
      String user,
      String password) throws SQLException, ClassNotFoundException {
    Class.forName("org.postgresql.Driver");

    String connection = "jdbc:postgresql://" + server + "/" + db + "?user="
        + user + "&password=" + password;

    System.err.println(connection);

    return DriverManager.getConnection(connection);
  }

  public static DatabaseConnection getDatabase() throws SQLException {
    return new JDBCConnection(getConnection());
  }
}
