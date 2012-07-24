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
   final String PAYMENTGATEWAYTEMPLATEDIR = TEMPLATEDIR + BODYDIR + PAYMENTGATEWAYDIR;

   HashMap rootPaymentGatewayTemplatesTransformInfoHashMap = 
      new TransformInfoPropertiesDocument(URLGLOBALS.getWebappPath() + 
      "wizard/rootPaymentGatewayTemplatesTransformInfo.xml"
      ).toTransformInfoPropertiesHashMap();

   HashMap paymentGatewayTemplatesTransformInfoHashMap = 
      new TransformInfoPropertiesDocument(URLGLOBALS.getWebappPath() + 
      "wizard/paymentGatewayTemplatesTransformInfo.xml"
      ).toTransformInfoPropertiesHashMap();
   
   Set paymentGatewaySet = rootPaymentGatewayTemplatesTransformInfoHashMap.keySet();
   Iterator paymentGatewayIter = paymentGatewaySet.iterator();
   while(paymentGatewayIter.hasNext())
   {
      String transformInfoName = (String) paymentGatewayIter.next();

      Replace replace = new Replace("_", FilePathData.SEPARATOR);
      String transformInfoDir = 
         replace.all(transformInfoName) + FilePathData.SEPARATOR;

      Set set = paymentGatewayTemplatesTransformInfoHashMap.keySet();
      Iterator iter = set.iterator();
      while(iter.hasNext())
      {
         String paymentGatewayTransformInfoName = (String) iter.next();

         TransformInfoProperties transformInfoProperties = 
            (TransformInfoProperties) 
               paymentGatewayTemplatesTransformInfoHashMap.get(
                  paymentGatewayTransformInfoName);

         viewNameKeyAndCompoundTemplateValueHashMap.put(
            transformInfoName + commonStrings.SPACE + paymentGatewayTransformInfoName,
            PAYMENTGATEWAYTEMPLATEDIR + transformInfoDir + 
            transformInfoProperties.getTemplateFile());
      }
   }
%>