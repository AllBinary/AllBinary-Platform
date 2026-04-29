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
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final LogConfigTypeFactory instance = new LogConfigTypeFactory();

    /**
     * @return the instance
     */
    public static LogConfigTypeFactory getInstance() {
        return LogConfigTypeFactory.instance;
    }
    
   private final String NO_DESCRIPTION = "No Description";
   
   //The Following Strings contain all of the logging levels
   public final LogConfigType AUTHENTICATION = new LogConfigType("Authentication",this.NO_DESCRIPTION);
   public final LogConfigType AUTHENTICATIONERROR = new LogConfigType("Authentication Error",this.NO_DESCRIPTION);
   
   public final LogConfigType CATEGORY = new LogConfigType("Category",this.NO_DESCRIPTION);
   public final LogConfigType CATEGORYERROR = new LogConfigType("Category Error",this.NO_DESCRIPTION);
   
   public final LogConfigType CRYPT = new LogConfigType("Crypt",this.NO_DESCRIPTION);
   public final LogConfigType CRYPTERROR = new LogConfigType("Crypt Error",this.NO_DESCRIPTION);
   
   public final LogConfigType EMAILLOGGING = new LogConfigType("Email",this.NO_DESCRIPTION);
   //org.allbinary.business.user.modules.admin.configuration.AdminConfiguration
   //org.allbinary.business.context.configuration.ContextConfiguration

   public final LogConfigType EMAILLOGGINGERROR = new LogConfigType("Email Error",this.NO_DESCRIPTION);
   
   public final LogConfigType ENTITYFACTORYERROR = new LogConfigType("Entity Factory Error",this.NO_DESCRIPTION);
   //org.allbinary.data.tables.licensing.server.LicenseServerEntityFactory
   //org.allbinary.data.tables.licensing.server.request.LicenseServerRequestEntityFactory
   
   public final LogConfigType NETBEANS_MODULE = new LogConfigType("NetBeans Module",this.NO_DESCRIPTION);
   public final LogConfigType NETBEANS_MODULE_ERROR = new LogConfigType("NetBeans Module Error",this.NO_DESCRIPTION);
   
   public final LogConfigType FACTORYERROR = new LogConfigType("General Factory Error",this.NO_DESCRIPTION);
   
   public final LogConfigType FILE = new LogConfigType("File",this.NO_DESCRIPTION);
   public final LogConfigType FILEERROR = new LogConfigType("File Error",this.NO_DESCRIPTION);
   
   public final LogConfigType HTTP = new LogConfigType("HTTP",this.NO_DESCRIPTION);
   public final LogConfigType HTTPERROR = new LogConfigType("HTTP Error",this.NO_DESCRIPTION);
   
   public final LogConfigType HTTPREQUEST = new LogConfigType("Http Request",this.NO_DESCRIPTION);
   //org.allbinary.logic.communication.http.request
   public final LogConfigType HTTPREQUESTERROR = new LogConfigType("Http Request Error",this.NO_DESCRIPTION);
   
   public final LogConfigType IDLOGGING = new LogConfigType("ID Logging",this.NO_DESCRIPTION);
   
   public final LogConfigType JSPEXTRAOUTPUT = new LogConfigType("JSP Extra Output",this.NO_DESCRIPTION);
   
   public final LogConfigType JSPTAGEXTRAOUTPUT = new LogConfigType("JSP Tag Extra Output",this.NO_DESCRIPTION);
   
   public final LogConfigType JSPTAG = new LogConfigType("JSP Tag",this.NO_DESCRIPTION);
   public final LogConfigType JSPTAGERROR = new LogConfigType("JSP Tag Error",this.NO_DESCRIPTION);
   
   public final LogConfigType LICENSING = new LogConfigType("Licensing",this.NO_DESCRIPTION);
   public final LogConfigType LICENSINGERROR = new LogConfigType("Licensing Error",this.NO_DESCRIPTION);
   //org.allbinary.logic.system.security.licensing.server.AbeRequestClientInformation
   //org.allbinary.business.object.licensing.server.request.ServerLicenseRequestFactory
   //org.allbinary.business.object.licensing.server.ServerLicenseXmlRpcReponseFactory
   //org.allbinary.servlet.licensing.server.AbeLicenseServerServlet
   //org.allbinary.business.object.licensing.server.ServerLicenseFactory
   //org.allbinary.logic.system.security.licensing.server.AbeLicenseServerXmlRpcHandler
   
   public final LogConfigType LOADER = new LogConfigType("Loader",this.NO_DESCRIPTION);
   
   public final LogConfigType LOADERERROR = new LogConfigType("Loader Error",this.NO_DESCRIPTION);
   
   public final LogConfigType OS = new LogConfigType("OS",this.NO_DESCRIPTION);
   public final LogConfigType OSERROR = new LogConfigType("OS Error",this.NO_DESCRIPTION);
   
   public final LogConfigType PAYMENT = new LogConfigType("Payment",this.NO_DESCRIPTION);
