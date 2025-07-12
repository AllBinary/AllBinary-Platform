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

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.logic.control.crypt.SuperCrypt;
import org.allbinary.logic.string.StringUtil;

public class PaymentGateway implements PaymentGatewayInterface
{
   //private String gatewayId;
   private String storeName;
   private String name;
   
   private String enable;
   
   private String paymentMethod;
   private String mode;
   private String testProtocol;
   private String testServer;
   private String testPort;
   private String testPath;
   private String serverProtocol;
   private String server;
   private String serverPort;
   private String serverPath;
   
   private String timeout;
   
   private String userName;
   private String password;
   
   private String proxyProtocol;
   private String proxyServer;
   private String proxyPort;
   private String proxyPath;
   private String proxyUserName;
   private String proxyPassword;
   private String proxyTimeout;
   
   private String special1;
   private String special2;
   private String special3;
   private String special4;
   private String special5;
   private String special6;
   private String special7;
   private String special8;
   private String special9;
   
   private String lastModified;
   private String timeEntered;

   public PaymentGateway(HashMap hashMap)
   {
      this.getFormData(hashMap);
   }
   
   public void getFormData(HashMap hashMap)
   {
       StringUtil stringUtil = StringUtil.getInstance();
       
      final String empty = stringUtil.EMPTY_STRING;

      //this.gatewayId = (String) hashMap.get(PaymentGatewayData.ID.toString());      
      //if(this.gatewayId==null) this.gatewayId = empty;

      this.storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME.toString());
      //Will cause error if storename not given
      //if(this.storeName==null) this.storeName = empty;
      
      this.name = (String) hashMap.get(PaymentGatewayData.NAME.toString());
      //Will cause error if gateway not provided
      //if(this.gatewayName==null) this.gatewayName = empty;

      this.enable = 
         stringUtil.getInstance((String) hashMap.get(EntryData.getInstance().ENABLE.toString()));

      this.paymentMethod = 
         stringUtil.getInstance((String) hashMap.get(PaymentData.METHOD.toString()));

      this.mode = 
         stringUtil.getInstance((String) hashMap.get(PaymentGatewayData.MODE.toString()));

      this.testProtocol = 
         stringUtil.getInstance((String) hashMap.get(PaymentGatewayData.TESTPROTOCOL.toString()));

      this.testServer = 
         stringUtil.getInstance((String) hashMap.get(PaymentGatewayData.TESTSERVER.toString()));

      this.testPort = 
         stringUtil.getInstance((String) hashMap.get(PaymentGatewayData.TESTPORT.toString()));

      this.testPath = (String) hashMap.get(PaymentGatewayData.TESTPATH.toString());
      if(this.testPath==null) this.testPath = empty;

      this.serverProtocol = (String) hashMap.get(PaymentGatewayData.SERVERPROTOCOL.toString());
      if(this.serverProtocol==null) this.serverProtocol = empty;

      this.server = (String) hashMap.get(PaymentGatewayData.SERVER.toString());
      if(this.server==null) this.server = empty;
      
      this.serverPort = (String) hashMap.get(PaymentGatewayData.SERVERPORT.toString());
      if(this.serverPort==null) this.serverPort = empty;
      
      this.serverPath = (String) hashMap.get(PaymentGatewayData.SERVERPATH.toString());
      if(this.serverPath==null) this.serverPath = empty;
      
      this.userName = (String) hashMap.get(UserData.USERNAME.toString());
      if(this.userName==null) this.userName = empty;
      
      this.password = (String) hashMap.get(UserData.PASSWORD.toString());
      if(this.password==null) this.password = empty;
      
      this.timeout = (String) hashMap.get(PaymentGatewayData.TIMEOUT.toString());
      if(this.timeout==null) this.timeout = empty;
      
      this.proxyProtocol = (String) hashMap.get(PaymentGatewayData.PROXYPROTOCOL.toString());
      if(this.proxyProtocol==null) this.proxyProtocol = empty;
      
