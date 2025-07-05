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
   set = paymentGatewayTemplatesTransformInfoHashMap.keySet();
   iter = set.iterator();
   while(iter.hasNext())
   {
      //Actually Payment Gateway Name
      String viewName = (String) iter.next();
      TransformInfoProperties transformInfoProperties = 
         (TransformInfoProperties) 
            paymentGatewayTemplatesTransformInfoHashMap.get(viewName);

      String gatewayTemplateTransformInfo = storeName + commonStrings.SPACE + 
         viewName + commonStrings.SPACE  + PAGETEMPLATECUSTOMIZERVIEWNAME;
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= gatewayTemplateTransformInfo %>"
   objectFile="<%= PAGEVIEWOBJECT %>"
   objectConfigFile="<%= PAGETEMPLATECUSTOMIZEROBJECTCONFIGFILE %>"
   type="<%= InputOutputTypeData.DB %>"
   templateFile="<%= IGNOREXMLXSL %>" >
</transform:info>
<%
   }
%>
