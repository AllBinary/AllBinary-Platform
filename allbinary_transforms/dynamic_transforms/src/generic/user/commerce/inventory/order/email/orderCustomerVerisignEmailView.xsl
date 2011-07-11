<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<!--
Basket Text Summary
-->
   <xsl:template name="basket" >
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal/ORDERHISTORY" >
      
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />

Product&#160;Name&#160;Number&#160;Cost&#160;Total

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
            
<xsl:value-of select="$summaryValue" disable-output-escaping="yes" />&#160;
<xsl:value-of select="$numberValue" />&#160;
USD <xsl:value-of select="$priceValue" />&#160;
<xsl:value-of select="$totalValue" />&#13;

            </xsl:for-each>
         </xsl:for-each>    

      </xsl:for-each>
   </xsl:template>

<!--
Paypal OrderShippingAddress
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="shippingAddress" >
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
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

<xsl:value-of select="$addressName" />&#13;
<xsl:value-of select="$addressStreet" />&#13;
<xsl:value-of select="$addressCity" />,&#160;<xsl:value-of select="$addressState" />&#160;&#160;<xsl:value-of select="$addressCode" />&#13;
<xsl:value-of select="$addressCountry" />&#13;

      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>                  
   </xsl:template>

<!--
Paypal OrderShippingSummary
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="shippingSummary" >
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal/ORDERHISTORY" >
               
         <xsl:variable name="defaultShippingMethod" select="DEFAULT/value" />
         
         <xsl:variable name="subTotal" select="ORDERHISTORY_SUB_TOTAL/value" />
         <xsl:variable name="shippingCost" select="ORDERHISTORY_SHIPPING_COST/value" />
         <xsl:variable name="tax" select="ORDERHISTORY_TAX/value" />
         <xsl:variable name="total" select="ORDERHISTORY_TOTAL/value" />

Sub Total:&#160;<xsl:value-of select="$subTotal" />&#13;
Shipping:&#160;<xsl:value-of select="$shippingCost" />&#13;
Tax:&#160;<xsl:value-of select="$tax" />&#13;
Total:&#160;<xsl:value-of select="$total" />&#13;

      </xsl:for-each>
   </xsl:template>

<!--
Order Payment Method
-->
   <xsl:template name="orderVerisignPaymentView" >
         
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Verisign_Payflow_Pro/ORDERHISTORY" >
               
         <xsl:for-each select="PAYMENT" >

         <xsl:variable name="paymentNameName" select="PAYMENT_NAME/name" />
         <xsl:variable name="paymentName" select="PAYMENT_NAME/value" />
         <xsl:variable name="paymentTypeName" select="PAYMENT_TYPE/name" />
         <xsl:variable name="paymentType" select="PAYMENT_TYPE/value" />
         <xsl:variable name="paymentExpirationName" select="PAYMENT_EXPIRATION/name" />
         <xsl:variable name="paymentExpiration" select="PAYMENT_EXPIRATION/value" />
         <xsl:variable name="paymentLastFourName" select="PAYMENT_NUMBER/name" />
         <xsl:variable name="paymentLastFour" select="PAYMENT_NUMBER/value" />
         <xsl:variable name="paymentTenderTypeName" select="PAYMENT_TENDERTYPE/name" />
         <xsl:variable name="paymentTenderType" select="PAYMENT_TENDERTYPE/value" />
         <xsl:variable name="paymentTransactionTypeName" select="PAYMENT_TRANSACTIONTYPE/name" />
         <xsl:variable name="paymentTransactionType" select="PAYMENT_TRANSACTIONTYPE/value" />
         <xsl:variable name="paymentAbaName" select="PAYMENT_ABA/name" />
         <xsl:variable name="paymentAba" select="PAYMENT_ABA/value" />
         <xsl:variable name="paymentAccountName" select="PAYMENT_ACCOUNT/name" />
         <xsl:variable name="paymentAccount" select="PAYMENT_ACCOUNT/value" />
         <xsl:variable name="paymentAccountTypeName" select="PAYMENT_ACCOUNTTYPE/name" />
         <xsl:variable name="paymentAccountType" select="PAYMENT_ACCOUNTTYPE/value" />
         <xsl:variable name="paymentAuthorizationCodeName" select="PAYMENT_AUTHORIZATIONCODE/name" />
         <xsl:variable name="paymentAuthorizationCode" select="PAYMENT_AUTHORIZATIONCODE/value" />
         <xsl:variable name="paymentCheckNumberName" select="PAYMENT_CHECKNUMBER/name" />
         <xsl:variable name="paymentCheckNumber" select="PAYMENT_CHECKNUMBER/value" />
         <xsl:variable name="paymentDriversLicenseName" select="PAYMENT_DRIVERSLICENSE/name" />
         <xsl:variable name="paymentDriversLicense" select="PAYMENT_DRIVERSLICENSE/value" />
         <xsl:variable name="paymentMagneticInkCheckReaderName" select="PAYMENT_MAGNETICINKCHECKREADER/name" />
         <xsl:variable name="paymentMagneticInkCheckReader" select="PAYMENT_MAGNETICINKCHECKREADER/value" />

<xsl:value-of select="$paymentName" />&#13;
<xsl:value-of select="$paymentType" />&#13;
<xsl:value-of select="$paymentLastFour" />&#13;
XXXXXXXXXXXX<xsl:value-of select="$paymentExpiration" />&#13;

         </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>                  
   </xsl:template>
         
   <xsl:output method="html"/>
   
      <xsl:template name="orderPaypalView" >
<!--
Paypal OrderView
-->
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal/ORDERHISTORY" >
               
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />

Order #: <xsl:value-of select="$orderId" />&#13;&#13;

Shipping Address:&#13;
<xsl:call-template name="shippingAddress" />&#13;

      <xsl:variable name="shippingNameName" select="ORDER/SHIPPINGMETHOD_NAME/name" />
      <xsl:variable name="shippingName" select="ORDER/SHIPPINGMETHOD_NAME/value" />
<xsl:value-of select="$shippingName" />&#13;
<xsl:call-template name="shippingSummary" />&#13;

<xsl:call-template name="basket" />&#13;

Please contact us if you have any questions. Thank You.
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>                  
   </xsl:template>
</xsl:stylesheet>