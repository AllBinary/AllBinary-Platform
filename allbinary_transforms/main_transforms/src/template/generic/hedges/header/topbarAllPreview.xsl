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

   <xsl:import href="/template/generic/buttons/buttonsPreview.xsl" />
   <xsl:import href="/template/generic/hedges/header/imports/headingPreview.xsl" />

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

<xsl:call-template name="headingPreview" />

<table class="topBar2" 
   onClick="abScrollTo('topBar2{$scrollName}');{$javascriptErrorControl}" 
   cellspacing="2"  cellpadding="2" >

<tr>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Home</xsl:with-param>
      <xsl:with-param name="page">HOMEPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Services</xsl:with-param>
      <xsl:with-param name="page">SERVICESPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Contacts</xsl:with-param>
      <xsl:with-param name="page">CONTACTPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">About</xsl:with-param>
      <xsl:with-param name="page">ABOUTPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Auctions</xsl:with-param>
      <xsl:with-param name="page">AUCTIONSPAGE</xsl:with-param>
   </xsl:call-template>
</td>

</tr>
</table>

<table class="topBar2" 
   onClick="abScrollTo('topBar2{$scrollName}');{$javascriptErrorControl}" 
   cellspacing="2"  cellpadding="2" >
<tr>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Help</xsl:with-param>
      <xsl:with-param name="page">HELPPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">News</xsl:with-param>
<xsl:with-param name="page">NEWSPAGE</xsl:with-param>
</xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Support</xsl:with-param>
      <xsl:with-param name="page">SUPPORTPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Links</xsl:with-param>
      <xsl:with-param name="page">LINKSPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Portfolio</xsl:with-param>
      <xsl:with-param name="page">PORTFOLIOPAGE</xsl:with-param>
   </xsl:call-template>
</td>

</tr>
</table>

<table class="topBar2" 
   onClick="abScrollTo('topBar2{$scrollName}');{$javascriptErrorControl}" 
   cellspacing="2"  cellpadding="2" >
<tr>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">Basket</xsl:with-param>
<xsl:with-param name="page">BASKETPAGE</xsl:with-param>
</xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Products</xsl:with-param>
      <xsl:with-param name="page">PRODUCTSPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">Account</xsl:with-param>
<xsl:with-param name="page">FORWARDREVIEWPAGE</xsl:with-param>
</xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">Review</xsl:with-param>
<xsl:with-param name="page">REVIEWPAGE</xsl:with-param>
</xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">Customer Profile</xsl:with-param>
<xsl:with-param name="page">CUSTOMERPROFILEPAGE</xsl:with-param>
</xsl:call-template>
</td>

</tr>
</table>

<table class="topBar2" 
   onClick="abScrollTo('topBar2{$scrollName}');{$javascriptErrorControl}" 
   cellspacing="2"  cellpadding="2" >
<tr>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Register</xsl:with-param>
      <xsl:with-param name="page">REGISTERPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Login</xsl:with-param>
      <xsl:with-param name="page">LOGINPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<xsl:call-template name="menuButtonItemPreview">
<xsl:with-param name="name">Logout</xsl:with-param>
<xsl:with-param name="page">LOGOUTPAGE</xsl:with-param>
</xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Make Payment</xsl:with-param>
      <xsl:with-param name="page">paymentGatewayPageData.MAKEPAYMENT</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Checkout</xsl:with-param>
      <xsl:with-param name="page">paymentGatewayPageData.PAYMENTOPTIONS</xsl:with-param>
   </xsl:call-template>
</td>

</tr>
</table>

<table class="topBar2" 
   onClick="abScrollTo('topBar2{$scrollName}');{$javascriptErrorControl}" 
   cellspacing="2"  cellpadding="2" >
<tr>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Quote Request</xsl:with-param>
      <xsl:with-param name="page">QUOTEREQUESTPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
   <xsl:call-template name="menuButtonItemPreview">
      <xsl:with-param name="name">Forum</xsl:with-param>
      <xsl:with-param name="page">FORUMPAGE</xsl:with-param>
   </xsl:call-template>
</td>

<td>
<jsp:directive.include file="horizontalProductSearchPreview.jsp" />
</td>

</tr>
</table>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>
</xsl:stylesheet> 