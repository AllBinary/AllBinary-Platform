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
<TITLE>New Password - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBarBlank.jsp" %>
<%@ include file="adminTopBar2Blank.jsp" %>

<div class="mainHeading">
<p>New Password - <a href="<%= ADMINFREEBLISKETONLINEHELP + NEWPASSWORDPAGE %>">Help</a></p>
<div class="main">

<%
String NEWPASSWORD = "Send";

Vector roles = new Vector();
roles.add(BasicUserRole.getAll());
%>

<%
   if(command != null)
   {
      if(command.compareTo(NEWPASSWORD)==0)
      {
%>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.NEWPASSWORD %>" 
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

<form method="POST" action="<%= NEWPASSWORDPAGE %>">
<table class="main" >
<tr>
<td>User Name:</td>
<td><input class="text" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30"></td>
</tr>
<tr>
<td>Email:</td>
<td><input class="text" type="text" name="<%= UserData.MAINEMAIL %>" size="30"></td>
</tr>
</table>
<p>
<input class="submit" type="submit" value="<%= NEWPASSWORD %>" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
