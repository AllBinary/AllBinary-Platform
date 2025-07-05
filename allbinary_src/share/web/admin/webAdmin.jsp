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
<TITLE>Web Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> onload="initTabs('webAdmin',1,5)" >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.WEBMANAGER);
roles.add(BasicUserRole.WORKFLOWEDITOR);
//roles.add(BasicUserRole.WIZARDEDITOR);
roles.add(BasicUserRole.VIEWEDITOR);

final String TEMPLATESTYLEPAGE = "../wizard/selectTemplateStyle.jsp";
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Web Admin - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + WEBADMINPAGE %>">Help</a></p>
<div class="main">

<%@ include file="webAdminProcessing.jsp" %>

<a href="#target1" onclick="selectTab('webAdmin',1)" id="webAdminLink1" class="tablink" >Customizers</a>
<a href="#target2" onclick="selectTab('webAdmin',2)" id="webAdminLink2" class="tablink" >Generators</a>
<a href="#target3" onclick="selectTab('webAdmin',3)" id="webAdminLink3" class="tablink" >Workflows</a>
<a href="#target4" onclick="selectTab('webAdmin',4)" id="webAdminLink4" class="tablink" >Wizards</a>
<a href="#target5" onclick="selectTab('webAdmin',5)" id="webAdminLink5" class="tablink" >Views</a>
<a href="#target6" onclick="selectTab('webAdmin',6)" id="webAdminLink6" class="tablink" >Other</a>

<%@ include file="webAdminCustomizersTab.jsp" %>

<div id="webAdminTabbedPane2" class="tab" >

<%@ include file="webAdminGeneratorTab.jsp" %>

</div>

<div id="webAdminTabbedPane3" class="tab" >

<%= WEBLISKETFEATURE %>

</div>

<div id="webAdminTabbedPane4" class="tab" >
You can change your template with the following form wizard(s):<p/>

<%= WEBLISKETFEATURE %>

</div>

<div id="webAdminTabbedPane5" class="tab" >

<%= WEBLISKETFEATURE %>

</div>

<%@ include file="webAdminOtherTab.jsp" %>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>