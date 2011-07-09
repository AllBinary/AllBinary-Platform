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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import abcs.business.init.db.DbConnectionInfo;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.basic.string.regex.replace.Replace;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class AbSqlRow extends AbSqlColumn
{

    public AbSqlRow(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    //Don't allow "null"
    private synchronized String getValue(String value)
    {
        if (StringValidationUtil.getInstance().isEmpty(value))
        {
            return StringUtil.getInstance().EMPTY_STRING;
        } else
        {
            return value;
        }
    }

    public synchronized void updateWhere(String key, String value, HashMap updatedKeyValuePairs)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("UPDATE ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" SET ");

        try
        {
            Iterator iter = updatedKeyValuePairs.keySet().iterator();
            while (iter.hasNext())
            {
                String columnName = iter.next().toString();
                stringBuffer.append(CommonSeps.getInstance().SPACE);
                stringBuffer.append(columnName);
                stringBuffer.append("=\"");
                String columnValue = (String) updatedKeyValuePairs.get(columnName);

                if (columnValue == null)
                {
                    columnValue = StringUtil.getInstance().EMPTY_STRING;
                } else
                {
                    columnValue = new Replace("\"", "\\\"").all(columnValue);
                }

                stringBuffer.append(this.getValue(columnValue));
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(",");
                }
            }

            stringBuffer.append(" WHERE ");
            stringBuffer.append(key);
            stringBuffer.append(" = \"");

            stringBuffer.append(this.getValue(value));
            stringBuffer.append("\"");

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Update Succeeded\nSQL Statement: " + sqlStatement, this, "updateWhere"));
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Update Failed\nSQL Statement: " + stringBuffer, this, "updateWhere", e));
            }
        }
    }

    public synchronized void updateWhere(HashMap whereKeyValuePairs, HashMap updatedKeyValuePairs) throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("UPDATE ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" SET ");

        try
        {
            Iterator iter = updatedKeyValuePairs.keySet().iterator();
            while (iter.hasNext())
            {
                String columnName = iter.next().toString();
                stringBuffer.append(" ");
                stringBuffer.append(columnName);
                stringBuffer.append("=\"");

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("columnName: " + columnName, this, "updateWhere"));
                }

                String columnValue = (String) updatedKeyValuePairs.get(columnName);

                if (columnValue == null)
                {
                    columnValue = StringUtil.getInstance().EMPTY_STRING;
                } else
                {
                    columnValue = new Replace("\"", "\\\"").all(columnValue);
                }

                stringBuffer.append(this.getValue(columnValue));
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(",");
                }
            }

            stringBuffer.append(" WHERE ");

            Set set = whereKeyValuePairs.keySet();
            Iterator whereIter = set.iterator();

            while (whereIter.hasNext())
            {
                String key = (String) whereIter.next();
                String value = (String) whereKeyValuePairs.get(key);
                //if(value!=null && value.compareTo("null")!=0)
                {
                    stringBuffer.append(key);
                    stringBuffer.append(" = \"");

                    stringBuffer.append(this.getValue(value));
                    stringBuffer.append("\"");

                    if (whereIter.hasNext())
                    {
                        stringBuffer.append(" AND ");
                    }
                }

            }

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Update Succeeded\nSQL Statement: " + sqlStatement, this, "updateWhere"));
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Update Failed\nSQL Statement: " + stringBuffer, this, "updateWhere", e));
            }
            throw e;
        }
    }

    public synchronized void deleteWhere(String key, String value)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("DELETE FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");
        stringBuffer.append(key);
        stringBuffer.append(" = \"");
        stringBuffer.append(value);
        stringBuffer.append("\"");

        try
        {
            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Delete Succeeded\nSQL Statement: " + sqlStatement, this, "deleteWhere"));
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Delete Failed\nSQL Statement: " + stringBuffer.toString(), this, "deleteWhere", e));
            }
        }
    }

    public synchronized void deleteWhere(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("DELETE FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");

        try
        {
            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(" = \"");
                stringBuffer.append(this.getValue(value));
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(" AND ");
                }
            }

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Delete Succeeded\nSQL Statement: " + sqlStatement, this, "deleteWhere"));
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Insert Succeeded\nSQL Statement: " + sqlStatement, this, "insert"));
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Insert Failed\nSQL Statement: " + stringBuffer.toString(), this, "insert", e));
            }
        }
    }

    public HashMap getRow(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT *");
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");

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
                stringBuffer.append(" = \"");

                stringBuffer.append(this.getValue(value));
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(" AND ");
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement: " + sqlStatement, this, "getRow"));
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

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRow"));
                }
                return result;
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("No Results in ResultSet", this, "getRow"));
            }

            return null;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + stringBuffer, this, "getRow", e));
            }
            return null;
        }
    }

    private static final String SELECT_ALL = "SELECT *";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String EQUAL_QUOTE = " = \"";
    private static final String CLOSE_QUOTE = "\"";
    
    public Vector getRows(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(SELECT_ALL);
        stringBuffer.append(FROM);
        stringBuffer.append(this.getTableName());
        stringBuffer.append(WHERE);

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
                stringBuffer.append(EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(CLOSE_QUOTE);

                if (iter.hasNext())
                {
                    stringBuffer.append(AND);
                }
            }

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
            	LogUtil.put(LogFactory.getInstance("SQL Statement: " + stringBuffer, this, "getRows"));
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

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRows"));
                }
                LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRows"));

                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + stringBuffer, this, "getRows", e));
            }
            return null;
        }
    }

    public Vector getAllRows()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT *");
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.getTableName());

        try
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement: " + stringBuffer, this, "getAllRows"));
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

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Row Values: " + result.toString(), this, "getAllRows"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + stringBuffer, this, "getAllRows", e));
            }
            return null;
        }
    }

    /*
    public Vector getRowsWhereLike(HashMap keysAndValues, HashMap likeKeysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append("SELECT *");
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");

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
                stringBuffer.append(" = \"");

                stringBuffer.append(this.getValue(value));
                stringBuffer.append("\"");

                if (iter.hasNext() && likeKeysAndValues.size() == 0)
                {
                    stringBuffer.append(" AND ");
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
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(" AND ");
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement: " + sqlStatement, this, "getRowsWhereLike"));
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
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + stringBuffer, this, "getRowsWhereLike", e));
            }
            return new Vector();
        }
    }
    */

    public Vector getRowsWhereBetween(HashMap whereKeyValuePairs,
        String betweenColumn, String smallest, String largest)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT * FROM ");
        stringBuffer.append(this.getTableName());

        try
        {
            Vector rows = new Vector();
            stringBuffer.append(" WHERE ");

            Set set = whereKeyValuePairs.keySet();
            Iterator whereIter = set.iterator();

            while (whereIter.hasNext())
            {
                String key = (String) whereIter.next();
                String value = (String) whereKeyValuePairs.get(key);

                stringBuffer.append(key);
                stringBuffer.append(" = \"");
                stringBuffer.append(this.getValue(value));
                stringBuffer.append("\"");
                stringBuffer.append(" AND ");
            }

            stringBuffer.append(betweenColumn);
            stringBuffer.append(" > \"");
            stringBuffer.append(smallest);
            stringBuffer.append("\"");
            stringBuffer.append(" AND ");
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" < \"");
            stringBuffer.append(largest);
            stringBuffer.append("\"");

            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement: " + sqlStatement, this, "getRowsWhereBetween"));
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
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRowsWhereBetween"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Get Table Failed\nSQL Statement: " + stringBuffer, this, "getRowsWhereBetween()", e));
            }
            return null;
        }
    }

    public Vector getRowsWhereBetween(String betweenColumn, String smallest, String largest)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT * FROM ");
        stringBuffer.append(this.getTableName());

        try
        {
            Vector rows = new Vector();
            stringBuffer.append(" WHERE ");
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" > \"");
            stringBuffer.append(smallest);
            stringBuffer.append("\"");
            stringBuffer.append(" AND ");
            stringBuffer.append(betweenColumn);
            stringBuffer.append(" < \"");
            stringBuffer.append(largest);
            stringBuffer.append("\"");

            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement: " + sqlStatement, this, "getRowsWhereBetween"));
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
                    LogUtil.put(LogFactory.getInstance("Row Value: " + result.toString(), this, "getRowsWhereBetween"));
                }
                rows.add(result);
            }
            return rows;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Get Table Failed\nSQL Statement: " + stringBuffer, this, "getRowsWhereBetween()", e));
            }
            return null;
        }
    }
}
