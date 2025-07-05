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
<TITLE>Adjuster - Store Admin</TITLE>
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
roles.add(BasicUserRole.ADJUSTER);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Order Adjuster - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + StoreAdminPageData.ADJUSTMENT %>">Help</a></p>
<div class="main">

<%

final String EDITORDER = "Edit Order";

   if(command!=null && storeName!=null)
   {
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
   xsl="<%= ORDERHISTORYADJUSTXSL %>" />
<%
      }
      else
      if(command.compareTo(VIEWCUSTOMERORDERHISTORY)==0)
      {
%>
<admin:usernameorderhistory command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>"
   xsl="<%= ORDERHISTORYADJUSTXSL %>" />
<%
      }
      else
      if(command.compareTo(GLOBALS.VIEW)==0)
      {
%>
Update Order Form: <p/>
<form method="POST" action="<%= StoreAdminPageData.ADJUSTMENT %>">
<generic:order command="<%= GLOBALS.VIEW %>" 
storeName="<%= storeName %>"
   xsl="<%= ORDERADJUSTXSL %>" />
<input class="submit" type="submit" 
   value="<%= GLOBALS.UPDATE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
<%
      }
      else
      if(command.compareTo(GLOBALS.UPDATE)==0)
      {
%>
<ecommerce:updateorder isSelected="true" command="<%= command %>" />
<%
     }
     else
     if(command.compareTo(GLOBALS.UPDATE)==0)
     {
%>
<ecommerce:orderitems isSelected="true" command="<%= command %>" />
<%
     }
%>
<p/>
<%
}
%>

<form method="POST" action="<%= StoreAdminPageData.ADJUSTMENT %>">
Search By Order:<p/>
Order Id: <input class="text" type="text" name="<%= OrderData.ID %>" size="30">
<input class="submit" type="submit" value="<%= EDITORDER %>" name="<%= GLOBALS.ADMINCOMMAND %>" >
</form>

<form method="POST" action="<%= StoreAdminPageData.ADJUSTMENT %>">
Search By Customer:<p/>
User Name: <input class="text" type="text" name="<%= UserData.USERNAME %>" size="30">
<input class="submit" type="submit" value="<%= VIEWCUSTOMERORDERHISTORY %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<form method="POST" action="<%= StoreAdminPageData.ADJUSTMENT %>">
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
<br />
</form>

<form method="POST" action="<%= StoreAdminPageData.ADJUSTMENT %>">
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