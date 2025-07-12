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
package org.allbinary.data.tables.user.commerce.money.payment;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.money.payment.Payment;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.money.payment.PaymentInterface;
import org.allbinary.data.generator.PaymentIdGenerator;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.SuperCrypt;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class PaymentEntity extends AbSqlBean implements PaymentEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String tableName = "payment";

    public PaymentEntity()
    {
        super(new UserDbInitInfo());
        this.setTableName(tableName);
    }

    public String getLastId(String userName)
    {
        return super.getLargestIntegerInColumnWhere(PaymentData.ID, UserData.USERNAME, userName);
    }

    public void setDefault(String userName, Integer index)
    {
        try
        {
            HashMap updateKeyAndValue = new HashMap();
            HashMap whereKeyAndValue = new HashMap();

            whereKeyAndValue.put(UserData.USERNAME, userName);

            //remove old default value if it exist
            PaymentInterface paymentInterface = getDefault(userName);
            if(paymentInterface != null)
            {
                updateKeyAndValue.put(EntryData.getInstance().DEFAULT, StringUtil.getInstance().EMPTY_STRING);
                whereKeyAndValue.put(PaymentData.ID, paymentInterface.getId());
                super.updateWhere(whereKeyAndValue, updateKeyAndValue);
            }

            updateKeyAndValue.put(EntryData.getInstance().DEFAULT, EntryData.getInstance().DEFAULT);
            whereKeyAndValue.put(PaymentData.ID, index.toString());

            super.updateWhere(whereKeyAndValue, updateKeyAndValue);
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "setDefault");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "setDefault", e);
            }
        }
    }

    public Vector get(String userName)
    {
        try
        {
            Vector paymentVector = new Vector();
            HashMap keyAndValue = new HashMap();
            keyAndValue.put(UserData.USERNAME, userName);
            Vector paymentList = super.getRows(keyAndValue);

            final int size = paymentList.size();
            for (int index = 0; index < size; index++)
            {
                HashMap paymentHashMap = (HashMap) paymentList.get(index);

                Payment payment = new Payment(paymentHashMap);

                if(payment != null)
                {
                    paymentVector.add(payment);
                }
            }

            return paymentVector;

        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, commonStrings.GET, e);
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
            updateKeyAndValue.put(EntryData.getInstance().DEFAULT, EntryData.getInstance().DEFAULT);
            updateKeyAndValue.put(UserData.USERNAME, userName);

            paymentHashMap = super.getRow(updateKeyAndValue);
            if(paymentHashMap != null)
            {
                Payment payment = new Payment(paymentHashMap);
                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(this.commonStrings.SUCCESS, this, "getDefault");
                }
                return (PaymentInterface) payment;
            }else
            {
                return null;
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "getDefault", e);
            }
            return null;
        }
    }

    public void remove(String userName, Integer index)
    {
        try
        {
            HashMap whereHashMap = new HashMap();
            whereHashMap.put(UserData.USERNAME, userName);
            whereHashMap.put(PaymentData.ID, (String) index.toString());
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

    public void add(String userName, PaymentInterface paymentInterface)
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

            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            vector.add(time);

            super.insert(vector);

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

    public final String createTableStatement()
    {
        EntryData entryData = EntryData.getInstance();

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(PaymentData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_AUTO_INCREMENT_NOT_NULL)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(entryData.DEFAULT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(PaymentData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(PaymentData.TYPE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(PaymentData.EXPIRATION)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(PaymentData.NUMBER)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(entryData.ENCRYPTION)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(entryData.TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(PaymentData.ID)
                .append(this.sqlStrings.END);

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
}
