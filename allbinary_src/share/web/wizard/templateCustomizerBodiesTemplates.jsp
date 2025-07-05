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
   //Generic Body Customizer Form
   final String GENERICBODYCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "generic/generic.xsl";
   final String TITLEBODYCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "generic/title/title.xsl";

   final String ABOUTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "about/about.xsl";
   final String CONTACTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "contact/contact.xsl";
   final String SUPPORTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "support/support.xsl";
   
   final String HELPCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "help/help.xsl";
   final String PRIVACYCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "help/privacy/privacy.xsl";
   final String TERMSANDCONDITIONSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "help/termsAndConditions/termsAndConditions.xsl";
   final String FAQSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "help/faqs/faqs.xsl";

   final String TESTIMONIALSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "testimonials/testimonials.xsl";

   final String LINKSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "links/links.xsl";   
   final String AUCTIONSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "auctions/auctions.xsl";
   final String NEWSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "news/news.xsl";
   final String SERVICESCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "services/services.xsl";
   final String PORTFOLIOCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "portfolio/portfolio.xsl";
   
   final String BASKETCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "basket/basket.xsl";
   final String EMPTYBASKETCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "basket/emptyBasket.xsl";
   final String SEARCHCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "search/search.xsl";
   
   final String PRODUCTSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "products/products.xsl";
   final String SUMMARYCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "products/summary.xsl";
   
   final String REVIEWCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "customer/review/review.xsl";
   final String CUSTOMERPROFILECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "customer/profile/profile.xsl";
   
   final String REGISTERCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "register/register.xsl";
   final String LOGINCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "login/login.xsl";
   final String LOGOUTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "logout/logout.xsl";

   final String QUOTEREQUESTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "quote/request.xsl";
   final String QUOTEREQUESTCOMPLETECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "quote/requestComplete.xsl";

   final String ERRORCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/error.xsl";
   final String JSPERRORCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/jsperror.xsl";
   final String LICENSINGERRORCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "error/licensingerror.xsl";
   
   final String MAKEPAYMENTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "payment/makePayment.xsl";
   final String FINISHCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "payment/finish.xsl";
   
   final String PAYMENTOPTIONSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + PAYMENTGATEWAYDIR + "options/options.xsl";   

   final String INDEXCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "index/index.xsl";
   
   final String CUSTOMBODYCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + CUSTOMBODYDIR + "custom/customBody.xsl";
   
   final String CUSTOMPAGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + CUSTOMDIR + "customPage.xsl";
  
   HashMap viewNameKeyAndTemplateCustomizerBodiesValueHashMap = new HashMap();
   
   //Insert normal compound pages
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(ABOUTPAGE,ABOUTCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(CONTACTPAGE,CONTACTCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(SUPPORTPAGE,SUPPORTCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(HELPPAGE,HELPCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PRIVACYPAGE,PRIVACYCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(TERMSANDCONDITIONSPAGE,TERMSANDCONDITIONSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(FAQSPAGE,FAQSCUSTOMIZERTEMPLATEFILE);   
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(LINKSPAGE,LINKSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(AUCTIONSPAGENAME,AUCTIONSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(NEWSPAGE,NEWSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(SERVICESPAGE,SERVICESCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PORTFOLIOPAGE,PORTFOLIOCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(BASKETPAGE,BASKETCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(EMPTYBASKETPAGE,EMPTYBASKETCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(SEARCHPAGE,SEARCHCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PRODUCTSPAGE,PRODUCTSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(SUMMARYPAGE,SUMMARYCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(REVIEWPAGENAME,REVIEWCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(CUSTOMERPROFILEPAGE,CUSTOMERPROFILECUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(REGISTERPAGE,REGISTERCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(LOGINPAGENAME,LOGINCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(LOGOUTPAGENAME,LOGOUTCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(QUOTEREQUESTPAGE,QUOTEREQUESTCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(QUOTEREQUESTCOMPLETEPAGE,QUOTEREQUESTCOMPLETECUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(ERRORPAGE,ERRORCUSTOMIZERTEMPLATEFILE);   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(JSPERRORPAGE,JSPERRORCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(LICENSINGERRORPAGE,LICENSINGERRORCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PaymentGatewayPageData.MAKEPAYMENT,MAKEPAYMENTCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PaymentGatewayPageData.FINISH,FINISHCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(PaymentGatewayPageData.PAYMENTOPTIONS, PAYMENTOPTIONSCUSTOMIZERTEMPLATEFILE);
   

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
         String paymentGatewayCustomizerTemplateFile = 
                 TEMPLATECUSTOMIZERDIR + BODYDIR;

         viewNameKeyAndTemplateCustomizerBodiesValueHashMap.put(
            transformInfoName + commonStrings.SPACE + paymentGatewayPageName,
            paymentGatewayCustomizerTemplateFile);
      }
   }
%>