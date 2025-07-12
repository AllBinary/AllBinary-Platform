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
package org.allbinary.business.user.commerce.money.payment;

import java.util.HashMap;

import javax.servlet.ServletRequest;

import org.allbinary.business.entry.EntryData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.control.crypt.SuperCrypt;
import org.allbinary.logic.string.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Payment implements PaymentInterface, DomNodeInterface
{
   private String name;
   private String expiration;
   private String encryption;
   private String number;
   private String id;
   private String type;
   
   private String tenderType;
   private String transactionType;
   
   private String aba;
   private String account;
   private String accountType;
   private String authorizationCode;
   private String checkNumber;
   private String checkType;
   private String driversLicense;
   private String magneticInkCheckReader;
   
   private boolean isDefault=false;
      
   public Payment(HashMap payment)
   {
      this.name = (String) payment.get(PaymentData.NAME);
      this.type = (String) payment.get(PaymentData.TYPE);
      this.expiration = (String) payment.get(PaymentData.EXPIRATION);
      this.encryption = (String) payment.get(EntryData.getInstance().ENCRYPTION);
      this.number = (String) new SuperCrypt(new Integer(encryption).intValue()).decrypt((String) payment.get(PaymentData.NUMBER));
      this.id = (String) payment.get(PaymentData.ID);
      String def = (String) payment.get(EntryData.getInstance().DEFAULT);
      if(def!=null && def.compareTo(EntryData.getInstance().DEFAULT)==0)
      {
         this.isDefault = true;
      }
      else
      {
         this.isDefault = false;
      }
      
      this.tenderType = (String) payment.get(PaymentData.TENDERTYPE);
      this.transactionType = (String) payment.get(PaymentData.TRANSACTIONTYPE);
      
      this.aba = (String) payment.get(PaymentData.ABA);
      this.account = (String) payment.get(PaymentData.ACCOUNT);
      this.accountType = (String) payment.get(PaymentData.ACCOUNTTYPE);
      this.authorizationCode = (String) payment.get(PaymentData.AUTHORIZATIONCODE);
      this.checkNumber = (String) payment.get(PaymentData.CHECKNUMBER);
      this.checkType = (String) payment.get(PaymentData.CHECKTYPE);
      this.driversLicense = (String) payment.get(PaymentData.DRIVERSLICENSE);
      this.magneticInkCheckReader = (String) payment.get(PaymentData.MAGNETICINKCHECKREADER);      
   }
   
   public Payment(ServletRequest request)
   {
      this.name  = request.getParameter(PaymentData.NAME);
      this.type = request.getParameter(PaymentData.TYPE);
      this.expiration = request.getParameter(PaymentData.EXPIRATION);
      this.number = request.getParameter(PaymentData.NUMBER);      
      
      this.tenderType = request.getParameter(PaymentData.TENDERTYPE);
      this.transactionType = request.getParameter(PaymentData.TRANSACTIONTYPE);
      
      this.aba = request.getParameter(PaymentData.ABA);
      this.account = request.getParameter(PaymentData.ACCOUNT);
      this.accountType = request.getParameter(PaymentData.ACCOUNTTYPE);
      this.authorizationCode = request.getParameter(PaymentData.AUTHORIZATIONCODE);
      this.checkNumber = request.getParameter(PaymentData.CHECKNUMBER);
      this.checkType = request.getParameter(PaymentData.CHECKTYPE);
      this.driversLicense = request.getParameter(PaymentData.DRIVERSLICENSE);
      this.magneticInkCheckReader = request.getParameter(PaymentData.MAGNETICINKCHECKREADER);
   }
   
   public Payment()
   {
   }
   
   public Boolean isValid()
   {
      final StringUtil stringUtil = StringUtil.getInstance();

      Boolean aFalse = Boolean.FALSE;
      if(this.name==null || this.name.compareTo(stringUtil.EMPTY_STRING)==0)
      {
         return aFalse;
      }
      
      if(this.type==null || this.type.compareTo(stringUtil.EMPTY_STRING)==0)
      {
         return aFalse;
      }
      
      if(this.expiration==null || this.expiration.compareTo(stringUtil.EMPTY_STRING)==0)
      {
         return aFalse;
      }

      if(this.number==null || this.number.compareTo(stringUtil.EMPTY_STRING)==0)
      {
         return aFalse;
      }

      if(this.number.length()>org.allbinary.business.user.commerce.money.payment.types.CreditCardType.MAXLEN)
      {
         return aFalse;
      }

      return Boolean.TRUE;
   }
   
   public void setTenderType(String value)
   {
      this.tenderType = value;
   }
   
   public void setTransactionType(String value)
   {
      this.transactionType = value;
   }
   
   public void setAuthorizationCode(String value)
   {
      this.authorizationCode = value;
   }
   
   public void setCheckType(String value)
   {
      this.checkType = value;
   }
   
   //
   public void setCheckNumber(String value)
   {
      this.checkNumber=value;
   }
   
   public void setDriversLicense(String value)
   {
      this.driversLicense=value;
   }
   
   public void setMagneticInkCheckReader(String value)
   {
      this.magneticInkCheckReader=value;
   }
   
   public void setAba(String value)
   {
      this.aba=value;
   }
   
   public void setAccountType(String value)
   {
      this.accountType = value;
   }
   //
   public void setName(String value)
   {
      this.name=value;
   }
   
   public void setType(String value)
   {
      this.type=value;
   }
   
   public void setExpiration(String value)
   {
      this.expiration=value;
   }
   
   public void setNumber(String value)
   {
      this.number = value;
   }
   
   public String getId()
   {
      return this.id;
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public String getType()
   {
      return this.type;
   }
   
   public String getExpiration()
   {
      return this.expiration;
   }
   
   public String getEncryption()
   {
      return this.encryption;
   }
   
   public String getNumber()
   {
      return this.number;
   }
   
   public String getTenderType()
   {
      return this.tenderType;
   }
   
   public String getTransactionType()
   {
      return this.transactionType;
   }
   
   public String getAba()
   {
      return this.aba;
   }
   
   public String getAccount()
   {
      return this.account;
   }
   
   public String getAccountType()
   {
      return this.accountType;
   }
   
   public String getAuthorizationCode()
   {
      return this.authorizationCode;
   }
   
   public String getCheckNumber()
   {
      return this.checkNumber;
   }
   
   public String getCheckType()
   {
      return this.checkType;
   }
   
   public String getDriversLicense()
   {
      return this.driversLicense;
   }
   
   public String getMagneticInkCheckReader()
   {
      return this.magneticInkCheckReader;
   }
   
   public String getLastFour()
   {
      if(this.number.length()>PaymentData.MIN && this.number.length()<=PaymentData.MAX)
      {
         return this.number.substring(this.number.length()-4);
      }
      else 
      {
         return "Invalid #";
      }
   }
   
   public boolean isDefault()
   {
      return isDefault;
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(PaymentData.NAME, this.name);
      hashMap.put(PaymentData.TYPE, this.type);
      hashMap.put(PaymentData.EXPIRATION, this.expiration);      
      hashMap.put(PaymentData.NUMBER, this.getLastFour());
      hashMap.put(PaymentData.TENDERTYPE, this.tenderType);
      hashMap.put(PaymentData.TRANSACTIONTYPE, this.transactionType);
      hashMap.put(PaymentData.ABA, this.aba);
      hashMap.put(PaymentData.ACCOUNT, this.account);
      hashMap.put(PaymentData.ACCOUNTTYPE, this.accountType);
      hashMap.put(PaymentData.AUTHORIZATIONCODE, this.authorizationCode);
      hashMap.put(PaymentData.CHECKNUMBER, this.checkNumber);
      hashMap.put(PaymentData.DRIVERSLICENSE, this.driversLicense);
      hashMap.put(PaymentData.MAGNETICINKCHECKREADER, this.magneticInkCheckReader);
      
      if(this.isDefault)
      {
         hashMap.put(EntryData.getInstance().DEFAULT,EntryData.getInstance().DEFAULT);
      }
      else 
      {
         hashMap.put(EntryData.getInstance().DEFAULT, StringUtil.getInstance().EMPTY_STRING);
      }
      
      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.toHashMap();

      Node node = ModDomHelper.createNameValueNodes(
         document, PaymentData.PAYMENT, hashMap);

      return node;
   }

   public Document toXmlDoc()
   {
      return null;      
   }

   public String view()
   {
      return null;
   }   
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo()
   {
      return null;
   }
   
}
