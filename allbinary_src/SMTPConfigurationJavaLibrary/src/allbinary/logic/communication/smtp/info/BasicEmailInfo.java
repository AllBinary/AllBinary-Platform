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
package allbinary.logic.communication.smtp.info;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationInterface;

public abstract class BasicEmailInfo
{
   private String subject;
   private String textBody;
   
   protected EmailServerConfigurationInterface emailServerConfigurationInterface;
   
   public BasicEmailInfo(String subject, String textBody)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "BasicEmailInfo"));
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
   
   protected abstract void init() throws Exception;

}
