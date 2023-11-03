package interfaces;

import models.Reservation;
import models.Room;

import java.util.ArrayList;

public interface ILodgeService {
  ArrayList<Reservation> GetAllReserveByUser(String cpfUser);
  ArrayList<Room> GetAllRooms();
  Boolean CreateReserve();

  boolean RegisterUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf);
  boolean LoginUser(String cpf, String dataNascimento);

}
