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

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

//ActualPlatform
public class TimeStampUtil {

    private static final TimeStampUtil instance = new TimeStampUtil();

    //ActualPlatform
    public static TimeStampUtil getInstance() {
        return TimeStampUtil.instance;
    }

    private TimeStampUtil() {
    }

    //ActualPlatform    
    public String getAsString() {
        // GWT's replacement for SimpleDateFormat
        final DateTimeFormat simpleDataFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // In GWT, creating a new Date() directly yields the current timestamp, 
        // replacing the need for Calendar.getInstance().getTime()
        final Date date = new Date();

        return simpleDataFormat.format(date);
    }

//        const currentDate = new Date();
//        const year = currentDate.getFullYear();
//        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
//        const day = String(currentDate.getDate()).padStart(2, '0');
//        const hours = String(currentDate.getHours()).padStart(2, '0');
//        const minutes = String(currentDate.getMinutes()).padStart(2, '0');
//        const seconds = String(currentDate.getSeconds()).padStart(2, '0');
//        const milliseconds = String(currentDate.getMilliseconds()).padStart(3, '0');
//
//        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.${milliseconds}`;

}
