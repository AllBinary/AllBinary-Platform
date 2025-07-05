<?xml version="1.0" encoding="UTF-8" ?>

<!--
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002,2004 AllBinary
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
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />
   
   <xsl:import href="/template/generic/buttons/itemPreview.xsl" />
   <xsl:import href="/template/generic/buttons/buttonsPreview.xsl" />

   <xsl:output method="xml" indent="yes" />

   <xsl:variable name="scrollName" select="'CssElementScrollName'" />
   <xsl:variable name="javascriptErrorControl" select="'return true;'" />

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

<jsp:root 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld"
   version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<table cellspacing="1" cellpadding="1">
<tr>

<td>
<xsl:call-template name="menuItemPreview">
   <xsl:with-param name="name">
      Authentication
   </xsl:with-param>
</xsl:call-template>
</td>

</tr>
</table>

<table >
<tr>
<td>

<jutil:element name="form">
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="name">
      <jsp:expression>LOGIN</jsp:expression>
   </jutil:attribute>   
   <jutil:attribute name="action">
      <jsp:expression>LOGINPAGE</jsp:expression>
   </jutil:attribute>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.PAGE</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression>paymentGatewayPageData.PAYMENTOPTIONS</jsp:expression>
      </jutil:attribute>
   </jutil:element>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.NAME</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression>GLOBALS.CHECKOUT</jsp:expression>
      </jutil:attribute>
   </jutil:element>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.VALUE</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression>GLOBALS.CHECKOUT</jsp:expression>
      </jutil:attribute>
   </jutil:element>
         
   
<table class="miniTable" 
   onClick="abScrollTo('miniTable{$scrollName}');{$javascriptErrorControl}" >
<tr>
<td>User Name:</td>
</tr>

<tr>
<td>

<jutil:element name="input">
   <jutil:attribute name="class">
      miniTextField
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>WeblisketSessionData.REMOVABLEUSERNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="onClick">
      abScrollTo('miniTextField<xsl:value-of select="$scrollName" />');<xsl:value-of select="$javascriptErrorControl" />
   </jutil:attribute>
</jutil:element>

</td>
</tr>
<tr>
<td>Password:</td>
</tr>

<tr>
<td>

<jutil:element name="input">
   <jutil:attribute name="class">
      miniTextField
   </jutil:attribute>
   <jutil:attribute name="type">
      password
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>WeblisketSessionData.REMOVABLEPASSWORD</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="onClick">
      abScrollTo('miniTextField<xsl:value-of select="$scrollName" />');<xsl:value-of select="$javascriptErrorControl" />
   </jutil:attribute>
</jutil:element>

</td>
</tr>
</table>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>GLOBALS.ADMINCOMMAND</jsp:expression>
      </jutil:attribute>
   </jutil:element>

<xsl:call-template name="submitMenuButtonItemPreview">
   <xsl:with-param name="name">
      LOGIN
   </xsl:with-param>
   <xsl:with-param name="command">
      NONE
   </xsl:with-param>
</xsl:call-template>

</jutil:element>

      </td>
   </tr>
</table>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
