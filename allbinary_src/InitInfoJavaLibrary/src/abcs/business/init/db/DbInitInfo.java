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
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
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
package abcs.business.init.db;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.AbDataInputStream;
import abcs.logic.basic.io.AbDataOutputStream;
import abcs.logic.basic.io.AbFileInputStream;
import abcs.logic.basic.io.DataOutputStreamFactory;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.PreLogUtil;
import abcs.logic.system.security.crypt.DatabaseEncoder;
import abcs.logic.system.security.crypt.WeakCrypt;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

public class DbInitInfo extends DbConnectionInfo
{   
   private String initFileName;
   
   //private final String PACKAGE = "/allbinary/ecommerce/init/db/";
   private final String PACKAGE = FREEBLISKET_PATH_GLOBALS.getInstance().DBINITPATH;
   
   private boolean hasRead = false;

   //read argument is off for installation only
   public DbInitInfo(String initFileName, boolean read)
   {
      super();
      this.initFileName = initFileName;
      if(read) this.updateIfNeeded();
      else this.setHasRead(true);
   }

   public String getUrl()
   {
      String url = super.getUrl();
          //this.getHost() + this.getName() +
         //this.getUserNameKey() + this.getUserName() +
         //this.getPasswordKey() + this.getPassword();

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
      {
         PreLogUtil.put("Url: " + url, this, "getUrl");
      }
      return url;
   }
   
   public synchronized void write() throws Exception
   {
      AbPath FILEABPATH = new AbPath(URLGLOBALS.getWebappPath() + PACKAGE, this.initFileName);
      try
      {
         AbFile newFile = new AbFile(FILEABPATH);

         newFile.createNewFile();

         //PreLogUtil.put("Host: " + this.getHost(), this, "write");

         AbDataOutputStream dataOutputStream =
             DataOutputStreamFactory.getInstance().getInstance(newFile);

         try
         {

         byte[] cryptedJdbcDriver = new WeakCrypt(1).encrypt(this.getJdbcDriver()).getBytes();
         
         byte[] cryptedName = new WeakCrypt(2).encrypt(this.getName()).getBytes();
         byte[] cryptedUserName = new WeakCrypt(3).encrypt(this.getUserName()).getBytes();
         byte[] cryptedPassword = new WeakCrypt(4).encrypt(this.getPassword()).getBytes();
         byte[] cryptedSchema = new WeakCrypt(5).encrypt(this.getSchema()).getBytes();
         byte[] cryptedServer = new WeakCrypt(6).encrypt(this.getServer()).getBytes();
         byte[] cryptedPort = new WeakCrypt(7).encrypt(this.getPort()).getBytes();
         
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedJdbcDriver));
         
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedName));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedUserName));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedPassword));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedSchema));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedServer));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedPort));
         hasRead = false;

         }
         finally
         {
        	 StreamUtil.getInstance().close(dataOutputStream);
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
             abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {
            PreLogUtil.put("Failed Write: " + FILEABPATH.toString(), this, "write", e);
         }
      }
   }
   
   private synchronized void load() throws Exception
   {
      AbPath FILEABPATH =
         new AbPath(URLGLOBALS.getWebappPath() + PACKAGE, this.initFileName);
      try
      {
         AbFile file =  new AbFile(FILEABPATH);
         if(file.isFile())
         {
            AbFileInputStream iFile = new AbFileInputStream(file);
            AbDataInputStream iData = new AbDataInputStream(iFile);

            try
            {
            String decryptedJdbcDriver = new String(DatabaseEncoder.decode(iData.readUTF()));
            
            String decryptedName = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedUserName = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedPassword = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedSchema = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedServer = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedPort = new String(DatabaseEncoder.decode(iData.readUTF()));
            
            this.setJdbcDriver(new WeakCrypt(1).decrypt(decryptedJdbcDriver));
            this.setName(new WeakCrypt(2).decrypt(decryptedName));
            this.setUserName(new WeakCrypt(3).decrypt(decryptedUserName));
            this.setPassword(new WeakCrypt(4).decrypt(decryptedPassword));
            this.setSchema(new WeakCrypt(5).decrypt(decryptedSchema));
            this.setServer(new WeakCrypt(6).decrypt(decryptedServer));
            this.setPort(new WeakCrypt(7).decrypt(decryptedPort));

            //this.setJdbcDriver("org.jiql.jdbc.Driver");
            //this.setName(this.getClass().getName());
            //this.setUserName("admin");
            //this.setPassword("jiql");
            //this.setSchema("jdbc:jiql");
            //this.setServer("local");
            //this.setPort("");
            
            }
            finally
            {
            	StreamUtil.getInstance().close(iData);
            }
            
            //PreLogUtil.put("Host: " + this.getHost(), this, "load");
         }
         else
         {
           hasRead = false;
           if(  abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
           {
              PreLogUtil.put("Not a File - Failed Loading: " + FILEABPATH.toString(), this, "load");
           }
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {
            PreLogUtil.put("Failed Loading: " + FILEABPATH.toString(), this, "load", e);
         }
      }
   }
   
   public synchronized void setHasRead(boolean value)
   {
      this.hasRead = value;
   }
   
   private synchronized void updateIfNeeded()
   {
      try
      {
         if(!hasRead)
         {
            hasRead = true;
            this.load();
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {
         PreLogUtil.put("Failed", this, "updateIfNeeded", e);
         }
      }      
   }
   
   public String getName()
   {
      this.updateIfNeeded();
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
      {      
         PreLogUtil.put("Name: " + super.getName(), this, "getName");
      }
      
      return super.getName();
   }
   
   public String getUserName()
   {
      this.updateIfNeeded();
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
      {      
         PreLogUtil.put("Name: " + super.getUserName(), this, "getUserName");
      }
      
      return super.getUserName();
   }
   
   public String getPassword()
   {
      this.updateIfNeeded();
      return super.getPassword();
   }

   public String getSchema()
   {
      this.updateIfNeeded();      
      return super.getSchema();
   }
   
   public String getServer()
   {
      this.updateIfNeeded();
      return super.getServer();
   }

   public String getPort()
   {
      this.updateIfNeeded();      
      return super.getPort();
   }
}
