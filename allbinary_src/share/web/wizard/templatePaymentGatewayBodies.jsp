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