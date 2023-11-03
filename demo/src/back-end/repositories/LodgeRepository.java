package repositories;


import models.Host;
import models.Reservation;
import models.Room;

import java.sql.*;
import java.util.ArrayList;

public class LodgeRepository {
  public Connection connection;
  public LodgeRepository(){
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3307/poo?serverTimezone=UTC";
      String username = "root";
      String password = "8837";

      try {
        connection = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  public boolean createHost(Host host) {
    try {
      String sql = "INSERT INTO Host (nome, data_aniversario, cpf, contato) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, host.getName());
      preparedStatement.setDate(2, java.sql.Date.valueOf(host.getBirthDate()));
      preparedStatement.setString(3, host.getCpf());
      preparedStatement.setString(4, host.getContact());

      //      Statement statement = connection.createStatement();
      //      ResultSet resultSet = statement.executeQuery(sql);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean createReservation(Reservation reservation){
    return true;
  }

  public boolean loginHost(String cpf, java.sql.Date dataNascimento) {
    try {
      String sql = "SELECT 1 FROM Host WHERE cpf = ? AND data_aniversario = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, cpf);
      preparedStatement.setDate(2, dataNascimento);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return true;  // Host encontrado
      } else {
        return false; // Host não encontrado
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public ArrayList<Reservation> getAllReservationByCpf(String cpfUser) {
    try {
      String sql = "SELECT R.*, H.*, Ro.* " +
                  "FROM Reservation AS R " +
                  "INNER JOIN Host AS H ON R.fk_Host_id = H.id " +
                  "INNER JOIN Room AS Ro ON R.fk_Room_id = Ro.id " +
                  "WHERE H.cpf = ? AND R.esta_ativo IS TRUE ";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, cpfUser);

      ResultSet resultSet = preparedStatement.executeQuery();

      ArrayList<Reservation> reservations = new ArrayList<>();
      while (resultSet.next()) {
        int idHost = resultSet.getInt("fk_Host_id");
        int idRoom = resultSet.getInt("fk_Room_id");

        var checkIn = resultSet.getDate("check_in");
        var checkOut = resultSet.getDate("check_out");
        var guestNumber = resultSet.getInt("numero_convidados_reserva");

        Host host = new Host(null, null, null, null);
        Room room = new Room(null, null,0, 0, null);

        Reservation reservation = new Reservation(host, idHost, room, idRoom, checkIn, checkOut, guestNumber);
        reservation.setId(resultSet.getInt("id"));

        reservations.add(reservation);
      }

      return reservations;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>(); // Tratamento de erro
    }
  }
}