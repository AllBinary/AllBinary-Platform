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
    
    public final Exception PRETEND_EXCEPTION = new Exception("Not Really An Exception");

    private final String NONE = "No Stack Trace";

    public String getStackTrace(Throwable e)
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        if(e != nullUtil.NULL_OBJECT) {
            ((Throwable) e).printStackTrace();
        }

        if (bs.toString() != null)
        {
            return new String(bs.toString());
        }
        return NONE;
    }
}
