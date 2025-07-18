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
package views.admin.payment.gateways;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewaysData;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import org.allbinary.business.user.commerce.money.payment.types.PaymentTypeUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class PaymentGatewaysView extends HttpStoreComponentView 
   implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private Vector paymentGatewayVector;

   public PaymentGatewaysView(TransformInfoInterface transformInfoInterface, Vector gatewayVector) throws Exception
   {
      super(transformInfoInterface);
      this.paymentGatewayVector = gatewayVector;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
    	  String storeName = this.getTransformInfoInterface().getStoreName();
    	  
         Node paymentGatewaysNode = 
            document.createElement(PaymentGatewaysData.NAME);
         
         int size = this.paymentGatewayVector.size();
         for (int index = 0; index < size; index++)
         {
            BasicPaymentType paymentType = (BasicPaymentType) this.paymentGatewayVector.get(index);
            HashMap hashMap = paymentType.toHashMap();

            //Get the Default for payment Option Screen
            hashMap.put(EntryData.getInstance().DEFAULT, 
            		PaymentTypeUtil.getInstance().getDefault(storeName).getBasicPaymentType().getName());

            Node paymentGatewayNode = 
               ModDomHelper.createNodeWithValueNodes(
                  document, PaymentGatewayData.NAME.toString(), hashMap);

            paymentGatewaysNode.appendChild(paymentGatewayNode);
         }
         return paymentGatewaysNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            logUtil.put(this.commonStrings.FAILURE, this, "toXmlNode", e);
         }
         throw e;
      }
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view()
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view payment gateways";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         return error;
      }
   }
}
