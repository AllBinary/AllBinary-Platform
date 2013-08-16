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
package abcs.logic.communication.log;

import abcs.logic.communication.log.*;
import java.util.Iterator;
import java.util.Vector;

public class LogBuffer
{
   Vector logEntryVector;
   
   public LogBuffer()
   {
      this.logEntryVector = new Vector();
   }

   public void add(Log log)
   {
      this.logEntryVector.add(log);
   }

   /*
   public void add(
         String specialMessage,
         Object object,
         String functionName,
         Exception exception)
   {
      this.logEntryVector.add(
         LogFactory.getInstance(specialMessage, object, functionName, exception));
   }

   public void add(
         String specialMessage,
         String className,
         String functionName,
         Exception exception)
   {
      this.logEntryVector.add(
         LogFactory.getInstance(specialMessage, className, functionName, exception));
   }
    */

   public void logAll()
   {
      Iterator iter = this.logEntryVector.iterator();
      while(iter.hasNext())
      {
          Log log = (Log) iter.next();
          LogUtil.put(log);
      }
   }
}
