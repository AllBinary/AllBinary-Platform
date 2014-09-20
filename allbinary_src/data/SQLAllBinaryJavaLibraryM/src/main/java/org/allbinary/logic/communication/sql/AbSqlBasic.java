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

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.communication.sql.connection.pool.SqlConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbSqlBasic
{
    private DbConnectionInfo databaseConnectionInfoInterface;
    private int connectAttemptCounter;
    //private boolean isInitialized = false;
    //protected String userid;
    //protected String password;
    protected Connection conn;
    //protected boolean useridAndPassword;

    protected final SqlStrings sqlStrings = SqlStrings.getInstance();
    
    public AbSqlBasic(DbConnectionInfo databaseConnectionInfoInterface)
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
    public ResultSet executeSQLStatement(String statement) throws SQLException
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
            SqlConnectionPool.add(this.getDatabaseConnectionInfoInterface().getUrl(), tempConnection);

            return rset;
        } catch (SQLException e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("SQL Statement Failed\nSQL Statement: " + statement, this, "executedSQLStatement", e));
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
         */
    }

    public String create(String databaseName)
    {
        try
        {
            this.executeSQLStatement(sqlStrings.CREATE_DATABASE + databaseName);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Database Created: " + databaseName, this, "create"));
            }
            return databaseName + sqlStrings.CREATE_RETURN;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Database Creation Failed: " + databaseName, this, "create", e));
            }
            return "Failed to Create " + databaseName;
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
            this.conn = SqlConnectionPool.get(
                    this.getDatabaseConnectionInfoInterface().getUrl());
            //}

        } catch (SQLException se)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("SQL Connection Retrying", this, "createConnection()", se));
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
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
                {
                    LogUtil.put(LogFactory.getInstance("SQL Connection Retry", this, "createConnection()", se));
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
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Load JDBC Driver Failed: " + this.getDatabaseConnectionInfoInterface().getJdbcDriver(), this, "initialize", e));
                        //LogUtil.put("Load mySQL Driver Failed: com.mysql.jdbc.Driver",this,"initialize",e);
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("SQL Connection Failed", this, "initialize", se));
            }
        }
    }

    public DatabaseConnectionInfoInterface getDatabaseConnectionInfoInterface()
    {
        return databaseConnectionInfoInterface;
    }

    public void setDatabaseConnectionInfoInterface(DbConnectionInfo databaseConnectionInfoInterface)
    {
        this.databaseConnectionInfoInterface = databaseConnectionInfoInterface;
    }
}
