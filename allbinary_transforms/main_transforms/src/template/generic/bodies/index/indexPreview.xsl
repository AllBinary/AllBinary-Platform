<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/template/generic/includes/style/css/include/cssElementDivExamples.xsl" />
   
   <xsl:import href="/template/generic/bodies/imports/generic/body/body.xsl" />
   
   <xsl:output method="xml" indent="yes" />

   <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>
         
   <xsl:call-template name="body" >
      <xsl:with-param name="bodyNode" >
         <xsl:value-of select="BODY_NAME" />
      </xsl:with-param>
      <xsl:with-param name="defaultBodyHeading" >
         Welcome
      </xsl:with-param>
   </xsl:call-template>

      <div id="previewHelp" class="main" 
         onClick="abScrollTo('main{$scrollName}');{$javascriptErrorControl}" 
         style="display: block;" >
         Notes:<br/><br/>
         
         This preview shows the general style, and not a final page.<br/>
         Two cursors/fonts are provided for browser compatibility.<br/>
         Click on a color to show/hide the color selector.<br/>
         <br/><br/>
         Modify the visual elements by doing the following:<br/><br/>
         1. Left clicking on the desired element in this preview<br/>
         (Result: Scrolls to style associated with the element)<br/>
         2. Set the associated element style<br/>
         3. Wait and review the change in the preview<br/>
         4. Repeat until satisfied<br/><br/>

         Short delays may occur when changing the style.  
         Processing for this step usually takes 5 minutes.<br/><br/>
      </div>

      <div id="elementPreview"
         style="display: none;height:5000px;" >

            <xsl:for-each select="STYLES_NAME" >
               <xsl:for-each select="STYLE_NAME" >
                  <xsl:for-each select="CSS_ELEMENT_NAME" >

                     <xsl:call-template name="generateCssLeafElements" >
                        <xsl:with-param name="cssElementNode" >
                           <xsl:value-of select="current()" />
                        </xsl:with-param>
                        <xsl:with-param name="rootElementNode" >
                           <xsl:value-of select="current()" />
                        </xsl:with-param>
                     </xsl:call-template>

                  </xsl:for-each>
               </xsl:for-each>
            </xsl:for-each>

         <!--
         <xsl:call-template name="jspDirectiveIncludeInclude" >
            <xsl:with-param name="fileName" >
               cssPreview
            </xsl:with-param>
         </xsl:call-template>
         -->
         
      </div>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>