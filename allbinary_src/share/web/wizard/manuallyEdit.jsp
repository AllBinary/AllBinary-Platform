<%
/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
<%@ include file="globals.jsp" %>
<TITLE>Store Wizard - Finished</TITLE>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" >
<%@ include file="header.jsp" %>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="wizardmainHeading" >
Manually Edit<P/>
<div class="wizardmain" >
If you have telnet or ftp access to your website you may edit the generated files manually.  
Although, generating files within the Admin or Wizard Web Inferfaces can overwrite manually modified files.  
Further information is available in the help section of our Store Manager Web Interface.<p/>

<a href="../admin/login.jsp" >Goto Store Manager Web Interface</a><p/>

To view your site <a href="../<%= storeName %>/index.jsp">Goto Your Store</a><P></P>

<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>