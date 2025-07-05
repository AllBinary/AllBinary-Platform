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
   //Note: The views below are wrapped views used for all of the wrapper templates above
   final String JSP = "jsp";
   
   final String THEMEVIEWNAME = "Theme";
   
   final String INDEXPAGE = "index";
   
   final String ABOUTPAGE = "About";
   final String CONTACTPAGE = "Contact";
   final String SUPPORTPAGE = "Support";

   final String HELPPAGE = "Help";
   final String PRIVACYPAGE = "Privacy";
   final String TERMSANDCONDITIONSPAGE = "TermsAndConditions";

   final String FAQSPAGE = "Faqs";
   final String TESTIMONIALSPAGE = "Testimonials";
   
   final String LINKSPAGE = "Links";
   final String AUCTIONSPAGENAME = "Auctions";
   final String NEWSPAGE = "News";
   final String SERVICESPAGE = "Services";
   final String PORTFOLIOPAGE = "Portfolio";
   
   final String SEARCHPAGE = "Search";

   final String EMPTYBASKETPAGE = "EmptyBasket";
   final String BASKETPAGE = "Basket";   
   final String PRODUCTSPAGE = "Products";
   final String SUMMARYPAGE = "Summary";

   final String REVIEWPAGENAME = "Review";

   final String CUSTOMERPROFILEPAGE = "CustomerProfile";

   final String REGISTERPAGE = "Register";
   final String LOGINPAGENAME = "Login";
   final String LOGOUTPAGENAME = "Logout";
   
   final String QUOTEREQUESTPAGE = "QuoteRequest";
   final String QUOTEREQUESTCOMPLETEPAGE = "QuoteRequestComplete";
   
   final String ERRORPAGE = "Error";
   final String JSPERRORPAGE = "JspError";
   final String LICENSINGERRORPAGE = "LicensingError";

   //start Weblisket
final String WEBLISKETPAGE= "Weblisket";
final String FREEBLISKETPAGE= "Freeblisket";
   //end Weblisket

   //start AllBinary
final String ADVERTISINGPAGE= "Advertising";
final String APPLICATIONDEVELOPMENTPAGE= "AppDev";
final String DATABASEDEVELOPMENTPAGE= "DbDev";
final String EXAMPLESPAGE= "Examples";
final String J2MEDEVELOPMENTPAGE= "J2meDev";
final String WEBDEVELOPMENTPAGE= "WebDev";
final String WEBEXAMPLESPAGE= "WebExamples";
final String GUIEXAMPLESPAGE= "GuiExamples";
final String WEBHOSTINGPAGE= "WebHosting";
final String BASICWEBHOSTINGCOMPARISONPAGE= "BasicWebHostingComparison";
final String DEDICATEDWEBHOSTINGCOMPARISONPAGE= "DedicatedWebhostingComparison";

