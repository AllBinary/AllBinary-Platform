<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

   <xsl:import href="/template/generic/includes/style/css/include/cssData.xsl" />

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/template/generic/customizer/generic/includes/style/properties/properties.xsl" />
      
   <xsl:template name="basicCssElementChild" >
      <xsl:param name="formName" />
      <xsl:param name="rootCssElementNode" />
      <xsl:param name="cssElementNode" />
      <xsl:param name="cssColorNode" />
      <xsl:param name="parentElementNameSpace" />

      <xsl:for-each select="$cssElementNode" >
         <xsl:variable name="cssElementTitle" select="title" />
         <xsl:variable name="cssElementDescription" select="description" />
         <xsl:variable name="cssElementValue" select="value" />
         <xsl:variable name="cssElementClassValue" select="substring($cssElementValue,2)" />

         <!--
         Add Element Input Tag - this assures that a element is created if no 
         properties exist for it and all parent properties will be inherited.
         -->
         <xsl:variable name="currentCssElementNameSpace" 
            select="concat($parentElementNameSpace, $elementNameSpace, $elementValueKey, $cssElementValue)" />
         <xsl:variable name="currentCssElementNameSpaceAndProperties" 
            select="concat($currentCssElementNameSpace, $propSep, $elementTitleKey, $cssElementTitle, $propSep, $elementDescriptionKey, $cssElementDescription)" />

         <input type="hidden" name="{$formName}{$preElementNameSpace}{$currentCssElementNameSpaceAndProperties}" value="" />         

         <!--
         Show/Hide css element
         -->
         <tr>
            <td>

            <table width="10" >
            <tr>
            <td>
            </td>
            </tr>
            </table>

            <a name="{$cssElementClassValue}{$scrollName}" />
            <table id="{$currentCssElementNameSpace}" class="wizardtable" >

            <tr>
               <td>
                  <div class="wizardheader" >
                     <xsl:value-of select="$cssElementValue" />:
                  </div>
                  <br/>
               </td>
            </tr>
            
            <tr>
               <td>
                  Example: <div class="{$cssElementClassValue}">Text</div>
               </td>
            </tr>
            
         <xsl:for-each select="CSS_PROPERTY_NAME" >

            <xsl:call-template name="generateCssProperty" >
               <xsl:with-param name="formName" >
                   <xsl:value-of select="$formName" />
               </xsl:with-param>
               <xsl:with-param name="cssElementNode" >
                  <xsl:value-of select="$cssElementNode" />
               </xsl:with-param>
               <xsl:with-param name="cssPropertyNode" >
                  <xsl:value-of select="current()" />
               </xsl:with-param>
               <xsl:with-param name="cssColorNode" >
                  <xsl:value-of select="$cssColorNode" />
               </xsl:with-param>
               <xsl:with-param name="parentCssElementNameSpace" >
                  <xsl:value-of select="$currentCssElementNameSpace" />
               </xsl:with-param>
               <xsl:with-param name="parentCssElementNameSpaceAndProperties" >
                  <xsl:value-of select="$currentCssElementNameSpaceAndProperties" />
               </xsl:with-param>
            </xsl:call-template>

         </xsl:for-each>

            </table>
            </td>
         </tr>

      </xsl:for-each>

   </xsl:template>

</xsl:stylesheet>