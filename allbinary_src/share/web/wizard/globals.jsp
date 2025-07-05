<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
%>
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>
<%@ taglib
       uri="/WEB-INF/generic.tld"
       prefix="generic"
%>
<%@ taglib
       uri="/WEB-INF/admin.tld"
       prefix="admin"
%>
<%@ taglib
       uri="/WEB-INF/payment.tld"
       prefix="payment"
%>
<%@ taglib
       uri="/WEB-INF/transform.tld"
       prefix="transform"
%>
<%@ taglib
       uri="/WEB-INF/customizers.tld"
       prefix="customizers"
%>
<%@ taglib
       uri="/WEB-INF/workflow.tld"
       prefix="workflow"
%>
<%@ taglib
       uri="/WEB-INF/transformInfoObjectConfig.tld"
       prefix="transformInfoObjectConfig"
%>

<%@ page buffer="128kb" %>

<%@ page import="java.io.File" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Vector" %>

<%@ page import="abcs.business.user.role.BasicUserRole" %>

<%@ page import="abcs.globals.URLGLOBALS" %>
<%@ page import="abcs.globals.AppUrlGlobals" %>

<%@ page import="org.allbinary.globals.FREEBLISKET_PATH_GLOBALS" %>

<%@ page import="abcs.logic.basic.io.LineReader" %>
<%@ page import="abcs.logic.basic.io.OutputTypeData" %>
<%@ page import="abcs.logic.basic.io.InputOutputTypeData" %>
<%@ page import="abcs.logic.basic.io.file.FilePathData" %>

<%@ page import="abcs.logic.basic.string.CommonStrings" %>
<%@ page import="abcs.logic.basic.string.StringValidationUtil" %>

<%@ page import="abcs.logic.basic.string.regex.replace.Replace" %>

<%@ page import="abcs.logic.system.loader.WebappClassLoaderInfo" %>

<%@ page import="abcs.logic.visual.transform.info.TransformInfoProperties" %>
<%@ page import="abcs.logic.visual.transform.info.dom.TransformInfoPropertiesDocument" %>

<%@ page import="org.allbinary.globals.GLOBALS" %>

<%@ page import="org.allbinary.business.context.modules.storefront.StoreFrontData" %>

<%@ page import="org.allbinary.business.user.UserData" %>

<%@ page import="org.allbinary.logic.communication.http.request.NameSpaceRequestParamData" %>

<%@ page import="org.allbinary.logic.communication.http.request.session.WeblisketSessionData" %>

<%@ page import="org.allbinary.logic.visual.dhtml.html.name.HtmlNameMathData" %>

<%@ page import="org.allbinary.logic.visual.transform.info.TransformInfosData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.TransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.CustomizerTransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.GeneratorTransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.RootTransformInfoData" %>
<%@ page import="org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData" %>

<%@ page import="org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData" %>
<%@ page import="org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData" %>

<%
AppUrlGlobals urlGlobals = new AppUrlGlobals();
urlGlobals.setWebappPath(request.getRealPath("/"));
URLGLOBALS.init(urlGlobals);

  WebappClassLoaderInfo.setLoader(getClass().getClassLoader());

   //Only works after store and manager are created
   String storeName = (String) session.getAttribute(StoreFrontData.NAME);

   final String SEQUENCEHEADING = "Steps: ";
   
   final String TAB = "TAB_COMMAND";

   String command = (String) request.getParameter(GLOBALS.ADMINCOMMAND);
   String tabCommand = (String) request.getParameter(TAB);

   final String HEADERCOMMAND = "HeaderCommand";
   final String FEELCOMMAND = "FeelCommand";

   final String BACKGROUNDCOLOR="#FFFFFF";

   final String BUTTONOVERCOLOR="#777777";
   final String BUTTONCOLOR="#0000AA";
   final String SIDEBARCOLOR="#BB00DD";
   final String SPECIALCOLOR1 = "#AA00AA";
   final String SPECIALCOLOR2 = "#999999";

   final String ERRORMESSAGE = "An Error Has Occured.  " +
      "Please contact support if the error persists.<br/>";

   //wizard URLs
   final String LOGOUTPAGE = "logout.jsp";

   final String STARTPAGE = "start.jsp";
   final String LOGINPAGE = "start.jsp";

   final String ADDPAGES = "addPages.jsp";
   final String EDITPAGE = "editPage.jsp";
   final String UPDATEPAGE = "updatePage.jsp";

   final String GENERATEPAGESPAGE = "generatePages.jsp";

   //final String CREATEPAGE = "createStore.jsp";

   final String TEMPLATETYPEPAGE = "selectTemplateType.jsp";
   final String ADDHEADERPAGE = "addHeaderPage.jsp";
   final String ADDTEMPLATETHEMEPAGE = "addTemplateTheme.jsp";
   final String ADDTEMPLATESTYLEPAGE = "addTemplateStyle.jsp";

   final String FEELSTYLEPAGE = "createFeelStyle.jsp";
   
   final String CREATEHEADERPAGE = "createHeader.jsp";
   
   //final String PAGE = "createFooter.jsp";
   
   //final String PAGE = "gatewayConfig.jsp";
   
   final String FINISHPAGE = "finish.jsp";
   
