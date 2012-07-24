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
<TITLE>Login Page</TITLE>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" >
<ecommerce:authentication 
   command="<%= WeblisketSessionData.INVALIDATESESSION %>" >
<jsp:forward page="<%= LOGINPAGE %>" /> 
</ecommerce:authentication>

<%@ include file="copyright.jsp" %>
</BODY>
</HTML>