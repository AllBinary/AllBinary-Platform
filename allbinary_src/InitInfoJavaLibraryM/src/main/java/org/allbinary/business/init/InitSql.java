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
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.communication.sql.SqlStrings;
import org.allbinary.logic.communication.sql.SqlTypeStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

public class InitSql
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final StringUtil stringUtil = StringUtil.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final SqlTypeStrings sqlTypeStrings = SqlTypeStrings.getInstance();
    protected final SqlStrings sqlStrings = SqlStrings.getInstance();

    protected final String INSERT = "insert";
    protected final String UPDATE = "update";
    
   private DbConnectionInfo databaseConnectionInfoInterface;

   private String userid;
   private String password;
   
   private String tableName;
   
   private Connection conn;
   
   private boolean useridAndPassword;
   
   protected final String SUCCESS_SQL_STATEMENT = "Success\nSQL Statement: ";
   protected final String FAILED_SQL_STATEMENT = "Failed\nSQL Statement: ";
   
   private final String INIT_SQL = "InitSql";
   
   protected final CommonStrings commonStrings = CommonStrings.getInstance();
   
   private final String EQUAL_QUOTE = "=\"";
   //private final String ESCAPE_QUOTES = "\\\"";

   private final String METHOD_GET_ROW = "getRow()";
   private final String METHOD_UPDATE_WHERE = "updateWhere()";

   private final String ROW_VALUE_LABEL = "Row Value: ";
   private final String NO_RESULTS_IN_RESULT_SET = "No Results in ResultSet";

   private final String INSERT_END = "')";

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
            final StringMaker stringBuffer = new StringMaker();
            stringBuffer.append("Creating Table: ");
            stringBuffer.append(tableData);
            
            PreLogUtil.put(stringBuffer.toString(), INIT_SQL, "createTable()");
            
          
         this.executeSQLStatement(tableData);
         return true;         
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {
             PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "createTable()", e);
         }
         return false;
      }
   }

    public boolean dropTable()
    {
        String sqlStatement = sqlStrings.DROP_TABLE + tableName;
        try
        {
            this.executeSQLStatement(sqlStatement);
            return true;
        }catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "dropTables()", e);
            }
            return false;
        }
    }
      
    public HashMap getRow(HashMap keysAndValues)
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.SELECT_ALL_FROM);
        stringBuffer.append(this.tableName);
        stringBuffer.append(sqlStrings.WHERE);

        try
        {
            HashMap result = null;
            Set set = keysAndValues.keySet();
            Object[] keyArray = set.toArray();
            int size = keyArray.length;
            for (int i = 0; i < size; i++)
            {
                String key = (String) keyArray[i];
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(sqlStrings.EQUAL_QUOTE);

                stringBuffer.append(this.getValue(value));
                stringBuffer.append(sqlStrings.CLOSE_QUOTE);

                if (i < size - 1)
                {
                    stringBuffer.append(sqlStrings.AND);
                }
            }

            String sqlStatement = stringBuffer.toString();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                PreLogUtil.put(sqlStrings.SQL_STATEMENT_LABEL + sqlStatement, this.INIT_SQL, this.METHOD_GET_ROW);
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

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    PreLogUtil.put(ROW_VALUE_LABEL + result.toString(), this.INIT_SQL, this.METHOD_GET_ROW);
                }
                return result;
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                PreLogUtil.put(NO_RESULTS_IN_RESULT_SET, this.INIT_SQL, this.METHOD_GET_ROW);
            }

            return null;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                PreLogUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this.INIT_SQL, this.METHOD_GET_ROW, e);
            }
            return null;
        }
    }

    public synchronized void updateWhere(String key, String value, HashMap updatedKeyValuePairs)
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.UPDATE);
        stringBuffer.append(this.tableName);
        stringBuffer.append(this.sqlStrings.SET);

        try
        {
            Object[] columnArray = updatedKeyValuePairs.keySet().toArray();
            int size = columnArray.length;
            for (int i = 0; i < size; i++)
            {
                String columnName = columnArray[i].toString();
                stringBuffer.append(this.commonSeps.SPACE);
                stringBuffer.append(columnName);
                stringBuffer.append(EQUAL_QUOTE);
                String columnValue = (String) updatedKeyValuePairs.get(columnName);

                if (columnValue == null)
                {
                    columnValue = this.stringUtil.EMPTY_STRING;
                } else
                {
                    //columnValue = new Replace(sqlStrings.CLOSE_QUOTE, ESCAPE_QUOTES).all(columnValue);
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

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                PreLogUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this.INIT_SQL, METHOD_UPDATE_WHERE);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                PreLogUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer, this.INIT_SQL, METHOD_UPDATE_WHERE, e);
            }
        }
    }
      
    public void insert(Vector values)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.INSERT_INTO);
        stringBuffer.append(this.tableName);
        stringBuffer.append(this.sqlStrings.VALUES);

        try
        {
            int size = values.size();
            for (int i = 0; i < size - 1; i++)
            {
                String value = this.getValue((String) values.get(i));
                //value = new Replace(this.sqlStrings.ESCAPE, this.sqlStrings.DOUBLE_ESCAPE).all(value);

                stringBuffer.append(value);
                stringBuffer.append(this.sqlStrings.SINGLE_QUOTE_COMMA_SEP);
            }

            String value = this.getValue((String) values.lastElement());
            //value = new Replace(this.sqlStrings.ESCAPE, this.sqlStrings.DOUBLE_ESCAPE).all(value);

            stringBuffer.append(value);
            stringBuffer.append(INSERT_END);

            String sqlStatement = stringBuffer.toString();
            this.executeSQLStatement(sqlStatement);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                PreLogUtil.put(this.SUCCESS_SQL_STATEMENT + sqlStatement, this.INIT_SQL, INSERT);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                PreLogUtil.put(this.FAILED_SQL_STATEMENT + stringBuffer.toString(), this.INIT_SQL, INSERT, e);
            }
        }
    }

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
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {         
            PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "executeSQLStatement()", e);
         }
         throw e;
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {         
            PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "executeSQLStatement()", e);
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
               this.databaseConnectionInfoInterface.getUrl(), userid, password);
         }
         else
         {
            conn = DriverManager.getConnection(
               this.databaseConnectionInfoInterface.getUrl());
         }
      }
      catch(SQLException se)
      {
         PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "createConnection()", se);
         throw se;
      }
   }
   
   private void initialize() throws Exception
   {
      try
      {
         try
         {            
            final String jdbcDriver = this.databaseConnectionInfoInterface.getJdbcDriver();
            PreLogUtil.put(new StringBuilder().append("Loading DbConnnectionInfo: ")
                    .append(this.databaseConnectionInfoInterface.getClass().getName())
                    .append(" Driver: ").append(jdbcDriver).toString(), INIT_SQL, "initialize()");
            Class.forName(jdbcDriver).newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
         }
         catch(Exception e)
         {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {                     
               PreLogUtil.put("LoadDriver Failed: " + 
                  this.databaseConnectionInfoInterface.getJdbcDriver(), INIT_SQL, "initialize()", e);
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
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
         {                  
            PreLogUtil.put(this.commonStrings.EXCEPTION, INIT_SQL, "initialize()", se);
         }
         throw se;
      }
   }

    public DatabaseConnectionInfoInterface getDatabaseConnectionInfoInterface()
    {
        return databaseConnectionInfoInterface;
    }
}
