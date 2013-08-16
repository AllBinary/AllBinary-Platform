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
package allbinary.data.tables.user.commerce.money.payment;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import abcs.business.init.db.UserDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.basic.io.file.generators.PaymentIdGenerator;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.UserData;
import allbinary.business.user.commerce.money.payment.Payment;
import allbinary.business.user.commerce.money.payment.PaymentData;
import allbinary.business.user.commerce.money.payment.PaymentInterface;
import allbinary.logic.communication.sql.AbSqlBean;
import allbinary.logic.control.crypt.SuperCrypt;

public class PaymentEntity extends AbSqlBean implements PaymentEntityInterface
{
   private final String tableName  = "payment";
   
   public PaymentEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
   
   public String getLastId(String userName)
   {
      return super.getLargestIntegerInColumnWhere(PaymentData.ID,UserData.USERNAME,userName);
   }
   
   public void setDefault(String userName,Integer index)
   {
      try
      {
         HashMap updateKeyAndValue = new HashMap();
         HashMap whereKeyAndValue = new HashMap();
         
         whereKeyAndValue.put(UserData.USERNAME,userName);
         
         //remove old default value if it exist
         PaymentInterface paymentInterface = getDefault(userName);
         if(paymentInterface!=null)
         {
            updateKeyAndValue.put(EntryData.getInstance().DEFAULT, StringUtil.getInstance().EMPTY_STRING);
            whereKeyAndValue.put(PaymentData.ID,paymentInterface.getId());
            super.updateWhere(whereKeyAndValue,updateKeyAndValue);
         }
         
         updateKeyAndValue.put(EntryData.getInstance().DEFAULT,EntryData.getInstance().DEFAULT);
         whereKeyAndValue.put(PaymentData.ID,index.toString());
         
         super.updateWhere(whereKeyAndValue,updateKeyAndValue);
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"setDefault"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"setDefault",e));
         }
      }
   }
   
   public Vector get(String userName)
   {
      try
      {
         Vector paymentVector = new Vector();
         HashMap keyAndValue = new HashMap();
         keyAndValue.put(UserData.USERNAME,userName);
         Vector payments = super.getRows(keyAndValue);

         Iterator iter = payments.iterator();
                           
         while(iter.hasNext())
         {
            HashMap paymentHashMap = (HashMap) iter.next();
            
            Payment payment = new Payment(paymentHashMap);
            
            if(payment!=null)
               paymentVector.add(payment);
         }
         
         return paymentVector;
         
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
         }
         return null;
      }
   }
   
   public PaymentInterface getDefault(String userName)
   {      
      try
      {         
         HashMap paymentHashMap = new HashMap();
         HashMap updateKeyAndValue = new HashMap();
         updateKeyAndValue.put(EntryData.getInstance().DEFAULT,EntryData.getInstance().DEFAULT);
         updateKeyAndValue.put(UserData.USERNAME,userName);
         
         paymentHashMap = super.getRow(updateKeyAndValue);
         if(paymentHashMap!=null)
         {
            Payment payment = new Payment(paymentHashMap);
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Command Success",this,"getDefault"));
            }
            return (PaymentInterface) payment;
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
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"getDefault",e));
         }
         return null;
      }
   }
   
   public void remove(String userName,Integer index)
   {
      try
      {
         HashMap whereHashMap =new HashMap();
         whereHashMap.put(UserData.USERNAME,userName);
         whereHashMap.put(PaymentData.ID,(String) index.toString());
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
   
   public void add(String userName,PaymentInterface paymentInterface)
   {
      try
      {
         
         Vector vector = new Vector();
         
         //vector.add(StringUtil.getInstance());
         //vector.add("auto_increment");
         vector.add(new PaymentIdGenerator().getNext());

         vector.add(userName);
         vector.add(StringUtil.getInstance().EMPTY_STRING);
         vector.add(paymentInterface.getName());
         vector.add(paymentInterface.getType());
         vector.add(paymentInterface.getExpiration());
         
         int random = new Random().nextInt(SuperCrypt.KEYMAX);
         vector.add(new SuperCrypt(random).encrypt(paymentInterface.getNumber()));
         vector.add(new Integer(random).toString());
         
         Calendar calendar=Calendar.getInstance();
         String time = new String(new Long(calendar.getTimeInMillis()).toString());
         vector.add(time);
         
         super.insert(vector);
         
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
   
    public final String createTableStatement()
    {
    	EntryData entryData = EntryData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");
        
        stringBuffer.append(PaymentData.ID + " BIGINT(19) UNSIGNED AUTO_INCREMENT NOT NULL," +
   UserData.USERNAME + " VARCHAR(255) NOT NULL," +
   entryData.DEFAULT + " VARCHAR(255) NOT NULL," +
   PaymentData.NAME + " VARCHAR(255) NOT NULL," +
   PaymentData.TYPE + " VARCHAR(255) NOT NULL," +
   PaymentData.EXPIRATION + " VARCHAR(255) NOT NULL," +
   PaymentData.NUMBER + " VARCHAR(255) NOT NULL," +
   entryData.ENCRYPTION + " BIGINT(19) UNSIGNED NOT NULL," +
   entryData.TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(PaymentData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
   
   public String drop()
   {
      return super.dropTable();
   }
   
   public String backupTable()
   {
      return super.backupTable();
   }
   
   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }
}
