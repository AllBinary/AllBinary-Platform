<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page"
>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/template/generic/bodies/imports/jsp/forward.xsl" />
   <xsl:import href="/template/generic/buttons/buttons.xsl" />

   <xsl:output method="xml" indent="yes" />

<xsl:template name="defaultBody" 
   xmlns:jsp="http://java.sun.com/JSP/Page"
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
              xmlns:payment="/WEB-INF/payment.tld"
>

<jsp:scriptlet>
   session.setAttribute(PaymentGatewayData.NAME.toString(), "Paypal_Basic");
</jsp:scriptlet>

<payment:gatewayView
   command="%= GLOBALS.VIEW %" 
   xsl="%= MAKEPAYMENTVIEWXSL %" >
</payment:gatewayView>

</xsl:template>

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                     xmlns:payment="/WEB-INF/payment.tld"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<ecommerce:authentication 
   command="%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %" 
   roles="%= roles %" >
   
   <xsl:call-template name="jspForward">
      <xsl:with-param name="PAGE">
         FORWARDLOGOUTPAGE
      </xsl:with-param>
      <xsl:with-param name="FORWARDPAGE">
         paymentGatewayPageData.FORWARDMAKEPAYMENT
      </xsl:with-param>
      <xsl:with-param name="FORWARDNAME">
         BODYLOGINNAME
      </xsl:with-param>
      <xsl:with-param name="FORWARDVALUE">
         LOGIN
      </xsl:with-param>
   </xsl:call-template>
   
</ecommerce:authentication>

         <xsl:variable name="defaultBodyHeading" select="'Payments'" />

         <xsl:for-each select="BODY_NAME" >

            <xsl:variable name="titleTextName" select="TITLE_NAME/TITLE_TEXT/name" />
            <xsl:variable name="titleTextValue" select="TITLE_NAME/TITLE_TEXT/value" />
            <xsl:variable name="titleTextLen" select="string-length(normalize-space($titleTextValue))" />

            <xsl:variable name="bodyDataName" select="name" />
            <xsl:variable name="bodyDataValue" select="value" />
            <xsl:variable name="bodyDataLen" select="string-length(normalize-space($bodyDataValue))" />

                     <xsl:if test="$titleTextLen = 0" >

                        <xsl:if test="$bodyDataLen = 0" >
<div class="mainHeading">
                        <p><xsl:value-of select="$defaultBodyHeading" /></p>
   <div class="main">
      <xsl:call-template name="defaultBody" />
   </div>
      
</div>
                        </xsl:if>

                        <xsl:if test="$bodyDataLen != 0" >
                           <xsl:value-of select="$bodyDataValue" />
                        </xsl:if>
                        
                     </xsl:if>
                     
                     <xsl:if test="$titleTextLen != 0" >
                        
                        <xsl:if test="$bodyDataLen != 0" >
                     
<div class="mainHeading">
                        <p><xsl:value-of select="$titleTextValue" /></p>

                        <xsl:value-of select="$bodyDataValue" />
</div>
                        </xsl:if>

                        <xsl:if test="$bodyDataLen = 0" >
<div class="mainHeading">
                        <p><xsl:value-of select="$titleTextValue" /></p>
   <div class="main">
      <xsl:call-template name="defaultBody" />
   </div>
      
</div>
                        </xsl:if>

                     </xsl:if>
                     
         </xsl:for-each>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>