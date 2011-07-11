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
   xmlns:jsp="http://java.sun.com/JSP/Page" >
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />

<xsl:template name="inputForm" >
                  
   <xsl:for-each select="/html" >
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

      <xsl:variable name="ADDBILLINGADDRESS" select="ADDBILLINGADDRESS/value" />
      <xsl:variable name="SETTOSHIPPINGADDRESS" select="SETTOSHIPPINGADDRESS/value" />
      <xsl:variable name="SELECTBILLINGADDRESS" select="SELECTBILLINGADDRESS/value" />
      <xsl:variable name="REMOVEBILLINGADDRESS" select="REMOVEBILLINGADDRESS/value" />
      <xsl:variable name="MAKENEWBILLINGADDRESS" select="MAKENEWBILLINGADDRESS/value" />

<xsl:for-each select="BILLINGADDRESSES" >
      
         <xsl:for-each select="FORM/STREETADDRESS_ADDRESS" >

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

<table class="table" cellpadding="5">
<tr>
<td width="52" height="22" >
Name: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressNameName}" value="{$addressName}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
Street: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressStreetName}" value="{$addressStreet}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
City: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressCityName}" value="{$addressCity}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
State: 
</td>
<td width="141" height="22" >
<select class="select" size="1" name="{$addressStateName}" >
   <xsl:for-each select="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >   
            <xsl:for-each select="STATES" >
            
      <xsl:for-each select="STATE" >
   <option><xsl:value-of select="value" /></option>
      </xsl:for-each>
      
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>      
   </xsl:for-each>
</select>
</td>
</tr>

<tr>
<td width="52" height="22" >
Code: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressCodeName}" value="{$addressCode}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
Country: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressCountryName}" value="{$addressCountry}" />
</td>
</tr>
</table>

   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$ADDBILLINGADDRESS" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$ADDBILLINGADDRESS" />
   </xsl:with-param>
</xsl:call-template>

<br></br>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$SETTOSHIPPINGADDRESS" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$SETTOSHIPPINGADDRESS" />      
   </xsl:with-param>
</xsl:call-template>

            </xsl:for-each>      
      </xsl:for-each>
</xsl:for-each>

         </xsl:for-each>
      </xsl:for-each>
   </xsl:template>               
</xsl:stylesheet> 
