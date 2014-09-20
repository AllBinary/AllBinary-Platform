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
import org.EntryData;
import org.allbinary.business.user.UserData;
import org.OrderData;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionKeysFactory;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResult;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.TransactionResultInterface;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class TransactionResultEntity extends AbSqlBean 
        implements TransactionResultEntityInterface
{
    private final String tableName = "vresults";

    public TransactionResultEntity() {
        super(new UserDbInitInfo());
        this.setTableName(tableName);
    }

    public void remove(String userName, String orderNumber) {
        try {
            HashMap whereHashMap = new HashMap();
            whereHashMap.put(OrderData.ID, (String) orderNumber);
            whereHashMap.put(UserData.USERNAME, userName);
            super.deleteWhere(whereHashMap);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "remove"));
            }
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "remove", e));
            }
        }
    }

    public void add(String userName, String orderNumber, TransactionResultInterface transactionResultInterface) {
        try {
            Vector values = new Vector();

            values.add(orderNumber);
            values.add(userName);
            values.addAll(transactionResultInterface.getValues());

            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            values.add(time);
            values.add(time);

            super.insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "add"));
            }
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "add", e));
            }
        }
    }

    public TransactionResultInterface getTransactionResultInterface(String orderNumber) {
        try {
            HashMap resultHashMap = new HashMap();
            HashMap updateKeyAndValue = new HashMap();
            updateKeyAndValue.put(OrderData.ID, orderNumber);
            resultHashMap = super.getRow(updateKeyAndValue);

            if (resultHashMap != null) {
                TransactionResult transactionResult = new TransactionResult(resultHashMap);
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                    LogUtil.put(LogFactory.getInstance("Command Success", this, "getTransactionResultInterface"));
                }
                return (TransactionResultInterface) transactionResult;
            } else {
                return null;
            }
        } catch (Exception e) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "getTransactionResultInterface", e));
            }
            return null;
        }
    }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        PaymentTransactionKeysFactory paymentTransactionKeysFactory =
        	PaymentTransactionKeysFactory.getInstance();
        
        stringBuffer.append(OrderData.ID + " VARCHAR(255) NOT NULL,"
            + UserData.USERNAME + " VARCHAR(255) NOT NULL,"
            + paymentTransactionKeysFactory.RESULT + " INT(11) NOT NULL," + //unknown max size but 0 means transaction was a success
            paymentTransactionKeysFactory.PNREF + " VARCHAR(12) NOT NULL,"
            + paymentTransactionKeysFactory.RESPMSG + " BLOB NOT NULL," + //unknown max size of alphanumeric data
            paymentTransactionKeysFactory.AUTHCODE + " VARCHAR(6) NOT NULL,"
            + paymentTransactionKeysFactory.AVSADDR + " VARCHAR(1) NOT NULL,"
            + paymentTransactionKeysFactory.AVSZIP + " VARCHAR(1) NOT NULL,"
            + //Norwest only - unknown sizes/type of data
            paymentTransactionKeysFactory.ORIGRESULT + " VARCHAR(255) NOT NULL,"
            + paymentTransactionKeysFactory.STATUS + " VARCHAR(255) NOT NULL,"
            + //fraud responses
            paymentTransactionKeysFactory.FRAUDCODE + " INT(2) NOT NULL," + //codes available but not yet programmed
            paymentTransactionKeysFactory.FRAUDMSG + " INT(11) NOT NULL," + //msgs available but not yet programmed
            paymentTransactionKeysFactory.ERRCODE + " INT(11) NOT NULL," + //unknown size of number
            paymentTransactionKeysFactory.SCORE + " INT(3) NOT NULL," + //1-999 where 999 is the most likely fraud
            paymentTransactionKeysFactory.REASON1 + " INT(4) NOT NULL," + //reason codes available but not yet programmed
            paymentTransactionKeysFactory.REASON2 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.REASON3 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION1 + " INT(4) NOT NULL," + //exceptions available but not yet programmed
            paymentTransactionKeysFactory.EXCEPTION2 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION3 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION4 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION5 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION6 + " INT(4) NOT NULL,"
            + paymentTransactionKeysFactory.EXCEPTION7 + " INT(4) NOT NULL,"
            + EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, "
            + EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(OrderData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

    public String backupTable() {
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
    }
     **/
    public String dropTable() {
        return super.dropTable();
    }
}
