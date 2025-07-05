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
<TITLE>Store Wizard - Generate Pages - Step 2 of <%= TOTALSTEPS %></TITLE>
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
//Generate template files included by others
%>
<transform:generic
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>" >
</transform:generic>

<jsp:forward page="<%= FINISHPAGE %>" />

<% 
final String PAGECOMMAND = "GENERATEPAGE_COMMAND";
if(command!=null && command.compareTo(PAGECOMMAND)==0)
{
   //Add base templates to db from files for store
   //Set Selected Template As Parent of other templates
%>

<jsp:forward page="<%= FREEBLISKET_PATH_GLOBALS.getInstance().ERRORPAGE %>" />

<% 
}
%>

Generate Pages - <p></p>
<form method="POST" action="<%= GENERATEPAGESPAGE %>" >
<input class="wizardsubmit" type="hidden" value="<%= PAGECOMMAND %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="wizardsubmit" type="submit" value="Continue" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<p />
<%@ include file="copyright.jsp" %>
</BODY>
</HTML>