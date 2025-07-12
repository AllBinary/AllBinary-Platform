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
   
   <xsl:import href="/template/generic/pages/imports/globals/pages.xsl" />
   
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />

   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         
      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeStaticPathName" select="STOREFRONT_NAME/STATICPATH/name" />
      <xsl:variable name="storeStaticPath" select="STOREFRONT_NAME/STATICPATH/value" />

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
      
         <xsl:variable name="descriptionValue" select="BASICITEM_DESCRIPTION/value" />
         <xsl:variable name="mediumImageValue" select="BASICITEM_MEDIUM_IMG/value" />
         <xsl:variable name="largeImageValue" select="BASICITEM_LARGE_IMG/value" />

<div class="mainHeading" ><b><xsl:value-of select="$summaryValue" disable-output-escaping="yes" /></b>

<div class="main" >

<p>
<table class="table" border="1" cellpadding="3" cellspacing="2" >
<tr valign="top" >
<td rowspan="1" class="productSummary" >
<a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$largeImageValue}" >
(Click here for larger product image)<br></br>
<img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$mediumImageValue}" border="0" />      
</a>
</td>
<td><b>Pricing:</b><p>
USD <xsl:value-of select="$priceValue" disable-output-escaping="yes"/>
 
<form name="Buy{$idValue}" method="POST" action="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$basketPage}?AdminCommand=BASKET_INSERT&#38;{$idName}={$idValue}" >
<input type="text" value="1" name="BASKET_ITEMTOTALINBASKET" size="4" />

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
</p>
<b>Shipping Info:</b><p>

<xsl:if test="$numberValue>0" >
In Stock<br></br>
Ships in 1-3 Days
</xsl:if>

<xsl:if test="$numberValue=0" >
Out Of Stock<br></br>
Backordered
</xsl:if>
</p>

</td>
</tr>
</table>
</p>
<p>
<b>Description:</b><br></br><br></br>
<xsl:value-of select="$descriptionValue" disable-output-escaping="yes"/>
</p>
</div>
</div>
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>            
   </xsl:template>
</xsl:stylesheet> 
