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

import org.allbinary.util.BasicArrayList;


public interface BasicStoreFrontInterface
{ 
   String getTestHtmlPath();
   String getCurrentHostName();
   String getCurrentHostNamePath();   
   String getCurrentHomeHostName();
   String getCurrentHomeHostNamePath();   
   String getName();
   String getUserName();
   String getBasketName();
   String getHomeHostName();
   String getHomeHostNamePath();
   String getHostName();
   String getHostNamePath();
   String getTestHomeHostName();
   String getTestHomeHostNamePath();
   String getTestHostName();
   String getTestHostNamePath();
   //public String getImagePath();
   String getStaticPath();
   String getCategoryPath();
   BasicArrayList getSubStores() throws Exception;
   String getTagLocation();
   String getPackageLocation();
   String getInventoryControl();

   String getFtp();
   String getFtpPath();
   String getFtpUserName();
   String getFtpPassword();
   String getTestFtp();
   String getTestFtpPath();
   String getTestFtpUserName();
   String getTestFtpPassword();
   String getTimeCreated();
   String getLastModified();   

}
