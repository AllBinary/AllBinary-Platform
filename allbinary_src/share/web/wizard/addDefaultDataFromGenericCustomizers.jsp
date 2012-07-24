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
<TITLE>Store Wizard - Try Again</TITLE>
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
Try Again<p />
<div class="wizardmain" >

<workflow:basic name="<%= storeName + commonStrings.SPACE + PAGETEMPLATECUSTOMIZERVIEWNAME %>" >
   <workflow:basic name="<%= storeName + commonStrings.SPACE + GLOBALS.INSERT + commonStrings.SPACE + GLOBALSPAGE + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>" >
      <jsp:forward page="<%= GENERATEPAGESPAGE %>" />
   </workflow:basic>   
</workflow:basic>
   
<%= ERRORMESSAGE %>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
