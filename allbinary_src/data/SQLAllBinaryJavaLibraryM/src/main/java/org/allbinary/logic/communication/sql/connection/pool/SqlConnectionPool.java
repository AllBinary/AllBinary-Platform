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
    private static HashMap connectionHashMap = null;

    private static final SqlConnectionPool instance = new SqlConnectionPool();

    private static final String FIRST_NEW_CONNECTION_CREATED = "First New Connection Created: ";
    private static final String FIRST = "First ";
    private static final String NEW_CONNECTION_FOR = "New Connection For ";
    private static final String CREATED = " Created";
    private static final String NUMBER_OF_SQL_CONNECTIONS_FOR = "Number Of Sql Connections for: ";
    private static final String NUMBER_OF_SQL_CONNECTION_VECTORS = "Number Of Sql Connection Vectors: ";
    
    private static final String IS = " is ";
    private static final String CONNECTION_ALLREADY_CLOSED = "Connection AllReady Closed";
    
    private static final String METHOD_GET = "get()";
    private static final String METHOD_ADD = "add()";
    
    private SqlConnectionPool()
    {
    }

    /*
    public static synchronized Connection get(String url, String userid, String password) throws SQLException
    {
    }
     */
    public static synchronized Connection get(String url) throws SQLException
    {
        if (SqlConnectionPool.connectionHashMap == null)
        {
            SqlConnectionPool.connectionHashMap = new HashMap();

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
            {
                LogUtil.put(LogFactory.getInstance(FIRST_NEW_CONNECTION_CREATED + url, instance, METHOD_GET));
            }

            return DriverManager.getConnection(url);
        } else
        {
            Vector connectionVector =
                (Vector) SqlConnectionPool.connectionHashMap.get(url);

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

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, METHOD_GET));
                }

                return DriverManager.getConnection(url);
            } else if (connectionVector.size() == 0)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
                {
                    LogUtil.put(LogFactory.getInstance(new StringBuilder().append(NEW_CONNECTION_FOR).append(url).append(CREATED).toString(), instance, METHOD_GET));
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
                        SqlConnectionPool.connectionHashMap.put(url, connectionVector);

                        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append(NUMBER_OF_SQL_CONNECTIONS_FOR);
                            stringBuffer.append(url);
                            stringBuffer.append(IS);
                            stringBuffer.append(connectionVector.size());

                            LogUtil.put(LogFactory.getInstance(NUMBER_OF_SQL_CONNECTION_VECTORS
                                + SqlConnectionPool.connectionHashMap.size(), instance, METHOD_GET));
                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, METHOD_GET));
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

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, METHOD_GET));
        }

        return DriverManager.getConnection(url);
    }

    public static synchronized void add(String url, Connection sqlConnection)
        throws SQLException
    {
        //sqlConnection.close();
        //sqlConnection = null;

        //Connection could be closed
        if (!sqlConnection.isClosed())
        {
            Vector connectionVector;

            if (SqlConnectionPool.connectionHashMap == null)
            {
                SqlConnectionPool.connectionHashMap = new HashMap();
                connectionVector = new Vector();
                connectionVector.add(sqlConnection);
            } else
            {
                connectionVector =
                    (Vector) SqlConnectionPool.connectionHashMap.get(url);

                if (connectionVector == null)
                {
                    connectionVector = new Vector();
                }

                connectionVector.add(sqlConnection);
                SqlConnectionPool.connectionHashMap.put(url, connectionVector);
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
                    + SqlConnectionPool.connectionHashMap.size(), instance, METHOD_ADD));
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, METHOD_ADD));
            }
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGPOOL))
        {
            LogUtil.put(LogFactory.getInstance(CONNECTION_ALLREADY_CLOSED, instance, METHOD_ADD));
        }
    }

    /*
    public static synchronized void add(String url, String userid, String password, Connection sqlConnection) throws SQLException
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
