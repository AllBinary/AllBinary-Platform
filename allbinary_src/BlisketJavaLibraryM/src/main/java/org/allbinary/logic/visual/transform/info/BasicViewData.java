/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.logic.visual.transform.info;

/**
 *
 * @author user
 */
public class BasicViewData
{
    private static final BasicViewData instance = new BasicViewData();

    public static BasicViewData getInstance()
    {
        return BasicViewData.instance;
    }

    private BasicViewData()
    {
    }

    final String GENERICDIR = "generic/";
    final String USERDIR = this.GENERICDIR + "user/";
    final String ADDRESSDIR = this.USERDIR + "address/";
    final String BILLINGADDRESSDIR = this.ADDRESSDIR + "billing/";
    final String SHIPPINGADDRESSDIR = this.ADDRESSDIR + "shipping/";
    final String COMMERCEDIR = this.USERDIR + "commerce/";
    final String INVENTORYDIR = this.COMMERCEDIR + "inventory/";
    final String BASKETDIR = this.INVENTORYDIR + "basket/";
    final String ITEMDIR = this.INVENTORYDIR + "item/";
    final String MONEYDIR = this.COMMERCEDIR + "money/";
    final String SHIPPINGDIR = this.COMMERCEDIR + "shipping/";
    final String ORDERDIR = this.INVENTORYDIR + "order/";
    final String ORDEREMAILDIR = this.ORDERDIR + "email/";
    final String ORDERHISTORYDIR = this.ORDERDIR + "history/";
    final String ORDERREVIEWDIR = this.ORDERDIR + "review/";
    public final String PAYMENTDIR = this.MONEYDIR + "payment/";
    final String GENERATIONDIR = this.GENERICDIR + "generation/";
    final String PRODUCTSDIR = this.GENERICDIR + "products/";
//used as a place holder for future view
    public final String IGNOREXMLXSL = this.GENERICDIR + "ignoreXmlView.xsl";
//Dynamic Views
    public final String REVIEWORDERVIEWXSL = this.ORDERREVIEWDIR + "reviewOrderView.xsl";
    public final String ORDERVIEWXSL = this.REVIEWORDERVIEWXSL;
    public final String INVENTORYXSL = this.INVENTORYDIR + "inventory.xsl";
    public final String BASKETXSL = this.BASKETDIR + "basket.xsl";
    public final String CHECKOUTBASKETXSL = this.BASKETDIR + "staticBasket.xsl";
    public final String MINIBASKETXSL = this.BASKETDIR + "miniBasket.xsl";
    public final String BILLINGADDRESSCHANGEFORMXSL = this.BILLINGADDRESSDIR + "billingAddressChangeForm.xsl";
    public final String BILLINGADDRESSINPUTFORMXSL = this.BILLINGADDRESSDIR + "billingAddressInputForm.xsl";
    public final String BILLINGADDRESSVIEWXSL = this.BILLINGADDRESSDIR + "billingAddressView.xsl";
    public final String SHIPPINGADDRESSCHANGEFORMXSL = this.SHIPPINGADDRESSDIR + "shippingAddressChangeForm.xsl";
    public final String SHIPPINGADDRESSINPUTFORMXSL = this.SHIPPINGADDRESSDIR + "shippingAddressInputForm.xsl";
    public final String SHIPPINGADDRESSVIEWXSL = this.SHIPPINGADDRESSDIR + "shippingAddressView.xsl";
    public final String NEWTAXADDRESSXSL = this.SHIPPINGADDRESSDIR + "newTaxAddressView.xsl";
    public final String EDITTAXADDRESSXSL = this.SHIPPINGADDRESSDIR + "editTaxAddressView.xsl";
    public final String SHIPPINGMETHODSXSL = this.SHIPPINGDIR + "shippingMethods.xsl";
    public final String ORDERCUSTOMEREMAILVIEWXSL = this.ORDEREMAILDIR + "orderCustomerEmailView.xsl";
    public final String ORDERMANAGEREMAILVIEWXSL = this.ORDEREMAILDIR + "orderManagerEmailView.xsl";
    public final String EDITCUSTOMERXSL = this.USERDIR + "editCustomerInputForm.xsl";
    public final String ORDERHISTORYXSL = this.ORDERHISTORYDIR + "orderHistory.xsl";
    public final String PAYMENTVIEWXSL = this.PAYMENTDIR + "paymentView.xsl";
    public final String SUMMARYXSL = this.ITEMDIR + "summary/summary.xsl";
    public final String PAYMENTGATEWAYOPTIONSXSL = this.PAYMENTDIR + "paymentGatewayOptions.xsl";
    public final String MAKEPAYMENTVIEWXSL = this.PAYMENTDIR + "invoice/" + "makePayment.xsl";
}
