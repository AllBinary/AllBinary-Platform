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

<xsl:template name="genericTextDecorationOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
      <option value="none" >None</option>
      <option value="underline" >Underline</option>
      <option value="overline" >Overline</option>
      <option value="line-through" >Line-through</option>
      <option value="blink" >Blink</option>

</xsl:template>

<xsl:template name="genericTextTransformOptions" >
   <xsl:param name="default" />

   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="none" >None</option>
   <option value="capitalize" >Capitalize</option>
   <option value="uppercase" >Uppercase</option>
   <option value="lowercase" >Lowercase</option>

</xsl:template>

<xsl:template name="genericTextAlignOptions" >
   <xsl:param name="default" />

   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="left" >Left</option>
   <option value="right" >Right</option>
   <option value="center" >Center</option>
   <option value="justify" >Justify</option>

</xsl:template>
 
<xsl:template name="genericTextIndentOptions" >
   <xsl:param name="size" />
   <xsl:param name="default" />
   
   <xsl:if test="not($size)" >
      <option value="{$default}" ><xsl:value-of select="$default" /></option>
      <xsl:call-template name="genericTextIndentOptions" >
         <xsl:with-param name="size" >1</xsl:with-param>
      </xsl:call-template>
   </xsl:if>

   <xsl:if test="$size &lt; 20" >
      <option value="{$size}" ><xsl:value-of select="$size" /></option>
      <xsl:call-template name="genericTextIndentOptions" >
         <xsl:with-param name="size" ><xsl:value-of select="$size + 1" /></xsl:with-param>
      </xsl:call-template>
   </xsl:if>

</xsl:template>

</xsl:stylesheet> 
