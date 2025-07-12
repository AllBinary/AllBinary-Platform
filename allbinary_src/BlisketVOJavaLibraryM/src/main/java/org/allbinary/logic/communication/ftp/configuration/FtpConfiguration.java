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
package org.allbinary.logic.communication.ftp.configuration;

import java.util.HashMap;

public class FtpConfiguration implements FtpConfigurationInterface
{
   private String ftpServer;
   private String ftpPath;
   private String ftpUserName;
   private String ftpPassword;

   public FtpConfiguration()
   {
   }
   
   public FtpConfiguration(HashMap hashMap)
   {
      this.ftpServer = (String) hashMap.get(FtpConfigurationData.SERVER);
      this.ftpPath = (String) hashMap.get(FtpConfigurationData.PATH);
      this.ftpUserName = (String) hashMap.get(FtpConfigurationData.USERNAME);
      this.ftpPassword = (String) hashMap.get(FtpConfigurationData.PASSWORD);
   }
   
   public String getServer()
   {
      return this.ftpServer;
   }
   
   public String getPath()
   {
      return this.ftpPath;
   }

   public String getUserName()
   {
      return this.ftpUserName;
   }

   public String getPassword()
   {
      return this.ftpPassword;
   }

   public void setServer(String value)
   {
      this.ftpServer = value;
   }
   
   public void setPath(String value)
   {
      this.ftpPath = value;
   }

   public void setUserName(String value)
   {
      this.ftpUserName = value;
   }

   public void setPassword(String value)
   {
      this.ftpPassword = value;
   }
}