      this.proxyServer = (String) hashMap.get(PaymentGatewayData.PROXYSERVER.toString());
      if(this.proxyServer==null) this.proxyServer = empty;
      
      this.proxyPort = (String) hashMap.get(PaymentGatewayData.PROXYPORT.toString());
      if(this.proxyPort==null) this.proxyPort = empty;
      
      this.proxyPath = (String) hashMap.get(PaymentGatewayData.PROXYPATH.toString());
      if(this.proxyPath==null) this.proxyPath = empty;
      
      this.proxyUserName = (String) hashMap.get(PaymentGatewayData.PROXYUSERNAME.toString());
      if(this.proxyUserName==null) this.proxyUserName = empty;
      
      this.proxyPassword = (String) hashMap.get(PaymentGatewayData.PROXYPASSWORD.toString());
      if(this.proxyPassword==null) this.proxyPassword = empty;
      
      this.proxyTimeout = (String) hashMap.get(PaymentGatewayData.PROXYTIMEOUT.toString());
      if(this.proxyTimeout==null) this.proxyTimeout = empty;
      
      this.special1 = (String) hashMap.get(PaymentGatewayData.SPECIAL1.toString());
      if(this.special1==null) this.special1 = empty;
      
      this.special2 = (String) hashMap.get(PaymentGatewayData.SPECIAL2.toString());
      if(this.special2==null) this.special2 = empty;
      
      this.special3 = (String) hashMap.get(PaymentGatewayData.SPECIAL3.toString());
      if(this.special3==null) this.special3 = empty;
      
      this.special4 = (String) hashMap.get(PaymentGatewayData.SPECIAL4.toString());
      if(this.special4==null) this.special4 = empty;
      
      this.special5 = (String) hashMap.get(PaymentGatewayData.SPECIAL5.toString());
      if(this.special5==null) this.special5 = empty;
      
      this.special6 = (String) hashMap.get(PaymentGatewayData.SPECIAL6.toString());
      if(this.special6==null) this.special6 = empty;
      
      this.special7 = (String) hashMap.get(PaymentGatewayData.SPECIAL7.toString());
      if(this.special7==null) this.special7 = empty;
      
      this.special8 = (String) hashMap.get(PaymentGatewayData.SPECIAL8.toString());
      if(this.special8==null) this.special8 = empty;
      
      this.special9 = (String) hashMap.get(PaymentGatewayData.SPECIAL9.toString());
      if(this.special9==null) this.special9 = empty;
      
      this.lastModified = (String) hashMap.get(EntryData.getInstance().LASTMODIFIED.toString());
      if(this.lastModified==null) this.lastModified = empty;
      
