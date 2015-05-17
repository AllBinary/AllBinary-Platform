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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.basic.io.AbFileOutputStream;
import org.allbinary.logic.basic.io.BufferedLineReader;
import org.allbinary.logic.basic.io.StreamUtil;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.FileUtil;
import org.allbinary.logic.basic.io.file.directory.Directory;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.basic.path.AbPathData;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.PATH_GLOBALS;
import java.io.OutputStream;

public class AbSqlTable extends AbSqlBasic
{

    private String tableName;
    private static String EXTENSION = ".adb";

    public AbSqlTable(DbConnectionInfo databaseConnectionInfoInterface)
    {
        super(databaseConnectionInfoInterface);
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public synchronized String createTable(String data)
    {
        try
        {
            this.executeSQLStatement(data);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Table Creation Successful: " + this.tableName, this, "createTable"));
            }
            return tableName + sqlStrings.CREATE_RETURN;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Table Creation Failed: " + this.tableName, this, "createTable", e));
            }
            return "Failed to Create " + tableName + " table.";
        }
    }

    public synchronized String dropTable()
    {
        String sqlStatement = sqlStrings.DROP_TABLE + tableName;
        try
        {
            this.executeSQLStatement(sqlStatement);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Table Drop Successful\nSQL Statement: " + sqlStatement, this, "dropTable"));
            }
            return new String(tableName + " Dropped Successfully");
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Table Drop Failed\nSQL Statement: " + sqlStatement, this, "dropTable", e));
            }
            return "Failed to Drop " + tableName + " table.";
        }
    }

    private synchronized OutputStream getOutputStream(String backupPath, String tableName)
    {
        try
        {
            String fileName = tableName + EXTENSION;

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Saving: " + tableName, this, "createFile()"));
            }

            AbPath backupFilePath = new AbPath(backupPath, fileName);

            AbFile backupFile = new AbFile(backupFilePath);

            if (backupFile.exists())
            {
                backupFile(backupFilePath, backupPath, tableName);
                backupFile.delete();
            }

            backupFile.createNewFile();

            OutputStream outputStream = new AbFileOutputStream(backupFile);

            return outputStream;

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Create File", this, "createFile()", e));
            }
            return null;
        }
    }

    private synchronized boolean backupFile(AbPath path, String backupPath, String tableName)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            Long timeLong = new Long(calendar.getTimeInMillis());
            String time = new String(timeLong.toString());

            StringBuffer stringBuffer = new StringBuffer();

            String fileName = tableName + EXTENSION;

            stringBuffer.append(backupPath);
            stringBuffer.append(AbPathData.getInstance().SEPARATOR);
            stringBuffer.append(time);

            AbPath backupAbPath = new AbPath(stringBuffer.toString());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append("Saving Backup: Path: ");
                stringBuffer.append(backupAbPath.toFileSystemString());
                stringBuffer.append(" File: ");
                stringBuffer.append(fileName);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "backupFile()"));
            }

            Directory.create(backupAbPath);
            AbFile backupFileBak = new AbFile(backupAbPath.toFileSystemString());

            backupFileBak.createNewFile();

            FileUtil.getInstance().copy(path, backupAbPath);

            return true;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Backup File", this, "backupFile()", e));
            }
            return false;
        }
    }

    private String convertNewLines(String value)
    {
        //Replace replace = new Replace("\n","\\n");
        StringBuffer stringBuffer = new StringBuffer();

        char specialCharArray[] =
        {
            '\n', '\f', '\r'
        };

        int index = 0;
        int lastIndex = 0;
        while (index < value.length())
        {
            index = value.indexOf(specialCharArray[0], lastIndex);
            if (index != -1)
            {
                String nextLine = value.substring(lastIndex, index - 1);
                stringBuffer.append(nextLine + "\\n");
                lastIndex = index + 1;
            } else
            {
                break;
            }
        }

        //stringBuffer.append(replace.all(rset.getString(i)));

        //add the last line since if it did not end with a new line
        if (lastIndex < value.length())
        {
            stringBuffer.append(value.substring(lastIndex, value.length()));
        }
        return stringBuffer.toString();
    }

    //This is not good for large data
    public synchronized String backupTable()
    {
        try
        {
            int count = 0;

            String sqlStatement = "SELECT * FROM " + tableName;
            String path = org.allbinary.globals.URLGLOBALS.getMainPath()
                + PATH_GLOBALS.getInstance().BACKUP_PATH;

            if (!Directory.create(new AbPath(path)))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Error Creating: " + path, this, "backupTable()"));
                }
            }

            ResultSet rset = this.executeSQLStatement(sqlStatement);

            ResultSetMetaData rsmd = rset.getMetaData();

            int colNum = rsmd.getColumnCount();

            final String QUERY_START = "INSERT INTO " + tableName + " VALUES ('";

            StringBuffer stringBuffer = new StringBuffer();

            //createFile(path, tableName, returnBuffer.toString());
            OutputStream outputStream = this.getOutputStream(path, tableName);

            while (rset.next())
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(QUERY_START);

                for (int i = 1; i < colNum; i++)
                {
                    //get rid of new lines

                    String value = rset.getString(i);

                    stringBuffer.append(this.convertNewLines(value));

                    stringBuffer.append("','");
                }
                stringBuffer.append(rset.getString(colNum));
                stringBuffer.append("')\n");

                String sqlStatementLine = stringBuffer.toString();

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                {
                    LogUtil.put(LogFactory.getInstance("Appending: " + sqlStatementLine, this, "backupTable()"));
                }

                outputStream.write(sqlStatementLine.getBytes());
            }

            StreamUtil.getInstance().close(outputStream);

            return "Table: " + this.tableName + " Backup Success";
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Backup Table Failed\nSQL Statement", this, "backupTable()", e));
            }
            return "Table: " + this.tableName + " Backup Failed";
        }
    }

    public synchronized String restoreTable(Portion portion)
    {
        try
        {
            String path = org.allbinary.globals.URLGLOBALS.getMainPath()
                + PATH_GLOBALS.getInstance().BACKUP_PATH;

            int current = portion.getCurrent().intValue();
            if (current == 0)
            {
                if (Directory.create(new AbPath(path)))
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
                    {
                        LogUtil.put(LogFactory.getInstance("Error Creating: " + path, this, "restoreTable()"));
                    }
                }
            }

            AbFile backupFile = new AbFile(path, tableName + EXTENSION);

            BufferedLineReader bufferedLineReader = new BufferedLineReader(backupFile);

            long size = bufferedLineReader.getSize();

            //Add one to round up so files will not be missed
            long section = size / portion.getTotal().intValue() + 1;

            long start = section * current;

            long end = start + section;

            if (end > size)
            {
                end = size;
            }

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(" Total: ");
            stringBuffer.append(size);
            stringBuffer.append(" Section: ");
            stringBuffer.append(start);
            stringBuffer.append(" - ");
            stringBuffer.append(end);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "restoreTable()"));
            }

            //If the readahead is less than 2 lines then this will only work some of the time
            bufferedLineReader.readUpToLines(start);

            String line = this.stringUtil.EMPTY_STRING;
            while (bufferedLineReader.getCurrent() < end && (line = bufferedLineReader.readLine()) != null)
            {
                if (line.length() > 1)
                {
                    executeSQLStatement(line);
                }
            }

            stringBuffer.append(" Table: ");
            stringBuffer.append(this.tableName);
            stringBuffer.append(" Portion Restored");

            return stringBuffer.toString();

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
            {
                LogUtil.put(LogFactory.getInstance("Restore Table Failed\nSQL Statement", this, "restoreTable()", e));
            }

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Table: ");
            stringBuffer.append(this.tableName);
            stringBuffer.append(" Restoration Failed");

            return stringBuffer.toString();
        }
    }
}
