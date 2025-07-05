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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>

<HTML>
<HEAD>
<TITLE>Installation - Help</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >
Installation - Help<p/>

Summary<p/>

This page contains valuable help information for the installation configuration<br/>
of Weblisket.  Each section beyond the summary provides help for a given step in <br/>
the installation process.<p/>

Start Page - Step 1 of 4<p/>

This step requires the Installer to provide a username and password.  The default <br/>
username/password for the Web Installation is as follows:<p/>

UserName: Installer<br/>
Password: Installer<p/>

Note: If you have completed the installation before you may have a different password.<br/>

You must also read and agree to the license agreement by checking the box.<p/>

Database Info - Step 2 of 4

This step requires the Installer to enter a valid JDBC driver in the text field.<br/>

The following describes the possible problems and solutions:<p/>

1. The JDBC driver you specified is not in your classpath.<p/>

Solution 1: Move the JDBC driver into any directory specified in the existing classpath. <br/>
Solution 2: Add the directory that contains the JDBC driver to the classpath.<p/>

2. The JDBC driver you specified does not exit.<p/>

Solution: Get a JDBC driver. <p/>

3. The JDBC driver you specified is not valid.<p/>

Solution: Use a valid JDBC driver.<p/>

Path Selection - Not in Installation Yet<p/>

This step requires you to provide the following directories:

Main Path<p/>

This path is used to store all initialization, logging, backup, and <br/>
specialized data.<p/>

The Main Path directory hierarchy is as follows:<p/>

Logs - Main Path/data/log<br/>
Backups - Main Path/data/backup<br/>
Initialization - Main Path/data/init<br/>
Specialized - Main Path/data/init<p/>

Test Html Path<p/>

This path is used as a temporary location for storing statically<br/>
generated html files.<p/>

Directories with names cooresponding to a specific store are created as needed when<br/>
static files are generated.<p/>

Webapp Path<p/>

This path should be the same as the path used by the JSP Server to<br/>
specify the context/directory of the WebApp.<p/>

Administrator User Creation - Step 3 of 4<p/>

This step requires the username and password for the Administrator of Weblisket.<br/>
The Administrator has access to Weblisket administrative functions.<br>

Change Installer Password Page - Step 4 of 4<p>

This step allows you to secure your installation from future modification by <br>
others.  Please keep the new password in a safe place.<br/>

Warning: Make sure you write down the new password.<p/>

<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>