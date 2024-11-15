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

import java.io.IOException;
import java.util.Hashtable;

import org.apache.xmlrpc.XmlRpcException;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

import org.allbinary.canvas.SpecialMessageUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class LogUtil
{
    private static boolean isFirstException = true;
    private static TimeDelayHelper timeDelayHelper = new TimeDelayHelper(200000);

    public static AbeClientInformationInterface abeClientInformation;
    
    private LogUtil()
    {
    }

    private static final StringMaker stringBuffer = new StringMaker();
    

    public synchronized static void put(Log log)
    {
        if(log == null)
            return;
        
        Throwable exception = log.getThrowable();

        if (exception != null)
        {
            //If XmlRpcException then don't try to log it
            if(exception.getClass().getName().compareTo(XmlRpcException.class.getName()) == 0)
                return;
            if(exception.getClass().getName().compareTo(IOException.class.getName()) == 0)
                return;
            
            if (LogUtil.isFirstException || LogUtil.timeDelayHelper.isTime())
            {
                String specialMessage = log.getSpecialMessage();
                Object object = log.getObject();
                String functionName = log.getFunctionName();

                String className = CommonStrings.getInstance().EMPTY;
                LogUtil.isFirstException = false;
                
                if (object != null && object.getClass().getName() != null)
                {
                    className = new String(object.getClass().getName());
                }

                String message = LogFormatUtil.getInstance().get(
                    className, functionName, specialMessage, exception);

                try
                {
                    System.out.println("Eeeek");
                    
                    //android.util.Log.i("allbinary","Eeeek");
                    
                    //System.out.println("message: " + message);
                    if(abeClientInformation == null) throw new RuntimeException();

                    Hashtable hashtable = abeClientInformation.toHashtable();

                    stringBuffer.delete(0, stringBuffer.length());
                    stringBuffer.append(message);
                    stringBuffer.append(CommonSeps.getInstance().SPACE);
                    stringBuffer.append(SpecialMessageUtil.getInstance().get());

                    hashtable.put("message", stringBuffer.toString());

                    //System.out.println("Sending");
                    new XmlRpcRemoteLogClient(abeClientInformation).get(hashtable);
                } catch (Throwable e)
                {
                    //Hmmmm well you will never know
                    System.out.println("Exception");
                    e.printStackTrace();
                }
                
                /*
                if (className != null)
                {
                    put(specialMessage, className, functionName, exception);
                } else if (object != null)
                {
                    put(specialMessage, object, functionName, exception);
                } else
                {
                    put(specialMessage, "This Should Never Happed", functionName, exception);
                }
                */
            }
        }
//        else
//        {
//            String specialMessage = log.getSpecialMessage();
//            Object object = log.getObject();
//            String functionName = log.getFunctionName();
//
//            String className = EMPTY;
//            String message = LogFormatUtil.getInstance().get(
//                    className, functionName, specialMessage);
//            System.out.println(message);
//        }
    }

    /*
     * private final static Calendar calendar = Calendar.getInstance();
    public synchronized static void put(
        String specialMessage,
        Object object,
        String functionName)
    {
    }

    public synchronized static void put(
        String specialMessage,
        Object object,
        String functionName,
        Throwable exception)
    {
        //Date date = calendar.getTime();
        //String time = new String(date.toString());

        String className = "Empty";

        if (object != null && object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }

        String message = LogFormatUtil.get(
            className, functionName, specialMessage, exception);

        try
        {
            //System.out.println(message);
            AbeClientInformationInterface abeClientInformation =
                AbeClientInformationInterfaceFactory.getInstance();
            Hashtable hashtable = abeClientInformation.toHashtable();
            hashtable.put("message", message);
            new XmlRpcRemoteLogClient(abeClientInformation).get(hashtable);
        } catch (Throwable e)
        {
            //Hmmmm well you will never know
        }
    }

    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName)
    {
    }

    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName,
        Throwable exception)
    {
        String message = LogFormatUtil.get(
            className, functionName, specialMessage, exception);

        try
        {
            //System.out.println(message);
            AbeClientInformationInterface abeClientInformation =
                AbeClientInformationInterfaceFactory.getInstance();
            Hashtable hashtable = abeClientInformation.toHashtable();
            hashtable.put("message", message);
            new XmlRpcRemoteLogClient(abeClientInformation).get(hashtable);
        } catch (Throwable e)
        {
            //Hmmmm well you will never know
        }
    }
    */
}
