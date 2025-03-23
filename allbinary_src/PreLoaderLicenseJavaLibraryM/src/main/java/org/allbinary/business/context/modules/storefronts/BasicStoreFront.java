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
package org.allbinary.business.context.modules.storefronts;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.tokens.Tokenizer;
import java.util.HashMap;

import org.allbinary.business.user.UserData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.util.BasicArrayList;

public class BasicStoreFront implements BasicStoreFrontInterface
{   
   private String name;
   private String userName;   
   private String basketName;   
   private String homeHostName;
   private String homeHostNamePath;   
   private String hostName;
   private String hostNamePath;
   private String testHomeHostName;
   private String testHomeHostNamePath;
   private String testHostName;
   private String testHostNamePath;
   private String imagePath;
   private String staticPath;
   private String categoryPath;
   private String inventoryControl;
   private String subStores;
   private String tagLocation;
   private String packageLocation;
   private String ftp;
   private String ftpPath;
   private String ftpUserName;
   private String ftpPassword;
   private String testFtp;
   private String testFtpPath;
   private String testFtpUserName;
   private String testFtpPassword;
   private String timeCreated;
   private String lastModified;
   
   public BasicStoreFront()
   {
   }
   
   public BasicStoreFront(HashMap storeHashMap)
   {
	   StoreFrontData storeFrontData = StoreFrontData.getInstance();
	   
      this.name = (String) storeHashMap.get(storeFrontData.NAME);
      this.userName = (String) storeHashMap.get(UserData.USERNAME);
      this.basketName = (String) storeHashMap.get(storeFrontData.NAME);
      this.homeHostName = (String) storeHashMap.get(storeFrontData.HOMEHOSTNAME);
      this.homeHostNamePath = (String) storeHashMap.get(storeFrontData.HOMEHOSTNAMEPATH);
      this.hostName = (String) storeHashMap.get(storeFrontData.HOSTNAME);
      this.hostNamePath = (String) storeHashMap.get(storeFrontData.HOSTNAMEPATH);
      this.testHomeHostName = (String) storeHashMap.get(storeFrontData.TESTHOMEHOSTNAME);
      this.testHomeHostNamePath = (String) storeHashMap.get(storeFrontData.TESTHOMEHOSTNAMEPATH);
      this.testHostName = (String) storeHashMap.get(storeFrontData.TESTHOSTNAME);
      this.testHostNamePath = (String) storeHashMap.get(storeFrontData.TESTHOSTNAMEPATH);
      this.imagePath = (String) storeHashMap.get(storeFrontData.IMAGEPATH);
      this.staticPath = (String) storeHashMap.get(storeFrontData.STATICPATH);
      this.categoryPath = (String) storeHashMap.get(storeFrontData.CATEGORYPATH);
      this.inventoryControl = (String) storeHashMap.get(storeFrontData.INVENTORYCONTROL);

      this.subStores = (String) storeHashMap.get(storeFrontData.SUBSTORES);
      this.tagLocation = (String) storeHashMap.get(storeFrontData.TAGLOCATION);
      this.packageLocation = (String) storeHashMap.get(storeFrontData.PACKAGELOCATION);
      this.ftp = (String) storeHashMap.get(storeFrontData.FTP);
      this.ftpPath = (String) storeHashMap.get(storeFrontData.FTPPATH);
      this.ftpUserName = (String) storeHashMap.get(storeFrontData.FTPUSERNAME);
      this.ftpPassword = (String) storeHashMap.get(storeFrontData.FTPPASSWORD);
      this.testFtp = (String) storeHashMap.get(storeFrontData.TESTFTP);
      this.testFtpPath = (String) storeHashMap.get(storeFrontData.TESTFTPPATH);
      this.testFtpUserName = (String) storeHashMap.get(storeFrontData.TESTFTPUSERNAME);
      this.testFtpPassword = (String) storeHashMap.get(storeFrontData.TESTFTPPASSWORD);
      this.timeCreated = (String) storeHashMap.get(EntryData.getInstance().TIMECREATED);
      this.lastModified = (String) storeHashMap.get(EntryData.getInstance().LASTMODIFIED);      
   }
   
