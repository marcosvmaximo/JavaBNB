package interfaces;

import java.util.ArrayList;

import models.Reservation;
import models.Room;

public interface ILodgeService {
  ArrayList<Reservation> GetAllReserveByUser(int idUser);  
  ArrayList<Room> GetAllRooms();

  // pegar dados necessários do front-end
  Boolean CreateReserve();
  Boolean FinishReserve();
  Boolean CancelReserve();

  Boolean RegisterUser();
  Boolean LoginUser();
}
