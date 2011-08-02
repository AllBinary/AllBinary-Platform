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
package allbinary.logic.communication.ftp.configuration;

public interface FtpConfigurationInterface
{
   public String getServer();
   
   public String getPath();

   public String getUserName();

   public String getPassword();

   public void setServer(String value);
   
   public void setPath(String value);

   public void setUserName(String value);

   public void setPassword(String value);
}
