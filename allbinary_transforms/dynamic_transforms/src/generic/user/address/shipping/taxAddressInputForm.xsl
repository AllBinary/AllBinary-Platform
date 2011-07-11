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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template name="inputForm" >
                  
<xsl:for-each select="FORM" >
      
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
            <xsl:variable name="addressCountry" select="'USA'" />
            
<table class="table" cellpadding="5" >
<input type="hidden" name="{$addressIdName}" value="{$addressId}" />
<input type="hidden" name="{$addressNameName}" value="SHIPPINGADDRESS_TAX" />
<input type="hidden" name="{$addressStreetName}" value="" />
<input type="hidden" name="{$addressCityName}" value="" />
<input type="hidden" name="{$addressCodeName}" value="" />

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
            
      <xsl:for-each select="STREETADDRESS_STATE" >
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
Country: 
</td>
<td width="141" height="22" >
<input class="text" type="text" name="{$addressCountryName}" value="{$addressCountry}" />
</td>
</tr>
</table>

      </xsl:for-each>
   </xsl:for-each>
      
            </xsl:template>
</xsl:stylesheet> 
