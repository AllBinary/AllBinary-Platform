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
    <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

         <xsl:variable name="storeName" select="STOREFRONT_NAME/STOREFRONT_NAME/value" />
         
            <xsl:for-each select="PAGE_NAME" >
               <xsl:variable name="pageName" select="value" />
               Editing <xsl:value-of select="$pageName" /> Body:<p/>
            </xsl:for-each>

<form method="POST" action="webAdmin.jsp" >

            <xsl:for-each select="BODY_NAME" >

               <xsl:variable name="bodyDataName" select="name" />
               <xsl:variable name="bodyDataValue" select="value" />

<table class="table" >

               <xsl:for-each select="TITLE_NAME" >

                  <xsl:variable name="titleTextName" select="TITLE_TEXT/name" />
                  <xsl:variable name="titleText" select="TITLE_TEXT/value" />
   <tr>
     <td>Heading:</td>
     <td>
        <input class="text" type="text" name="{$titleTextName}" size="30" value="{$titleText}" />
     </td>
   </tr>
               </xsl:for-each>

   <tr>
     <td>Body:</td>
     <td>

<textarea class="text" name="{$bodyDataName}" rows="20" 
   cols="65" wrap="virtual" >
   
               <xsl:if test="$bodyDataValue=''">
                  <div class="main">Your Body</div>
               </xsl:if>

               <xsl:value-of select="$bodyDataValue" />

</textarea>
     
     </td>
   </tr>

</table>
            </xsl:for-each>
<p/>

            <xsl:for-each select="PAGE_NAME" >
               <xsl:variable name="pageName" select="value" />
               
<input type="hidden" value="{$storeName} Insert {$pageName} Title Body CUSTOMIZER_TRANSFORM_INFO_NAME" name="TEMPLATE_CUSTOMIZER_NAME" />

            </xsl:for-each>

<input type="hidden" value="CustomizersTab" name="TAB_COMMAND" />

<input class="submit" type="submit" value="Update" name="AdminCommand" />

</form>


         </xsl:for-each>
       </xsl:for-each>                     
    </xsl:template>

</xsl:stylesheet> 
