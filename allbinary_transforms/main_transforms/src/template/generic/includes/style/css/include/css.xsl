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
   
   <xsl:template name="generateCssLeafElements" >
      <xsl:param name="cssElementNode" />
      <xsl:param name="rootElementNode" />

      <!--
      If current css element has no more child css elements - now build css element with parent overrides
      -->
      <!--
      If not root element get properties from parent elements
      -->
      <xsl:for-each select="$cssElementNode" >

         <xsl:variable name="cssElementNameValue" select="value" />

<xsl:value-of select="$cssElementNameValue" />
{
            <xsl:for-each select="CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

      </xsl:for-each>

}

      <xsl:for-each select="$cssElementNode" >
         <!--
         If current css element has child css elements get next child css element
         -->
         <xsl:for-each select="CSS_ELEMENT_NAME" >
            <xsl:variable name="cssElementNameValue" select="value" />

<xsl:value-of select="$cssElementNameValue" />
{
            <xsl:for-each select="../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

}
         </xsl:for-each>
         
         <!--
         If current css element has child css elements get next child css element
         -->
         <xsl:for-each select="CSS_ELEMENT_NAME/CSS_ELEMENT_NAME" >
            <xsl:variable name="cssElementNameValue" select="value" />

<xsl:value-of select="$cssElementNameValue" />
{
            <xsl:for-each select="../../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

}
         </xsl:for-each>

         <!--
         If current css element has child css elements get next child css element
         -->
         <xsl:for-each select="CSS_ELEMENT_NAME/CSS_ELEMENT_NAME/CSS_ELEMENT_NAME" >
            <xsl:variable name="cssElementNameValue" select="value" />

<xsl:value-of select="$cssElementNameValue" />
{
            <xsl:for-each select="../../../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="../../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="../CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

            <xsl:for-each select="CSS_PROPERTY_NAME" >
               <xsl:variable name="cssPropertyNameValue" select="value" />
<xsl:value-of select="$cssPropertyNameValue" />:<xsl:for-each select="CSS_PROPERTY_VALUES" ><xsl:for-each select="CSS_PROPERTY_VALUE" ><xsl:variable name="cssPropertyValueValue" select="value" /><xsl:value-of select="$cssPropertyValueValue" /><xsl:if test="position()!=last()">,</xsl:if></xsl:for-each>;
</xsl:for-each></xsl:for-each>

}
         </xsl:for-each>

      </xsl:for-each>

   </xsl:template>

</xsl:stylesheet>
