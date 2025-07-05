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
   final String ABOUTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "about/about.xsl";
   final String CONTACTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "contact/contact.xsl";
   final String SUPPORTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "support/support.xsl";
   
   final String HELPTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "help/help.xsl";
   final String PRIVACYTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "help/privacy/privacy.xsl";
   final String TERMSANDCONDITIONSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "help/termsAndConditions/termsAndConditions.xsl";
   final String FAQSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "help/faqs/faqs.xsl";

   final String TESTIMONIALSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "testimonials/testimonials.xsl";
   
   final String LINKSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "links/links.xsl";
   final String AUCTIONSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "auctions/auctions.xsl";
   final String NEWSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "news/news.xsl";
   final String SERVICESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "services/services.xsl";
   final String PORTFOLIOTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "portfolio/portfolio.xsl";
   
   final String BASKETTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "basket/basket.xsl";
   final String EMPTYBASKETTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "basket/emptyBasket.xsl";
   final String SEARCHTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "search/search.xsl";
   
   final String PRODUCTSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "products/products.xsl";
   final String SUMMARYTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "products/summary.xsl";
   
   final String REVIEWTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "customer/review/review.xsl";
   
   final String CUSTOMERPROFILETEMPLATEFILE = TEMPLATEDIR + BODYDIR + "customer/profile/profile.xsl";
   
   final String REGISTERTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "register/register.xsl";
   final String LOGINTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "login/login.xsl";
   final String LOGOUTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "logout/logout.xsl";
      
   final String QUOTEREQUESTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "quote/request.xsl";
   final String QUOTEREQUESTCOMPLETETEMPLATEFILE = TEMPLATEDIR + BODYDIR + "quote/requestComplete.xsl";

   final String ERRORTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "error/error.xsl";
   final String JSPERRORTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "error/jsperror.xsl";
   final String LICENSINGERRORTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "error/licensingerror.xsl";
   
   final String MAKEPAYMENTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "payment/makePayment.xsl";
   final String FINISHTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "payment/finish.xsl";
   
   final String PAYMENTOPTIONSTEMPLATEFILE = TEMPLATEDIR + BODYDIR + PAYMENTGATEWAYDIR + "options/options.xsl";   
   
   final String INDEXTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "index/index.xsl";
   final String PREVIEWINDEXTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "index/indexPreview.xsl";

   final String CUSTOMBODYTEMPLATEFILE = TEMPLATEDIR + CUSTOMBODYDIR + "custom/customBody.xsl";

   final String CUSTOMPAGETEMPLATEFILE = TEMPLATEDIR + CUSTOMDIR + "customPage.xsl";

   HashMap viewNameKeyAndCompoundTemplateValueHashMap = new HashMap();

   //Insert normal compound pages
   viewNameKeyAndCompoundTemplateValueHashMap.put(INDEXPAGE,INDEXTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(INDEXPAGE + commonStrings.SPACE + TransformInfosData.PREVIEW, PREVIEWINDEXTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(ABOUTPAGE,ABOUTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(CONTACTPAGE,CONTACTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(SUPPORTPAGE,SUPPORTTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(HELPPAGE,HELPTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(PRIVACYPAGE,PRIVACYTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(TERMSANDCONDITIONSPAGE,TERMSANDCONDITIONSTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(LINKSPAGE,LINKSTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(AUCTIONSPAGENAME,AUCTIONSTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(NEWSPAGE,NEWSTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(SERVICESPAGE,SERVICESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(PORTFOLIOPAGE,PORTFOLIOTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(BASKETPAGE,BASKETTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(EMPTYBASKETPAGE,EMPTYBASKETTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(SEARCHPAGE,SEARCHTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(PRODUCTSPAGE,PRODUCTSTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(SUMMARYPAGE,SUMMARYTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(REVIEWPAGENAME,REVIEWTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(CUSTOMERPROFILEPAGE,CUSTOMERPROFILETEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(REGISTERPAGE,REGISTERTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(LOGINPAGENAME,LOGINTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(LOGOUTPAGENAME,LOGOUTTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(QUOTEREQUESTPAGE,QUOTEREQUESTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(QUOTEREQUESTCOMPLETEPAGE,QUOTEREQUESTCOMPLETETEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(ERRORPAGE,ERRORTEMPLATEFILE);   
   viewNameKeyAndCompoundTemplateValueHashMap.put(JSPERRORPAGE,JSPERRORTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(LICENSINGERRORPAGE,LICENSINGERRORTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(PaymentGatewayPageData.MAKEPAYMENT,MAKEPAYMENTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(PaymentGatewayPageData.FINISH,FINISHTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(PaymentGatewayPageData.PAYMENTOPTIONS,PAYMENTOPTIONSTEMPLATEFILE);

%>
<%@ include file="templatePaymentGatewayBodies.jsp" %>
<%@ include file="templateHedges.jsp" %>
<%@ include file="templateComponents.jsp" %>
<%@ include file="templateBodiesAllBinary.jsp" %>
<%@ include file="templateBodiesDartStatistics.jsp" %>
<%@ include file="templateBodiesMilitiaGear.jsp" %>
<%@ include file="templateBodiesSilkyPrint.jsp" %>
<%@ include file="templateBodiesWW275.jsp" %>