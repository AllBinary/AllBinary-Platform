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
package allbinary.logic.visual.transform.info;

/**
 *
 * @author user
 */
public class BasicViewData
{
    private static final BasicViewData instance = new BasicViewData();

    public static BasicViewData getInstance()
    {
        return instance;
    }

    private BasicViewData()
    {
    }

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
    public final String PAYMENTDIR = MONEYDIR + "payment/";
    final String GENERATIONDIR = GENERICDIR + "generation/";
    final String PRODUCTSDIR = GENERICDIR + "products/";
//used as a place holder for future view
    public final String IGNOREXMLXSL = GENERICDIR + "ignoreXmlView.xsl";
//Dynamic Views
    public final String REVIEWORDERVIEWXSL = ORDERREVIEWDIR + "reviewOrderView.xsl";
    public final String ORDERVIEWXSL = REVIEWORDERVIEWXSL;
    public final String INVENTORYXSL = INVENTORYDIR + "inventory.xsl";
    public final String BASKETXSL = BASKETDIR + "basket.xsl";
    public final String CHECKOUTBASKETXSL = BASKETDIR + "staticBasket.xsl";
    public final String MINIBASKETXSL = BASKETDIR + "miniBasket.xsl";
    public final String BILLINGADDRESSCHANGEFORMXSL = BILLINGADDRESSDIR + "billingAddressChangeForm.xsl";
    public final String BILLINGADDRESSINPUTFORMXSL = BILLINGADDRESSDIR + "billingAddressInputForm.xsl";
    public final String BILLINGADDRESSVIEWXSL = BILLINGADDRESSDIR + "billingAddressView.xsl";
    public final String SHIPPINGADDRESSCHANGEFORMXSL = SHIPPINGADDRESSDIR + "shippingAddressChangeForm.xsl";
    public final String SHIPPINGADDRESSINPUTFORMXSL = SHIPPINGADDRESSDIR + "shippingAddressInputForm.xsl";
    public final String SHIPPINGADDRESSVIEWXSL = SHIPPINGADDRESSDIR + "shippingAddressView.xsl";
    public final String NEWTAXADDRESSXSL = SHIPPINGADDRESSDIR + "newTaxAddressView.xsl";
    public final String EDITTAXADDRESSXSL = SHIPPINGADDRESSDIR + "editTaxAddressView.xsl";
    public final String SHIPPINGMETHODSXSL = SHIPPINGDIR + "shippingMethods.xsl";
    public final String ORDERCUSTOMEREMAILVIEWXSL = ORDEREMAILDIR + "orderCustomerEmailView.xsl";
    public final String ORDERMANAGEREMAILVIEWXSL = ORDEREMAILDIR + "orderManagerEmailView.xsl";
    public final String EDITCUSTOMERXSL = USERDIR + "editCustomerInputForm.xsl";
    public final String ORDERHISTORYXSL = ORDERHISTORYDIR + "orderHistory.xsl";
    public final String PAYMENTVIEWXSL = PAYMENTDIR + "paymentView.xsl";
    public final String SUMMARYXSL = ITEMDIR + "summary/summary.xsl";
    public final String PAYMENTGATEWAYOPTIONSXSL = PAYMENTDIR + "paymentGatewayOptions.xsl";
    public final String MAKEPAYMENTVIEWXSL = PAYMENTDIR + "invoice/" + "makePayment.xsl";
}
