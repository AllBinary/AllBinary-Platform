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

import abcs.business.init.db.DbConnectionInfo;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.communication.log.LogFactory;

import abcs.logic.communication.log.LogUtil;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AbSqlBean extends AbSqlRow
{
    public AbSqlBean(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public String getField(String key, String value, String requestedField)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT ");
        stringBuffer.append(requestedField);
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");
        stringBuffer.append(key);
        stringBuffer.append(" = \"");
        stringBuffer.append(value);
        stringBuffer.append("\"");

        String sqlStatement = stringBuffer.toString();

        try
        {
            String field = "";
            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {

                field = rset.getString(requestedField);
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("SQL Statement: ");
                    stringBuffer.append(sqlStatement);
                    stringBuffer.append("\nField Value: ");
                    stringBuffer.append(field);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getField"));
                }

                return field;
            }
            return null;
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getField", e));
            }
            return "Failed\nSQL Statement: " + sqlStatement;
        }
    }

    public String getField(HashMap keysAndValues, String requestedField)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT ");
        stringBuffer.append(requestedField);
        stringBuffer.append(" FROM ");
        stringBuffer.append(this.getTableName());
        stringBuffer.append(" WHERE ");
        
        try
        {
            String field = "";

            Set set = keysAndValues.keySet();
            Iterator iter = set.iterator();

            while (iter.hasNext())
            {
                String key = (String) iter.next();
                String value = new String((String) keysAndValues.get(key));

                stringBuffer.append(key);
                stringBuffer.append(" = \"");
                stringBuffer.append(value);
                stringBuffer.append("\"");

                if (iter.hasNext())
                {
                    stringBuffer.append(" AND ");
                }
            }

            String sqlStatement = stringBuffer.toString();

            ResultSet rset = executeSQLStatement(sqlStatement);
            while (rset.next())
            {
                field = rset.getString(requestedField);
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    stringBuffer.delete(0, stringBuffer.length());

                    stringBuffer.append("SQL Statement: ");
                    stringBuffer.append(sqlStatement);
                    stringBuffer.append("\nField Value: ");
                    stringBuffer.append(field);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "getField"));
                }
                return field;
            }
            return null;
        } catch (Exception e)
        {
            String sqlStatement = stringBuffer.toString();

            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "getField", e));
            }
            return "Failed\nSQL Statement: " + sqlStatement;
        }
    }

    //empty hashmap will cause problems
    public String isSubsetOfEntry(String key, String value, HashMap columnsAndValues)
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("SELECT ");

        try
        {
            Iterator iter = columnsAndValues.keySet().iterator();

            stringBuffer.append(CommonSeps.getInstance().SPACE);
            stringBuffer.append(key);

            while (iter.hasNext())
            {
                stringBuffer.append(CommonSeps.getInstance().SPACE);
                stringBuffer.append(iter.next());
            }

            stringBuffer.append(" FROM ");
            stringBuffer.append(this.getTableName());
            stringBuffer.append(" WHERE ");
            stringBuffer.append(key);
            stringBuffer.append(" = \"");
            stringBuffer.append(value);
            stringBuffer.append("\"");

            String sqlStatement = stringBuffer.toString();

            ResultSet rset = executeSQLStatement(sqlStatement);

            while (rset.next())
            {
                Iterator iter2 = columnsAndValues.keySet().iterator();
                while (iter2.hasNext())
                {
                    String columnName = iter2.next().toString();
                    String field = rset.getObject(columnName).toString();
                    if (field.compareTo((String) columnsAndValues.get(columnName)) != 0)
                    {
                        return allbinary.globals.GLOBALS.NOTASUBSET;
                    }
                }

            }

            if (rset.getRow() != 1)
            {
                return allbinary.globals.GLOBALS.NOTASUBSET;
            } else
            {
                return allbinary.globals.GLOBALS.ISASUBSET;
            }
        } catch (Exception e)
        {
            String sqlStatement = stringBuffer.toString();
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed\nSQL Statement: " + sqlStatement, this, "isSubsetOfEntry", e));
            }
            return "Failed\nSQL Statement: " + sqlStatement;
        }
    }
}
