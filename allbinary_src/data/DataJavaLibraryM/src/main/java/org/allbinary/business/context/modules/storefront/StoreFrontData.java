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
package org.allbinary.business.context.modules.storefront;

public class StoreFrontData
{
	private static final StoreFrontData instance = new StoreFrontData();

	public static StoreFrontData getInstance() {
		return instance;
	}
	
   //StoreFront Commands
   public final String CANCEL = "Cancel Store Front";
   public final String SELECT = "Select Store Front";
   
   public final String INSTALL = "Install Store Front";
   public final String INSTALL_COMPLETE = "Install Store Front Complete";
   
   //public final String USENAMEASPATH = "Use Name As Path";
   //public final String USEMAINEMAILFORNOTIFICATION = "Use Main Email For Notify";
   
   //public final String STORE = "STOREFRONT_STORE";
   //public final String USERNAME = "USERNAME";
   
   public final String NAME = "STOREFRONT_NAME";      
   
   public final String CURRENTHOSTNAME = "STOREFRONT_CURRENTHOSTNAME";
   public final String CURRENTHOSTNAMEPATH = "STOREFRONT_CURRENTHOSTNAMEPATH";

   public final String CURRENTHOMEHOSTNAME = "STOREFRONT_CURRENTHOMEHOSTNAME";
   public final String CURRENTHOMEHOSTNAMEPATH = "STOREFRONT_CURRENTHOMEHOSTNAMEPATH";
   
   public final String HOMEHOSTNAME = "STOREFRONT_HOMEHOSTNAME";
   public final String HOMEHOSTNAMEPATH = "STOREFRONT_HOMEHOSTNAMEPATH";
   public final String HOSTNAME = "STOREFRONT_HOSTNAME";
   public final String HOSTNAMEPATH = "STOREFRONT_HOSTNAMEPATH";
   public final String TESTHOMEHOSTNAME = "STOREFRONT_TESTHOMEHOSTNAME";   
   public final String TESTHOMEHOSTNAMEPATH = "STOREFRONT_TESTHOMEHOSTNAMEPATH";   
   public final String TESTHOSTNAME = "STOREFRONT_TESTHOSTNAME";
   public final String TESTHOSTNAMEPATH = "STOREFRONT_TESTHOSTNAMEPATH";
   public final String IMAGEPATH = "STOREFRONT_IMAGEPATH";
   public final String STATICPATH = "STOREFRONT_STATICPATH";
   public final String CATEGORYPATH = "STOREFRONT_CATEGORYPATH";
   public final String INVENTORYCONTROL = "STOREFRONT_INVENTORYCONTROL";

   public final String CONFIGURATION = "STOREFRONT_CONFIGURATION";
   
   public final String SUBSTORES = "STOREFRONT_SUBSTORES";
   public final String TAGLOCATION = "STOREFRONT_TAGLOCATION";
   public final String PACKAGELOCATION = "STOREFRONT_PACKAGELOCATION";
   public final String FTP = "STOREFRONT_FTP";
   public final String FTPPATH = "STOREFRONT_FTPPATH";
   public final String FTPUSERNAME = "STOREFRONT_FTPUSERNAME";
   public final String FTPPASSWORD = "STOREFRONT_FTPPASSWORD";
   public final String TESTFTP = "STOREFRONT_TESTFTP";
   public final String TESTFTPPATH = "STOREFRONT_TESTFTPPATH";
   public final String TESTFTPUSERNAME = "STOREFRONT_TESTFTPUSERNAME";
   public final String TESTFTPPASSWORD = "STOREFRONT_TESTFTPPASSWORD";
      
   public final String SELECTSTORENAME = "STOREFRONT_SELECTSTORENAME";

}
