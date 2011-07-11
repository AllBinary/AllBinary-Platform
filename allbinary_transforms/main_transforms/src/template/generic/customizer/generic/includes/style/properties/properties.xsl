<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/template/generic/includes/style/css/include/cssData.xsl" />
   <xsl:import href="/template/generic/includes/style/css/include/cssPropertyData.xsl" />

   <xsl:import href="/template/generic/customizer/generic/includes/style/options/cssOptions.xsl" />
   
   <xsl:template name="generateCssProperty" >
      <xsl:param name="formName" />
      <xsl:param name="cssElementNode" />
      <xsl:param name="cssPropertyNode" />
      <xsl:param name="cssColorNode" />
      <xsl:param name="parentCssElementNameSpace" />
      <xsl:param name="parentCssElementNameSpaceAndProperties" />
      
      <!-- 
      Css Unknown 
      -->
      <xsl:variable name="marginProperty" 
         select="'margin'" />
      <xsl:variable name="topMarginProperty" 
         select="'topMargin'" />
      <xsl:variable name="leftMarginProperty" 
         select="'leftMargin'" />
       
      <!-- 
      Css M$ Extension
      -->
      <xsl:variable name="scrollbarDarkShadowColorProperty" 
         select="'scrollbar-darkshadow-color'" />
      <xsl:variable name="scrollbarHighlightColorProperty"
         select="'scrollbar-highlight-color'" />
      <xsl:variable name="scrollbar3dLightColorProperty" 
         select="'scrollbar-3dlight-color'" />
      <xsl:variable name="scrollbarShadowColorProperty" 
         select="'scrollbar-shadow-color'" />
      <xsl:variable name="scrollbarArrowColorProperty" 
         select="'scrollbar-arrow-color'" />
      <xsl:variable name="scrollbarBaseColorProperty" 
         select="'scrollbar-base-color'" />
      <xsl:variable name="scrollbarFaceColorProperty" 
         select="'scrollbar-face-color'" />

      <!-- 
      Css Level 1 Properties
      -->
            <xsl:variable name="fontFamilyProperty" 
               select="'font-family'" />
            <xsl:variable name="fontStyleProperty"
               select="'font-style'" />
            <xsl:variable name="fontVariantProperty"
               select="'font-variant'" />
            <xsl:variable name="fontWeightProperty"
               select="'font-weight'" />
            <xsl:variable name="fontSizeProperty"
               select="'font-size'" />
            <xsl:variable name="fontProperty"
               select="'font'" />

            <xsl:variable name="colorProperty" 
               select="'color'" />
               
            <xsl:variable name="backgroundColorProperty" 
               select="'background-color'" />
            <xsl:variable name="backgroundImageProperty" 
               select="'background-image'" />
            <xsl:variable name="backgroundRepeatProperty" 
               select="'background-repeat'" />
            <xsl:variable name="backgroundAttachmentProperty" 
               select="'background-attachment'" />
            <xsl:variable name="backgroundPositionProperty" 
               select="'background-position'" />
            <xsl:variable name="backgroundProperty" 
               select="'background'" />

            <xsl:variable name="cursorProperty" 
               select="'cursor'" />

            <xsl:variable name="wordSpacingProperty" 
               select="'word-spacing'" />
            <xsl:variable name="letterSpacingProperty" 
               select="'letter-spacing'" />
            <xsl:variable name="textDecorationProperty" 
               select="'text-decoration'" />
            <xsl:variable name="verticalAlignProperty" 
               select="'vertical-align'" />
               
            <xsl:variable name="textTransformProperty" 
               select="'text-transform'" />
            <xsl:variable name="textAlignProperty" 
               select="'text-align'" />
            <xsl:variable name="textIndentProperty" 
               select="'text-indent'" />
            <xsl:variable name="lineHeightProperty" 
               select="'line-height'" />

            <xsl:variable name="marginTopProperty" 
               select="'margin-top'" />
            <xsl:variable name="marginRightProperty" 
               select="'margin-right'" />
            <xsl:variable name="marginBottomProperty" 
               select="'margin-bottom'" />
            <xsl:variable name="marginLeftProperty" 
               select="'margin-left'" />
            <xsl:variable name="marginProperty" 
               select="'margin'" />

            <xsl:variable name="paddingTopProperty" 
               select="'padding-top'" />
            <xsl:variable name="paddingRightProperty"
               select="'padding-right'" />
            <xsl:variable name="paddingBottomProperty"
               select="'padding-bottom'" />
            <xsl:variable name="paddingLeftProperty"
               select="'padding-left'" />

            <xsl:variable name="borderTopWidthProperty" 
               select="'border-top-width'" />
            <xsl:variable name="borderRightWidthProperty" 
               select="'border-right-width'" />
            <xsl:variable name="borderBottomWidthProperty" 
               select="'border-bottom-width'" />
            <xsl:variable name="borderLeftWidthProperty" 
               select="'border-left-width'" />
            <xsl:variable name="borderWidthProperty" 
               select="'border-width'" />
            <xsl:variable name="borderColorProperty" 
               select="'border-color'" />
            <xsl:variable name="borderStyleProperty" 
               select="'border-style'" />
            <xsl:variable name="borderTopProperty" 
               select="'border-top'" />
            <xsl:variable name="borderRightProperty" 
               select="'border-right'" />
            <xsl:variable name="borderBottomProperty" 
               select="'border-bottom'" />
            <xsl:variable name="borderLeftProperty" 
               select="'border-left'" />
            <xsl:variable name="borderProperty" 
               select="'border'" />

            <xsl:variable name="widthProperty" 
               select="'width'" />
            <xsl:variable name="heightProperty" 
               select="'height'" />
            <xsl:variable name="floatProperty" 
               select="'float'" />
            <xsl:variable name="clearProperty" 
               select="'clear'" />
               
            <xsl:variable name="displayProperty" 
               select="'display'" />
            <xsl:variable name="whiteSpaceProperty" 
               select="'white-space'" />
               
            <xsl:variable name="listStyleTypeProperty" 
               select="'list-style-type'" />
            <xsl:variable name="listStyleImageProperty" 
               select="'list-style-image'" />
            <xsl:variable name="listStylePositionProperty" 
               select="'list-style-position'" />
            <xsl:variable name="listStyleProperty" 
               select="'list-style'" />
               
            <xsl:variable name="fontFamilyPropertyNameSpace" 
               select="concat($propertyValueKey, 'font-family')" />
            <xsl:variable name="fontStylePropertyNameSpace"
               select="concat($propertyValueKey, 'font-style')" />
            <xsl:variable name="fontVariantPropertyNameSpace"
               select="concat($propertyValueKey, 'font-variant')" />
            <xsl:variable name="fontWeightPropertyNameSpace"
               select="concat($propertyValueKey, 'font-weight')" />
            <xsl:variable name="fontSizePropertyNameSpace"
               select="concat($propertyValueKey, 'font-size')" />
            <xsl:variable name="fontPropertyNameSpace"
               select="concat($propertyValueKey, 'font')" />

            <xsl:variable name="colorPropertyNameSpace" 
               select="concat($propertyValueKey, 'color')" />
               
            <xsl:variable name="backgroundColorPropertyNameSpace" 
               select="concat($propertyValueKey, 'background-color')" />
            <xsl:variable name="backgroundImagePropertyNameSpace" 
               select="concat($propertyValueKey, 'background-image')" />
            <xsl:variable name="backgroundRepeatPropertyNameSpace" 
               select="concat($propertyValueKey, 'background-repeat')" />
            <xsl:variable name="backgroundAttachmentPropertyNameSpace" 
               select="concat($propertyValueKey, 'background-attachment')" />
            <xsl:variable name="backgroundPositionPropertyNameSpace" 
               select="concat($propertyValueKey, 'background-position')" />
            <xsl:variable name="backgroundPropertyNameSpace" 
               select="concat($propertyValueKey, 'background')" />

            <xsl:variable name="cursorPropertyNameSpace" 
               select="concat($propertyValueKey, 'cursor')" />

            <xsl:variable name="wordSpacingPropertyNameSpace" 
               select="concat($propertyValueKey, 'word-spacing')" />
            <xsl:variable name="letterSpacingPropertyNameSpace" 
               select="concat($propertyValueKey, 'letter-spacing')" />
            <xsl:variable name="verticalAlignPropertyNameSpace" 
               select="concat($propertyValueKey, 'vertical-align')" />
            <xsl:variable name="textDecorationPropertyNameSpace" 
               select="concat($propertyValueKey, 'text-decoration')" />
               
            <xsl:variable name="textTransformPropertyNameSpace" 
               select="concat($propertyValueKey, 'text-transform')" />
            <xsl:variable name="textAlignPropertyNameSpace" 
               select="concat($propertyValueKey, 'text-align')" />
            <xsl:variable name="textIndentPropertyNameSpace" 
               select="concat($propertyValueKey, 'text-indent')" />
            <xsl:variable name="lineHeightPropertyNameSpace" 
               select="concat($propertyValueKey, 'line-height')" />

            <xsl:variable name="marginTopPropertyNameSpace" 
               select="concat($propertyValueKey, 'margin-top')" />
            <xsl:variable name="marginRightPropertyNameSpace" 
               select="concat($propertyValueKey, 'margin-right')" />
            <xsl:variable name="marginBottomPropertyNameSpace" 
               select="concat($propertyValueKey, 'margin-bottom')" />
            <xsl:variable name="marginLeftPropertyNameSpace" 
               select="concat($propertyValueKey, 'margin-left')" />
            <xsl:variable name="marginPropertyNameSpace" 
               select="concat($propertyValueKey, 'margin')" />

            <xsl:variable name="paddingTopPropertyNameSpace" 
               select="concat($propertyValueKey, 'padding-top')" />
            <xsl:variable name="paddingRightPropertyNameSpace"
               select="concat($propertyValueKey, 'padding-right')" />
            <xsl:variable name="paddingBottomPropertyNameSpace"
               select="concat($propertyValueKey, 'padding-bottom')" />
            <xsl:variable name="paddingLeftPropertyNameSpace"
               select="concat($propertyValueKey, 'padding-left')" />

            <xsl:variable name="borderTopWidthPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-top-width')" />
            <xsl:variable name="borderRightWidthPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-right-width')" />
            <xsl:variable name="borderBottomWidthPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-bottom-width')" />
            <xsl:variable name="borderLeftWidthPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-left-width')" />
            <xsl:variable name="borderWidthPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-width')" />
            <xsl:variable name="borderColorPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-color')" />
            <xsl:variable name="borderStylePropertyNameSpace" 
               select="concat($propertyValueKey, 'border-style')" />
            <xsl:variable name="borderTopPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-top')" />
            <xsl:variable name="borderRightPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-right')" />
            <xsl:variable name="borderBottomPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-bottom')" />
            <xsl:variable name="borderLeftPropertyNameSpace" 
               select="concat($propertyValueKey, 'border-left')" />
            <xsl:variable name="borderPropertyNameSpace" 
               select="concat($propertyValueKey, 'border')" />

            <xsl:variable name="widthPropertyNameSpace" 
               select="concat($propertyValueKey, 'width')" />
            <xsl:variable name="heightPropertyNameSpace" 
               select="concat($propertyValueKey, 'height')" />
            <xsl:variable name="floatPropertyNameSpace" 
               select="concat($propertyValueKey, 'float')" />
            <xsl:variable name="clearPropertyNameSpace" 
               select="concat($propertyValueKey, 'clear')" />
               
            <xsl:variable name="displayPropertyNameSpace" 
               select="concat($propertyValueKey, 'display')" />
            <xsl:variable name="whiteSpacePropertyNameSpace" 
               select="concat($propertyValueKey, 'white-space')" />
               
            <xsl:variable name="listStyleTypePropertyNameSpace" 
               select="concat($propertyValueKey, 'list-style-type')" />
            <xsl:variable name="listStyleImagePropertyNameSpace" 
               select="concat($propertyValueKey, 'list-style-image')" />
            <xsl:variable name="listStylePositionPropertyNameSpace" 
               select="concat($propertyValueKey, 'list-style-position')" />
            <xsl:variable name="listStylePropertyNameSpace" 
               select="concat($propertyValueKey, 'list-style')" />

   <xsl:for-each select="$cssPropertyNode" >

      <xsl:variable name="cssPropertyName" select="value" />

      <xsl:variable name="cssPropertyValuesNameSpace"
         select="concat($preElementNameSpace, $parentCssElementNameSpace, $cssPropertyNameSpace, $propertyNameValueKey, $cssPropertyName, $propertyValueNameSpace)" />

      <xsl:variable name="cssPropertyNameSpaceUnique" 
         select="concat($cssPropertyValuesNameSpace,  '[0]', $properties)" />

      <xsl:variable name="cssPropertyValueNameSpace" 
         select="concat($cssPropertyNameSpaceUnique, $propertyValueKey, $inputValue)" />

      <xsl:if test="$cssPropertyName = $colorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $backgroundColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarDarkShadowColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarHighlightColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbar3dLightColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarShadowColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarArrowColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarBaseColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName = $scrollbarFaceColorProperty" >
      <tr>
         <td>
         </td>
         <td id="colorSelector{$formName}{$cssPropertyValueNameSpace}" >
         </td>
      </tr>
      </xsl:if>

      <xsl:if test="$cssPropertyName" >
      <tr>
         <td>
            <xsl:value-of select="$cssPropertyName" />:
         </td>
         <td>

      <!-- 
      Css Unknown
      -->
      <xsl:choose>

      <xsl:when test="$cssPropertyName = $marginProperty" >
      </xsl:when>

      <xsl:when test="$cssPropertyName = $topMarginProperty" >
      </xsl:when>

      <xsl:when test="$cssPropertyName = $leftMarginProperty" >
      </xsl:when>

      <xsl:when test="$cssPropertyName = $scrollbarDarkShadowColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $scrollbarHighlightColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $scrollbar3dLightColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                      
                  />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>
      
      <xsl:when test="$cssPropertyName = $scrollbarShadowColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $scrollbarArrowColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>
      
      <xsl:when test="$cssPropertyName = $scrollbarBaseColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $scrollbarFaceColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <!-- 
      Css Level 1 Properties
      -->
      <xsl:when test="$cssPropertyName = $fontFamilyProperty" >
      
         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

               <xsl:variable name="index" select="position() - 1" />

               <xsl:variable name="cssPropertyNameSpaceIndexed" 
                  select="concat($cssPropertyValuesNameSpace, '[', $index, ']', $properties)" />

               <xsl:variable name="cssPropertyValueNameSpaceIndexed" 
                  select="concat($cssPropertyNameSpaceIndexed, $propertyValueKey, $inputValue)" />

               <SELECT id="{$formName}{$cssPropertyNameSpaceIndexed}" 
                  class="wizardselect" 
                  name="{$cssPropertyValueNameSpaceIndexed}" 
                  onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceIndexed}');return true;" >

                  <xsl:choose>
                     <xsl:when test="$cssPropertyValue = 'serif'" >
                        <xsl:call-template name="genericFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:when>
                     <xsl:when test="$cssPropertyValue = 'sans-serif'" >
                        <xsl:call-template name="genericFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:when>
                     <xsl:when test="$cssPropertyValue = 'cursive'" >
                        <xsl:call-template name="genericFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:when>                     
                     <xsl:when test="$cssPropertyValue = 'fantasy'" >
                        <xsl:call-template name="genericFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:when>
                     <xsl:when test="$cssPropertyValue = 'monospace'" >
                        <xsl:call-template name="genericFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:when>
                     <xsl:otherwise>
                        <xsl:call-template name="primaryFontFamilyOptions" >
                           <xsl:with-param name="default" >
                              <xsl:value-of select="$cssPropertyValue" />
                           </xsl:with-param>
                        </xsl:call-template>
                     </xsl:otherwise>
                  </xsl:choose>
               </SELECT>

               </xsl:for-each>
            </xsl:for-each>
      </xsl:when>

      <xsl:when test="$cssPropertyName = $fontStyleProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericFontStyleOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $fontVariantProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericFontVariantOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>
            
      <xsl:when test="$cssPropertyName = $fontWeightProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericFontWeightOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $fontSizeProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericFontSizeOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $colorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $backgroundColorProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <input class="wizardtext" 
                      id="selectedColor{$formName}{$cssPropertyValueNameSpace}Input" 
                      type="hidden" 
                      name="{$cssPropertyValueNameSpace}" 
                      value="{$cssPropertyValue}" 
                      onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','selectedColor{$formName}{$cssPropertyValueNameSpace}Input');return true;" 
                       />

                  <xsl:call-template name="unifiedColorOptions" >
                     <xsl:with-param name="formName" >
                        <xsl:value-of select="$formName" />
                     </xsl:with-param>
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                     <xsl:with-param name="cssColorNode" >
                        <xsl:value-of select="$cssColorNode" />
                     </xsl:with-param>
                     <xsl:with-param name="idNameSpace" >
                        <xsl:value-of select="$cssPropertyValueNameSpace" />
                     </xsl:with-param>
                     <xsl:with-param name="parentCssElementNameSpace" >
                        <xsl:value-of select="$parentCssElementNameSpace" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $backgroundImageProperty" >
         Provide 'none' or URL to image<br/>
         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

               <input id="{$formName}{$cssPropertyValueNameSpace}"
                  class="wizardtext" type="text" size="30" 
                  name="{$cssPropertyValueNameSpace}" 
                  value="{$cssPropertyValue}" />
