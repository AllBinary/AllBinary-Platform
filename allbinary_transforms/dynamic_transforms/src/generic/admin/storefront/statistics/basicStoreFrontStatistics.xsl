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

<!--
Should probably have the customers last selection recorded        
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="html"/>
   <xsl:template match="/html" >

      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

            <xsl:for-each select="STOREFRONT_STATISTICS_NAME" >

               <p/>
               Inventory Statistics:<p/>
               <table class="main" >

               <xsl:for-each select="STOREFRONT_INVENTORY_STATISTICS_NAME" >
                  <xsl:variable name="numberOfItems" select="STOREFRONT_INVENTORY_STATISTICS_NUMBER_OF_ITEMS/value" />
                  <xsl:variable name="inventoryValue" select="STOREFRONT_INVENTORY_STATISTICS_TOTAL_VALUE/value" />

               <tr>
                  <td>
                     Number Of Items:                     
                  </td>
                  <td>
                     <xsl:value-of select="$numberOfItems" />
                  </td>
               </tr>

               <tr>
                  <td>
                     Total Value:                     
                  </td>
                  <td>
                     <xsl:value-of select="$inventoryValue" />
                  </td>
               </tr>

               </xsl:for-each>

               </table>

               <p/>
               Order History Statistics:<p/>
               <table class="main" >

               <xsl:for-each select="STOREFRONT_ORDERS_HISTORY_STATISTICS_NAME" >
                  <xsl:variable name="numberOfOrders" select="STOREFRONT_ORDERS_HISTORY_STATISTICS_NUMBER_OF_ORDERS/value" />
                  <xsl:variable name="subTotal" select="STOREFRONT_ORDERS_HISTORY_STATISTICS_SUBTOTAL/value" />
                  <xsl:variable name="shippingCost" select="STOREFRONT_ORDERS_HISTORY_STATISTICS_SHIPPINGCOST/value" />
                  <xsl:variable name="taxes" select="STOREFRONT_ORDERS_HISTORY_STATISTICS_TAXES/value" />
                  <xsl:variable name="total" select="STOREFRONT_ORDERS_HISTORY_STATISTICS_TOTAL/value" />

               <tr>
                  <td>
                     Number Of Orders: 
                  </td>
                  <td>
                     <xsl:value-of select="$numberOfOrders" />
                  </td>
               </tr>

               <tr>
                  <td>
                     Sub Total: 
                  </td>
                  <td>
                     <xsl:value-of select="$subTotal" />
                  </td>
               </tr>

               <tr>
                  <td>
                     Shipping Cost: 
                  </td>
                  <td>
                     <xsl:value-of select="$shippingCost" />
                  </td>
               </tr>

               <tr>
                  <td>
                     Taxes: 
                  </td>
                  <td>
                     <xsl:value-of select="$taxes" />
                  </td>
               </tr>

               <tr>
                  <td>
                     Total: 
                  </td>
                  <td>
                     <xsl:value-of select="$total" />
                  </td>
               </tr>

               </xsl:for-each>

               </table>

               <xsl:for-each select="STOREFRONT_USERS_STATISTICS_NAME" >
                  <xsl:variable name="numberOfUsers" select="STOREFRONT_USERS_STATISTICS_NUMBER_OF_USERS/value" />

               </xsl:for-each>

            </xsl:for-each>     
   
         </xsl:for-each>
      </xsl:for-each>
          
   </xsl:template>
</xsl:stylesheet>