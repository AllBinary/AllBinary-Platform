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
<div class="main">
No Items To List.
</div>
</xsl:if>

<p>Found ~<xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> Products</p>
<div class="main">

<table class="table" border="1" cellpadding="0" cellspacing="0" width="550" >

         <tr valign="top" >

<td>
<b>Summary</b>
</td>      

<td>
<b>In Stock</b>
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
<a href="productManager.jsp?AdminCommand=Edit&#38;{$idName}={$idValue}" >
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />
</a>
</td>      

<td>
<xsl:value-of select="$numberValue" disable-output-escaping="yes" />
</td>

<td>
<xsl:value-of select="$priceValue" disable-output-escaping="yes" />
</td>

</tr>

      </xsl:for-each>
</table>
<br></br>

</div>

   </xsl:for-each>
   </xsl:for-each>
   </xsl:for-each>      
   
   </xsl:template>
</xsl:stylesheet> 
