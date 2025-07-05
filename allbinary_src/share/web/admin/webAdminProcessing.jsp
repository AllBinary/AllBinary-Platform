<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
%>

<%

   final String EDIT = "Edit";
   final String UPDATE = "Update";
   final String INSERT = "Add";
   final String DELETE = "Delete";

   final String PRODUCTPAGES = "Product Pages";
   final String MAKEPUBLIC = "Make Public";
   
   final String GENERATESTORE = "Generate Store";
   
   //define tab commands
   final String CUSTOMIZERSTAB = "CustomizersTab";
   final String GENERATORSTAB = "GeneratorsTab";
   final String WORKFLOWSTAB = "WorkFlowsTab";
   final String WIZARDSTAB = "PaymentGatewayTab";
   final String VIEWSTAB = "ImportExportTab";
   final String OTHERTAB = "OtherTab";


//Store Section
   if(command!=null && tabCommand!=null && storeName!=null)
   {
//Payment Gateway
      if(tabCommand.compareTo(CUSTOMIZERSTAB)==0)
      {
         String selectedView = 
            request.getParameter(CustomizerTransformInfoData.NAME);
         if(selectedView != null)
         {
            
         if(command.compareTo(EDIT)==0)
         {
%>
<workflow:basic name="<%= selectedView %>" />
<p/>
<%
         }
         else
         if(command.compareTo(UPDATE)==0)
         {
            //currently data is set and the viewinfo is updated in the db instead of updating existing data 
            //(new implementation may allow multilple customizers to manipulate a single view)

            //add in view name
%>
<workflow:basic name="<%= selectedView %>" />
<%
         }

         }
      }
      else
      if(tabCommand.compareTo(GENERATORSTAB)==0)
      {
         if(command.compareTo(GENERATESTORE)==0)
         {
%>
<transform:generic storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>" >
</transform:generic>
<%
         }
         else
         if(command.compareTo(PRODUCTPAGES)==0)
         {
%>
<ecommerce:staticpages isSelected="true" command="<%= SearchData.GENERATESTATICPAGES %>"
   xsl="<%= JSPLISTING %>" />
<P></P>
<%
         }
         else
         if(command.compareTo(MAKEPUBLIC)==0)
         {
%>
<ecommerce:staticpages isSelected="true" command="<%= SearchData.MAKEPUBLIC %>" />
<P/>
<%
         }
      }
      else
      if(tabCommand.compareTo(WORKFLOWSTAB)==0)
      {
         if(command.compareTo(UPDATE)==0)
         {
%>
<%
         }
      }
      else
%>
<%
      if(tabCommand.compareTo(WIZARDSTAB)==0)
      {
         if(command.compareTo(UPDATE)==0)
         {
%>

<%
         }
      }
      else
%>

<%
      if(tabCommand.compareTo(VIEWSTAB)==0)
      {
         if(command.compareTo(UPDATE)==0)
         {
%>
<%
         }
      }
   }
%>