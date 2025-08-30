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
import java.util.Date;

//NoPlatform
public class TimeStampUtil
{
    private static final TimeStampUtil instance = new TimeStampUtil();

    public static TimeStampUtil getInstance()
    {
        return instance;
    }
    
    private TimeStampUtil()
    {
    }
    
    public String getAsString()
    {
        throw new RuntimeException();
    }

}
