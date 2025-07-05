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
<%@ taglib
       uri="/WEB-INF/admin.tld"
       prefix="admin"
%>

<HTML>
<HEAD>
<TITLE>Installation - Administrator User Creation - Step 7 of 9</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >
Installation - Add Admin User - Step 5 of 9<p>

<%
Vector aroles = new Vector();
aroles.add(BasicUserRole.INSTALLER);
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

<admin:user 
   command="<%= GLOBALS.INSERT %>" 
   role="<%= BasicUserRole.ADMINISTRATOR %>"
   xsl="<%= IGNOREXMLXSL %>" >

<ecommerce:user command="<%= GLOBALS.INSERT %>" 
   isSelected="true" role="<%= BasicUserRole.ADMINISTRATOR %>" >

   <ecommerce:authentication 
      command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
      userName="<%= userName %>" 
      password="<%= password %>" 
      roles="<%= roles %>" >
   <% //EXAMPLESTOREINITPAGE %>
      <jsp:forward page="<%= FINISHPAGE %>" />
   </ecommerce:authentication>

</ecommerce:user>

<ecommerce:user command="<%= GLOBALS.UPDATE %>" 
   isSelected="true" role="<%= BasicUserRole.ADMINISTRATOR %>" >

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

<p />
<%@ include file="adminuserinitform.jsp" %>

<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>
