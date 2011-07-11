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
            <xsl:for-each select="HEADER_NAME" >

<p/>
Note: You can specify a title and/or image to appear in the header.
<p/>
<table class="wizardtable" >

<xsl:for-each select="TITLE_NAME" >
<xsl:variable name="titleTextName" select="TITLE_TEXT/name" />
<xsl:variable name="titleText" select="TITLE_TEXT/value" />
  <tr>
    <td>Title:</td>
    <td>
       <input class="wizardtext" type="text" name="{$titleTextName}" size="30" value="{$titleText}" />
    </td>
  </tr>
</xsl:for-each>

<xsl:for-each select="LOGO_NAME" >
<xsl:variable name="logoImageName" select="LOGO_IMAGE/name" />
<xsl:variable name="logoImagePathName" select="LOGO_IMAGE_PATH/name" />
<xsl:variable name="logoImagePath" select="LOGO_IMAGE_PATH/value" />

<xsl:variable name="logoImageFile" select="LOGO_IMAGE_FILE_NAME/value" />
  <tr>
    <td>Image:</td>
    <td>    
       <input class="wizardtext" type="file" name="{$logoImageName}" size="30" value="" />
    </td>
  </tr>
  <xsl:if test="not($logoImageFile)">
  <tr>
    <td width="100%"><img src="{$logoImagePath}{$logoImageFile}" /></td>
  </tr>
  </xsl:if>
</xsl:for-each>
    
</table>
            </xsl:for-each>            
         </xsl:for-each>
       </xsl:for-each>                     
    </xsl:template>

</xsl:stylesheet> 
