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
   <xsl:template name="windowOptions" >

            <xsl:for-each select="WINDOWS_NAME" >

            <xsl:variable name="categoryDropDownValue" select="WINDOWS_CATEGORY_DROP_DOWN/value" />
            <xsl:variable name="categoryDropDownName" select="WINDOWS_CATEGORY_DROP_DOWN/name" />
            
            <xsl:variable name="categoryListValue" select="WINDOWS_CATEGORY_LIST" />
            <xsl:variable name="categoryLinkValue" select="WINDOWS_CATEGORY_LINK" />
            <xsl:variable name="categoryListAndLinkValue" select="WINDOWS_CATEGORY_LIST_AND_LINK" />

            <xsl:variable name="customerAccountValue" select="WINDOWS_CUSTOMER_ACCOUNT" />
            <xsl:variable name="customerAuthenticationValue" select="WINDOWS_CUSTOMER_AUTHENTICATION" />
            <xsl:variable name="customerMiniBasketValue" select="WINDOWS_CUSTOMER_MINI_BASKET" />

            <xsl:variable name="productsClearanceBestValue" select="WINDOWS_PRODUCTS_CLEARANCE_BEST" />
            <xsl:variable name="productsClearanceLinkValue" select="WINDOWS_PRODUCTS_CLEARANCE_LINK" />
            <xsl:variable name="productsBestValue" select="WINDOWS_PRODUCTS_BEST" />
            <xsl:variable name="productsNewValue" select="WINDOWS_PRODUCTS_NEW" />
            <xsl:variable name="productsPopularValue" select="WINDOWS_PRODUCTS_POPULAR" />
            <xsl:variable name="productsOnSaleValue" select="WINDOWS_PRODUCTS_ON_SALE" />
            <xsl:variable name="productsSpecialsValue" select="WINDOWS_PRODUCTS_SPECIALS" />

            <xsl:variable name="rebatesLinkValue" select="WINDOWS_REBATES_LINK" />
            <xsl:variable name="rebatesBestValue" select="WINDOWS_REBATES_BEST" />
            
            <xsl:variable name="homeValue" select="WINDOWS_HOME" />

            <xsl:variable name="helpValue" select="WINDOWS_HELP_HELP" />

            <xsl:variable name="newsValue" select="WINDOWS_NEWS" />

            <xsl:variable name="partnersValue" select="WINDOWS_PARTNERS" />

                        
<table class="table" >

  <tr>
    <td width="51%">Category:</td>  
  </tr>

  <tr>
    <td width="49%">
    <input type="checkbox" name="${CATEGORYLINKNAME}" />List Link
    </td>
  </tr>

  <tr>
    <td width="49%">
    <input type="checkbox" name="${CATEGORYLISTNAME}" />Short List
    </td>
  </tr>

  <tr>
    <td width="51%">Category 1:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYONENAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 2:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYTWONAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 3:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYTHREENAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 4:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYFOURNAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 5:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYFIVENAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 6:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYSIXNAME}" />
    </td>
  </tr>

  <tr>
    <td width="51%">Category 7:</td>  
    <td width="49%">
    <input class="text" type="text" name="${CATEGORYSEVENNAME}" />
    </td>
  </tr>
                
  <tr>
    <td width="49%">
    <input type="checkbox" name="${CATEGORYDROPDOWNNAME}" />Drop Down
    </td>
  </tr>

  <tr>
    <td width="51%">Customer:</td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${CUSTOMERACCOUNTNAME}" />Account
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${CUSTOMERAUTHENTICATIONNAME}" />Authentication
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${CUSTOMERMINIBASKETNAME}" />Mini Basket
    </td>
  </tr>

  <tr>
    <td width="51%">Products:</td>
  </tr>

  <tr>
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSEARCHNAME}" />Search
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSCLEARANCEBESTNAME}" />Clearance Best
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSCLEARANCELINKNAME}" />Clearance Link
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSBESTNAME}" />Best
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSNEWNAME}" />New
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSPOPULARNAME}" />Popular
    </td>
  </tr>
        
  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSONSALENAME}" />On Sale
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PRODUCTSSPECIALNAME}" />Special
    </td>
  </tr>
  
  <tr>
    <td width="51%">Rebates:</td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${REBATESLINKNAME}" />Link
    </td>
  </tr>

  <tr>    
    <td width="49%">
    <input type="checkbox" name="${REBATESBESTNAME}" />Best
    </td>
  </tr>
  
  <tr>
    <td width="51%">Other:</td>
  </tr>
      
  <tr>    
    <td width="49%">
    <input type="checkbox" name="${HELPNAME}" />Help
    </td>
  </tr>
  <tr>    
    <td width="49%">
    <input type="checkbox" name="${NEWSNAME}" />News
    </td>
  </tr>
  <tr>    
    <td width="49%">
    <input type="checkbox" name="${PARTNERSNAME}" />Partners
    </td>
  </tr>
  <tr>    
    <td width="49%">
    <input type="checkbox" name="${HOMENAME}" />Home
    </td>
  </tr>
          
</table>

       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
