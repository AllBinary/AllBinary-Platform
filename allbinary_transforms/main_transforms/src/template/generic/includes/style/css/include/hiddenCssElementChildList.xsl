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

   <xsl:import href="/template/generic/includes/style/css/include/cssData.xsl" />
   
   <xsl:template name="generateHiddenCssElementChildList" >
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
         <xsl:variable name="currentCssElementNameSpace" 
            select="concat($elementNameSpace, $elementValueKey, $cssElementNameValue)" />

         <div id="{$currentCssElementNameSpace}" style="display: none;" >
            <xsl:value-of select="$cssElementNameValue" />

            <xsl:for-each select="CSS_ELEMENT_NAME" >
               <xsl:variable name="cssElementNameValue" select="value" />
               <xsl:variable name="currentCssElementNameSpace" 
                  select="concat($elementNameSpace, $elementValueKey, $cssElementNameValue)" />
                  
               <div id="{$currentCssElementNameSpace}" style="display: none;" >
                  <xsl:value-of select="$cssElementNameValue" />
                  
                  <xsl:for-each select="CSS_ELEMENT_NAME" >
                     <xsl:variable name="cssElementNameValue" select="value" />
                     <xsl:variable name="currentCssElementNameSpace" 
                        select="concat($elementNameSpace, $elementValueKey, $cssElementNameValue)" />

                     <div id="{$currentCssElementNameSpace}" style="display: none;" >
                        <xsl:value-of select="$cssElementNameValue" />
                     </div>

                  </xsl:for-each>

               </div>

            </xsl:for-each>

         </div>

      </xsl:for-each>

   </xsl:template>

</xsl:stylesheet>
