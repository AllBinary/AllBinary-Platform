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
   HashMap paymentGatewayTemplatesTransformInfoHashMap = 
      new TransformInfoPropertiesDocument(URLGLOBALS.getWebappPath() + 
      "wizard/rootPaymentGatewayTemplatesTransformInfo.xml"
      ).toTransformInfoPropertiesHashMap();

   Set set = paymentGatewayTemplatesTransformInfoHashMap.keySet();
   Iterator iter = set.iterator();
   while(iter.hasNext())
   {
      String viewName = (String) iter.next();
      TransformInfoProperties transformInfoProperties = 
         (TransformInfoProperties) 
            paymentGatewayTemplatesTransformInfoHashMap.get(viewName);

      String gatewayTemplateTransformInfo = storeName + commonStrings.SPACE + 
         viewName + commonStrings.SPACE + GeneratorTransformInfoData.NAME;
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= gatewayTemplateTransformInfo %>"
   objectFile="<%= OVERRIDEOBEJCTCONFIGCONTEXTROOTVIEWOBJECTFILE %>"
   objectConfigFile="<%= transformInfoProperties.getObjectConfigFile() %>"
   templateFile=""
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
   }
%>
