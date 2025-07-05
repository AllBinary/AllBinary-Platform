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