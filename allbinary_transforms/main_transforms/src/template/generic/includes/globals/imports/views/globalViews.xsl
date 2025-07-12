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
   <xsl:output method="xml" indent="yes" />

    <xsl:template name="globalViews"
                  xmlns:jsp="http://java.sun.com/JSP/Page"
    >

<jsp:scriptlet>

final String GENERICDIR = "generic/";

final String USERDIR = GENERICDIR + "user/";

final String ADDRESSDIR = USERDIR + "address/";
final String BILLINGADDRESSDIR = ADDRESSDIR + "billing/";
final String SHIPPINGADDRESSDIR = ADDRESSDIR + "shipping/";

final String COMMERCEDIR = USERDIR + "commerce/";
final String INVENTORYDIR = COMMERCEDIR + "inventory/";
final String BASKETDIR = INVENTORYDIR + "basket/";
final String ITEMDIR = INVENTORYDIR + "item/";
final String MONEYDIR = COMMERCEDIR + "money/";
final String SHIPPINGDIR = COMMERCEDIR + "shipping/";

final String ORDERDIR = INVENTORYDIR + "order/";
final String ORDEREMAILDIR = ORDERDIR + "email/";
final String ORDERHISTORYDIR = ORDERDIR + "history/";
final String ORDERREVIEWDIR = ORDERDIR + "review/";

final String PAYMENTDIR = MONEYDIR + "payment/";

final String GENERATIONDIR = GENERICDIR + "generation/";

final String PRODUCTSDIR = GENERICDIR + "products/";

//used as a place holder for future view
final String IGNOREXMLXSL = GENERICDIR + "ignoreXmlView.xsl";

//Dynamic Views
final String REVIEWORDERVIEWXSL = ORDERREVIEWDIR + "reviewOrderView.xsl";
final String ORDERVIEWXSL = REVIEWORDERVIEWXSL;

final String INVENTORYXSL = INVENTORYDIR + "inventory.xsl";

final String BASKETXSL = BASKETDIR + "basket.xsl";
final String CHECKOUTBASKETXSL = BASKETDIR + "staticBasket.xsl";
final String MINIBASKETXSL = BASKETDIR + "miniBasket.xsl";

final String BILLINGADDRESSCHANGEFORMXSL = BILLINGADDRESSDIR + "billingAddressChangeForm.xsl";
final String BILLINGADDRESSINPUTFORMXSL = BILLINGADDRESSDIR + "billingAddressInputForm.xsl";
final String BILLINGADDRESSVIEWXSL = BILLINGADDRESSDIR + "billingAddressView.xsl";

final String SHIPPINGADDRESSCHANGEFORMXSL = SHIPPINGADDRESSDIR + "shippingAddressChangeForm.xsl";
final String SHIPPINGADDRESSINPUTFORMXSL = SHIPPINGADDRESSDIR + "shippingAddressInputForm.xsl";
final String SHIPPINGADDRESSVIEWXSL = SHIPPINGADDRESSDIR + "shippingAddressView.xsl";

final String NEWTAXADDRESSXSL = SHIPPINGADDRESSDIR + "newTaxAddressView.xsl";
final String EDITTAXADDRESSXSL = SHIPPINGADDRESSDIR + "editTaxAddressView.xsl";

final String SHIPPINGMETHODSXSL = SHIPPINGDIR + "shippingMethods.xsl";

final String ORDERCUSTOMEREMAILVIEWXSL = ORDEREMAILDIR + "orderCustomerEmailView.xsl";
final String ORDERMANAGEREMAILVIEWXSL = ORDEREMAILDIR + "orderManagerEmailView.xsl";

final String EDITCUSTOMERXSL = USERDIR + "editCustomerInputForm.xsl";

final String ORDERHISTORYXSL = ORDERHISTORYDIR + "orderHistory.xsl";

final String PAYMENTVIEWXSL = PAYMENTDIR + "paymentView.xsl";

final String SUMMARYXSL = ITEMDIR + "summary/summary.xsl";

final String PAYMENTGATEWAYOPTIONSXSL = PAYMENTDIR + "paymentGatewayOptions.xsl";
final String MAKEPAYMENTVIEWXSL = PAYMENTDIR + "invoice/" + "makePayment.xsl";

</jsp:scriptlet>

    </xsl:template>

</xsl:stylesheet> 
