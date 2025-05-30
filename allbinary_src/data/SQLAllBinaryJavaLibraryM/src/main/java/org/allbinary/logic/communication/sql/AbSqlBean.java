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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AbSqlBean extends AbSqlRow
{
    private final String METHOD_GET_FIELD = "getField";
    private final String METHOD_IS_SUBSET_OF_ENTRY = "isSubsetOfEntry";
    
    public AbSqlBean(final DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public String getField(final String key, final String value, final String requestedField)
    {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(requestedField);
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
            String field = this.stringUtil.EMPTY_STRING;
            final ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {

                field = rset.getString(requestedField);
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                    stringBuffer.append(sqlStatement);
                    stringBuffer.append(sqlStrings.FIELD_VALUE);
                    stringBuffer.append(field);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_GET_FIELD));
                }

                return field;
            }
            return null;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }

    public String getField(final HashMap keysAndValues, final String requestedField)
    {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(requestedField);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        
        try
        {
            String field = this.stringUtil.EMPTY_STRING;

            final Set set = keysAndValues.keySet();
            final Iterator iter = set.iterator();

            String key;
            String value;
            while (iter.hasNext())
            {
                key = (String) iter.next();
                value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(value);
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            final String sqlStatement = stringBuffer.toString();

            final ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                field = rset.getString(requestedField);
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append(sqlStrings.SQL_STATEMENT_LABEL);
                    stringBuffer.append(sqlStatement);
                    stringBuffer.append(sqlStrings.FIELD_VALUE);
                    stringBuffer.append(field);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_GET_FIELD));
                }
                return field;
            }
            return null;
        } catch (Exception e)
        {
            final String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }

    //empty hashmap will cause problems
    public String isSubsetOfEntry(final String key, final String value, final HashMap columnsAndValues)
    {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);

        try
        {
            final Iterator iter = columnsAndValues.keySet().iterator();
            
            stringBuffer.append(this.commonSeps.SPACE);
            stringBuffer.append(key);

            while (iter.hasNext())
            {
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(iter.next());
            }

            stringBuffer.append(sqlStrings.FROM);
            stringBuffer.append(this.getTableName());
            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(key);
            stringBuffer.append(sqlStrings.EQUAL_QUOTE);
            stringBuffer.append(value);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            final String sqlStatement = stringBuffer.toString();

            final ResultSet rset = executeSQLStatement(sqlStatement);

            String columnName;
            String field;
            Iterator iter2;
            while (rset.next())
            {
                iter2 = columnsAndValues.keySet().iterator();
                while (iter2.hasNext())
                {
                    columnName = iter2.next().toString();
                    field = rset.getObject(columnName).toString();
                    if (field.compareTo((String) columnsAndValues.get(columnName)) != 0)
                    {
                        return org.allbinary.globals.GLOBALS2.NOTASUBSET;
                    }
                }

            }

            if (rset.getRow() != 1)
            {
                return org.allbinary.globals.GLOBALS2.NOTASUBSET;
            } else
            {
                return org.allbinary.globals.GLOBALS2.ISASUBSET;
            }
        } catch (Exception e)
        {
            final String sqlStatement = stringBuffer.toString();
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_IS_SUBSET_OF_ENTRY, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }
}
