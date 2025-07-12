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

<!--

CurrentPage: <xsl:value-of select="PAGE/value" disable-output-escaping="yes" />
 Total Number Of Pages: <xsl:value-of select="TOTALNUMBEROFPAGES/value" disable-output-escaping="yes" />

Total Results:
<xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> 

Found <xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> Products 
On <xsl:value-of select="TOTALNUMBEROFPAGES/value" disable-output-escaping="yes" /> Pages

-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />
   <xsl:import href="/generic/user/commerce/inventory/item/imports/genericItemListing.xsl" />
   <xsl:import href="/generic/generation/products/listing/imports/pageLinks.xsl" />

   <xsl:template name="genericListing" >
      <xsl:param name="storeCurrentHostName" />
      <xsl:param name="storeCurrentHostNamePath" />
      <xsl:param name="storeCurrentHomeHostName" />
      <xsl:param name="storeCurrentHomeHostNamePath" />
      <xsl:param name="storeStaticPath" />

<div class="mainHeading">
<p>Product Listing</p>
<div class="main">
                
      <xsl:for-each select="INVENTORY" >      
      
      <xsl:variable name="baseFileName" select="SEARCHDATA_BASEFILENAME/value" />
      
<xsl:call-template name="pageLinksTable" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="storeStaticPath" >
      <xsl:value-of select="$storeStaticPath" />
   </xsl:with-param>
   <xsl:with-param name="baseFileName" >
      <xsl:value-of select="$baseFileName" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" ></xsl:with-param>
</xsl:call-template>

<xsl:call-template name="basicItems" >
   <xsl:with-param name="storeCurrentHostName" >
      <xsl:value-of select="$storeCurrentHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHostNamePath" >
      <xsl:value-of select="$storeCurrentHostNamePath" />
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="pageLinksTable" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="storeStaticPath" >
      <xsl:value-of select="$storeStaticPath" />
   </xsl:with-param>
   <xsl:with-param name="baseFileName" >
      <xsl:value-of select="$baseFileName" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" >2</xsl:with-param>
</xsl:call-template>

      </xsl:for-each>
</div>
</div>

   </xsl:template>
</xsl:stylesheet>
