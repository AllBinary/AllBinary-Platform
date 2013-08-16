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
package allbinary.business.user.commerce.inventory.order.status;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.user.UserInterface;
import allbinary.business.user.commerce.inventory.order.OrderHistory;
import allbinary.data.tables.user.UserEntityFactory;
import allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;
import allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import allbinary.logic.communication.smtp.info.AdminEmailInfo;
import allbinary.logic.communication.smtp.info.BasicEmailInfo;
import allbinary.logic.communication.smtp.info.EmailInfo;
import allbinary.logic.communication.smtp.info.StoreEmailInfo;

public class OrderStatusEmail
{
   private OrderHistory orderHistory;
   private StoreFrontInterface storeFrontInterface;
   private UserInterface user;
   
/*
import allbinary.business.user.commerce.inventory.order.OrderHistoryData;
      OrderHistoryData.PREPROCESSING
      OrderHistoryData.PROCESSING

      OrderHistoryData.PARTIALLYSHIPPED
      OrderHistoryData.CANCELLED
      OrderHistoryData.SHIPPED
 **/
   
   public OrderStatusEmail(OrderHistory orderHistory) throws Exception
   {
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
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
            AdminUserEmailEventHandlerSingletons.getInstance(
               userEmailEventNameData);

         UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance(
               userEmailEventNameData,
               this.storeFrontInterface);

         storeAdminUserEmailEventHandler.receiveEmailInfo(userEmailEventNameData, storeAdminEmailInfo);
         adminUserEmailEventHandler.receiveEmailInfo(userEmailEventNameData, adminEmailInfo);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "emailAdmin", e));
         }
         //throw e;
      }
   }

   ///String subject, String textBody
   private void notifyUser() throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
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
            UserEmailEventHandlerSingletons.getInstance(
               userEmailEventNameData, this.user);

         userEmailEventHandler.receiveEmailInfo(userEmailEventNameData, emailInfo);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "notifyUser", e));
         }
         throw e;
      }
   }
}
