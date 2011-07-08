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

import abcs.logic.communication.log.LogFactory;
import java.util.Iterator;
import java.util.Vector;


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.logic.communication.log.LogUtil;


import allbinary.business.user.commerce.money.payment.Payment;
import allbinary.business.user.commerce.money.payment.PaymentData;


import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.data.tables.user.commerce.money.payment.PaymentEntityFactory;

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
         Iterator iter = paymentVector.iterator();
         while(iter.hasNext())
         {
            Payment payment = (Payment) iter.next();
            paymentNode.appendChild(payment.toXmlNode(document));
         }

         return paymentNode;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"toXmlNode",e));
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         return error;
      }
   }
}
