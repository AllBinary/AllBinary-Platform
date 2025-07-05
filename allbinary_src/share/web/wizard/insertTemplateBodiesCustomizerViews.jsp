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

<%@ include file="templateCustomizerBodiesTemplates.jsp" %>
<%@ include file="templateCustomizerBodiesObjectConfigs.jsp" %>
<%@ include file="templateCustomizerBodiesClassPaths.jsp" %>

<%
/*
Inserting page template customizer views for page views
 */
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + PAGETEMPLATECUSTOMIZERVIEWNAME %>"
   objectFile="<%= PAGEVIEWOBJECT %>"
   objectConfigFile="<%= PAGETEMPLATECUSTOMIZEROBJECTCONFIGFILE %>"
   type="<%= InputOutputTypeData.DB %>"
   templateFile="<%= IGNOREXMLXSL %>" >
</transform:info>

<%@ include file="insertPaymentGatewayPageCustomizerTransformInfos.jsp" %>

<%
/*
Inserting template body customizer form views for page views
 */

   Set customizerSet = 
      viewNameKeyAndTemplateCustomizerBodiesValueHashMap.keySet();
      //viewNameKeyAndTemplateBodyCustomizationFormObjectConfigsValueHashMap.keySet();
   Iterator customizerIter = customizerSet.iterator();
   while(customizerIter.hasNext())
   {
      String nextPage = (String) customizerIter.next();
      //String nextTemplateFile = (String) 
      //   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.get(nextPage);
      //templateFile="%= nextTemplateFile %"
      
      //String nextObjectConfigFile = (String) 
      //   viewNameKeyAndTemplateCustomizationFormObjectConfigsValueHashMap.get(nextPage);

      //String nextClassPath = (String) 
      //   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.get(nextPage);
      //objectFile="%= STOREFRONTCONTEXTCLASSPATH + "customizer." + nextClassPath + "." + nextViewPrefix + nextPage + "ValidationView" %"
      //objectFile="%= STOREFRONTCONTEXTCLASSPATH + "customizer." + "bodies" + "." + "generic" + "." + nextViewPrefix + nextPage + "ValidationView" %"
%>

<%
/*
Inserting customization form views - they allow the user to modify the generic 
 *content in a generic template by setting the xml.
 */
//generate view files on the fly using page string with generated and final prefix and final postfix
//Customizer
//objectConfigFile="%= nextObjectConfigFile %"

/*
   command="%= GLOBALS.INSERT %"
   storeName="%= storeName %"
   objectFile="%= GENERICBODYCUSTOMIZERVIEWOBJECTPACKAGE + nextViewPrefix + GENERICBODYCUSTOMIZERVIEWOBJECT %"
   objectConfigFile="%= GENERICBODYCUSTOMIZEROBJECTCONFIGFILE %"
   type="%= InputOutputTypeData.DB %"
   name="%= storeName + commonStrings.SPACE + nextViewPrefix + commonStrings.SPACE + nextPage + " Generic" + commonStrings.SPACE + BodyData.VIEWNAMEKEY + commonStrings.SPACE + CustomizerTransformInfoData.NAME %"
   templateFile="%= GENERICBODYCUSTOMIZERTEMPLATEFILE %" 
*/
      Iterator viewPrefixIter = viewPrefixVector.iterator();
      while(viewPrefixIter.hasNext())
      {
         String nextViewPrefix = (String) viewPrefixIter.next();
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + nextViewPrefix + commonStrings.SPACE + nextPage + commonStrings.SPACE + TitleData.VIEWNAMEKEY + commonStrings.SPACE + BodyData.VIEWNAMEKEY + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>"
   objectFile="<%= TITLEBODYCUSTOMIZERVIEWOBJECTPACKAGE + nextViewPrefix + TITLEBODYCUSTOMIZERVIEWOBJECT %>"
   objectConfigFile="<%= GENERICBODYCUSTOMIZEROBJECTCONFIGFILE %>"
   type="<%= InputOutputTypeData.DB %>"
   templateFile="<%= TITLEBODYCUSTOMIZERTEMPLATEFILE %>" >
</transform:info>
<%
      }
   }
%>