   //This path is used by the product listings class to save newly generated static pages
   //these files should be copied to the real server + staticpages path probably with ftp
   public String getTestHtmlPath()
   {      
      //in testing mode return the test location else return the working location
      return org.allbinary.globals.URLGLOBALS.getTestHtmlPath() + this.getCurrentHostNamePath();
   }

   public String getCurrentHostName()
   {
      String location = null;
      //in testing mode return the test location else return the working location
      if(org.allbinary.globals.URLGLOBALS.isTestingMode())
      {
         location = this.getTestHostName();
      }
      else
      {
         location = this.getHostName();
      }
      return location;
   }
   
   public String getCurrentHostNamePath()
   {
      String location = null;
      //in testing mode return the test location else return the working location
      if(org.allbinary.globals.URLGLOBALS.isTestingMode())
      {
         location = this.getTestHostNamePath();
      }
      else
      {
         location = this.getHostNamePath();
      }
      return location;
   }

   public String getCurrentHomeHostName()
   {
      String location = null;
      //in testing mode return the test location else return the working location
      if(org.allbinary.globals.URLGLOBALS.isTestingMode())
      {
         location = this.getTestHomeHostName();
      }
      else
      {
         location = this.getHomeHostName();
      }
      return location;
   }
   
   public String getCurrentHomeHostNamePath()
   {
      String location = null;
      //in testing mode return the test location else return the working location
      if(org.allbinary.globals.URLGLOBALS.isTestingMode())
      {
         location = this.getTestHomeHostNamePath();
      }
      else
      {
         location = this.getHomeHostNamePath();
      }
      return location;
   }

   public String getName()   
   {
      return this.name;
   }
   
   public String getUserName()
   {
      return this.userName;
   }

   public String getBasketName()
   {
      return this.basketName;
   }
      
   public String getHomeHostName()
   {
      return this.homeHostName;
   }

   public String getHomeHostNamePath()
   {
      return this.homeHostNamePath;
   }

   public String getHostName()
   {
      return this.hostName;
   }

   public String getHostNamePath()
   {
      return this.hostNamePath;
   }
   
   public String getTestHomeHostName()
   {
      return this.testHomeHostName;
   }

   public String getTestHomeHostNamePath()
   {
      return this.testHomeHostNamePath;
   }
   
   public String getTestHostName()
   {
      return this.testHostName;
   }

   public String getTestHostNamePath()
   {
      return this.testHostNamePath;
   }
   /*
   public String getImagePath()
   {
      return this.imagePath;
   }
   */
   public String getStaticPath()
   {
      return this.staticPath;
   }
   
   public String getCategoryPath()
   {
      return this.categoryPath;
   }

   public String getInventoryControl()
   {
      return this.inventoryControl;
   }

   public BasicArrayList getSubStores() throws Exception
   {
      try
      {
      Tokenizer tokenizer = new Tokenizer(CommonSeps.getInstance().SEMICOLON);
      BasicArrayList subStoreVector = tokenizer.getTokens(this.subStores, new BasicArrayList());
      return subStoreVector;
      }catch(Exception e)
      {
         throw e;
      }
   }
   
   public String getTagLocation()
   {
      return this.tagLocation;
   }

   public String getPackageLocation()
   {
      return this.packageLocation;
   }
   
   public String getFtp()
   {
      return this.ftp;
   }
   
   public String getFtpUserName()
   {
      return this.ftpUserName;
   }
   
   public String getFtpPassword()
   {
      return this.ftpPassword;
   }

   public String getTestFtp()
   {
      return this.testFtp;
   }
   
   public String getTestFtpUserName()
   {
      return this.testFtpUserName;
   }
   
   public String getTestFtpPassword()
   {
      return this.testFtpPassword;
   }

   public String getFtpPath()
   {
      return this.ftpPath;
   }
   
   public String getTestFtpPath()
   {
      return this.testFtpPath;
   }

   public String getTimeCreated()
   {
      return this.timeCreated;
   }
   
   public String getLastModified()
   {
      return this.lastModified;
   }
}
