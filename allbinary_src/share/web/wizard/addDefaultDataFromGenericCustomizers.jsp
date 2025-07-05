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
