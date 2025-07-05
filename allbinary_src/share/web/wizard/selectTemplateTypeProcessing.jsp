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
final String TEMPLATECOMMAND = "TEMPLATE_COMMAND";
if(command!=null && command.compareTo(TEMPLATECOMMAND)==0)
{
   //Add base templates to db from files for store
   //Set Selected Template As Parent of other templates
   //viewName is in request
   //No objectConfigFile add with inner tag
   //No templateFile
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + RootTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + TransformInfosData.PREVIEW  + commonStrings.SPACE + RootTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + TransformInfosData.PREVIEW  + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + TransformInfosData.SMALL_PREVIEW  + commonStrings.SPACE + RootTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<transform:info 
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + TransformInfosData.SMALL_PREVIEW  + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>"
   objectFile="<%= CONTEXTROOTVIEWOBJECTFILE %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
String templateCustomizerViewName = 
   storeName + commonStrings.SPACE + GLOBALS.INSERT + commonStrings.SPACE + CustomizerTransformInfoData.NAME;
final String TEMPLATE_DOT = "template.";
final String CUSTOMER_VALIDATION_VIEW = "CustomizerValidationView";
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= templateCustomizerViewName %>"
   objectFile="<%= CUSTOMIZERSCLASSPATH + TEMPLATE_DOT + GLOBALS.INSERT + CUSTOMER_VALIDATION_VIEW %>"
   objectConfigFile="<%= ROOTTEMPLATESCUSTOMIZEROBJECTCONFIGFILE %>"
   templateFile=""
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<%@ include file="insertPaymentGatewayTransformTemplates.jsp" %>

<jsp:forward page="<%= ADDPAGES %>" />

<jsp:forward page="<%= FREEBLISKET_PATH_GLOBALS.getInstance().ERRORPAGE %>" />
<% 
}
%>
