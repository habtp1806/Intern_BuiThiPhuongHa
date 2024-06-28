package utils;

import base.Config;
import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class DataUtils {
    public static User getUserFromJson(String key) {
        JSONObject jsonObject = Config.getJsonObject();
        JSONObject loginData = jsonObject.getJSONObject(key);
        String email = loginData.getString("email");
        String password = loginData.getString("password");
        return new User(email, password);
    }
    

    @DataProvider(name = "bookTicketDataProvider")
    public static Object[][] bookTicketDataProvider() {
        return new Object[][]{
                {
                        new BookTicket(DateUtils.calculateNextDepartDate(12), RailwayStation.NHA_TRANG, RailwayStation.HUE, SeatType.SOFT_BED_AIR_CONDITIONER, "1")
                },
                {
                        new BookTicket(DateUtils.calculateNextDepartDate(25), RailwayStation.NHA_TRANG, RailwayStation.SAI_GON, SeatType.SOFT_SEAT_AIR_CONDITIONER, "1")
                },
        };
    }


}
