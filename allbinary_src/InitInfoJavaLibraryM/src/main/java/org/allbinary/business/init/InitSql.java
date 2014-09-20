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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.basic.string.StringValidationUtil;

import org.allbinary.logic.communication.log.PreLogUtil;

public class InitSql
{
   private DbConnectionInfo databaseConnectionInfoInterface;

   private String userid;
   private String password;
   
   private String tableName;
   
   private Connection conn;
   
   private boolean useridAndPassword;
      
   public InitSql(DbConnectionInfo databaseConnectionInfoInterface)
   {
      this.setDatabaseConnectionInfoInterface(databaseConnectionInfoInterface);
   }

   public void setTable(String tableName)
   {
      this.tableName = tableName;
   }

   public void setDatabaseConnectionInfoInterface(
      DbConnectionInfo databaseConnectionInfoInterface)
   {
      this.databaseConnectionInfoInterface = databaseConnectionInfoInterface;
   }
   
   public boolean createTable(String tableData)
   {
      try
      {
         this.executeSQLStatement(tableData);
         return true;         
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
             PreLogUtil.put("error","InitSql","createTables()",e);
         }
         return false;
      }
   }
   
   public boolean dropTable()
   {
      String sqlStatement = "DROP TABLE " + tableName;
      try
      {
         this.executeSQLStatement(sqlStatement);
         return true;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {         
            PreLogUtil.put("Failed to Drop","InitSql","dropTables()",e);
         }
         return false;
      }
   }
      
    public HashMap getRow(HashMap keysAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT *");
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.tableName);
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                PreLogUtil.put("SQL Statement: " + stringBuffer, this, "getRow");
            }

            String sqlStatement = stringBuffer.toString();
            ResultSet rset = this.executeSQLStatement(sqlStatement);
            ResultSetMetaData resultSetMetaData = rset.getMetaData();

            while (rset.next())
            {
                result = new HashMap();
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
                    PreLogUtil.put("Row Value: " + result.toString(), this, "getRow");
                }
                return result;
            }
            return null;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                PreLogUtil.put("Failed\nSQL Statement: " + stringBuffer, this, "getRow", e);
            }
            return null;
        }
    }
   
    public synchronized void updateWhere(String key, String value, HashMap updatedKeyValuePairs)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("UPDATE ");
        stringBuffer.append(this.tableName);
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
                PreLogUtil.put("Update Succeeded\nSQL Statement: " + sqlStatement, this, "updateWhere");
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                PreLogUtil.put("Update Failed\nSQL Statement: " + stringBuffer, this, "updateWhere", e);
            }
        }
    }
      
    public void insert(Vector values)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(this.tableName);
        stringBuffer.append(" VALUES ('");

        try
        {
            //Iterator iter = values.iterator();
            for (int i = 0; i < values.size() - 1; i++)
            {
                String value = this.getValue((String) values.get(i));

                stringBuffer.append(value);
                stringBuffer.append("','");
            }

            String value = this.getValue((String) values.lastElement());

            stringBuffer.append(value);
            stringBuffer.append("')");

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                PreLogUtil.put("Insert Succeeded\nSQL Statement: " + sqlStatement, this, "insert");
            }
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                PreLogUtil.put("Insert Failed\nSQL Statement: " + stringBuffer.toString(), this, "insert", e);
            }
        }
    }

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

   public ResultSet executeSQLStatement(String statement) throws Exception, SQLException
   {
      try
      {
         if(conn == null)
         {
            initialize();
         }
         Statement stmt = conn.createStatement();
         //ResultSet rset = stmt.executeQuery(statement);
         //TWB- Changes For GAE JIQL
         stmt.execute(statement);
         ResultSet rset = stmt.getResultSet();

         stmt.close();
         return rset;
      }
      catch(SQLException e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {         
            PreLogUtil.put("SQL error","InitSql","executeSQLStatement()",e);
         }
         throw e;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {         
            PreLogUtil.put("SQL error","InitSql","executeSQLStatement()",e);
         }
         throw e;
      }      
   }
   
   private void createConnection() throws SQLException
   {
      try
      {
         if(useridAndPassword==true)
         {
            conn = DriverManager.getConnection(
               this.getDatabaseConnectionInfoInterface().getUrl(), userid, password);
         }
         else
         {
            conn = DriverManager.getConnection(
               this.getDatabaseConnectionInfoInterface().getUrl());
         }
      }
      catch(SQLException se)
      {
         PreLogUtil.put("error","InitSql","createConnection()",se);
         throw se;
      }
   }
   
   private void initialize() throws Exception
   {
      try
      {
         try
         {            
            Class.forName(this.getDatabaseConnectionInfoInterface().getJdbcDriver()).newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
         }
         catch(Exception e)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {                     
               PreLogUtil.put("Load mySQL Driver Failed: " + 
                  this.getDatabaseConnectionInfoInterface().getJdbcDriver(),
                  "InitSql","initialization()",e);
            }
            throw e;
         }         
         if(userid == null && password == null)
         {
            useridAndPassword=true;
         }         
         this.createConnection();         
      }
      catch(Exception se)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {                  
            PreLogUtil.put("Error","InitSql","initialization()",se);         
         }
         throw se;
      }
   }

    public DatabaseConnectionInfoInterface getDatabaseConnectionInfoInterface()
    {
        return databaseConnectionInfoInterface;
    }
}
