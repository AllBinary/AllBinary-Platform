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
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.string.regex.replace.Replace;

public class AbSqlRow extends AbSqlColumn
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final String EQUAL_QUOTE = "=\"";
    private final String ESCAPE_QUOTES = "\\\"";
    
    private final String GET_ROW = "Row Value: ";
    private final String METHOD_GET_ROW = "getRow()";
    private final String METHOD_GET_ROWS = "getRows()";
    private final String METHOD_GET_ALL_ROWS = "getAllRows()";
    private final String METHOD_UPDATE_WHERE = "updateWhere()";
    private final String METHOD_GET_ROWS_WHERE_BETWEEN = "getRowsWhereBetween()";
    private final String METHOD_DELETE_WHERE = "deleteWhere()";
    
    private final String ROW_VALUE_LABEL = "Row Value: ";
    private final String ROW_VALUES_LABEL = "Row Values: ";
    
    private final String NO_RESULTS_IN_RESULT_SET = "No Results in ResultSet";
        
    private final String COLUMN_NAME_LABEL = "columnName: ";
    
    private final String INSERT_END = "')";
    
    public AbSqlRow(final DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    //Don't allow "null"
    private synchronized String getValue(final String value)
    {
        if (StringValidationUtil.getInstance().isEmpty(value))
        {
            return this.stringUtil.EMPTY_STRING;
        } else
        {
            return value;
        }
    }

    public synchronized void updateWhere(final String key, final String value, final HashMap updatedKeyValuePairs)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.UPDATE);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.SET);

        try
        {
            final Object[] keyArray = updatedKeyValuePairs.keySet().toArray();
            final int size = keyArray.length;
            for (int i = 0; i < size; i++)
            {
                final String columnName = keyArray[i].toString();
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(columnName);
                stringBuffer.append(EQUAL_QUOTE);
                String columnValue = (String) updatedKeyValuePairs.get(columnName);

                if (columnValue == null)
                {
                    columnValue = this.stringUtil.EMPTY_STRING;
                } else
                {
                    columnValue = new Replace(sqlStrings.CLOSE_QUOTE, ESCAPE_QUOTES).all(columnValue);
                }

                stringBuffer.append(this.getValue(columnValue));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (i < size - 1)
                {
                    stringBuffer.append(this.commonSeps.COMMA);
                }
            }

            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(key);
            stringBuffer.append(sqlStrings.EQUAL_QUOTE);

            stringBuffer.append(this.getValue(value));
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            final String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, METHOD_UPDATE_WHERE);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, METHOD_UPDATE_WHERE, e);
            }
        }
    }

    public synchronized void updateWhere(final HashMap whereKeyValuePairs, final HashMap updatedKeyValuePairs) throws Exception
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.UPDATE);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.SET);

        try
        {
            final Object[] keyArray = updatedKeyValuePairs.keySet().toArray();
            final int size = keyArray.length;
            for (int i = 0; i < size; i++)
            {
                final String columnName = keyArray[i].toString();
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(columnName);
                stringBuffer.append(this.EQUAL_QUOTE);

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(COLUMN_NAME_LABEL + columnName, this, METHOD_UPDATE_WHERE);
                }

                String columnValue = (String) updatedKeyValuePairs.get(columnName);

                if (columnValue == null)
                {
                    columnValue = this.stringUtil.EMPTY_STRING;
                } else
                {
                    columnValue = new Replace(sqlStrings.CLOSE_QUOTE, this.ESCAPE_QUOTES).all(columnValue);
                }

                stringBuffer.append(this.getValue(columnValue));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (i < size - 1)
                {
                    stringBuffer.append(this.commonSeps.COMMA);
                }
            }

            stringBuffer.append(sqlStrings.WHERE);

            final Set set = whereKeyValuePairs.keySet();
            final Object[] keyArray2 = set.toArray();
            final int size2 = keyArray2.length;
            for (int i = 0; i < size2; i++)
            {
                final String key = (String) keyArray2[i];
                final String value = (String) whereKeyValuePairs.get(key);
                //if(value!=null && value.compareTo("null")!=0)
                {
                    stringBuffer.append(key);
                    stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                    stringBuffer.append(this.getValue(value));
                    stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                    if (i < size2 - 1)
                    {
                        stringBuffer.append(sqlStrings.AND);
                    }
                }
            }

            final String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, METHOD_UPDATE_WHERE);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, METHOD_UPDATE_WHERE, e);
            }
            throw e;
        }
    }

    public synchronized void deleteWhere(final String key, final String value)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.DELETE);
        stringBuffer.append(this.sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);
        stringBuffer.append(key);
        stringBuffer.append(sqlStrings.EQUAL_QUOTE);
        stringBuffer.append(value);
        stringBuffer.append(sqlStrings.CLOSE_QUOTE);

        try
        {
            final String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, this.METHOD_DELETE_WHERE);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer.toString(), this, this.METHOD_DELETE_WHERE, e);
            }
        }
    }

    public synchronized void deleteWhere(final HashMap keysAndValues)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.DELETE);
        stringBuffer.append(this.sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.WHERE);

        try
        {
            final Set set = keysAndValues.keySet();
            final Object[] keyArray = set.toArray();
            final int size = keyArray.length;
            for (int i = 0; i < size; i++)
            {
                String key = (String) keyArray[i];
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(this.sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(this.getValue(value));
                stringBuffer.append(this.sqlStrings.CLOSE_QUOTE);

                if (i < size - 1)
                {
                    stringBuffer.append(this.sqlStrings.AND);
                }
            }

            final String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, this.METHOD_DELETE_WHERE);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer.toString(), this, this.METHOD_DELETE_WHERE, e);
            }
        }
    }

    public void insert(final Vector values)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.INSERT_INTO);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.VALUES);

        try
        {
            for (int i = 0; i < values.size() - 1; i++)
            {
                String value = this.getValue((String) values.get(i));
                value = new Replace(this.sqlStrings.ESCAPE, this.sqlStrings.DOUBLE_ESCAPE).all(value);

                stringBuffer.append(value);
                stringBuffer.append(this.sqlStrings.SINGLE_QUOTE_COMMA_SEP);
            }

            String value = this.getValue((String) values.lastElement());
            value = new Replace(this.sqlStrings.ESCAPE, this.sqlStrings.DOUBLE_ESCAPE).all(value);

            stringBuffer.append(value);
            stringBuffer.append(INSERT_END);

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this, INSERT);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer.toString(), this, INSERT, e);
            }
        }
    }
    
    public HashMap getRow(final HashMap keysAndValues)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            HashMap result = null;
            final Set set = keysAndValues.keySet();

            final Object[] keyArray = set.toArray();
            final int size = keyArray.length;
            for(int index = 0; index < size; index++)
            {
                final String key = (String) keyArray[index];
                final String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (index < size - 1)
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            final String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, this.METHOD_GET_ROW);
            }

            final ResultSet rset = this.executeSQLStatement(sqlStatement);
            final ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                result = new HashMap();
                //Vector columnNames = new Vector();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    String columnName = resultSetMetaData.getColumnName(index);
                    String field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ROW_VALUE_LABEL + result.toString(), this, this.METHOD_GET_ROW);
                }
                return result;
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(NO_RESULTS_IN_RESULT_SET, this, this.METHOD_GET_ROW);
            }

            return null;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, this.METHOD_GET_ROW, e);
            }
            return null;
        }
    }

    public Vector getRows(final HashMap keysAndValues) {
        return this.getRows(keysAndValues, StringUtil.getInstance().EMPTY_STRING);
    }
    
    public Vector getRows(final HashMap keysAndValues, final String more)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            final Vector rows = new Vector();
            final Set set = keysAndValues.keySet();

            final Object[] keyArray = set.toArray();
            final int size = keyArray.length;
            for(int index = 0; index < size; index++)
            {
                final String key = (String) keyArray[index];
                final String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (index < size - 1)
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
            	logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + stringBuffer, this, this.METHOD_GET_ROWS);
            }

            stringBuffer.append(more);
            final String sqlStatement = stringBuffer.toString();
            final ResultSet rset = this.executeSQLStatement(sqlStatement);
            final ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                final HashMap result = new HashMap();
                //Vector columnNames = new Vector();
                final int columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    final String columnName = resultSetMetaData.getColumnName(index);
                    final String field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                final String resultAsString = result.toString();
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(this.GET_ROW + resultAsString, this, this.METHOD_GET_ROWS);
                }
                //logUtil.put(this.GET_ROW + resultAsString, this, this.METHOD_GET_ROWS);

                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, this.METHOD_GET_ROWS, e);
            }
            return null;
        }
    }

    public Vector getAllRows()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + stringBuffer, this, this.METHOD_GET_ALL_ROWS);
            }

            final Vector rows = new Vector();
            final String sqlStatement = stringBuffer.toString();

            final ResultSet rset = this.executeSQLStatement(sqlStatement);
            final ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                final HashMap result = new HashMap();
                //final Vector columnNames = new Vector();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    final String columnName = resultSetMetaData.getColumnName(index);
                    final String field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ROW_VALUES_LABEL + result.toString(), this, this.METHOD_GET_ALL_ROWS);
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, this.METHOD_GET_ALL_ROWS, e);
            }
            return null;
        }
    }

    /*
    public Vector getRowsWhereLike(HashMap keysAndValues, HashMap likeKeysAndValues)
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(this.sqlStrings.SELECT_ALL);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            Vector rows = new Vector();
            Set set = keysAndValues.keySet();
            iter = set;

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext() && likeKeysAndValues.size() == 0)
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            set = likeKeysAndValues.keySet();
            iter = set;

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) likeKeysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(this.sqlStrings.LIKE_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, "getRowsWhereLike");
            }

            ResultSet rset = this.executeSQLStatement(sqlStatement);
            ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                HashMap result = new HashMap();
                Vector columnNames = new Vector();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    String columnName = resultSetMetaData.getColumnName(index);
                    String field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ROW_VALUE_LABEL + result.toString(), this, "getRowsWhereLike");
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, "getRowsWhereLike", e);
            }
            return new Vector();
        }
    }
    */

    public Vector getRowsWhereBetween(final HashMap whereKeyValuePairs,
        final String betweenColumn, final String smallest, final String largest)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            final Vector rows = new Vector();
            stringBuffer.append(sqlStrings.WHERE);
            final Set set = whereKeyValuePairs.keySet();
            
            String key;
            String value;
            
            final Object[] keyArray = set.toArray();
            final int size = keyArray.length;
            for(int index = 0; index < size; index++)
            {
                key = (String) keyArray[index];
                value = (String) whereKeyValuePairs.get(key);

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);
                stringBuffer.append(sqlStrings.AND);
            }

            stringBuffer.append(betweenColumn);
            stringBuffer.append(this.sqlStrings.MORE_THAN_QUOTE);
            stringBuffer.append(smallest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);
            stringBuffer.append(sqlStrings.AND);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(this.sqlStrings.LESS_THAN_QUOTE);
            stringBuffer.append(largest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            final String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, METHOD_GET_ROWS_WHERE_BETWEEN);
            }

            final ResultSet rset = this.executeSQLStatement(sqlStatement);
            final ResultSetMetaData resultSetMetaData = rset.getMetaData();

            HashMap result;
            Vector columnNames;
            int columnCount;
            String columnName;
            String field;

            while (rset.next())
            {
                result = new HashMap();
                columnNames = new Vector();
                columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    columnName = resultSetMetaData.getColumnName(index);
                    field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ROW_VALUE_LABEL + result.toString(), this, METHOD_GET_ROWS_WHERE_BETWEEN);
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, METHOD_GET_ROWS_WHERE_BETWEEN, e);
            }
            return null;
        }
    }

    public Vector getRowsWhereBetween(final String betweenColumn, final String smallest, final String largest)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            final Vector rows = new Vector();
            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(this.sqlStrings.MORE_THAN_QUOTE);
            stringBuffer.append(smallest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);
            stringBuffer.append(sqlStrings.AND);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(this.sqlStrings.LESS_THAN_QUOTE);
            stringBuffer.append(largest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            final String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, METHOD_GET_ROWS_WHERE_BETWEEN);
            }

            final ResultSet rset = this.executeSQLStatement(sqlStatement);
            final ResultSetMetaData resultSetMetaData = rset.getMetaData();

            String columnName;
            String field;
            HashMap result;
            Vector columnNames;
            int columnCount;
            while (rset.next())
            {
                result = new HashMap();
                columnNames = new Vector();
                columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    columnName = resultSetMetaData.getColumnName(index);
                    field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ROW_VALUE_LABEL + result.toString(), this, METHOD_GET_ROWS_WHERE_BETWEEN);
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this, METHOD_GET_ROWS_WHERE_BETWEEN, e);
            }
            return null;
        }
    }
}
