/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.data.tables.generator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class IdGeneratorEntity extends AbSqlBean
        implements IdGeneratorEntityInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String tableName = "idgenerator";
    private final String tableData;

    final String NAME = "NAME";
    final String VALUE = "VALUE";

    public IdGeneratorEntity() {
        super(new UserDbInitInfo());

        this.tableData = new StringBuilder()
                .append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(VALUE)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                //.append(EntryData.getInstance().LASTMODIFIED)
                //.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(NAME)
                .append(this.sqlStrings.END)
                .toString();

        super.setTableName(tableName);
    }

    public Long get(String name)
            throws Exception {

        HashMap keysAndValues = new HashMap();
        keysAndValues.put(NAME, name);
        HashMap hashMap = super.getRow(keysAndValues);
        
        if(((String) hashMap.get(NAME)).compareTo(name) != 0)
        {
            throw new Exception("results do not match");
        }

        String value = (String) hashMap.get(VALUE);
        
        return Long.parseLong(value);
    }

    public void insert(Vector values) {
        try {
            //Calendar calendar = Calendar.getInstance();
            //String time = new String(new Long(calendar.getTimeInMillis()).toString());
            //String time = new Long(calendar.getTimeInMillis()).toString();
            //values.add(time);
            //values.add(time);

            super.insert(values);

            logUtil.put(this.commonStrings.SUCCESS, this, INSERT);
        } catch (Exception e) {
            logUtil.put(this.commonStrings.FAILURE, this, INSERT, e);
        }
    }

    public void delete(String value) {
        try {

            super.deleteWhere(NAME, value);

            logUtil.put(this.commonStrings.SUCCESS, this, commonStrings.delete);

        } catch (Exception e) {

            logUtil.put(this.commonStrings.FAILURE, this, commonStrings.delete, e);
        }
    }
    
    public void update(String name, Long value)
    {
        final HashMap map= new HashMap();

        map.put(NAME, name);
        map.put(VALUE, value.toString());

        this.update(map);
    }
    
    public void update(HashMap hashMap) {

        //Calendar calendar = Calendar.getInstance();
        //String time = new String(new Long(calendar.getTimeInMillis()).toString());
        //String time = new Long(calendar.getTimeInMillis()).toString();

        //hashMap.put(EntryData.getInstance().LASTMODIFIED, time);

        super.updateWhere(
                NAME,
                (String) hashMap.get(NAME),
                hashMap);
    }

    public String createTable() {
        String returnStr = super.createTable(tableData);
        return returnStr;
    }

    public String dropTable() {
        return super.dropTable();
    }
}
