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
    <xsl:import href="/template/generic/buttons/buttons.xsl" />
    <xsl:import href="/template/generic/hedges/header/imports/heading.xsl" />
    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<xsl:call-template name="heading" />

<table class="topBar2" cellspacing="2"  cellpadding="2">

<tr>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Home</xsl:with-param>
      <xsl:with-param name="page">HOMEPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Services</xsl:with-param>
      <xsl:with-param name="page">SERVICESPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Portfolio</xsl:with-param>
      <xsl:with-param name="page">PORTFOLIOPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Quote Request</xsl:with-param>
      <xsl:with-param name="page">QUOTEREQUESTPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItem">
<xsl:with-param name="name">Make Payment</xsl:with-param>
<xsl:with-param name="page">paymentGatewayPageData.MAKEPAYMENT</xsl:with-param>
</xsl:call-template>
</td>

</tr>
</table>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>
</xsl:stylesheet> 