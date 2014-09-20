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
package org.allbinary.logic.communication.log.config.type;

import org.allbinary.util.BasicArrayList;

public class LogConfigType
{
   private String name;
   private String description;
   
   public static BasicArrayList availableLogConfigTypes = new BasicArrayList();
   
   private static final String NO_DESCRIPTION = "No Description";
   
   //The Following Strings contain all of the logging levels
   public static LogConfigType AUTHENTICATION = new LogConfigType("Authentication",NO_DESCRIPTION);
   public static LogConfigType AUTHENTICATIONERROR = new LogConfigType("Authentication Error",NO_DESCRIPTION);
   
   public static LogConfigType CATEGORY = new LogConfigType("Category",NO_DESCRIPTION);
   public static LogConfigType CATEGORYERROR = new LogConfigType("Category Error",NO_DESCRIPTION);
   
   public static LogConfigType CRYPT = new LogConfigType("Crypt",NO_DESCRIPTION);
   public static LogConfigType CRYPTERROR = new LogConfigType("Crypt Error",NO_DESCRIPTION);
   
   public static LogConfigType EMAILLOGGING = new LogConfigType("Email",NO_DESCRIPTION);
   public static LogConfigType EMAILLOGGINGERROR = new LogConfigType("Email Error",NO_DESCRIPTION);
   
   public static LogConfigType ENTITYFACTORYERROR = new LogConfigType("Entity Factory Error",NO_DESCRIPTION);
   
   public static LogConfigType NETBEANS_MODULE = new LogConfigType("NetBeans Module",NO_DESCRIPTION);
   public static LogConfigType NETBEANS_MODULE_ERROR = new LogConfigType("NetBeans Module Error",NO_DESCRIPTION);
   
   public static LogConfigType FACTORYERROR = new LogConfigType("General Factory Error",NO_DESCRIPTION);
   
   public static LogConfigType FILE = new LogConfigType("File",NO_DESCRIPTION);
   public static LogConfigType FILEERROR = new LogConfigType("File Error",NO_DESCRIPTION);
   
   public static LogConfigType HTTP = new LogConfigType("HTTP",NO_DESCRIPTION);
   public static LogConfigType HTTPERROR = new LogConfigType("HTTP Error",NO_DESCRIPTION);
   
   public static LogConfigType HTTPREQUEST = new LogConfigType("Http Request",NO_DESCRIPTION);
   public static LogConfigType HTTPREQUESTERROR = new LogConfigType("Http Request Error",NO_DESCRIPTION);
   
   public static LogConfigType IDLOGGING = new LogConfigType("ID Logging",NO_DESCRIPTION);
   
   public static LogConfigType JSPEXTRAOUTPUT = new LogConfigType("JSP Extra Output",NO_DESCRIPTION);
   
   public static LogConfigType JSPTAGEXTRAOUTPUT = new LogConfigType("JSP Tag Extra Output",NO_DESCRIPTION);
   
   public static LogConfigType JSPTAG = new LogConfigType("JSP Tag",NO_DESCRIPTION);
   public static LogConfigType JSPTAGERROR = new LogConfigType("JSP Tag Error",NO_DESCRIPTION);
   
   public static LogConfigType LICENSING = new LogConfigType("Licensing",NO_DESCRIPTION);
   public static LogConfigType LICENSINGERROR = new LogConfigType("Licensing Error",NO_DESCRIPTION);
   
   public static LogConfigType LOADER = new LogConfigType("Loader",NO_DESCRIPTION);
   
   public static LogConfigType LOADERERROR = new LogConfigType("Loader Error",NO_DESCRIPTION);
   
   public static LogConfigType OS = new LogConfigType("OS",NO_DESCRIPTION);
   public static LogConfigType OSERROR = new LogConfigType("OS Error",NO_DESCRIPTION);
   
   public static LogConfigType PAYMENT = new LogConfigType("Payment",NO_DESCRIPTION);
   public static LogConfigType PAYMENTERROR = new LogConfigType("Payment Error",NO_DESCRIPTION);
   
   public static LogConfigType PRELOADER = new LogConfigType("PreLoader",NO_DESCRIPTION);
   public static LogConfigType PRELOADERERROR = new LogConfigType("PreLoader Error",NO_DESCRIPTION);
   
   public static LogConfigType PRODUCTSEARCHLOGGING = new LogConfigType("Product Search Logging",NO_DESCRIPTION);
   public static LogConfigType PRODUCTSEARCHLOGGINGERROR = new LogConfigType("Product Search Logging Error",NO_DESCRIPTION);
   
