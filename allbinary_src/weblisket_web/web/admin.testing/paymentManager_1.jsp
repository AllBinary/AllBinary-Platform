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
<TITLE>Payment Managment - Store Admin</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%   
   Vector roles = new Vector();
   roles.add(BasicUserRole.ADMINISTRATOR);
   roles.add(BasicUserRole.STOREMANAGER);
   roles.add(BasicUserRole.PAYMENTMANAGER);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Payment Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + PAYMENTMANAGERPAGE %>">Help</a></p>
<div class="main">

<%   
   final String VIEWORDERHISTORY = "View Order History";
   String id = request.getParameter(OrderData.ID);

   if(command!=null && storeName!=null)
   {
      if(command.compareTo(VIEWORDERHISTORY)==0)
      {
%>
<ecommerce:orderhistory isSelected="true" command="<%= command %>" />
<form method="POST" 
   action="<%= PAYMENTMANAGERPAGE %>">
Get Order Details:<p>
Order Id: <input type="text" name="<%= OrderData.ID %>" size="30"><p>
<input class="submit" type="submit" value="<%= GLOBALS.VIEW %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" ></p>

<%
   }
   else
   if(command.compareTo(GLOBALS.VIEW)==0)
   {
%>
Order: <p>
<ecommerce:orderhistory isSelected="true" command="<%= command %>" />
<ecommerce:orderitems isSelected="true" command="<%= command %>" />
<p>
Process/Stop Payment For Delayed Credit Card Payment:<p>
<ecommerce:order command="<%= command %>" />

<form method="POST" action="<%= PAYMENTMANAGERPAGE %>" >
Order Id: <input type="text" name="<%= OrderData.ID %>" size="30" value="<%= id %>"><br>
Amount: <input type="text" name="<%= OrderHistoryData.TOTAL %>" size="30" 
   value="<ecommerce:order command="<%= GLOBALS.AMOUNTVALUE %>" />">
<p>
<input class="submit" type="submit" value="<%= GLOBALS.PAYORDERANDEVALBODY %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"></p>
<input class="submit" type="submit" value="<%= GLOBALS.AUTHORIZEORDERANDEVALBODY %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"></p>
<input class="submit" type="submit" value="<%= GLOBALS.INQUIRYORDERANDEVALBODY %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"></p>
<input class="submit" type="submit" value="<%= GLOBALS.VOIDORDERANDEVALBODY %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"></p>
</form>
<%
   }
   else
   if(command.compareTo(GLOBALS.PAYORDERANDEVALBODY)==0 ||
      command.compareTo(GLOBALS.VOIDORDERANDEVALBODY)==0)
   {
%>
Payment Result: 
<ecommerce:order command="<%= command %>" />
<%
   }
}
%>
<p>
<form method="POST" action="<%= PAYMENTMANAGERPAGE %>">
<INPUT type="CHECKBOX" value="<%= OrderHistoryData.SHIPPED %>" 
   name="<%= OrderHistoryData.SHIPPED %>" >Shipped<br>
<INPUT type="CHECKBOX" value="<%= OrderHistoryData.PARTIALLYSHIPPED %>" 
   name="<%= OrderHistoryData.PARTIALLYSHIPPED %>" >Partially<br>
<INPUT type="CHECKBOX" value="<%= OrderHistoryData.PROCESSING %>" 
   name="<%= OrderHistoryData.PROCESSING %>" >Processing<br>
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTHOUR %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTDAY %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= VIEWORDERSINLASTWEEK %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>