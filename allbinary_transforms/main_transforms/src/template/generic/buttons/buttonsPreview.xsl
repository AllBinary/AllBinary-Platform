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

   <xsl:import href="/template/generic/buttons/mousePreview.xsl" />

   <xsl:template name="submitMenuButtonItemPreview"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="value"/>
      <xsl:param name="command"/>
      
      <xsl:variable name="nameNoSpace" select="normalize-space($name)" />

      <jutil:element name="div">
         <jutil:attribute name="ID"><jsp:expression><xsl:value-of select="$nameNoSpace" /></jsp:expression>Div</jutil:attribute>
         <jutil:attribute name="class">menuButtonItemMouseOut</jutil:attribute>

         <jutil:attribute name="onMouseOver">
            setDomNodeIdStyleWithCssElement('<jsp:expression><xsl:value-of select="$nameNoSpace" /></jsp:expression>Div','.menuButtonItemMouseOver');return true;
         </jutil:attribute>

         <jutil:attribute name="onMouseOut">
            setDomNodeIdStyleWithCssElement('<jsp:expression><xsl:value-of select="$nameNoSpace" /></jsp:expression>Div','.menuButtonItemMouseOut');return true;
         </jutil:attribute>

         <jutil:attribute name="onClick">
            abScrollTo('menuButtonItemMouseOut<xsl:value-of select="$scrollName" />');<xsl:value-of select="$javascriptErrorControl" />
         </jutil:attribute>

         <xsl:if test="not($value)" >      
            <jsp:expression><xsl:value-of select="$name" /></jsp:expression>
         </xsl:if>
         <xsl:if test="$value" >
            <jsp:expression><xsl:value-of select="$value" /></jsp:expression>
         </xsl:if>

      </jutil:element>
            
   </xsl:template>


   <xsl:template name="menuButtonItemPreview"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>


      <jutil:element name="a">      
         <jutil:attribute name="class">cloak</jutil:attribute>
      
         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonMenuItemPreview">
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>

         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonMenuItemPreview">
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>
   

   <xsl:template name="menuButtonItemNewWindowPreview"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <jutil:element name="a">
         <jutil:attribute name="class">cloak</jutil:attribute>

         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonMenuItemPreview">
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>

         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonMenuItemPreview">  
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>


   <xsl:template name="bodyButtonItemPreview"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <jutil:element name="a">      
         <jutil:attribute name="class">cloak</jutil:attribute>

         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonBodyItemPreview">
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
         
         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonBodyItemPreview">
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>
             
</xsl:stylesheet> 