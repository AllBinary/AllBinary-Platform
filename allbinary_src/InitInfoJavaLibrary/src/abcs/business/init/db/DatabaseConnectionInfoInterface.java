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
package abcs.business.init.db;

public interface DatabaseConnectionInfoInterface
{
   public String getUrl();

   public String getJdbcDriver();
   
   public String getName();
   
   public String getUserName();
   
   public String getPassword();
   
   public String getHost();

   public String getSchema();
   
   public String getServer();

   public String getPort();

   public void setJdbcDriver(String value);
   
   public void setName(String value);
   
   public void setUserName(String value);
   
   public void setPassword(String value);

   public void setSchema(String value);
   
   public void setServer(String value);

   public void setPort(String value);
   
   public String getUserNameKey();
   
   public String getPasswordKey();  
}
