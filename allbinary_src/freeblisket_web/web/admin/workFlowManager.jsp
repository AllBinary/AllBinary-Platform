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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>
<%@ taglib
       uri="/WEB-INF/admin.tld"
       prefix="admin"
%>
<%@ taglib
       uri="/WEB-INF/generic.tld"
       prefix="generic"
%>
<HTML>
<HEAD>
<TITLE>WorkFlow Management - Store Admin</TITLE>
</HEAD>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.WORKFLOWEDITOR);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - WorkFlow Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + WORKFLOWMANAGERPAGE %>">Help</a></p>
<div class="main">

<%
   String ADD = "Add";
   String ADDNEW = "Add New";
   String REMOVE = "Remove";
   String VIEWALL = "View All";
   String EDIT = "Edit";
   String UPDATE = "Update";
   String SEARCH = "Search";

   String role = request.getParameter(UserRoleData.NAME); 

   if(command!=null && storeName!=null)
   {
      boolean tableSelected=false;
      String sqlStatement = null;
      if(command.compareTo(ADD)==0)
      {
%>
<admin:workflows 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:workflows isSelected="true" 
   command="<%= GLOBALS.INSERT %>" />
</admin:workflows>
<p>
<%
      }
      else
      if(command.compareTo(REMOVE)==0)
      {
%>
<admin:workflows
   command="<%= GLOBALS.DELETE %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:workflows isSelected="true" 
   command="<%= GLOBALS.DELETE %>" />
</admin:workflows>
<p>
<%     
      }
      else
      if(command.compareTo(VIEWALL)==0)
      {
%>
<admin:workflows command="<%= GLOBALS.VIEW %>" 
   storeName="<%= storeName %>" 
   xsl="<%= WORKFLOWSXSL %>" />
<p>
<%	
      }
      else
      if(command.compareTo(EDIT)==0)
      {
%>
<admin:workflows 
   command="<%= GLOBALS.EDIT %>"
   storeName="<%= storeName %>"  
   xsl="<%= EDITWORKFLOWXSL %>" />
<p>
<%	
      }
      else
      if(command.compareTo(ADDNEW)==0)
      {
%>

<admin:workflows 
   command="<%= GLOBALS.NEW %>"
   storeName="<%= storeName %>" 
   xsl="<%= ADDWORKFLOWXSL %>" />
<p>
<%	
      }
      else
      if(command.compareTo(UPDATE)==0)
      {
%>
<admin:workflows 
   command="<%= GLOBALS.UPDATE %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >
<ecommerce:workflows  isSelected="true" 
   command="<%= GLOBALS.UPDATE %>" />
</admin:workflows>
<p>
<%
      }
   }
 //GLOBALS.ENCRYPTION TIMECREATED LASTMODIFIED used internally
%>

<form method="POST" action="<%= WORKFLOWMANAGERPAGE %>">
<input type="hidden" value="<%= ADDNEW %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Add" />
</form>

<form method="POST" action="<%= WORKFLOWMANAGERPAGE %>">
Name: <input type="text" name="<%= WorkFlowData.NAME %>" value="" size="38" />
<input type="hidden" value="<%= EDIT %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<br /><br />

<input class="submit" type="submit" value="Edit" />
</form>

<form method="POST" action="<%= WORKFLOWMANAGERPAGE %>">
Name: <input type="text" name="<%= WorkFlowData.NAME %>" value="" size="38" />
<input type="hidden" value="<%= REMOVE %>" name="<%= GLOBALS.ADMINCOMMAND %>">

<br /><br />
<input class="submit" type="submit" value="Remove" />
</form>

<form method="POST" action="<%= WORKFLOWMANAGERPAGE %>">
<input type="hidden" value="<%= VIEWALL %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="View All" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>