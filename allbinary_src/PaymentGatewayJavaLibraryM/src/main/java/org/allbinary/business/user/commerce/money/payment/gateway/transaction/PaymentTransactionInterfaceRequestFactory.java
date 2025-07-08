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
package org.allbinary.business.user.commerce.money.payment.gateway.transaction;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.data.generator.OrderIdGenerator;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.inventory.basket.Basket;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.payment.Payment;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.commerce.money.payment.types.TenderTypeFactory;
import org.allbinary.business.user.commerce.money.payment.types.TransactionTypeFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityInterface;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;

public class PaymentTransactionInterfaceRequestFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PaymentTransactionInterfaceRequestFactory instance
            = new PaymentTransactionInterfaceRequestFactory();

    public static PaymentTransactionInterfaceRequestFactory getInstance() {
        return instance;
    }

    private PaymentTransactionInterfaceRequestFactory()
    {
    }

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public PaymentTransactionInterface getInstance(TransformInfoInterface transformInfoInterface)
            throws Exception
    {
        try
        {
            TransformInfoHttpInterface httpTransformInfoInterface
                    = (TransformInfoHttpInterface) transformInfoInterface;

            PageContext pageContext = httpTransformInfoInterface.getPageContext();

            HttpServletRequest httpServletRequest
                    = (HttpServletRequest) pageContext.getRequest();

            String command
                    = httpServletRequest.getParameter(org.allbinary.globals.GLOBALS2.ADMINCOMMAND);

            //Only if testing a gateway with a form instead of using OrderHistory
            if(command != null && command.compareTo(org.allbinary.globals.GLOBALS2.AUTHORIZEFORMEVALBODYONERROR) == 0)
            {
                return generateFromTestData(transformInfoInterface);
            }else
            {

                HashMap propertiesHashMap = httpTransformInfoInterface.getPropertiesHashMap();

                WeblisketSession weblisketSession
                        = new WeblisketSession(propertiesHashMap, pageContext);

                StoreFrontInterface storeFrontInterface
                        = StoreFrontFactory.getInstance(weblisketSession.getStoreName());

                OrderInterface order = weblisketSession.getOrder();
                String orderId = order.getId();

                OrderHistoryEntityInterface orderHistoryEntityInterface
                        = OrderHistoryEntityFactory.getInstance();

                OrderHistory orderReview
                        = orderHistoryEntityInterface.getOrder(orderId);
                Payment orderPaymentInfo = orderReview.getPaymentInfo();
                orderPaymentInfo.setTransactionType(TransactionTypeFactory.getInstance().SALE.toString());
                orderPaymentInfo.setTenderType(TenderTypeFactory.getInstance().CREDITCARD.toString());
                orderReview.setPaymentInfo(orderPaymentInfo);

                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
                {
                    logUtil.put(orderReview.getId(), this, "getPaymentTransactionInterface()");
                }
            //HashMap hashMap = new HashMap(httpServletRequest.getParameterMap());
                //this.paymentGatewayInterface = new PaymentGateway(hashMap);
                PaymentTransactionInterface paymentTransactionInterface
                        = PaymentTransactionInterfaceFactory.getInstance().getInstance(orderReview);
                return paymentTransactionInterface;
            }

        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "getPaymentTransactionInterface()", e);
            }
            throw e;
        }
    }

    private PaymentTransactionInterface generateFromTestData(TransformInfoInterface transformInfoInterface)
            throws Exception
    {
        try
        {
            TransformInfoHttpInterface httpTransformInfoInterface
                    = (TransformInfoHttpInterface) transformInfoInterface;

            HashMap propertiesHashMap = httpTransformInfoInterface.getPropertiesHashMap();
            PageContext pageContext = httpTransformInfoInterface.getPageContext();

            WeblisketSession weblisketSession
                    = new WeblisketSession(propertiesHashMap, pageContext);

            StoreFrontInterface storeFrontInterface
                    = StoreFrontFactory.getInstance(weblisketSession.getStoreName());

            HttpServletRequest httpServletRequest
                    = (HttpServletRequest) pageContext.getRequest();

            String gatewayName
                    = (String) weblisketSession.getPaymentMethod();

            if(StringValidationUtil.getInstance().isEmpty(gatewayName))
            {
                gatewayName = httpServletRequest.getParameter(
                        PaymentGatewayData.NAME.toString());
            }

            String transactionType
                    = httpServletRequest.getParameter(PaymentData.TRANSACTIONTYPE);
            String tenderType
                    = httpServletRequest.getParameter(PaymentData.TENDERTYPE);

            Payment payment = new Payment();
            payment.setTransactionType(transactionType);
            payment.setTenderType(tenderType);

            OrderHistory orderReview = new OrderHistory(new Basket());
            orderReview.setStoreName(storeFrontInterface.getName());
            orderReview.setPaymentMethod(gatewayName);
            orderReview.setUserName("testing");

            String orderId = httpServletRequest.getParameter("orderNumber");
            if(orderId == null || orderId.compareTo(StringUtil.getInstance().EMPTY_STRING) == 0)
            {
                orderId = new OrderIdGenerator().getNext();
                orderReview.setId(orderId);
                //payment information
                String account = httpServletRequest.getParameter("account");
                String expirationDate = httpServletRequest.getParameter("expirationDate");

                String checkNumber = httpServletRequest.getParameter("checkNumber");
                String driversLicense = httpServletRequest.getParameter("driversLicense");

                String magneticInkCheckReader
                        = httpServletRequest.getParameter("magneticInkCheckReader");

                String aba = httpServletRequest.getParameter("aba");
                String accountType = httpServletRequest.getParameter("accountType");
                String name = httpServletRequest.getParameter("name");

                payment.setName(name);
                payment.setNumber(account);
                payment.setExpiration(expirationDate);

                payment.setCheckNumber(checkNumber);
                payment.setDriversLicense(driversLicense);
                payment.setMagneticInkCheckReader(magneticInkCheckReader);
                payment.setAba(aba);
                payment.setAccountType(accountType);

                //billing address info
                String street = httpServletRequest.getParameter("street");
                String city = httpServletRequest.getParameter("city");
                String state = httpServletRequest.getParameter("state");
                String zip = httpServletRequest.getParameter("zip");

                StreetAddress streetAddress = new StreetAddress();
                streetAddress.setName(name);
                streetAddress.setStreet(street);
                streetAddress.setCity(city);
                streetAddress.setState(state);
                streetAddress.setCode(zip);

                String email = httpServletRequest.getParameter("email");
                String amount = httpServletRequest.getParameter("amount");

                orderReview.setTotal(new Money(amount));
                orderReview.setBillingAddress(streetAddress);
            }else
            {
                orderReview.setId(orderId);
            }
            orderReview.setPaymentInfo(payment);

            PaymentTransactionInterface paymentTransactionInterface
                    = PaymentTransactionInterfaceFactory.getInstance().getInstance(orderReview);
            return paymentTransactionInterface;
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, "generatePaymentTransactionInterfaceFromTestData()", e);
            }
            throw e;
        }
    }
}
