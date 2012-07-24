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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>

<HTML>
<HEAD>
<TITLE>Installation Logout Page</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>

<ecommerce:authentication 
   command="<%= WeblisketSessionData.INVALIDATESESSION %>" >
<jsp:forward page="<%= STARTPAGE %>" />
</ecommerce:authentication>

<div class="text" >
Installation Logout Page

<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>