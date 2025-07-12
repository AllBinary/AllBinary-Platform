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

   <xsl:template name="mouseMenuItem"
                 xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="id">
         <xsl:value-of select="$name" />
      </xsl:param>
      
      <xsl:variable name="idNoSpace" select="normalize-space($id)" />
      
      <jutil:element name="div">

         <jutil:attribute name="ID">
            <xsl:value-of select="$idNoSpace" />Div
         </jutil:attribute>
      
         <jutil:attribute name="class">menuItemMouseOut</jutil:attribute>

         <jutil:attribute name="onMouseOver">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.menuItemMouseOver');return true;
         </jutil:attribute>
      
         <jutil:attribute name="onMouseOut">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.menuItemMouseOut');return true;
         </jutil:attribute>

         <xsl:value-of select="$name" />
      </jutil:element>
            
   </xsl:template>   

   <xsl:template name="mouseBodyItem"
                 xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="id">
         <xsl:value-of select="$name" />
      </xsl:param>
      
      <xsl:variable name="idNoSpace" select="normalize-space($id)" />
      
      <jutil:element name="div">
      
         <jutil:attribute name="ID">
            <xsl:value-of select="$idNoSpace" />Div
         </jutil:attribute>
      
         <jutil:attribute name="class">bodyItemMouseOut</jutil:attribute>
      
         <jutil:attribute name="onMouseOver">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.bodyItemMouseOver');return true;
         </jutil:attribute>
      
         <jutil:attribute name="onMouseOut">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.bodyItemMouseOut');return true;
         </jutil:attribute>
      
         <xsl:value-of select="$name" />
      </jutil:element>
            
   </xsl:template>   
   
   <xsl:template name="mouseButtonMenuItem"
                 xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="id">
         <xsl:value-of select="$name" />
      </xsl:param>
      
      <jutil:element name="div">

         <xsl:variable name="idNoSpace" select="normalize-space($id)" />
      
         <jutil:attribute name="ID">
            <xsl:value-of select="$idNoSpace" />Div
         </jutil:attribute>
            
         <jutil:attribute name="class">menuButtonItemMouseOut</jutil:attribute>
      
         <jutil:attribute name="onMouseOver">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.menuButtonItemMouseOver');return true;
         </jutil:attribute>
   
         <jutil:attribute name="onMouseOut">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.menuButtonItemMouseOut');return true;
         </jutil:attribute>

         <xsl:value-of select="$name" />
      </jutil:element>
            
   </xsl:template>

    <xsl:template name="mouseButtonBodyItem"
                  xmlns:jutil="/WEB-INF/jutil.tld">
      <xsl:param name="name"/>
      <xsl:param name="id">
         <xsl:value-of select="$name" />
      </xsl:param>
      
      <xsl:variable name="idNoSpace" select="normalize-space($id)" />
      
      <jutil:element name="div">
      
         <jutil:attribute name="ID">
            <xsl:value-of select="$idNoSpace" />Div
         </jutil:attribute>

         <jutil:attribute name="class">bodyButtonItemMouseOut</jutil:attribute>
      
         <jutil:attribute name="onMouseOver">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.bodyButtonItemMouseOver');return true;
         </jutil:attribute>

         <jutil:attribute name="onMouseOut">
            setDomNodeIdStyleWithCssElement('<xsl:value-of select="$idNoSpace" />Div','.bodyButtonItemMouseOut');return true;
         </jutil:attribute>

         <xsl:value-of select="$name" />
      </jutil:element>
            
   </xsl:template>   
         
</xsl:stylesheet> 