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
>
   <xsl:param name="contactData" />
   
                     <xsl:for-each select="contactData" >

                              <xsl:variable name="contactName" select="CONTACT_NAME/value" />
                              <xsl:variable name="contactCity" select="CONTACT_CITY/value" />
                              <xsl:variable name="contactState" select="CONTACT_STATE/value" />
                              <xsl:variable name="contactZip" select="CONTACT_ZIP/value" />
                              <xsl:variable name="contactLocalPhoneNumber" select="CONTACT_LOCAL_PHONE/value" />
                              <xsl:variable name="contactPhoneNumber" select="CONTACT_PHONE/value" />
                              <xsl:variable name="contactSupportEmail" select="CONTACT_SUPPORT_EMAIL/value" />
                              <xsl:variable name="contactSalesEmail" select="CONTACT_SALES_EMAIL/value" />
                              <xsl:variable name="contactOtherEmail" select="CONTACT_OTHER_EMAIL/value" />
                              <xsl:variable name="contactMessenger" select="CONTACT_MESSENGER/value" />

<table border="1" class="main" >

<xsl:if test="string-length($contactSalesEmail) != 0" >
<tr>
<td>
Sales
</td>
<td>
<a href="mailto:{$contactSalesEmail}" ><xsl:value-of select="$contactSalesEmail" /></a>
</td>
</tr>
</xsl:if>

<xsl:if test="string-length($contactSupportEmail) != 0" >
<tr>
<td>
Support
</td>
<td>
<a href="mailto:{$contactSupportEmail}" ><xsl:value-of select="$contactSupportEmail" /></a>
</td>
</tr>
</xsl:if>

<xsl:if test="string-length($contactLocalPhoneNumber) != 0" >
<tr>
<td>
Local
</td>
<td>
<xsl:value-of select="$contactLocalPhoneNumber" />
</td>
</tr>
</xsl:if>

<xsl:if test="string-length($contactPhoneNumber) != 0" >
<tr>
<td>
Phone
</td>
<td>
<xsl:value-of select="$contactPhoneNumber" />
</td>
</tr>
</xsl:if>

<xsl:if test="string-length($contactMessenger) != 0" >
<tr>
<td>
Messenger
</td>
<td>
<xsl:value-of select="$contactMessenger" />
</td>
</tr>
</xsl:if>

<xsl:if test="string-length($contactName) != 0" >
<tr>
<td>
Address
</td>
<td>
<xsl:value-of select="$contactName" />
<br />
<xsl:value-of select="$contactCity" />
<br />
<xsl:value-of select="$contactState" />
<br />
<xsl:value-of select="$contactZip" />
</td>
</tr>
</xsl:if>

</table>

</xsl:for-each>
   
</xsl:template>

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>
         
<xsl:variable name="defaultBodyHeading" select="'Contacts'" />
      
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

      <xsl:if test="not(BODY_NAME/CONTACT_DATA)" >
         <jsp:expression>DEFAULTBODYMESSAGE</jsp:expression>
      </xsl:if>

      <xsl:call-template name="defaultBody" >
         <xsl:with-param name="contactData" >
            <xsl:value-of select="BODY_NAME/CONTACT_DATA" />
         </xsl:with-param>
      </xsl:call-template>

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
   
      <xsl:if test="not(BODY_NAME/CONTACT_DATA)" >
         <jsp:expression>DEFAULTBODYMESSAGE</jsp:expression>
      </xsl:if>
   
      <xsl:call-template name="defaultBody" >
         <xsl:with-param name="contactData" >
            <xsl:value-of select="BODY_NAME/CONTACT_DATA" />
         </xsl:with-param>
      </xsl:call-template>

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