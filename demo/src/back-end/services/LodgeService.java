package services;

import java.sql.Date;
import java.util.ArrayList;

import interfaces.ILodgeService;
import models.Contact;
import models.Host;
import models.Reservation;
import models.Room;

public class LodgeService implements ILodgeService {

  @Override
  public ArrayList<Reservation> GetAllReserveByUser(int idUser) {
    // acessa repositorio
    // busca usuario especifico
    // retorna
  }

  @Override
  public ArrayList<Room> GetAllRooms() {
    // acessa repositorio
    // Busca todos quartos
    // retorna
  }

  @Override
  public Boolean CreateReserve(int days) {
    // Verificar se quarto está disponível por todo esse tempo
    // Criar o objeto de reserva
    // Salvar
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
  public Boolean RegisterUser(String nomeCompleto, Date dataNascimento, String numeroTelefone, String cpf) {
    if(nomeCompleto == null || nomeCompleto.isBlank()){
      return false;
    }

    if(numeroTelefone == null || numeroTelefone.isBlank()){
      return false;
    }

    if(cpf == null || cpf.isBlank()){
      return false;
    }

    Contact contact = new Contact(numeroTelefone);
    Host host = new Host(numeroTelefone, cpf, dataNascimento, contact);

    HostRepository repository = new HostRepository();
    repository.adicionarPessoa(host);
    // salva o host
    return true;
  }

  @Override
  public Host LoginUser(String cpf, Date dataNascimento) {
    // Busca o host com esse dado,
    // Retorna ok com os dados desse
  }
}
