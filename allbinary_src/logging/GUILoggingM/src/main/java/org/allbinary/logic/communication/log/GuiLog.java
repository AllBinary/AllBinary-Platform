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

import org.allbinary.string.CommonStrings;
import java.awt.GridLayout;
import javax.swing.JDialog;

public class GuiLog
{
    private static final GuiLog instance = new GuiLog();

    /**
     * @return the instance
     */
    public static GuiLog getInstance()
    {
        return instance;
    }

   private GuiLog()
   {
   }
   
   public synchronized static void showDialog(String msg)
   {
      JDialog error = new JDialog();
      int size = msg.length() * 9;      
      int x = size;
      if(x>550)
         x=550;
      error.getContentPane().setLayout(new GridLayout(1,1));
      if(size>255)
      {
         int y = 550;
         error.setSize(x,y);
         error.getContentPane().add(new javax.swing.JScrollPane(new javax.swing.JTextArea(msg)));
      }
      else
      if(size>0)
      {
         int y = 50;
         error.setSize(x,y);
         error.getContentPane().add(new javax.swing.JScrollPane(new javax.swing.JLabel(msg)));
      }      
      error.show();      
   }
         
   public synchronized static String put(
   String specialMessage,
   Object object,
   String functionName)
   {
      return GuiLog.put(specialMessage,object,functionName,null);
   }
   
   public synchronized static String put(
   String specialMessage,
   Object object,
   String functionName,
   Exception exception)
   {
      try
      {
         String data = LogFormatUtil.getInstance().get(specialMessage, 
               object.getClass().getName(), functionName, exception);
         GuiLog.showDialog(data);
         System.out.println(data);
         return data;
      }
      catch (Exception e)
      {
          PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, instance, "put", e);
         return "Logging Error";
      }
   }

   public synchronized static String put(
   String specialMessage,
   String className,
   String functionName)
   {
      return GuiLog.put(specialMessage, className, functionName, null);
   }
   
   public synchronized static String put(
   String specialMessage,   
   String className,
   String functionName,
   Exception exception)
   {      
      try
      {
         String data = LogFormatUtil.getInstance().get(specialMessage, 
               className, functionName, exception);
         GuiLog.showDialog(data);
         System.out.println(data);
         return data;
      }
      catch (Exception e)
      {
          PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, instance, "put", e);
         return "Logging Error";
      }
   }
}