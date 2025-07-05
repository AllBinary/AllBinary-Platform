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
<TITLE>Login Page</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBarBlank.jsp" %>
<%@ include file="adminTopBar2Blank.jsp" %>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
   Vector roles = new Vector();
   roles.add(BasicUserRole.ADMINISTRATOR);
   roles.add(BasicUserRole.STOREMANAGER);
   roles.add(BasicUserRole.CUSTOMERMANAGER);
   roles.add(BasicUserRole.ADJUSTER);
   roles.add(BasicUserRole.PRODUCTMANAGER);
   roles.add(BasicUserRole.REVIEWER);
   roles.add(BasicUserRole.SHIPPING);
%>
<ecommerce:authentication 
   command="<%= allbinary.globals.GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   userName="<%= userName %>" 
   password="<%= password %>" 
   roles="<%= roles %>" >
<%
   String role = (String) 
      session.getAttribute(UserRoleData.NAME.toString()).toString();

   if(role != null)
   {      
      if(role.compareTo(BasicUserRole.ADMINISTRATOR.getRole())==0)
      {
        %> <jsp:forward page="<%= AdminPageData.STORES %>" /> <% 
      }
      else
      if(role.compareTo(BasicUserRole.STOREMANAGER.getRole())==0)
      {
        %> <jsp:forward page="<%= STOREMANAGERPAGE %>" /> <% 
      }
      else
      if(role.compareTo(BasicUserRole.REVIEWER.getRole())==0)
      {
         %> <jsp:forward page="<%= StoreAdminPageData.REVIEW %>" /> <%
      }
      else
      if(role.compareTo(BasicUserRole.SHIPPING.getRole())==0)
      {
         %> <jsp:forward page="<%= StoreAdminPageData.SHIPPING %>" /> <%
      }
      else
      if(role.compareTo(BasicUserRole.ADJUSTER.getRole())==0)
      {
         %> <jsp:forward page="<%= StoreAdminPageData.SHIPPING %>" /> <%
      }
      else
      if(role.compareTo(BasicUserRole.PRODUCTMANAGER.getRole())==0)
      {
         %> <jsp:forward page="<%= PRODUCTMANAGERPAGE %>" /> <%
      }
      else
      if(role.compareTo(BasicUserRole.CUSTOMERMANAGER.getRole())==0)
      {
         %> <jsp:forward page="<%= CUSTOMERMANAGERPAGE %>" /> <%
      }
   }
%>   
</ecommerce:authentication>

<div class="mainHeading">
<p>Login Page</p>

<div class="main">
<form method="POST" action="<%= LOGINPAGE %>">
<table class="main" >
<tr>
<td>User Name:</td>
<td><input class="text" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30"></td>
</tr>
<tr>
<td>Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLEPASSWORD %>" size="30"></td>
</tr>
</table>
<p><input class="submit" type="submit" value="Login" name="<%= allbinary.globals.GLOBALS.ADMINCOMMAND %>"></p>
</form>
<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>