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
   public Boolean isNameValid();
   public Boolean isValid();
   public String nameValidationInfo();
   public String validationInfo();
   
   public String getTestHtmlPath();
   public String getCurrentHostName();
   public String getCurrentHostNamePath();   
   public String getCurrentHomeHostName();
   public String getCurrentHomeHostNamePath();   
   public String getName();
   
   //public String getUserName();
   
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

   public ContextConfigurationInterface getContextConfigurationInterface();
   public void setContextConfigurationInterface(ContextConfigurationInterface contextConfigurationInterface);
   
   /*
   public String getOut();
   public String getAge();
   public String getSale();
   public String getNotification();
   public String getOrder();
   public String getOrderCancel();
    **/
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

   public void setName(String value);
   public void setBasketName(String value);
   public void setHomeHostName(String value);
   public void setHomeHostNamePath(String value) throws Exception;
   public void setHostName(String value);
   public void setHostNamePath(String value) throws Exception;
   public void setTestHomeHostName(String value);
   public void setTestHomeHostNamePath(String value) throws Exception;
   public void setTestHostName(String value);
   public void setTestHostNamePath(String value) throws Exception;
   public void setImagePath(String value) throws Exception;
   public void setStaticPath(String value) throws Exception;
   public void setCategoryPath(String value) throws Exception;
   public void setPackageLocation(String value);
   public void setInventoryControl(String value);
   
   /*
   public void setOut(String value);
   public void setAge(String value);
   public void setSale(String value);
   public void setNotification(String value);
   public void setOrder(String value);
   public void setOrderCancel(String value);
    */
   
   public void setFtp(String value);   
   public void setFtpPath(String value) throws Exception;
   public void setFtpUserName(String value);
   public void setFtpPassword(String value);
   public void setTestFtp(String value);
   public void setTestFtpPath(String value) throws Exception;
   public void setTestFtpUserName(String value);
   public void setTestFtpPassword(String value);
   public void setTimeCreated(String value);
   public void setLastModified(String value);

   public void install(int current, int total) throws Exception;
   //public boolean createDirectories();
}
