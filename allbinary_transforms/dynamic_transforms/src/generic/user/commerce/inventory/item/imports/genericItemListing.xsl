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
   
   <xsl:template name="basicItems" >
      <xsl:param name="storeCurrentHostName" />
      <xsl:param name="storeCurrentHostNamePath" />

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

<table class="table" border="1" cellpadding="3" cellspacing="2" width="100%" >
<tr valign="top" >
<td rowspan="1" class="productSummary" width="100%" >
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$summaryPage}?{$idName}={$idValue}" >
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />
</a>
</td>      
<td rowspan="2" valign="top">
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$summaryPage}?{$idName}={$idValue}" >
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$smallImageValue}" border="0" />      
</a><br clear="all"></br> 
</td>            
<td>
USD <xsl:value-of select="$priceValue" disable-output-escaping="yes" />
</td>
</tr>
<tr valign="top" >
<td>
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
<td>

<xsl:if test="$numberValue>0" >
<form name="Buy{$idValue}" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$basketPage}?AdminCommand=BASKET_INSERT&#38;{$idName}={$idValue}&#38;{$inBasketName}={$inBasketValue}" >

   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      Buy<xsl:value-of select="$idValue" />
   </xsl:with-param>
   <xsl:with-param name="value">
      Buy
   </xsl:with-param>
   <xsl:with-param name="command">
      Buy
   </xsl:with-param>
</xsl:call-template>

</form>
</xsl:if>


<xsl:if test="$numberValue=0" >
Unavailable
</xsl:if>

</td></tr></table>
<br></br>
      </xsl:for-each>

   </xsl:template>
</xsl:stylesheet>
