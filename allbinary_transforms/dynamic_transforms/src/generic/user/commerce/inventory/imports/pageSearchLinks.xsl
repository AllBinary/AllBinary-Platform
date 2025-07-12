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
   
   <xsl:import href="/template/generic/pages/imports/globals/pages.xsl" />
   
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />

   <xsl:template name="pageSearchLinks" >
      <xsl:param name="storeCurrentHomeHostName" />
      <xsl:param name="storeCurrentHomeHostNamePath" />
      <xsl:param name="baseFileName" />
      <xsl:param name="idUnique" />
      <xsl:param name="keywordsName" />
      <xsl:param name="keywords" />
      <xsl:param name="lengthName" />
      <xsl:param name="length" />
      <xsl:param name="orderName" />
      <xsl:param name="order" />
      <xsl:param name="sortByName" />
      <xsl:param name="sortBy" />

      <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" />
      
      <xsl:variable name="totalNumberOfPages" select="SEARCHDATA_TOTALNUMBEROFPAGES/value" />
            
      <xsl:variable name="currentPageName" select="SEARCHDATA_PAGE/name" />
      <xsl:variable name="currentPage" select="SEARCHDATA_PAGE/value" />
      
<xsl:if test="$currentPage>0" >
<td>
<form id="backOnePageForm{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$realSearchPage}" >

<input type="hidden" name="COLUMN_NAME[0]" value="{$keywordsName}" />
<input type="hidden" name="COLUMN_VALUE[0]" value="{$keywords}" />

<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage - 1}" />
<input type="hidden" name="AdminCommand" value="Search" />

<div ID="backOnePage" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOut');return true;"
   onClick="submit('backOnePageForm{$idUnique}')" >
&lt;
</div>
</form>
</td>
</xsl:if>

<xsl:for-each select="pageInfo" >
   <xsl:variable name="nextPage" select="name" />
   <xsl:variable name="searchPage" select="value" />
<td>
<form id="page{$nextPage}Form{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$realSearchPage}" >

<input type="hidden" name="COLUMN_NAME[0]" value="{$keywordsName}" />
<input type="hidden" name="COLUMN_VALUE[0]" value="{$keywords}" />

<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$currentPageName}" value="{$nextPage}" />
<input type="hidden" name="AdminCommand" value="Search" />

<xsl:if test="$currentPage!=$nextPage" >
<div ID="page{$nextPage}" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('page{$nextPage}','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('page{$nextPage}','.pageLinkMouseOut');return true;"
   onClick="submit('page{$nextPage}Form{$idUnique}')" >
<xsl:value-of select="$nextPage+1" />
</div>
</xsl:if>

<xsl:if test="$currentPage=$nextPage" >
<div ID="page{$nextPage}" class="currentDefaultButtonItem" >
<xsl:value-of select="$nextPage+1" />
</div>
</xsl:if>

</form>
</td>
</xsl:for-each>

<xsl:if test="$totalNumberOfPages>$currentPage + 1" >
<td>
<form id="forwardOnePageForm{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$realSearchPage}" >

<input type="hidden" name="COLUMN_NAME[0]" value="{$keywordsName}" />
<input type="hidden" name="COLUMN_VALUE[0]" value="{$keywords}" />

<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage + 1}" />
<input type="hidden" name="AdminCommand" value="Search" />

<div ID="forwardOnePage" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('forwardOnePage','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('forwardOnePage','.pageLinkMouseOut');return true;"
   onClick="submit('forwardOnePageForm{$idUnique}')" >
&gt;
</div>
</form>
</td>
</xsl:if>

   </xsl:template>

   <xsl:template name="pageSearchLinksTable" >
      <xsl:param name="storeCurrentHomeHostName" />
      <xsl:param name="storeCurrentHomeHostNamePath" />
      <xsl:param name="baseFileName" />
      <xsl:param name="idUnique" />
      <xsl:param name="keywordsName" />
      <xsl:param name="keywords" />
      <xsl:param name="lengthName" />
      <xsl:param name="length" />
      <xsl:param name="orderName" />
      <xsl:param name="order" />
      <xsl:param name="sortByName" />
      <xsl:param name="sortBy" />
   
<table class="table" >
<tr>
<td>
Pages: 
</td>
<xsl:call-template name="pageSearchLinks" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" >
      <xsl:value-of select="$idUnique" />
   </xsl:with-param>
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
</tr>
</table>

   </xsl:template>
   
</xsl:stylesheet>
