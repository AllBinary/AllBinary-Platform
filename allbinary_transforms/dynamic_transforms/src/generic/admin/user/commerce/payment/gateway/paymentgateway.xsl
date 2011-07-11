<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/generic/admin/user/commerce/payment/gateway/AuthorizeNet/AIM/AuthorizeNetAIMPaymentGateway.xsl" />
   <xsl:import href="/generic/admin/user/commerce/payment/gateway/AuthorizeNet/SIM/AuthorizeNetSIMPaymentGateway.xsl" />
   <xsl:import href="/generic/admin/user/commerce/payment/gateway/eProcessingNetwork/Emulation/AuthorizeNet/SIM/eProcessingNetworkEmulationAuthorizeNetSIMPaymentGateway.xsl" />
   <xsl:import href="/generic/admin/user/commerce/payment/gateway/Paypal/Basic/PaypalBasicPaymentGateway.xsl" />
   <xsl:import href="/generic/admin/user/commerce/payment/gateway/Verisign/PayflowPro/VerisignPayflowProPaymentGateway.xsl" />

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

            <xsl:for-each select="PAYMENTGATEWAY_NAME">

            <xsl:variable name="paymentGatewayName" select="PAYMENTGATEWAY_NAME" />

               <xsl:if test="$paymentGatewayName='AuthorizeNet_AIM'" >
                  <xsl:call-template name="AuthorizeNetAIMPaymentGateway" >
                     <xsl:with-param name="paymentGateway" >
                        <xsl:value-of select="current()" />
                     </xsl:with-param>
                  </xsl:call-template>
               </xsl:if>
            
               <xsl:if test="$paymentGatewayName='AuthorizeNet_SIM'" >
                  <xsl:call-template name="AuthorizeNetSIMPaymentGateway" >
                     <xsl:with-param name="paymentGateway" >
                        <xsl:value-of select="current()" />
                     </xsl:with-param>
                  </xsl:call-template>
               </xsl:if>

               <xsl:if test="$paymentGatewayName='eProcessingNetwork_Emulation_AuthorizeNet_SIM'" >
                  <xsl:call-template name="eProcessingNetworkEmulationAuthorizeNetSIMPaymentGateway" >
                     <xsl:with-param name="paymentGateway" >
                        <xsl:value-of select="current()" />
                     </xsl:with-param>
                  </xsl:call-template>
               </xsl:if>

               <xsl:if test="$paymentGatewayName='Paypal_Basic'" >
                  <xsl:call-template name="PaypalBasicPaymentGateway" >
                     <xsl:with-param name="paymentGateway" >
                        <xsl:value-of select="current()" />
                     </xsl:with-param>
                  </xsl:call-template>
               </xsl:if>
                              
               <xsl:if test="$paymentGatewayName='Verisign_Payflow_Pro'" >
                  <xsl:call-template name="VerisignPayflowProPaymentGateway" >
                     <xsl:with-param name="paymentGateway" >
                        <xsl:value-of select="current()" />
                     </xsl:with-param>
                  </xsl:call-template>
               </xsl:if>

               <xsl:for-each select="AuthorizeNet">
                   <xsl:for-each select="AIM">
                   </xsl:for-each>
                   <xsl:for-each select="SIM">
                   </xsl:for-each>
               </xsl:for-each>
               <xsl:for-each select="Paypal">
               </xsl:for-each>
               <xsl:for-each select="Verisign">
                  <xsl:for-each select="PayflowPro">
                  </xsl:for-each>
               </xsl:for-each>
               
            </xsl:for-each>

         </xsl:for-each>
      </xsl:for-each>

   </xsl:template>    
</xsl:stylesheet>
