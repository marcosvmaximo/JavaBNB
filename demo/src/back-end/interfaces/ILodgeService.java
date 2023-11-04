package interfaces;

import models.Host;
import models.Reservation;
import models.Room;

import java.util.ArrayList;

public interface ILodgeService {
  ArrayList<Reservation> getAllReserveByUser(String cpfUser);
  ArrayList<Room> getAllRooms();
  Boolean createReserve(Reservation reservation);
  Room getRoomByName(String roomName);
  Host getHostByCpf(String user);

  boolean registerUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf);
  boolean loginUser(String cpf, String dataNascimento);

}
