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

            <xsl:variable name="inputValue" 
               select="'INPUT_VALUE'" />

            <xsl:variable name="cssPropertyNodeName" 
               select="'CSS_PROPERTY_NAME'" />

            <xsl:variable name="cssPropertyNameSpace" 
               select="concat($sep, $cssPropertyNodeName, $properties)" />

            <xsl:variable name="cssPropertyValuesNodeName" 
               select="'CSS_PROPERTY_VALUES'" />

            <xsl:variable name="cssPropertyValueNodeName" 
               select="'CSS_PROPERTY_VALUE'" />

            <xsl:variable name="propertyNameValueKey"
               select="concat($valueNodeName, $equals)" />
            <xsl:variable name="propertyValueKey"
               select="concat($valueNodeName, $equals)" />

            <xsl:variable name="propertyValueNameSpace" 
               select="concat($sep, $cssPropertyValuesNodeName, $sep, $cssPropertyValueNodeName)" />

</xsl:stylesheet>
