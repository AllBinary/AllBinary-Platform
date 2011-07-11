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

<xsl:template name="genericPercentOptions" >
   <xsl:param name="size" />
   <xsl:param name="default" />
   
   <xsl:if test="$size &lt; 100" >
      <option value="{$size}" ><xsl:value-of select="$size" /></option>
      <xsl:call-template name="genericPercentOptions" >
         <xsl:with-param name="size" ><xsl:value-of select="$size + 1" />%</xsl:with-param>
      </xsl:call-template>
   </xsl:if>
   
</xsl:template>

<xsl:template name="genericLengthOptions" >
   <xsl:param name="size" />
   <xsl:param name="default" />
   
   <xsl:if test="$size &lt; 100" >
      <option value="{$size}" ><xsl:value-of select="$size" /></option>
      <xsl:call-template name="genericPercentOptions" >
         <xsl:with-param name="size" ><xsl:value-of select="$size + 1" /></xsl:with-param>
      </xsl:call-template>
   </xsl:if>
   
</xsl:template>

</xsl:stylesheet> 
