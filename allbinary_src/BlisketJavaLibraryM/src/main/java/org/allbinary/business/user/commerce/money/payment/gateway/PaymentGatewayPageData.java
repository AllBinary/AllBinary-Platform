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
package org.allbinary.business.user.commerce.money.payment.gateway;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author user
 */
public class PaymentGatewayPageData
{
    private final static String EXTENSION = ".jsp";
    //private final static String NAME = "PaymentGateway";
    private final static String NAME = StringUtil.getInstance().EMPTY_STRING;
    //Payment pages automatically added for each possible payment processor
    public final static String FORWARDMAKEPAYMENT = "MakePayment.jsp";
    public final String MAKEPAYMENT;
    public final static String PAYMENTOPTIONS = "PaymentOptions" + PaymentGatewayPageData.EXTENSION;
    public final static String STARTCHECKOUT = "StartCheckout" + PaymentGatewayPageData.EXTENSION;
    public final String CHECKOUT;
    public final String SHIPPING;
    //String FORWARDSHIPPINGADDRESSPAGE = paymentMethod + SHIPPINGADDRESSPAGENAME;
    public final String SHIPPINGADDRESS;
    public final String SHIPPINGADDRESSACTION;
    public final String BILLINGADDRESS;
    public final String PAYMENT;
    public final String AUTHORIZE;
    public final String FINISH;

    public PaymentGatewayPageData(String location, String paymentMethod)
    {
        final String paymentMethodString = PaymentGatewayPageData.NAME + paymentMethod;

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(location);
        stringBuffer.append("MakePayment");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        MAKEPAYMENT = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("Checkout");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        CHECKOUT = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("Shipping");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        SHIPPING = stringBuffer.toString();

        //String FORWARDSHIPPINGADDRESSPAGE = paymentMethod + SHIPPINGADDRESSPAGENAME;

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("ShippingAddress");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        SHIPPINGADDRESS = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("ShippingAddressAction");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        SHIPPINGADDRESSACTION = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("BillingAddress");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        BILLINGADDRESS = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("Payment");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        PAYMENT = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("Authorize");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        AUTHORIZE = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append(paymentMethodString);
        stringBuffer.append("PaymentFinish");
        stringBuffer.append(PaymentGatewayPageData.EXTENSION);

        FINISH = stringBuffer.toString();
    }
}
