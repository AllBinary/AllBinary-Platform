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
<TITLE>Installation - Database Info - Step 3 of 5</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >

<%
Vector roles = new Vector();
roles.add(BasicUserRole.INSTALLER);
%>
<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= INSTALLERLOGOUTPAGE %>" />
</ecommerce:fileauthentication>

Installation - Database Info - Step 3 of 5 - <a href="<%= INSTALLATIONHELPPAGE %>" target="<%= targetValue %>" >Help</a><p/>

<%
final String DEFAULTSCHEMA = "jdbc:mysql";
final String DEFAULTSERVER = "localhost";
final String DEFAULTPORT = "3306";

final String MYSQLDRIVER = "org.gjt.mm.mysql.Driver";
final String ODBCDRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";

String jdbcDriver = request.getParameter(GLOBALS.JDBCDRIVER);

jdbcDriver = MYSQLDRIVER;
%>

<%
/*
Customer Database
Set Name: securedc_customer
Set UserName: securedc_cutome
Set Password: customer835

History Database
Set Name: securedc_history
Set UserName: securedc_hisory
Set Password: history835

Log Database
Set Name: securedc_securelog
Set UserName: securedc_securel
Set Password: securesite835

StaticPages Database
securedc_static
securedc_static
securesite835

Inventory Database
securedc_inventory
securedc_invento
inventory835
*/   

final String DBINIT = "DbInit";
String command = request.getParameter(GLOBALS.ADMINCOMMAND);

if(command!=null && command.compareTo(DBINIT) == 0)
{
   Initializer initializer = new Initializer(request.getParameterMap());
   if(initializer.isValid())
   {
      //initialize database data if valid
      initializer.set();
      //create databases and database users   
      //not yet implemented createDatabases()

      boolean isUsersCreated = initializer.createUsers();
      boolean isDbsCreated = false;
      boolean isTablesCreated = false;
         
      if(isUsersCreated)
      {
         isDbsCreated = initializer.createDatabases();
         if(isDbsCreated)
         {
            isTablesCreated =  initializer.createTables();
            if(isTablesCreated)
            {
               //forwards to the next step in the installation 
            %>
               <jsp:forward page="<%= INITPAGE %>" />
            <%
            }
            else
            {
            %>
Unable to create tables.<br />
            <%
            }
         }
         else
         {
         %>
Unable to create one or more databases.<br />
         <%
         }
      }
      else
      {
      %>
Unable to create users.<br />
      <%
      }

      //show error resolution if databases not created
      if(!isDbsCreated || !isUsersCreated || !isTablesCreated)
      {  
%>
<p />
<a href="<%= INITPAGE %>" >
If you need to create the databases and/or users manually you may skip to the next step by clicking here.<p>
</a>

This page only show up if the Weblisket was unable to automatically create the databases with SQL commands.  This can occur as a result of one or more of the following problems:<p>

1.  The JDBC driver you selected does not have the ability to create databases and users.<br>

Possible Solution 1:<br>
You can start the web installation process over and enter a better driver.<br>
Possible Solution 2:<br>
You can create the databases manually (by requesting your host administrator to create them or use the web interface provided by your hosting company.<br>

Note: Many hosting companies do not allow a user to create databases or users on a database server directly. However, many provide a web interface for adding databases and/or users.<br>

2.  You do not have the privilages to add databases and users.<br>

Possible Solution 1:<br>
You may have mistyped one of the usernames and/or passwords.<br>

Possible Solution 2:<br>
You can create them manually.  Just make sure the names, usernames, and passwords match the databases and users you created manually.<br>
<p>

<%
      }
   }
   else
   {  
      //If the data entered is not valid
%>
      <%= initializer.getInvalidInfo() %>
<%
   }
} //end command if
%>

<p />
Notes: We included an old JDBC driver (<%= MYSQLDRIVER %>), but if you want to use 
another database/driver like <%= ODBCDRIVER %> it should work.<p />

Instructions: Please provide a JDBC driver, database name, username, and password 
for each database.  The data is encryption and used internally.<p/>

Default Database URI: <%= DEFAULTSCHEMA %>://<%= DEFAULTSERVER %>:<%= DEFAULTPORT %>/
<p>

<form method="POST" action="<%= DBINITPAGE %>">

Database User with table creation privilages:<p>

<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.ADMINJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.ADMINSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.ADMINSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.ADMINPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.DBUSER %>" value="root" size="30"><br>
</td>
</tr>
<tr>
<td>
Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.DBPASSWORD %>" value="" size="30"><p>
</td>
</tr>
</table>

<p>
Customer Database<p>

<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<td>
DB Name: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERNAME %>" value="customer" size="30"><br>
</td>
</tr>
<tr>
<td>

Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.CUSTOMERUSERNAME %>" value="custome" size="30"><br>
</td>
</tr>
<tr>
<td>

Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.CUSTOMERPASSWORD %>" value="customer835" size="30"><p>
</td>
</tr>
</table>

<p>
History Database<p>
<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<tr>
<td>
DB Name: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYNAME %>" value="history" size="30"><br>
</td>
</tr>
<tr>
<td>
Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.HISTORYUSERNAME %>" value="history" size="30"><br>
</td>
</tr>
<tr>
<td>
Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.HISTORYPASSWORD %>" value="history835" size="30"><p>
</td>
</tr>
</table>

<p>
Log Database<p>
<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<tr>
<td>
DB Name: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGNAME %>" value="securelog" size="30"><br>
</td>
</tr>
<tr>
<td>
Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.LOGUSERNAME %>" value="securel" size="30"><br>
</td>
</tr>
<tr>
<td>
Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.LOGPASSWORD %>" value="securesite835" size="30"><p>
</td>
</tr>
</table>

<p>
Inventory Database<p>
<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<tr>
<td>
DB Name: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYNAME %>" value="inventory" size="30"><br>
</td>
</tr>
<tr>
<td>
Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.INVENTORYUSERNAME %>" value="invento" size="30"><br>
</td>
</tr>

<tr>
<td>
Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.INVENTORYPASSWORD %>" value="inventory835" size="30"><p>
</td>
</tr>
</table>

<p>
Static Pages Database<p>
<table class="table">

<tr>
<td>
JDBC Driver:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESJDBCDRIVER %>" value="<%= jdbcDriver %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Schema:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESSCHEMA %>" value="<%= DEFAULTSCHEMA %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Server:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESSERVER %>" value="<%= DEFAULTSERVER %>" size="30"><br>
</td>
</tr>

<tr>
<td>
Port:
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESPORT %>" value="<%= DEFAULTPORT %>" size="30"><br>
</td>
</tr>

<tr>
<td>
DB Name: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESNAME %>" value="static" size="30"><br>
</td>
</tr>
<tr>
<td>

Username: 
</td>
<td>
<input class="text" type="text" name="<%= Initializer.STATICPAGESUSERNAME %>" value="static" size="30"><br>
</td>
</tr>
<tr>
<td>

Password: 
</td>
<td>
<input class="text" type="password" name="<%= Initializer.STATICPAGESPASSWORD %>" value="securesite835" size="30"><p>
</td>
</tr>
</table>
<p>
<input type="hidden" value="<%= DBINIT %>" name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="submit" type="submit" value="Continue" name="" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>
