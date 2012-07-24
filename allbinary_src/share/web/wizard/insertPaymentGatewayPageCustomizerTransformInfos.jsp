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
