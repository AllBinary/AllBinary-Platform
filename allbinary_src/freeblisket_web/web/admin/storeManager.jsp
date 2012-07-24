<%
/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>
<HTML>
<HEAD>
<TITLE>Welcome Store Manager</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
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
<p>Welcome <%= storeName %> Store Manager</p>
<div class="main">
Important First Time Store Manager Info: <p/>

Note: Many pages have a help link after the heading for specific information about that page.<p/>

Even though your website is online you should still do the following if you have not done so before:<p/>

1.  Add Products - Click on Products and then click on Add.<p/>

2.  Set Meta Data - Click on Customize and select the Wizards Tab<p/>

3.  Set Popular Categories - Click on Customize and select the Wizards Tab<p/>

4.  Setup Payment Gateway - Click on Store Admin and select the Payment Gateway Tab<p/>

5.  Generate Static Product Pages - Click on Store Admin and click on the Generators Tab<p/>

Warning: If you make product modifications you need to regenerate static pages.  If you don't your changes will only show up on dynamic pages.  In newer versions this will no longer be required.<p/>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
