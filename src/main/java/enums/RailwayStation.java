package enums;

public enum RailwayStation {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");


    private final String value;

    RailwayStation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

