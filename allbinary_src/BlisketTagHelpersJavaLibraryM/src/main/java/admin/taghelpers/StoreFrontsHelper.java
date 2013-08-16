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
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import abcs.business.installer.Portion;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import allbinary.logic.visual.dhtml.html.select.HtmlSelect;

public class StoreFrontsHelper implements BasicTableInterface
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "drop()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to drop storefronts table";
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
            String success = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().createTable();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "create()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to create new storefronts table";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
            String success = "Restore Successful";

            String result = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().restoreTable(this.portion);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to restore backup";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
            String success = "Restore Successful";

            String result = StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().backupTable();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "backup()"));
            }
            return result;
        } catch (Exception e)
        {
            String error = "Failed to make backup";

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "backup()", e));
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

            Iterator storeNameIterator = storeNamesVector.iterator();
            while (storeNameIterator.hasNext())
            {
                String storeName = (String) storeNameIterator.next();
                storeSelect.addOption(storeName);
            }
            success += storeSelect;

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "generateSelect()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to generate storefronts select";
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "generateSelect()", e));
            }
            return error;
        }
    }
}
