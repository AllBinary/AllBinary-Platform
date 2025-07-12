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

                <jsp:root
                        xmlns:jsp="http://java.sun.com/JSP/Page"
                        version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<meta http-equiv="Content-Language" content="en-us" />

               <xsl:for-each select="HTMLMETA_NAME" >

               <xsl:variable name="metatagLabelName" select="HTMLMETA_LABEL/name" />
               <xsl:variable name="metatagLabelValue" select="HTMLMETA_LABEL/value" />

               <xsl:variable name="metatagNameName" select="HTMLMETA_ATTRIBUTE_NAME/name" />
               <xsl:variable name="metatagNameValue" select="HTMLMETA_ATTRIBUTE_NAME/value" />

               <xsl:variable name="metatagHttpEquivName" select="HTMLMETA_ATTRIBUTE_HTTPEQUIV/name" />
               <xsl:variable name="metatagHttpEquivValue" select="HTMLMETA_ATTRIBUTE_HTTPEQUIV/value" />

               <xsl:variable name="metatagContentName" select="HTMLMETA_ATTRIBUTE_CONTENT/name" />
               <xsl:variable name="metatagContentValue" select="HTMLMETA_ATTRIBUTE_CONTENT/value" />

<xsl:if test="$metatagNameValue" >
<meta name="{$metatagNameValue}" content="{$metatagContentValue}" />
</xsl:if>

<xsl:if test="$metatagHttpEquivValue" >
<meta http-equiv="{$metatagHttpEquivValue}" content="{$metatagContentValue}" />
</xsl:if>

               </xsl:for-each>

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
</jsp:root>         
            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>                     
    </xsl:template>

</xsl:stylesheet> 
