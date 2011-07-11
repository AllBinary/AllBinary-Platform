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

   <xsl:template name="orderHistory" >   
      <xsl:param name="reviewPage" />

      <xsl:for-each select="ORDERS" >
      
   <xsl:if test="not(ORDERHISTORY)" >
      No Orders Returned<br /><br />
   </xsl:if>
   
   <xsl:if test="ORDERHISTORY" >
   
      <xsl:variable name="VIEWOLDORDER" select="VIEWOLDORDER/value" />
      <xsl:variable name="PREPROCESSING" select="PREPROCESSING/value" />      
      <xsl:variable name="PROCESSING" select="PROCESSING/value" />
      <xsl:variable name="CANCELLED" select="CANCELLED/value" />
      <xsl:variable name="SHIPPED" select="SHIPPED/value" />
      <xsl:variable name="PARTIALLYSHIPPED" select="PARTIALLYSHIPPED/value" />
         
<table border="1" class="table" >
<tr><td>User Name</td><td>Order ID</td><td>Date Ordered</td><td>Status</td><td>Total</td></tr>      
      
      <xsl:for-each select="ORDERHISTORY" >
             
         <xsl:variable name="userName" select="CUSTOMER_USER_NAME/value" />
         
         <xsl:variable name="orderStatus" select="ORDERHISTORY_STATUS/value" />
         
         <xsl:variable name="orderIdName" select="ORDER/ORDER_ID/name" />
         <xsl:variable name="orderId" select="ORDER/ORDER_ID/value" />
         
         <xsl:variable name="orderTotal" select="ORDERHISTORY_TOTAL/value" />         

         <xsl:variable name="orderDate" select="ORDERHISTORY_ORDER_DATE_FORMATTED/value" />

         <xsl:if test="$orderStatus=$PREPROCESSING" >
<tr>
<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;CUSTOMER_USER_NAME={$userName}">
<xsl:value-of select="$userName" />
</a>
</td>
<td>
<a href="{$reviewPage}?AdminCommand=View&#38;{$orderIdName}={$orderId}">
<xsl:value-of select="$orderId" />
</a>
</td>
<td>
<xsl:value-of select="$orderDate" />
</td>
<td>
<xsl:value-of select="$orderStatus" />
</td><td>
<xsl:value-of select="$orderTotal" />
</td>
</tr>
         </xsl:if>

                  <xsl:if test="$orderStatus=$PROCESSING" >
<tr>
<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;CUSTOMER_USER_NAME={$userName}">
<xsl:value-of select="$userName" />
</a>
</td>
<td>
<a href="{$reviewPage}?AdminCommand=View&#38;{$orderIdName}={$orderId}">
<xsl:value-of select="$orderId" />
</a>
</td>
<td>
<xsl:value-of select="$orderDate" />
</td>
<td>
<xsl:value-of select="$orderStatus" />
</td><td>
<xsl:value-of select="$orderTotal" />
</td>
</tr>
         </xsl:if>

         <xsl:if test="$orderStatus=$CANCELLED" >
<tr>
<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;CUSTOMER_USER_NAME={$userName}">
<xsl:value-of select="$userName" />
</a>
</td>
<td>
<a href="{$reviewPage}?AdminCommand=View&#38;{$orderIdName}={$orderId}">
<xsl:value-of select="$orderId" />
</a>
</td>
<td>
<xsl:value-of select="$orderDate" />
</td>
<td>
<xsl:value-of select="$orderStatus" />
</td><td>
<xsl:value-of select="$orderTotal" />
</td>
</tr>
         </xsl:if>                  

         <xsl:if test="$orderStatus=$SHIPPED" >
<tr>
<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;CUSTOMER_USER_NAME={$userName}">
<xsl:value-of select="$userName" />
</a>
</td>
<td>
<a href="{$reviewPage}?AdminCommand=View&#38;{$orderIdName}={$orderId}">
<xsl:value-of select="$orderId" />
</a>
</td>
<td>
<xsl:value-of select="$orderDate" />
</td>
<td>
<xsl:value-of select="$orderStatus" />
</td><td>
<xsl:value-of select="$orderTotal" />
</td>
</tr>
         </xsl:if>                  

         <xsl:if test="$orderStatus=$PARTIALLYSHIPPED" >
<tr>
<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;CUSTOMER_USER_NAME={$userName}">
<xsl:value-of select="$userName" />
</a>
</td>
<td>
<a href="{$reviewPage}?AdminCommand=View&#38;{$orderIdName}={$orderId}">
<xsl:value-of select="$orderId" />
</a>
</td>
<td>
<xsl:value-of select="$orderDate" />
</td>
<td>
<xsl:value-of select="$orderStatus" />
</td><td>
<xsl:value-of select="$orderTotal" />
</td>
</tr>
         </xsl:if>
                           
      </xsl:for-each>
      
</table>
<br></br>
   
         </xsl:if>
      </xsl:for-each>
            
   </xsl:template>
</xsl:stylesheet> 
