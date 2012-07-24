<%
/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
%>
<%@ include file="directiveTagLibGlobals.jsp" %>

<%@ include file="directivePageImportGlobals.jsp" %>

<%@ include file="otherGlobals.jsp" %>

<%

final String ALLBINARYSITE = "http://www.allbinary.com/";
final String WEBLISKETSITE = "http://www.weblisket.com/";

final String WEBLISKETFORUM = WEBLISKETSITE + "forum/";
final String WEBLISKETSUPPORTPAGE = WEBLISKETSITE + "Support.jsp";

final String FREEBLISKETONLINEHELP = WEBLISKETSITE + "help/";
final String ADMINFREEBLISKETONLINEHELP = FREEBLISKETONLINEHELP + "admin/";
final String STOREADMINFREEBLISKETONLINEHELP = FREEBLISKETONLINEHELP + "store/";

AppUrlGlobals urlGlobals = new AppUrlGlobals();
urlGlobals.setWebappPath(request.getRealPath("/"));
URLGLOBALS.init(urlGlobals);

WebappClassLoaderInfo.setLoader(getClass().getClassLoader());

final String TAB = "TAB_COMMAND";

String command = (String) request.getParameter(GLOBALS.ADMINCOMMAND);
String tabCommand = (String) request.getParameter(TAB);

String storeName = (String) session.getAttribute(StoreFrontData.NAME);
String category = (String) session.getAttribute(CategoryData.NAME);

final String CUSTOMDIR = "custom/";
final String GENERICDIR = "generic/";

final String ADMINXSLPATH = GENERICDIR + "admin/";

final String WORKFLOWPATH = ADMINXSLPATH + "workflow/";

final String WORKFLOWSPATH = ADMINXSLPATH + "workflows/";

final String ORDERXSLPATH = ADMINXSLPATH + "order/";

final String ORDERHISTORYXSLPATH = ORDERXSLPATH + "history/";

final String USERXSLPATH = ADMINXSLPATH + "user/";
final String COMMERCEXSLPATH = USERXSLPATH + "commerce/";
final String PAYMENTXSLPATH = COMMERCEXSLPATH + "payment/";
final String PAYMENTGATEWAYXSLPATH = PAYMENTXSLPATH + "gateway/";

final String PHPLISTING = "custom/phplisting.xsl";
final String JSPLISTING = "custom/jsplisting.xsl";

final String EXISTINGPAYMENTGATEWAYSXSL = PAYMENTGATEWAYXSLPATH + "existingpaymentgateways.xsl";
final String NEWPAYMENTGATEWAYSXSL = PAYMENTGATEWAYXSLPATH + "newpaymentgateways.xsl";
final String PAYMENTGATEWAYSXSL = PAYMENTGATEWAYXSLPATH + "paymentgateway.xsl";

final String INVENTORYXSL = ADMINXSLPATH + "inventoryView.xsl";
final String INVENTORYSEARCHXSL = ADMINXSLPATH + "inventorySearch.xsl";

final String USERSXSL = USERXSLPATH + "usersView.xsl";
final String EDITUSERXSL = USERXSLPATH + "editUserInputForm.xsl";
final String ADDUSERXSL = USERXSLPATH + "addUserInputForm.xsl";

final String CUSTOMERSXSL = USERXSLPATH + "customersView.xsl";
final String EDITCUSTOMERXSL = USERXSLPATH + "editCustomerInputForm.xsl";
final String ADDCUSTOMERXSL = USERXSLPATH + "addCustomerInputForm.xsl";

final String WORKFLOWSXSL = WORKFLOWSPATH + "view.xsl";
final String EDITWORKFLOWXSL = WORKFLOWPATH + "edit.xsl";
final String ADDWORKFLOWXSL = WORKFLOWPATH + "add.xsl";

final String EDITSTOREFRONTXSL = ADMINXSLPATH + "storeFrontInputForm.xsl";
final String BASICITEMINPUTFORMXSL = ADMINXSLPATH + "basicItemInputForm.xsl";

