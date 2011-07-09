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
package allbinary.logic.communication.sql.connection.pool;

import abcs.logic.communication.log.LogFactory;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Vector;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import abcs.logic.communication.log.LogUtil;

public class SqlConnectionPool
{
    private static HashMap connectionHashMap = null;

    private static final SqlConnectionPool instance = new SqlConnectionPool();

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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
            {
                LogUtil.put(LogFactory.getInstance("First New Connection Created: " + url, instance, "get"));
            }

            return DriverManager.getConnection(url);
        } else
        {
            Vector connectionVector =
                (Vector) SqlConnectionPool.connectionHashMap.get(url);

            if (connectionVector == null)
            {
                connectionVector = new Vector();

                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append("First New Connection For ");
                    stringBuffer.append(url);
                    stringBuffer.append(" Created");

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "get"));
                }

                return DriverManager.getConnection(url);
            } else if (connectionVector.size() == 0)
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                    abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
                {
                    LogUtil.put(LogFactory.getInstance("New Connection For " + url + " Created", instance, "get"));
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

                        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                            abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
                        {
                            stringBuffer.delete(0, stringBuffer.length());

                            stringBuffer.append("Number Of Sql Connections for: ");
                            stringBuffer.append(url);
                            stringBuffer.append(" is ");
                            stringBuffer.append(connectionVector.size());

                            LogUtil.put(LogFactory.getInstance("Number Of Sql Connection Vectors: "
                                + SqlConnectionPool.connectionHashMap.size(), instance, "get"));
                            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "get"));
                        }
                        return (Connection) sqlConnection;
                    }
                }
            }
        }

        //Should never happen
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("New Connection For ");
            stringBuffer.append(url);
            stringBuffer.append(" Created");

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "get"));
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

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
            {

                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Number Of Sql Connections for: ");
                stringBuffer.append(url);
                stringBuffer.append(" is ");
                stringBuffer.append(connectionVector.size());


                LogUtil.put(LogFactory.getInstance("Number Of Sql Connection Vectors: "
                    + SqlConnectionPool.connectionHashMap.size(), instance, "add"));
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), instance, "add"));
            }
        }

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGPOOL))
        {
            LogUtil.put(LogFactory.getInstance("Connection AllReady Closed", instance, "add"));
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
