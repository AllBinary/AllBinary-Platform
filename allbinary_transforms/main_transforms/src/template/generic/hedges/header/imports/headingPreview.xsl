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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
   xmlns:jsp="http://java.sun.com/JSP/Page" >

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:output method="xml" indent="yes" />

   <xsl:template name="headingPreview" >

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />

      <xsl:variable name="storeStaticPathName" select="STOREFRONT_NAME/STATICPATH/name" />
      <xsl:variable name="storeStaticPath" select="STOREFRONT_NAME/STATICPATH/value" />

      <xsl:for-each select="HEADER_NAME" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<div class="header"
   onClick="abScrollTo('header{$scrollName}');{$javascriptErrorControl}" >

<table align="left" >
   <tr>
      <td align="left" >

         <xsl:for-each select="LOGO_NAME" >
         
<xsl:variable name="logoImagePath" select="LOGO_IMAGE_PATH/value" />

<xsl:variable name="logoImageFile" select="LOGO_IMAGE_FILE_NAME/value" />


<xsl:if test="$logoImageFile!=''">
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}/logo/{$logoImageFile}" />
</xsl:if>

         </xsl:for-each>

      </td>
   <td>

<div class="title" 
   onClick="abScrollTo('title{$scrollName}');{$javascriptErrorControl}" >
   
         <xsl:for-each select="TITLE_NAME" >
         
<xsl:variable name="titleText" select="TITLE_TEXT/value" />

<xsl:if test="$titleText!=''">
<xsl:value-of select="$titleText" />
</xsl:if>

         </xsl:for-each>

</div>

      </td>
   </tr>
</table>

</div>
<br/>
      </xsl:for-each>
      
   </xsl:template>
   
</xsl:stylesheet> 