   public static LogConfigType SERVLET = new LogConfigType("Servlet",NO_DESCRIPTION);
   public static LogConfigType SERVLETERROR = new LogConfigType("Servlet Error",NO_DESCRIPTION);
   
   public static LogConfigType SHIPPINGERROR = new LogConfigType("Shipping Error",NO_DESCRIPTION);
   
   public static LogConfigType SQLLOGGINGERROR = new LogConfigType("SQL Error",NO_DESCRIPTION);
   public static LogConfigType SQLLOGGING = new LogConfigType("SQL Logging",NO_DESCRIPTION);
   
   public static LogConfigType SQLTAGS = new LogConfigType("SQL Tag Logging",NO_DESCRIPTION);
   public static LogConfigType SQLTAGSERROR = new LogConfigType("SQL Tag Error",NO_DESCRIPTION);
   
   public static LogConfigType STYLE = new LogConfigType("Style",NO_DESCRIPTION);
   public static LogConfigType STYLEERROR = new LogConfigType("Style Error",NO_DESCRIPTION);
   
   public static LogConfigType TAGHELPER = new LogConfigType("Tag Helper",NO_DESCRIPTION);
   public static LogConfigType TAGHELPERERROR = new LogConfigType("Tag Helper Error",NO_DESCRIPTION);
   
   public static LogConfigType TAGHELPERFACTORY = new LogConfigType("Tag Helper Factory",NO_DESCRIPTION);
   public static LogConfigType TAGHELPERFACTORYERROR = new LogConfigType("Tag Helper Factory Error",NO_DESCRIPTION);
   
   public static LogConfigType TAX = new LogConfigType("Tax",NO_DESCRIPTION);
   public static LogConfigType TAXERROR = new LogConfigType("Tax Error",NO_DESCRIPTION);
   
   public static LogConfigType VALIDATION = new LogConfigType("Validation",NO_DESCRIPTION);
   public static LogConfigType VALIDATIONERROR = new LogConfigType("Validation Error",NO_DESCRIPTION);
   
   public static LogConfigType VIEW = new LogConfigType("View",NO_DESCRIPTION);
   public static LogConfigType VIEWERROR = new LogConfigType("View Error",NO_DESCRIPTION);
   
   public static LogConfigType VIEWFACTORYERROR = new LogConfigType("View Factory Error",NO_DESCRIPTION);
   
   public static LogConfigType WORKFLOW = new LogConfigType("Workflow",NO_DESCRIPTION);
   public static LogConfigType WORKFLOWERROR = new LogConfigType("Workflow Error",NO_DESCRIPTION);
   
   public static LogConfigType XMLLOGGING = new LogConfigType("XML Logging",NO_DESCRIPTION);
   public static LogConfigType XMLLOGGINGERROR = new LogConfigType("XML Logging Error",NO_DESCRIPTION);
   
   public static LogConfigType XSLLOGGING = new LogConfigType("XSL Logging",NO_DESCRIPTION);
   public static LogConfigType XSLLOGGINGERROR = new LogConfigType("XSL Logging Error",NO_DESCRIPTION);
   //end log vars

   //The Following Strings contain all of the logging levels
   public static LogConfigType REPLACE = new LogConfigType("Replace", NO_DESCRIPTION);
   public static LogConfigType REPLACE_INFO = new LogConfigType("Replace Info", NO_DESCRIPTION);
   public static LogConfigType REPLACEERROR = new LogConfigType("Replace Error", NO_DESCRIPTION);
   
   public static LogConfigType SQLLOGGINGPOOL = new LogConfigType("SQL Pool", NO_DESCRIPTION);
   public static LogConfigType SQLLOGGINGPOOLERROR = new LogConfigType("SQL Pool Error", NO_DESCRIPTION);
   
   public static LogConfigType STATICPAGEGENERATIONLOGGING = new LogConfigType("Static Page Generation Logging", NO_DESCRIPTION);
   public static LogConfigType STATICPAGEGENERATIONLOGGINGERROR = new LogConfigType("Static Page Generation Logging Error", NO_DESCRIPTION);
   
   public LogConfigType(String name, String description)
   {
      this.name = name;
      this.description = description;
      availableLogConfigTypes.add(this);
   }

   public String getName()
   {
      return this.name;
   }

   public String getDescription()
   {
      return this.description;
   }

   public void setName(String value)
   {
      this.name = value;
   }

   public void setDescription(String value)
   {
      this.description = value;
   }
   
}
