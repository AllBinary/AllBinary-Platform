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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;

public class InventoryHelper implements BasicTableInterface
{

    private final Portion portion;

    public InventoryHelper(HashMap hashMap, PageContext pageContext)
    {
        this.portion = new Portion(hashMap);
    }

    public String drop()
    {
        try
        {
            String success = InventoryEntityFactory.getInstance().getInventoryEntityInstance().dropTable();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "drop()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to drop inventory table";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "drop()", e));
            }
            return error;
        }

    }

    public String create()
    {

        try
        {
            String success = InventoryEntityFactory.getInstance().getInventoryEntityInstance().createTable();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "create()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to create new inventory table";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "create()", e));
            }
            return error;
        }
    }

    public String restore()
    {
        try
        {
            final String success = "Restore Successful";
            final String result = AbSqlTableUtil.getInstance().restoreTable(InventoryEntityFactory.getInstance().getInventoryEntityInstance(), this.portion);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to restore backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "restore()", e));
            }
            return error;
        }
    }

    public String backup()
    {
        try
        {
            final String success = "Backup Successful";
            final String result = AbSqlTableUtil.getInstance().backupTable(InventoryEntityFactory.getInstance().getInventoryEntityInstance());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "backup()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to make backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "backup()", e));
            }
            return error;
        }
    }
}
