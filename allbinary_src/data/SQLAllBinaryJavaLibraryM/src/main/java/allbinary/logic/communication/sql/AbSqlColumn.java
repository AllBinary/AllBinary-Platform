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
package allbinary.logic.communication.sql;

import abcs.business.init.db.DbConnectionInfo;
import abcs.logic.communication.log.LogFactory;

import abcs.logic.communication.log.LogUtil;
import java.sql.ResultSet;
import java.util.Vector;

public class AbSqlColumn extends AbSqlTable
{

    public AbSqlColumn(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public String getLargestIntegerInColumnWhere(String columnName, String key, String value)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(sqlStrings.EQUAL_QUOTE);
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        String sqlStatement = stringBuffer.toString();

        try
        {
            int largest = 0;
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                int intValue = rset.getInt(columnName);

                if (intValue > largest)
                {
                    largest = intValue;
                }
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append("\nLargest Int Value in column: ");
                stringBuffer.append(new Integer(largest).toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getLargestIntegerInColumn"));
            }

            return new Integer(largest).toString();
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getLargestIntegerInColumn", e));
            }
            return "-1";
        }
    }

    public Vector getColumn(String columnName)
    {
        Vector column = new Vector();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());

        String sqlStatement = stringBuffer.toString();

        try
        {
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                String field = rset.getObject(columnName).toString();
                column.add(field);
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getColumn"));
            }

            return column;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getColumn", e));
            }
            return column;
        }
    }

    public Vector getColumnWhere(String columnName, String key, String value)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(sqlStrings.EQUAL_QUOTE);
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        String sqlStatement = stringBuffer.toString();
        Vector column = new Vector();
        try
        {
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                String field = rset.getObject(columnName).toString();
                column.add(field);
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getColumnWhere"));
            }

            return column;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getColumn", e));
            }
            return column;
        }
    }

    /*
    public Vector getColumnWhereLike(String columnName, String key, String value)
    {
        Vector column = new Vector();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(" LIKE \"");
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        String sqlStatement = stringBuffer.toString();

        try
        {
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                String field = rset.getObject(columnName).toString();
                column.add(field);
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length()); 

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getColumnWhereLike"));
            }

            return column;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getColumn", e));
            }
            return column;
        }
    }
    */
}
