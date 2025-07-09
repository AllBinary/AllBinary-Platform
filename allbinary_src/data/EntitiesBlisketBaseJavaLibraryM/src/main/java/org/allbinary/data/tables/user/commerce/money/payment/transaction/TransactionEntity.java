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
package org.allbinary.data.tables.user.commerce.money.payment.transaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.UserData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionKeysFactory;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class TransactionEntity extends AbSqlBean implements TransactionEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String tableName = "vtrans";

    public TransactionEntity()
    {
        super(new UserDbInitInfo());
        this.setTableName(tableName);
    }

    public void remove(String userName, String orderNumber)
    {
        try
        {
            HashMap whereHashMap = new HashMap();
            whereHashMap.put(OrderData.ID, (String) orderNumber);
            whereHashMap.put(UserData.USERNAME, userName);
            super.deleteWhere(whereHashMap);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "remove");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "remove", e);
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

            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            values.add(time);
            values.add(time);

            super.insert(values);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "add");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "add", e);
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
            
     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
     {
     logUtil.put(this.commonStrings.SUCCESS,this,"getTransactionInterface()");
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
     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
     {
     logUtil.put(this.commonStrings.FAILURE,this,"getTransactionResultInterface()",e);
     }
     return null;
     }
     }
     */
    public final String createTableStatement()
    {
        PaymentTransactionKeysFactory paymentTransactionKeysFactory
                = PaymentTransactionKeysFactory.getInstance();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(OrderData.ID)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TRXTYPE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TENDER)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ACCT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXPDATE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.AMT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.AUTHCODE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.MICR)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CHECKNUM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.STREET)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CITY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.STATE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ZIP)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DL)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.EMAIL)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMENT1)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMENT2)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ORIGID)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PONUM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DESC)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DESC1TO4)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.INVNUM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOZIP)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SWIPE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TAXAMT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMCARD)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DUTYAMT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.FREIGHTAMT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ORDERDATE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TAXEXEMPT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COUNTRYCODE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CUSTCODE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CVV2)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ABA)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ACCTTYPE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DISCOUNT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.FIRSTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.LASTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPFROMZIP)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PRENOTE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CHKTYPE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DOB)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PHONENUM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SS)
                .append(" VARCHAR(30) NOT NULL,")
                .append(paymentTransactionKeysFactory.COMPANYNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COUNTRY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOCITY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOFIRSTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOLASTNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOSTATE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOSTREET)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL1)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL2)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL3)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(EntryData.getInstance().LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(OrderData.ID)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
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
