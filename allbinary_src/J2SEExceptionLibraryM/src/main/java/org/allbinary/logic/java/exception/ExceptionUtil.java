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
package org.allbinary.logic.java.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.allbinary.logic.NullUtil;

public class ExceptionUtil
{

    private static final ExceptionUtil instance = new ExceptionUtil();

    /**
     * @return the instance
     */
    public static ExceptionUtil getInstance()
    {
        return instance;
    }

    private ExceptionUtil()
    {
    }

    private final NullUtil nullUtil = NullUtil.getInstance();
    
    private final String NONE = "No Stack Trace";

    public String getStackTrace(final Object e)
    {
        if(e != null) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final PrintStream printStream = new PrintStream(byteArrayOutputStream);

            if (e != nullUtil.NULL_OBJECT) {
                ((Throwable) e).printStackTrace(printStream);
            }

            if (byteArrayOutputStream.toString() != null) {
                return new String(byteArrayOutputStream.toString());
            }
        }
        return NONE;
    }
}
