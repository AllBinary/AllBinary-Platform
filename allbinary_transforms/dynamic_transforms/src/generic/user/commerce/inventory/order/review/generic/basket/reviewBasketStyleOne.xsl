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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

<!--
Basket Table Summary - from stored item entity
-->
   <xsl:template name="reviewBasket" >
   
      <xsl:variable name="currencyMarker" select="'$'" />
      
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal_Basic/ORDERHISTORY" >
               
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />

<table border="1" class="table" width="100%" >
<tr><td>Product Name</td><td>Number</td><td>Cost</td><td>Total</td></tr>
         <xsl:for-each select="BASKET" >
            <xsl:for-each select="ITEM/BASICITEM" >
            <xsl:variable name="idName" select="BASICITEM_ID/name" />
            <xsl:variable name="idValue" select="BASICITEM_ID/value" />
            <xsl:variable name="numberValue" select="BASICITEM_NUMBER/value" />
            <xsl:variable name="summaryValue" select="BASICITEM_SUMMARY/value" />
            <xsl:variable name="categoryValue" select="BASICITEM_CATEGORY/value" />
            <xsl:variable name="smallImageValue" select="BASICITEM_SMALL_IMG/value" />
            <xsl:variable name="priceValue" select="BASICITEM_PRICE/value" />
            <xsl:variable name="totalValue" select="BASICITEM_TOTAL/value" />
      
            <xsl:variable name="descriptionValue" select="BASICITEM_DESCRIPTION/value" />
            <xsl:variable name="mediumImageValue" select="BASICITEM_MEDIUM_IMG/value" />
            <xsl:variable name="largeImageValue" select="BASICITEM_LARGE_IMG/value" />
<tr>
<td><xsl:value-of select="$summaryValue" disable-output-escaping="yes" /></td>

<td><xsl:value-of select="$numberValue" /></td>

<td> X <xsl:value-of select="$currencyMarker" /><xsl:value-of select="$priceValue" /></td>

<td> = <xsl:value-of select="$currencyMarker" /> <xsl:value-of select="$totalValue" /></td>
</tr>
            </xsl:for-each>
         </xsl:for-each>    
</table>

               </xsl:for-each>
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>
   </xsl:template>

</xsl:stylesheet>
