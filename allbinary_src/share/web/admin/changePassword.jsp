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
<TITLE>Change Password - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBarBlank.jsp" %>
<%@ include file="adminTopBar2Blank.jsp" %>

<div class="mainHeading">
<p>Change Password - <a href="<%= ADMINFREEBLISKETONLINEHELP + CHANGEPASSWORDPAGE %>">Help</a></p>
<div class="main">

<%
String CHANGEPASSWORD = "Change";

Vector roles = new Vector();
roles.add(BasicUserRole.getAll());
%>

<%
   if(command != null)
   {
      if(command.compareTo(CHANGEPASSWORD)==0)
      {
%>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.CHANGEPASSWORD %>" 
   userName="<%= userName %>" 
   password="<%= password %>" 
   roles="<%= roles %>" >
<%
//Password Change Failed
%>
<br/>
</ecommerce:authentication>

<%
//Forward to login page that will forward to the appropriate page after password is changed
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= LOGINPAGE %>" />
</ecommerce:authentication>

<%
      }
   }
%>

</p>

<form method="POST" action="<%= CHANGEPASSWORDPAGE %>">
<table class="main" >
<tr>
<td>User Name:</td>
<td><input class="text" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30"></td>
</tr>
<tr>
<td>Current Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLEPASSWORD %>" size="30"></td>
</tr>
<tr>
<td>New Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLENEWPASSWORD %>" size="30"></td>
</tr>
<tr>
<td>Reenter New Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLEREENTERNEWPASSWORD %>" size="30"></td>
</tr>
</table>
<p>
<input class="submit" type="submit" value="<%= CHANGEPASSWORD %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
