package interfaces;

import models.Reservation;
import models.Room;

import java.util.ArrayList;

public interface ILodgeService {
  ArrayList<Reservation> GetAllReserveByUser(String cpfUser);
  ArrayList<Room> GetAllRooms();

  // pegar dados necessários do front-end
  Boolean CreateReserve();

  Boolean CreateReserve(int days);

  Boolean FinishReserve();
  Boolean CancelReserve();

  boolean RegisterUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf);
  boolean LoginUser(String cpf, String dataNascimento);

}