//   org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResult
   public final LogConfigType PAYMENTERROR = new LogConfigType("Payment Error",this.NO_DESCRIPTION);
   
   public final LogConfigType PRELOADER = new LogConfigType("PreLoader",this.NO_DESCRIPTION);
   public final LogConfigType PRELOADERERROR = new LogConfigType("PreLoader Error",this.NO_DESCRIPTION);
   
   public final LogConfigType PRODUCTSEARCHLOGGING = new LogConfigType("Product Search Logging",this.NO_DESCRIPTION);
   public final LogConfigType PRODUCTSEARCHLOGGINGERROR = new LogConfigType("Product Search Logging Error",this.NO_DESCRIPTION);
   
   public final LogConfigType SERVLET = new LogConfigType("Servlet",this.NO_DESCRIPTION);
   public final LogConfigType SERVLETERROR = new LogConfigType("Servlet Error",this.NO_DESCRIPTION);
   
   public final LogConfigType SHIPPINGERROR = new LogConfigType("Shipping Error",this.NO_DESCRIPTION);
   
   public final LogConfigType SQLLOGGINGERROR = new LogConfigType("SQL Error",this.NO_DESCRIPTION);
   public final LogConfigType SQLLOGGING = new LogConfigType("SQL Logging",this.NO_DESCRIPTION);
   //org.allbinary.data.tables.licensing.server.LicenseServerEntity
   //org.allbinary.data.tables.licensing.server.request.LicenseServerRequestEntity
   
   public final LogConfigType SQLTAGS = new LogConfigType("SQL Tag Logging",this.NO_DESCRIPTION);
   public final LogConfigType SQLTAGSERROR = new LogConfigType("SQL Tag Error",this.NO_DESCRIPTION);
   
   public final LogConfigType STYLE = new LogConfigType("Style",this.NO_DESCRIPTION);
   public final LogConfigType STYLEERROR = new LogConfigType("Style Error",this.NO_DESCRIPTION);
   
   public final LogConfigType TAGHELPER = new LogConfigType("Tag Helper",this.NO_DESCRIPTION);
   public final LogConfigType TAGHELPERERROR = new LogConfigType("Tag Helper Error",this.NO_DESCRIPTION);
   
   public final LogConfigType TAGHELPERFACTORY = new LogConfigType("Tag Helper Factory",this.NO_DESCRIPTION);
   public final LogConfigType TAGHELPERFACTORYERROR = new LogConfigType("Tag Helper Factory Error",this.NO_DESCRIPTION);
   
   public final LogConfigType TAX = new LogConfigType("Tax",this.NO_DESCRIPTION);
   public final LogConfigType TAXERROR = new LogConfigType("Tax Error",this.NO_DESCRIPTION);
   
   public final LogConfigType VALIDATION = new LogConfigType("Validation",this.NO_DESCRIPTION);
   public final LogConfigType VALIDATIONERROR = new LogConfigType("Validation Error",this.NO_DESCRIPTION);
   
   public final LogConfigType VIEW = new LogConfigType("View",this.NO_DESCRIPTION);
   public final LogConfigType VIEWERROR = new LogConfigType("View Error",this.NO_DESCRIPTION);
   
   public final LogConfigType VIEWFACTORYERROR = new LogConfigType("View Factory Error",this.NO_DESCRIPTION);
   
   public final LogConfigType WORKFLOW = new LogConfigType("Workflow",this.NO_DESCRIPTION);
   public final LogConfigType WORKFLOWERROR = new LogConfigType("Workflow Error",this.NO_DESCRIPTION);
   
   public final LogConfigType XMLLOGGING = new LogConfigType("XML Logging",this.NO_DESCRIPTION);
   public final LogConfigType XMLLOGGINGERROR = new LogConfigType("XML Logging Error",this.NO_DESCRIPTION);
   
   public final LogConfigType XSLLOGGING = new LogConfigType("XSL Logging",this.NO_DESCRIPTION);
   public final LogConfigType XSLLOGGINGERROR = new LogConfigType("XSL Logging Error",this.NO_DESCRIPTION);
   //end log vars

   //The Following Strings contain all of the logging levels
   public final LogConfigType REPLACE = new LogConfigType("Replace", this.NO_DESCRIPTION);
   public final LogConfigType REPLACE_INFO = new LogConfigType("Replace Info", this.NO_DESCRIPTION);
   public final LogConfigType REPLACEERROR = new LogConfigType("Replace Error", this.NO_DESCRIPTION);
   
   public final LogConfigType SQLLOGGINGPOOL = new LogConfigType("SQL Pool", this.NO_DESCRIPTION);
   public final LogConfigType SQLLOGGINGPOOLERROR = new LogConfigType("SQL Pool Error", this.NO_DESCRIPTION);
   
   public final LogConfigType STATICPAGEGENERATIONLOGGING = new LogConfigType("Static Page Generation Logging", this.NO_DESCRIPTION);
   public final LogConfigType STATICPAGEGENERATIONLOGGINGERROR = new LogConfigType("Static Page Generation Logging Error", this.NO_DESCRIPTION);

   private LogConfigTypeFactory() {
       PreLogUtil.put("FreeBlisket", this, CommonStrings.getInstance().CONSTRUCTOR);
   }
}
