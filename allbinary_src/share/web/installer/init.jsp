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
<TITLE>Installation - Path Selection - Step 4 of 5</TITLE>
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

<%
               String mainPath = request.getRealPath("/");
               String testHtmlPath = mainPath + AbPathData.getInstance().SEPARATOR + "temp_data";

               InitInfo.setTesting("false");

               if(InitInfo.isMainPathValid(new AbPath(mainPath)))
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
Unable to set or create temp html path.<p>
<%
                  }
               }
               else 
               {
%>
Unable to set the data path.<p>
<%
               }
%>
<%@ include file="copyright.jsp" %>
</div>
</BODY>
</HTML>
