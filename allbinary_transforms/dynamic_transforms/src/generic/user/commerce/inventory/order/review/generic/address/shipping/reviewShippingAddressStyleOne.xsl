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
Paypal Order ShippingAddress
Costing summary for order based of selected shipping method
-->   
   <xsl:template name="reviewShippingAddress" >
   
      <xsl:variable name="currencyMarker" select="'$'" />
      
      <xsl:for-each select="/html" >
         <xsl:for-each select="en" >
            <xsl:for-each select="US" >
               <xsl:for-each select="Paypal_Basic/ORDERHISTORY/SHIPPINGADDRESS/STREETADDRESS_ADDRESS" >
               
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

<table class="table" cellpadding="1" >
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
      
               </xsl:for-each>
            </xsl:for-each>
         </xsl:for-each>
      
   </xsl:template>

</xsl:stylesheet>
