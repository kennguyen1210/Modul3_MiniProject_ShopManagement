package ra.business.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatTime {
    public static LocalDate formatDate(String date){
        DateTimeFormatter s = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,s);
    }
    public static String formatTime(LocalDate date){
        try {
            String year = String.valueOf(date.getYear());
            String month = String.valueOf(date.getMonthValue());
            String day = String.valueOf(date.getDayOfMonth());
            return day+"/"+month +"/"+year;
        }catch (NullPointerException e){
            return null;
        }
    }
}
