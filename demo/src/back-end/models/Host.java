package models;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import models.common.User;

public class Host extends User {
    private ArrayList<Reservation> reservation;
    private int maxReservationNumber = 2;
    
    public Host(String name, String cpf, Date birth, Contact contact) {
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
        LocalDate today = LocalDate.now();
        LocalDate birthLocalDate = getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period period = Period.between(birthLocalDate, today);

        return period.getYears();
    }

    public int getMaxReservationNumber(){
        return this.maxReservationNumber;
    }
    
    @Override
    public void Validate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Validate'");
    }
}
