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


import org.allbinary.business.context.configuration.ContextConfigurationInterface;
import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.util.BasicArrayList;

public interface StoreFrontInterface extends TableMappingInterface
{ 
   Boolean isNameValid();
   Boolean isValid();
   String nameValidationInfo();
   String validationInfo();
   
   String getTestHtmlPath();
   String getCurrentHostName();
   String getCurrentHostNamePath();   
   String getCurrentHomeHostName();
   String getCurrentHomeHostNamePath();   
   String getName();
   
   //public String getUserName();
   
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

   ContextConfigurationInterface getContextConfigurationInterface();
   void setContextConfigurationInterface(ContextConfigurationInterface contextConfigurationInterface);
   
   /*
   public String getOut();
   public String getAge();
   public String getSale();
   public String getNotification();
   public String getOrder();
   public String getOrderCancel();
    **/
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

   void setName(String value);
   void setBasketName(String value);
   void setHomeHostName(String value);
   void setHomeHostNamePath(String value) throws Exception;
   void setHostName(String value);
   void setHostNamePath(String value) throws Exception;
   void setTestHomeHostName(String value);
   void setTestHomeHostNamePath(String value) throws Exception;
   void setTestHostName(String value);
   void setTestHostNamePath(String value) throws Exception;
   void setImagePath(String value) throws Exception;
   void setStaticPath(String value) throws Exception;
   void setCategoryPath(String value) throws Exception;
   void setPackageLocation(String value);
   void setInventoryControl(String value);
   
   /*
   public void setOut(String value);
   public void setAge(String value);
   public void setSale(String value);
   public void setNotification(String value);
   public void setOrder(String value);
   public void setOrderCancel(String value);
    */
   
   void setFtp(String value);   
   void setFtpPath(String value) throws Exception;
   void setFtpUserName(String value);
   void setFtpPassword(String value);
   void setTestFtp(String value);
   void setTestFtpPath(String value) throws Exception;
   void setTestFtpUserName(String value);
   void setTestFtpPassword(String value);
   void setTimeCreated(String value);
   void setLastModified(String value);

   void install(int current, int total) throws Exception;
   //public boolean createDirectories();
}
