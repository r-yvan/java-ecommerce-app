package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
  private static final String URL = "jdbc:postgresql://localhost:5432/java-ecommerce-app-db";
  private static final String USER = "postgres";
  private static final String PASSWORD = "Nry369&2008";
  
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}

