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

import java.util.Vector;

import views.admin.payment.gateway.PaymentGatewayViewAbstract;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class SelectNewView extends PaymentGatewayViewAbstract
{
   public SelectNewView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }

   public String view() throws Exception
   {
      try
      {
         PaymentGatewayEntityInterface paymentGatewayEntityInterface = 
            PaymentGatewayEntityFactory.getInstance();

         Vector existingGateways =
            paymentGatewayEntityInterface.findPaymentTypeVectorByStore(
               this.getPaymentGatewayPrimaryKey().getStoreName());

         Vector allGatewaysLessExisting = BasicPaymentTypeUtil.getInstance().difference(existingGateways);

         return new PaymentGatewaysView(
            this.getTransformInfoInterface(), allGatewaysLessExisting).view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
             LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "view", e));
         }
         throw e;
      }
   }
}