final String INDEXBODYPAGE= "IndexBody";
final String WEBHOSTINGBODYPAGE= "WebHostingBody";
   //end AllBinary

   //start DartStatistics
   final String DARTACCESSORIESCATEGORIESPAGE = "DartAccessoriesCategories";
   final String DARTSCATEGORIESPAGE = "DartsCategories";
   final String DARTSTATISTICSSOFTWAREPAGE = "DartStatisticsSoftware";
   //end DartStatistics
   
   //start militia gear and mg accessories
   final String MILITIAGEARACCESSORIESCATEGORIESPAGE = "MilitiaGearAccessoriesCategories";
   //end militia gear and mg accessories
   
   final String SILKYPRINTWIZARDPAGE = "SilkyPrintWizard";
   
   final String MOBILEAPPSPAGE = "MobileAppDev";
   final String MOBILEGAMESPAGE = "MobileGameDev";
   
   final String CUSTOMPAGE = "Custom";

   //Hedges
   final String HEADERPAGE = "topbar";
   final String HEADERALLPAGE = "topbarAll";
   final String HEADERCUSTOMEREXISTINGPAGE = "topbarCustomerExisting";
   final String HEADERPRODUCTSPAGE = "topbarProducts";
   final String HEADERSERVICESPAGE = "topbarServices";
   final String HEADERSERVICESCLIENTAREAPAGE = "topbarServicesClientArea";
   final String HEADERSERVICESSEPARATEWIDEPAGE = "topbarServicesSeparateWide";
   final String HEADERSERVICESTOGETHERWIDEPAGE = "topbarServicesTogetherWide";
   final String HEADERSERVICESSEPARATEPAGE = "topbarServicesSeparate";
   final String HEADERSERVICESTOGETHERPAGE = "topbarServicesTogether";
   final String HEADERSUBSCRIPTIONPAGE = "topbarSubscription";
   final String HEADERSUPPORTPAGE = "topbarSupport";

   final String LEFTALLHEDGEPAGE = "leftAll";
   final String LEFTCUSTOMERHEDGEPAGE = "leftCustomer";
   final String LEFTPRODUCTSHEDGEPAGE = "leftProducts";
   final String LEFTSERVICESHEDGEPAGE = "leftServices";
   final String LEFTSUBSCRIPTIONHEDGEPAGE = "leftSubscription";
   final String LEFTSUPPORTHEDGEPAGE = "leftSupport";

   final String RIGHTALLHEDGEPAGE = "rightAll";
   final String RIGHTCUSTOMERHEDGEPAGE = "rightCustomer";
   final String RIGHTPRODUCTSHEDGEPAGE = "rightProducts";
   final String RIGHTSERVICESHEDGEPAGE = "rightServices";
   final String RIGHTSUBSCRIPTIONHEDGEPAGE = "rightSubscription";
   final String RIGHTSUPPORTHEDGEPAGE = "rightSupport";
   
   final String FOOTERPAGE = "copyright";

   //includes
   final String CSSPAGE = "css";
   final String ADVANCEDCSSPAGE = "Advanced css";
   final String BASICCSSPAGE = "Basic css";
   final String CSSSTYLEONEPAGE = "cssStyleOne";
   final String CSSSTYLETWOPAGE = "cssStyleTwo";
   final String CSSSTYLETHREEPAGE = "cssStyleThree";
   final String CSSSTYLEFOURPAGE = "cssStyleFour";
   final String CSSSTYLEFIVEPAGE = "cssStyleFive";
   final String JAVASCRIPTPAGE = "javascript";
   final String GLOBALSPAGE = "globals";
   final String METASPAGE = "metatags";

   //hedge windows
   final String CATEGORYDROPDOWNMENUCOMPONENT = "categoryDropDownMenu";   
   final String CATEGORYLISTCOMPONENT = "categoryList";
   
   final String AUTHENTICATIONCOMPONENT = "authentication";
   final String CUSTOMERLINKSSTYLEONECOMPONENT = "customerLinksStyleOne";
   final String CUSTOMERLINKSSTYLETWOCOMPONENT = "customerLinksStyleTwo";
   final String CUSTOMERLINKSSTYLETHREECOMPONENT = "customerLinksStyleThree";
   final String MICROREGISTRATIONCOMPONENT = "microRegistration";
   final String MINIBASKETCOMPONENT = "miniBasket";
   final String MINIREGISTRATIONCOMPONENT = "miniRegistration";
   
   final String HELPCOMPONENT = "helpList";
   final String LIVEHELPCOMPONENT = "liveHelp";
   
   final String NEWSCOMPONENT = "newsList";
   
   final String PARTNERSCOMPONENT = "partnersList";
   
   final String CLEARANCEBESTCOMPONENT = "clearanceBest";
   final String PRODUCTSEARCHCOMPONENT = "productSearch";
   final String HORIZONTALPRODUCTSEARCHCOMPONENT = "horizontalProductSearch";
   final String PRODUCTBESTCOMPONENT = "productsBest";
   final String PRODUCTNEWCOMPONENT = "productsNew";
   final String PRODUCTSPOPULARCOMPONENT = "productsPopular";
   final String SALEPRODUCTSCOMPONENT = "saleProducts";
   final String SPECIALSCOMPONENT = "specials";
   
   final String REBATEBESTCOMPONENT = "rebatesBest";

   class PaymentGatewayPageData
   {
      public final static String MAKEPAYMENT = "MakePayment";

      public final static String PAYMENTOPTIONS = "PaymentOptions";

      public final static String FINISH = "PaymentFinish";
   }
   
   final String PAGETEMPLATECUSTOMIZERVIEWNAME = 
      "Page" + commonStrings.SPACE + CustomizerTransformInfoData.NAME;
%>