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

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:template name="smallAnchorItemPreview"
                 xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="page"/>      

      <jutil:element name="a">      
         <jutil:attribute name="class">smallCloak</jutil:attribute>
         <jutil:attribute name="onClick">
            abScrollTo('smallCloak<xsl:value-of select="$scrollName" />');<xsl:value-of select="$javascriptErrorControl" />
         </jutil:attribute>

         <xsl:value-of select="$name" />

      </jutil:element>
            
   </xsl:template>


    <xsl:template name="anchorPreview"
                  xmlns:jutil="/WEB-INF/jutil.tld">
      <xsl:param name="name"/>
      <xsl:param name="page"/>      

      <jutil:element name="a">      
         <jutil:attribute name="class">bodyCloak</jutil:attribute>
         <jutil:attribute name="onClick">
            abScrollTo('bodyCloak<xsl:value-of select="$scrollName" />');<xsl:value-of select="$javascriptErrorControl" />
         </jutil:attribute>

         <xsl:value-of select="$name" />

      </jutil:element>
            
   </xsl:template>

</xsl:stylesheet> 