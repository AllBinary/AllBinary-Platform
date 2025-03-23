/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class LogConfigTypeFactory {
    
    private static final LogConfigTypeFactory instance = new LogConfigTypeFactory();

    /**
     * @return the instance
     */
    public static LogConfigTypeFactory getInstance() {
        return instance;
    }
    
   private final String NO_DESCRIPTION = "No Description";
   
   //The Following Strings contain all of the logging levels
   public final LogConfigType AUTHENTICATION = new LogConfigType("Authentication",NO_DESCRIPTION);
   public final LogConfigType AUTHENTICATIONERROR = new LogConfigType("Authentication Error",NO_DESCRIPTION);
   
   public final LogConfigType CATEGORY = new LogConfigType("Category",NO_DESCRIPTION);
   public final LogConfigType CATEGORYERROR = new LogConfigType("Category Error",NO_DESCRIPTION);
   
   public final LogConfigType CRYPT = new LogConfigType("Crypt",NO_DESCRIPTION);
   public final LogConfigType CRYPTERROR = new LogConfigType("Crypt Error",NO_DESCRIPTION);
   
   public final LogConfigType EMAILLOGGING = new LogConfigType("Email",NO_DESCRIPTION);
   //org.allbinary.business.user.modules.admin.configuration.AdminConfiguration
   //org.allbinary.business.context.configuration.ContextConfiguration

   public final LogConfigType EMAILLOGGINGERROR = new LogConfigType("Email Error",NO_DESCRIPTION);
   
   public final LogConfigType ENTITYFACTORYERROR = new LogConfigType("Entity Factory Error",NO_DESCRIPTION);
   //org.allbinary.data.tables.licensing.server.LicenseServerEntityFactory
   //org.allbinary.data.tables.licensing.server.request.LicenseServerRequestEntityFactory
   
   public final LogConfigType NETBEANS_MODULE = new LogConfigType("NetBeans Module",NO_DESCRIPTION);
   public final LogConfigType NETBEANS_MODULE_ERROR = new LogConfigType("NetBeans Module Error",NO_DESCRIPTION);
   
   public final LogConfigType FACTORYERROR = new LogConfigType("General Factory Error",NO_DESCRIPTION);
   
   public final LogConfigType FILE = new LogConfigType("File",NO_DESCRIPTION);
   public final LogConfigType FILEERROR = new LogConfigType("File Error",NO_DESCRIPTION);
   
   public final LogConfigType HTTP = new LogConfigType("HTTP",NO_DESCRIPTION);
   public final LogConfigType HTTPERROR = new LogConfigType("HTTP Error",NO_DESCRIPTION);
   
   public final LogConfigType HTTPREQUEST = new LogConfigType("Http Request",NO_DESCRIPTION);
   //org.allbinary.logic.communication.http.request
   public final LogConfigType HTTPREQUESTERROR = new LogConfigType("Http Request Error",NO_DESCRIPTION);
   
   public final LogConfigType IDLOGGING = new LogConfigType("ID Logging",NO_DESCRIPTION);
   
   public final LogConfigType JSPEXTRAOUTPUT = new LogConfigType("JSP Extra Output",NO_DESCRIPTION);
   
   public final LogConfigType JSPTAGEXTRAOUTPUT = new LogConfigType("JSP Tag Extra Output",NO_DESCRIPTION);
   
   public final LogConfigType JSPTAG = new LogConfigType("JSP Tag",NO_DESCRIPTION);
   public final LogConfigType JSPTAGERROR = new LogConfigType("JSP Tag Error",NO_DESCRIPTION);
   
   public final LogConfigType LICENSING = new LogConfigType("Licensing",NO_DESCRIPTION);
   public final LogConfigType LICENSINGERROR = new LogConfigType("Licensing Error",NO_DESCRIPTION);
   //org.allbinary.logic.system.security.licensing.server.AbeRequestClientInformation
   //org.allbinary.business.object.licensing.server.request.ServerLicenseRequestFactory
   //org.allbinary.business.object.licensing.server.ServerLicenseXmlRpcReponseFactory
   //org.allbinary.servlet.licensing.server.AbeLicenseServerServlet
   //org.allbinary.business.object.licensing.server.ServerLicenseFactory
   //org.allbinary.logic.system.security.licensing.server.AbeLicenseServerXmlRpcHandler
   
   public final LogConfigType LOADER = new LogConfigType("Loader",NO_DESCRIPTION);
   
   public final LogConfigType LOADERERROR = new LogConfigType("Loader Error",NO_DESCRIPTION);
   
   public final LogConfigType OS = new LogConfigType("OS",NO_DESCRIPTION);
   public final LogConfigType OSERROR = new LogConfigType("OS Error",NO_DESCRIPTION);
   
   public final LogConfigType PAYMENT = new LogConfigType("Payment",NO_DESCRIPTION);
//   org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResult
   public final LogConfigType PAYMENTERROR = new LogConfigType("Payment Error",NO_DESCRIPTION);
   
   public final LogConfigType PRELOADER = new LogConfigType("PreLoader",NO_DESCRIPTION);
   public final LogConfigType PRELOADERERROR = new LogConfigType("PreLoader Error",NO_DESCRIPTION);
   
   public final LogConfigType PRODUCTSEARCHLOGGING = new LogConfigType("Product Search Logging",NO_DESCRIPTION);
   public final LogConfigType PRODUCTSEARCHLOGGINGERROR = new LogConfigType("Product Search Logging Error",NO_DESCRIPTION);
   
   public final LogConfigType SERVLET = new LogConfigType("Servlet",NO_DESCRIPTION);
   public final LogConfigType SERVLETERROR = new LogConfigType("Servlet Error",NO_DESCRIPTION);
   
   public final LogConfigType SHIPPINGERROR = new LogConfigType("Shipping Error",NO_DESCRIPTION);
   
   public final LogConfigType SQLLOGGINGERROR = new LogConfigType("SQL Error",NO_DESCRIPTION);
   public final LogConfigType SQLLOGGING = new LogConfigType("SQL Logging",NO_DESCRIPTION);
   //org.allbinary.data.tables.licensing.server.LicenseServerEntity
   //org.allbinary.data.tables.licensing.server.request.LicenseServerRequestEntity
   
   public final LogConfigType SQLTAGS = new LogConfigType("SQL Tag Logging",NO_DESCRIPTION);
   public final LogConfigType SQLTAGSERROR = new LogConfigType("SQL Tag Error",NO_DESCRIPTION);
   
   public final LogConfigType STYLE = new LogConfigType("Style",NO_DESCRIPTION);
   public final LogConfigType STYLEERROR = new LogConfigType("Style Error",NO_DESCRIPTION);
   
   public final LogConfigType TAGHELPER = new LogConfigType("Tag Helper",NO_DESCRIPTION);
   public final LogConfigType TAGHELPERERROR = new LogConfigType("Tag Helper Error",NO_DESCRIPTION);
   
   public final LogConfigType TAGHELPERFACTORY = new LogConfigType("Tag Helper Factory",NO_DESCRIPTION);
   public final LogConfigType TAGHELPERFACTORYERROR = new LogConfigType("Tag Helper Factory Error",NO_DESCRIPTION);
   
   public final LogConfigType TAX = new LogConfigType("Tax",NO_DESCRIPTION);
   public final LogConfigType TAXERROR = new LogConfigType("Tax Error",NO_DESCRIPTION);
   
   public final LogConfigType VALIDATION = new LogConfigType("Validation",NO_DESCRIPTION);
   public final LogConfigType VALIDATIONERROR = new LogConfigType("Validation Error",NO_DESCRIPTION);
   
   public final LogConfigType VIEW = new LogConfigType("View",NO_DESCRIPTION);
   public final LogConfigType VIEWERROR = new LogConfigType("View Error",NO_DESCRIPTION);
   
   public final LogConfigType VIEWFACTORYERROR = new LogConfigType("View Factory Error",NO_DESCRIPTION);
   
   public final LogConfigType WORKFLOW = new LogConfigType("Workflow",NO_DESCRIPTION);
   public final LogConfigType WORKFLOWERROR = new LogConfigType("Workflow Error",NO_DESCRIPTION);
   
   public final LogConfigType XMLLOGGING = new LogConfigType("XML Logging",NO_DESCRIPTION);
   public final LogConfigType XMLLOGGINGERROR = new LogConfigType("XML Logging Error",NO_DESCRIPTION);
   
   public final LogConfigType XSLLOGGING = new LogConfigType("XSL Logging",NO_DESCRIPTION);
   public final LogConfigType XSLLOGGINGERROR = new LogConfigType("XSL Logging Error",NO_DESCRIPTION);
   //end log vars

   //The Following Strings contain all of the logging levels
   public final LogConfigType REPLACE = new LogConfigType("Replace", NO_DESCRIPTION);
   public final LogConfigType REPLACE_INFO = new LogConfigType("Replace Info", NO_DESCRIPTION);
   public final LogConfigType REPLACEERROR = new LogConfigType("Replace Error", NO_DESCRIPTION);
   
   public final LogConfigType SQLLOGGINGPOOL = new LogConfigType("SQL Pool", NO_DESCRIPTION);
   public final LogConfigType SQLLOGGINGPOOLERROR = new LogConfigType("SQL Pool Error", NO_DESCRIPTION);
   
   public final LogConfigType STATICPAGEGENERATIONLOGGING = new LogConfigType("Static Page Generation Logging", NO_DESCRIPTION);
   public final LogConfigType STATICPAGEGENERATIONLOGGINGERROR = new LogConfigType("Static Page Generation Logging Error", NO_DESCRIPTION);

   private LogConfigTypeFactory() {
       PreLogUtil.put("FreeBlisket", this, CommonStrings.getInstance().CONSTRUCTOR);
   }
}
