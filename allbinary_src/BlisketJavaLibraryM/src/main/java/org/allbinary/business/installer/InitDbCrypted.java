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
package org.allbinary.business.installer;

import java.net.InetAddress;

import org.allbinary.business.init.InitInfoEntity;
import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.init.db.LogDbInitInfo;
import org.allbinary.business.init.db.StaticPagesDbInitInfo;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.basic.io.AbDataOutputStream;
import org.allbinary.logic.basic.io.DataOutputStreamFactory;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.FileFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import org.allbinary.data.tables.log.LogTableEntityFactory;
import org.allbinary.data.tables.staticpages.StaticPagesEntityFactory;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntityFactory;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntityFactory;
import org.allbinary.data.tables.user.commerce.money.payment.PaymentEntityFactory;
import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import org.allbinary.data.tables.user.commerce.money.payment.transaction.TransactionEntityFactory;
import org.allbinary.data.tables.user.commerce.money.payment.transaction.TransactionResultEntityFactory;
import org.allbinary.data.tables.user.quoterequest.QuoteRequestEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlBean;

//Warning you must have sql root access
public class InitDbCrypted extends AbSqlBean
//extends InitSql
{
    private final StringBuffer sqlCommandLog = new StringBuffer();
    private UserDbInitInfo userDbInitInfo;
    private InventoryDbInitInfo inventoryDbInitInfo;
    private HistoryDbInitInfo historyDbInitInfo;
    private StaticPagesDbInitInfo staticpagesDbInitInfo;
    private LogDbInitInfo logDbInitInfo;

    //String db, String userName, String password
    public InitDbCrypted(
        DatabaseConnectionInfoInterface databaseConnectionInfoInterface)
    {
        super((DbConnectionInfo) databaseConnectionInfoInterface);
    }

    private String getHostName()
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Failed", this, "getHostName()", e));
            return null;
        }
    }

    private boolean addDbUser(String db, String userName, String password)
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ");
            stringBuffer.append("DROP, INDEX, ALTER ON ");
            stringBuffer.append(db);
            stringBuffer.append(".*");
            stringBuffer.append(" TO ");
            stringBuffer.append(userName);
            stringBuffer.append("@localhost IDENTIFIED BY '");
            stringBuffer.append(password);
            stringBuffer.append("' WITH GRANT OPTION");

            String sqlStatement = stringBuffer.toString();

            this.sqlCommandLog.append(sqlStatement);
            this.sqlCommandLog.append("\n");

            super.executeSQLStatement(sqlStatement);

            String hostName = this.getHostName();
            if (hostName != null)
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ");
                stringBuffer.append("DROP, INDEX, ALTER ON ");
                stringBuffer.append(db);
                stringBuffer.append(".*");
                stringBuffer.append(" TO ");
                stringBuffer.append(userName);
                stringBuffer.append("@");
                stringBuffer.append(hostName);
                stringBuffer.append(" IDENTIFIED BY '");
                stringBuffer.append(password);
                stringBuffer.append("' WITH GRANT OPTION");

                sqlStatement = stringBuffer.toString();

                super.executeSQLStatement(sqlStatement);
            }
            return true;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to Create User", this, "addDbUser()", e));
            return false;
        }
    }

    public Boolean addUsers()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Adding Users", this, "addUsers()"));

            userDbInitInfo = new UserDbInitInfo();
            inventoryDbInitInfo = new InventoryDbInitInfo();
            historyDbInitInfo = new HistoryDbInitInfo();
            staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = userDbInitInfo.getName();
            String CUSTOMERDBUSER = userDbInitInfo.getUserName();
            String CUSTOMERDBPASSWORD = userDbInitInfo.getPassword();

            if (!this.addDbUser(CUSTOMERDB, CUSTOMERDBUSER, CUSTOMERDBPASSWORD))
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create User: " + CUSTOMERDBUSER, this, "add()"));
                return Boolean.FALSE;
            }

            String INVENTORYDB = inventoryDbInitInfo.getName();
            String INVENTORYDBUSER = inventoryDbInitInfo.getUserName();
            String INVENTORYDBPASSWORD = inventoryDbInitInfo.getPassword();

            if (!this.addDbUser(INVENTORYDB, INVENTORYDBUSER, INVENTORYDBPASSWORD))
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create User: " + INVENTORYDBUSER, this, "add()"));
                return Boolean.FALSE;
            }

            String HISTORYDB = historyDbInitInfo.getName();
            String HISTORYDBUSER = historyDbInitInfo.getUserName();
            String HISTORYDBPASSWORD = historyDbInitInfo.getPassword();

            if (!this.addDbUser(HISTORYDB, HISTORYDBUSER, HISTORYDBPASSWORD))
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create User: " + HISTORYDBUSER, this, "add()"));
                return Boolean.FALSE;
            }

            String STATICPAGESDB = staticpagesDbInitInfo.getName();
            String STATICPAGESDBUSER = staticpagesDbInitInfo.getUserName();
            String STATICPAGESDBPASSWORD = staticpagesDbInitInfo.getPassword();

            if (!this.addDbUser(STATICPAGESDB, STATICPAGESDBUSER, STATICPAGESDBPASSWORD))
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create User: " + STATICPAGESDBUSER, this, "add()"));
                return Boolean.FALSE;
            }

            String LOGDB = logDbInitInfo.getName();
            String LOGDBUSER = logDbInitInfo.getUserName();
            String LOGDBPASSWORD = logDbInitInfo.getPassword();

            if (!this.addDbUser(LOGDB, LOGDBUSER, LOGDBPASSWORD))
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create User: " + LOGDBUSER, this, "add()"));
                return Boolean.FALSE;
            }

            LogUtil.put(LogFactory.getInstance("Users Added", this, "addUsers()"));

            return Boolean.TRUE;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to Create User", this, "addUsers()", e));
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    private Boolean addDb(String db)
    {
        try
        {
            String sqlStatement = "CREATE DATABASE " + db;

            this.sqlCommandLog.append(sqlStatement);
            this.sqlCommandLog.append("\n");

            super.executeSQLStatement(sqlStatement);

            return Boolean.TRUE;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to Create User", this, "addDb()", e));
            return Boolean.FALSE;
        }
    }

    public Boolean addDatabases()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Adding Database", this, "addDatabases()"));

            userDbInitInfo = new UserDbInitInfo();
            inventoryDbInitInfo = new InventoryDbInitInfo();
            historyDbInitInfo = new HistoryDbInitInfo();
            staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = userDbInitInfo.getName();
            if (this.addDb(CUSTOMERDB) == Boolean.FALSE)
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create Database: " + CUSTOMERDB, this, "addDatabases()"));
                return Boolean.FALSE;
            }

            String INVENTORYDB = inventoryDbInitInfo.getName();
            if (this.addDb(INVENTORYDB) == Boolean.FALSE)
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create Database: " + INVENTORYDB, this, "addDatabases()"));
                return Boolean.FALSE;
            }

            String HISTORYDB = historyDbInitInfo.getName();
            if (this.addDb(HISTORYDB) == Boolean.FALSE)
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create Database: " + HISTORYDB, this, "addDatabases()"));
                return Boolean.FALSE;
            }

            String STATICPAGESDB = staticpagesDbInitInfo.getName();
            if (this.addDb(STATICPAGESDB) == Boolean.FALSE)
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create Database: " + STATICPAGESDB, this, "addDatabases()"));
                return Boolean.FALSE;
            }

            String LOGDB = logDbInitInfo.getName();
            if (this.addDb(LOGDB) == Boolean.FALSE)
            {
                LogUtil.put(LogFactory.getInstance("Unable to Create Database: " + LOGDB, this, "addDatabases()"));
                return Boolean.FALSE;
            }

            AbFile file = FileFactory.getInstance().getInstance("initWeblisket.sql");
            file.createNewFile();

            AbDataOutputStream idOutData =
                DataOutputStreamFactory.getInstance().getInstance(file);

            idOutData.writeBytes(this.sqlCommandLog.toString());

            return Boolean.TRUE;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to Create Database", this, "addDatabases()", e));
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    public Boolean addTables()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            LogUtil.put(LogFactory.getInstance("Adding Tables", this, "addTables"));

            stringBuffer.append(UserEntityFactory.getInstance().createTable());

            stringBuffer.append(StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().createTable());

            stringBuffer.append(BillingAddressesEntityFactory.getInstance().getInstance("").createTable());
            stringBuffer.append(ShippingAddressesEntityFactory.getInstance().getInstance("").createTable());
            stringBuffer.append(PaymentEntityFactory.getInstance().getPaymentEntityInstance().createTable());
            stringBuffer.append(QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().createTable());

            stringBuffer.append(OrderHistoryEntityFactory.getInstance().createTable());
            stringBuffer.append(OrderItemsEntityFactory.getInstance().createTable());
            stringBuffer.append(TransactionEntityFactory.getInstance().getTransactionEntityInstance().createTable());
            stringBuffer.append(TransactionResultEntityFactory.getInstance().getTransactionResultEntityInstance().createTable());

            stringBuffer.append(PaymentGatewayEntityFactory.getInstance().createTable());

            //stringBuffer.append(CategoryEntityFactory.getInstance().createTable());
            stringBuffer.append(InventoryEntityFactory.getInstance().getInventoryEntityInstance().createTable());

            /*
            stringBuffer.append(CustomItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(DownloadItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(BasicGroupItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(BasicOptionItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(XmlOptionItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(PermissionItemsEntityFactory.getInstance().createTable()));
            stringBuffer.append(SpecialItemsEntityFactory.getInstance().createTable()));
             */

            stringBuffer.append(new InitInfoEntity().createTable());
            stringBuffer.append(LogTableEntityFactory.getInstance().getLogTableEntityInstance().createTable());
            stringBuffer.append(StaticPagesEntityFactory.getInstance().getStaticPagesEntityInstance().createTable());
            stringBuffer.append(TransformInfoEntityBuilder.getInstance().createTable());
            //ViewTemplateEntityFactory.getInstance().createTable());

            LogUtil.put(LogFactory.getInstance("Add Table Results: " + stringBuffer.toString(), this, "addTables"));

            return Boolean.TRUE;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to Create Tables", this, "addTables", e));
            return Boolean.FALSE;
        }
    }

    /*
    public Boolean useTemporaryMainPath()
    {
    try
    {
    LogUtil.put(LogFactory.getInstance("Set Temp Main Path",this,"setTemporaryMainPath()");
    URLGLOBALS.useTemporaryMainPath();
    return Boolean.TRUE;
    }
    catch(Exception e)
    {
    LogUtil.put(LogFactory.getInstance("Unable to set temp main path",this,"setTemporaryMainPath()",e);
    return Boolean.FALSE;
    }
    }

    public Boolean useNormalMainPath()
    {
    try
    {
    LogUtil.put(LogFactory.getInstance("Set Temp Main Path",this,"setTemporaryMainPath()");
    URLGLOBALS.useNormalMainPath();
    return Boolean.TRUE;
    }
    catch(Exception e)
    {
    LogUtil.put(LogFactory.getInstance("Unable to set temp main path",this,"setTemporaryMainPath()",e);
    return Boolean.FALSE;
    }
    }
     */
}
