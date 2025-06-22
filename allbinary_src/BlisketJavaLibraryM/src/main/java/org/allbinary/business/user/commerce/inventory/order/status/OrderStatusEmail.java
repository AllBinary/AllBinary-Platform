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
package org.allbinary.business.user.commerce.inventory.order.status;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.AdminEmailInfo;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.communication.smtp.info.StoreEmailInfo;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class OrderStatusEmail
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final AbeClientInformationInterface abeClientInformation;
    
   private OrderHistory orderHistory;
   private StoreFrontInterface storeFrontInterface;
   private UserInterface user;
   
/*
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
      OrderHistoryData.PREPROCESSING
      OrderHistoryData.PROCESSING

      OrderHistoryData.PARTIALLYSHIPPED
      OrderHistoryData.CANCELLED
      OrderHistoryData.SHIPPED
 **/
   
   public OrderStatusEmail(final AbeClientInformationInterface abeClientInformation, final OrderHistory orderHistory) throws Exception
   {
       this.abeClientInformation = abeClientInformation;
      this.orderHistory = orderHistory;

      String storeName = orderHistory.getStoreName();

      this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);

      this.user =
         UserEntityFactory.getInstance().getUser(orderHistory.getUserName());
   }

   public void process() throws Exception
   {
      this.notifyStoreAdmin();
      this.notifyUser();
   }
   
   private void notifyStoreAdmin() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Order Email", this, "emailStoreAdmin"));
         }

         String adminEmailSubject = "Order " + this.orderHistory.getStatus() + " Attempt";
         String adminEmailTextBody = "Order attempt: " + this.orderHistory.getId() + 
            " Preprocessing by: " + user.getUserName();

         BasicEmailInfo adminBasicEmailInfo = (BasicEmailInfo)
            new AdminEmailInfo(adminEmailSubject, adminEmailTextBody);

         BasicEmailInfo storeAdminBasicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, adminEmailSubject, adminEmailTextBody);

         EmailInfo storeAdminEmailInfo = new EmailInfo(storeAdminBasicEmailInfo);

         EmailInfo adminEmailInfo = new EmailInfo(adminBasicEmailInfo);
         
         UserEmailEventNameData userEmailEventNameData =
            UserEmailEventNameData.getInstance(this.orderHistory.getStatus());
         
         //Send response to Admin(s)
         UserEmailEventHandler adminUserEmailEventHandler =
            AdminUserEmailEventHandlerSingletons.getInstance().getInstance(
               this.abeClientInformation, userEmailEventNameData);

         UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance().getInstance(
               userEmailEventNameData, this.abeClientInformation, this.storeFrontInterface);

         storeAdminUserEmailEventHandler.receiveEmailInfo(userEmailEventNameData, storeAdminEmailInfo);
         adminUserEmailEventHandler.receiveEmailInfo(userEmailEventNameData, adminEmailInfo);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "emailAdmin", e));
         }
         //throw e;
      }
   }

   ///String subject, String textBody
   private void notifyUser() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Email User", this, "notifyUser()"));
         }

         String subject = "Order " + this.orderHistory.getStatus() + " Attempt";
         String textBody = "Order attempt: " + this.orderHistory.getId() + 
            " Preprocessing by: " + user.getUserName();
         
         BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
            new StoreEmailInfo(this.storeFrontInterface, subject, textBody);

         EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

         UserEmailEventNameData userEmailEventNameData =
            UserEmailEventNameData.getInstance(this.orderHistory.getStatus());

         UserEmailEventHandler userEmailEventHandler =
            UserEmailEventHandlerSingletons.getInstance().getInstance(
               this.abeClientInformation, userEmailEventNameData, this.user);

         userEmailEventHandler.receiveEmailInfo(userEmailEventNameData, emailInfo);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "notifyUser", e));
         }
         throw e;
      }
   }
}
