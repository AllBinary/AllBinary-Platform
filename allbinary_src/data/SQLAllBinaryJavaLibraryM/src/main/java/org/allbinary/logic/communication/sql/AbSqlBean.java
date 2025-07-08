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

import java.util.Set;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AbSqlBean extends AbSqlRow
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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

                    logUtil.put(stringBuffer.toString(), this, METHOD_GET_FIELD);
                }

                return field;
            }
            return null;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e);
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
            final Object[] keyArray = set.toArray();
            final int size = keyArray.length;

            String key;
            String value;
            for (int i = 0; i < size; i++)
            {
                key = (String) keyArray[i];
                value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(value);
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (i < size - 1)
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

                    logUtil.put(stringBuffer.toString(), this, METHOD_GET_FIELD);
                }
                return field;
            }
            return null;
        } catch (Exception e)
        {
            final String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e);
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
            final Object[] keyArray = columnsAndValues.keySet().toArray();
            final int size = keyArray.length;
            
            stringBuffer.append(this.commonSeps.SPACE);
            stringBuffer.append(key);

            for (int i = 0; i < size; i++)
            {
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(keyArray[i]);
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
            while (rset.next())
            {
                Object[] keyArray2 = columnsAndValues.keySet().toArray();
                int size2 = keyArray2.length;
                for (int i = 0; i < size2; i++)
                {
                    columnName = keyArray2[i].toString();
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
                logUtil.put(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_IS_SUBSET_OF_ENTRY, e);
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }
}
