package interfaces;

import java.sql.Date;
import java.util.ArrayList;

import models.Host;
import models.Reservation;
import models.Room;

public interface ILodgeService {
  ArrayList<Reservation> GetAllReserveByUser(int idUser);  
  ArrayList<Room> GetAllRooms();

  // pegar dados necessários do front-end
  Boolean CreateReserve();

  Boolean CreateReserve(int days);

  Boolean FinishReserve();
  Boolean CancelReserve();

  Boolean RegisterUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf);
  Host LoginUser(String cpf, Date dataNascimento);
}
