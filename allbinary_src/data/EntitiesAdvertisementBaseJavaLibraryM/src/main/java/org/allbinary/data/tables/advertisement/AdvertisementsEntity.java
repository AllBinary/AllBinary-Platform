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
package org.allbinary.data.tables.advertisement;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.advertisement.AdvertisementData;
import org.allbinary.business.advertisement.AdvertisementInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;

public class AdvertisementsEntity extends AbSqlBean implements AdvertisementsEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final String tableName = "advertisements";

    private final String _INDEX = "_index";

    public AdvertisementsEntity()
    {
        super(new UserDbInitInfo());
        this.setTableName(tableName);
    }

    /*
     public void insert(Vector values)
     {
     try
     {
     super.insert(values);
         
     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
     {
     logUtil.put(this.commonStrings.SUCCESS,this,INSERT);
     }
     }
     catch(Exception e)
     {
     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
     {
     logUtil.put(this.commonStrings.FAILURE,this,INSERT,e);
     }
     }
     }
     */
    public void delete(String value)
    {
        try
        {
            super.deleteWhere(EntryData.getInstance().ID, value);
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, commonStrings.delete);
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, commonStrings.delete, e);
            }
        }
    }

    //AdvertisementsInterface
    public Vector get(String storeName)
    {
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
        Vector hashMapVector = super.getRows(keysAndValues);

        Vector vector = new Vector();
        final int size = hashMapVector.size();
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) hashMapVector.get(index);
        // if(hashMap!=null)
            // vector.add((AdvertisementInterface) new Advertisement(hashMap));
        }
        return (Vector) vector;
    }

    public AdvertisementInterface get(String storeName, String advertismentName)
    {
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
        keysAndValues.put(AdvertisementData.getInstance().NAME, advertismentName);
        HashMap hashMap = super.getRow(keysAndValues);

     // if(hashMap!=null) 
        //     return (AdvertisementInterface) new Advertisement(hashMap);
        // else 
        return null;
    }

    public final String createTableStatement()
    {
        AdvertisementData advertisementData = AdvertisementData.getInstance();

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);

        stringBuffer.append(this.getTableName())
                .append(this.sqlStrings.START)
                .append(advertisementData.NAME)
                .append(_INDEX)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(advertisementData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(StoreFrontData.getInstance().NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(advertisementData.DESCRIPTION)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

       //Holds the ad component usually uses the default
        //AdComponent that uses the AdViewComponent
        stringBuffer.append(DynamicObjectData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(advertisementData.NAME)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        String returnStr = super.createTable(this.createTableStatement());
        return returnStr;
    }

    public void update(HashMap updatedValues)
    {
        //super.updateWhere(StoreFrontData.NAME,(String) updatedValues.get(StoreFrontData.NAME),updatedValues);      
        super.updateWhere(EntryData.getInstance().ID, (String) updatedValues.get(EntryData.getInstance().ID), updatedValues);
    }

    public String dropTable()
    {
        return super.dropTable();
    }
}
