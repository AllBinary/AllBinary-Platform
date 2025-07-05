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
<TITLE>User Management - Admin</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
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
<p>User Management - <a href="<%= ADMINFREEBLISKETONLINEHELP + AdminPageData.USERMANAGER %>">Help</a></p>
<div class="main">

<%
   String ADDCUSTOMER = "Add";
   String ADDNEWCUSTOMER = "Add New";
   String REMOVECUSTOMER = "Remove";
   String VIEWALL = "View All";
   String EDITCUSTOMER = "Edit";
   String UPDATECUSTOMER = "Update";
   String SEARCH = "Search";

   if(!StringValidationUtil.isEmpty(command))
   {
      String role = (String) request.getParameter(UserRoleData.NAME);

      if(command.compareTo(ADDCUSTOMER)==0)
      {
%>
<admin:user 
   command="<%= GLOBALS.INSERT %>"
   role="<%= role %>"
   xsl="<%= IGNOREXMLXSL %>" >
   <ecommerce:user isSelected="true" 
      command="<%= GLOBALS.INSERT %>" role="<%= role %>" />
</admin:user>
<p>
<%
      }
      else
      if(command.compareTo(REMOVECUSTOMER)==0)
      {
%>
<admin:user 
   command="<%= GLOBALS.DELETE %>" 
   role="<%= role %>"
   xsl="<%= IGNOREXMLXSL %>" >
   <ecommerce:user isSelected="true" command="<%= GLOBALS.DELETE %>" />
</admin:user>
<p>
<%
      }
      else
      if(command.compareTo(VIEWALL)==0)
      {
%>
<admin:users command="<%= GLOBALS.VIEW %>"
   role="<%= role %>"
   xsl="<%= USERSXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(EDITCUSTOMER)==0)
      {
%>
<admin:user 
   command="<%= GLOBALS.EDIT %>"
   role="<%= role %>"
   xsl="<%= EDITUSERXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(ADDNEWCUSTOMER)==0)
      {
%>

<admin:user 
   command="<%= GLOBALS.NEW %>"
   role="<%= role %>"
   xsl="<%= ADDUSERXSL %>" />
<p>
<%
      }
      else
      if(command.compareTo(UPDATECUSTOMER)==0)
      {
%>
<admin:user 
   command="<%= GLOBALS.UPDATE %>" 
   role="<%= role %>"
   xsl="<%= IGNOREXMLXSL %>" >
   <ecommerce:user isSelected="true" command="<%= GLOBALS.UPDATE %>" 
      role="<%= role %>" />
</admin:user>
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
<p>
<%
      }
   }
 //GLOBALS.ENCRYPTION TIMECREATED LASTMODIFIED used internally
%>

<form method="POST" action="<%= AdminPageData.USERMANAGER %>">
<input type="hidden" value="<%= ADDNEWCUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Add" />
</form>

<form method="POST" action="<%= AdminPageData.USERMANAGER %>">

<table class="table" >
<tr>
<td>
User Name: 
</td>
<td>
<input class="text" type="text" name="<%= UserData.USERNAME %>" value="" />
</td>
</tr>
</table>

<input type="hidden" value="<%= EDITCUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Edit" />
</form>

<br />

Warning: Do not use the remove command if you want to disable a customer temporarily.<br />
<form method="POST" action="<%= AdminPageData.USERMANAGER %>">

<table class="table" >
<tr>
<td>
User Name: 
</td>
<td>
<input class="text" type="text" name="<%= UserData.USERNAME %>" value="" />
</td>
</tr>
</table>

<input type="hidden" value="<%= REMOVECUSTOMER %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Remove" />
</form>

<form method="POST" action="<%= AdminPageData.USERMANAGER %>">

<table class="table" >
<%@ include file="basicUserRoleSelect.jsp" %>
</table>

<input type="hidden" value="<%= VIEWALL %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="View All" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>