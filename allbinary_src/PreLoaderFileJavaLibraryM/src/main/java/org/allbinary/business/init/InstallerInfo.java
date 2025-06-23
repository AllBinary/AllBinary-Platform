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
package org.allbinary.business.init;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.AbDataInputStream;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileLocalInputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;

import org.allbinary.logic.io.file.AbFile;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.system.security.crypt.DatabaseEncoder;
import org.allbinary.logic.system.security.crypt.WeakCrypt;
import org.allbinary.globals.PATH_GLOBALS;

public class InstallerInfo
{
	private static final InstallerInfo instance = new InstallerInfo();
	
   private static final String INITFILENAME = "installerdata.dat";    

   //private static final String PACKAGE = "/allbinary//init/";
   private static final String PACKAGE = PATH_GLOBALS.getInstance().INIT_PATH;
   
   private static String userName=null;
   private static String password=null;
      
   private static boolean hasRead = false;
   
   public InstallerInfo()
   {
   }
   
   public synchronized void write() throws Exception
   {
      AbPath FILEABPATH = 
         new AbPath(URLGLOBALS.getMainPath() + PACKAGE, INITFILENAME);
      try
      {
         AbFile newFile = new AbFile(FILEABPATH);
         newFile.createNewFile();

         AbDataOutputStream dataOutputStream =
             DataOutputStreamFactory.getInstance().getInstance(newFile);
         
         byte[] cryptedUserName = new WeakCrypt(1).encrypt(this.getUserName()).getBytes();
         byte[] cryptedPassword = new WeakCrypt(2).encrypt(this.getPassword()).getBytes();
         
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedUserName));
         dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedPassword));
         hasRead = false;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            LogUtil.put(LogFactory.getInstance("Failed", this, "write"));
         }
         throw e;
      }      
   }
      
   private synchronized void read() throws Exception
   {
      AbPath FILEABPATH = 
         new AbPath(URLGLOBALS.getMainPath() + PACKAGE, INITFILENAME);
      try
      {
         AbFile file = new AbFile(FILEABPATH);
         if(file.isFile())
         {
            AbFileLocalInputStream iFile = new AbFileLocalInputStream(file);
            AbDataInputStream iData = new AbDataInputStream(iFile);

            String decryptedUserName = new String(DatabaseEncoder.decode(iData.readUTF()));
            String decryptedPassword = new String(DatabaseEncoder.decode(iData.readUTF()));
            
            this.setUserName(new WeakCrypt(1).decrypt(decryptedUserName));
            this.setPassword(new WeakCrypt(2).decrypt(decryptedPassword));
         }
         else
         {
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADER))
            {
               LogUtil.put(LogFactory.getInstance("Not a File - Failed Loading: " + FILEABPATH.toString(), this, "read"));
            }
         }         
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            LogUtil.put(LogFactory.getInstance("Failed", this, "read"));
         }
      }
   }

   public synchronized static void setHasRead(boolean value)
   {
      InstallerInfo.hasRead = value;
   }
   
   public void setUserName(String userName)
   {
      InstallerInfo.userName = userName;
   }

   public void setPassword(String password)
   {
      InstallerInfo.password = password;
   }

   private synchronized void updateIfNeeded() throws Exception
   {
      if(!hasRead)
      {
         hasRead = true;
         this.read();
         if(InstallerInfo.userName == null || InstallerInfo.password == null)
         {
            if( org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADER))
            {
               LogUtil.put(LogFactory.getInstance("Failed", this, "updateIfNeeded"));
            }
            //throw new InitException();
         }
      }            
   }
   
   public String getUserName() throws Exception
   {      
      this.updateIfNeeded();
      return InstallerInfo.userName;
   }   

   public String getPassword() throws Exception
   {      
      this.updateIfNeeded();
      return InstallerInfo.password;
   }
   
   public boolean isValid(String userName, String password) throws Exception
   {
      this.updateIfNeeded();
      if(this.userName != null && this.userName.compareTo(userName)==0 &&
         this.password != null && this.password.compareTo(password)==0)
      {
         return true;
      }
      return false;
   }
}
