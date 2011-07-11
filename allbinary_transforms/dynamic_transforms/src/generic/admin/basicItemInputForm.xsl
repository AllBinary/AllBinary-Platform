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
   <xsl:output method="html"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
   
      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
      
      <xsl:for-each select="BASICITEM" >
      
      <xsl:variable name="command" select="REQUEST/AdminCommand/value" />
      
         <xsl:variable name="idName" select="BASICITEM_ID/name" />
         <xsl:variable name="idValue" select="BASICITEM_ID/value" />
         
         <xsl:variable name="numberName" select="BASICITEM_NUMBER/name" />
         <xsl:variable name="numberValue" select="BASICITEM_NUMBER/value" />
         
         <xsl:variable name="inBasketsName" select="BASICITEM_IN_BASKETS/name" />
         <xsl:variable name="inBasketsValue" select="BASICITEM_IN_BASKETS/value" />
         
         <xsl:variable name="weightName" select="BASICITEM_WEIGHT/name" />
         <xsl:variable name="weightValue" select="BASICITEM_WEIGHT/value" />

         <xsl:variable name="enableName" select="ENTRY_ENABLE/name" />
         <xsl:variable name="enableValue" select="ENTRY_ENABLE/value" />
                     
         <xsl:variable name="newOrUsedName" select="BASICITEM_NEW_OR_USED/name" />
         <xsl:variable name="newOrUsedValue" select="BASICITEM_NEW_OR_USED/value" />
         
         <xsl:variable name="summaryName" select="BASICITEM_SUMMARY/name" />
         <xsl:variable name="summaryValue" select="BASICITEM_SUMMARY/value" />
         
         <xsl:variable name="distributorName" select="BASICITEM_DISTRIBUTOR/name" />
         <xsl:variable name="distributorValue" select="BASICITEM_DISTRIBUTOR/value" />

         <xsl:variable name="idUsedByDistributorName" select="BASICITEM_IDUSEDBYDISTRIBUTOR/name" />
         <xsl:variable name="idUsedByDistributorValue" select="BASICITEM_IDUSEDBYDISTRIBUTOR/value" />
                           
         <xsl:variable name="producedByName" select="BASICITEM_PRODUCEDBY/name" />
         <xsl:variable name="producedByValue" select="BASICITEM_PRODUCEDBY/value" />

         <xsl:variable name="productionDateName" select="BASICITEM_PRODUCTIONDATE/name" />
         <xsl:variable name="productionDateValue" select="BASICITEM_PRODUCTIONDATE/value" />

         <xsl:variable name="startProductionDateName" select="BASICITEM_STARTPRODUCTIONDATE/name" />
         <xsl:variable name="startProductionDateValue" select="BASICITEM_STARTPRODUCTIONDATE/value" />

         <xsl:variable name="descriptionName" select="BASICITEM_DESCRIPTION/name" />
         <xsl:variable name="descriptionValue" select="BASICITEM_DESCRIPTION/value" />

         <xsl:variable name="keywordsName" select="BASICITEM_KEYWORDS/name" />
         <xsl:variable name="keywordsValue" select="BASICITEM_KEYWORDS/value" />

         <xsl:variable name="categoryName" select="BASICITEM_CATEGORY/name" />
         <xsl:variable name="categoryValue" select="BASICITEM_CATEGORY/value" />

         <xsl:variable name="typeName" select="BASICITEM_TYPE/name" />
         <xsl:variable name="typeValue" select="BASICITEM_TYPE/value" />

         <xsl:variable name="imageName" select="BASICITEM_IMG/name" />
         <xsl:variable name="imageValue" select="BASICITEM_IMG/value" />
                     
         <xsl:variable name="smallImageName" select="BASICITEM_SMALL_IMG/name" />
         <xsl:variable name="smallImageValue" select="BASICITEM_SMALL_IMG/value" />

         <xsl:variable name="mediumImageName" select="BASICITEM_MEDIUM_IMG/name" />
         <xsl:variable name="mediumImageValue" select="BASICITEM_MEDIUM_IMG/value" />

         <xsl:variable name="largeImageName" select="BASICITEM_LARGE_IMG/name" />
         <xsl:variable name="largeImageValue" select="BASICITEM_LARGE_IMG/value" />
                  
         <xsl:variable name="priceName" select="BASICITEM_PRICE/name" />
         <xsl:variable name="priceValue" select="BASICITEM_PRICE/value" />         
         
         <xsl:variable name="commentName" select="BASICITEM_COMMENT/name" />
         <xsl:variable name="commentValue" select="BASICITEM_COMMENT/value" />

         <xsl:variable name="customsName" select="BASICITEM_CUSTOMS/name" />
         <xsl:variable name="customsValue" select="BASICITEM_CUSTOMS/value" />

         <xsl:variable name="downloadsName" select="BASICITEM_DOWNLOADS/name" />
         <xsl:variable name="downloadsValue" select="BASICITEM_DOWNLOADS/value" />

         <xsl:variable name="groupsName" select="BASICITEM_GROUPS/name" />
         <xsl:variable name="groupsValue" select="BASICITEM_GROUPS/value" />

         <xsl:variable name="optionsName" select="BASICITEM_OPTIONS/name" />
         <xsl:variable name="optionsValue" select="BASICITEM_OPTIONS/value" />

         <xsl:variable name="permissionsName" select="BASICITEM_PERMISSIONS/name" />
         <xsl:variable name="permissionsValue" select="BASICITEM_PERMISSIONS/value" />

         <xsl:variable name="specialsName" select="BASICITEM_SPECIALS/name" />
         <xsl:variable name="specialsValue" select="BASICITEM_SPECIALS/value" />

         <xsl:variable name="timeCreatedName" select="ENTRY_TIMECREATED/name" />
         <xsl:variable name="timeCreatedValue" select="ENTRY_TIMECREATED/value" />

         <xsl:variable name="lastModifiedName" select="ENTRY_LASTMODIFIED/name" />
         <xsl:variable name="lastModifiedValue" select="ENTRY_LASTMODIFIED/value" />

