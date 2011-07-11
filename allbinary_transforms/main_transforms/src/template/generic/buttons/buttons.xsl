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
   <xsl:import href="/template/generic/buttons/mouse.xsl" />

   <xsl:template name="unusedbuttonPostForm"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="action"/>

<jutil:element name="form">
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="name">
      
   </jutil:attribute>   
   <jutil:attribute name="action">
      <xsl:value-of select="$action" />
   </jutil:attribute>
</jutil:element>
            
   </xsl:template>

   <xsl:template name="unusedSubmitMenuButtonItem"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="name"/>
      <xsl:param name="value"/>
      
      <jutil:element name="input" >
         <jutil:attribute name="class" >
            submit
         </jutil:attribute>
         <jutil:attribute name="type" >
            submit
         </jutil:attribute>
         <jutil:attribute name="name" >
            <xsl:value-of select="$name" />
         </jutil:attribute>
         <jutil:attribute name="value" >
            <xsl:value-of select="$value" />
         </jutil:attribute>
      </jutil:element>
            
   </xsl:template>
   
   <xsl:template name="submitMenuButtonItem"
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
            adminCommandSubmit('<jsp:expression><xsl:value-of select="$nameNoSpace" /></jsp:expression>','<jsp:expression><xsl:value-of select="$command" /></jsp:expression>')
         </jutil:attribute>

         <xsl:if test="not($value)" >      
            <jsp:expression><xsl:value-of select="$name" /></jsp:expression>
         </xsl:if>
         <xsl:if test="$value" >
            <jsp:expression><xsl:value-of select="$value" /></jsp:expression>
         </xsl:if>

      </jutil:element>
            
   </xsl:template>


   <xsl:template name="menuButtonItem"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <jutil:element name="a">      
         <jutil:attribute name="class">cloak</jutil:attribute>
         <jutil:attribute name="href">
            <jsp:expression><xsl:value-of select="$page" /></jsp:expression>
         </jutil:attribute>
      
         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonMenuItem">            
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>

         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonMenuItem">  
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>

      
   <xsl:template name="menuButtonItemNewWindow"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <jutil:element name="a">
         <jutil:attribute name="target">_blank</jutil:attribute>
         <jutil:attribute name="class">cloak</jutil:attribute>      
         <jutil:attribute name="href">
            <jsp:expression><xsl:value-of select="$page" /></jsp:expression>
         </jutil:attribute>
      
         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonMenuItem">            
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>

         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonMenuItem">  
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>
   
   
   <xsl:template name="bodyButtonItem"
      xmlns:jsp="http://java.sun.com/JSP/Page" 
      xmlns:jutil="/WEB-INF/jutil.tld" >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <jutil:element name="a">      
         <jutil:attribute name="class">cloak</jutil:attribute>
         <jutil:attribute name="href">
         <jsp:expression><xsl:value-of select="$page" /></jsp:expression></jutil:attribute>

         <xsl:if test="$id" >
         <xsl:call-template name="mouseButtonBodyItem">
            <xsl:with-param name="id">
               <xsl:value-of select="$id" />
            </xsl:with-param>
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
         
         <xsl:if test="not($id)" >
         <xsl:call-template name="mouseButtonBodyItem">
            <xsl:with-param name="name">
               <xsl:value-of select="$name" />
            </xsl:with-param>
         </xsl:call-template>
         </xsl:if>
                  
      </jutil:element>
            
   </xsl:template>
             
</xsl:stylesheet> 