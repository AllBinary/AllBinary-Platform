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
<TITLE>Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> onload="initTabs('storeAdmin',1,5)" >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
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
<p><%= storeName %> - Store Admin - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + STOREADMINPAGE %>">Help</a></p>
<div class="main">

<%@ include file="storeAdminProcessing.jsp" %>

<a href="#target1" onclick="selectTab('storeAdmin',1)" id="storeAdminLink1" class="tablink" >Import/Export</a>
<a href="#target2" onclick="selectTab('storeAdmin',2)" id="storeAdminLink2" class="tablink" >Generators</a>
<a href="#target3" onclick="selectTab('storeAdmin',3)" id="storeAdminLink3" class="tablink" >Components</a>
<a href="#target4" onclick="selectTab('storeAdmin',4)" id="storeAdminLink4" class="tablink" >Config</a>
<a href="#target5" onclick="selectTab('storeAdmin',5)" id="storeAdminLink5" class="tablink" >Other</a>        

   
<%@ include file="storeAdminImportExportTab.jsp" %>

<%@ include file="storeAdminGeneratorTab.jsp" %>

<%@ include file="storeAdminComponentsTab.jsp" %>

<%@ include file="storeAdminConfigTab.jsp" %>

<%@ include file="storeAdminOtherTab.jsp" %>


<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>