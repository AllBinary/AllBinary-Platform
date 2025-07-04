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

/*
User Database
Set Name: securedc_user
Set UserName: securedc_cutome
Set Password: user835
 
History Database
Set Name: securedc_history
Set UserName: securedc_history
Set Password: history835
 
Log Database
Set Name: securedc_securelog
Set UserName: securedc_securel
Set Password: securesite835
 
StaticPages Database
securedc_static
securedc_static
securesite835
 
Inventory Database
securedc_inventory
securedc_invento
inventory835
 */
package org.allbinary.business.init.db;

import org.allbinary.logic.communication.log.PreLogUtil;
//import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
//import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class DbInitInfo extends DbConnectionInfo
{
   public DbInitInfo(String initFileName, boolean read)
   {
      super();
   }

   public String getUrl()
   {
      String url = super.getUrl();
          //this.getHost() + this.getName() +
         //this.getUserNameKey() + this.getUserName() +
         //this.getPasswordKey() + this.getPassword();

      //if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
      //{
         PreLogUtil.put("Url: " + url, this, "getUrl");
      //}
      return url;
   }
   
   public synchronized void write() throws Exception
   {
   }
   
   private synchronized void load() throws Exception
   {            
            //this.setJdbcDriver(new WeakCrypt(1).decrypt(decryptedJdbcDriver));
            //this.setName(new WeakCrypt(2).decrypt(decryptedName));
            //this.setUserName(new WeakCrypt(3).decrypt(decryptedUserName));
            //this.setPassword(new WeakCrypt(4).decrypt(decryptedPassword));
            //this.setSchema(new WeakCrypt(5).decrypt(decryptedSchema));
            //this.setServer(new WeakCrypt(6).decrypt(decryptedServer));
            //this.setPort(new WeakCrypt(7).decrypt(decryptedPort));

            //this.setJdbcDriver("org.jiql.jdbc.Driver");
            //this.setName(this.getClass().getName());
            //this.setUserName("admin");
            //this.setPassword("jiql");
            //this.setSchema("jdbc:jiql");
            //this.setServer("local");
            //this.setPort(stringUtil.EMPTY_STRING);
            
            //PreLogUtil.put("Host: " + this.getHost(), this, commonStrings.LOAD);
   }
   
   public synchronized void setHasRead(boolean value)
   {
   }
}
