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
<TITLE>Store Management - Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="adminTopBar.jsp" %>
<%@ include file="adminTopBar2.jsp" %>
<%
Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>
<div class="mainHeading">
<p>Store Management - <a href="<%= ADMINFREEBLISKETONLINEHELP + AdminPageData.STORES %>">Help</a></p>
<div class="main">
<%

final String MANAGE = "Manage";
final String EDIT = "Edit";
final String UPDATE = "Update";
final String INSERT = "Add";
final String DELETE = "Delete";

//if(storeName==null || storeName.compareTo("")==0)
{
   storeName = (String) request.getParameter(StoreFrontData.SELECTSTORENAME);
   session.setAttribute(StoreFrontData.NAME,storeName);
}

   if(command!=null && storeName!=null)
   {
      //boolean tableSelected=false;
      //String sqlStatement = null;
      if(command.compareTo(INSERT)==0)
      {
%>
<form method="POST" action="<%= AdminPageData.STORES %>">
<TABLE class="main" >
<TR>
<TD width="%30">Name: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.NAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Username: </TD><TD width="%70">
<input class="text"  type="text" size="30" name="<%= UserData.USERNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Home HostName: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.HOMEHOSTNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Home HostName Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.HOMEHOSTNAMEPATH %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Hostname: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.HOSTNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Hostname Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.HOSTNAMEPATH %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test Home HostName: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTHOMEHOSTNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test Home HostName Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTHOMEHOSTNAMEPATH %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test Hostname: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTHOSTNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test Hostname Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTHOSTNAMEPATH %>" value=""><br>
</TD></TR>
<TR>
<TD width="%30">Image Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.IMAGEPATH %>" value=""><br>
</TD>
</TR><TR>
<TD width="%30">Static Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.STATICPATH %>" value=""><br>
</TD>
</TR><TR>
<TD width="%30">Category Path: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.CATEGORYPATH %>" value=""><br>
</TD>
</TR>
<TR><TD width="%30">Inventory Control: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.INVENTORYCONTROL %>" value=""><br>
</TD></TR>

<TR>
<TD width="%30">Package Location: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.PACKAGELOCATION %>" value=""><br>
</TD></TR>
<TR><TD width="%30">FTP: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.FTP %>" value=""><br>
</TD></TR>
<TR><TD width="%30">FTP Username: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.FTPUSERNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">FTP Password: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.FTPPASSWORD %>" value=""> <br>
</TD></TR>
<TR><TD width="%30">Test FTP: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTFTP %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test FTP Username: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTFTPUSERNAME %>" value=""><br>
</TD></TR>
<TR><TD width="%30">Test FTP Password: </TD>
<TD width="%70">
<input class="text"  type="text" size="30" name="<%= StoreFrontData.TESTFTPPASSWORD %>" value=""> <br>
</TD></TR>

<TR>
<TD width="%30"></TD>
<TD width="%70"></TD>
</TR>

</TABLE>
<input class="submit" type="submit" 
 value="<%= GLOBALS.INSERT %>" 
 name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
<%
      }
      else
      if(command.compareTo(MANAGE)==0)
      {

//forward administrator to storemanager page as if store owner
%>
<jsp:forward page="<%= STOREMANAGERPAGE %>" />
<%
      }
      else
      if(command.compareTo(GLOBALS.UPDATE)==0 ||
         command.compareTo(GLOBALS.INSERT)==0 ||
         command.compareTo(GLOBALS.DELETE)==0)
      {
%>
   <admin:storefront
      command="<%= command %>"
      storeName="<%= storeName %>"
      xsl="<%= IGNOREXMLXSL %>" >
      <ecommerce:storefronts isSelected="true" command="<%= command %>" />
   </admin:storefront>
<%
      }
   }
%>

<form method="POST" action="<%= AdminPageData.STORES %>">
<p>
<ecommerce:storefronts isSelected="true" command="<%= StoreFrontData.SELECT %>"/>
</p>
<input class="submit" type="submit" value="<%= MANAGE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<p>
<input class="submit" type="submit" value="<%= DELETE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"/>
</p>
</form>

<form method="POST" action="<%= STOREWIZARDPAGE %>">
<input class="submit" type="submit" value="<%= INSERT %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>"/>
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
