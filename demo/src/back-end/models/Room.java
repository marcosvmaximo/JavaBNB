package models;
import java.util.ArrayList;

import enums.ERoomType;
import models.common.Entity;

public class Room extends Entity{
  private String name;
  private ERoomType type;
  private int guestNumberMax;
  private double dailyPrice;
  private String description;
  private Boolean isReservation;

  private ArrayList<Reservation> reservations;

  public Room(String name, ERoomType type, int guestNumberMax, double dailyPrice, String description) {
    this.name = name;
    this.type = type;
    this.guestNumberMax = guestNumberMax;
    this.description = description;
    this.dailyPrice = dailyPrice * calculateValuesOfTypeRoom(type);
    this.isReservation = false;

    this.reservations = new ArrayList<Reservation>(reservations);
  }

  public void addReserve(Reservation reserva){
    if(reserva == null){
      throw new IllegalArgumentException("Reserva inválido.");
    }

    if(reserva.getGuestNumbersToReserve() >= this.guestNumberMax){
      throw new IllegalArgumentException("Não é possível realizar uma reserva com a quantidade de hospedes maior que o quarto comporta.");
    }

    this.reservations.add(reserva);
    isReservation = true;
  }

  public void completeReserve(Reservation reserva){
    Boolean existReservation = this.reservations.contains(reserva);
    if(!existReservation){
      throw new IllegalArgumentException("Não é possível completar uma reserva inexistente.");
    }

    reserva.finish();
    isReservation = false;
  }

  public double calculateTotalPrice(int days){
    return days * dailyPrice;
  }

  private double calculateValuesOfTypeRoom(ERoomType type){
    switch (type) {
      case Suite:
          return 0.3;

      case Familia:
          return 0.4;

      case Individual:
          return 0.15;

      default:
          return 0.1;
  }
  }

  public String getName() {
    return name;
  }

  public ERoomType getType() {
    return type;
  }

  public int getGuestNumberMax() {
    return guestNumberMax;
  }

  public double getDailyPrice() {
    return dailyPrice;
  }

  public ArrayList<Reservation> getReservations() {
    return reservations;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getIsReservation() {
    return isReservation;
  }

  @Override
  public void Validate() {
    if (guestNumberMax <= 0 || dailyPrice <= 0) {
      throw new IllegalArgumentException("Dados inválidos para o quarto.");
    }
  }
}
