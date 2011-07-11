<?xml version="1.0" encoding="UTF-8" ?>


<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

   <xsl:import href="/generic/user/commerce/inventory/order/review/generic/basket/reviewBasketStyleOne.xsl" />
   <xsl:import href="/generic/user/commerce/inventory/order/review/generic/address/shipping/reviewShippingAddressStyleOne.xsl" />
   <xsl:import href="/generic/user/commerce/inventory/order/review/generic/shipping/reviewShippingSummaryStyleOne.xsl" />

   <xsl:output method="html"/>
   
      <xsl:template name="orderPaypalView" >
      
      <xsl:variable name="currencyMarker" select="'$'" />
<!--
Paypal OrderView
-->
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal_Basic/ORDERHISTORY" >
               
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />

<input type="hidden" name="SHIPPINGMETHOD_GROUP" size="30" value="0" />
<input type="hidden" name="$orderIdName" value="$orderId" />

<table class="table" border="1" cellpadding="1" width="100%" >
<tr>
<td>

<table class="table" border="1" width="100%" >
<tr>
<td>
Order #: <xsl:value-of select="$orderId" />
</td>
</tr>
<tr>
<td>
<xsl:call-template name="reviewShippingSummary" />
</td>
</tr>
</table>

</td>
</tr>

<tr>
<td>
<xsl:call-template name="reviewBasket" />
</td>
</tr>
</table>

               </xsl:for-each>
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>
   </xsl:template>
</xsl:stylesheet>