//   final String UPDATEHEADERPAGE = "updateHeaderPage.jsp";
//   final String DELETEHEADERPAGE = "deleteHeaderPage.jsp";
//   final String EDITHEADERPAGE = "editHeaderPage.jsp";
   
   final String ADDTEMPLATECUSTOMIZERPAGE = "addTemplateCustomizerPage.jsp";
   final String UPDATETEMPLATECUSTOMIZERPAGE = 
      "updateTemplateCustomizerPage.jsp";
   final String DELETETEMPLATECUSTOMIZERPAGE = 
      "deleteTemplateCustomizerPage.jsp";
   final String EDITTEMPLATECUSTOMIZERPAGE = "editTemplateCustomizerPage.jsp";
   final String NEWTEMPLATECUSTOMIZERPAGE = "newTemplateCustomizerPage.jsp";

   final String ADDDEFAULTDATAFROMGENERICCUSTOMIZERSPAGE = 
      "addDefaultDataFromGenericCustomizers.jsp";
   
   //View Info

   final String CONTEXTCLASSPATH = "views.business.context.";
   final String MODULECONTEXTCLASSPATH = CONTEXTCLASSPATH + "modules.";
   final String STOREFRONTCONTEXTCLASSPATH = 
      MODULECONTEXTCLASSPATH + "storefront.";
   
   //for body and other generic views
   final String GENERICVIEWOBJECTFILE = 
      STOREFRONTCONTEXTCLASSPATH + "StoreComponentView";
   //for page views
   final String STORETEMPLATECOMPOUNDVIEWOBJECTFILE = 
      "views.compound.CompoundComponentView";
   //for page views
   
   final String CUSTOMIZERSCLASSPATH = 
      STOREFRONTCONTEXTCLASSPATH + "customizer.";
   
   final String GENERICBODYCUSTOMIZERVIEWOBJECTPACKAGE = 
      CUSTOMIZERSCLASSPATH + "bodies.generic.";
   final String TITLEBODYCUSTOMIZERVIEWOBJECTPACKAGE = 
      CUSTOMIZERSCLASSPATH + "bodies.generic.title.";
   
   final String GENERICBODYCUSTOMIZERVIEWOBJECT = "GenericBodyValidationView";
   final String TITLEBODYCUSTOMIZERVIEWOBJECT = "TitleBodyValidationView";

   final String PAGEVIEWOBJECT = 
      CUSTOMIZERSCLASSPATH + "generic.page.PageValidationView";
   
   final String CUSTOMDIR = "custom/";
   final String GENERICDIR = "generic/";

   final String BODYDIR = GENERICDIR + "bodies/";
   final String HEDGEDIR = GENERICDIR + "hedges/";
   final String INCLUDESDIR = GENERICDIR + "includes/";

   final String WINDOWDIR = INCLUDESDIR + "windows/";

   final String CATEGORYDIR = WINDOWDIR + "category/";
   final String CUSTOMERDIR = WINDOWDIR + "customer/";
   final String HELPDIR = WINDOWDIR + "help/";
   final String NEWSDIR = WINDOWDIR + "news/";
   final String PARTNERSDIR = WINDOWDIR + "partners/";
   final String PRODUCTSDIR = WINDOWDIR + "products/";
   final String REBATESDIR = WINDOWDIR + "rebates/";
   final String SEARCHDIR = WINDOWDIR + "search/";
   
   final String CUSTOMBODYDIR = CUSTOMDIR + "bodies/";
   
   final String WIZARDDIR = GENERICDIR + "wizard/";
   final String COLOROPTIONS = WIZARDDIR + "colorOptions.xsl";

   final String TEMPLATEDIR = "template/";
   final String TYPEDIR = "type/";
   final String STYLEDIR = "style/";
   final String THEMEDIR = "theme/";
   final String TEMPLATECUSTOMIZERDIR = 
      TEMPLATEDIR + GENERICDIR + "customizer/";

   final String PAYMENTGATEWAYDIR = "payment/gateway/";

   final String ADMINXSLPATH = GENERICDIR + "admin/";
   final String IGNOREXMLXSL = GENERICDIR + "ignoreXmlView.xsl";
   final String CSSVIEWTEMPLATE = 
      "template/generic/includes/style/css/dynamicCss.xsl";

   final int TOTALSTEPS = 5;
   
   final String BASIC = "Basic";
   final String ADVANCED = "Advanced";
   
   Vector viewPrefixVector = new Vector();
   viewPrefixVector.add(GLOBALS.NEW);
   viewPrefixVector.add(GLOBALS.INSERT);
   viewPrefixVector.add(GLOBALS.DELETE);
   viewPrefixVector.add(GLOBALS.EDIT);
   viewPrefixVector.add(GLOBALS.UPDATE);

   final CommonStrings commonStrings = CommonStrings.getInstance();
%>
<%@ include file="templateGlobals.jsp" %>
<%@ include file="../admin/generatedPageNames.jsp" %>

<ecommerce:customloader command="<%= GLOBALS.SET %>"
   webappPath="<%= URLGLOBALS.getWebappPath() %>"/>

<%
   Vector roles = new Vector();
   roles.add(BasicUserRole.STOREMANAGER);
%>
