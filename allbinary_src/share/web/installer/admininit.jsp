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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>

<HTML>
<HEAD>
<TITLE>Installation - Administrator And Server Configuration Creation - Step 3 of 4</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >
Installation - Administrator And Server Configuration - Step 3 of 4 - <a href="<%= INSTALLATIONHELPPAGE %>" target="<%= targetValue %>" >Help</a><p/>
<%
Vector aroles = new Vector();
aroles.add(BasicUserRole.INSTALLER);

String command = (String) request.getParameter(GLOBALS.ADMINCOMMAND);

if(command!=null && command.compareTo("AdminInit")==0)
{
%>

<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
%>

<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= aroles %>" >
   <jsp:forward page="<%= INSTALLERLOGOUTPAGE %>" />
</ecommerce:fileauthentication>

<ecommerce:fileauthentication 
   command="<%= WeblisketSessionData.INVALIDATESESSION %>" >     
</ecommerce:fileauthentication>

<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
%>

<%
//Add or update the ecommerce configuration - email server configuration
%>
<ecommerce:config isSelected="true" command="<%= GLOBALS.INSERT %>" >

<%
//Add or update the admin user
%>
<admin:user
   command="<%= GLOBALS.INSERT %>" 
   role="<%= BasicUserRole.ADMINISTRATOR.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >

<ecommerce:user command="<%= GLOBALS.INSERT %>" 
   isSelected="true" 
   role="<%= BasicUserRole.ADMINISTRATOR.toString() %>" 
   enable="Yes" >

   <ecommerce:authentication 
      command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
      userName="<%= userName %>" 
      password="<%= password %>" 
      roles="<%= roles %>" >
   <% //EXAMPLESTOREINITPAGE %>
      <jsp:forward page="<%= FINISHPAGE %>" />
   </ecommerce:authentication>

</ecommerce:user>

</admin:user>

<admin:user
   command="<%= GLOBALS.UPDATE %>" 
   role="<%= BasicUserRole.ADMINISTRATOR.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >

<ecommerce:user command="<%= GLOBALS.UPDATE %>" 
   isSelected="true" role="<%= BasicUserRole.ADMINISTRATOR.toString() %>" >

   <ecommerce:authentication 
      command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
      userName="<%= userName %>" 
      password="<%= password %>" 
      roles="<%= roles %>" >
   <% //EXAMPLESTOREINITPAGE %>
      <jsp:forward page="<%= FINISHPAGE %>" />
   </ecommerce:authentication>

</ecommerce:user>

</admin:user>

</ecommerce:config>
<% 
}
else
{
%>

<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= aroles %>" >
   <jsp:forward page="<%= INSTALLERLOGOUTPAGE %>" />
</ecommerce:fileauthentication>

<% 
}
%>

<%@ include file="admininitform.jsp" %>

<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>
