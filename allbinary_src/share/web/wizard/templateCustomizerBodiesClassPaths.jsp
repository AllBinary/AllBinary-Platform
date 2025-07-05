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
   //Custom View Objects   
   HashMap viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap = new HashMap();

   final String BODYCLASSPATH = "body.";

   //Insert normal compound pages
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(ABOUTPAGE,BODYCLASSPATH + "about");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(CONTACTPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(SUPPORTPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(HELPPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(LINKSPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(AUCTIONSPAGENAME,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(NEWSPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(SERVICESPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(PORTFOLIOPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(BASKETPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(EMPTYBASKETPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(SEARCHPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(PRODUCTSPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(SUMMARYPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(REVIEWPAGENAME,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(CUSTOMERPROFILEPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(REGISTERPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(LOGINPAGENAME,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(LOGOUTPAGENAME,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(QUOTEREQUESTPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(QUOTEREQUESTCOMPLETEPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(ERRORPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(JSPERRORPAGE,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(LICENSINGERRORPAGE,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(PaymentGatewayPageData.MAKEPAYMENT,BODYCLASSPATH + "");
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(PaymentGatewayPageData.FINISH,BODYCLASSPATH + "");
   
   viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(PaymentGatewayPageData.PAYMENTOPTIONS,BODYCLASSPATH + "");

   //Add The Specific Payment Gateway Views
   paymentGatewaySet = rootPaymentGatewayTemplatesTransformInfoHashMap.keySet();
   paymentGatewayIter = paymentGatewaySet.iterator();
   while(paymentGatewayIter.hasNext())
   {
      String transformInfoName = (String) paymentGatewayIter.next();

      set = paymentGatewayTemplatesTransformInfoHashMap.keySet();
      iter = set.iterator();
      while(iter.hasNext())
      {
         String paymentGatewayPageName = (String) iter.next();
         String paymentGatewayTemplateObject = BODYCLASSPATH + "";

         viewNameKeyAndTemplateBodyCustomizationClassPathsValueHashMap.put(
            transformInfoName + commonStrings.SPACE + paymentGatewayPageName,
            paymentGatewayTemplateObject);
      }
   }
%>