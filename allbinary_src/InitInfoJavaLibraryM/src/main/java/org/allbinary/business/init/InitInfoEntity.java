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
package org.allbinary.business.init;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class InitInfoEntity extends InitSql
//extends AbSqlBean
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    private final String NAME = "InitInfoEntity";

    private final String NOTHING = "NOTHING";
    //private final String tableName  = "licenseServerInitdata";
    private final String tableName = "initdata";
    private final String tableData;

    private final String NOT_IN_DB = "Not In DB";
    
    private final String METHOD_GET = "get()";
    private final String METHOD_IS = "is()";
    private final String METHOD_ADD = "add()";

    private final String CREATED_SUCCESS = " Created Successfully";
    private final String FAILED_TO_CREATE = "Failed to create ";
    
    public InitInfoEntity()
    {
        super(new UserDbInitInfo());

        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);
        stringBuffer.append(NOTHING);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(InitInfo.getInstance().TESTING);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(InitInfo.getInstance().TESTHTMLPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(InitInfo.getInstance().MAINPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(NOTHING);
        stringBuffer.append(this.sqlStrings.END);

        this.tableData = stringBuffer.toString();
        
        this.setTable(this.tableName);
    }

    public boolean get()
    {
        try
        {
            HashMap keyAndValue = new HashMap();
            keyAndValue.put(NOTHING, NOTHING);
            HashMap hashMap = getRow(keyAndValue);
            if (hashMap != null)
            {
                InitInfo.getInstance().set(hashMap);
                return true;
            } else
            {
                if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
                {
                    PreLogUtil.put(this.NOT_IN_DB, NAME, this.METHOD_GET);
                }
                return false;
            }
        } catch (Exception e)
        {
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put(this.commonStrings.EXCEPTION, this.NAME, this.METHOD_GET, e);
            }
            return false;
        }
    }

    public boolean is()
    {
        try
        {
            HashMap keyAndValue = new HashMap();
            keyAndValue.put(NOTHING, NOTHING);
            HashMap hashMap = getRow(keyAndValue);
            if (hashMap != null)
            {
                return true;
            } else
            {
                if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
                {
                    PreLogUtil.put(this.NOT_IN_DB, this.NAME, this.METHOD_IS);
                }
                return false;
            }
        } catch (Exception e)
        {
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put(this.commonStrings.EXCEPTION, this.NAME, this.METHOD_IS, e);
            }
            return false;
        }
    }

    public void update()
    {
        updateWhere(NOTHING, NOTHING, InitInfo.getInstance().toHashMap());
    }

    public void add()
    {
        try
        {
            Vector values = new Vector();
            values.add(NOTHING);
            values.add(InitInfo.getInstance().getTesting());
            values.add(InitInfo.getInstance().getTestHtmlPath());
            values.add(InitInfo.getInstance().getMainPath());
            insert(values);

        } catch (Exception e)
        {
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put(this.commonStrings.EXCEPTION, this.NAME, this.METHOD_ADD, e);
            }
        }
    }

    public String createTable()
    {
        if (super.createTable(tableData))
        {
            return tableName + CREATED_SUCCESS;
        } else
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(FAILED_TO_CREATE);
            stringBuffer.append(tableData);
            stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);

            return stringBuffer.toString();
        }
    }
    /*
    public String getTable()
    {
    return super.getTable();
    }
     */
}
