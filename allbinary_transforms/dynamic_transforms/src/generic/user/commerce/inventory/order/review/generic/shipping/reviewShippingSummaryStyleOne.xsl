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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" >
    <xsl:output method="html"/>

<!--
Paypal OrderShippingSummary
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="reviewShippingSummary" >

      <xsl:variable name="currencyMarker" select="'$'" />

      <xsl:for-each select="/html" >    
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal_Basic/ORDERHISTORY" >    

         <xsl:variable name="defaultShippingMethod" select="DEFAULT/value" />
         
         <xsl:variable name="subTotal" select="ORDERHISTORY_SUB_TOTAL/value" />
         <xsl:variable name="shippingCost" select="ORDERHISTORY_SHIPPING_COST/value" />
         <xsl:variable name="tax" select="ORDERHISTORY_TAX/value" />
         <xsl:variable name="total" select="ORDERHISTORY_TOTAL/value" />

<table class="table" cellpadding="1" >
<tr>
<td  height="20" >
Sub Total: 
</td>
<td  height="20" >
<xsl:value-of select="$currencyMarker" /><xsl:value-of select="$subTotal" />
</td>
</tr>

<tr>
<td  height="20" >
Shipping: 
</td>
<td  height="20" >
<xsl:value-of select="$currencyMarker" /><xsl:value-of select="$shippingCost" />
</td>
</tr>

<tr>
<td  height="20" >
Tax: 
</td>
<td  height="20" >
<xsl:value-of select="$currencyMarker" /><xsl:value-of select="$tax" />
</td>
</tr>

<tr>
<td  height="20" >
Total: 
</td>
<td  height="20" >
<xsl:value-of select="$currencyMarker" /><xsl:value-of select="$total" />
</td>
</tr>
</table>
                  </xsl:for-each>
               </xsl:for-each>
            </xsl:for-each>
         </xsl:for-each>
   </xsl:template>

</xsl:stylesheet>
