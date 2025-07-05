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
<TITLE>Blog Management - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
   Vector roles = new Vector();
   roles.add(BasicUserRole.ADMINISTRATOR);
   roles.add(BasicUserRole.STOREMANAGER);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Blog Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + BLOGSPAGE %>">Help</a></p>
<div class="main">
<%= WEBLISKETFEATURE %>
<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>