package services;

import interfaces.ILodgeService;
import models.Contact;
import models.Host;
import models.Reservation;
import models.Room;
import repositories.HostRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LodgeService implements ILodgeService {

  @Override
  public ArrayList<Reservation> GetAllReserveByUser(int idUser) {
    // acessa repositorio
    // busca usuario especifico
    return new ArrayList<>();
    // retorna
  }

  @Override
  public ArrayList<Room> GetAllRooms() {
    // acessa repositorio
    // Busca todos quartos
    // retorna
    return new ArrayList<>();
  }

  @Override
  public Boolean CreateReserve() {
    return null;
  }

  @Override
  public Boolean CreateReserve(int days) {
    // Verificar se quarto está disponível por todo esse tempo
    // Criar o objeto de reserva
    // Salvar
    return true;
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
  public Boolean RegisterUser(String nomeCompleto, String dataNascimento, String numeroTelefone, String cpf) {
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

    HostRepository repository = new HostRepository();
    repository.adicionarPessoa(host);
    // salva o host
    return true;
  }

  @Override
  public Host LoginUser(String cpf, Date dataNascimento) {
    // Busca o host com esse dado,
    // Retorna ok com os dados desse
    return new Host(null, null, null, null);
  }
}
