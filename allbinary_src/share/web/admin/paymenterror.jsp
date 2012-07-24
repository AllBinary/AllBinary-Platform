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
<TITLE>Payment Processing Error Page</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<BODY COLOR=#ffffff>
<%
   String PAYMENTTESTRESULTSPAGE = "paymenttesterresults.jsp";
   Vector roles = new Vector();
   roles.add(BasicUserRole.CUSTOMER);   
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="logoutpaymenttester.jsp" />
</ecommerce:authentication>

<p>
Payment Processing Error Page
</p>
<p>
Payment processing failed due to an error.
</p>
<a href="logoutpaymenttester.jsp">Test Logout</a>

<%@ include file="copyright.jsp" %>
</BODY>
</HTML>