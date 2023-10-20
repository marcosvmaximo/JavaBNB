package services;

import java.util.ArrayList;

import interfaces.ILodgeService;
import models.Reservation;
import models.Room;

public class LodgeService implements ILodgeService {

  @Override
  public ArrayList<Reservation> GetAllReserveByUser(int idUser) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'GetAllReserveByUser'");
  }

  @Override
  public ArrayList<Room> GetAllRooms() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'GetAllRooms'");
  }

  @Override
  public Boolean CreateReserve() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'CreateReserve'");
  }

  @Override
  public Boolean FinishReserve() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'FinishReserve'");
  }

  @Override
  public Boolean CancelReserve() {
        throw new UnsupportedOperationException("Unimplemented method 'RegisterUser'");
    // busca a reserva no bd
    // verifica se ela existe 
    // reserva.Cancelar()
    // Salvar();
  }

  @Override
  public Boolean RegisterUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'RegisterUser'");
  }

  @Override
  public Boolean LoginUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'LoginUser'");
  }
  
}
