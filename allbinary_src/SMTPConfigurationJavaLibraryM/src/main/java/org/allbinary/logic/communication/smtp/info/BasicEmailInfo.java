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
package org.allbinary.logic.communication.smtp.info;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationInterface;
import org.allbinary.string.CommonStrings;

public class BasicEmailInfo
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String subject;
   private String textBody;
   
   protected EmailServerConfigurationInterface emailServerConfigurationInterface;
   
   public BasicEmailInfo(String subject, String textBody)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
      }
      
      this.subject = subject;
      this.textBody = textBody;
   }
   
   public String getSubject()
   {
      return this.subject;
   }
   
   public String getTextBody()
   {
      return this.textBody;
   }

   public EmailServerConfigurationInterface getEmailServerConfigurationInterface()
   {
      return emailServerConfigurationInterface;
   }
   
   public void setEmailServerConfigurationInterface(EmailServerConfigurationInterface emailServerConfigurationInterface)
   {
      this.emailServerConfigurationInterface = emailServerConfigurationInterface;
   }
   
   public String toString()
   {
	   StringBuffer stringBuffer = new StringBuffer();
	   
	   stringBuffer.append(this.getEmailServerConfigurationInterface().toString());
	   stringBuffer.append("\n");
	   stringBuffer.append("Subject: \n");
	   stringBuffer.append(this.getSubject());
	   stringBuffer.append("\nText Body: \n");
	   stringBuffer.append(this.getTextBody());
       
      return stringBuffer.toString();
   }
   
   protected void init() throws Exception {
       throw new RuntimeException();
   }

}
