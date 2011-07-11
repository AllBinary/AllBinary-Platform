<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!--
Basket Table Summary - from stored item entity
-->
   <xsl:template name="basket" >
      <xsl:for-each select="Paypal/ORDERHISTORY" >
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

<td> X <xsl:value-of select="$priceValue" /></td>

<td> = USD <xsl:value-of select="$totalValue" /></td>
</tr>
            </xsl:for-each>
         </xsl:for-each>    
</table>
      </xsl:for-each>
   </xsl:template>

<!--
Paypal OrderShippingAddress
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="shippingAddress" >
      <xsl:for-each select="Paypal/ORDERHISTORY/SHIPPINGADDRESS/STREETADDRESS_ADDRESS" >
   
            <xsl:variable name="addressDefaultName" select="STREETADDRESS_DEFAULT_ADDRESS/name" />
            <xsl:variable name="addressDefault" select="STREETADDRESS_DEFAULT_ADDRESS/value" />
         
            <xsl:variable name="addressIdName" select="STREETADDRESS_ID/name" />
            <xsl:variable name="addressId" select="STREETADDRESS_ID/value" />
         
            <xsl:variable name="addressNameName" select="STREETADDRESS_NAME/name" />
            <xsl:variable name="addressName" select="STREETADDRESS_NAME/value" />
         
            <xsl:variable name="addressStreetName" select="STREETADDRESS_STREET/name" />
            <xsl:variable name="addressStreet" select="STREETADDRESS_STREET/value" />
         
            <xsl:variable name="addressCityName" select="STREETADDRESS_CITY/name" />
            <xsl:variable name="addressCity" select="STREETADDRESS_CITY/value" />
         
            <xsl:variable name="addressStateName" select="STREETADDRESS_STATE/name" />
            <xsl:variable name="addressState" select="STREETADDRESS_STATE/value" />
         
            <xsl:variable name="addressCodeName" select="STREETADDRESS_CODE/name" />
            <xsl:variable name="addressCode" select="STREETADDRESS_CODE/value" />
         
            <xsl:variable name="addressCountryName" select="STREETADDRESS_COUNTRY/name" />
            <xsl:variable name="addressCountry" select="STREETADDRESS_COUNTRY/value" />

<table class="table" cellpadding="1" width="100%" >
<tr>
<td  height="20" >
<xsl:value-of select="$addressName" />
</td>
</tr>
<tr>
<td  height="20" >
<xsl:value-of select="$addressStreet" />
</td>
</tr>
<tr>
<td  height="20" >
<xsl:value-of select="$addressCity" />,&#160;<xsl:value-of select="$addressState" />&#160;&#160;<xsl:value-of select="$addressCode" />
</td>
</tr>
<tr>
<td  height="20" >
<xsl:value-of select="$addressCountry" />
</td>
</tr>
</table>
      </xsl:for-each>
   </xsl:template>

<!--
Paypal OrderShippingSummary
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="shippingSummary" >
      <xsl:for-each select="Paypal/ORDERHISTORY" >    
         <xsl:variable name="defaultShippingMethod" select="DEFAULT/value" />
         
         <xsl:variable name="subTotal" select="ORDERHISTORY_SUB_TOTAL/value" />
         <xsl:variable name="shippingCost" select="ORDERHISTORY_SHIPPING_COST/value" />
         <xsl:variable name="tax" select="ORDERHISTORY_TAX/value" />
         <xsl:variable name="total" select="ORDERHISTORY_TOTAL/value" />
         
<table class="table" cellpadding="1" width="100%" >
<tr>
<td  height="20" >
Sub Total: 
</td>
<td  height="20" >
<xsl:value-of select="$subTotal" />
</td>
</tr>

<tr>
<td  height="20" >
Shipping: 
</td>
<td  height="20" >
<xsl:value-of select="$shippingCost" />
</td>
</tr>

<tr>
<td  height="20" >
Tax: 
</td>
<td  height="20" >
<xsl:value-of select="$tax" />
</td>
</tr>

