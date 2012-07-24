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
<TITLE>Logging - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBar.jsp" %>
<%@ include file="adminTopBar2.jsp" %>
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
   //roles.add(BasicUserRole.ORDERMANAGER);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Logging - <a href="<%= ADMINFREEBLISKETONLINEHELP + AdminPageData.LOGGING %>">Help</a></p>
<div class="main">
<%= WEBLISKETFEATURE %>
<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>