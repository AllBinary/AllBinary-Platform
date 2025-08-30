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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//ActualPlatform
public class TimeStampUtil
{
    private static final TimeStampUtil instance = new TimeStampUtil();

    //ActualPlatform
    public static TimeStampUtil getInstance()
    {
        return instance;
    }
    
    private final SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private TimeStampUtil()
    {
    }
    
    //ActualPlatform
    public String getAsString()
    {
        final Calendar calendar = Calendar.getInstance();
        final Date date = calendar.getTime();
        return simpleDataFormat.format(date);
    }

}
