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
<TITLE>Installation - Path Selection - Step 3 of 5</TITLE>
</HEAD>
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
String testing = request.getParameter(InitInfo.TESTING);
String mainPath = request.getParameter(InitInfo.MAINPATH);
String testHtmlPath = request.getParameter(InitInfo.TESTHTMLPATH);

if(testing!=null && mainPath!=null && testHtmlPath!=null)
{
   InitInfo.setTesting(testing);

   if(InitInfo.isMainPathValid(new AbPath(mainPath)))
   {
      boolean isCopied = false;
      if(URLGLOBALS.getMainPath().compareTo(mainPath)!=0)
      {
         AbPath from = new AbPath(URLGLOBALS.getMainPath() + PATH_GLOBALS.getInstance().DATA_PATH);
         AbPath to = new AbPath(mainPath + PATH_GLOBALS.getInstance().DATA_PATH);
         if(Directory.create(to))
         {
            try
            {
               isCopied = FileUtil.copy(from, to);
            }
            catch(Exception e)
            {
               isCopied = false;
            }
         }
         else
         {
            isCopied = false;
         }

         if(!isCopied)
         {
%>
            Error copying data to new main path.  Please go back and check the main path.<p/>
<%
         }
      }
      else
      {
         //No need to copy
         isCopied = true;
      }
      
      if(isCopied)
      {
         
      InitInfo.setMainPath(new AbPath(mainPath));
      
      if(InitInfo.isTestHtmlPathValid(new AbPath(testHtmlPath)))
      {
         InitInfo.setTestHtmlPath(new AbPath(testHtmlPath));
         InitInfo.setHasRead(true);
         InitInfo.set();
%>
<jsp:forward page="<%= ADMININITPAGE %>" />
<%
      }
      else
      {
%>
         The Test Html Path you provided is not a valid path.<p>      
<%
      }

      }
   }
   else 
   {
%>
      The Main Path you provided is not a valid path.<p>     
<%
   }

}
%>

Installation - Path Selection - Step 3 of 5<p>
Note: The webapp path is used by default.  You should change the main path to<br />
a more secure location (Not in a public path).  If the directories you enter <br />
do not exist the installation program will attempt to create them.<p />

Warning: Using paths exposed to the other systems by http, ftp, or other service <br />
can degrade security.<br />
<form method="POST" action="<%= INITPAGE %>">
<input class="text" type="hidden" name="<%= InitInfo.TESTING %>" value="false" size="8">
<table class="table">
<tr>
<td>
Main Path: 
</td>
<td>
<input class="text" type="text" name="<%= InitInfo.MAINPATH %>"
   value="<%= URLGLOBALS.getWebappPath() %>" size="35"><br>
</td>
</tr>
<tr>
<td>
Temp Html Path: 
</TD>
<TD>

    <%
    final String DATA = "data";
       final String HTML = "html";
    %>

<input class="text" type="text" name="<%= InitInfo.TESTHTMLPATH %>"
   value="<%= URLGLOBALS.getWebappPath() + DATA + AbPathData.getInstance().SEPARATOR + HTML + AbPathData.getInstance().SEPARATOR %>" size="35"><p>
</td>
</tr>
</TABLE>
<p />
<input class="submit" type="submit" value="Continue" >
</form>
<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>
