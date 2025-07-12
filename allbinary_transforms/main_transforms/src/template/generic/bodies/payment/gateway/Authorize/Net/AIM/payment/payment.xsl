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

   <xsl:output method="xml" indent="yes" />

<xsl:template name="defaultBody" 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
>

<jsp:scriplet>
   String repeat = request.getParameter("Repeat");   
</jsp:scriplet>

<jsp:scriplet>
 //Check to see if basket is empty. if it is process body of tag 
</jsp:scriplet>

<ecommerce:basket command="%= BasketData.ISEMPTY %"    
   storeName="%= STORENAME %" >   
   <jsp:forward page="%= FORWARDEMPTYBASKETPAGE %" />
</ecommerce:basket>

<jsp:scriplet>
if(repeat!=null AND repeat.compareTo("Repeat")==0)
{
</jsp:scriplet>
Please verify your payment information below.  If the payment information is correct please try ordering again. Otherwise correct the payment information before trying again.<br/>
If you continue to have problems please contact us. Thank You.<p/>
<jsp:scriplet>
}
</jsp:scriplet>

<jutil:element name="form">
   <jutil:attribute name="name">   
      <jsp:expression></jsp:expression>      
   </jutil:attribute>
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="action">
      <jsp:expression>paymentGatewayPageData.PAYMENT</jsp:expression>
   </jutil:attribute>

<generic:payment 
   command="%= command %" 
   storeName="%= STORENAME %" >

   <payment:payment command="%= command %" isSelected="true" >
      <jsp:forward page="%= paymentGatewayPageData.SHIPPING %" />      
   </payment:payment>

   <generic:payment 
      command="%= PaymentData.CHANGE %" 
      storeName="%= STORENAME %" />

</generic:payment>

</jutil:element>

<p/>

<generic:basket command="%= GLOBALS.VIEW %"    
   storeName="%= STORENAME %" xsl="%= CHECKOUTBASKETXSL %" />

</xsl:template>
   
    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:jutil="/WEB-INF/jutil.tld"
                     xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                     xmlns:generic="/WEB-INF/generic.tld"
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
   <jsp:forward page="%= FORWARDLOGOUTPAGE %" />
</ecommerce:authentication>

         <xsl:variable name="defaultBodyHeading" select="'Step 3 of 6 - Payment Information'" />

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