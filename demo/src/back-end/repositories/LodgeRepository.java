package repositories;


import enums.ERoomType;
import models.Contact;
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
    try {
      String updateRoomQuery = "UPDATE Room SET esta_reservado = ? WHERE id = ?";
      PreparedStatement roomUpdateStatement = connection.prepareStatement(updateRoomQuery);
      roomUpdateStatement.setBoolean(1, false);
      roomUpdateStatement.setInt(2, reservation.getIdRoom());
      roomUpdateStatement.executeUpdate();

      String insertQuery = "INSERT INTO Reservation (check_in, check_out, dias_totais, numero_convidados_reserva, preco_total, esta_ativo, fk_Host_id, fk_Room_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

      preparedStatement.setDate(1, Date.valueOf(reservation.getCheckIn()));
      preparedStatement.setDate(2, Date.valueOf(reservation.getCheckOut()));
      preparedStatement.setInt(3, reservation.getDaysTotal());
      preparedStatement.setInt(4, reservation.getGuestNumbersToReserve());
      preparedStatement.setDouble(5, reservation.getTotalPrice());
      preparedStatement.setBoolean(6, reservation.isActive());

      preparedStatement.setInt(7, reservation.getIdHost());
      preparedStatement.setInt(8, reservation.getIdRoom());
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
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

        Room room = null;

        ArrayList<Room> rooms = getAllRooms();
        for (Room r: rooms) {
          if(r.getId() == idRoom){
            room = r;
            break;
          }
        }

        Host host = new Host(null, null, null, null);

        Reservation reservation = new Reservation(null, idHost, room, room.getId(), checkIn.toLocalDate(), checkOut.toLocalDate(), guestNumber);
        reservation.setId(resultSet.getInt("id"));

        reservations.add(reservation);
      }

      return reservations;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>(); // Tratamento de erro
    }
  }

  public ArrayList<Room> getAllRooms() {
    try {
      String sql = "SELECT * FROM Room";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      ResultSet resultSet = preparedStatement.executeQuery();

      ArrayList<Room> rooms = new ArrayList<>();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        int capacidade = resultSet.getInt("capacidade");
        int tipoNumero = resultSet.getInt("tipo");
        float precoDiaria = resultSet.getFloat("preco_diaria");
        String descricao = resultSet.getString("descricao");
        boolean estaReservado = resultSet.getBoolean("esta_reservado");

        ERoomType tipo = GetTypeOfRoom(tipoNumero);
        Room room = new Room(nome, tipo, capacidade, precoDiaria, descricao, estaReservado);
        room.setId(id);

        rooms.add(room);
      }

      return rooms;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ArrayList<>(); // Tratamento de erro
    }
  }

  private ERoomType GetTypeOfRoom(int number){
    switch (number){
      case 1:
        return ERoomType.Suite;
      case 2:
        return ERoomType.Familia;
      case 3:
        return ERoomType.Individual;
    }

    return ERoomType.Familia;
  }

  public Host getHostByCpf(String cpf) {
    try {
      String sql = "SELECT * FROM Host h WHERE h.cpf = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, cpf);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        var dataAniversario = resultSet.getDate("data_aniversario");
        String cpfStr = resultSet.getString("cpf");
        String contato = resultSet.getString("contato");

        Contact contact = new Contact(contato);

        Host host = new Host(nome, cpfStr, dataAniversario.toLocalDate(), contact);
        host.setId(id);
        return host;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }
}