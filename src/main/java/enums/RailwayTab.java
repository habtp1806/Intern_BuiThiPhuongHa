package enums;

public enum RailwayTab {

    HOME("Home"),
    FAQ("FAQ"),
    CONTACT("Contact"),
    TIMETABLE("Timetable"),
    TICKET_PRICE("Ticket price"),
    BOOK_TICKET("Book ticket"),
    REGISTER("Register"),
    LOGIN("Login"),
    MY_TICKET("My ticket"),
    CHANGE_PASSWORD("Change password"),
    LOG_OUT("Log out");

    private final String value;

    RailwayTab(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

