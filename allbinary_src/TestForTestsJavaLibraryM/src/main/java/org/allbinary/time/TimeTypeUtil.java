package org.allbinary.time;

//import java.time.Instant;
//import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author User
 */
public class TimeTypeUtil {

    private static final TimeTypeUtil instance = new TimeTypeUtil();

    /**
     * @return the instance
     */
    public static TimeTypeUtil getInstance() {
        return instance;
    }
    
    public final String NIGHT = "Night";
    public final String DAY = "Day";
//    public final String DUSK = "Dusk";
//    public final String DAWN = "Dawn";
//    public final String MORNING = "Morning";
//    //Before 8 am or closer to 11 am, respectively.
//    public final String EARLY_MORNING = "Early Morning";
//    public final String LATE_MORNING = "Late Morning";
//    ///MIDDAY This is the middle of the day, also called "NOON" (12:00 hours).
//    public final String NOON = "Noon";
//    //Late Night/Wee Hours: Time immediately following midnight, often considered 12 am to 4 am.    
//    public final String LATE_NIGHT = "Late Night/Wee Hours";
//    public final String MIDNIGHT = "Midnight";
//    //The period between morning and midday.
//    public final String FORENOON = "Forenoon";
//    //The time from noon to evening, often subdivided into early (1–3 pm) and late (4–5 pm).
//    public final String AFTERNOON = "Afternoon";
//    //The transition from afternoon to night (approx. 5 pm to 9 pm).
//    public final String EVENING = "Evening";
//    //Historically the middle of the night, often associated with supernatural, typically considered 3 am.
//    public final String THE_WITCHING_HOUR = "The Witching Hour";
    
    public boolean isNight()
    {
        final int hoursOfDay = this.getHourOfDay();
        return hoursOfDay < 6 || hoursOfDay > 18;
    }
    
    public int getHourOfDay() {
        //return LocalTime.now().getHour();
        final Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public String getNightOrDay() {
        return isNight() ? NIGHT : DAY;
    }
    
    public int getHourOfDay(long timeInMillis) {
        //final int hoursOfDay = this.getHourOfDay(timeInMillis);
        //return hoursOfDay < 6 || hoursOfDay > 18;        
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public boolean isNight(long timeInMillis) {
        final int hoursOfDay = this.getHourOfDay(timeInMillis);
        return hoursOfDay < 6 || hoursOfDay > 18;
    }
    
    public String getNightOrDay(long timeInMillis) {
        return isNight(timeInMillis) ? NIGHT : DAY;
    }
}
