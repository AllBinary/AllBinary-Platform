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
      
      <xsl:variable name="ADDSHIPPINGADDRESS" select="ADDSHIPPINGADDRESS/value" />
      <xsl:variable name="SETSHIPPINGADDRESSTOBILLINGADDRESS" select="SETSHIPPINGADDRESSTOBILLINGADDRESS/value" />
      <xsl:variable name="numberOfShippingAddresses" select="SHIPPINGADDRESSES/STREETADDRESS_NUMBEROFADDRESSES" />

      <xsl:for-each select="SHIPPINGADDRESSES" >      
         <xsl:for-each select="STREETADDRESS_ADDRESS" >

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
            
               <xsl:if test="$addressDefault=$addressDefaultName" >
               
<xsl:value-of select="$addressName" />
<br></br><xsl:value-of select="$addressStreet" />
<br></br><xsl:value-of select="$addressCity" />, <xsl:value-of select="$addressState" />&#160;&#160;<xsl:value-of select="$addressCode" />
<br></br><xsl:value-of select="$addressCountry" />
<br></br>
               </xsl:if>
         
            </xsl:for-each>
         </xsl:for-each>
<!--
If the default addresses was not found display a blank form
-->
      <xsl:for-each select="SHIPPINGADDRESSES" >         
               
         <xsl:variable name="numberOfShippingAddresses" select="NUMBEROFADDRESSES/value" />         

         <xsl:if test="$numberOfShippingAddresses=0" >
         
            <xsl:for-each select="FORM/ADDRESS" >
            
            <xsl:variable name="addressDefaultName" select="DEFAULT_ADDRESS/name" />
            <xsl:variable name="addressDefault" select="DEFAULT_ADDRESS/value" />

            <xsl:variable name="addressIdName" select="ID/name" />
            <xsl:variable name="addressId" select="ID/value" />

            <xsl:variable name="addressNameName" select="NAME/name" />
            <xsl:variable name="addressName" select="NAME/value" />

            <xsl:variable name="addressStreetName" select="STREET/name" />
            <xsl:variable name="addressStreet" select="STREET/value" />

            <xsl:variable name="addressCityName" select="CITY/name" />
            <xsl:variable name="addressCity" select="CITY/value" />

            <xsl:variable name="addressStateName" select="STATE/name" />
            <xsl:variable name="addressState" select="STATE/value" />

            <xsl:variable name="addressCodeName" select="CODE/name" />
            <xsl:variable name="addressCode" select="CODE/value" />

            <xsl:variable name="addressCountryName" select="COUNTRY/name" />
            <xsl:variable name="addressCountry" select="COUNTRY/value" />

<table class="table" cellpadding="5">
<tr>
<td width="52" height="22" >
Name: 
</td>
<td width="141" height="22" >
<input type="text" name="{$addressNameName}" value="{$addressName}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
Street: 
</td>
<td width="141" height="22" >
<input type="text" name="{$addressStreetName}" value="{$addressStreet}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
City: 
</td>
<td width="141" height="22" >
<input type="text" name="{$addressCityName}" value="{$addressCity}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
State: 
</td>
<td width="141" height="22" >
<select size="1" name="{$addressStateName}" >
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
<input type="text" name="{$addressCodeName}" value="{$addressCode}" />
</td>
</tr>

<tr>
<td width="52" height="22" >
Country: 
</td>
<td width="141" height="22" >
<input type="text" name="{$addressCountryName}" value="{$addressCountry}" />
</td>
</tr>
</table>

   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$ADDSHIPPINGADDRESS" />      
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$ADDSHIPPINGADDRESS" />      
   </xsl:with-param>
</xsl:call-template>

<br></br>
<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$SETSHIPPINGADDRESSTOBILLINGADDRESS" />      
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$SETSHIPPINGADDRESSTOBILLINGADDRESS" />      
   </xsl:with-param>
</xsl:call-template>

            </xsl:for-each>
         </xsl:if>
      </xsl:for-each>

      </xsl:for-each>
      </xsl:for-each>                  
      
   </xsl:template>
</xsl:stylesheet> 
