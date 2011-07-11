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
    
   <xsl:template name="globalCommands" 
       xmlns:jsp="http://java.sun.com/JSP/Page" >

<jsp:scriptlet>

//unique commands Names
final String PRODUCTSEARCHNAME = "ProductSearchName";
final String SHIPPINGCHECKOUTNAME = "ShippingCheckoutName";
final String ACCOUNTNAME = "accountName";
final String LOGINNAME = "LoginName";
final String BODYLOGINNAME = "BodyLoginName";
final String NEWREGISTRATIONNAME = "NewRegistrationName";
final String QUOTEREGISTRATIONNAME = "QuoteRegistrationName";
final String MAKEPAYMENTNAME = "MakePaymentName";
final String QUOTEREQUESTNAME = "QuoteRequestName";
final String FINISHCHECKOUTNAME = "FinishCheckoutName";
final String HORIZONTALPRODUCTSEARCHNAME = "HorizontalProductSearchName";
final String MICROREGISTRATIONNAME = "microRegistrationName";
final String MINIREGISTRATIONNAME = "miniRegistrationName";
final String REGISTRATIONNAME = "registrationName";

//command values
final String CONTINUE = "Continue";
final String SEARCH = "Search";
final String LOGIN = "Login";
final String REGISTER = "Register";
final String NEWREGISTRATION = "New Customer";
final String CANCELORDER = "Cancel Order";
final String REVIEWORDERS = "Review Orders";
//final String QUOTEREQUEST = "Quote Request";
final String MAKEPAYMENT = "Click Here To Pay";
final String SUBMIT = "Submit";
final String FINISH = "Finish";
final String ADJUST = "Adjust";
final String INSERT = "Insert";
final String REMOVE = "Remove";
final String BUY = "Buy";
final String UPDATE = "Update";

</jsp:scriptlet>

    </xsl:template>

</xsl:stylesheet> 
