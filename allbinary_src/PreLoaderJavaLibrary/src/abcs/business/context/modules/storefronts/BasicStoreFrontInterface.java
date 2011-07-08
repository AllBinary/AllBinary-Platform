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
package abcs.business.context.modules.storefronts;

import org.allbinary.util.BasicArrayList;


public interface BasicStoreFrontInterface
{ 
   public String getTestHtmlPath();
   public String getCurrentHostName();
   public String getCurrentHostNamePath();   
   public String getCurrentHomeHostName();
   public String getCurrentHomeHostNamePath();   
   public String getName();
   public String getUserName();
   public String getBasketName();
   public String getHomeHostName();
   public String getHomeHostNamePath();
   public String getHostName();
   public String getHostNamePath();
   public String getTestHomeHostName();
   public String getTestHomeHostNamePath();
   public String getTestHostName();
   public String getTestHostNamePath();
   //public String getImagePath();
   public String getStaticPath();
   public String getCategoryPath();
   public BasicArrayList getSubStores() throws Exception;
   public String getTagLocation();
   public String getPackageLocation();
   public String getInventoryControl();

   public String getFtp();
   public String getFtpPath();
   public String getFtpUserName();
   public String getFtpPassword();
   public String getTestFtp();
   public String getTestFtpPath();
   public String getTestFtpUserName();
   public String getTestFtpPassword();
   public String getTimeCreated();
   public String getLastModified();   

}
