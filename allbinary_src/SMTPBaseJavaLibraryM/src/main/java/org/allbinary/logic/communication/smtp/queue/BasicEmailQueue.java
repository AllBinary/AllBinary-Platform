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
package org.allbinary.logic.communication.smtp.queue;

import java.util.Iterator;
import javax.mail.Transport;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.EmailInterface;
import org.allbinary.logic.util.queue.BasicQueue;

public class BasicEmailQueue extends BasicQueue
   implements EmailQueueInterface
{
	private static final BasicEmailQueue instance = new BasicEmailQueue();
	
   private static BasicEmailQueue basicEmailQueue;

   private BasicEmailQueue()
   {
   }

   public static synchronized BasicEmailQueue getInstance()
   {
      if(BasicEmailQueue.basicEmailQueue == null)
      {
         BasicEmailQueue.basicEmailQueue = new BasicEmailQueue();
      }
      return BasicEmailQueue.basicEmailQueue;
   }

   public synchronized boolean offer(EmailInterface emailInterface)
      throws Exception
   {
      this.offer((Object) emailInterface);
      this.post(emailInterface);
      return true;
   }

   private synchronized void remove(EmailInterface emailInterface) 
   {
      this.remove((Object) emailInterface);
   }

   public synchronized EmailInterface remove()
   {
      return null;
   }

   private synchronized void post(EmailInterface emailInterface) 
      throws Exception
   {
      if(this.send(emailInterface))
      {
         this.remove(emailInterface);
         processAllUnsent();
      }
   }

   protected synchronized void processAllUnsent() throws Exception
   {
      Iterator iter = this.queueVector.iterator();
      while(iter.hasNext())
      {
         EmailInterface emailInterface = (EmailInterface) iter.next();
         
         if(this.send(emailInterface))
         {
             //if(emailInterface.getEvent())
             this.remove(emailInterface);
             processAllUnsent();
         }
      }
   }

   private synchronized boolean send(EmailInterface emailInterface)
   {
      try
      {
         //Should start thread here
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Sending: " + emailInterface.log(), this, "send"));
         }
         
         Transport.send(emailInterface.getMimeMessage());

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Email Send Debug: " + emailInterface.getDebugInfo(), this, "send"));
         }
         return true;
      }
      catch(Exception e)
      {
         //userEmailInfoEvent
         //Should fire email send event error
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Failed Email Send Debug: " + emailInterface.getDebugInfo(), this, "send", e));
         }
         return false;
      }
   }
}