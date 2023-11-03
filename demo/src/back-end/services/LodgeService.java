package services;

import interfaces.ILodgeService;
import models.Contact;
import models.Host;
import models.Reservation;
import models.Room;
import repositories.LodgeRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LodgeService implements ILodgeService {

  @Override
  public ArrayList<Reservation> GetAllReserveByUser(String cpfUser) {
    if(cpfUser == null || cpfUser.isBlank()){
      return null;
    }

    LodgeRepository repository = new LodgeRepository();
    return repository.getAllReservationByCpf(cpfUser);
  }

  @Override
  public ArrayList<Room> GetAllRooms() {
    LodgeRepository repository = new LodgeRepository();
    return repository.getAllRooms();
  }

  @Override
  public Boolean CreateReserve() {

//    // Buscar Host
//    Host host = new Host();
//    // Buscar Room
//    Room room = new Room();
//
//    Reservation reservation = new Reservation();
//
//    LodgeRepository repository = new LodgeRepository();
//    var result = repository.createReservation(reservation);

    return true;
  }
  
  @Override
  public boolean RegisterUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf) {
    if(nomeCompleto == null || nomeCompleto.isBlank()){
      return false;
    }

    if(numeroTelefone == null || numeroTelefone.isBlank()){
      return false;
    }

    if(cpf == null || cpf.isBlank()){
      return false;
    }

    String[] partesData = dataNascimento.split("/");

    int dia = Integer.parseInt(partesData[0]);
    int mes = Integer.parseInt(partesData[1]);
    int ano = Integer.parseInt(partesData[2]);

    LocalDateTime dateTime = LocalDateTime.of(ano, mes, dia, 0, 0);

    Contact contact = new Contact(numeroTelefone);
    Host host = new Host(nomeCompleto, cpf, dateTime, contact);

    LodgeRepository repository = new LodgeRepository();
    repository.createHost(host);
    // salva o host
    return true;
  }

  @Override
  public boolean LoginUser(String cpf, String dataNascimento) {
    if(cpf.isBlank() || cpf == null){
      return false;
    }

    if(dataNascimento == null){
      return false;
    }

    String[] partesData = dataNascimento.split("/");

    int dia = Integer.parseInt(partesData[0]);
    int mes = Integer.parseInt(partesData[1]);
    int ano = Integer.parseInt(partesData[2]);

    Date data = new Date(ano - 1900, mes - 1, dia);

    LodgeRepository repository = new LodgeRepository();
    return repository.loginHost(cpf, data);
  }
}
