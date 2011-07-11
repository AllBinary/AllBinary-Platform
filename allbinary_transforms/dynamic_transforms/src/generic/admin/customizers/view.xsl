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

<xsl:template name="addOptions" >
      <xsl:for-each select="TEMPLATE_CUSTOMIZER_NAME" >
         <xsl:variable name="nextCustomizerName" select="name" />
         <xsl:variable name="nextCustomizerNameValue" select="value" />
         <option value="{$nextCustomizerName}" ><xsl:value-of select="$nextCustomizerNameValue" /></option>
      </xsl:for-each>
</xsl:template>

   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeCurrentHomeHostNameName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/name" />
      <xsl:variable name="storeCurrentHomeHostName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/value" />

      <xsl:variable name="storeCurrentHomeHostNamePathName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHomeHostNamePath" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/value" />
       
            <xsl:for-each select="TEMPLATE_CUSTOMIZERS_NAME" >
               <xsl:variable name="numberOfCustomizers" select="count(TEMPLATE_CUSTOMIZER_NAME)" />
               <xsl:variable name="maxSelectSize" select="'20'" />

               <xsl:if test="$numberOfCustomizers > $maxSelectSize" >
                  <select class="select" size="{$maxSelectSize}" name="TEMPLATE_CUSTOMIZER_NAME" multiple="true" >
                     <xsl:call-template name="addOptions" />
                  </select>
               </xsl:if>

               <xsl:if test="$maxSelectSize >= $numberOfCustomizers" >
                  <select class="select" size="{$numberOfCustomizers}" name="TEMPLATE_CUSTOMIZER_NAME" multiple="true" >
                     <xsl:call-template name="addOptions" />
                  </select>
               </xsl:if>
                              
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>      
   
   </xsl:template>
</xsl:stylesheet> 
