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

<xsl:template name="radioForm" >
   
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

      <xsl:variable name="ADDSHIPPINGADDRESS" select="ADDSHIPPINGADDRESS/value" />
      <xsl:variable name="SETTOBILLINGADDRESS" select="SETTOBILLINGADDRESS/value" />
      <xsl:variable name="SELECTSHIPPINGADDRESS" select="SELECTSHIPPINGADDRESS/value" />
      <xsl:variable name="REMOVESHIPPINGADDRESS" select="REMOVESHIPPINGADDRESS/value" />
      <xsl:variable name="MAKENEWSHIPPINGADDRESS" select="MAKENEWSHIPPINGADDRESS/value" />

<!--
If address(es) where found display radio form
-->
      <xsl:for-each select="SHIPPINGADDRESSES" >
      
         <xsl:variable name="numberOfShippingAddresses" select="STREETADDRESS_NUMBEROFADDRESSES/value" />
         
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
            
            <xsl:if test="$addressDefault!=$addressDefaultName" >
<input type="radio" name="INDEX" value="{$addressId}" />
            </xsl:if>
            <xsl:if test="$addressDefault=$addressDefaultName" >
<input type="radio" name="INDEX" value="{$addressId}" checked="true" />
            </xsl:if>
      
<xsl:value-of select="$addressName" />
<br></br>&#160;&#160;&#160;&#160;&#160;<xsl:value-of select="$addressStreet" />
<br></br>&#160;&#160;&#160;&#160;&#160;<xsl:value-of select="$addressCity" />, <xsl:value-of select="$addressState" />&#160;&#160;<xsl:value-of select="$addressCode" />
<br></br>&#160;&#160;&#160;&#160;&#160;<xsl:value-of select="$addressCountry" />
<br></br>
<br></br>
         </xsl:for-each>

         <xsl:if test="$numberOfShippingAddresses!=0" >
<br></br>

   <xsl:call-template name="hiddenSubmitCommand" />
   
<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$SELECTSHIPPINGADDRESS" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$SELECTSHIPPINGADDRESS" />
   </xsl:with-param>
</xsl:call-template>

<br></br>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$REMOVESHIPPINGADDRESS" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$REMOVESHIPPINGADDRESS" />
   </xsl:with-param>
</xsl:call-template>

<br></br>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$MAKENEWSHIPPINGADDRESS" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$MAKENEWSHIPPINGADDRESS" />
   </xsl:with-param>
</xsl:call-template>
<br></br>
         </xsl:if>
      </xsl:for-each>
</xsl:for-each>

</xsl:for-each>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet> 
