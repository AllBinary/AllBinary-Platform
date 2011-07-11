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
            //success += "Preprocessing: <br>";
            //success += "Shipped: <br>";
            //success += "Partially Shipped: <br>";
            //success += "Processing: <br>";
            //success += "Cancelled: <br>";            
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="html"/>
   <xsl:template match="/html" >

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeCurrentHomeHostNameName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/name" />
      <xsl:variable name="storeCurrentHomeHostName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/value" />

      <xsl:variable name="storeCurrentHomeHostNamePathName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHomeHostNamePath" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/value" />
            
      <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" />
      
      <xsl:variable name="totalNumberOfPages" select="SEARCHDATA_TOTALNUMBEROFPAGES/value" />
      
      <xsl:variable name="currentPageName" select="SEARCHDATA_PAGE/name" />
      <xsl:variable name="currentPage" select="SEARCHDATA_PAGE/value" />
      
      <xsl:variable name="keywordsName" select="SEARCHDATA_KEYWORDS/name" />
      <xsl:variable name="keywords" select="SEARCHDATA_KEYWORDS/value" />
      
      <xsl:variable name="lengthName" select="SEARCHDATA_LENGTH/name" />
      <xsl:variable name="length" select="SEARCHDATA_LENGTH/value" />

      <xsl:variable name="orderName" select="SEARCHDATA_ORDER/name" />
      <xsl:variable name="order" select="SEARCHDATA_ORDER/value" />
            
      <xsl:variable name="fieldName" select="SEARCHDATA_FIELD/name" />
      <xsl:variable name="field" select="SEARCHDATA_FIELD/value" />
      
      <xsl:variable name="sortByName" select="SEARCHDATA_SORTBY/name" />
      <xsl:variable name="sortBy" select="SEARCHDATA_SORTBY/value" />
      
      <xsl:variable name="typeName" select="SEARCHDATA_TYPE/name" />
      <xsl:variable name="type" select="SEARCHDATA_TYPE/value" />

      <xsl:variable name="categoryName" select="CATEGORY/name" />
      <xsl:variable name="category" select="CATEGORY/value" />      

<xsl:if test="$totalNumberOfPages=0" >
<div class="mainHeading">
0 Search Results. Please Try Again.
</div>
</xsl:if>

<xsl:if test="$totalNumberOfPages>=1" >
<div class="mainHeading">
<p>Found ~<xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> Products</p>
<div class="main">
      
<table class="main" >
<tr>
<td>
Pages: 
</td>
<xsl:if test="$currentPage>0" >
<td>
<form id="backOnePageForm" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}search.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage - 1}" />
<div ID="backOnePage" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('backOnePage','#999999');return true;"
   onMouseOut="changeBackgroundColor('backOnePage','#CCCCCC');return true;"
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
<form id="page{$nextPage}Form" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$searchPage}.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$nextPage}" />

<xsl:if test="$currentPage!=$nextPage" >
<div ID="page{$nextPage}" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('page{$nextPage}','#999999');return true;"
   onMouseOut="changeBackgroundColor('page{$nextPage}','#CCCCCC');return true;"
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
<form id="forwardOnePageForm" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}search.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage + 1}" />
<div ID="forwardOnePage" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('forwardOnePage','#999999');return true;"
   onMouseOut="changeBackgroundColor('forwardOnePage','#CCCCCC');return true;"
   onClick="submit('forwardOnePageForm')" >
&gt;
</div>
</form>
</td>
</xsl:if>

</tr>
</table>
      
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

<table class="main" border="1" cellpadding="3" cellspacing="2" width="500" >
<tr valign="top" >
<td rowspan="1" class="sans" width="252" >
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}summary.jsp?{$idName}={$idValue}" >
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />
</a>
</td>      
<td rowspan="2" valign="top">
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}summary.jsp?{$idName}={$idValue}" >
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$smallImageValue}" border="0" />      
</a><br clear="all"></br> 
</td>            
<td width="150" >
USD <xsl:value-of select="$priceValue" disable-output-escaping="yes" />
</td>
</tr>
<tr valign="top" >
<td width="252" >
<br></br>

<xsl:if test="$numberValue>0" >
In Stock<br></br>
Ships in 1-3 Days
</xsl:if>

<xsl:if test="$numberValue=0" >
Out Of Stock<br></br>
Backordered
</xsl:if>

</td>
<td width="120" >
<form method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}basket.jsp?AdminCommand=Add&#38;{$idName}={$idValue}&#38;{$inBasketName}={$inBasketValue}" >
<input type="submit" value="Buy" name="AdminCommand" />
</form>
</td></tr></table>
<br></br>

      </xsl:for-each>

<table class="main" >
<tr>
<td>
Pages:
</td>
<xsl:if test="$currentPage>0" >
<td>
<form id="backOnePageForm2" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}search.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage - 1}" />
<div ID="backOnePage" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('backOnePage','#999999');return true;"
   onMouseOut="changeBackgroundColor('backOnePage','#CCCCCC');return true;"
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
<form id="page{$nextPage}Form2" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$searchPage}.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$nextPage}" />

<xsl:if test="$currentPage!=$nextPage" >
<div ID="page{$nextPage}" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('page{$nextPage}','#999999');return true;"
   onMouseOut="changeBackgroundColor('page{$nextPage}','#CCCCCC');return true;"
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
<form id="forwardOnePageForm2" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}search.jsp" >
<input type="hidden" name="{$keywordsName}" value="{$keywords}" />
<input type="hidden" name="{$lengthName}" value="{$length}" />
<input type="hidden" name="{$orderName}" value="{$order}" />
<input type="hidden" name="{$fieldName}" value="{$field}" />
<input type="hidden" name="{$sortByName}" value="{$sortBy}" />
<input type="hidden" name="{$typeName}" value="{$type}" />
<input type="hidden" name="{$categoryName}" value="{$category}" />
<input type="hidden" name="{$currentPageName}" value="{$currentPage + 1}" />
<div ID="forwardOnePage" class="defaultButtonItem"
   onMouseOver="changeBackgroundColor('forwardOnePage','#999999');return true;"
   onMouseOut="changeBackgroundColor('forwardOnePage','#CCCCCC');return true;"
   onClick="submit('forwardOnePageForm2')" >
&gt;
</div>
</form>
</td>
</xsl:if>

</tr>
</table>

</div>
</div>
</xsl:if>

   </xsl:template>
</xsl:stylesheet> 
