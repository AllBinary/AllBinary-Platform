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
package org.allbinary.logic.communication.smtp.event;

import java.util.EventObject;

import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.string.StringMaker;

public class EmailEvent extends EventObject
{
   private EmailInfo emailInfo;
   private UserEmailEventNameData userEmailEventNameData;
   private int attempts;
   
   public EmailEvent(
      Object emailEventHandler, 
      UserEmailEventNameData userEmailEventNameData,
      EmailInfo emailInfo, int attempts)
   {
      super(emailEventHandler);
      this.userEmailEventNameData = userEmailEventNameData;
      this.emailInfo = emailInfo;
   }

   public EmailInfo getEmailInfo()
   {
      return this.emailInfo;
   }

    public int getAttempts()
    {
        return attempts;
    }

    public void setAttempts(int attempts)
    {
        this.attempts = attempts;
    }
    
    public String toString()
    {
    	final StringMaker stringBuffer = new StringMaker();
    	
    	stringBuffer.append("EmailEvent Log: \n");
    	stringBuffer.append("Event Name: ");
    	stringBuffer.append(this.userEmailEventNameData.toString());
    	stringBuffer.append(this.getEmailInfo().toString());
    	stringBuffer.append("\nNumber of Attempts: ");
    	stringBuffer.append(this.getAttempts());
    	
       return stringBuffer.toString();
    }
}
