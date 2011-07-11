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

      <xsl:for-each select="INVENTORY" >      
      
         <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" /> 

<xsl:if test="$totalNumberOfItems=0" >
<div class="mainHeading">
No Items To List.
</div>
</xsl:if>

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
         
<table class="table" border="1" cellpadding="3" cellspacing="2" width="500" >
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
<form method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}basket.jsp?AdminCommand=Adjust&#38;{$idName}={$idValue}" >
$ <xsl:value-of select="$priceValue" disable-output-escaping="yes" />
Each X 
<input type="text" name="NUM" size="4" value="{$inBasketValue}" />
=  <xsl:value-of select="$totalValue" disable-output-escaping="yes"/>
<br></br>
<input type="submit" value="Adjust" name="AdminCommand" />
</form>
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
<form method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}basket.jsp?AdminCommand=Remove&#38;{$idName}={$idValue}" >
<input type="submit" value="Remove" name="AdminCommand" />
</form>
</td></tr></table>
<br></br>
      </xsl:for-each>

<table class="table" >
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

</xsl:if>   

</xsl:for-each>
</xsl:for-each>
   
   </xsl:template>
</xsl:stylesheet> 
