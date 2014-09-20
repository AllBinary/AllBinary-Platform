/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.time;

import java.util.Calendar;

public class TimeUtil
{

    private static final TimeUtil instance = new TimeUtil();

    public static TimeUtil getInstance()
    {
        return instance;
    }

    public void setCalendar(
        Calendar calendar,
        String yearString,
        String monthString,
        String dayString,
        String hourString)
    {
        int year = new Integer(yearString).intValue();
        int month = new Integer(monthString).intValue();
        int day = new Integer(dayString).intValue();
        int hour = new Integer(hourString).intValue();

        calendar.set(year, month, day, hour, 0);
    }

    public void setCalendar(
        Calendar calendar,
        String yearString,
        String monthString,
        String dayString,
        String hourString,
        String minuteString,
        String secondString)
    {
        int year = new Integer(yearString).intValue();
        int month = new Integer(monthString).intValue();
        int day = new Integer(dayString).intValue();
        int hour = new Integer(hourString).intValue();
        int minute = new Integer(minuteString).intValue();
        int second = new Integer(secondString).intValue();

        calendar.set(year, month, day, hour, minute, second);
    }

    public void setExpirationCalendar(
        Calendar calendar,
        String yearsString,
        String monthsString,
        String daysString,
        String hoursString,
        String minutesString,
        String secondsString)
    {
        int year = calendar.get(Calendar.YEAR) + new Integer(yearsString).intValue();
        int month = calendar.get(Calendar.MONTH) + new Integer(monthsString).intValue();
        int day = calendar.get(Calendar.DAY_OF_MONTH) + new Integer(daysString).intValue();
        int hour = calendar.get(Calendar.HOUR) + new Integer(hoursString).intValue();
        int minute = calendar.get(Calendar.MINUTE) + new Integer(minutesString).intValue();
        int second = calendar.get(Calendar.SECOND) + new Integer(secondsString).intValue();

        calendar.set(year, month, day, hour, minute, second);
    }

    private final static Calendar calendar = Calendar.getInstance();    
    public long getTotalTime(
        String yearString,
        String monthString,
        String dayString,
        String hourString,
        String minuteString,
        String secondString)
    {
        long time = calendar.getTimeInMillis();

        this.setExpirationCalendar(calendar,
            yearString, monthString, dayString,
            hourString, minuteString, secondString);

        long timeDelta = calendar.getTimeInMillis();

        return timeDelta - time;
    }
}
