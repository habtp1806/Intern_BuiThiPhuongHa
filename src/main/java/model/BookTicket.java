package model;

public class BookTicket {
    private String departDate;
    private String departFrom;
    private String arriveAt;
    private String seatType;
    private String ticketAmount;

    public BookTicket() {
        this.departDate = "";
        this.departFrom = "";
        this.arriveAt = "";
        this.seatType = "";
        this.ticketAmount = "";
    }

    // Constructor with parameters
    public BookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount) {
        this.departDate = (departDate != null) ? departDate : "";
        this.departFrom = (departFrom != null) ? departFrom : "";
        this.arriveAt = (arriveAt != null) ? arriveAt : "";
        this.seatType = (seatType != null) ? seatType : "";
        this.ticketAmount = (ticketAmount != null) ? seatType : "";
    }


    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public String getArriveAt() {
        return arriveAt;
    }

    public void setArriveAt(String arriveAt) {
        this.arriveAt = arriveAt;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(String ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
}
