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

   <xsl:import href="/generic/user/address/shipping/shippingAddressInputForm.xsl" />
   <xsl:import href="/generic/user/address/shipping/shippingAddressRadioForm.xsl" />

   <xsl:output method="html"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

   <xsl:call-template name="radioForm" />
   
<!--
If No addresses or add new was selected where found display a blank form
-->      
      <xsl:for-each select="SHIPPINGADDRESSES" >

         <xsl:variable name="numberOfAddresses" select="STREETADDRESS_NUMBEROFADDRESSES/value" /> 
                  
         <xsl:call-template name="inputForm" />
         
      </xsl:for-each>
      
      </xsl:for-each>
      </xsl:for-each>            

   </xsl:template>
</xsl:stylesheet> 
