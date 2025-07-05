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
<%@ include file="globals.jsp" %>

<TITLE>Store Wizard - Add Pages Error</TITLE>
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

<%
//Insert the selected template type into the root template objectconfig
String templateCustomizerViewName = 
   storeName + commonStrings.SPACE + 
   GLOBALS.INSERT + commonStrings.SPACE + 
   CustomizerTransformInfoData.NAME;
%>
<workflow:basic 
   name="<%= templateCustomizerViewName %>" >
</workflow:basic>

<%@ include file="insertGenericTemplateViews.jsp" %>
<%@ include file="insertGenericTemplateViewCustomizers.jsp" %>

<%
//insert default customizers that do not require form data
%>
<%@ include file="newAllDefaultCustomizersPage.jsp" %>

<jsp:forward page="<%= ADDHEADERPAGE %>" />

<p />
<%@ include file="copyright.jsp" %>
</BODY>
</HTML>