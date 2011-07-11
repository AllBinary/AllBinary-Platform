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
               Editing <xsl:value-of select="$pageName" />:<p/>
            </xsl:for-each>
            
<form method="POST" action="webAdmin.jsp" >

<textarea class="text" name="BODY_NAME" rows="20" 
   cols="76" wrap="virtual" >

            <xsl:for-each select="BODY_NAME" >

            <xsl:variable name="bodyDataName" select="name" />
            <xsl:variable name="bodyDataValue" select="value" />
   
               <xsl:if test="$bodyDataValue=''">
                  <div class="mainHeading">
                  <p>Your Heading</p>
                  <div class="main">Your Body</div>
                  </div>
               </xsl:if>


               <xsl:value-of select="$bodyDataValue" />

            </xsl:for-each>
</textarea>
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
