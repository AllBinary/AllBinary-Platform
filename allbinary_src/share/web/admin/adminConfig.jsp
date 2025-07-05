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
<TITLE>Configuration - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBar.jsp" %>
<%@ include file="adminTopBar2.jsp" %>
<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
   Vector roles = new Vector();
   roles.add(BasicUserRole.ADMINISTRATOR);
   roles.add(BasicUserRole.STOREMANAGER);
   roles.add(BasicUserRole.CUSTOMERMANAGER);
   roles.add(BasicUserRole.ADJUSTER);
   roles.add(BasicUserRole.PRODUCTMANAGER);
   roles.add(BasicUserRole.REVIEWER);
   roles.add(BasicUserRole.SHIPPING);
   //roles.add(BasicUserRole.ORDERMANAGER);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p>Configuration - <a href="<%= ADMINFREEBLISKETONLINEHELP + AdminPageData.CONFIG %>">Help</a></p>
<div class="main">

<%
   final String PRODUCTPAGES = "Product Pages";
   if(command!=null)
   {
      if(command.compareTo(PRODUCTPAGES)==0)
      {
%>
<ecommerce:staticpages isSelected="true" command="<%= SearchData.GENERATESTATICPAGES %>"
   xsl="<%= PHPLISTING %>" />
<P></P>
<%
      }
   }
%>

<a href="#target1" onclick="selectTab('storeConfig',1)" id="storeConfigLink1" class="tablink" >Generators</a>

<div id="storeConfigTabbedPane1" class="tab" >
Warning: The generator is process hungry and can over write existing static pages.<p />

Generate static pages for all stores.  It is best to generate product pages for the following reasons:<p />

Special Customization - Make special edits to product pages<br />
Search Engine Indexing - Allow search engines to index pages<br />
Performance - Reduce request time by not generating pages dynamically<br />
<p />
<form method="POST" action="<%= AdminPageData.CONFIG %>">
<input type="hidden" value="<%= storeName %>" 
   name="<%= StoreFrontData.NAME %>" />

<input type="hidden" name="<%= SearchData.PAGE %>" value="1000" />
<input type="hidden" name="<%= SearchData.FIELD %>" value="<%= BasicItemData.SUMMARY %>" />
<input type="hidden" name="<%= SearchData.SORTBY %>" value="AlphaNumeric" />
<input type="hidden" name="<%= SearchData.LENGTH %>" value="10" />
<input type="hidden" name="<%= SearchData.ORDER %>" value="Ascending" />

<input class="submit" type="submit" value="<%= PRODUCTPAGES %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>
</div>

<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>