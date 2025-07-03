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

import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;
import org.allbinary.logic.visual.dhtml.html.select.HtmlSelect;

public class StoreFrontsHelper extends BasicTable
{
    //private StoreFrontInterface storeFrontInterface;

    private final Portion portion;

    public StoreFrontsHelper(HashMap hashMap, PageContext pageContext)
    {
        this.portion = new Portion(hashMap);
    }

    public String drop()
    {
        try
        {
            String success = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().dropTable();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, commonStrings.DROP));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to drop storefronts table";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.DROP, e));
            }
            return error;
        }
    }

    public String create()
    {
        try
        {
            String success = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().createTable();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "create()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to create new storefronts table";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "create()", e));
            }
            return error;
        }
    }

    public String restore()
    {
        try
        {
            final String success = "Restore Successful";
            final String result = AbSqlTableUtil.getInstance().restoreTable(StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance(), this.portion);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to restore backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "restore()", e));
            }
            return error;
        }
    }

    public String backup()
    {
        try
        {
            final String success = "Restore Successful";
            final String result = AbSqlTableUtil.getInstance().backupTable(StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "backup()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to make backup";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "backup()", e));
            }
            return error;
        }
    }

    private static final String ONE = "1";
    private static final String CLASS = "class";
    private static final String TEXT = "text";

    public String generateSelect()
    {
        try
        {
            String success = CommonSeps.getInstance().SPACE;
            Vector storeNamesVector = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().getStoreFrontNames();

            HtmlSelect storeSelect = new HtmlSelect(
                StringUtil.getInstance().EMPTY_STRING, ONE, 
                StoreFrontData.getInstance().SELECTSTORENAME, 
                StringUtil.getInstance().EMPTY_STRING);

            storeSelect.addAttribute(CLASS, TEXT);

            final int size = storeNamesVector.size();
            for (int index = 0; index < size; index++)
            {
                String storeName = (String) storeNamesVector.get(index);
                storeSelect.addOption(storeName);
            }
            success += storeSelect;

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "generateSelect()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to generate storefronts select";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "generateSelect()", e));
            }
            return error;
        }
    }
}
