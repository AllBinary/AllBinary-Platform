/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.allbinary.logic.communication.sql;

import java.net.InetAddress;

import org.allbinary.business.init.db.DatabaseConnectionInfoInterface;
import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class AbDatabaseManagement extends AbSqlBean
{
    protected CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected final StringBuffer sqlCommandLog = new StringBuffer();

    private final String GRANT_ALL = "GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER ON ";
    private final String DOT_STAR = ".*";
    private final String TO = " TO ";
    private final String AT_LOCALHOST = "@localhost";
    private final String IDENTIFIED_BY = " IDENTIFIED BY '";
    private final String WITH_GRANT_OPTION = "' WITH GRANT OPTION";

    private final String FAILED = "Failed";
    private final String GET_HOST_NAME = "getHostName()";
    
    private final String METHOD_ADD_USER = "addUser()";
    protected final String METHOD_ADD_USERS = "addUsers()";
    
    private final String METHOD_ADD_DB = "addDb()";
    protected final String METHOD_ADD_DATABASES = "addDatabases()";
    protected final String METHOD_ADD_TABLES = "addTables()";
    
    protected final String UNABLE_TO_CREATE_USER = "Unable to Create User: ";
    protected final String UNABLE_TO_CREATE_DATABASE = "Unable to Create Database: ";
    protected final String UNABLE_TO_CREATE_TABLES = "Unable to Create Tables";

    protected final String ADD_TABLES_RESULTS_LABEL = "Add Table Results: ";
    
    public AbDatabaseManagement(
            DatabaseConnectionInfoInterface databaseConnectionInfoInterface)
    {
        super((DbConnectionInfo) databaseConnectionInfoInterface);
    }

    public AbDatabaseManagement(DbConnectionInfo connectionInfo)
    {
        super(connectionInfo);
    }

    private String getHostName()
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        }catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.FAILED, this, GET_HOST_NAME, e));
            return null;
        }
    }

    protected boolean addDbUser(String hostName, String db, String userName, String password)
            throws Exception
    {
            final StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(this.GRANT_ALL);
            stringBuffer.append(db);
            stringBuffer.append(this.DOT_STAR);
            stringBuffer.append(this.TO);
            stringBuffer.append(userName);
            stringBuffer.append(hostName);
            stringBuffer.append(this.IDENTIFIED_BY);
            stringBuffer.append(password);
            stringBuffer.append(this.WITH_GRANT_OPTION);

            String sqlStatement = stringBuffer.toString();

            this.sqlCommandLog.append(sqlStatement);
            this.sqlCommandLog.append(this.commonSeps.NEW_LINE);

            super.executeSQLStatement(sqlStatement);
            
            return true;
    }
    
    protected boolean addDbUser(String db, String userName, String password)
    {
        try
        {
            this.addDbUser(this.AT_LOCALHOST, db, userName, password);

            String hostName = this.getHostName();
            if(hostName != null)
            {
                this.addDbUser(hostName, db, userName, password);
            }

            return true;
        }catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.UNABLE_TO_CREATE_USER + userName, this, this.METHOD_ADD_USER, e));
            return false;
        }
    }

    protected Boolean addDb(String db)
    {
        try
        {
            String sqlStatement = this.sqlStrings.CREATE_DATABASE + db;

            this.sqlCommandLog.append(sqlStatement);
            this.sqlCommandLog.append(this.commonSeps.NEW_LINE);

            super.executeSQLStatement(sqlStatement);

            return Boolean.TRUE;
        }catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(UNABLE_TO_CREATE_DATABASE + db, this, this.METHOD_ADD_DB, e));
            return Boolean.FALSE;
        }
    }

}
