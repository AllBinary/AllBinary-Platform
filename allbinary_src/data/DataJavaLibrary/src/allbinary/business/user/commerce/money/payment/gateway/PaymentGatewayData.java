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
package allbinary.business.user.commerce.money.payment.gateway;

public class PaymentGatewayData
{
   public final static int CRYPTNUM = 5;

   private String key;

   public static final PaymentGatewayData ID = 
      new PaymentGatewayData("PAYMENTGATEWAY_ID");
   
   public static final PaymentGatewayData NAME = 
      new PaymentGatewayData("PAYMENTGATEWAY_NAME");

   public static final PaymentGatewayData VALUE = 
      new PaymentGatewayData("PAYMENTGATEWAY_VALUE");
   public static final PaymentGatewayData PAYMENTTRANSACTIONINTERFACEFACTORYINTERFACE = 
      new PaymentGatewayData("PAYMENTGATEWAY_PAYMENTTRANSACTIONINTERFACEFACTORYINTERFACE");
   public static final PaymentGatewayData PAYMENTPROCESSORINTERFACEFACTORYINTERFACE = 
      new PaymentGatewayData("PAYMENTGATEWAY_PAYMENTPROCESSORINTERFACEFACTORYINTERFACE");

   public static final PaymentGatewayData MODE = 
      new PaymentGatewayData("MODE");

   public static final PaymentGatewayData SERVERPROTOCOL = 
      new PaymentGatewayData("SERVERPROTOCOL");
   public static final PaymentGatewayData SERVER = 
      new PaymentGatewayData("SERVER");
   public static final PaymentGatewayData SERVERPORT = 
      new PaymentGatewayData("SERVERPORT");
   public static final PaymentGatewayData SERVERPATH = 
      new PaymentGatewayData("SERVERPATH");

   public static final PaymentGatewayData TESTPROTOCOL = 
      new PaymentGatewayData("TESTSERVERPROTOCOL");
   public static final PaymentGatewayData TESTSERVER = 
      new PaymentGatewayData("TESTSERVER");
   public static final PaymentGatewayData TESTPORT = 
      new PaymentGatewayData("TESTPORT");
   public static final PaymentGatewayData TESTPATH = 
      new PaymentGatewayData("TESTSERVERPATH");

   public static final PaymentGatewayData TIMEOUT = 
      new PaymentGatewayData("TIMEOUT");

   public static final PaymentGatewayData PROXYPROTOCOL = 
      new PaymentGatewayData("PROXYPROTOCOL");
   public static final PaymentGatewayData PROXYSERVER = 
      new PaymentGatewayData("PROXYSERVER");
   public static final PaymentGatewayData PROXYPATH = 
      new PaymentGatewayData("PROXYPATH");
   public static final PaymentGatewayData PROXYUSERNAME = 
      new PaymentGatewayData("PROXYUSERNAME");
   public static final PaymentGatewayData PROXYPASSWORD = 
      new PaymentGatewayData("PROXYPASSWORD");
   public static final PaymentGatewayData PROXYPORT = 
      new PaymentGatewayData("PROXYPORT");
   public static final PaymentGatewayData PROXYTIMEOUT = 
      new PaymentGatewayData("PROXYTIMEOUT");
   
   public static final PaymentGatewayData SPECIAL1 = 
      new PaymentGatewayData("SPECIAL1");
   public static final PaymentGatewayData SPECIAL2 = 
      new PaymentGatewayData("SPECIAL2");
   public static final PaymentGatewayData SPECIAL3 = 
      new PaymentGatewayData("SPECIAL3");
   public static final PaymentGatewayData SPECIAL4 = 
      new PaymentGatewayData("SPECIAL4");
   public static final PaymentGatewayData SPECIAL5 = 
      new PaymentGatewayData("SPECIAL5");
   public static final PaymentGatewayData SPECIAL6 = 
      new PaymentGatewayData("SPECIAL6");
   public static final PaymentGatewayData SPECIAL7 = 
      new PaymentGatewayData("SPECIAL7");
   public static final PaymentGatewayData SPECIAL8 = 
      new PaymentGatewayData("SPECIAL8");
   public static final PaymentGatewayData SPECIAL9 = 
      new PaymentGatewayData("SPECIAL9");
      
   private PaymentGatewayData(String value)
   {
      this.key = value;
   }

   public String toString()
   {
      return key;
   }
}
