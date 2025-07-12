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

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionKeysFactory;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResult;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResultInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;

public class TransactionResultEntity extends AbSqlBean
        implements TransactionResultEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String tableName = "vresults";

    public TransactionResultEntity()
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

    public void add(String userName, String orderNumber, TransactionResultInterface transactionResultInterface)
    {
        try
        {
            Vector values = new Vector();

            values.add(orderNumber);
            values.add(userName);
            values.addAll(transactionResultInterface.getValues());

            Calendar calendar = Calendar.getInstance();
            String time = new Long(calendar.getTimeInMillis()).toString();
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

    public TransactionResultInterface getTransactionResultInterface(String orderNumber)
    {
        try
        {
            HashMap resultHashMap = new HashMap();
            HashMap updateKeyAndValue = new HashMap();
            updateKeyAndValue.put(OrderData.ID, orderNumber);
            resultHashMap = super.getRow(updateKeyAndValue);

            if(resultHashMap != null)
            {
                TransactionResult transactionResult = new TransactionResult(resultHashMap);
                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(this.commonStrings.SUCCESS, this, "getTransactionResultInterface");
                }
                return (TransactionResultInterface) transactionResult;
            }else
            {
                return null;
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "getTransactionResultInterface", e);
            }
            return null;
        }
    }

    public final String createTableStatement()
    {
        final PaymentTransactionKeysFactory paymentTransactionKeysFactory
                = PaymentTransactionKeysFactory.getInstance();

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(OrderData.ID)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        //unknown max size but 0 means transaction was a success
        stringBuffer.append(paymentTransactionKeysFactory.RESULT.toString())
                .append(this.sqlTypeStrings.MAX_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.PNREF.toString())
                .append(this.sqlTypeStrings.TWELVE_CHAR_COLUMN_NOT_NULL);
        //unknown max size of alphanumeric data
        stringBuffer.append(paymentTransactionKeysFactory.RESPMSG.toString())
                .append(this.sqlTypeStrings.BLOB_NOT_NULL)
                .append(paymentTransactionKeysFactory.AUTHCODE.toString())
                .append(this.sqlTypeStrings.SIX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.AVSADDR.toString())
                .append(this.sqlTypeStrings.ONE_CHAR_COLUMN_NOT_NULL);
        //Norwest only - unknown sizes/type of data
        stringBuffer.append(paymentTransactionKeysFactory.AVSZIP.toString())
                .append(this.sqlTypeStrings.ONE_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ORIGRESULT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.STATUS.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        //fraud responses
        //codes available but not yet programmed
        stringBuffer.append(paymentTransactionKeysFactory.FRAUDCODE.toString())
                .append(this.sqlTypeStrings.TWO_INT_NOT_NULL);
        //msgs available but not yet programmed
        stringBuffer.append(
                paymentTransactionKeysFactory.FRAUDMSG.toString())
                .append(this.sqlTypeStrings.MAX_INT_NOT_NULL);
        //unknown size of number
        stringBuffer.append(paymentTransactionKeysFactory.ERRCODE.toString())
                .append(this.sqlTypeStrings.MAX_INT_NOT_NULL);
        //1-999 where 999 is the most likely fraud
        stringBuffer.append(paymentTransactionKeysFactory.SCORE.toString())
                .append(this.sqlTypeStrings.THREE_INT_NOT_NULL);
        //reason codes available but not yet programmed
        stringBuffer.append(paymentTransactionKeysFactory.REASON1.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.REASON2.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.REASON3.toString());
        //exceptions available but not yet programmed
        stringBuffer.append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION1.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION2.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION3.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION4.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION5.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION6.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXCEPTION7.toString())
                .append(this.sqlTypeStrings.FOUR_INT_NOT_NULL)
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
     }
     **/
    public String dropTable()
    {
        return super.dropTable();
    }
}
