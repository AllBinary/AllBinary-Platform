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

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">

<OBJECTCONFIG_NAME OBJECTCONFIG_NAME="Payment Gateway Processing" >

   <TRANSFORM_COMPONENTS_NAME TRANSFORM_COMPONENTS_GROUP="TRANSFORM_COMPONENTS_ALL" >

      <COMPONENT_NAME TRANSFORM_INFO_NAME="$STOREFRONT_NAME {$PAYMENTGATEWAY_NAME} StartCheckout" >
      </COMPONENT_NAME>
      <COMPONENT_NAME TRANSFORM_INFO_NAME="$STOREFRONT_NAME {$PAYMENTGATEWAY_NAME} Shipping" >
      </COMPONENT_NAME>
      <COMPONENT_NAME TRANSFORM_INFO_NAME="$STOREFRONT_NAME {$PAYMENTGATEWAY_NAME} ShippingAddress" >
      </COMPONENT_NAME>
      <COMPONENT_NAME TRANSFORM_INFO_NAME="$STOREFRONT_NAME {$PAYMENTGATEWAY_NAME} ShippingAddressAction" >
      </COMPONENT_NAME>
      <COMPONENT_NAME TRANSFORM_INFO_NAME="$STOREFRONT_NAME {$PAYMENTGATEWAY_NAME} Checkout" >
      </COMPONENT_NAME>

   </TRANSFORM_COMPONENTS_NAME>

</OBJECTCONFIG_NAME>

    </xsl:template>

</xsl:stylesheet>
