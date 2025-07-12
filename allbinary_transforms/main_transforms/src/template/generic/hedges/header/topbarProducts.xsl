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

<table align="left" class="topBar2" cellspacing="2"  cellpadding="2">

<tr>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Home</xsl:with-param>
      <xsl:with-param name="page">HOMEPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Products</xsl:with-param>
      <xsl:with-param name="page">PRODUCTSPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItem">
      <xsl:with-param name="name">Auctions</xsl:with-param>
      <xsl:with-param name="page">AUCTIONSPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<jsp:directive.include file="horizontalProductSearch.jsp" />
</td>

</tr>
</table>

</jsp:root>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>
</xsl:stylesheet> 