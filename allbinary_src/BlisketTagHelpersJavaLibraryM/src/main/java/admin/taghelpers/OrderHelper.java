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
package admin.taghelpers;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.order.Order;

import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderProcessorUtil;

import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;


import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntity;
import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;

import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import java.util.HashMap;
import java.util.Vector;

public class OrderHelper
    extends TagHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private WeblisketSession weblisketSession;
    private StoreFrontInterface storeFrontInterface;
    private HashMap propertiesHashMap;
    private PageContext pageContext;
    private HttpServletRequest request;

    public OrderHelper(HashMap propertiesHashMap, PageContext pageContext)
    {
        this.propertiesHashMap = propertiesHashMap;
        this.pageContext = pageContext;

        this.request = (HttpServletRequest) pageContext.getRequest();

        String storeName = (String) propertiesHashMap.get(StoreFrontData.getInstance().NAME);

        if (!StringValidationUtil.getInstance().isEmpty(storeName))
        {
            this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
        }

        this.weblisketSession =
            new WeblisketSession(this.propertiesHashMap, this.pageContext);
    }

    public Boolean setPaymentGateway()
    {
        try
        {
            Boolean paymentGatewayBoolean = Boolean.FALSE;

            OrderInterface orderInterface = this.weblisketSession.getOrder();

            //Set Payment Method to Request Param
            String requestPaymentGateway = (String) request.getParameter(PaymentGatewayData.NAME.toString());
            if (!StringValidationUtil.getInstance().isEmpty(requestPaymentGateway))
            {
                this.weblisketSession.setPaymentMethod(requestPaymentGateway);
                orderInterface.setPaymentMethod(requestPaymentGateway);
                paymentGatewayBoolean = Boolean.TRUE;
            }

            //Otherwise set if only one payment gateway is available
            PaymentGatewayEntity paymentGatewayEntityInterface = (PaymentGatewayEntity) PaymentGatewayEntityFactory.getInstance();
            Vector paymentTypeVector =
                paymentGatewayEntityInterface.findPaymentTypeVectorByStore(
                this.weblisketSession.getStoreName());

            if (paymentTypeVector.size() == 1)
            {
                BasicPaymentType paymentType = (BasicPaymentType) paymentTypeVector.get(0);
                PaymentGatewayInterface paymentGatewayInterface =
                    (PaymentGatewayInterface) paymentGatewayEntityInterface.getPaymentGatewayInterface(
                    this.weblisketSession.getStoreName(), paymentType);

                String paymentGateway = paymentGatewayInterface.getName();
                this.weblisketSession.setPaymentMethod(paymentGateway);
                orderInterface.setPaymentMethod(paymentGateway);
                paymentGatewayBoolean = Boolean.TRUE;
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Successfully set PaymentGateway Order: ");
                stringBuffer.append(orderInterface.getId());
                stringBuffer.append(" to: ");
                stringBuffer.append(orderInterface.getPaymentMethod());

                logUtil.put(stringBuffer.toString(), this, "setPaymentGateway()");
            }
            return paymentGatewayBoolean;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Failed to set PaymentGateway for Order: ");

                try
                {
                    OrderInterface orderInterface = this.weblisketSession.getOrder();
                    if (orderInterface != null)
                    {
                        stringBuffer.append(orderInterface.getId());
                        stringBuffer.append(" to: ");
                        stringBuffer.append(orderInterface.getPaymentMethod());
                    }
                } catch (Exception ex)
                {
                    stringBuffer.append(" Exception Getting");
                }

                logUtil.put(stringBuffer.toString(), this, "setPaymentGateway()", e);
            }
            return Boolean.FALSE;
        }
    }

    public String process()
    {
        try
        {
            OrderInterface order = this.weblisketSession.getOrder();
            order.setStoreName(this.storeFrontInterface.getName());
            String result = OrderProcessorUtil.getInstance().process(
                this.weblisketSession.getUserName(), (Order) order);

            //OrderHistoryEntityFactory.getInstance().setPaymentMethod(order.getId(),this.paymentMethod);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                logUtil.put("Successfully Processed Order: " + result, this, "processOrder()");
            }
            return result;

        } catch (Exception e)
        {
            final StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Failed to Process Order: ");

            try
            {
                stringBuffer.append(this.weblisketSession.getOrder().getId());

            } catch (Exception ex)
            {
                stringBuffer.append(" Exception Getting Id");
            }

            final String error = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
            }
            return error;
        }
    }

    /*
    public Boolean authorize()
    {
    try
    {
    OrderInterface order = getOrder();
    OrderHistory orderReview = OrderHistoryEntityFactory.getInstance().getOrder(order.getId());
    Boolean result = orderReview.authorizeDelayedCaptureCreditCard();

    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
    {
    logUtil.put("Successfully Authorized Order: " + result,this,"authorizeOrder()");
    }
    return result;
    }
    catch(Exception e)
    {
    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
    {
    logUtil.put(commonStrings.EXCEPTION,this,"authorizeOrder()",e);
    }
    return Boolean.FALSE;
    }
    }
     */
}
