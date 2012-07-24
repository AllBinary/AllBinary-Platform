<%
/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
%>

<%
   final String EDIT = "Edit";
   final String UPDATE = "Update";
   final String INSERT = "Add";
   final String DELETE = "Delete";

   //define tab commands
   final String STORETAB = "StoreTab";
   final String PAYMENTGATEWAYTAB = "PaymentGatewayTab";
   final String IMPORTEXPORTTAB = "ImportExportTab";
   final String OTHERTAB = "OtherTab";
   
//Store Section
   if(command!=null && tabCommand!=null && storeName!=null)
   {
      if(tabCommand.compareTo(STORETAB)==0)
      {
         if(command.compareTo(UPDATE)==0)
         {
%>
   <admin:storefront
      command="<%= GLOBALS.UPDATE %>"
      storeName="<%= storeName %>"
      xsl="<%= IGNOREXMLXSL %>" >
      <ecommerce:storefronts isSelected="true" command="<%= GLOBALS.UPDATE %>" />
   </admin:storefront>
<%
         }
      }
      else
%>

<%
//Payment Gateway
      if(tabCommand.compareTo(PAYMENTGATEWAYTAB)==0)
      {
         if(command.compareTo(UPDATE)==0)
         {
%>
         <payment:gateway isSelected="true" command="<%= GLOBALS.UPDATE %>" />
<%
         }
         else
         if(command.compareTo(INSERT)==0)
         {
            String gatewayName = (String)
               request.getParameter(PaymentGatewayData.NAME.toString());

            String gatewayTemplateTransformInfo = 
               storeName + commonStrings.SPACE + gatewayName + commonStrings.SPACE;
%>
         <payment:gateway isSelected="true" command="<%= GLOBALS.INSERT %>" />

         <workflow:basic name="<%= storeName + commonStrings.SPACE + PAGETEMPLATECUSTOMIZERVIEWNAME %>" >

            <transform:generic storeName="<%= storeName %>"
               name="<%= gatewayTemplateTransformInfo + GeneratorTransformInfoData.NAME %>" >
            </transform:generic>
         
         </workflow:basic>
<%
         }
         else
         if(command.compareTo(DELETE)==0)
         {
%>
         <payment:gateway isSelected="true" command="<%= GLOBALS.DELETE %>" />
<%
         }

         if(command.compareTo(INSERT)==0 || command.compareTo(EDIT)==0)
         {
%>
<form method="post" action="<%= STOREADMINPAGE %>" >

<payment:gatewayView command="<%= GLOBALS.VIEW %>" xsl="<%= PAYMENTGATEWAYSXSL %>" />

<input type="hidden" 
   value="<%= PAYMENTGATEWAYTAB %>" 
   name="<%= TAB %>" />
<input class="submit" type="submit" 
   value="<%= GLOBALS.UPDATE %>" 
   name="<%= allbinary.globals.GLOBALS.ADMINCOMMAND %>">
</form>
<%
         }
      }
      else
%>   

<%
      if(tabCommand.compareTo(IMPORTEXPORTTAB)==0)
      {
         if(command.compareTo(GLOBALS.BACKUP)==0)
         {
            
         }
         else
         if(command.compareTo(GLOBALS.BACKUP)==0)
         {
            
         }
         //insert tags for store backup and restore
%>
<%
      }
   }
%>