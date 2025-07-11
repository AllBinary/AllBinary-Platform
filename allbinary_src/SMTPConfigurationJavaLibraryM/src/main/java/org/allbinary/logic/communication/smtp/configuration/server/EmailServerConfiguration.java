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
package org.allbinary.logic.communication.smtp.configuration.server;

import java.util.HashMap;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class EmailServerConfiguration implements EmailServerConfigurationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String accountName;
   private String password;
   
   private String smtpServer;
   
   public EmailServerConfiguration()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
        final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
      }

      this.setSmtpServer(StringUtil.getInstance().EMPTY_STRING);
   }
   
   public EmailServerConfiguration(HashMap hashMap)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
        final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, "EmailServerConfiguration(HashMap)");
      }

      this.setAccountName((String) hashMap.get(EmailServerConfigurationData.ACCOUNT));
      this.setPassword((String) hashMap.get(EmailServerConfigurationData.PASSWORD));
      this.setSmtpServer((String) hashMap.get(EmailServerConfigurationData.SERVER));
      
      this.log();
   }

   public EmailServerConfiguration(String account, String password, String server)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
        final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, "EmailServerConfiguration(HashMap)");
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
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         logUtil.put(this.toString(), this, "EmailServerConfiguration");
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