<input class="text" type="hidden" name="{$inBasketsName}" size="11" value="{$inBasketsValue}" />
<input class="text" type="hidden" name="{$customsName}" size="50" value="{$customsValue}" />
<input class="text" type="hidden" name="{$downloadsName}" size="50" value="{$downloadsValue}" />
<input class="text" type="hidden" name="{$groupsName}" size="50" value="{$groupsValue}" />
<input class="text" type="hidden" name="{$optionsName}" size="50" value="{$optionsValue}" />
<input class="text" type="hidden" name="{$permissionsName}" size="50" value="{$permissionsValue}" />
<input class="text" type="hidden" name="{$specialsName}" size="50" value="{$specialsValue}" />
<p>
<table class="table" border="0" cellpadding="2" cellspacing="2" 
   style="border-collapse: collapse" bordercolor="#111111" width="500" >
   <tr>
      <td width="51%" valign="top" >*Enabled:</td>
      <td width="49%"><SELECT class="select" name='{$enableName}' >
                         <OPTION>Yes</OPTION>
                         <OPTION>No</OPTION>
                      </SELECT>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Condition:</td>
      <td width="49%"><SELECT class="select" name='{$newOrUsedName}' >
                         <OPTION>New</OPTION>
                         <OPTION>Used</OPTION>
                      </SELECT>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Unique ID:</td>
      <td width="49%">
      <input class="text" type="text" name="{$idName}" 
         size="18" value="{$idValue}" /></td> 
   </tr>
   <tr>
      <td width="51%" valign="top" >*Price:</td>
      <td width="49%">
      <input class="text" type="text" name="{$priceName}" 
         size="18" value="{$priceValue}" /></td>
   </tr> 
   <tr>
      <td width="51%" valign="top" >*Number:</td>
      <td width="49%">
      <input class="text" type="text" name="{$numberName}" 
         size="18" value="{$numberValue}" /></td>
   </tr> 
   <tr>
      <td width="51%" valign="top" >*Weight:</td>
      <td width="49%">
      <input class="text" type="text" name="{$weightName}" 
         size="18" value="{$weightValue}" /></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Summary:</td>
      <td width="49%"><input class="text" type="text" name="{$summaryName}" 
         size="50" value="{$summaryValue}" /></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Description:</td>
      <td width="49%">
         <textarea class="text" name="{$descriptionName}" rows="10" 
            cols="48" wrap="virtual" >
            <xsl:value-of select="$descriptionValue"/>
         </textarea>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Keywords:</td>
      <td width="49%"><input class="text" type="text" name="{$keywordsName}" 
         size="50" value="{$keywordsValue}" /></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Category:</td>
      <td width="49%"><input class="text" type="text" name="{$categoryName}" 
         size="50" value="{$categoryValue}" /></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Type:</td>
      <td width="49%"><input class="text" type="text" name="{$typeName}" 
         size="50" value="{$typeValue}" /></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Current Image:</td>
      <td width="49%">
         <a href="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$mediumImageValue}" >
            <img src="{$storeCurrentHostName}{$storeCurrentHostNamePath}{$categoryValue}/{$smallImageValue}" border="0" />
         </a>
      </td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Image:</td>
      <td width="49%"><input class="text" type="file" name="{$imageName}" 
         size="37" value="{$imageValue}" />
         <input type="hidden" name="{$smallImageName}" value="{$smallImageValue}" />
         <input type="hidden" name="{$mediumImageName}" value="{$mediumImageValue}" />
         <input type="hidden" name="{$largeImageName}" value="{$largeImageValue}" />
         </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >Distributor:</td>
      <td width="49%"><input class="text" type="text" name="{$distributorName}" 
         size="50" value="{$distributorValue}" /></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >ID Used By Distributor:</td>
      <td width="49%"><input class="text" type="text" name="{$idUsedByDistributorName}" 
         size="50" value="{$idUsedByDistributorValue}" /></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Produced By:</td>
      <td width="49%"><input class="text" type="text" name="{$producedByName}" 
         size="50" value="{$producedByValue}" /></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >Production Date:</td>
      <td width="49%"><input class="text" type="text" name="{$productionDateName}" 
         size="50" value="{$productionDateValue}" /></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >First Production Date:</td>
      <td width="49%"><input class="text" type="text" name="{$startProductionDateName}" 
         size="50" value="{$startProductionDateValue}" /></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Comment:</td>
      <td width="49%"><textarea class="text" name="{$commentName}"
          rows="10" cols="48" wrap="virtual" >
          <xsl:value-of select="$commentValue"/>
          </textarea></td>
   </tr>
</table>
</p>
            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>            
                  
   </xsl:template>
</xsl:stylesheet> 
