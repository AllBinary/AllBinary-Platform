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
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionKeysFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;

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

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(OrderData.ID)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TRXTYPE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TENDER.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ACCT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.EXPDATE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.AMT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.AUTHCODE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.MICR.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CHECKNUM.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.NAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.STREET.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CITY.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.STATE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ZIP.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DL.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.EMAIL.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMENT1.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMENT2.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ORIGID.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PONUM.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DESC.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DESC1TO4.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.INVNUM.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOZIP.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SWIPE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TAXAMT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COMMCARD.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DUTYAMT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.FREIGHTAMT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ORDERDATE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.TAXEXEMPT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COUNTRYCODE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CUSTCODE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CVV2.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ABA.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.ACCTTYPE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DISCOUNT.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.FIRSTNAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.LASTNAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPFROMZIP.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PRENOTE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.CHKTYPE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.DOB.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.PHONENUM.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SS.toString())
                .append(" VARCHAR(30) NOT NULL,")
                .append(paymentTransactionKeysFactory.COMPANYNAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.COUNTRY.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOCITY.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOFIRSTNAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOLASTNAME.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOSTATE.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SHIPTOSTREET.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL1.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL2.toString())
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(paymentTransactionKeysFactory.SPECIAL3.toString())
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
