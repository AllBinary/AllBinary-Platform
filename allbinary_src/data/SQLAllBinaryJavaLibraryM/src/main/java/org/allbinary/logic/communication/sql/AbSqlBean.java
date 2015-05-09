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
import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AbSqlBean extends AbSqlRow
{
    private final String METHOD_GET_FIELD = "getField";
    private final String METHOD_IS_SUBSET_OF_ENTRY = "isSubsetOfEntry";
    
    public AbSqlBean(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public String getField(String key, String value, String requestedField)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(requestedField);
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
            String field = StringUtil.getInstance().EMPTY_STRING;
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {

                field = rset.getString(requestedField);
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }

    public String getField(HashMap keysAndValues, String requestedField)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);
        stringBuffer.append(requestedField);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        
        try
        {
            String field = StringUtil.getInstance().EMPTY_STRING;

            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(value);
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();

            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                field = rset.getString(requestedField);
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
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
            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_GET_FIELD, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }

    //empty hashmap will cause problems
    public String isSubsetOfEntry(String key, String value, HashMap columnsAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT);

        try
        {
            Iterator iter = columnsAndValues.keySet().iterator();

            final String SPACE = CommonSeps.getInstance().SPACE;
            
            stringBuffer.append(SPACE);
            stringBuffer.append(key);

            while (iter.hasNext())
            {
                stringBuffer.append(SPACE);
                stringBuffer.append(iter.next());
            }

            stringBuffer.append(sqlStrings.FROM);
            stringBuffer.append(this.getTableName());
            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(key);
            stringBuffer.append(sqlStrings.EQUAL_QUOTE);
            stringBuffer.append(value);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            String sqlStatement = stringBuffer.toString();

            ResultSet rset = executeSQLStatement(sqlStatement);

            while (rset.next())
            {
                Iterator iter2 = columnsAndValues.keySet().iterator();
                while (iter2.hasNext())
                {
                    String columnName = iter2.next().toString();
                    String field = rset.getObject(columnName).toString();
                    if (field.compareTo((String) columnsAndValues.get(columnName)) != 0)
                    {
                        return org.allbinary.globals.GLOBALS.NOTASUBSET;
                    }
                }

            }

            if (rset.getRow() != 1)
            {
                return org.allbinary.globals.GLOBALS.NOTASUBSET;
            } else
            {
                return org.allbinary.globals.GLOBALS.ISASUBSET;
            }
        } catch (Exception e)
        {
            String sqlStatement = stringBuffer.toString();
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + sqlStatement, this, METHOD_IS_SUBSET_OF_ENTRY, e));
            }
            return this.FAILED_SQL_STATEMENT + sqlStatement;
        }
    }
}
