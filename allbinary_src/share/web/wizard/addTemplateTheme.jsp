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

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<TITLE>Store Wizard - Theme Selection - Step 4 of <%= TOTALSTEPS %></TITLE>
<%@ include file="javascript.jsp" %>

<script language="JavaScript">

<%@ include file="/javascript/log/javascriptLog.jsp" %>

<%@ include file="/javascript/widgets/tabs/javascriptTabs.jsp" %>

<%@ include file="/javascript/helpers/image/ImageSrcAttributeHashMapPreload.jsp" %>

</script>

<%@ include file="css.jsp" %>

</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" onload="initTabs('addTemplateTheme',1,1);" >

<%@ include file="header.jsp" %>

<div class="wizardmainHeading" >
Theme Selection - Step 4 of <%= TOTALSTEPS %><p/>
<div class="wizardmain" >

<%@ include file="addTemplateThemeProcessing.jsp" %>

<a href="#target1" onclick="selectTab('addTemplateTheme',1);" id="addTemplateThemeLink1" class="wizardtablink" >Themes</a>
<div id="addTemplateThemeTabbedPane1" class="wizardtab" >
<br/>
<div class="wizardheading" >Notes:</div>
<br/>
Processing for this step usually takes 1-5 minutes.<br /><br/>

<form method="POST" action="addTemplateTheme.jsp" >

<workflow:basic 
   name="<%= storeName + commonStrings.SPACE + GLOBALS.EDIT + selectedView %>" />

</form>
</div>

<p />
<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
