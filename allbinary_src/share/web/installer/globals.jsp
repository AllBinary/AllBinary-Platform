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
       uri="/WEB-INF/admin.tld"
       prefix="admin"
%>
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>

<%@ page buffer="32kb" %>

<%@ page import="java.io.File" %>

<%@ page import="java.util.*" %>

<%@ page import="abcs.business.user.role.BasicUserRole" %>

<%@ page import="abcs.globals.URLGLOBALS" %>
<%@ page import="abcs.globals.AppUrlGlobals" %>
<%@ page import="org.allbinary.globals.PATH_GLOBALS" %>

<%@ page import="abcs.business.init.InitInfo" %>
<%@ page import="abcs.business.init.InitInfoEntity" %>
<%@ page import="abcs.business.init.WeblisketFinder" %>

<%@ page import="abcs.business.installer.Initializer" %>

<%@ page import="abcs.logic.basic.io.LineReader" %>
<%@ page import="abcs.logic.basic.io.file.FileUtil" %>
<%@ page import="abcs.logic.basic.io.file.directory.Directory" %>
<%@ page import="abcs.logic.basic.io.file.generators.OrderIdGenerator" %>
<%@ page import="abcs.logic.basic.io.file.generators.ProductIdGenerator" %>

<%@ page import="abcs.logic.basic.path.AbPath" %>
<%@ page import="abcs.logic.basic.path.AbPathData" %>

<%@ page import="abcs.logic.system.loader.WebappClassLoaderInfo" %>

<%@ page import="org.allbinary.business.user.UserData" %>

<%@ page import="org.allbinary.globals.GLOBALS" %>

<%@ page import="org.allbinary.logic.communication.http.request.session.WeblisketSessionData" %>
<%@ page import="org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationData" %>

<%
AppUrlGlobals urlGlobals = new AppUrlGlobals();
urlGlobals.setWebappPath(request.getRealPath("/"));
URLGLOBALS.init(urlGlobals);

WebappClassLoaderInfo.setLoader(getClass().getClassLoader());

final String IGNOREXMLXSL = "/generic/ignoreXmlView.xsl";
final String BACKGROUNDCOLOR="#FFFFFF";

final String BUTTONOVERCOLOR="#777777";
final String BUTTONCOLOR="#0000AA";
final String SIDEBARCOLOR="#BB00DD";
final String SPECIALCOLOR1 = "#AA00AA";
final String SPECIALCOLOR2 = "#999999";

final String targetValue="_blank";
final String INSTALLATIONHELPPAGE = "help.jsp";

final String INSTALLERLOGOUTPAGE = "installerlogout.jsp";
final String STARTPAGE = "start.jsp";
final String INSTALLTYPEPAGE = "installtype.jsp";

final String BASICINSTALLPAGE = "basicinstall.jsp";

final String INITPAGE = "init.jsp";
final String DBINITPAGE = "dbinit.jsp";

//final String HOSTNAMEINITPAGE = "hostnameinit.jsp";

final String ADMININITPAGE = "admininit.jsp";
final String ADMININITFORMPAGE = "admininitform.jsp";
final String BASICADMININITPAGE = "basicadmininit.jsp";

final String EXAMPLESTOREINITPAGE = "storeinit.jsp";
final String CHANGEPASSWORDPAGE = "changepassword.jsp";  
final String FINISHPAGE = "finish.jsp";
final String LOGOUTPAGE = "logout.jsp";

%>
<ecommerce:customloader command="<%= GLOBALS.SET %>"
   webappPath="<%= URLGLOBALS.getWebappPath() %>"/>
