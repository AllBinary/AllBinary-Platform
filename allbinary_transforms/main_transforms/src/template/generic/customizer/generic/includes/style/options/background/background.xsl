<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<xsl:template name="genericBackgroundRepeatOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="repeat" >Repeat</option>
   <option value="repeat-x" >Repeat-X</option>
   <option value="repeat-y" >Repeat-Y</option>
   <option value="no-repeat" >No-Repeat</option>
   
</xsl:template>

<xsl:template name="genericBackgroundAttachmentOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="scroll" >Scroll</option>
   <option value="fixed" >Fixed</option>
   
</xsl:template>

<xsl:template name="genericBackgroundPositionFirstOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="top" >top</option>
   <option value="center" >center</option>
   <option value="bottom" >bottom</option>

   <xsl:call-template name="genericPercentOptions" >
      <xsl:with-param name="size" >0</xsl:with-param>
   </xsl:call-template>
   
</xsl:template>

<xsl:template name="genericBackgroundPositionSecondOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="left" >left</option>
   <option value="center" >center</option>
   <option value="right" >right</option>
   
   <xsl:call-template name="genericPercentOptions" >
      <xsl:with-param name="size" >0</xsl:with-param>
   </xsl:call-template>
   
</xsl:template>

</xsl:stylesheet> 
