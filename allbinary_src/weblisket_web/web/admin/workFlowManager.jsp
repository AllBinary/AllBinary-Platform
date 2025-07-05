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