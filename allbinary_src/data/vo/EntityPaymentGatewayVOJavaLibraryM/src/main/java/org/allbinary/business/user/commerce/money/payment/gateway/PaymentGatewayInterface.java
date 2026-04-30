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
package org.allbinary.business.user.commerce.money.payment.gateway;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

//import org.allbinary.business.user.commerce.money.payment.gateway.*;

public interface PaymentGatewayInterface 
   extends TableMappingInterface
   //, DomNodeInterface
{           
   //public String getGatewayId();
   
   String getEnable();
    
   String getStoreName();

   String getName();
   
   String getPaymentMethod();

   String getMode();
   
   String getTestProtocol();

   String getTestServer();

   String getTestPort();
      
   String getTestPath();
   
   String getServerProtocol();

   String getServer();

   String getServerPort();
   
   String getServerPath();
   
   String getUserName();

   String getPassword();
   
   String getProxyProtocol();

   String getProxyServer();

   String getProxyPath();

   String getProxyPort();

   String getProxyUserName();
   
   String getProxyPassword();   
   
   String getTimeout();
   
   String getProxyTimeout();
   
   String getSpecial1();
   String getSpecial2();
   String getSpecial3();
   String getSpecial4();
   String getSpecial5();
   String getSpecial6();
   String getSpecial7();
   String getSpecial8();
   String getSpecial9();
   
   String getLastModified();
   String getTimeEntered(); 
         
   //public void setGatewayId(String value);
   
   void setEnable(String value);
    
   void setStoreName(String value);

   void setName(String value);
   
   void setPaymentMethod(String value);

   void setMode(String value);
   
   void setTestProtocol(String value);

   void setTestServer(String value);

   void setTestPort(String value);
      
   void setTestPath(String value);
   
   void setServerProtocol(String value);

   void setServer(String value);

   void setServerPort(String value);
   
   void setServerPath(String value);
   
   void setUserName(String value);

   void setPassword(String value);

   void setProxyProtocol(String value);

   void setProxyServer(String value);

   void setProxyPath(String value);

   void setProxyPort(String value);

   void setProxyUserName(String value);
   
   void setProxyPassword(String value);
   
   void setTimeout(String value);
   
   void setProxyTimeout(String value);
   
   void setSpecial1(String value);
   void setSpecial2(String value);
   void setSpecial3(String value);
   void setSpecial4(String value);
   void setSpecial5(String value);
   void setSpecial6(String value);
   void setSpecial7(String value);
   void setSpecial8(String value);
   void setSpecial9(String value);
   
   void setLastModified(String value);
   void setTimeEntered(String value);
   
   HashMap toHashMap(boolean isEncrypted) throws Exception;
   Vector toVector(boolean isEncrypted);
}
