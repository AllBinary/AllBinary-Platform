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
