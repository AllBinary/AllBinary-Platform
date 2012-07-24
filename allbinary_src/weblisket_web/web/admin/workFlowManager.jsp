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
<%@ taglib
       uri="/WEB-INF/admin.tld"
       prefix="admin"
%>
<%@ taglib
       uri="/WEB-INF/generic.tld"
       prefix="generic"
%>
<HTML>
<HEAD>
<TITLE>WorkFlow Management - Store Admin</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.WORKFLOWEDITOR);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - WorkFlow Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + WORKFLOWMANAGERPAGE %>">Help</a></p>
<div class="main">

<%= WEBLISKETFEATURE %>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>