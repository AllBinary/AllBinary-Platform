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
package views.generic.payment;

import org.allbinary.logic.communication.log.LogFactory;

import java.util.Vector;


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.business.user.commerce.money.payment.Payment;
import org.allbinary.business.user.commerce.money.payment.PaymentData;


import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.data.tables.user.commerce.money.payment.PaymentEntityFactory;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class PaymentView  extends HttpStoreComponentView implements DomNodeInterface
{
   public PaymentView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);      
   }
      
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         Node paymentNode = document.createElement(PaymentData.PAYMENT);
         document.appendChild(paymentNode);
         
         Vector paymentVector = PaymentEntityFactory.getInstance().getPaymentEntityInstance().get(
                 this.getWeblisketSession().getUserName());
         int size = paymentVector.size();
         for (int index = 0; index < size; index++)
         {
            Payment payment = (Payment) paymentVector.get(index);
            paymentNode.appendChild(payment.toXmlNode(document));
         }

         return paymentNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"toXmlNode",e));
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
         String error = "Failed to view Payment";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         return error;
      }
   }
}
