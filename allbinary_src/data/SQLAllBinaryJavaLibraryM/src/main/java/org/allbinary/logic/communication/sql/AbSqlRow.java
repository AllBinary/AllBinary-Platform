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
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.basic.string.StringValidationUtil;
import org.allbinary.logic.basic.string.regex.replace.Replace;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AbSqlRow extends AbSqlColumn
{

    private final String EQUAL_QUOTE = "=\"";
    private final String ESCAPE_QUOTES = "\\\"";
    
    public AbSqlRow(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    //Don't allow "null"
    private synchronized String getValue(String value)
    {
        if (StringValidationUtil.getInstance().isEmpty(value))
        {
            return this.stringUtil.EMPTY_STRING;
        } else
        {
            return value;
        }
    }

    public synchronized void updateWhere(String key, String value, HashMap updatedKeyValuePairs)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.UPDATE);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.SET);

        try
        {
            Iterator iter = updatedKeyValuePairs.keySet().iterator();
            while (iter.hasNext())
            {
                String columnName = iter.next().toString();
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

                if (iter.hasNext())
                {
                    stringBuffer.append(this.commonSeps.COMMA);
                }
            }

            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(key);
            stringBuffer.append(sqlStrings.EQUAL_QUOTE);

            stringBuffer.append(this.getValue(value));
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Update Succeeded\nSQL Statement: " + sqlStatement, this, "updateWhere"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Update Failed\nSQL Statement: " + stringBuffer, this, "updateWhere", e));
            }
        }
    }

    public synchronized void updateWhere(HashMap whereKeyValuePairs, HashMap updatedKeyValuePairs) throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.UPDATE);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.SET);

        try
        {
            Iterator iter = updatedKeyValuePairs.keySet().iterator();
            while (iter.hasNext())
            {
                String columnName = iter.next().toString();
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(columnName);
                stringBuffer.append(this.EQUAL_QUOTE);

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("columnName: " + columnName, this, "updateWhere"));
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

                if (iter.hasNext())
                {
                    stringBuffer.append(this.commonSeps.COMMA);
                }
            }

            stringBuffer.append(sqlStrings.WHERE);

            Set set = whereKeyValuePairs.keySet();
            Iterator whereIter = set.iterator();

            while (whereIter.hasNext())
            {
                String key = (String) whereIter.next();
                String value = (String) whereKeyValuePairs.get(key);
                //if(value!=null && value.compareTo("null")!=0)
                {
                    stringBuffer.append(key);
                    stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                    stringBuffer.append(this.getValue(value));
                    stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                    if (whereIter.hasNext())
                    {
                        stringBuffer.append(sqlStrings.AND);
                    }
                }
            }

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Update Succeeded\nSQL Statement: " + sqlStatement, this, "updateWhere"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Update Failed\nSQL Statement: " + stringBuffer, this, "updateWhere", e));
            }
            throw e;
        }
    }

    public synchronized void deleteWhere(String key, String value)
    {
        StringBuffer stringBuffer = new StringBuffer();

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
            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Delete Succeeded\nSQL Statement: " + sqlStatement, this, "deleteWhere"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Delete Failed\nSQL Statement: " + stringBuffer.toString(), this, "deleteWhere", e));
            }
        }
    }

    public synchronized void deleteWhere(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.DELETE);
        stringBuffer.append(this.sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.WHERE);

        try
        {
            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(this.sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(this.getValue(value));
                stringBuffer.append(this.sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(this.sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Delete Succeeded\nSQL Statement: " + sqlStatement, this, "deleteWhere"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Delete Failed\nSQL Statement: " + stringBuffer.toString(), this, "deleteWhere", e));
            }
        }
    }

    public void insert(Vector values)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" VALUES ('");

        try
        {
            //Iterator iter = values.iterator();
            for (int i = 0; i < values.size() - 1; i++)
            {
                String value = this.getValue((String) values.get(i));
                value = new Replace("\\", "\\\\").all(value);

                stringBuffer.append(value);
                stringBuffer.append("','");
            }

            String value = this.getValue((String) values.lastElement());
            value = new Replace("\\", "\\\\").all(value);

            stringBuffer.append(value);
            stringBuffer.append("')");

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Insert Succeeded\nSQL Statement: " + sqlStatement, this, "insert"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Insert Failed\nSQL Statement: " + stringBuffer.toString(), this, "insert", e));
            }
        }
    }

    public HashMap getRow(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.SELECT_ALL);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            HashMap result = null;
            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, "getRow"));
            }

            ResultSet rset = this.executeSQLStatement(sqlStatement);
            ResultSetMetaData resultSetMetaData = rset.getMetaData();

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

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRow"));
                }
                return result;
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("No Results in ResultSet", this, "getRow"));
            }

            return null;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + stringBuffer, this, "getRow", e));
            }
            return null;
        }
    }
    
    public Vector getRows(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(sqlStrings.SELECT_ALL);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            Vector rows = new Vector();
            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
            	LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + stringBuffer, this, "getRows"));
            }

            String sqlStatement = stringBuffer.toString();
            ResultSet rset = this.executeSQLStatement(sqlStatement);
            ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                HashMap result = new HashMap();
                //Vector columnNames = new Vector();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int index = 1; index <= columnCount; index++)
                {
                    String columnName = resultSetMetaData.getColumnName(index);
                    String field = rset.getString(columnName);
                    result.put(columnName, field);
                }

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRows"));
                }
                LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRows"));

                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + stringBuffer, this, "getRows", e));
            }
            return null;
        }
    }

    public Vector getAllRows()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.SELECT_ALL);
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + stringBuffer, this, "getAllRows"));
            }

            Vector rows = new Vector();
            String sqlStatement = stringBuffer.toString();

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

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Values: " + result.toString(), this, "getAllRows"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + stringBuffer, this, "getAllRows", e));
            }
            return null;
        }
    }

    /*
    public Vector getRowsWhereLike(HashMap keysAndValues, HashMap likeKeysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append("SELECT *");
        stringBuffer.append(sqlStrings.FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            Vector rows = new Vector();
            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

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
            iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) likeKeysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(" LIKE \"");

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, "getRowsWhereLike"));
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

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("\nRow Value: " + result.toString(), this, "getRowsWhereLike"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(this.FAILED_SQL_STATEMENT + stringBuffer, this, "getRowsWhereLike", e));
            }
            return new Vector();
        }
    }
    */

    public Vector getRowsWhereBetween(HashMap whereKeyValuePairs,
        String betweenColumn, String smallest, String largest)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.SELECT_ALL);
        stringBuffer.append(this.sqlStrings.FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            Vector rows = new Vector();
            stringBuffer.append(sqlStrings.WHERE);

            Set set = whereKeyValuePairs.keySet();
            Iterator whereIter = set.iterator();

            while (whereIter.hasNext())
            {
                String key = (String) whereIter.next();
                String value = (String) whereKeyValuePairs.get(key);

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);
                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);
                stringBuffer.append(sqlStrings.AND);
            }

            stringBuffer.append(betweenColumn);
            stringBuffer.append(" > \"");
            stringBuffer.append(smallest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);
            stringBuffer.append(sqlStrings.AND);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" < \"");
            stringBuffer.append(largest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, "getRowsWhereBetween"));
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

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRowsWhereBetween"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Get Table Failed\nSQL Statement: " + stringBuffer, this, "getRowsWhereBetween()", e));
            }
            return null;
        }
    }

    public Vector getRowsWhereBetween(String betweenColumn, String smallest, String largest)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.SELECT_ALL);
        stringBuffer.append(this.sqlStrings.FROM);
        stringBuffer.append(this.getTableName());

        try
        {
            Vector rows = new Vector();
            stringBuffer.append(sqlStrings.WHERE);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" > \"");
            stringBuffer.append(smallest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);
            stringBuffer.append(sqlStrings.AND);
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" < \"");
            stringBuffer.append(largest);
            stringBuffer.append(sqlStrings.CLOSE_QUOTE);

            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this, "getRowsWhereBetween"));
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

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRowsWhereBetween"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Get Table Failed\nSQL Statement: " + stringBuffer, this, "getRowsWhereBetween()", e));
            }
            return null;
        }
    }
}
