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
    <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
            <xsl:for-each select="HTMLMETAS_NAME" >
            
<xsl:text disable-output-escaping="yes" >
<![CDATA[
<jsp:root 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld"
   version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>
]]>
</xsl:text>
Note: Metatags are important for some search engines.  Other than product pages<br/>

<table class="table" >

               <xsl:for-each select="HTMLMETA_NAME" >

               <xsl:variable name="metatagLabelName" select="HTMLMETA_LABEL/name" />
               <xsl:variable name="metatagLabelValue" select="HTMLMETA_LABEL/value" />

               <xsl:variable name="metatagNameName" select="HTMLMETA_ATTRIBUTE_NAME/name" />
               <xsl:variable name="metatagNameValue" select="HTMLMETA_ATTRIBUTE_NAME/value" />

               <xsl:variable name="metatagHttpEquivName" select="HTMLMETA_ATTRIBUTE_HTTPEQUIV/name" />
               <xsl:variable name="metatagHttpEquivValue" select="HTMLMETA_ATTRIBUTE_HTTPEQUIV/value" />

               <xsl:variable name="metatagContentName" select="HTMLMETA_ATTRIBUTE_CONTENT/name" />
               <xsl:variable name="metatagContentValue" select="HTMLMETA_ATTRIBUTE_CONTENT/value" />

  <tr>
    <td width="51%"><xsl:value-of select="{$metatagLabelValue}" />:</td>
    <td width="49%">

<xsl:if test="$metatagNameValue" >
    Http-Equiv: <input class="textField" type="text" name="{$metatagNameName}" size="30" value="{$metatagNameValue}" />
</xsl:if>

<xsl:if test="$metatagHttpEquivValue" >
    Name: <input class="textField" type="text" name="{$metatagHttpEquivName}" size="30" value="{$metatagHttpEquivValue}" />
</xsl:if>
    Content: <input class="textField" type="text" name="{$metatagContentName}" size="30" value="{$metatagContentValue}" />
    </td>
  </tr>

               </xsl:for-each>
</table>

            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>                     
    </xsl:template>

</xsl:stylesheet> 
