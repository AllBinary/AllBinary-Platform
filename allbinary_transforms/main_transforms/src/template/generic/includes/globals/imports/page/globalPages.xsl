<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="globalPages"
                  xmlns:jsp="http://java.sun.com/JSP/Page"
    >

<jsp:scriptlet>

final String ABOUTPAGE= LOCATION + "About.jsp";
final String CONTACTPAGE= LOCATION + "Contact.jsp";
final String SUPPORTPAGE= LOCATION + "Support.jsp";
final String HELPPAGE= LOCATION + "Help.jsp";
final String LINKSPAGE= LOCATION + "Links.jsp";
final String AUCTIONSPAGE= LOCATION + "Auctions.jsp";
final String NEWSPAGE= LOCATION + "News.jsp";
final String SERVICESPAGE= LOCATION + "Services.jsp";
final String PORTFOLIOPAGE= LOCATION + "Portfolio.jsp";
   
//final String EMPTYBASKETPAGE = LOCATION + "EmptyBasket.jsp";
final String FORWARDEMPTYBASKETPAGE = "EmptyBasket.jsp";

final String FORUMPAGE = "./forum/";

final String BASKETPAGE= LOCATION + "Basket.jsp";
final String SEARCHPAGE= LOCATION + "Search.jsp";
final String PRODUCTSPAGE= LOCATION + "Products.jsp";

   final String REVIEWPAGE= LOCATION + "Review.jsp";
   final String FORWARDREVIEWPAGE = "Review.jsp";

   final String CUSTOMERPROFILEPAGE = LOCATION + "CustomerProfile.jsp";
   final String FORWARDCUSTOMERPROFILEPAGE = "CustomerProfile.jsp";

   final String REGISTERPAGE= LOCATION + "Register.jsp";

   final String LOGINPAGE = LOCATION + "Login.jsp";
   final String FORWARDLOGINPAGE = "Login.jsp";
   
   final String LOGOUTPAGE = LOCATION + "Logout.jsp";
   final String FORWARDLOGOUTPAGE = "Logout.jsp";

   final String QUOTEREQUESTPAGE= LOCATION + "QuoteRequest.jsp";
   final String FORWARDQUOTEREQUESTPAGE = "QuoteRequest.jsp";
   //final String QUOTEREQUESTCOMPLETEPAGE= LOCATION + "QuoteRequestComplete.jsp";
   final String FORWARDQUOTEREQUESTCOMPLETEPAGE= "QuoteRequestComplete.jsp";
   
   //final String PAYMENTERRORPAGE = LOCATION + "PaymentError.jsp";
   final String FORWARDPAYMENTERRORPAGE = "PaymentError.jsp";
   
   final String ERRORPAGE = LOCATION + "Error.jsp";
   final String FORWARDERRORPAGE = "Error.jsp";
   final String JSPERRORPAGE = LOCATION + "JspError.jsp";
   final String LICENSINGERRORPAGE = LOCATION + "LicensingError.jsp";   

   final BasicWeblisketSession basicWeblisketSession = 
      new BasicWeblisketSession(pageContext);
   final String paymentMethod = 
      (String) basicWeblisketSession.getPaymentMethod();

   class PaymentGatewayPageData
   {
      private final static String EXTENSION = ".jsp";
      //private final static String NAME = "PaymentGateway";
      private final static String NAME = "";

      //Payment pages automatically added for each possible payment processor
      public final String  MAKEPAYMENT = 
         LOCATION + "MakePayment" + EXTENSION;
      public final static String  FORWARDMAKEPAYMENT = "MakePayment.jsp";

      public final String PAYMENTOPTIONS = "PaymentOptions" + EXTENSION;

      public final String STARTCHECKOUT = "StartCheckout" + EXTENSION;

      public final String CHECKOUT = 
         PaymentGatewayPageData.NAME + paymentMethod + "Checkout" + EXTENSION;

      public final String SHIPPING = 
         PaymentGatewayPageData.NAME + paymentMethod + "Shipping" + EXTENSION;

      //String FORWARDSHIPPINGADDRESSPAGE = paymentMethod + SHIPPINGADDRESSPAGENAME;

      public final String SHIPPINGADDRESS = 
         PaymentGatewayPageData.NAME + paymentMethod + "ShippingAddress" + EXTENSION;

      public final String SHIPPINGADDRESSACTION = 
         PaymentGatewayPageData.NAME + paymentMethod + "ShippingAddressAction" + EXTENSION;

      public final String BILLINGADDRESS = 
         PaymentGatewayPageData.NAME + paymentMethod + "BillingAddress" + EXTENSION;

      public final String PAYMENT = 
         PaymentGatewayPageData.NAME + paymentMethod + "Payment" + EXTENSION;

      public final String AUTHORIZE = 
         PaymentGatewayPageData.NAME + paymentMethod + "Authorize" + EXTENSION;

      public final String FINISH = 
         PaymentGatewayPageData.NAME + paymentMethod + "PaymentFinish" + EXTENSION;
   }

   PaymentGatewayPageData paymentGatewayPageData = 
      new PaymentGatewayPageData();
   
   final String PRIVACYPAGE = LOCATION + "Privacy.jsp";
   final String TERMSANDCONDITIONSPAGE = LOCATION + "TermsAndConditions.jsp";

   final String HOMEPAGE= LOCATION + "index.jsp";

   //Components
   final String CATEGORYDROPDOWNMENUCOMPONENTPAGE = LOCATION + "categoryDropDown.jsp";   
   final String CATEGORYLISTCOMPONENTPAGE = LOCATION + "categoryList.jsp";

   final String AUTHENTICATIONCOMPONENTPAGE = LOCATION + "authentication.jsp";
   final String MICROREGISTRATIONCOMPONENTPAGE = LOCATION + "microRegistration.jsp";
   final String MINIBASKETCOMPONENTPAGE = LOCATION + "miniBasket.jsp";
   final String MINIREGISTRATIONCOMPONENTPAGE = LOCATION + "miniRegistration.jsp";

   final String HELPCOMPONENTPAGE = LOCATION + "helpList.jsp";

   final String NEWSCOMPONENTPAGE = LOCATION + "newsList.jsp";

   final String PARTNERSCOMPONENTPAGE = LOCATION + "partnersList.jsp";   

   final String CLEARANCEBESTCOMPONENTPAGE = LOCATION + "clearanceBest.jsp";
   final String PRODUCTSEARCHCOMPONENTPAGE = LOCATION + "productSearch.jsp";
   final String HORIZONTALPRODUCTSEARCHCOMPONENTPAGE = LOCATION + "horizontalProductSearch.jsp";
   final String PRODUCTBESTCOMPONENTPAGE = LOCATION + "productsBest.jsp";
   final String PRODUCTNEWCOMPONENTPAGE = LOCATION + "productsNew.jsp";
   final String PRODUCTSPOPULARCOMPONENTPAGE = LOCATION + "productsPopular.jsp";
   final String SALEPRODUCTSCOMPONENTPAGE = LOCATION + "saleProducts.jsp";
   final String SPECIALSCOMPONENTPAGE = LOCATION + "specials.jsp";

   final String REBATEBESTCOMPONENTPAGE = LOCATION + "rebatesBest.jsp";

</jsp:scriptlet>

   </xsl:template>

</xsl:stylesheet>
