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

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/template/generic/buttons/itemPreview.xsl" />
   <xsl:import href="/template/generic/buttons/linksPreview.xsl" />
    
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

<table cellspacing="1"  cellpadding="1">
<tr>

<td>
<xsl:call-template name="menuItemPreview">
   <xsl:with-param name="name">
      Account
   </xsl:with-param>
</xsl:call-template>
</td>

</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      QUOTEREQUESTPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Quote Request
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      paymentGatewayPageData.MAKEPAYMENT
   </xsl:with-param>
   <xsl:with-param name="name">
      Make Payment
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      FORWARDREVIEWPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Order Review
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      paymentGatewayPageData.PAYMENTOPTIONS
   </xsl:with-param>
   <xsl:with-param name="name">
      Checkout
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      LOGOUTPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Logout
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>
<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      CUSTOMERPROFILEPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Profile
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      BASKETPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Basket
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

</table>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
