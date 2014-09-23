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
import org.allbinary.logic.basic.path.AbPathData;
import org.allbinary.logic.communication.log.config.type.LogConfigType;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class InitInfoEntity extends InitSql
//extends AbSqlBean
{

    private final String NOTHING = "NOTHING";
    //private final String tableName  = "licenseServerInitdata";
    private final String tableName = "initdata";
    private final String tableData;

    public InitInfoEntity()
    {
        super(new UserDbInitInfo());

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");
        stringBuffer.append(NOTHING);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(InitInfo.getInstance().TESTING);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(InitInfo.getInstance().TESTHTMLPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(InitInfo.getInstance().MAINPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append("PRIMARY KEY(NOTHING) )");

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
                if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADER))
                {
                    PreLogUtil.put("Not In DB", "InitInfoEntity", "get()");
                }
                return false;
            }
        } catch (Exception e)
        {
            if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADERERROR))
            {
                PreLogUtil.put("Not In DB", "InitInfoEntity", "get()", e);
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
                if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADER))
                {
                    PreLogUtil.put("Not In DB", "InitInfoEntity", "is()");
                }
                return false;
            }
        } catch (Exception e)
        {
            if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADERERROR))
            {
                PreLogUtil.put("Not In DB", "InitInfoEntity", "is()", e);
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
            if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADERERROR))
            {
                PreLogUtil.put("error", "InitInfoEntity", "add()", e);
            }
        }
    }

    public String createTable()
    {
        if (super.createTable(tableData))
        {
            return tableName + " Created Successfully";
        } else
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Failed to create ");
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
