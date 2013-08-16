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
package allbinary.logic.communication.smtp.event.handler;

import java.util.Iterator;
import java.util.Vector;

import allbinary.logic.communication.smtp.event.EmailEvent;
import allbinary.logic.communication.smtp.event.UserEmailEventListenerInterface;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;

import allbinary.logic.communication.smtp.info.EmailInfo;
   
public class UserEmailEventHandler
{
   private Vector emailVector;
   private EmailInfo emailInfo;
   private UserEmailEventNameData userEmailEventNameData;
   
   public UserEmailEventHandler()
   {
      this.emailVector = new Vector();
   }
   
   public synchronized void receiveEmailInfo(
      UserEmailEventNameData userEmailEventNameData, EmailInfo emailInfo)
      throws Exception
   {
      this.userEmailEventNameData = userEmailEventNameData;
      this.emailInfo = emailInfo;
      this.fireEmailEvent();
   }
   
   public synchronized void addListener(Vector vector)
   {
      Iterator iter = vector.iterator();
      while(iter.hasNext())
      {
         UserEmailEventListenerInterface userEmailEventListenerInterface =
            (UserEmailEventListenerInterface) iter.next();
         this.addListener(userEmailEventListenerInterface);
      }
   }
   
   public synchronized void addListener(
      UserEmailEventListenerInterface emailEventListenerInterface)
   {
      this.emailVector.add(emailEventListenerInterface);
   }
   
   public synchronized void removeListener(
      UserEmailEventListenerInterface emailEventListenerInterface)
   {
      this.emailVector.remove(emailEventListenerInterface);
   }
   
   public synchronized void fireEmailEvent() throws Exception
   {
      EmailEvent emailEvent = new EmailEvent(this,
         this.userEmailEventNameData, this.emailInfo, 0);
      Iterator iter = this.emailVector.iterator();
      while(iter.hasNext())
      {
         UserEmailEventListenerInterface emailEventListenerInterface =
            (UserEmailEventListenerInterface) iter.next();
         
         emailEventListenerInterface.onEmailSendRequest(emailEvent);
      }
   }
}