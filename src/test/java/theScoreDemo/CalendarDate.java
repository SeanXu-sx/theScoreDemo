package theScoreDemo;

import org.assertj.core.api.SoftAssertions;

import java.text.SimpleDateFormat;
import java.util.*;

//Created by: Sean Xu
//This CalendarDate generate recent four days date in order to use the dates to verify the dates on the sub-tab of
//Leagures details page. For example, today is MAY 3, then this class will return MAY 1, MAY 2, MAY 4, MAY 5

public class CalendarDate {

    public static String[] getDates() {
        String[] dates = new String[4];
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM d", Locale.ENGLISH);
        TimeZone timeZone = TimeZone.getTimeZone("America/Toronto");
        format.setTimeZone(timeZone);
        Calendar calendar1 = Calendar.getInstance(timeZone, Locale.CANADA);
        Calendar calendar2 = Calendar.getInstance(timeZone, Locale.CANADA);
        Calendar calendar3 = Calendar.getInstance(timeZone, Locale.CANADA);
        Calendar calendar4 = Calendar.getInstance(timeZone, Locale.CANADA);
        calendar1.setTime(date);
        calendar2.setTime(date);
        calendar3.setTime(date);
        calendar4.setTime(date);

        calendar1.add(Calendar.DATE, -2);
        date = calendar1.getTime();
        dates[0] = format.format(date).toUpperCase();
        calendar2.add(Calendar.DATE, -1);
        date = calendar2.getTime();
        dates[1] = format.format(date).toUpperCase();
        calendar3.add(Calendar.DATE, +1);
        date = calendar3.getTime();
        dates[2] = format.format(date).toUpperCase();
        calendar4.add(Calendar.DATE, +2);
        date = calendar4.getTime();
        dates[3] = format.format(date).toUpperCase();
        return dates;
    }
}
