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
