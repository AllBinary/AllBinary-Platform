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
package allbinary.data.tables.user.commerce.money.payment.transaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import abcs.business.init.db.UserDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.UserData;
import allbinary.business.user.commerce.inventory.order.OrderData;
import allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionKeysFactory;
import allbinary.logic.communication.sql.AbSqlBean;

public class TransactionEntity extends AbSqlBean implements TransactionEntityInterface
{         
   private final String tableName  = "vtrans";   

   public TransactionEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
      
   public void remove(String userName, String orderNumber)
   {
      try
      {         
         HashMap whereHashMap =new HashMap();         
         whereHashMap.put(OrderData.ID,(String) orderNumber);
         whereHashMap.put(UserData.USERNAME,userName);
         super.deleteWhere(whereHashMap);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"remove"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"remove",e));
         }
      }
   }

   public void add(String userName, String orderNumber, PaymentTransactionInterface paymentTransactionInterface)
   {
      try
      {
         Vector values = new Vector();
         
         values.add(orderNumber);
         values.add(userName);         
         values.addAll(paymentTransactionInterface.toVector());

         Calendar calendar=Calendar.getInstance();         
         String time = new String(new Long(calendar.getTimeInMillis()).toString());
         values.add(time);
         values.add(time);

         super.insert(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"add"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"add",e));
         }
      }
   }
   
   /*
   public PaymentTransactionInterface getTransactionInterface(String orderNumber)
   {
      try
      {
         HashMap verisignHashMap = new HashMap();
         HashMap updateKeyAndValue = new HashMap();
         updateKeyAndValue.put(PaymentTransactionKeys.ORDERNUMBER, orderNumber);         
         verisignHashMap = super.getRow(updateKeyAndValue);
         
         if(verisignHashMap!=null)
         {            
            PaymentTransactionInterface paymentTransactionInterface;
            
            paymentTransactionInterface = (PaymentTransactionInterface) new VerisignTransaction(verisignHashMap);
            
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put("Command Success",this,"getTransactionInterface()");
            }
            return (PaymentTransactionInterface) paymentTransactionInterface;
         }
         else
         {
            return null;
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put("Command Failed",this,"getTransactionResultInterface()",e);
         }
         return null;
      }
   }
  */

    public final String createTableStatement()
    {
    	PaymentTransactionKeysFactory paymentTransactionKeysFactory = 
    		PaymentTransactionKeysFactory.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(OrderData.ID + " VARCHAR(255) NOT NULL," +
   UserData.USERNAME + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.TRXTYPE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.TENDER + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ACCT + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.EXPDATE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.AMT + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.AUTHCODE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.MICR + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.CHECKNUM + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.NAME + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.STREET + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.CITY + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.STATE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ZIP + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.DL + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.EMAIL + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.COMMENT1 + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.COMMENT2 + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ORIGID + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.PONUM + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.DESC + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.DESC1TO4 + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.INVNUM + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SHIPTOZIP + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SWIPE + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.TAXAMT + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.COMMCARD + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.DUTYAMT + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.FREIGHTAMT + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ORDERDATE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.TAXEXEMPT + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.COUNTRYCODE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.CUSTCODE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.CVV2 + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ABA + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.ACCTTYPE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.DISCOUNT + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.FIRSTNAME + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.LASTNAME + " VARCHAR(255) NOT NULL," +   
   paymentTransactionKeysFactory.SHIPFROMZIP + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.PRENOTE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.CHKTYPE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.DOB + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.PHONENUM + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SS + " VARCHAR(30) NOT NULL," +
   paymentTransactionKeysFactory.COMPANYNAME + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.COUNTRY + " VARCHAR(255) NOT NULL," +         
   paymentTransactionKeysFactory.SHIPTOCITY + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SHIPTOFIRSTNAME + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SHIPTOLASTNAME + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SHIPTOSTATE + " VARCHAR(255) NOT NULL," +
   paymentTransactionKeysFactory.SHIPTOSTREET + " VARCHAR(255) NOT NULL," +    
   paymentTransactionKeysFactory.SPECIAL1 + " VARCHAR(255) NOT NULL," + 
   paymentTransactionKeysFactory.SPECIAL2 + " VARCHAR(255) NOT NULL," + 
   paymentTransactionKeysFactory.SPECIAL3 + " VARCHAR(255) NOT NULL," + 
   EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, " +
   EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(OrderData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

   public String backupTable()
   {
      return super.backupTable();
   }
   
   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }
   
   /*
   public String getTable()
   {
      return super.getTable();
   }*/
   
   public String dropTable()
   {
      return super.dropTable();
   }
}
