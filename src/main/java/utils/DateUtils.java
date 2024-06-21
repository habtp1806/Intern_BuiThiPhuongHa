package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String calculateNextDepartDate(int daysToAdd) {
        LocalDate departDate = LocalDate.now().plusDays(daysToAdd);
        return departDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }
}
