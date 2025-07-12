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
package org.allbinary.data.tables.staticpages;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.init.db.StaticPagesDbInitInfo;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.search.SearchData;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

public class StaticPagesEntity extends AbSqlBean implements StaticPagesEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final String TABLENAME = "staticpages";

    public StaticPagesEntity()
    {
        super(new StaticPagesDbInitInfo());
        this.setTableName(TABLENAME);
    }

    public void insert(Vector values)
    {
        try
        {
            super.insert(values);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, INSERT);
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, INSERT, e);
            }
        }
    }

    public String getFile(String store, String keywords)
    {
        HashMap whereHashMap = new HashMap();
        whereHashMap.put(StoreFrontData.getInstance().NAME, store);
        whereHashMap.put(BasicItemData.KEYWORDS, keywords);
        String file = super.getField(whereHashMap, SearchData.PAGE);
        return file;
    }

    /*
     public String getTable()
     {
     return super.getTable();
     }
     */
    public void delete(String keywords)
    {
        super.deleteWhere(BasicItemData.KEYWORDS, keywords);
    }

    public String dropTable()
    {
        String returnStr = super.dropTable();
        return returnStr;
    }

    /*
     public String getInputForm(String keywords)
     {
     return super.getInputWhere(SearchData.KEYWORDS, keywords);
     }
     */
    public void update(HashMap updatedValues)
    {
        super.updateWhere(BasicItemData.KEYWORDS, (String) updatedValues.get(BasicItemData.KEYWORDS), updatedValues);
    }

    public final String createTableStatement()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(TABLENAME)
                .append(this.sqlStrings.START)
                .append(StoreFrontData.getInstance().NAME)
                .append(this.sqlTypeStrings.SIXTY_CHAR_COLUMN_NOT_NULL)
                .append(BasicItemData.KEYWORDS)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(SearchData.PAGE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(StoreFrontData.getInstance().NAME)
                .append(CommonSeps.getInstance().COMMA_SEP)  //, or , space
                .append(BasicItemData.KEYWORDS)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    //return super.createTable("CREATE TABLE " + super.getTableName() + tableData);
    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
}
