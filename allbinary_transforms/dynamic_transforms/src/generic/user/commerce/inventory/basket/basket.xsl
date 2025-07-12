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

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

   <xsl:import href="/generic/imports/buttons/buttons.xsl" />
   <xsl:import href="/generic/imports/buttons/links.xsl" />

   <xsl:import href="/template/generic/pages/imports/globals/pages.xsl" />
   
   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
      
      <xsl:for-each select="BASKET" >

         <xsl:variable name="basketSubTotal" select="BASKET_SUBTOTAL" />
         
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
<td rowspan="1" class="productSummary" width="500" >
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$summaryPage}?{$idName}={$idValue}" >
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />
</a>
</td>      
<td rowspan="2" valign="top" width="130">
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$summaryPage}?{$idName}={$idValue}" >
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$smallImageValue}" border="0" />      
</a><br clear="all"></br> 
</td>            
<td>
<form name="Adjust{$idValue}" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$basketPage}?AdminCommand=BASKET_ADJUST&#38;{$idName}={$idValue}" >
$<xsl:value-of select="$priceValue" disable-output-escaping="yes" />
Each X 
<input type="text" name="{$inBasketName}" size="4" value="{$inBasketValue}" />
=  $<xsl:value-of select="$totalValue" disable-output-escaping="yes"/>
<br></br>

   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      Adjust<xsl:value-of select="$idValue" />
   </xsl:with-param>
   <xsl:with-param name="value">
      Adjust
   </xsl:with-param>
   <xsl:with-param name="command">
      Adjust
   </xsl:with-param>
</xsl:call-template>

</form>
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
<form name="Remove{$idValue}" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$basketPage}?AdminCommand=BASKET_DELETE&#38;{$idName}={$idValue}" >
   
   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      Remove<xsl:value-of select="$idValue" />
   </xsl:with-param>
   <xsl:with-param name="value">
      Remove
   </xsl:with-param>
   <xsl:with-param name="command">
      Remove
   </xsl:with-param>
</xsl:call-template>

</form>
</td></tr></table>
<br></br>
      </xsl:for-each>

Sub Total:  <xsl:value-of select="$basketSubTotal" disable-output-escaping="yes"/>

            </xsl:for-each>

         </xsl:for-each>
      </xsl:for-each>
   </xsl:template>
</xsl:stylesheet> 
