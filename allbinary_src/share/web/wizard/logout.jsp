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