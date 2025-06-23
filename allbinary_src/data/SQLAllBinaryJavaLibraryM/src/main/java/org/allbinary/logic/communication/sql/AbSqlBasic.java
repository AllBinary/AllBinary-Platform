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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.connection.pool.SqlConnectionPool;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;

public class AbSqlBasic
{
    private final SqlConnectionPool sqlConnectionPool = SqlConnectionPool.getInstance();
        
    private DbConnectionInfo databaseConnectionInfoInterface;
    private int connectAttemptCounter;
    //private boolean isInitialized = false;
    //protected String userid;
    //protected String password;
    protected Connection conn;
    //protected boolean useridAndPassword;

    protected final StringUtil stringUtil = StringUtil.getInstance();
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final SqlTypeStrings sqlTypeStrings = SqlTypeStrings.getInstance();
    protected final SqlStrings sqlStrings = SqlStrings.getInstance();

    protected final String INSERT = "insert";
    protected final String UPDATE = "update";
    protected final String DELETE = "delete";
        
    protected final String FAILED_SQL_STATEMENT = "Failed\nSQL Statement: ";
    protected final String SUCCESS_SQL_STATEMENT = "Success\nSQL Statement: ";

    private final String METHOD_EXECUTED_SQL_STATEMENT = "executedSQLStatement";
    private final String METHOD_CREATE = "create";
    
    private final String DATABASE_CREATED_LABEL = "Database Created: ";
    private final String DATABASE_CREATION_FAILED_LABEL = "Database Creation Failed: ";
    
    private final String SQL_CONNECTION_FAILED = "SQL Connection Failed";
    private final String SQL_CONNECTION_RETRYING = "SQL Connection Retrying";
    private final String SQL_CONNECTION_RETRY = "SQL Connection Retry";
    private final String METHOD_CREATE_CONNECTION = "createConnection()";
    private final String METHOD_INITIALIZE = "initialize";
    
    private final String LOAD_JDBC_DRIVER_FAILED_LABEL = "Load JDBC Driver Failed: ";
    
    public AbSqlBasic(final DbConnectionInfo databaseConnectionInfoInterface)
    {
        this.databaseConnectionInfoInterface = databaseConnectionInfoInterface;
        this.connectAttemptCounter = 0;
        this.conn = null;

        //userid = null;
        ///password = null;
        //useridAndPassword=false;
    }

    /*
    public void setUserid(String userid)
    {
    this.userid = userid;
    }

    public String getUserid()
    {
    return userid;
    }

    public void setPassword(String password)
    {
    this.password = password;
    }

    public String getPassword()
    {
    return password;
    }
     */
    public ResultSet executeSQLStatement(final String statement) throws SQLException
    {
        try
        {
            //if(conn == null)
            //{
            initialize();
            //}

            Statement stmt = conn.createStatement();
            //ResultSet rset = stmt.executeQuery(statement);
            //TWB- Changes For GAE JIQL
            stmt.execute(statement);
            ResultSet rset = stmt.getResultSet();

            stmt.close();

            Connection tempConnection = this.conn;
            this.conn = null;
            sqlConnectionPool.add(this.getDatabaseConnectionInfoInterface().getUrl(), tempConnection);

            return rset;
        } catch (SQLException e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(FAILED_SQL_STATEMENT + statement, this, METHOD_EXECUTED_SQL_STATEMENT, e));
            }

            this.conn.close();
            this.conn = null;

            throw e;
        }
        /*
        finally
        {
        this.conn.close();
        this.conn = null;
        }
        /*
        finally
        {
        this.conn.close();
        this.conn = null;
        }
         */
    }

    public String create(final String databaseName)
    {
        try
        {
            this.executeSQLStatement(sqlStrings.CREATE_DATABASE + databaseName);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.DATABASE_CREATED_LABEL + databaseName, this, METHOD_CREATE));
            }
            return databaseName + sqlStrings.CREATE_RETURN;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(DATABASE_CREATION_FAILED_LABEL + databaseName, this, this.METHOD_CREATE, e));
            }
            return DATABASE_CREATION_FAILED_LABEL + databaseName;
        }
    }

    private void createConnection()
    {
        try
        {
            /*
            if(useridAndPassword==true)
            {
            this.conn = SqlConnectionPool.get(this.url, this.userid, this.password);
            }
            else
            {*/
            this.conn = sqlConnectionPool.get(this.getDatabaseConnectionInfoInterface().getUrl());
            //}

        } catch (SQLException se)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(SQL_CONNECTION_RETRYING, this, METHOD_CREATE_CONNECTION, se));
            }
            try
            {
                //should restart mysql here if needed
                //wait and hope mysql catches up
                Thread.currentThread().sleep(2000);
                //give up after 20tries
                if (connectAttemptCounter < 10)
                {
                    connectAttemptCounter++;
                    this.initialize();
                }
            } catch (Exception e)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
                {
                    LogUtil.put(LogFactory.getInstance(SQL_CONNECTION_RETRY + se.getMessage(), this, this.METHOD_CREATE_CONNECTION, e));
                }

            }
        }
    }

    private void initialize()
    {
        try
        {
            //if(!this.isInitialized)
            {

                try
                {
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();

                    Class jdbcDriverClass =
                        loader.loadClass(
                        this.getDatabaseConnectionInfoInterface().getJdbcDriver());
                    //private Driver driver;
                    //driver = (Driver)
                    jdbcDriverClass.newInstance();

                    //Class.forName(JdbcInitInfo.getDriver()).newInstance();
                    //Class.forName("com.mysql.jdbc.Driver").newInstance();
                    //this.isInitialized = true;
                } catch (Exception e)
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
                    {
                        LogUtil.put(LogFactory.getInstance(LOAD_JDBC_DRIVER_FAILED_LABEL + this.getDatabaseConnectionInfoInterface().getJdbcDriver(), this, METHOD_INITIALIZE, e));
                        //LogUtil.put(LogFactory.getInstance("Load mySQL Driver Failed: com.mysql.jdbc.Driver",this,"initialize",e);
                    }
                }

            }
            /*
            if(this.userid == null && this.password == null)
            {
            this.useridAndPassword=true;
            }
             */

            this.createConnection();

        } catch (Exception se)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance(SQL_CONNECTION_FAILED, this, this.METHOD_INITIALIZE, se));
            }
        }
    }

    public DatabaseConnectionInfoInterface getDatabaseConnectionInfoInterface()
    {
        return databaseConnectionInfoInterface;
    }

    public void setDatabaseConnectionInfoInterface(final DbConnectionInfo databaseConnectionInfoInterface)
    {
        this.databaseConnectionInfoInterface = databaseConnectionInfoInterface;
    }
}
