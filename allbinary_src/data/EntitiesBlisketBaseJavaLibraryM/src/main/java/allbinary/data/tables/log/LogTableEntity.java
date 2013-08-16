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
package allbinary.data.tables.log;

import abcs.business.init.db.LogDbInitInfo;
import allbinary.logic.communication.sql.AbSqlBean;

public class LogTableEntity extends AbSqlBean implements LogTableEntityInterface
{
   private final String tableName = "log";
   
   public LogTableEntity()
   {
      super(new LogDbInitInfo());
      this.setTableName(tableName);
   }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(   "ID VARCHAR(255) NOT NULL, " +
   "BROWSER VARCHAR(255) NOT NULL, " +
   "HOST VARCHAR(255), " +
   "ADDRESS VARCHAR(255) NOT NULL, " +
   "PAGE VARCHAR(255) NOT NULL, " +
   "PORT INT(11) UNSIGNED NOT NULL, " +
   "TIME BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append("ID");
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    //return super.createTable("CREATE TABLE " + super.getTableName() + tableData);
    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
}

