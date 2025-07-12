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
import org.allbinary.logic.string.StringMaker;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.io.AbFileOutputStream;
import org.allbinary.logic.io.BufferedLineReader;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileUtil;
import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.PATH_GLOBALS;
import java.io.OutputStream;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;

public class AbSqlTableUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final AbSqlTableUtil instance = new AbSqlTableUtil();

    /**
     * @return the instance
     */
    public static AbSqlTableUtil getInstance()
    {
        return instance;
    }

    private String EXTENSION = ".adb";

    private final String END = "')\n";
    
    private final String SAVING_BACKUP_PATH = "Saving Backup: Path: ";
    private final String FILE_LABEL = " File: ";

    private final String METHOD_RESTORE_TABLE = "restoreTable()";
    private final String METHOD_BACKUP_TABLE = "backupTable()";
    private final String METHOD_BACKUP_FILE = "backupFile()";
    private final String METHOD_GET_OUTPUT_STREAM = "getOutputStream()";
    
    private final String TABLE_CREATION_SUCCESS = "Table Creation Successful: ";
    private final String DROPPED_SUCCESS = " Dropped Successfully";
    private final String SAVING = "Saving: ";
    private final String APPENDING = "Appending: ";
    
    private final String TABLE_LABEL = "Table: ";
    private final String BACKUP_SUCCESS = " Backup Success";

    private final String ERROR_CREATING = "Error Creating: ";
    
    private final String TOTAL_LABEL = " Total: ";
    private final String SECTION_LABEL = " Section: ";
    private final String DASH = " - ";
    private final String PORTION_RESTORED = " Portion Restored";

    private final char[] specialCharArray =
        {
            '\n', '\f', '\r'
        };

    private final String NEW_LINE = "\\n";
        
    protected final StringUtil stringUtil = StringUtil.getInstance();
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final SqlStrings sqlStrings = SqlStrings.getInstance();
    
    private synchronized OutputStream getOutputStream(String backupPath, String tableName)
    {
        try
        {
            String fileName = tableName + EXTENSION;

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(SAVING + tableName, this, this.METHOD_GET_OUTPUT_STREAM);
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put("Create File", this, this.METHOD_GET_OUTPUT_STREAM, e);
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
            String time = timeLong.toString();

            final StringMaker stringBuffer = new StringMaker();

            String fileName = tableName + EXTENSION;

            stringBuffer.append(backupPath);
            stringBuffer.append(AbPathData.getInstance().SEPARATOR);
            stringBuffer.append(time);

            AbPath backupAbPath = new AbPath(stringBuffer.toString());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(SAVING_BACKUP_PATH);
                stringBuffer.append(backupAbPath.toFileSystemString());
                stringBuffer.append(FILE_LABEL);
                stringBuffer.append(fileName);

                logUtil.put(stringBuffer.toString(), this, this.METHOD_BACKUP_FILE);
            }

            Directory.create(backupAbPath);
            AbFile backupFileBak = new AbFile(backupAbPath.toFileSystemString());

            backupFileBak.createNewFile();

            FileUtil.getInstance().copy(path, backupAbPath);

            return true;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put(this.commonStrings.EXCEPTION, this, this.METHOD_BACKUP_FILE, e);
            }
            return false;
        }
    }

    private String convertNewLines(String value)
    {
        //Replace replace = new Replace("\n","\\n");
        StringMaker stringBuffer = new StringMaker();

        int index = 0;
        int lastIndex = 0;
        while (index < value.length())
        {
            index = value.indexOf(specialCharArray[0], lastIndex);
            if (index != -1)
            {
                String nextLine = value.substring(lastIndex, index - 1);
                stringBuffer.append(nextLine);
                stringBuffer.append(NEW_LINE);
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
    public synchronized String backupTable(AbSqlTable abSqlTable)
    {
        final String tableName = abSqlTable.getTableName();
        
        try
        {
            int count = 0;

            String sqlStatement = this.sqlStrings.SELECT_ALL_FROM + tableName;
            String path = org.allbinary.globals.URLGLOBALS.getMainPath()
                + PATH_GLOBALS.getInstance().BACKUP_PATH;

            if (!Directory.create(new AbPath(path)))
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(ERROR_CREATING + path, this, this.METHOD_BACKUP_TABLE);
                }
            }

            ResultSet rset = abSqlTable.executeSQLStatement(sqlStatement);

            ResultSetMetaData rsmd = rset.getMetaData();

            int colNum = rsmd.getColumnCount();

            final String QUERY_START = new StringBuilder().append(this.sqlStrings.INSERT_INTO).append(tableName).append(this.sqlStrings.VALUES).toString();

            StringMaker stringBuffer = new StringMaker();

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

                    stringBuffer.append(this.sqlStrings.SINGLE_QUOTE_COMMA_SEP);
                }
                stringBuffer.append(rset.getString(colNum));
                stringBuffer.append(END);

                String sqlStatementLine = stringBuffer.toString();

                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(APPENDING + sqlStatementLine, this, this.METHOD_BACKUP_TABLE);
                }

                outputStream.write(sqlStatementLine.getBytes());
            }

            StreamUtil.getInstance().close(outputStream);

            return TABLE_LABEL + tableName + BACKUP_SUCCESS;
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put("Backup Table Failed\nSQL Statement", this, this.METHOD_BACKUP_TABLE, e);
            }
            return TABLE_LABEL + tableName + " Backup Failed";
        }
    }

    public synchronized String restoreTable(AbSqlTable abSqlTable, Portion portion)
    {
        final String tableName = abSqlTable.getTableName();

        try
        {
            String path = org.allbinary.globals.URLGLOBALS.getMainPath()
                + PATH_GLOBALS.getInstance().BACKUP_PATH;

            int current = portion.getCurrent().intValue();
            if (current == 0)
            {
                if (Directory.create(new AbPath(path)))
                {
                    if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                    {
                        logUtil.put(this.ERROR_CREATING + path, this, this.METHOD_RESTORE_TABLE);
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

            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(TOTAL_LABEL);
            stringBuffer.append(size);
            stringBuffer.append(SECTION_LABEL);
            stringBuffer.append(start);
            stringBuffer.append(DASH);
            stringBuffer.append(end);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(stringBuffer.toString(), this, this.METHOD_RESTORE_TABLE);
            }

            //If the readahead is less than 2 lines then this will only work some of the time
            bufferedLineReader.readUpToLines(start);

            String line = this.stringUtil.EMPTY_STRING;
            while (bufferedLineReader.getCurrent() < end && (line = bufferedLineReader.readLine()) != null)
            {
                if (line.length() > 1)
                {
                    abSqlTable.executeSQLStatement(line);
                }
            }

            stringBuffer.append(this.commonSeps.SPACE);
            stringBuffer.append(this.TABLE_LABEL);
            stringBuffer.append(tableName);
            stringBuffer.append(PORTION_RESTORED);

            return stringBuffer.toString();

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGINGERROR))
            {
                logUtil.put("Restore Table Failed\nSQL Statement", this, this.METHOD_RESTORE_TABLE, e);
            }

            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(TABLE_LABEL);
            stringBuffer.append(tableName);
            stringBuffer.append(" Restoration Failed");

            return stringBuffer.toString();
        }
    }
}
