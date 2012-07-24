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
<TITLE>Installation Finished</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >

<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>
<%
new ProductIdGenerator().initialize(0);
new OrderIdGenerator().initialize(0);
%>
Installation Finished<p/>

We hope to provide for all of your future E-Commerce needs.
<p />

Now you can start the <a href="../wizard/start.jsp" >Store Wizard</a> to create 
a new store or goto the <br /> 
<a href="../admin/login.jsp" >Admin Web Interface</a> if you wish to import/restore an existing store.
<p />
Test Admin Email Results: <admin:basictextemail subject="Admin Test Email" body="Admin Test Email" />
<p />

<%@ include file="copyright.jsp" %>
</div>

</BODY>
</HTML>