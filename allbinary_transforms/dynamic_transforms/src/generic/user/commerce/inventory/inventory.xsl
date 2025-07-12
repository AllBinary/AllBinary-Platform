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

<p>Found <xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> Products 
On <xsl:value-of select="TOTALNUMBEROFPAGES/value" disable-output-escaping="yes" /> Pages</p>

-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />
   <xsl:import href="/generic/user/commerce/inventory/item/imports/genericItemListing.xsl" />
   <xsl:import href="/generic/user/commerce/inventory/imports/pageSearchLinks.xsl" />
   
   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeCurrentHomeHostNameName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/name" />
      <xsl:variable name="storeCurrentHomeHostName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/value" />

      <xsl:variable name="storeCurrentHomeHostNamePathName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHomeHostNamePath" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/value" />

      <xsl:variable name="keywordsName" select="SEARCHDATA_PARAMS/SEARCHDATA_FIELDS/SEARCHDATA_FIELD/name" />
      <xsl:variable name="keywords" select="SEARCHDATA_PARAMS/SEARCHDATA_FIELDS/SEARCHDATA_FIELD/value" />

      <xsl:variable name="lengthName" select="SEARCHDATA_PARAMS/SEARCHDATA_LENGTH/name" />
      <xsl:variable name="length" select="SEARCHDATA_PARAMS/SEARCHDATA_LENGTH/value" />

      <xsl:variable name="orderName" select="SEARCHDATA_PARAMS/SEARCHDATA_ORDER/name" />
      <xsl:variable name="order" select="SEARCHDATA_PARAMS/SEARCHDATA_ORDER/value" />

      <xsl:variable name="sortByName" select="SEARCHDATA_PARAMS/SEARCHDATA_SORTBY/name" />
      <xsl:variable name="sortBy" select="SEARCHDATA_PARAMS/SEARCHDATA_SORTBY/value" />

      <xsl:for-each select="INVENTORY" >      

      <xsl:variable name="currentPageName" select="SEARCHDATA_PAGE/name" />
      <xsl:variable name="currentPage" select="SEARCHDATA_PAGE/value" />

      <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" /> 
      <xsl:variable name="totalNumberOfPages" select="SEARCHDATA_TOTALNUMBEROFPAGES/value" />
      
<xsl:if test="not($totalNumberOfPages)" >
<div class="mainHeading">
Search Error.
</div>
</xsl:if>
      
<xsl:if test="$totalNumberOfPages=0" >
<div class="mainHeading">
0 Search Results. Please Try Again.
</div>
</xsl:if>

<xsl:if test="$totalNumberOfPages>=1" >

<div class="mainHeading">
<p>Found ~<xsl:value-of select="$totalNumberOfItems" disable-output-escaping="yes" /> Products</p>
<div class="main">

<xsl:call-template name="pageSearchLinksTable" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" ></xsl:with-param>
   <xsl:with-param name="keywordsName" >
      <xsl:value-of select="$keywordsName" />
   </xsl:with-param>
   <xsl:with-param name="keywords" >
      <xsl:value-of select="$keywords" />
   </xsl:with-param>
   <xsl:with-param name="lengthName" >
      <xsl:value-of select="$lengthName" />
   </xsl:with-param>
   <xsl:with-param name="length" >
      <xsl:value-of select="$length" />
   </xsl:with-param>
   <xsl:with-param name="orderName" >
      <xsl:value-of select="$orderName" />
   </xsl:with-param>
   <xsl:with-param name="order" >
      <xsl:value-of select="$order" />
   </xsl:with-param>
   <xsl:with-param name="sortByName" >
      <xsl:value-of select="$sortByName" />
   </xsl:with-param>
   <xsl:with-param name="sortBy" >
      <xsl:value-of select="$sortBy" />
   </xsl:with-param>   
</xsl:call-template>

<xsl:call-template name="basicItems" >
   <xsl:with-param name="storeCurrentHostName" >
      <xsl:value-of select="$storeCurrentHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHostNamePath" >
      <xsl:value-of select="$storeCurrentHostNamePath" />
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="pageSearchLinksTable" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" >2</xsl:with-param>
   <xsl:with-param name="keywordsName" >
      <xsl:value-of select="$keywordsName" />
   </xsl:with-param>
   <xsl:with-param name="keywords" >
      <xsl:value-of select="$keywords" />
   </xsl:with-param>
   <xsl:with-param name="lengthName" >
      <xsl:value-of select="$lengthName" />
   </xsl:with-param>
   <xsl:with-param name="length" >
      <xsl:value-of select="$length" />
   </xsl:with-param>
   <xsl:with-param name="orderName" >
      <xsl:value-of select="$orderName" />
   </xsl:with-param>
   <xsl:with-param name="order" >
      <xsl:value-of select="$order" />
   </xsl:with-param>
   <xsl:with-param name="sortByName" >
      <xsl:value-of select="$sortByName" />
   </xsl:with-param>
   <xsl:with-param name="sortBy" >
      <xsl:value-of select="$sortBy" />
   </xsl:with-param>   
</xsl:call-template>

</div>
</div>
</xsl:if>

         </xsl:for-each>
      </xsl:for-each>
   </xsl:for-each>

<br />
<br />
   </xsl:template>
</xsl:stylesheet> 
