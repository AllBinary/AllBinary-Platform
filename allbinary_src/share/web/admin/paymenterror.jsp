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