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
package org.allbinary.logic.communication.log;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.time.TimeStampUtil;

//NoPlatform
public class LogFormatUtil
{

    private static final LogFormatUtil instance = new LogFormatUtil();

    public static LogFormatUtil getInstance()
    {
        return instance;
    }
    
    private LogFormatUtil()
    {
    }

    public String get(final String className, final String functionName, final String specialMessage, final Object exception)
    {
        throw new RuntimeException();
    }

    public String get(final String className, final String functionName, final String specialMessage)
    {
        throw new RuntimeException();
    }

    private StringMaker get(final String className, String functionName)
    {
        throw new RuntimeException();
    }

    public String get(final Object exception)
    {
        throw new RuntimeException();
    }

}
