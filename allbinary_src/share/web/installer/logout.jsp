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