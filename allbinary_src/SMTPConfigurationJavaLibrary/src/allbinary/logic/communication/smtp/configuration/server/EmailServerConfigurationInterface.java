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

public interface EmailServerConfigurationInterface
{
   public void setSmtpServer(String value);
   public String getSmtpServer();

   public String getAccountName();
   public void setAccountName(String accountName);

   public String getPassword();
   public void setPassword(String password);

   public void log();

   public String toString();
}