<!--
onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;" 
onMouseOut="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;"
-->
            </xsl:for-each>
         </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $backgroundRepeatProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericBackgroundRepeatOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $backgroundAttachmentProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericBackgroundAttachmentOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $backgroundPositionProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericBackgroundPositionOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $cursorProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericCursorOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>
      
      <xsl:when test="$cssPropertyName = $letterSpacingProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
                  <xsl:call-template name="genericLetterSpacingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $textDecorationProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
                  <xsl:call-template name="genericTextDecorationOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>
      
      <xsl:when test="$cssPropertyName = $textTransformProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericTextTransformOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $textAlignProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericTextAlignOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $textIndentProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericPaddingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>
            
      <xsl:when test="$cssPropertyName = $paddingTopProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericPaddingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $paddingRightProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericPaddingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $paddingBottomProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericPaddingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $paddingLeftProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
               
                  <xsl:call-template name="genericPaddingOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $widthProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
               
               <input id="{$formName}{$cssPropertyValueNameSpace}" 
                  class="wizardtext" type="text" size="5" 
                  name="{$cssPropertyValueNameSpace}" 
                  value="{$cssPropertyValue}" 
                  onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;" 
                  onMouseOut="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;" />

            </xsl:for-each>
         </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $heightProperty" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
               
               <input id="{$formName}{$cssPropertyValueNameSpace}" 
                  class="wizardtext" type="text" size="5" 
                  name="{$cssPropertyValueNameSpace}" 
                  value="{$cssPropertyValue}" 
                  onKeyUp="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;"
                  onMouseOut="cssElementPropertyInputOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyValueNameSpace}');return true;" />

            </xsl:for-each>
         </xsl:for-each>

      </xsl:when>

      <xsl:when test="$cssPropertyName = $displayProperty" >

         <SELECT id="{$formName}{$cssPropertyNameSpaceUnique}" 
            class="wizardselect" 
            name="{$cssPropertyValueNameSpace}" 
            onChange="cssElementPropertySelectOnChange('{$formName}', '{$parentCssElementNameSpace}','{$formName}{$cssPropertyNameSpaceUnique}');return true;" >

         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />

                  <xsl:call-template name="genericDisplayOptions" >
                     <xsl:with-param name="default" >
                        <xsl:value-of select="$cssPropertyValue" />
                     </xsl:with-param>
                  </xsl:call-template>

               </xsl:for-each>
            </xsl:for-each>
         </SELECT>

      </xsl:when>

      <xsl:otherwise>
         <xsl:variable name="cssElementTitle" select="$cssElementNode/title" />
         <xsl:variable name="cssElementDescription" select="$cssElementNode/description" />
         <xsl:variable name="cssElementValue" select="$cssElementNode/value" />

         Property Not Implemented For<br/><br/> 
         Element: Title: <xsl:value-of select="$cssElementTitle" />
         Description: <xsl:value-of select="$cssElementDescription" />
         Value: <xsl:value-of select="$cssElementValue" /><br/>
         Property: <xsl:value-of select="$cssPropertyName" /><br/>
         NameSpace: <xsl:value-of select="$cssPropertyValueNameSpace" /><br/>
         <xsl:for-each select="CSS_PROPERTY_VALUES" >
            <xsl:for-each select="CSS_PROPERTY_VALUE" >
               <xsl:variable name="cssPropertyValue" select="value" />
               Value: <xsl:value-of select="$cssPropertyValue" /><br/>
            </xsl:for-each>
            <br/>
         </xsl:for-each>

      </xsl:otherwise>

      </xsl:choose>
         </td>
      </tr>

      </xsl:if>

      </xsl:for-each>

   </xsl:template>

</xsl:stylesheet> 
