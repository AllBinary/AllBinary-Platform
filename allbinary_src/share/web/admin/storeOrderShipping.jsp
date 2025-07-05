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
<TITLE>Shipping - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>

<BODY COLOR=#ffffff>
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.SHIPPING);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Order Shipping - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + StoreAdminPageData.SHIPPING %>">Help</a></p>
<div class="main">

<%
String id = request.getParameter(OrderData.ID);
final String SETSTATUS = "Set Status";

   if(command!=null && storeName!=null)
   {
         //command.compareTo(VIEWORDERSINRANGE)==0
         //not implemented well command.compareTo(VIEWALLORDERS)==0

      if(command.compareTo(VIEWORDERSINLASTHOUR)==0 ||
         command.compareTo(VIEWORDERSINLASTDAY)==0 ||
         command.compareTo(VIEWORDERSINLASTWEEK)==0 ||
         command.compareTo(VIEWORDERSINLAST30DAYS)==0 ||
         command.compareTo(VIEWORDERSINRANGE)==0 ||
         command.compareTo(VIEWALLORDERS)==0)
      {
%>
<admin:daterangeorderhistory command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERHISTORYSHIPPINGXSL %>" />
<%
      }
      else
      if(command.compareTo(VIEWCUSTOMERORDERHISTORY)==0)
      {
%>
<admin:usernameorderhistory command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERHISTORYSHIPPINGXSL %>" />
<%
      }   
   else
   if(command.compareTo(OrderHistoryData.SETSTATUS)==0)
   {
%>
<ecommerce:orderitems isSelected="true" command="<%= command %>" />
<p>
<ecommerce:orderhistory isSelected="true" command="<%= command %>" />
<p>
<%
   }
   else
   if(command.compareTo(GLOBALS.VIEW)==0)
   {
%>
Change Order Status:<p/>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>" >

<generic:order command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERSHIPPINGXSL %>" />

Set Order Status:<p/>
Status: <select class="text" name="<%= OrderHistoryData.STATUS %>" >
<OPTION><%= OrderHistoryData.SHIPPED %></OPTION>
<OPTION><%= OrderHistoryData.PROCESSING %></OPTION>
</select>
<p/>
<input class="submit" type="submit" value="<%= SETSTATUS %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<p/>
</form>

<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
Set Order Status:<p/>
<input type="hidden" name="<%= OrderData.ID %>" size="30" value="<%= id %>"><p/>
Item Group #: <input class="text" type="text" name="<%= ShippingMethodData.GROUP %>" size="30" value="0"><p/>
Status: <select class="text" name="<%= OrderHistoryData.STATUS %>" >
<OPTION><%= OrderHistoryData.SHIPPED %></OPTION>
<OPTION><%= OrderHistoryData.PROCESSING %></OPTION>
</select>
<p/>
<input class="submit" type="submit" value="<%= OrderHistoryData.SETSTATUS %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" >
</form>
<%
   }
}
%>
<p/>

Search By Order:<p/>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
Order Id: <input class="text" type="text" name="<%= OrderData.ID %>" size="30">
<input class="submit" type="submit" value="<%= GLOBALS.VIEW %>" name="<%= GLOBALS.ADMINCOMMAND %>" >
</form>

<br />

Search By Customer:<p/>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
User Name: <input class="text" type="text" name="<%= UserData.USERNAME %>" size="30">
<input class="submit" type="submit" value="<%= VIEWCUSTOMERORDERHISTORY %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<br />
Basic Date Search:<p/>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PREPROCESSINGNAME %>" >Preprocessing
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PROCESSINGNAME %>" >Processing
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.SHIPPEDNAME %>" >Shipped
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PARTIALLYSHIPPEDNAME %>" >Partially
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.CANCELLEDNAME %>" >Cancelled
<input type="hidden" value="<%= OrderHistoryData.TYPELONG %>" name="<%= OrderHistoryData.DATETYPE %>" >

<br />

<input class="submit" type="submit" value="<%= VIEWORDERSINLASTHOUR %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="<%= VIEWORDERSINLASTDAY %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="<%= VIEWORDERSINLASTWEEK %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="<%= VIEWORDERSINLAST30DAYS %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="<%= VIEWALLORDERS %>" name="<%= GLOBALS.ADMINCOMMAND %>">

</form>

<br />
Specific Date Search:<p/>
<form method="POST" action="<%= StoreAdminPageData.SHIPPING %>">
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PREPROCESSINGNAME %>" >Preprocessing
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PROCESSINGNAME %>" >Processing
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.SHIPPEDNAME %>" >Shipped
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.PARTIALLYSHIPPEDNAME %>" >Partially
<INPUT type="CHECKBOX" name="<%= OrderHistoryData.CANCELLEDNAME %>" >Cancelled
<br />

<jsp:include page="dateRangeTable.jsp" />

<input type="hidden" value="<%= OrderHistoryData.TYPECAESAR %>" name="<%= OrderHistoryData.DATETYPE %>" >

<br>
<input class="submit" type="submit" value="<%= VIEWORDERSINRANGE %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>