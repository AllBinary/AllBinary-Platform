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

<xsl:template name="unifiedColorSelector" >
   <xsl:param name="formName" />
   <xsl:param name="cssColorsNode" />

<div id="colorSelector{$formName}Div" style="display: none;" >

   <table class="wizardColorTable" border="0" cellpadding="0" cellspacing="0" >
   
   <xsl:for-each select="$cssColorsNode" >
      <xsl:for-each select="COLOR_NAME" >
         <xsl:variable name="rgbHexValue" select="value" />

         <xsl:if test="position() = 0" >
            <xsl:text disable-output-escaping="yes" >
            <![CDATA[
            <tr>
            ]]>
            </xsl:text>
         </xsl:if>

         <xsl:if test="position() mod 66 = 0 " >
            <xsl:text disable-output-escaping="yes" >
            <![CDATA[
            </tr>
            <tr>
            ]]>
            </xsl:text>
         </xsl:if>

         <td>
<div 
   style="background-color: {$rgbHexValue}; width: 5px; height: 7px;" 
   onclick="setColor('{$formName}','{$rgbHexValue}');" >
</div>
         </td>

         <xsl:if test="position() = last() " >
            <xsl:text disable-output-escaping="yes" >
            <![CDATA[
            </tr>
            ]]>
            </xsl:text>
         </xsl:if>
         
      </xsl:for-each>
   </xsl:for-each>

      </table>

</div>

</xsl:template>

<xsl:template name="unifiedColorOptions" >
   <xsl:param name="formName" />
   <xsl:param name="parentCssElementNameSpace" />
   <xsl:param name="cssColorNode" />
   <xsl:param name="idNameSpace" />
   <xsl:param name="default" />

   <table border="1" width="60" height="16" >
      <tr>
         <td>
            <div id="selectedColor{$formName}{$idNameSpace}Div"
               style="background-color: {$default}; width: 60; height: 16;"
               onclick="moveAndSetColorProperty('{$formName}','{$parentCssElementNameSpace}','{$idNameSpace}');" >
            </div>
         </td>
      </tr>
   </table>

</xsl:template>

<xsl:template name="unifiedColorOptionsasdf" >
   <xsl:param name="formName" />
   <xsl:param name="cssColorNode" />
   <xsl:param name="idNameSpace" />
   <xsl:param name="default" />

   <table border="1" width="120" height="16" >
      <tr>
         <td>
            <input id="selectedColor{$formName}{$idNameSpace}TextInput"
               class="wizardtext" style="width: 60; height: 16;"
               onKeyUp="setColorFromTextInput('{$formName}','{$idNameSpace}');"            
               onclick="moveAndSetColorProperty('{$formName}','{$idNameSpace}');" 
               type="text" value="{$default}" />
         </td>
         <td>
            <div id="selectedColor{$formName}{$idNameSpace}Div"
               style="background-color: {$default}; width: 60; height: 16;"
               onclick="moveAndSetColorProperty('{$formName}','{$idNameSpace}');" >
            </div>
         </td>
      </tr>
   </table>

</xsl:template>

<xsl:template name="dropDownColorOptions" >

   <option value="#FF0000" >Red</option>
   <option value="#00FF00" >Green</option>
   <option value="#0000FF" >Blue</option>

</xsl:template>

</xsl:stylesheet> 
