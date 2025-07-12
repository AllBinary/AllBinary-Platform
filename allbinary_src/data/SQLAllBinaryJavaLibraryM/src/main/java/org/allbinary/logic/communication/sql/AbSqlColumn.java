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

import java.sql.ResultSet;
import java.util.Vector;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;

public class AbSqlColumn extends AbSqlTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final String LARGEST_INT_VALUE_IN_COLUMN = "\nLargest Int Value in column: ";
    private final String METHOD_GET_LARGETS_INTEGER_IN_COLUMN = "getLargestIntegerInColumn";
    private final String METHOD_GET_COLUMN = "getColumn";
    private final String METHOD_GET_COLUMN_WHERE = "getColumnWhere";
    private final String INVALID_RESULT = "-1";
    
    public AbSqlColumn(final DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public String getLargestIntegerInColumnWhere(final String columnName, final String key, final String value)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(sqlStrings.EQUAL_QUOTE);
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        final String sqlStatement = stringBuffer.toString();

        try
        {
            int largest = 0;
            final ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                int intValue = rset.getInt(columnName);

                if (intValue > largest)
                {
                    largest = intValue;
                }
            }

            String largestAsString = new Integer(largest).toString();
            
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(LARGEST_INT_VALUE_IN_COLUMN);
                stringBuffer.append(largestAsString);

                logUtil.put(stringBuffer.toString(), this, METHOD_GET_LARGETS_INTEGER_IN_COLUMN);
            }

            return largestAsString;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_LARGETS_INTEGER_IN_COLUMN, e);
            }
            return INVALID_RESULT;
        }
    }

    public Vector getColumn(final String columnName)
    {
        final Vector column = new Vector();

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());

        final String sqlStatement = stringBuffer.toString();

        try
        {
            final ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                String field = rset.getObject(columnName).toString();
                column.add(field);
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                logUtil.put(stringBuffer.toString(), this, METHOD_GET_COLUMN);
            }

            return column;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_COLUMN, e);
            }
            return column;
        }
    }

    public Vector getColumnWhere(String columnName, String key, String value)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(columnName);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(sqlStrings.EQUAL_QUOTE);
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        final String sqlStatement = stringBuffer.toString();
        Vector column = new Vector();
        ResultSet rset;
        String field;
        try
        {
            rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                field = rset.getObject(columnName).toString();
                column.add(field);
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                logUtil.put(stringBuffer.toString(), this, METHOD_GET_COLUMN_WHERE);
            }

            return column;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, this.METHOD_GET_COLUMN_WHERE, e);
            }
            return column;
        }
    }

    /*
    public Vector getColumnWhereLike(String columnName, String key, String value)
    {
        Vector column = new Vector();

        StringMaker stringBuffer = new StringMaker();

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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length()); 

                stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                stringBuffer.append(sqlStatement);
                stringBuffer.append(sqlStrings.COLUMN_VALUE);
                stringBuffer.append(column.toString());

                logUtil.put(stringBuffer.toString(), this, "getColumnWhereLike");
            }

            return column;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, "getColumn", e);
            }
            return column;
        }
    }
    */
}
