<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page"
>
   
   <xsl:output method="xml" indent="yes" />
   
<xsl:template name="defaultBody" 
   xmlns:jsp="http://java.sun.com/JSP/Page"
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
              xmlns:payment="/WEB-INF/payment.tld"
>
   
<jsp:scriplet>
   String id = request.getParameter(OrderData.ID);
</jsp:scriplet>

<jsp:scriplet>
 // Check to see if basket is empty. if it is process body of tag 
</jsp:scriplet>
 
<ecommerce:basket command="%= BasketData.ISEMPTY %"  
   storeName="%= STORENAME %" >   
   <jsp:forward page="%= FORWARDEMPTYBASKETPAGE %" />
</ecommerce:basket>

<jsp:scriplet>
   if(StringUtil.isEmpty(paymentMethod))
   {
</jsp:scriplet>
   <jsp:forward page="%= FORWARDPAYMENTERRORPAGE %" />
<jsp:scriplet>
   }
</jsp:scriplet>

<jsp:scriplet>
   String orderId = request.getParameter("ORDER_ID");   
</jsp:scriplet>

<payment:process
   command="%= GLOBALS.AUTHORIZEORDERANDEVALBODY %"    
   storeName="%= STORENAME %" 
   xsl="%= VERISIGNPAYFLOWPROVIEWXSL %" >   
   <jsp:forward page="%= paymentGatewayPageData.FINISH %" >
      <jsp:param name="ORDER_ID" value="%= orderId %" />
   </jsp:forward>      
</payment:process>

<jsp:forward page="%= paymentGatewayPageData.PAYMENT %" >
   <jsp:param name="Repeat" value="Repeat" />
   <jsp:param name="AdminCommand" value="%= PaymentData.CHANGE %" />    
</jsp:forward>      

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
   <jsp:forward page="%= FORWARDLOGOUTPAGE %" />
</ecommerce:authentication>

         <xsl:variable name="defaultBodyHeading" select="'Error'" />

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