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
<HTML>
<HEAD>
<TITLE>Table Management - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBar.jsp" %>
<%@ include file="adminTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>
<div class="mainHeading">
<p>Database Management - Experts Only -  - <a href="<%= ADMINFREEBLISKETONLINEHELP + AdminPageData.TABLES %>">Help</a></p>
<div class="main">
Warning: Database Experts Only<br><br>
<%
//View Info Template - Template names
   final String BACKUPSELECTEDTABLES = "Export";
   final String RESTORESELECTEDTABLES = "Import";

   String isCustomer = request.getParameter("customers");
   String isBillingAddress = request.getParameter("billingaddress");
   String isShippingAddress = request.getParameter("shippingaddress");
   String isPayment = request.getParameter("payment");

   String isQuoteRequest = request.getParameter("quoterequest");

   String isOrderHistory = request.getParameter("orderhistory");
   String isOrderItems = request.getParameter("orderitems");
   String isTransaction = request.getParameter("transaction");
   String isTransactionResult = request.getParameter("transactionresult");

   String isStoreFronts = request.getParameter("storefronts");
   String isPaymentGateway = request.getParameter("gateways");

   String isCategory = request.getParameter("category");
   String isInventory = request.getParameter("inventory");
   String isCustomItems = request.getParameter("customitems");
   String isDownloadItems = request.getParameter("downloaditems");
   String isGroupItems = request.getParameter("groupitems");
   String isBasicOptionItems = request.getParameter("basicoptionitems");
   String isXmlOptionItems = request.getParameter("xmloptionitems");
   String isPermissionItems = request.getParameter("permissionitems");
   String isSpecialItems = request.getParameter("specialitems");

   String isAdvertisements = request.getParameter("advertisements");
   String isAdvertisementCampaignsInternal = request.getParameter("advertisementCampaignsInternal");
   String isAdvertisementCampaignsExternal = request.getParameter("advertisementCampaignsExternal");
   String isAffiliates = request.getParameter("affiliates");
   String isAffiliateSales = request.getParameter("affiliateSales");

   String isInit = request.getParameter("initdata");
//   String isAdmin = request.getParameter("admindata");
   String isLog = request.getParameter("log");
   String isStaticPages = request.getParameter("staticpages");
   String isViewInfo = request.getParameter("viewinfo");

   String isWorkFlows = request.getParameter("workflows");

   if(command!=null)
   {
      boolean tableSelected=false;
      String sqlStatement = null;     
       if(command.compareTo(GLOBALS.RESTORE +  " All")==0 || 
          command.compareTo(RESTORESELECTEDTABLES)==0)
      {
         command = GLOBALS.RESTORE;
      }
      else
       if(command.compareTo(GLOBALS.BACKUP +  " All")==0 || 
          command.compareTo(BACKUPSELECTEDTABLES)==0)
      {
         command = GLOBALS.BACKUP;
      }
      else
      if(command.compareTo("View")==0 || command.compareTo("View All")==0)
      {
         command = GLOBALS.VIEW;
      }
      else
      if(command.compareTo("Drop")==0 || command.compareTo("Drop All")==0)
      {
         command = GLOBALS.DROP;
      }
      else
      if(command.compareTo("Create")==0  || command.compareTo("Create All")==0)
      {
         command = GLOBALS.CREATE;
      }
%>
<ecommerce:workflows isSelected="<%= isWorkFlows %>" command="<%= command %>"/>
<ecommerce:staticpages isSelected="<%= isStaticPages %>" command="<%= command %>"/>
<transform:table isSelected="<%= isViewInfo %>" command="<%= command %>" />

<ecommerce:init isSelected="<%= isInit %>" command="<%= command %>" />

<ecommerce:log  isSelected="<%= isLog %>" command="<%= command %>"/>

<ecommerce:storefronts isSelected="<%= isStoreFronts %>" command="<%= command %>"/>

<ecommerce:user isSelected="<%= isCustomer %>" command="<%= command %>" />
<ecommerce:billingaddress isSelected="<%= isBillingAddress %>" command="<%= command %>" />
<ecommerce:shippingaddress isSelected="<%= isShippingAddress %>" command="<%= command %>" />
<payment:payment isSelected="<%= isPayment %>" command="<%= command %>" />
<ecommerce:quoterequest isSelected="<%= isQuoteRequest %>" command="<%= command %>"/>

<ecommerce:orderhistory  isSelected="<%= isOrderHistory %>" command="<%= command %>"/>
<ecommerce:orderitems  isSelected="<%= isOrderItems %>" command="<%= command %>"/>

<payment:gateway isSelected="<%= isPaymentGateway %>" command="<%= command %>"/>
<payment:transaction  isSelected="<%= isTransaction %>" command="<%= command %>"/>
<payment:transactionres isSelected="<%= isTransactionResult %>" command="<%= command %>"/>

<ecommerce:category  isSelected="<%= isCategory %>" command="<%= command %>"/>
<ecommerce:inventory  isSelected="<%= isInventory %>" command="<%= command %>"/>
<ecommerce:customitems isSelected="<%= isCustomItems %>" command="<%= command %>"/>
<ecommerce:downloaditems isSelected="<%= isDownloadItems %>" command="<%= command %>"/>
<ecommerce:basicgroupitems isSelected="<%= isGroupItems %>" command="<%= command %>"/>
<ecommerce:basicoptionitems isSelected="<%= isBasicOptionItems %>" command="<%= command %>"/>
<ecommerce:xmloptionitems isSelected="<%= isXmlOptionItems %>" command="<%= command %>"/>
<ecommerce:permissionitems isSelected="<%= isPermissionItems %>" command="<%= command %>"/>
<ecommerce:specialitems isSelected="<%= isSpecialItems %>" command="<%= command %>"/>

<%
         if(isCustomer==null && isBillingAddress==null && 
            isShippingAddress==null && isPayment==null &&
            isQuoteRequest==null &&
            isOrderHistory==null && isOrderItems==null &&
            isTransaction==null && isTransactionResult==null &&
            isStoreFronts==null && isPaymentGateway==null &&
            isCategory==null && isInventory==null &&
            isCustomItems==null && isDownloadItems==null &&
            isGroupItems==null && isBasicOptionItems==null &&
            isXmlOptionItems==null && isPermissionItems==null &&
            isSpecialItems==null && 
            isInit==null && 
            isLog==null && 
            isStaticPages==null &&
            isViewInfo==null &&
            isWorkFlows==null)
         {
            out.print("Error: No Table Selected");
         }
   }
