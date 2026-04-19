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
            this.logUtil.putF(this.commonStrings.START, this, this.METHOD_ADD_USERS);

            this.userDbInitInfo = new UserDbInitInfo();
            this.inventoryDbInitInfo = new InventoryDbInitInfo();
            this.historyDbInitInfo = new HistoryDbInitInfo();
            this.staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            this.logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = this.userDbInitInfo.getName();
            String CUSTOMERDBUSER = this.userDbInitInfo.getUserName();
            String CUSTOMERDBPASSWORD = this.userDbInitInfo.getPassword();

            if(!this.addDbUser(CUSTOMERDB, CUSTOMERDBUSER, CUSTOMERDBPASSWORD))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_USER + CUSTOMERDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String INVENTORYDB = this.inventoryDbInitInfo.getName();
            String INVENTORYDBUSER = this.inventoryDbInitInfo.getUserName();
            String INVENTORYDBPASSWORD = this.inventoryDbInitInfo.getPassword();

            if(!this.addDbUser(INVENTORYDB, INVENTORYDBUSER, INVENTORYDBPASSWORD))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_USER + INVENTORYDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String HISTORYDB = this.historyDbInitInfo.getName();
            String HISTORYDBUSER = this.historyDbInitInfo.getUserName();
            String HISTORYDBPASSWORD = this.historyDbInitInfo.getPassword();

            if(!this.addDbUser(HISTORYDB, HISTORYDBUSER, HISTORYDBPASSWORD))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_USER + HISTORYDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String STATICPAGESDB = this.staticpagesDbInitInfo.getName();
            String STATICPAGESDBUSER = this.staticpagesDbInitInfo.getUserName();
            String STATICPAGESDBPASSWORD = this.staticpagesDbInitInfo.getPassword();

            if(!this.addDbUser(STATICPAGESDB, STATICPAGESDBUSER, STATICPAGESDBPASSWORD))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_USER + STATICPAGESDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            String LOGDB = this.logDbInitInfo.getName();
            String LOGDBUSER = this.logDbInitInfo.getUserName();
            String LOGDBPASSWORD = this.logDbInitInfo.getPassword();

            if(!this.addDbUser(LOGDB, LOGDBUSER, LOGDBPASSWORD))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_USER + LOGDBUSER, this, this.METHOD_ADD_USERS);
                return Boolean.FALSE;
            }

            this.logUtil.putF(this.commonStrings.END, this, this.METHOD_ADD_USERS);

            return Boolean.TRUE;
        }catch(Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_USERS, e);
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    public Boolean addDatabases()
    {
        try
        {
            this.logUtil.putF(this.commonStrings.START, this, this.METHOD_ADD_DATABASES);

            this.userDbInitInfo = new UserDbInitInfo();
            this.inventoryDbInitInfo = new InventoryDbInitInfo();
            this.historyDbInitInfo = new HistoryDbInitInfo();
            this.staticpagesDbInitInfo = new StaticPagesDbInitInfo();
            this.logDbInitInfo = new LogDbInitInfo();

            String CUSTOMERDB = this.userDbInitInfo.getName();
            if(!this.addDb(CUSTOMERDB))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_DATABASE + CUSTOMERDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String INVENTORYDB = this.inventoryDbInitInfo.getName();
            if(!this.addDb(INVENTORYDB))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_DATABASE + INVENTORYDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String HISTORYDB = this.historyDbInitInfo.getName();
            if(!this.addDb(HISTORYDB))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_DATABASE + HISTORYDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String STATICPAGESDB = this.staticpagesDbInitInfo.getName();
            if(!this.addDb(STATICPAGESDB))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_DATABASE + STATICPAGESDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            String LOGDB = this.logDbInitInfo.getName();
            if(!this.addDb(LOGDB))
            {
                this.logUtil.putF(this.UNABLE_TO_CREATE_DATABASE + LOGDB, this, this.METHOD_ADD_DATABASES);
                return Boolean.FALSE;
            }

            AbFile file = FileFactory.getInstance().getInstance(this.SQL_FILE);
            file.createNewFile();

            AbDataOutputStream idOutData
                    = DataOutputStreamFactory.getInstance().getInstance(file);

            idOutData.writeBytes(this.sqlCommandLog.toString());

            return Boolean.TRUE;
        }catch(Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_DATABASES, e);
            //return Boolean.FALSE;
            return Boolean.TRUE;
        }
    }

    public Boolean addTables()
    {
        try
        {
            StringMaker stringBuffer = new StringMaker();

            this.logUtil.putF(commonStrings.START, this, this.METHOD_ADD_TABLES);

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

            this.logUtil.putF(ADD_TABLES_RESULTS_LABEL + stringBuffer.toString(), this, this.METHOD_ADD_TABLES);

            return Boolean.TRUE;
        }catch(Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_ADD_TABLES, e);
            return Boolean.FALSE;
        }
    }

    /*
     public Boolean useTemporaryMainPath()
     {
     try
     {
     this.logUtil.putF("Set Temp Main Path",this,"setTemporaryMainPath()");
     URLGLOBALS.useTemporaryMainPath();
     return Boolean.TRUE;
     }
     catch(Exception e)
     {
     this.logUtil.put("Unable to set temp main path",this,"setTemporaryMainPath()",e);
     return Boolean.FALSE;
     }
     }

     public Boolean useNormalMainPath()
     {
     try
     {
     this.logUtil.putF("Set Temp Main Path",this,"setTemporaryMainPath()");
     URLGLOBALS.useNormalMainPath();
     return Boolean.TRUE;
     }
     catch(Exception e)
     {
     this.logUtil.put("Unable to set temp main path",this,"setTemporaryMainPath()",e);
     return Boolean.FALSE;
     }
     }
     */
}
