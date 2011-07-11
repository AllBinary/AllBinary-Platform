<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template name="addThemes" >
      <xsl:param name="categoryNode" />
   
      <xsl:for-each select="$categoryNode" >
         <xsl:variable name="categoryAttributeLabelValue" select="@label"/>
         
         <xsl:for-each select="THEME_NAME" >

            <xsl:variable name="themeNameValue" select="THEME_NAME/value"/>
            <xsl:variable name="themePathValue" select="THEME_PATH/value"/>

            <option value="{$themePathValue}{$themeNameValue}" >
               <xsl:value-of select="$themeNameValue"/>
            </option>

         </xsl:for-each>
            
         <xsl:for-each select="CATEGORY_NAME" >
            <xsl:call-template name="addThemes" >
               <xsl:with-param name="categoryNode" >
                  <xsl:value-of select="current()" />
               </xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>

      </xsl:for-each>
   </xsl:template>

   <xsl:template name="addThemePreviewImages" >
      <xsl:param name="categoryNode" />
   
      <xsl:for-each select="$categoryNode" >
         <xsl:variable name="categoryAttributeLabelValue" select="@label"/>
         
         <xsl:for-each select="THEME_NAME" >
         
            <xsl:variable name="themeNameValue" select="THEME_NAME/value"/>
            <xsl:variable name="themePathValue" select="THEME_PATH/value"/>

            <xsl:variable name="themePreviewImageNameValue" select="THEME_PREVIEW_IMAGE_NAME/value"/>
            <xsl:variable name="themePreviewImagePathValue" select="THEME_PREVIEW_IMAGE_PATH/value"/>
       imageSrcAttributeHashMap.put('<xsl:value-of select="$themePathValue"/><xsl:value-of select="$themeNameValue"/>','<xsl:value-of select="$themePreviewImagePathValue"/><xsl:value-of select="$themePreviewImageNameValue"/>');
         </xsl:for-each>
            
         <xsl:for-each select="CATEGORY_NAME" >
            <xsl:call-template name="addThemePreviewImages" >
               <xsl:with-param name="categoryNode" >
                  <xsl:value-of select="current()" />
               </xsl:with-param>
            </xsl:call-template>
         </xsl:for-each>

      </xsl:for-each>
   </xsl:template>
      
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

    <script language="JavaScript">

       var imageSrcAttributeHashMap = 
          new ImageSrcAttributeHashMapPreload('themeImageId');
          
            <xsl:for-each select="CATEGORY_NAME">
               <xsl:variable name="categoryAttributeLabelValue" select="@label"/>
         
               <xsl:for-each select="THEME_NAME" >
                  <xsl:variable name="themeNameValue" select="THEME_NAME/value"/>
                  <xsl:variable name="themePathValue" select="THEME_PATH/value"/>

                  <xsl:variable name="themePreviewImageNameValue" select="THEME_PREVIEW_IMAGE_NAME/value"/>
                  <xsl:variable name="themePreviewImagePathValue" select="THEME_PREVIEW_IMAGE_PATH/value"/>
                  imageSrcAttributeHashMap.put('<xsl:value-of select="$themePathValue"/><xsl:value-of select="$themeNameValue"/>','<xsl:value-of select="$themePreviewImagePathValue"/><xsl:value-of select="$themePreviewImageNameValue"/>');
                </xsl:for-each>

            </xsl:for-each>

    </script>

            Selected Theme:
            <select class="wizardselect" name="THEME_PATH" 
               onChange="imageSrcAttributeHashMap.set(this);return true;" 
               onKeyUp="imageSrcAttributeHashMap.set(this);return true;" >

            <xsl:for-each select="CATEGORY_NAME">
               <xsl:variable name="categoryAttributeLabelValue" select="@label"/>
         
               <xsl:for-each select="THEME_NAME" >
                  <xsl:variable name="themeNameValue" select="THEME_NAME/value"/>
                  <xsl:variable name="themePathValue" select="THEME_PATH/value"/>

                  <option value="{$themePathValue}{$themeNameValue}" >
                     <xsl:value-of select="$themeNameValue"/>
                  </option>

               </xsl:for-each>

            </xsl:for-each>
            
            </select>

   <input class="wizardsubmit" type="submit" 
      value="Continue" name="AdminCommand" />
   
            <xsl:for-each select="CATEGORY_NAME">

               <xsl:variable name="firstThemePosition" select="position()"/>
               
               <xsl:for-each select="THEME_NAME" >
                              
               <xsl:if test="position() = $firstThemePosition" >
               
               <xsl:variable name="themeNameValue" select="THEME_NAME/value"/>
               <xsl:variable name="themePreviewImageNameValue" select="THEME_PREVIEW_IMAGE_NAME/value"/>
               <xsl:variable name="themePreviewImagePathValue" select="THEME_PREVIEW_IMAGE_PATH/value"/>

               <br/><br/>
               <img id="themeImageId" src="{$themePreviewImagePathValue}{$themePreviewImageNameValue}" /><br/>
               
               </xsl:if>

               </xsl:for-each>
            </xsl:for-each>
           
   <br/>
   <input class="wizardsubmit" type="submit" 
      value="Continue" name="AdminCommand" />

         </xsl:for-each>
      </xsl:for-each>

   </xsl:template>
</xsl:stylesheet>
