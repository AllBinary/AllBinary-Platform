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
<TITLE>Store Wizard - Finished</TITLE>
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
Finished<P/>
<div class="wizardmain" >
Congratulations you have completed your store installation.<p/>

To make further modifications:<p/>
<a target="_blank" href="../admin/login.jsp" >Goto Store Manager Web Interface</a><p/>
and/or<p/>
<a href="manuallyEdit.jsp">Edit the generated files manually.</a><p/>

To view your site <a target="_blank" href="../<%= storeName %>/index.jsp">Goto Your Store</a><P></P>

Tips: <p />

If you have a site with an existing store the administrator can import the data into the new store for you.<p />

If you have an existing website without a store you can link it to the Retail store.<p />
<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>