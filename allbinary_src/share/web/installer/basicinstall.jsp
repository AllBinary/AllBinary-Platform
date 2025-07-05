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
<TITLE>Basic Installation -  - Step 3</TITLE>
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
final String DEFAULTSCHEMA = "jdbc:mysql";
final String DEFAULTSERVER = "localhost";
final String DEFAULTPORT = "3306";

final String MYSQLDRIVER = "org.gjt.mm.mysql.Driver";

      HashMap hashMap = new HashMap();
      
      hashMap.put(Initializer.ADMINJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.ADMINSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.ADMINSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.ADMINPORT, DEFAULTPORT);
      
      hashMap.put(Initializer.DBUSER,"allbinary");
      hashMap.put(Initializer.DBPASSWORD,"det835");

      hashMap.put(Initializer.CUSTOMERJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.CUSTOMERSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.CUSTOMERSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.CUSTOMERPORT, DEFAULTPORT);      
      hashMap.put(Initializer.CUSTOMERNAME,"freeblisket_securedc_customer");
      hashMap.put(Initializer.CUSTOMERUSERNAME,"securedc_cutome");
      hashMap.put(Initializer.CUSTOMERPASSWORD,"customer");

      hashMap.put(Initializer.HISTORYJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.HISTORYSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.HISTORYSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.HISTORYPORT, DEFAULTPORT);
      hashMap.put(Initializer.HISTORYNAME,"freeblisket_securedc_history");
      hashMap.put(Initializer.HISTORYUSERNAME,"securedc_history");
      hashMap.put(Initializer.HISTORYPASSWORD,"history");

      hashMap.put(Initializer.LOGJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.LOGSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.LOGSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.LOGPORT, DEFAULTPORT);
      hashMap.put(Initializer.LOGNAME,"freeblisket_securedc_securelog");
      hashMap.put(Initializer.LOGUSERNAME,"securedc_securel");
      hashMap.put(Initializer.LOGPASSWORD,"securesite");

      hashMap.put(Initializer.INVENTORYJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.INVENTORYSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.INVENTORYSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.INVENTORYPORT, DEFAULTPORT);
      hashMap.put(Initializer.INVENTORYNAME,"freeblisket_securedc_static");
      hashMap.put(Initializer.INVENTORYUSERNAME,"securedc_static");
      hashMap.put(Initializer.INVENTORYPASSWORD,"securesite");

      hashMap.put(Initializer.STATICPAGESJDBCDRIVER, MYSQLDRIVER);
      hashMap.put(Initializer.STATICPAGESSCHEMA, DEFAULTSCHEMA);
      hashMap.put(Initializer.STATICPAGESSERVER, DEFAULTSERVER);
      hashMap.put(Initializer.STATICPAGESPORT, DEFAULTPORT);
      hashMap.put(Initializer.STATICPAGESNAME,"freeblisket_securedc_inventory");
      hashMap.put(Initializer.STATICPAGESUSERNAME,"securedc_invento");
      hashMap.put(Initializer.STATICPAGESPASSWORD,"inventory");

      Initializer initializer = new Initializer(hashMap);
      if(initializer.isValid())
      {
      //initialize database data if valid
      initializer.set();
      //create databases and database users   
      //not yet implemented createDatabases();

      boolean isUsersCreated = initializer.createUsers();
      boolean isDbsCreated = initializer.createDatabases();
      boolean isTablesCreated =  initializer.createTables();
      if(isUsersCreated)
      {
         if(isDbsCreated)
         {
            if(isTablesCreated)
            {
               String testing = "false";
               String mainPath = request.getRealPath("/");
               String testHtmlPath = mainPath + AbPathData.getInstance().SEPARATOR + "temp_data";

               InitInfo.setTesting(testing);

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
            }
            else
            {
               %>
               Error creating tables.<br />
              <%
            }
         }
         else
         {
            %>
            Error creating databases.<br />
            <%
         }
      }
      else
      {
          %>
          Error creating database users.<br />
         <%
      }

%>

<%
   }
   else
   {
      %>
      <%= initializer.getInvalidInfo() %>
      <%
   }
%>

</DIV>
</body>
</html>
