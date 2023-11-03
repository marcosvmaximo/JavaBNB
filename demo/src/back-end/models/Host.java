package models;

import models.common.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Host extends User {
    private ArrayList<Reservation> reservation;
    private int maxReservationNumber = 2;
    
    public Host(String name, String cpf, LocalDateTime birth, Contact contact) {
        super(name, cpf, birth, contact);
        
        reservation = new ArrayList<Reservation>();
    }

    public void GenerateReservation(Reservation reserva){
        if(reserva == null){
            throw new IllegalArgumentException("Reserva inválido.");
        }

        this.reservation.add(reserva);
    }

    public ArrayList<Reservation> getReservations(){
        return this.reservation;
    }

    @Override
    public int getAge() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime birthDateTime = LocalDateTime.ofInstant(getBirthDate().toInstant(), ZoneId.systemDefault());
//
//        return Period.between(birthDateTime.toLocalDate(), now.toLocalDate()).getYears();
        return 1;
    }

    @Override
    public void Validate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Validate'");
    }
}
