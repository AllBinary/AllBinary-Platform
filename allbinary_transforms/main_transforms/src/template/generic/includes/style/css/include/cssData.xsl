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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

            <xsl:variable name="sep" 
               select="'_DOT_'" />

            <xsl:variable name="nameSpace" 
               select="'NAMESPACE_REQUESTDATA_NAME'" />

            <xsl:variable name="stylesNode" 
               select="'STYLES_NAME'" />

            <xsl:variable name="styleNode" 
               select="'STYLE_NAME'" />

            <xsl:variable name="propSep" 
               select="'_PROPDOT_'" />

            <xsl:variable name="properties" 
               select="'_PROPERTIES_'" />

            <xsl:variable name="equals" 
               select="'_EQUALS_'" />

            <xsl:variable name="cssElementNodeName" 
               select="'CSS_ELEMENT_NAME'" />

            <xsl:variable name="preElementNameSpace" 
               select="concat($nameSpace, $sep, $stylesNode, $sep, $styleNode)" />

            <xsl:variable name="elementNameSpace" 
               select="concat($sep, $cssElementNodeName, $properties)" />

            <xsl:variable name="valueNodeName" 
               select="'value'" />
            <xsl:variable name="elementValueKey"
               select="concat($valueNodeName, $equals)" />

            <xsl:variable name="titleNodeName" 
               select="'title'" />
            <xsl:variable name="elementTitleKey"
               select="concat($titleNodeName, $equals)" />

            <xsl:variable name="descriptionNodeName" 
               select="'description'" />
            <xsl:variable name="elementDescriptionKey"
               select="concat($descriptionNodeName, $equals)" />

</xsl:stylesheet>
