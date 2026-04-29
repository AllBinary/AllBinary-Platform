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

//ActualPlatform
public class ExceptionUtil
{

    private static final ExceptionUtil instance = new ExceptionUtil();

    /**
     * @return the instance
     */
    //ActualPlatform
    public static ExceptionUtil getInstance()
    {
        return ExceptionUtil.instance;
    }

    private ExceptionUtil()
    {
    }

    //ActualPlatform
    public static final Exception PRETEND_EXCEPTION = new Exception("Not Really An Exception");
    private final String NONE = "No Stack Trace";
    
    //ActualPlatform
    public String getStackTrace(Throwable e)
    {
        e.printStackTrace();

        return NONE;
    }
}