      this.timeEntered = (String) hashMap.get(EntryData.getInstance().TIMECREATED.toString());
      if(this.timeEntered==null) this.timeEntered = empty;
   }
   
   /*
   public String getGatewayId()
   {
      return this.gatewayId;
   }
   */
   
   public String getStoreName()
   {
      return this.storeName;
   }
   
   public String getName()
   {
      return this.name;
   }

   public String getEnable()
   {
      return this.enable;
   }

   public String getPaymentMethod()
   {
      return this.paymentMethod;
   }
   
   public String getMode()
   {
      return this.mode;
   }
   
   public String getTestProtocol()
   {
      return this.testProtocol;
   }
   
   public String getTestServer()
   {
      return this.testServer;
   }
   
   public String getTestPort()
   {
      return this.testPort;
   }
   
   public String getTestPath()
   {
      return this.testPath;
   }
   
   public String getServerProtocol()
   {
      return this.serverProtocol;
   }
   
   public String getServer()
   {
      return this.server;
   }
   
   public String getServerPort()
   {
      return this.serverPort;
   }
   
   public String getServerPath()
   {
      return this.serverPath;
   }
   
   public String getUserName()
   {
      return this.userName;
   }
   
   public String getPassword()
   {
      return this.password;
   }
   
   public String getProxyProtocol()
   {
      return this.proxyProtocol;
   }
   
   public String getProxyServer()
   {
      return this.proxyServer;
   }
   
   public String getProxyPath()
   {
      return this.proxyPath;
   }
   
   public String getProxyPort()
   {
      return this.proxyPort;
   }
   
   public String getProxyUserName()
   {
      return this.proxyUserName;
   }
   
   public String getProxyPassword()
   {
      return this.proxyPassword;
   }
   
   public String getTimeout()
   {
      return this.timeout;
   }
   
   public String getProxyTimeout()
   {
      return this.proxyTimeout;
   }
   
   public String getSpecial1()
   {
      return this.special1;
   }
   
   public String getSpecial2()
   {
      return this.special2;
   }
   
   public String getSpecial3()
   {
      return this.special3;
   }
   
   public String getSpecial4()
   {
      return this.special4;
   }
   
   public String getSpecial5()
   {
      return this.special5;
   }
   
   public String getSpecial6()
   {
      return this.special6;
   }
   
   public String getSpecial7()
   {
      return this.special7;
   }
   
   public String getSpecial8()
   {
      return this.special8;
   }
   
   public String getSpecial9()
   {
      return this.special9;
   }
   
   public String getLastModified()
   {
      return this.lastModified;
   }
   
   public String getTimeEntered()
   {
      return this.timeEntered;
   }
   
   /*
   public void setGatewayId(String value)
   {
      this.gatewayId = value;
   }
   */
   
   public void setEnable(String value)
   {
      this.enable = value;
   }
   
   public void setStoreName(String value)
   {
      this.storeName = value;
   }
   
   public void setName(String value)
   {
      this.name = value;
   }
   
   public void setPaymentMethod(String value)
   {
      this.paymentMethod = value;
   }
   
   public void setMode(String value)
   {
      this.mode = value;
   }
   
   public void setTestProtocol(String value)
   {
      this.testProtocol = value;
   }
   
   public void setTestServer(String value)
   {
      this.testServer = value;
   }
   
   public void setTestPort(String value)
   {
      this.testPort = value;
   }
   
   public void setTestPath(String value)
   {
      this.testPath = value;
   }
   
   public void setServerProtocol(String value)
   {
      this.serverProtocol = value;
   }
   
   public void setServer(String value)
   {
      this.server = value;
   }
   
   public void setServerPort(String value)
   {
      this.serverPort = value;
   }
   
   public void setServerPath(String value)
   {
      this.serverPath = value;
   }
   
   public void setTimeout(String value)
   {
      this.timeout = value;
   }
   
   public void setUserName(String value)
   {
      this.userName = value;
   }
   
   public void setPassword(String value)
   {
      this.password = value;
   }
   
   public void setProxyProtocol(String value)
   {
      this.proxyProtocol = value;
   }
   
   public void setProxyServer(String value)
   {
      this.proxyServer = value;
   }
   
   public void setProxyPort(String value)
   {
      this.proxyPort = value;
   }
   
   public void setProxyPath(String value)
   {
      this.proxyPath = value;
   }
   
   public void setProxyUserName(String value)
   {
      this.proxyUserName = value;
   }
   
   public void setProxyPassword(String value)
   {
      this.proxyPassword = value;
   }
   
   public void setProxyTimeout(String value)
   {
      this.proxyTimeout = value;
   }
   
   public void setSpecial1(String value)
   {
      this.special1 = value;
   }
   
   public void setSpecial2(String value)
   {
      this.special2 = value;
   }
   
   public void setSpecial3(String value)
   {
      this.special3 = value;
   }
   
   public void setSpecial4(String value)
   {
      this.special4 = value;
   }
   
   public void setSpecial5(String value)
   {
      this.special5 = value;
   }
   
   public void setSpecial6(String value)
   {
      this.special6 = value;
   }
   
   public void setSpecial7(String value)
   {
      this.special7 = value;
   }
   
   public void setSpecial8(String value)
   {
      this.special8 = value;
   }
   
   public void setSpecial9(String value)
   {
      this.special9 = value;
   }
   
   public void setLastModified(String value)
   {
      this.lastModified = value;
   }
   
   public void setTimeEntered(String value)
   {
      this.timeEntered = value;
   }

   public HashMap toHashMap() throws Exception
   {
      return this.toHashMap(true);
   }

   public HashMap toHashMap(boolean isEncrypted) throws Exception
   {
      HashMap paymentGatewayHashMap = new HashMap();

      paymentGatewayHashMap.put(
         EntryData.getInstance().ENABLE, this.enable);

      paymentGatewayHashMap.put(
         StoreFrontData.getInstance().NAME.toString(),this.storeName);
      //paymentGatewayHashMap.put(
      //   PaymentGatewayData.ID.toString(),this.gatewayId);
      paymentGatewayHashMap.put(
         PaymentGatewayData.NAME.toString(),this.getName());

      paymentGatewayHashMap.put(
         PaymentData.METHOD.toString(),this.paymentMethod);
      paymentGatewayHashMap.put(
         PaymentGatewayData.MODE.toString(),this.mode);
      paymentGatewayHashMap.put(
         PaymentGatewayData.TESTPROTOCOL.toString(),this.testProtocol);
      paymentGatewayHashMap.put(
         PaymentGatewayData.TESTSERVER.toString(),this.testServer);
      paymentGatewayHashMap.put(
         PaymentGatewayData.TESTPORT.toString(),this.testPort);
      paymentGatewayHashMap.put(
         PaymentGatewayData.TESTPATH.toString(),this.testPath);
      paymentGatewayHashMap.put(
         PaymentGatewayData.SERVERPROTOCOL.toString(),this.serverProtocol);
      paymentGatewayHashMap.put(
         PaymentGatewayData.SERVER.toString(),this.server);
      paymentGatewayHashMap.put(
         PaymentGatewayData.SERVERPORT.toString(),this.serverPort);
      paymentGatewayHashMap.put(
         PaymentGatewayData.SERVERPATH.toString(),this.serverPath);
      
      if(isEncrypted)
      {
         SuperCrypt superCrypt = new SuperCrypt(PaymentGatewayData.CRYPTNUM);
         
         paymentGatewayHashMap.put(UserData.USERNAME.toString(),
            superCrypt.encrypt(this.userName));
         paymentGatewayHashMap.put(UserData.PASSWORD.toString(),
            superCrypt.encrypt(this.password));
      }
      else
      {
         paymentGatewayHashMap.put(
            UserData.USERNAME.toString(),this.userName);
         paymentGatewayHashMap.put(
            UserData.PASSWORD.toString(),this.password);
      }
      
      paymentGatewayHashMap.put(
         PaymentGatewayData.TIMEOUT.toString(),this.timeout);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYPROTOCOL.toString(),this.proxyProtocol);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYSERVER.toString(),this.proxyServer);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYPORT.toString(),this.proxyPort);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYPATH.toString(),this.proxyPath);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYUSERNAME.toString(),this.proxyUserName);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYPASSWORD.toString(),this.proxyPassword);
      paymentGatewayHashMap.put(
         PaymentGatewayData.PROXYTIMEOUT.toString(),this.proxyTimeout);
      
      if(isEncrypted)
      {
         SuperCrypt superCrypt = new SuperCrypt(PaymentGatewayData.CRYPTNUM);
         
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL1.toString(), 
            superCrypt.encrypt(this.special1));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL2.toString(),
            superCrypt.encrypt(this.special2));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL3.toString(),
            superCrypt.encrypt(this.special3));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL4.toString(),
            superCrypt.encrypt(this.special4));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL5.toString(),
            superCrypt.encrypt(this.special5));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL6.toString(),
            superCrypt.encrypt(this.special6));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL7.toString(),
            superCrypt.encrypt(this.special7));
         paymentGatewayHashMap.put(PaymentGatewayData.SPECIAL8.toString(),
            superCrypt.encrypt(this.special8));
      }
      else
      {
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL1.toString(),this.special1);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL2.toString(),this.special2);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL3.toString(),this.special3);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL4.toString(),this.special4);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL5.toString(),this.special5);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL6.toString(),this.special6);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL7.toString(),this.special7);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL8.toString(),this.special8);
         paymentGatewayHashMap.put(
            PaymentGatewayData.SPECIAL9.toString(),this.special9);
      }

      paymentGatewayHashMap.put(
         EntryData.getInstance().LASTMODIFIED.toString(),this.lastModified);
      //paymentGatewayHashMap.put(
        // EntryData.getInstance().TIMECREATED.toString(),this.timeEntered);

      return paymentGatewayHashMap;
   }

   public Vector toVector()
   {
      return this.toVector(true);
   }
   
   public Vector toVector(boolean isEncrypted)
   {
      Vector updateVector = new Vector();
      //updateVector.add(this.gatewayId);
      updateVector.add(this.enable);
      updateVector.add(this.storeName);
      updateVector.add(this.getName());
      updateVector.add(this.paymentMethod);
      updateVector.add(this.mode);
      updateVector.add(this.testProtocol);
      updateVector.add(this.testServer);
      updateVector.add(this.testPort);
      updateVector.add(this.testPath);
      updateVector.add(this.serverProtocol);
      updateVector.add(this.server);
      updateVector.add(this.serverPort);
      updateVector.add(this.serverPath);
      
      if(isEncrypted)
      {
         SuperCrypt superCrypt = new SuperCrypt(PaymentGatewayData.CRYPTNUM);
         
         updateVector.add(superCrypt.encrypt(this.userName));
         updateVector.add(superCrypt.encrypt(this.password));
      }
      else
      {
         updateVector.add(this.userName);
         updateVector.add(this.password);
      }
      
      updateVector.add(this.timeout);
      updateVector.add(this.proxyProtocol);
      updateVector.add(this.proxyServer);
      updateVector.add(this.proxyPort);
      updateVector.add(this.proxyPath);
      updateVector.add(this.proxyUserName);
      updateVector.add(this.proxyPassword);
      updateVector.add(this.proxyTimeout);
      
      if(isEncrypted)
      {
         SuperCrypt superCrypt = new SuperCrypt(PaymentGatewayData.CRYPTNUM);
         
         updateVector.add(superCrypt.encrypt(this.special1));
         updateVector.add(superCrypt.encrypt(this.special2));
         updateVector.add(superCrypt.encrypt(this.special3));
         updateVector.add(superCrypt.encrypt(this.special4));
         updateVector.add(superCrypt.encrypt(this.special5));
         updateVector.add(superCrypt.encrypt(this.special6));
         updateVector.add(superCrypt.encrypt(this.special7));
         updateVector.add(superCrypt.encrypt(this.special8));
         updateVector.add(superCrypt.encrypt(this.special9));
      }
      else
      {
         updateVector.add(this.special1);
         updateVector.add(this.special2);
         updateVector.add(this.special3);
         updateVector.add(this.special4);
         updateVector.add(this.special5);
         updateVector.add(this.special6);
         updateVector.add(this.special7);
         updateVector.add(this.special8);
         updateVector.add(this.special9);
      }

      //updateVector.add(this.lastModified);
      //updateVector.add(this.timeEntered);
      return updateVector;
   }   
   
   public Object getKey() throws Exception
   {
      return (Object) new PaymentGatewayPrimaryKey(this.getStoreName(), this.getName());
   }  
}