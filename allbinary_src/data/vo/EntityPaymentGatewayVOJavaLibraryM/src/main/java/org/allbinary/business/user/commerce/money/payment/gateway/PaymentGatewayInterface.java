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
   
   public String getEnable();
    
   public String getStoreName();

   public String getName();
   
   public String getPaymentMethod();

   public String getMode();
   
   public String getTestProtocol();

   public String getTestServer();

   public String getTestPort();
      
   public String getTestPath();
   
   public String getServerProtocol();

   public String getServer();

   public String getServerPort();
   
   public String getServerPath();
   
   public String getUserName();

   public String getPassword();
   
   public String getProxyProtocol();

   public String getProxyServer();

   public String getProxyPath();

   public String getProxyPort();

   public String getProxyUserName();
   
   public String getProxyPassword();   
   
   public String getTimeout();
   
   public String getProxyTimeout();
   
   public String getSpecial1();
   public String getSpecial2();
   public String getSpecial3();
   public String getSpecial4();
   public String getSpecial5();
   public String getSpecial6();
   public String getSpecial7();
   public String getSpecial8();
   public String getSpecial9();
   
   public String getLastModified();
   public String getTimeEntered(); 
         
   //public void setGatewayId(String value);
   
   public void setEnable(String value);
    
   public void setStoreName(String value);

   public void setName(String value);
   
   public void setPaymentMethod(String value);

   public void setMode(String value);
   
   public void setTestProtocol(String value);

   public void setTestServer(String value);

   public void setTestPort(String value);
      
   public void setTestPath(String value);
   
   public void setServerProtocol(String value);

   public void setServer(String value);

   public void setServerPort(String value);
   
   public void setServerPath(String value);
   
   public void setUserName(String value);

   public void setPassword(String value);

   public void setProxyProtocol(String value);

   public void setProxyServer(String value);

   public void setProxyPath(String value);

   public void setProxyPort(String value);

   public void setProxyUserName(String value);
   
   public void setProxyPassword(String value);
   
   public void setTimeout(String value);
   
   public void setProxyTimeout(String value);
   
   public void setSpecial1(String value);
   public void setSpecial2(String value);
   public void setSpecial3(String value);
   public void setSpecial4(String value);
   public void setSpecial5(String value);
   public void setSpecial6(String value);
   public void setSpecial7(String value);
   public void setSpecial8(String value);
   public void setSpecial9(String value);
   
   public void setLastModified(String value);
   public void setTimeEntered(String value);
   
   public HashMap toHashMap(boolean isEncrypted) throws Exception;
   public Vector toVector(boolean isEncrypted);
}
