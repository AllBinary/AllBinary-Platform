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

<!--
Arial
Impact
Roman
Small Fonts
System
Tahoma
Terminal
Times New Roman
Verdana
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<xsl:template name="primaryFontFamilyOptions" >
   <xsl:param name="default" />

   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="Arial" >Arial</option>
   <option value="Verdana" >Verdana</option>

</xsl:template>

<xsl:template name="genericFontFamilyOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="serif" >Serif</option>
   <option value="sans-serif" >Sans-Serif</option>
   <option value="cursive" >Cursive</option>
   <option value="fantasy" >Fantasy</option>
   <option value="monospace" >Monospace</option>

</xsl:template>

<xsl:template name="genericFontSizeOptions" >
   <xsl:param name="size" />
   <xsl:param name="default" />
   
   <xsl:if test="not($size)" >
      <option value="{$default}" ><xsl:value-of select="$default" /></option>
      <xsl:call-template name="genericFontSizeOptions" >
         <xsl:with-param name="size" >1</xsl:with-param>
      </xsl:call-template>
   </xsl:if>

   <xsl:if test="$size &lt; 40" >
      <option value="{$size}pt" ><xsl:value-of select="$size" />pt</option>
      <xsl:call-template name="genericFontSizeOptions" >
         <xsl:with-param name="size" ><xsl:value-of select="$size + 1" /></xsl:with-param>
      </xsl:call-template>
   </xsl:if>
   
</xsl:template>

<xsl:template name="genericFontVariantOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="normal" >Normal</option>
   <option value="small-caps" >Small-Caps</option>
   
</xsl:template>

<xsl:template name="genericFontWeightOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="normal" >Normal</option>
   <option value="bold" >Bold</option>
   <option value="bolder" >Bolder</option>
   <option value="lighter" >Lighter</option>
   <option value="100" >100</option>
   <option value="200" >200</option>
   <option value="300" >300</option>
   <option value="400" >400</option>
   <option value="500" >500</option>
   <option value="600" >600</option>
   <option value="700" >700</option>
   <option value="800" >800</option>
   <option value="900" >900</option>
   
</xsl:template>

<xsl:template name="genericFontStyleOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="normal" >Normal</option>
   <option value="italic" >Italic</option>
   <option value="oblique" >Oblique</option>
   
</xsl:template>

<xsl:template name="genericFontasdfOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="underlined" >Underlined</option>
</xsl:template>

</xsl:stylesheet> 
