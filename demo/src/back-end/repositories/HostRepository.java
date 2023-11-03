package repositories;


import models.Host;

import java.sql.*;

public class HostRepository {
  public void adicionarPessoa(Host host) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3307/test?serverTimezone=UTC";
      String username = "root";
      String password = "8837";

      Connection connection = DriverManager.getConnection(url, username, password);

      String sql = "SELECT * FROM Aula_Poo";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        // Processar os dados do resultado da consulta...
      }

      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

  }
}