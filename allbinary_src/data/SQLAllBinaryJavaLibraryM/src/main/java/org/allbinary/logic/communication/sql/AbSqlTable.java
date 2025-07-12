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
package org.allbinary.logic.communication.sql;


import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogUtil;

public class AbSqlTable extends AbSqlBasic
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private String tableName;

    private final String METHOD_CREATED_TABLE = "createTable()";
    private final String METHOD_DROP_TABLE = "dropTable()";
    
    private final String TABLE_CREATION_SUCCESS = "Table Creation Successful: ";
    private final String DROPPED_SUCCESS = " Dropped Successfully";
    
    public AbSqlTable(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public synchronized String createTable(String data)
    {
        try
        {
            this.executeSQLStatement(data);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(TABLE_CREATION_SUCCESS + this.tableName + " with statement: " + data, this, this.METHOD_CREATED_TABLE);
            }
            return tableName + sqlStrings.CREATE_RETURN;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put("Table Creation Failed: " + this.tableName + " with statement: " + data, this, this.METHOD_CREATED_TABLE, e);
            }
            return "Failed to Create " + tableName + " table.";
        }
    }

    public synchronized String dropTable()
    {
        String sqlStatement = sqlStrings.DROP_TABLE + tableName;
        try
        {
            this.executeSQLStatement(sqlStatement);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, this.METHOD_DROP_TABLE);
            }
            return tableName + DROPPED_SUCCESS;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, this.METHOD_DROP_TABLE, e);
            }
            return "Failed to Drop " + tableName + " table.";
        }
    }

}
