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
package allbinary.logic.communication.smtp.configuration.server;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import java.util.HashMap;

public class EmailServerConfiguration implements EmailServerConfigurationInterface
{
   private String accountName;
   private String password;
   
   private String smtpServer;
   
   public EmailServerConfiguration()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing Empty", this, "EmailServerConfiguration"));
      }

      this.setSmtpServer(StringUtil.getInstance().EMPTY_STRING);
   }
   
   public EmailServerConfiguration(HashMap hashMap)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "EmailServerConfiguration(HashMap)"));
      }

      this.setAccountName((String) hashMap.get(EmailServerConfigurationData.ACCOUNT));
      this.setPassword((String) hashMap.get(EmailServerConfigurationData.PASSWORD));
      this.setSmtpServer((String) hashMap.get(EmailServerConfigurationData.SERVER));
      
      this.log();
   }

   public EmailServerConfiguration(String account, String password, String server)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "EmailServerConfiguration(HashMap)"));
      }

      this.setAccountName(account);
      this.setPassword(password);
      this.setSmtpServer(server);
      
      this.log();
   }
   
   public void setSmtpServer(String value)
   {
      this.smtpServer = value;
   }

   public String getSmtpServer()
   {
      return this.smtpServer;
   }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void log()
    {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance(this.toString(), this, "EmailServerConfiguration"));
      }
    }
    
    public String toString()
    {
       return "Email Server Configuration:\n" + 
          "Smtp Server: \n" + this.getSmtpServer() + 
          "\nAccount Name: " + this.getAccountName() + 
          "\nPassword: " + this.getPassword();
    }
}
