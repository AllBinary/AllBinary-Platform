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

    <xsl:template name="mouseButtonBodyItem"
    >
      <xsl:param name="name"/>
      <xsl:param name="id">
         <xsl:value-of select="$name" />
      </xsl:param>
      
      <xsl:variable name="idNoSpace" select="normalize-space($id)" />
      
      <div id="{$idNoSpace}Div" class="bodyButtonItemMouseOut" 
         onMouseOver="setDomNodeIdStyleWithCssElement('{$idNoSpace}Div','.bodyButtonItemMouseOver');return true;" 
         onMouseOut="setDomNodeIdStyleWithCssElement('{$idNoSpace}Div','.bodyButtonItemMouseOut');return true;" >
         <xsl:value-of select="$name" />
      </div>
            
   </xsl:template>   
         
</xsl:stylesheet> 