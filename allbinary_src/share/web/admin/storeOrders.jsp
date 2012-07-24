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
<HTML>
<HEAD>
<TITLE>Order Processing - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);   
   Vector roles = new Vector();
   roles.add(BasicUserRole.ADMINISTRATOR);
   roles.add(BasicUserRole.STOREMANAGER);
   //roles.add(BasicUserRole.ORDERMANAGER);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>


<div class="mainHeading">
<p><%= storeName %> - Order Processing - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + ORDERSPAGE %>">Help</a></p>
<div class="main">
<table class="main" cellspacing="2"  cellpadding="2">

<tr><td>
<A class="cloak" HREF="<%= StoreAdminPageData.SHIPPING %>"  >
<div ID="shipping" class="menuItems"
   onMouseOver="changeBackgroundColor('shipping','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('shipping','<%= BUTTONCOLOR %>');return true;" >
Shipping
</div></A></td></tr>

<tr><td>
<A class="cloak" HREF="<%= StoreAdminPageData.ADJUSTMENT %>"  >
<div ID="adjustments" class="menuItems"
   onMouseOver="changeBackgroundColor('adjustments','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('adjustments','<%= BUTTONCOLOR %>');return true;" >
Adjustments
</div></A></td></tr>

<tr><td>
<A class="cloak" HREF="<%= StoreAdminPageData.REVIEW %>"  >
<div ID="review" class="menuItems"
   onMouseOver="changeBackgroundColor('review','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('review','<%= BUTTONCOLOR %>');return true;" >
Review
</div></A></td></tr>

</table>
<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>