final String ORDERHISTORYREVIEWXSL = ORDERHISTORYXSLPATH + "reviewOrderHistory.xsl";
final String ORDERHISTORYSHIPPINGXSL = ORDERHISTORYXSLPATH + "shippingOrderHistory.xsl";
final String ORDERHISTORYADJUSTXSL = ORDERHISTORYXSLPATH + "adjustOrderHistory.xsl";

final String ORDERREVIEWXSL = ORDERXSLPATH + "orderReview.xsl";
//final String ORDERSHIPPINGXSL = ORDERXSLPATH + "orderShipping.xsl";
//final String ORDERADJUSTXSL = ORDERXSLPATH + "orderAdjust.xsl";
final String ORDERSHIPPINGXSL = ORDERXSLPATH + "orderReview.xsl";
final String ORDERADJUSTXSL = ORDERXSLPATH + "orderReview.xsl";

final String TESTREPORTXSL = ADMINXSLPATH + "/storefront/statistics/basicStoreFrontStatistics.xsl";

//used as a place holder for future view
final String IGNOREXMLXSL = ADMINXSLPATH + "ignoreXmlView.xsl";

final String CUSTOMIZERSVIEWTEMPLATEFILE = ADMINXSLPATH + "customizers/view.xsl";

final String SEARCHVALUEKEY = "searchValue";

final String BACKGROUNDCOLOR = "#FFFFFF";

final String BUTTONOVERCOLOR = "#777777";
final String BUTTONCOLOR = "#0000AA";
final String SIDEBARCOLOR = "#BB00DD";
final String SPECIALCOLOR1 = "#AA00AA";
final String SPECIALCOLOR2 = "#999999";

   final String LOGINPAGE = "login.jsp";
   final String LOGOUTPAGE = "logout.jsp";

   final String PAYMENTERRORPAGE = "paymenterror.jsp";

   final String STOREWIZARDPAGE = "../wizard/start.jsp";

   final String NEWPASSWORDPAGE = "newPassword.jsp";
   final String CHANGEPASSWORDPAGE = "changePassword.jsp";

   final String VIEWCUSTOMERORDERHISTORY = "View Order(s)";   
   final String VIEWALLORDERS = "View All";
   final String VIEWORDERSINRANGE = "View In Range";
   final String VIEWORDERSINLASTHOUR = "Last Hour";
   final String VIEWORDERSINLASTDAY = "Last Day";
   final String VIEWORDERSINLASTWEEK = "Last Week";
   final String VIEWORDERSINLAST30DAYS = "30 Days";

   final String ADD_BILLING = "Add Billing Address";
   final String EDIT_BILLING = "Edit Billing Address";
   final String UPDATE_BILLING = "Update Billing Address";
   final String REMOVE_BILLING = "Remove Billing Address";
   final String VIEW_BILLING = "View Billing Address";
   final String NEW_BILLING = "Add New Billing Address";

   final String ADD_SHIPPING = "Add Shipping Address";   
   final String EDIT_SHIPPING = "Edit Shipping Address";
   final String UPDATE_SHIPPING = "Update Shipping Address";
   final String REMOVE_SHIPPING = "Remove Shipping Address";
   final String VIEW_SHIPPING = "View Shipping Address";
   final String NEW_SHIPPING = "Add New Shipping Address";
   
   //Used By basicUserRoleSelect
   Vector basicUserRoleVector = BasicUserRole.getAll();
   Iterator basicUserRoleIter = null;

   final CommonStrings commonStrings = CommonStrings.getInstance();
%>
<%@ include file="adminPages.jsp" %>
<%@ include file="storeAdminPages.jsp" %>
<%@ include file="generatedPageNames.jsp" %>

<ecommerce:customloader command="<%= GLOBALS.SET %>"
   webappPath="<%= URLGLOBALS.getWebappPath() %>"/>
