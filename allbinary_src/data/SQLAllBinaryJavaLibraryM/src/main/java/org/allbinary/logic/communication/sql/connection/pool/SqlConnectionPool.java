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
package org.allbinary.logic.communication.sql.connection.pool;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Vector;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class SqlConnectionPool
{
    private static final SqlConnectionPool instance = new SqlConnectionPool();
    
    public static SqlConnectionPool getInstance() {
        return instance;
    }

    private HashMap connectionHashMap = null;

    private final String FIRST_NEW_CONNECTION_CREATED = "First New Connection Created: ";
    private final String FIRST = "First ";
    private final String NEW_CONNECTION_FOR = "New Connection For ";
    private final String CREATED = " Created";
    private final String NUMBER_OF_SQL_CONNECTIONS_FOR = "Number Of Sql Connections for: ";
    private final String NUMBER_OF_SQL_CONNECTION_VECTORS = "Number Of Sql Connection Vectors: ";
    
    private final String IS = " is ";
    private final String CONNECTION_ALLREADY_CLOSED = "Connection AllReady Closed";
    
    private final String METHOD_GET = "get()";
    private final String METHOD_ADD = "add()";
    
    private SqlConnectionPool()
    {
    }

    /*
    public synchronized Connection get(String url, String userid, String password) throws SQLException
    {
    }
     */
    public synchronized Connection get(String url) throws SQLException
    {
        if (this.connectionHashMap == null)
        {
            this.connectionHashMap = new HashMap();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
            {
                LogUtil.put(LogFactory.getInstance(FIRST_NEW_CONNECTION_CREATED + url, this, METHOD_GET));
            }

            return DriverManager.getConnection(url);
        } else
        {
            Vector connectionVector =
                (Vector) this.connectionHashMap.get(url);

            if (connectionVector == null)
            {
                connectionVector = new Vector();

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(FIRST);
                    stringBuffer.append(NEW_CONNECTION_FOR);
                    stringBuffer.append(url);
                    stringBuffer.append(CREATED);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_GET));
                }

                return DriverManager.getConnection(url);
            } else if (connectionVector.size() == 0)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
                {
                    LogUtil.put(LogFactory.getInstance(new StringBuilder().append(NEW_CONNECTION_FOR).append(url).append(CREATED).toString(), this, METHOD_GET));
                }
                return DriverManager.getConnection(url);
            } else
            {
                StringBuffer stringBuffer = new StringBuffer();

                Iterator iter = connectionVector.iterator();
                while (iter.hasNext())
                {
                    Connection sqlConnection = (Connection) iter.next();
                    if (!sqlConnection.isClosed())
                    {
                        connectionVector.remove(sqlConnection);
                        this.connectionHashMap.put(url, connectionVector);

                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append(NUMBER_OF_SQL_CONNECTIONS_FOR);
                            stringBuffer.append(url);
                            stringBuffer.append(IS);
                            stringBuffer.append(connectionVector.size());

                            LogUtil.put(LogFactory.getInstance(NUMBER_OF_SQL_CONNECTION_VECTORS
                                + this.connectionHashMap.size(), this, METHOD_GET));
                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_GET));
                        }
                        return (Connection) sqlConnection;
                    }
                }
            }
        }

        //Should never happen
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(NEW_CONNECTION_FOR);
            stringBuffer.append(url);
            stringBuffer.append(CREATED);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_GET));
        }

        return DriverManager.getConnection(url);
    }

    public synchronized void add(String url, Connection sqlConnection)
        throws SQLException
    {
        //sqlConnection.close();
        //sqlConnection = null;

        //Connection could be closed
        if (!sqlConnection.isClosed())
        {
            Vector connectionVector;

            if (this.connectionHashMap == null)
            {
                this.connectionHashMap = new HashMap();
                connectionVector = new Vector();
                connectionVector.add(sqlConnection);
            } else
            {
                connectionVector =
                    (Vector) this.connectionHashMap.get(url);

                if (connectionVector == null)
                {
                    connectionVector = new Vector();
                }

                connectionVector.add(sqlConnection);
                this.connectionHashMap.put(url, connectionVector);
            }

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
            {

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append(NUMBER_OF_SQL_CONNECTIONS_FOR);
                stringBuffer.append(url);
                stringBuffer.append(IS);
                stringBuffer.append(connectionVector.size());


                LogUtil.put(LogFactory.getInstance(NUMBER_OF_SQL_CONNECTION_VECTORS
                    + this.connectionHashMap.size(), this, METHOD_ADD));
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_ADD));
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
        {
            LogUtil.put(LogFactory.getInstance(CONNECTION_ALLREADY_CLOSED, this, METHOD_ADD));
        }
    }

    /*
    public synchronized void add(String url, String userid, String password, Connection sqlConnection) throws SQLException
    {
    }
     */
    /*
    public void done()
    {
    if(conn != null)
    {
    try
    {
    conn.close();
    }
    catch(SQLException se)
    {
    }
    }
    }
     */
}
