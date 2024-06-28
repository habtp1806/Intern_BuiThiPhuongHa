package model;

import enums.RailwayStation;
import enums.SeatType;

public class BookTicket {
    private String departDate;
    private RailwayStation departStation;
    private RailwayStation arrivalStation;
    private SeatType seatType;
    private String ticketAmount;

    public BookTicket(String departDate, SeatType seatType, String ticketAmount) {
        this.departDate = departDate;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    // Constructor with parameters
    public BookTicket(String departDate, RailwayStation departStation, RailwayStation arrivalStation, SeatType seatType, String ticketAmount) {
        this.departDate = departDate;
        this.departStation = departStation;
        this.arrivalStation = arrivalStation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public RailwayStation getDepartStation() {
        return departStation;
    }

    public void setDepartStation(RailwayStation departStation) {
        this.departStation = departStation;
    }

    public RailwayStation getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(RailwayStation arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
}
