package models;

import enums.ERoomType;
import models.common.Entity;

import java.util.ArrayList;

public class Room extends Entity{
  private String name;
  private ERoomType type;
  private int guestNumberMax;
  private double dailyPrice;
  private String description;
  private Boolean isReservation;

  private ArrayList<Reservation> reservations;

  public Room(String name, ERoomType type, int guestNumberMax, double dailyPrice, String description, boolean isReservation) {
    this.name = name;
    this.type = type;
    this.guestNumberMax = guestNumberMax;
    this.description = description;
    this.dailyPrice = calculateValuesOfTypeRoom(dailyPrice, type);
    this.isReservation = isReservation;

    this.reservations = new ArrayList<Reservation>();
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

  private double calculateValuesOfTypeRoom(double price, ERoomType type){
    switch (type) {
      case Suite:
          return price + (0.3 * price);

      case Familia:
          return price + (0.4 * price);

      case Individual:
          return price + (0.15 * price);

      default:
          return price + (0.1 * price);
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
