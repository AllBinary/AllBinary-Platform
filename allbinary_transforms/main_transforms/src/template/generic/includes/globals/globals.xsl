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
   <xsl:import href="/template/generic/includes/globals/imports/storefront/storefrontGlobals.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/views/globalViews.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/commands/globalCommands.xsl" />
   <xsl:import href="/template/generic/includes/globals/imports/directive/page/import/jspDirectivePageImports.xsl" />
   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
            <xsl:for-each select="GLOBALS_NAME" >

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

</jsp:scriptlet>

<xsl:call-template name="storefrontGlobals" />

<jsp:scriptlet>

   String command = (String) request.getParameter(GLOBALS.ADMINCOMMAND);
   
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);

</jsp:scriptlet>

<xsl:call-template name="globalViews" />

<jsp:scriptlet>
final String SEARCHVALUEKEY="searchValue";
</jsp:scriptlet>

<xsl:call-template name="globalPages" />

<xsl:call-template name="globalCommands" />

<jsp:scriptlet>

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
