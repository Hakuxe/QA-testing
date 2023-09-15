package seleniumFramework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDate(){
        Calendar calendar =  Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getFutureDate(int extraDays){
        Calendar calendar =  Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, extraDays);
        return calendar.getTime();
    }

    public static String formatDate(Date date, String pattern){
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
