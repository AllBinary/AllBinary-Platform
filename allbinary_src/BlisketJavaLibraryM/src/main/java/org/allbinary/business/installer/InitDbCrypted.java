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

import org.allbinary.business.init.InitInfoEntity;
import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.init.db.LogDbInitInfo;
import org.allbinary.business.init.db.StaticPagesDbInitInfo;
import org.allbinary.business.init.db.UserDbInitInfo;
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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbDatabaseManagement;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.logic.string.StringMaker;

//Warning you must have sql root access
public class InitDbCrypted extends AbDatabaseManagement
//extends InitSql
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private UserDbInitInfo userDbInitInfo;
    private InventoryDbInitInfo inventoryDbInitInfo;
    private HistoryDbInitInfo historyDbInitInfo;
    private StaticPagesDbInitInfo staticpagesDbInitInfo;
    private LogDbInitInfo logDbInitInfo;

    private final String SQL_FILE = "initWeblisket.sql";

    //String db, String userName, String password
    public InitDbCrypted(
            DatabaseConnectionInfoInterface databaseConnectionInfoInterface)
    {
        super((DbConnectionInfo) databaseConnectionInfoInterface);
    }
    
    public Boolean addUsers()
    {
        try
        {
            logUtil.put(this.commonStrings.START, this, this.METHOD_ADD_USERS);

            userDbInitInfo = new UserDbInitInfo();
            inventoryDbInitInfo = new InventoryDbInitInfo();
            historyDbInitInfo = new HistoryDbInitInfo();
            staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = userDbInitInfo.getName();
            String CUSTOMERDBUSER = userDbInitInfo.getUserName();
            String CUSTOMERDBPASSWORD = userDbInitInfo.getPassword();

            if(!this.addDbUser(CUSTOMERDB, CUSTOMERDBUSER, CUSTOMERDBPASSWORD))
            {
                logUtil.put(this.UNABLE_TO_CREATE_USER + CUSTOMERDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String INVENTORYDB = inventoryDbInitInfo.getName();
            String INVENTORYDBUSER = inventoryDbInitInfo.getUserName();
            String INVENTORYDBPASSWORD = inventoryDbInitInfo.getPassword();

            if(!this.addDbUser(INVENTORYDB, INVENTORYDBUSER, INVENTORYDBPASSWORD))
            {
                logUtil.put(this.UNABLE_TO_CREATE_USER + INVENTORYDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String HISTORYDB = historyDbInitInfo.getName();
            String HISTORYDBUSER = historyDbInitInfo.getUserName();
            String HISTORYDBPASSWORD = historyDbInitInfo.getPassword();

            if(!this.addDbUser(HISTORYDB, HISTORYDBUSER, HISTORYDBPASSWORD))
            {
                logUtil.put(this.UNABLE_TO_CREATE_USER + HISTORYDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String STATICPAGESDB = staticpagesDbInitInfo.getName();
            String STATICPAGESDBUSER = staticpagesDbInitInfo.getUserName();
            String STATICPAGESDBPASSWORD = staticpagesDbInitInfo.getPassword();

            if(!this.addDbUser(STATICPAGESDB, STATICPAGESDBUSER, STATICPAGESDBPASSWORD))
            {
                logUtil.put(this.UNABLE_TO_CREATE_USER + STATICPAGESDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String LOGDB = logDbInitInfo.getName();
            String LOGDBUSER = logDbInitInfo.getUserName();
            String LOGDBPASSWORD = logDbInitInfo.getPassword();

            if(!this.addDbUser(LOGDB, LOGDBUSER, LOGDBPASSWORD))
            {
                logUtil.put(this.UNABLE_TO_CREATE_USER + LOGDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            logUtil.put(this.commonStrings.END, this, this.METHOD_ADD_USERS);

            return Boolean.TRUE;
        }catch(Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_USERS, e);
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    public Boolean addDatabases()
    {
        try
        {
            logUtil.put(this.commonStrings.START, this, this.METHOD_ADD_DATABASES);

            userDbInitInfo = new UserDbInitInfo();
            inventoryDbInitInfo = new InventoryDbInitInfo();
            historyDbInitInfo = new HistoryDbInitInfo();
            staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = userDbInitInfo.getName();
            if(!this.addDb(CUSTOMERDB))
            {
                logUtil.put(this.UNABLE_TO_CREATE_DATABASE + CUSTOMERDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String INVENTORYDB = inventoryDbInitInfo.getName();
            if(!this.addDb(INVENTORYDB))
            {
                logUtil.put(this.UNABLE_TO_CREATE_DATABASE + INVENTORYDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String HISTORYDB = historyDbInitInfo.getName();
            if(!this.addDb(HISTORYDB))
            {
                logUtil.put(this.UNABLE_TO_CREATE_DATABASE + HISTORYDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String STATICPAGESDB = staticpagesDbInitInfo.getName();
            if(!this.addDb(STATICPAGESDB))
            {
                logUtil.put(this.UNABLE_TO_CREATE_DATABASE + STATICPAGESDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String LOGDB = logDbInitInfo.getName();
            if(!this.addDb(LOGDB))
            {
                logUtil.put(this.UNABLE_TO_CREATE_DATABASE + LOGDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            AbFile file = FileFactory.getInstance().getInstance(SQL_FILE);
            file.createNewFile();

            AbDataOutputStream idOutData
                    = DataOutputStreamFactory.getInstance().getInstance(file);

            idOutData.writeBytes(this.sqlCommandLog.toString());

            return Boolean.TRUE;
        }catch(Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_DATABASES, e);
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    public Boolean addTables()
    {
        try
        {
            StringMaker stringBuffer = new StringMaker();

            logUtil.put(commonStrings.START, this, this.METHOD_ADD_TABLES);

            stringBuffer.append(UserEntityFactory.getInstance().createTable());

            stringBuffer.append(StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().createTable());

            stringBuffer.append(BillingAddressesEntityFactory.getInstance().getInstance(this.stringUtil.EMPTY_STRING).createTable());
            stringBuffer.append(ShippingAddressesEntityFactory.getInstance().getInstance(this.stringUtil.EMPTY_STRING).createTable());
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

            logUtil.put(ADD_TABLES_RESULTS_LABEL + stringBuffer.toString(), this, this.METHOD_ADD_TABLES);

            return Boolean.TRUE;
        }catch(Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_TABLES, e);
            return Boolean.FALSE;
        }
    }

    /*
     public Boolean useTemporaryMainPath()
     {
     try
     {
     logUtil.put("Set Temp Main Path",this,"setTemporaryMainPath()");
     URLGLOBALS.useTemporaryMainPath();
     return Boolean.TRUE;
     }
     catch(Exception e)
     {
     logUtil.put("Unable to set temp main path",this,"setTemporaryMainPath()",e);
     return Boolean.FALSE;
     }
     }

     public Boolean useNormalMainPath()
     {
     try
     {
     logUtil.put("Set Temp Main Path",this,"setTemporaryMainPath()");
     URLGLOBALS.useNormalMainPath();
     return Boolean.TRUE;
     }
     catch(Exception e)
     {
     logUtil.put("Unable to set temp main path",this,"setTemporaryMainPath()",e);
     return Boolean.FALSE;
     }
     }
     */
}
