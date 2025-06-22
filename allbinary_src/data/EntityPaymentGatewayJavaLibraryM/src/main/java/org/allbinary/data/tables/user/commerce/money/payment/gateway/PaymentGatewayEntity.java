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
package org.allbinary.data.tables.user.commerce.money.payment.gateway;

import org.allbinary.data.generator.PaymentGatewayIdGenerator;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayEncryptedMapping;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterfaceFactory;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.SuperCrypt;

public class PaymentGatewayEntity extends AbSqlBean implements PaymentGatewayEntityInterface
{

    private final String tableName = "paymentgateways";

    //note that xslt files format output from a paymentgateway for a client when required
    public PaymentGatewayEntity()
    {
        super(new UserDbInitInfo());
        this.setTableName(tableName);

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.CONSTRUCTOR, this, this.commonStrings.CONSTRUCTOR));
        }
    }

    public void add(PaymentGatewayInterface paymentGatewayInterface)
    {
        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "add"));
            }

            Vector vector = new Vector();

            vector.add(new PaymentGatewayIdGenerator().getNext());
            //vector.add("auto_increment");
            //values.add(StringUtil.getInstance());

            vector.addAll(new PaymentGatewayEncryptedMapping(paymentGatewayInterface).toVector());
            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            vector.add(time);
            vector.add(time);
            super.insert(vector);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("End", this, "add"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "add", e));
            }
        }
    }

    public void update(PaymentGatewayInterface paymentGatewayInterface)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            paymentGatewayInterface.setLastModified(time);

            HashMap whereKeyValuePairs = new HashMap();

            whereKeyValuePairs.put(
            		StoreFrontData.getInstance().NAME.toString(),
            		paymentGatewayInterface.getStoreName());

            whereKeyValuePairs.put(PaymentGatewayData.NAME.toString(),
                paymentGatewayInterface.getName());

            if(whereKeyValuePairs.get(EntryData.getInstance().TIMECREATED) != null)
            {
                throw new Exception("Don't update TimeCreated for update");
            }

            HashMap updateHashMap = new PaymentGatewayEncryptedMapping(
                paymentGatewayInterface).toHashMap();

            super.updateWhere(whereKeyValuePairs, updateHashMap);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, "update"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "update", e));
            }
        }
    }

    public PaymentGatewayInterface getPaymentGatewayInterface(
        String storeName, BasicPaymentType paymentType)
    {
        try
        {
            HashMap paymentGatewayHashMap = new HashMap();
            HashMap whereKeyAndValue = new HashMap();

            whereKeyAndValue.put(StoreFrontData.getInstance().NAME.toString(), storeName);
            whereKeyAndValue.put(
                PaymentGatewayData.NAME.toString(), paymentType.getName());

            paymentGatewayHashMap = super.getRow(whereKeyAndValue);

            if (paymentGatewayHashMap != null)
            {
                String userName = (String) paymentGatewayHashMap.get(UserData.USERNAME.toString());

                String password = (String) paymentGatewayHashMap.get(UserData.PASSWORD.toString());

                String special1 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL1.toString());

                String special2 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL2.toString());

                String special3 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL3.toString());

                String special4 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL4.toString());

                String special5 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL5.toString());

                String special6 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL6.toString());

                String special7 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL7.toString());

                String special8 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL8.toString());

                String special9 = (String) paymentGatewayHashMap.get(
                    PaymentGatewayData.SPECIAL9.toString());

                SuperCrypt superCrypt = new SuperCrypt(PaymentGatewayData.CRYPTNUM);

                paymentGatewayHashMap.put(
                    UserData.USERNAME.toString(), superCrypt.decrypt(userName));

                paymentGatewayHashMap.put(
                    UserData.PASSWORD.toString(), superCrypt.decrypt(password));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL1.toString(),
                    superCrypt.decrypt(special1));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL2.toString(),
                    superCrypt.decrypt(special2));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL3.toString(),
                    superCrypt.decrypt(special3));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL4.toString(),
                    superCrypt.decrypt(special4));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL5.toString(),
                    superCrypt.decrypt(special5));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL6.toString(),
                    superCrypt.decrypt(special6));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL7.toString(),
                    superCrypt.decrypt(special7));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL8.toString(),
                    superCrypt.decrypt(special8));

                paymentGatewayHashMap.put(
                    PaymentGatewayData.SPECIAL9.toString(),
                    superCrypt.decrypt(special9));

                PaymentGatewayInterface paymentGatewayInterface =
                    new PaymentGatewayInterfaceFactory().getInstance(
                    paymentGatewayHashMap);

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, "getPaymentGatewayInterface()"));
                }
                return paymentGatewayInterface;
            } else
            {
                return new PaymentGatewayInterfaceFactory().getInstance(paymentType);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed for paymentType: " + paymentType.getName(), this, "getPaymentGatewayInterface", e));
            }
            return null;
        }
    }

    public Vector findPaymentTypeVectorByStore(String storeName)
    {
        try
        {
            Vector paymentGatewayNameVector = new Vector();
            //HashMap whereKeyAndValue = new HashMap();

            paymentGatewayNameVector =
                super.getColumnWhere(
                PaymentGatewayData.NAME.toString(),
                StoreFrontData.getInstance().NAME.toString(),
                storeName);

            if (paymentGatewayNameVector != null)
            {
                Vector paymentGatewayVector = new Vector();
                Iterator iter = paymentGatewayNameVector.iterator();
                while (iter.hasNext())
                {
                    String paymentTypeString = (String) iter.next();
                    BasicPaymentType paymentType =
                        BasicPaymentTypeUtil.getInstance().get(paymentTypeString);
                    paymentGatewayVector.add(paymentType);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, "findPaymentTypeVectorByStore()"));
                }
                return paymentGatewayVector;
            } else
            {
                return new Vector();
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "findPaymentTypeVectorByStore", e));
            }
            return null;
        }
    }

    public void remove(String storeName, BasicPaymentType paymentType)
    {
        try
        {
            HashMap whereHashMap = new HashMap();
            
            whereHashMap.put(StoreFrontData.getInstance().NAME.toString(), storeName);
            whereHashMap.put(PaymentGatewayData.NAME.toString(), paymentType.getName());
            
            super.deleteWhere(whereHashMap);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, "remove"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "remove", e));
            }
        }
    }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);

        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(PaymentGatewayData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_AUTO_INCREMENT_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().ENABLE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(StoreFrontData.getInstance().NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentData.METHOD);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.MODE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        //testing or live

        stringBuffer.append(PaymentGatewayData.TESTPROTOCOL);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.TESTSERVER);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.TESTPORT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.TESTPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SERVERPROTOCOL);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SERVER);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SERVERPORT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SERVERPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(UserData.USERNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(UserData.PASSWORD);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.TIMEOUT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYPROTOCOL);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYSERVER);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYPORT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYUSERNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYPASSWORD);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.PROXYTIMEOUT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL1);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL2);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL3);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL4);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL5);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL6);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL7);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL8);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(PaymentGatewayData.SPECIAL9);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(PaymentGatewayData.ID);
        stringBuffer.append(this.sqlStrings.END);

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
