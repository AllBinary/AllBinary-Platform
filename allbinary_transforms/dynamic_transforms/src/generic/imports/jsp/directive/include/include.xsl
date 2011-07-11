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
   
   <xsl:template name="jspDirectiveIncludeInclude" xmlns:jsp="http://java.sun.com/JSP/Page" >
      <xsl:param name="fileName"/>
      
      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />      
      
      <xsl:variable name="fileNameNoSpace" select="normalize-space($fileName)" />
      
      <jsp:directive.include file="/{$storeCurrentHostNamePath}/{$fileNameNoSpace}.jsp" />

   </xsl:template>

   <xsl:template name="jspDirectiveIncludeMenu" xmlns:jsp="http://java.sun.com/JSP/Page" >
      <xsl:param name="fileName"/>
      
      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />      
      
      <xsl:variable name="fileNameNoSpace" select="normalize-space($fileName)" />
      
      <jsp:directive.include file="/{$storeCurrentHostNamePath}/{$fileNameNoSpace}.jsp" />

   </xsl:template>

   <xsl:template name="jspDirectiveIncludeWindow" xmlns:jsp="http://java.sun.com/JSP/Page" >
      <xsl:param name="fileName"/>
      
      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />      
      
      <xsl:variable name="fileNameNoSpace" select="normalize-space($fileName)" />
      
      <jsp:directive.include file="/{$storeCurrentHostNamePath}/{$fileNameNoSpace}.jsp" />

   </xsl:template>

</xsl:stylesheet>
