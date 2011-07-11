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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

   <xsl:import href="/template/generic/pages/imports/globals/pages.xsl" />

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
        
      <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" />
      
      <xsl:variable name="totalNumberOfPages" select="SEARCHDATA_TOTALNUMBEROFPAGES/value" />
      
      <xsl:variable name="currentPageName" select="SEARCHDATA_PAGE/name" />
      <xsl:variable name="currentPage" select="SEARCHDATA_PAGE/value" />

<xsl:if test="not($totalNumberOfPages)" >
<div class="main">
Search Error.
</div>
</xsl:if>
      
<xsl:if test="$totalNumberOfPages=0" >
<div class="main">
0 Search Results. Please Try Again.
</div>
</xsl:if>

<xsl:if test="$totalNumberOfPages>=1" >
<p>Found ~<xsl:value-of select="$totalNumberOfItems" disable-output-escaping="yes" /> Products</p>
<div class="main">
      
<table class="table" >
<tr>
<td>
Pages: 
</td>
<xsl:if test="$currentPage>0" >
<td>
<form id="backOnePageForm" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('backOnePageForm')" >
&lt;
</div>
</form>
</td>
</xsl:if>

<xsl:for-each select="pageInfo" >
   <xsl:variable name="nextPage" select="name" />
   <xsl:variable name="searchPage" select="value" />
<td>
<form id="page{$nextPage}Form" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('page{$nextPage}Form')" >
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
<form id="forwardOnePageForm" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('forwardOnePageForm')" >
&gt;
</div>
</form>
</td>
</xsl:if>

</tr>
</table>

<table class="table" border="1" cellpadding="3" cellspacing="2" width="500" >

<tr valign="top" >
<td>
<b>Summary</b>
</td>      

<td>
<b>Image</b>
</td>

<td>
<b>Price</b>
</td>

</tr>
      
      <xsl:for-each select="BASICITEM" >
         <xsl:variable name="totalValue" select="BASKET_ITEMTOTAL/value" />
         <xsl:variable name="inBasketName" select="BASKET_ITEMTOTALINBASKET/name" />
         <xsl:variable name="inBasketValue" select="BASKET_ITEMTOTALINBASKET/value" />
                  
         <xsl:variable name="idName" select="BASICITEM_ID/name" />
         <xsl:variable name="idValue" select="BASICITEM_ID/value" />
         <xsl:variable name="numberValue" select="BASICITEM_NUMBER/value" />
         <xsl:variable name="summaryValue" select="BASICITEM_SUMMARY/value" />
         <xsl:variable name="categoryValue" select="BASICITEM_CATEGORY/value" />
         <xsl:variable name="smallImageValue" select="BASICITEM_SMALL_IMG/value" />
         <xsl:variable name="priceValue" select="BASICITEM_PRICE/value" />         

<tr valign="top" >

<td>
<a href="{$productManagerPage}?AdminCommand=Edit&#38;{$idName}={$idValue}" >
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />
</a>
</td>      

<td valign="top">
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$smallImageValue}" border="0" />
</td>

<td>
<xsl:value-of select="$priceValue" disable-output-escaping="yes" />
</td>

</tr>

      </xsl:for-each>
</table>
<br></br>

<table class="table" >
<tr>
<td>
Pages:
</td>
<xsl:if test="$currentPage>0" >
<td>
<form id="backOnePageForm2" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('backOnePageForm2')" >
&lt;
</div>
</form>
</td>
</xsl:if>

<xsl:for-each select="pageInfo" >
   <xsl:variable name="nextPage" select="name" />
   <xsl:variable name="searchPage" select="value" />
<td>
<form id="page{$nextPage}Form2" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('page{$nextPage}Form2')" >
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
<form id="forwardOnePageForm2" method="POST" action="{$productManagerPage}" >

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
   onClick="submit('forwardOnePageForm2')" >
&gt;
</div>
</form>
</td>
</xsl:if>

</tr>
</table>

</div>
</xsl:if>

   </xsl:for-each>
   </xsl:for-each>
   </xsl:for-each>      
<br />
<br />
   </xsl:template>
</xsl:stylesheet> 
