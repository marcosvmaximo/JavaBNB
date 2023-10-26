
package models;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import models.common.Entity;

public class Reservation extends Entity {

  private Host host;
  private int idHost;
  private Room room;
  private int idRoom;

  private Date checkIn;
  private Date checkOut;
  private int daysTotal;
  private int guestNumbersToReserve;
  private double totalPrice;
  private boolean isActive;
  private boolean isCompleted;

  public Reservation(
      Host host,
      int idHost,
      Room room,
      int idRoom,
      Date checkIn,
      Date checkOut,
      int guestNumbersToReserve) {

    this.host = host;
    this.idHost = idHost;
    this.room = room;
    this.idRoom = idRoom;

    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.guestNumbersToReserve = guestNumbersToReserve;

    this.daysTotal = calculateDayGap(checkIn, checkOut);
    this.totalPrice = room.calculateTotalPrice(daysTotal);
    this.isActive = true;
    this.isCompleted = false;
  }

  public void cancel() {
    this.isCompleted = true;
  }

  public void finish() {
    this.isCompleted = true;
  }

  private int calculateDayGap(Date checkInDate, Date checkOutDate) {
    LocalDate checkInLocalDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate checkOutLocalDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    long stayDuration = ChronoUnit.DAYS.between(checkInLocalDate, checkOutLocalDate);

    return (int) stayDuration;
  }

  public int getGuestNumbersToReserve() {
    return guestNumbersToReserve;
  }

  public Host getHost() {
    return host;
  }

  public int getIdHost() {
    return idHost;
  }

  public Room getRoom() {
    return room;
  }

  public int getIdRoom() {
    return idRoom;
  }

  public Date getCheckIn() {
    return checkIn;
  }

  public Date getCheckOut() {
    return checkOut;
  }

  public int getDaysTotal() {
    return daysTotal;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public boolean isActive() {
    return isActive;
  }

  @Override
  public void Validate() {

    if (checkIn == null || checkOut == null || checkIn.after(checkOut)) {
      throw new IllegalArgumentException("Datas de check-in e check-out inválidas.");
    }

    if (guestNumbersToReserve <= 0 || guestNumbersToReserve > room.getGuestNumberMax()) {
      throw new IllegalArgumentException("Quantidade de hóspedes a serem reservados inválida.");
    }
  }
}
