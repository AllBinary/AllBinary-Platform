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
package allbinary.logic.communication.smtp.event;

import java.util.HashMap;

import allbinary.business.user.commerce.inventory.order.OrderHistoryData;

public class UserEmailEventNameData
{
   private static HashMap userNameEvenNameHashMap = new HashMap();
   private static int nextId = 0;
   private int eventNameId;
   private String eventHandlerName;
   
   public UserEmailEventNameData(String eventHandlerName)
   {
      this.eventHandlerName = eventHandlerName;
      this.initNextId();
      this.userNameEvenNameHashMap.put(this.eventHandlerName, this);
   }
   
   private synchronized void initNextId()
   {
      this.eventNameId = UserEmailEventNameData.nextId;
      UserEmailEventNameData.nextId++;
   }

   public static final UserEmailEventNameData INSTALLER =
      new UserEmailEventNameData("Installer");
   
   //Program wide
   public static final UserEmailEventNameData EXCEPTION =
      new UserEmailEventNameData("Exception");
   
   public static final UserEmailEventNameData LOGGEDEXCEPTION =
      new UserEmailEventNameData("Logged Exception");
   
   public static final UserEmailEventNameData ERROR =
      new UserEmailEventNameData("Error");
   
   public static final UserEmailEventNameData OUTOFMEMORY =
      new UserEmailEventNameData("Out Of Memory Error");
   
   //Admin Section
   public static final UserEmailEventNameData ADMINGENERATINGSTATICPAGES =
      new UserEmailEventNameData("Admin Generating Static Pages");
   
   //Store Admin Section
   public static final UserEmailEventNameData STOREERROROCCURRED =
      new UserEmailEventNameData("Store Error Occurred");
   
   public static final UserEmailEventNameData STOREOUTOFMEMORY =
      new UserEmailEventNameData("Store Out Of Memory Error");
   
   public static final UserEmailEventNameData STORECREATED =
      new UserEmailEventNameData("Store Created");
   
   public static final UserEmailEventNameData STOREOPENED =
      new UserEmailEventNameData("Store Opened");
   
   public static final UserEmailEventNameData STORECLOSED =
      new UserEmailEventNameData("Store Closed");
   
   public static final UserEmailEventNameData STOREGENERATINGSTATICPAGES =
      new UserEmailEventNameData("Store Generating Static Pages");
   
   //Inventory
   public static final UserEmailEventNameData PRODUCTISOUT =
      new UserEmailEventNameData("Product Is Out");
   
   public static final UserEmailEventNameData PRODUCTISAVAILABLE =
      new UserEmailEventNameData("Product Is Available");
   
   public static final UserEmailEventNameData PRODUCTISOLD =
      new UserEmailEventNameData("Product Is Old");
   
   public static final UserEmailEventNameData PRODUCTISONSALE =
      new UserEmailEventNameData("Product Is On Sale");
   
   public static final UserEmailEventNameData PRODUCTSALEISOVER =
      new UserEmailEventNameData("Product Sale Is Over");
   
   //Orders
   public static final UserEmailEventNameData ORDERPREPROCESSING =
      new UserEmailEventNameData(OrderHistoryData.PREPROCESSING);
   
   public static final UserEmailEventNameData ORDERPROCESSING =
      new UserEmailEventNameData(OrderHistoryData.PROCESSING);
   
   public static final UserEmailEventNameData ORDERCANCELLED =
      new UserEmailEventNameData(OrderHistoryData.CANCELLED);
   
   public static final UserEmailEventNameData ORDERPARTIALLYSHIPPED =
      new UserEmailEventNameData(OrderHistoryData.PARTIALLYSHIPPED);
   
   public static final UserEmailEventNameData ORDERSHIPPED =
      new UserEmailEventNameData(OrderHistoryData.SHIPPED);
   
   //Misc
   public static final UserEmailEventNameData QUOTEREQUEST =
      new UserEmailEventNameData("Quote Request");
   
   public static final UserEmailEventNameData NEWPASSWORD =
      new UserEmailEventNameData("New Password");
   
   public boolean isEvent(String userEmailEventNameString)
   {
      return this.userNameEvenNameHashMap.containsKey(userEmailEventNameString);
   }
   
   public static synchronized UserEmailEventNameData getInstance(
      String userEmailEventNameString) throws Exception
   {
      UserEmailEventNameData userEmailEventNameData =
         (UserEmailEventNameData)
         UserEmailEventNameData.userNameEvenNameHashMap.get(
         userEmailEventNameString);
      
      if(userEmailEventNameData == null)
      {
         throw new Exception();
      }
      else
      {
         return userEmailEventNameData;
      }
   }
   
   public String toString()
   {
      return this.eventHandlerName;
   }
}
