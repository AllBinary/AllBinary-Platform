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

import org.allbinary.business.installer.Portion;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;

public class AbSqlTableUtil
{
    private static final AbSqlTableUtil instance = new AbSqlTableUtil();

    /**
     * @return the instance
     */
    public static AbSqlTableUtil getInstance()
    {
        return instance;
    }
    
    private final String TABLE_LABEL = "Table: ";

    protected final StringUtil stringUtil = StringUtil.getInstance();
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final SqlStrings sqlStrings = SqlStrings.getInstance();
    
    //This is not good for large data
    public synchronized String backupTable(AbSqlTable abSqlTable)
    {
        final String tableName = abSqlTable.getTableName();
        return TABLE_LABEL + tableName + " Backup Failed";
    }

    public synchronized String restoreTable(AbSqlTable abSqlTable, Portion portion)
    {
        final String tableName = abSqlTable.getTableName();

        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(TABLE_LABEL);
        stringBuffer.append(tableName);
        stringBuffer.append(" Restoration Failed");

        return stringBuffer.toString();
    }
}
