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
package org.allbinary.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFormatUtil;

public class FileLog
{
    private static final StringUtil stringUtil = StringUtil.getInstance();

   private static final long logLength = 33000000;  // 33MB
   //private static final String logPath = MyFrame.PATH + "/log/";
   private static final String logPath = "g:\\log\\";
   private static final String extension = new String("dat");
   private static final String fileName = new String("log." + FileLog.extension);
   private static final String backupFileName = FileLog.fileName.concat(".bak");
   private static final String ORG_ALLBINARY = "org.allbinary: ";
   private static boolean firstTime = true;
   private static File logFile = new File(FileLog.logPath, FileLog.fileName);
   private static File logFileBak;
   private static BufferedWriter fileOut;
   private static int backupIndex;
   
   private FileLog()
   {
   }
   
   public static synchronized boolean createLogFile()
   {
      try
      {
         FileLog.logFile = new File(logPath, fileName);
         if(!FileLog.firstTime)
         {
            FileLog.fileOut = new BufferedWriter(new FileWriter(logFile));
         }
         else
         {
            FileLog.firstTime = false;
            RandomAccessFile raFile = new RandomAccessFile(logFile, "rw");
            raFile.seek(raFile.length());
            FileLog.fileOut = new BufferedWriter(new FileWriter(raFile.getFD()));
         }
         boolean canWrite = logFile.canWrite();         
         return canWrite;
      }
      catch (Exception e)
      {
         System.out.println("Error Creating Log: " + e);
         return false;
      }
   }
   
   private static synchronized boolean createLogFileBackup()
   {
      try
      {
         FileLog.logFileBak = new File(logPath, new StringBuilder().append(backupFileName).append(CommonSeps.getInstance().PERIOD).append(backupIndex).toString());
         while(FileLog.logFileBak.isFile()) {
             FileLog.backupIndex++;
             FileLog.logFileBak = new File(logPath, new StringBuilder().append(backupFileName).append(CommonSeps.getInstance().PERIOD).append(backupIndex).toString());
         }
         
         //Calendar calendar=Calendar.getInstance();
         //Date date = calendar.getTime();
         //String time= new String(date.toString());
         
         String line = new String();
          //+ time
         BufferedWriter tmpOut = new BufferedWriter(new FileWriter(logFileBak));
         BufferedReader tmpIn = new BufferedReader(new FileReader(logFile));
         
         while ((line = tmpIn.readLine()) != null)
         {
            tmpOut.write(line, 0, line.length());
            tmpOut.newLine();
         }
         tmpOut.flush();
         tmpOut.close();
         tmpIn.close();
         
         FileLog.logFile.delete();

         return true;
      }
      catch (Exception e)
      {
         System.out.println("Error Creating Backup: " + e);
         return false;
      }
   }
   
   public synchronized static String put(
   String specialMessage,
   Object object,
   String functionName)
   {
      return FileLog.put(specialMessage,object,functionName,null);
   }
   
   public synchronized static String put(
   String specialMessage,
   Object object,
   String functionName,
   Throwable exception)
   {
      
      try
      {
         if(FileLog.firstTime==true)
         {
            FileLog.createLogFileBackup();
            if (createLogFile()==false)
            {
               return new String("Log File Creation Error");
            }
         }
         
         long length = logFile.length();
         if (length > FileLog.logLength)
         {
            if (!(createLogFileBackup()) || createLogFile()==false)
            {
               return new String("Couldn't Create a Backup file");
            }
         }
         
         String className = new String(stringUtil.NULL_STRING);

         if(functionName==null) functionName = new String(FileLog.stringUtil.NULL_STRING);
         if(specialMessage==null) specialMessage = new String(FileLog.stringUtil.NULL_STRING);

         if(object.getClass().getName() != null) className = new String(object.getClass().getName());

         final String message = LogFormatUtil.getInstance().get(
                 className, functionName, specialMessage, exception);

         FileLog.fileOut.write(message, 0, message.length());
         FileLog.fileOut.newLine();
         FileLog.fileOut.flush();
         return new String(ORG_ALLBINARY + message);
      }
      catch (Exception e)
      {
         return new String("Error: " + e);
      }
   }

   public synchronized static String put(
   final String specialMessage,
   final String className,
   final String functionName)
   {
      return FileLog.put(specialMessage, className, functionName, null);
   }
   
   public synchronized static String put(
   String specialMessage, 
   final String className,
   String functionName,
   final Exception exception)
   {      
      try
      {
         if(FileLog.firstTime==true)
         {
            FileLog.createLogFileBackup();
            if (createLogFile()==false)
            {
               return new String("Log File Creation Error");
            }
         }
         
         long length = logFile.length();
         if (length > FileLog.logLength)
         {
            if (!(createLogFileBackup()) || createLogFile()==false)
            {
               return new String("Couldn't Create a Backup file");
            }
         }
         
         if(functionName==null) functionName = new String(FileLog.stringUtil.NULL_STRING);
         if(specialMessage==null) specialMessage = new String(FileLog.stringUtil.NULL_STRING);                  
         
         final String message = LogFormatUtil.getInstance().get(
                 className, functionName, specialMessage, exception);
                           
         FileLog.fileOut.write(message, 0, message.length());
         FileLog.fileOut.newLine();
         FileLog.fileOut.flush();
         
         return new String(ORG_ALLBINARY + message);
      }
      catch (Exception e)
      {
         return new String("Error: " + e);
      }
   }
   
   public static String getFilePath()
   {
      return FileLog.logPath + FileLog.fileName;
   }
}


