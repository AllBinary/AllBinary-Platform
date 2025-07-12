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
   <xsl:import href="/generic/imports/buttons/mouse.xsl" />
   
   <xsl:template name="hiddenSubmitCommand">
      <input type="hidden" name="AdminCommand" />
   </xsl:template>
      
   <xsl:template name="submitMenuButtonItem" >
      <xsl:param name="name"/>
      <xsl:param name="value"/>
      <xsl:param name="command"/>

      <xsl:variable name="nameNoSpace" select="normalize-space($name)" />
      <xsl:variable name="commandNoSpace" select="normalize-space($command)" />
      
      <div ID="{$nameNoSpace}Div" class="defaultMenuButtonItemMouseOut"
         onMouseOver="setDomNodeIdStyleWithCssElement('{$nameNoSpace}Div','.defaultMenuButtonItemMouseOver');return true;"
         onMouseOut="setDomNodeIdStyleWithCssElement('{$nameNoSpace}Div','.defaultMenuButtonItemMouseOut');return true;"
         onClick="adminCommandSubmit('{$nameNoSpace}','{$commandNoSpace}');return true;" >
               
      <xsl:if test="not($value)" >      
         <xsl:value-of select="$name" />
      </xsl:if>
      <xsl:if test="$value" >
         <xsl:value-of select="$value" />
      </xsl:if>

      </div>
      
   </xsl:template>

    <xsl:template name="bodyButtonItem"
    >
      <xsl:param name="id"/>
      <xsl:param name="name"/>
      <xsl:param name="page"/>

      <a class="cloak" href="{$page}" >
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
      </a>

   </xsl:template>

</xsl:stylesheet> 