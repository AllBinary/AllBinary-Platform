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
package org.allbinary.business.init.db;

public interface DatabaseConnectionInfoInterface
{
   String getUrl();

   String getJdbcDriver();
   
   String getName();
   
   String getUserName();
   
   String getPassword();
   
   String getHost();

   String getSchema();
   
   String getServer();

   String getPort();

   void setJdbcDriver(String value);
   
   void setName(String value);
   
   void setUserName(String value);
   
   void setPassword(String value);

   void setSchema(String value);
   
   void setServer(String value);

   void setPort(String value);
   
   String getUserNameKey();
   
   String getPasswordKey();  
}
