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
<TITLE>Installation - Type - Step 2</TITLE>

<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>

<%
Vector roles = new Vector();
roles.add(BasicUserRole.INSTALLER);
%>

<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= INSTALLERLOGOUTPAGE %>" />
</ecommerce:fileauthentication>

<div class="text" >
<%
final String INSTALL_TYPE = request.getParameter("INSTALL_TYPE");

if(INSTALL_TYPE!=null && INSTALL_TYPE.compareTo("")!=0)
{

if(INSTALL_TYPE.compareTo("Basic")==0)
{
%>
   <jsp:forward page="<%= BASICINSTALLPAGE %>" />
<%
}
else
if(INSTALL_TYPE.compareTo("Advanced")==0)
{
%>
   <jsp:forward page="<%= DBINITPAGE %>" />
<%
}

}
%>

Installation - Type - Step 2 - <a href="<%= INSTALLATIONHELPPAGE %>" target="<%= targetValue %>" >Help</a><p>
Note: Some systems may require the advanced installation for special <br />
configuration options.  The configuration includes the following:<br /><br /><br />
*Database Names - Basic install uses default names.<br />
*JDBC Driver Selection - Basic install always uses MySql<br />
*Database Access - Basic install assumes default username and passwords.<br />
*Storage Paths - Basic install defaults to the webapp path.<br />
<p />
<form method="POST" action="<%= INSTALLTYPEPAGE %>">
Installation Type:
<SELECT class="select" name="INSTALL_TYPE" >
<OPTION>Basic</OPTION>
<OPTION>Advanced</OPTION>
</SELECT>
<p>
<input class="submit" type="submit" value="Continue" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
</div>
<%@ include file="copyright.jsp" %>
</BODY>
</HTML>