%>
<form method="POST" action="<%= AdminPageData.TABLES %>">
<br/><br/>
System Data:
<p><input type="checkbox" disabled="true" name="initdata" >Init Data - Initialization data</p>
<p><input type="checkbox" name="workflows" >WorkFlow - Matches a request with a workflow</p>
<p><input type="checkbox" name="staticpages" >Static Pages - Pregenerated listings</p>
<p><input type="checkbox" name="viewinfo" >View Info - Custom view data</p>
<p><input type="checkbox" name="log" >Log - User access records</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>
Store Data:
<p><input type="checkbox" name="storefronts" >Store Fronts - Configuration information</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

Customer Data:
<p><input type="checkbox" name="customers" >Customers - Main customer data</p>
<p><input type="checkbox" name="billingaddress" >Billing Address - Customer billing addresses</p>
<p><input type="checkbox" name="shippingaddress" >Shipping Address - Customer shipping addresses</p>
<p><input type="checkbox" name="payment" >Payment - Customer payment data</p>
<p><input type="checkbox" name="quoterequest" >Quote Request - Customer quote request</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

Inventory Data:
<p><input type="checkbox" name="category" >Category - Inventory categories</p>
<p><input type="checkbox" name="inventory" >Inventory - Basic inventory</p>
<p><input type="checkbox" name="customitems" >Custom Items - Custom items</p>
<p><input type="checkbox" name="downloaditems" >Download Items - Downloadable items</p>
<p><input type="checkbox" name="groupitems" >Group Items - Grouped items</p>
<p><input type="checkbox" name="basicoptionitems" >Basic Option Items - Option items</p>
<p><input type="checkbox" name="xmloptionitems" >Complex Option Items - XML option items</p>
<p><input type="checkbox" name="permissionitems" >Permission Items - Pricing for specific customers</p>
<p><input type="checkbox" name="specialitems" >Specials Items - Timed sales</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

Order History:
<p><input type="checkbox" name="orderhistory" >OrderHistory - Past orders</p>
<p><input type="checkbox" name="orderitems" >OrderItems - Past order items</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

Payment Component Data:
<p><input type="checkbox" name="gateways" >Gateways - Third party payment configuration</p>
<p><input type="checkbox" name="transaction" >Payment Transactions - Third party payment data</p>
<p><input type="checkbox" name="transactionresult" >Payment Transactions Results - Third party payment results</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

Advertisement Component Data:
<p><input type="checkbox" name="advertisements" >Advertisements - Ads for Campaigns &amp; Affiliate Programs</p>
<p><input type="checkbox" name="advertisementCampaignsInternal" >Internal Campaigns - Ads for your stores</p>
<p><input type="checkbox" name="advertisementCampaignsExternal" >External Campaigns - Advertisements with others</p>
<p><input type="checkbox" name="affiliates" >Affiliates - Affiliate Programs for Affiliate Users</p>
<p><input type="checkbox" name="affiliateSales" >Affiliate Sales - Associated with Affiliate Users</p>
<input class="submit" type="submit" value="Drop" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Create" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= BACKUPSELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= RESTORESELECTEDTABLES %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<p/><p/>

</form>

<form method="POST" action="<%= AdminPageData.TABLES %>">

<input type="hidden" value="not" name="initdata" />

<input type="hidden" value="on" name="log" />
<input type="hidden" value="on" name="staticpages" />
<input type="hidden" value="on" name="viewinfo" />

<input type="hidden" value="on" name="storefronts" />

<input type="hidden" value="on" name="customers" />
<input type="hidden" value="on" name="billingaddress" />
<input type="hidden" value="on" name="shippingaddress" />
<input type="hidden" value="on" name="payment" />
<input type="hidden" value="on" name="quoterequest" />

<input type="hidden" value="on" name="workflows" />
<input type="hidden" value="on" name="category" />
<input type="hidden" value="on" name="inventory" />
<input type="hidden" value="on" name="customitems" />
<input type="hidden" value="on" name="downloaditems" />
<input type="hidden" value="on" name="groupitems" />
<input type="hidden" value="on" name="basicoptionitems" />
<input type="hidden" value="on" name="xmloptionitems" />
<input type="hidden" value="on" name="permissionitems" />
<input type="hidden" value="on" name="specialitems" />

<input type="hidden" value="on" name="orderhistory" />
<input type="hidden" value="on" name="orderitems" />

<input type="hidden" value="on" name="gateways" />
<input type="hidden" value="on" name="transaction" />
<input type="hidden" value="on" name="transactionresult" />

<input type="hidden" value="on" name="advertisements" />
<input type="hidden" value="on" name="advertisementCampaignsInternal" />
<input type="hidden" value="on" name="advertisementCampaignsExternal" />
<input type="hidden" value="on" name="affiliates" />
<input type="hidden" value="on" name="affiliatezSales" />

<input class="submit" type="submit" value="Drop All" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="Create All" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="<%= GLOBALS.BACKUP %> All" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="<%= GLOBALS.RESTORE %> All" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
