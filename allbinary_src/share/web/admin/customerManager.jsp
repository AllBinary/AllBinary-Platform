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
<TITLE>Customer Management - Store Admin</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.CUSTOMERMANAGER);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Customer Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + CUSTOMERMANAGERPAGE %>">Help</a></p>
<div class="main">

<%
   String ADDCUSTOMER = "Add";
   String ADDNEWCUSTOMER = "Add New";
   String REMOVECUSTOMER = "Remove";
   String VIEWALL = "View All";
   String EDITCUSTOMER = "Edit";
   String UPDATECUSTOMER = "Update";
   String SEARCH = "Search";

   if(!StringValidationUtil.isEmpty(command) && 
      !StringValidationUtil.isEmpty(storeName))
   {
      String role = request.getParameter(UserRoleData.NAME); 

      if(command.compareTo(ADDCUSTOMER)==0)
      {
%>
<generic:user 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:user isSelected="true" 
   command="<%= GLOBALS.INSERT %>" role="<%= role %>" />
</generic:user>
<p>
<%
      }
      else
      if(command.compareTo(REMOVECUSTOMER)==0)
      {
%>
<generic:user 
   command="<%= GLOBALS.DELETE %>" 
   storeName="<%= storeName %>" 
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:user isSelected="true" 
   command="<%= GLOBALS.DELETE %>" />
</generic:user>
<p>
<%
      }
      else
      if(command.compareTo(VIEWALL)==0)
      {
%>
<generic:users command="<%= GLOBALS.VIEW %>"
   storeName="<%= storeName %>"
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= CUSTOMERSXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(EDITCUSTOMER)==0)
      {
%>
<generic:user 
   command="<%= GLOBALS.EDIT %>"
   storeName="<%= storeName %>"  
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= EDITCUSTOMERXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(ADDNEWCUSTOMER)==0)
      {
%>

<generic:user 
   command="<%= GLOBALS.NEW %>"
   storeName="<%= storeName %>" 
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= ADDCUSTOMERXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(UPDATECUSTOMER)==0)
      {
%>
<generic:user 
   command="<%= GLOBALS.UPDATE %>" 
   storeName="<%= storeName %>" 
   role="<%= BasicUserRole.CUSTOMER.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:user isSelected="true" command="<%= GLOBALS.UPDATE %>" 
   role="<%= role %>" />
</generic:user>
<p>
<%
      }
      else
      if(command.compareTo(UPDATE_BILLING)==0)
      {
%>
<ecommerce:billingaddress isSelected="true" command="<%= GLOBALS.UPDATE %>" />
<p>
<%
      }
      else
      if(command.compareTo(UPDATE_SHIPPING)==0)
      {
%>
<ecommerce:shippingaddress isSelected="true" command="<%= GLOBALS.UPDATE %>" />
<p/>
<%
      }
   }
//GLOBALS.ENCRYPTION TIMECREATED LASTMODIFIED used internally
%>

<form method="POST" action="<%= CUSTOMERMANAGERPAGE %>" >

<input type="hidden" value="<%= ADDNEWCUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="Add" />

</form>
<br/>

<form method="POST" action="<%= CUSTOMERMANAGERPAGE %>">
User Name: <input class="text" type="text" name="<%= UserData.USERNAME %>" value="" />
<input type="hidden" value="<%= EDITCUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Edit" />
</form>

<br /><br />

Warning: Do not use the remove command if you want to disable a customer temporarily.<br /><br />
<form method="POST" action="<%= CUSTOMERMANAGERPAGE %>">
User Name: <input class="text" type="text" name="<%= UserData.USERNAME %>" value="" />
<input type="hidden" value="<%= REMOVECUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="Remove" />
</form>

<br />

<form method="POST" action="<%= CUSTOMERMANAGERPAGE %>">
<input type="hidden" value="<%= VIEWALL %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<input class="submit" type="submit" value="View All" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>