<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:import href="/template/generic/includes/globals/imports/globals.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/page/globalPages.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/views/globalViews.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/commands/globalCommands.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/directive/page/import/jspDirectivePageImports.xsl" />
   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
            <xsl:for-each select="GLOBALS_NAME" >
         
         <xsl:variable name="black" select="'#000000'" />
         <xsl:variable name="white" select="'#FFFFFF'" />
         
         <xsl:variable name="lightBlue" select="'#00CCFF'" />
         
         <xsl:variable name="lightGrey" select="'#999999'" />
         <xsl:variable name="grey" select="'#444444'" />
         
         <xsl:variable name="blue" select="'#0000AA'" />
         <xsl:variable name="red" select="'#AA0000'" />
         
         
         <xsl:variable name="backgroundColor" select="$blue" />
         <xsl:variable name="mainBackgroundColor" select="$lightBlue" />
         <xsl:variable name="hedgeBackgroundColor" select="$lightGrey" />
         
         <xsl:variable name="headingTextColor" select="$lightGrey" />
         
         <xsl:variable name="textColor" select="$black" />
         
         <xsl:variable name="textFieldTextColor" select="$textColor" />
         <xsl:variable name="textFieldBackgroundColor" select="$white" />
         
         <xsl:variable name="buttonOverColor" select="$grey" />
         <xsl:variable name="buttonColor" select="$blue" />

         <xsl:variable name="specialHeadingTextColor" select="$lightGrey" />
         <xsl:variable name="specialTextColor" select="$red" />

                <jsp:root
                        xmlns:jsp="http://java.sun.com/JSP/Page"
                        xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                        version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<jsp:directive.page buffer="32kb" />
<jsp:directive.page errorPage="JspError.jsp" />

<xsl:call-template name="jspDirectivePageImports" />

<jsp:scriptlet>
//GLOBALS

Vector roles = new Vector();
roles.add(BasicUserRole.CUSTOMER);
roles.add(BasicUserRole.SUBSCRIBERCUSTOMER);
roles.add(BasicUserRole.WHOLESALECUSTOMER);

Vector subscriberRoles = new Vector();
subscriberRoles.add(BasicUserRole.SUBSCRIBERCUSTOMER);

Vector wholesaleRoles = new Vector();
wholesaleRoles.add(BasicUserRole.WHOLESALECUSTOMER);

      <xsl:for-each select="STOREFRONT_NAME" >
         <xsl:variable name="storeNameName" select="STOREFRONT_NAME/name" />
         <xsl:variable name="storeNameValue" select="STOREFRONT_NAME/value" />
final String STORENAME = "<xsl:value-of select="$storeNameValue" />";
      </xsl:for-each>

URLGLOBALS.setWebappPath(request.getRealPath("/"));

loader.WebappClassLoaderInfo.setLoader(getClass().getClassLoader());

BasicStoreFrontInterface storeFrontInterface = 
   BasicStoreFrontFactory.getInstance(STORENAME);

String HOMEHOSTNAME = 
   storeFrontInterface.getCurrentHomeHostName() + 
   storeFrontInterface.getCurrentHomeHostNamePath();

   String LOCATION = 
      storeFrontInterface.getCurrentHostName() + 
      storeFrontInterface.getCurrentHostNamePath();

   String HOSTNAMEPATH = storeFrontInterface.getCurrentHostNamePath();

   final String STATICPATH = storeFrontInterface.getStaticPath();
   
   String command = (String) request.getParameter(GLOBALS.ADMINCOMMAND);
   
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
   
   final String BACKGROUNDCOLOR="<xsl:value-of select="$backgroundColor" />";
   final String MAINBACKGROUNDCOLOR="<xsl:value-of select="$mainBackgroundColor" />";   

   final String TEXTCOLORONE="<xsl:value-of select="$headingTextColor" />";
   final String TEXTCOLOR="<xsl:value-of select="$textColor" />";
   
   final String BUTTONOVERCOLOR="<xsl:value-of select="$buttonOverColor" />";
   final String BUTTONCOLOR="<xsl:value-of select="$buttonColor" />";
   
   final String SIDEBARCOLOR="<xsl:value-of select="$hedgeBackgroundColor" />";
   
   final String SPECIALCOLOR1 ="<xsl:value-of select="$specialHeadingTextColor" />";
   final String SPECIALCOLOR2 ="<xsl:value-of select="$specialTextColor" />";
   
   final String TEXTFIELDCOLOR ="<xsl:value-of select="$textFieldTextColor" />";
   final String TEXTFIELDBACKGROUNDCOLOR ="<xsl:value-of select="$textFieldBackgroundColor" />";

   final String MENUITEMTEXTCOLOR = TEXTFIELDBACKGROUNDCOLOR;
   //final String MENUITEMTEXTCOLOR=SIDEBARCOLOR;

<xsl:call-template name="globalViews" />

final String SEARCHVALUEKEY="searchValue";

<xsl:call-template name="globalPages" />

<xsl:call-template name="globalCommands" />

//misc
final String NONE = "none";
final String COLUMNZERO = "[0]";

final String COMINGSOON = "Coming Soon...";
final String DEFAULTBODYMESSAGE = "The page is under construction.";

</jsp:scriptlet>

<xsl:call-template name="globals" />

<ecommerce:customloader command="%= GLOBALS.SET %"
   webappPath="%= URLGLOBALS.getWebappPath() %"/>

<ecommerce:basket command="%= command %" 
   storeName="%= STORENAME %"/>
   
</jsp:root>

            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
