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
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
>
<ecommerce:authentication 
   command="%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %" 
   roles="%= roles %" >
   <jsp:forward page="%= FORWARDLOGOUTPAGE %" />
</ecommerce:authentication>

<ecommerce:quoterequest 
   command="EMAIL"
   isSelected="true" 
   storeName="%= STORENAME %" >
Thank You For Your Business.
</ecommerce:quoterequest >   

</xsl:template>

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                     version="1.2">

         <xsl:variable name="defaultBodyHeading" select="'Quote Request Complete'" />
      
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