<tr>
<td  height="20" >
Total: 
</td>
<td  height="20" >
<xsl:value-of select="$total" />
</td>
</tr>
</table>
      </xsl:for-each>
   </xsl:template>
   
   <xsl:output method="html"/>
   
      <xsl:template name="orderPaypalView" >
<!--
Paypal OrderView
-->
      <xsl:for-each select="Paypal/ORDERHISTORY" >
      
         <xsl:variable name="orderStatus" select="ORDERHISTORY_STATUS/value" />
      
         <xsl:variable name="orderShippedDate" select="ORDERHISTORY_SHIPPED_DATE/value" />
         <xsl:variable name="orderDate" select="ORDERHISTORY_ORDER_DATE/value" />
         <xsl:variable name="orderTransactionDate" select="ORDERHISTORY_TRANS_DATE/value" />
         <xsl:variable name="orderCancelDate" select="ORDERHISTORY_CANCEL_DATE/value" />         

         <xsl:variable name="orderShippedDateFormatted" select="ORDERHISTORY_SHIPPED_DATE_FORMATTED/value" />
         <xsl:variable name="orderDateFormatted" select="ORDERHISTORY_ORDER_DATE_FORMATTED/value" />
         <xsl:variable name="orderTransactionDateFormatted" select="ORDERHISTORY_TRANS_DATE_FORMATTED/value" />
         <xsl:variable name="orderCancelDateFormatted" select="ORDERHISTORY_CANCEL_DATE_FORMATTED/value" />         
               
         <xsl:variable name="userName" select="CUSTOMER_USER_NAME/value" />
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />

Order #: <xsl:value-of select="$orderId" />
<p></p>

<input type="hidden" name="SHIPPINGMETHOD_GROUP" size="30" value="0">
<input type="hidden" name="$orderIdName" value="$orderId" />

<table class="table" border="1" cellpadding="1" >
<tr>
<td>

<table class="table" border="1" width="100%" >

<tr>
<td height="20" >
User Name:
</td>
<td height="20" >
<xsl:value-of select="$userName" />
</td>
</tr>

<xsl:if test="$orderStatus">
<tr>
<td >
Status:
</td>
<td >
<xsl:value-of select="$orderStatus" /> 
</td>
</tr>
</xsl:if>

<xsl:if test="$orderDate!='0'">
<tr>
<td >
Ordered:
</td>
<td >
<xsl:value-of select="$orderDateFormatted" /> 
</td>
</tr>
</xsl:if>

<xsl:if test="$orderShippedDate!='0'">
<tr>
<td >
Shipped:
</td>
<td >
<xsl:value-of select="$orderShippedDateFormatted" /> 
</td>
</tr>
</xsl:if>

<xsl:if test="$orderTransactionDate!='0'">
<tr>
<td >
Transaction:
</td>
<td >
<xsl:value-of select="$orderTransactionDateFormatted" /> 
</td>
</tr>
</xsl:if>

<xsl:if test="$orderCancelDate!='0'">
<tr>
<td >
Cancel:
</td>
<td >
<xsl:value-of select="$orderCancelDateFormatted" /> 
</td>
</tr>
</xsl:if>

</table>

<table class="table" border="1" width="100%" >
<tr>
<td height="20" >
Shipping Address
</td>
         <xsl:variable name="shippingNameName" select="ORDER/SHIPPINGMETHOD_NAME/name" />
         <xsl:variable name="shippingName" select="ORDER/SHIPPINGMETHOD_NAME/value" />
<td height="20" >
<xsl:value-of select="$shippingName" />
</td>
</tr>

<tr>
<td>
<xsl:call-template name="shippingAddress" />
</td>
<td>
<xsl:call-template name="shippingSummary" />
</td>
</tr>

</table>

</td>
</tr>

<tr>
<td>
<xsl:call-template name="basket" />
</td>
</tr>
</table>

      </xsl:for-each>
   </xsl:template>
</xsl:stylesheet>