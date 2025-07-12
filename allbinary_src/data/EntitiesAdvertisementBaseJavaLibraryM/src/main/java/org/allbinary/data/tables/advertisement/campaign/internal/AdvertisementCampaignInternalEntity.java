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
package org.allbinary.data.tables.advertisement.campaign.internal;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.advertisement.campaign.AdvertisementCampaign;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignData;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignInterface;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaigns;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignsInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.time.TimeData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;

public class AdvertisementCampaignInternalEntity extends AbSqlBean
        implements AdvertisementCampaignInternalEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final String tableName = "adCampaignInternal";

    public AdvertisementCampaignInternalEntity()
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
            super.deleteWhere(StoreFrontData.getInstance().NAME, value);

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

    public AdvertisementCampaignsInterface getCampaignsInStore(String storeName)
    {
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
        Vector hashMapVector = super.getRows(keysAndValues);

        Vector vector = new Vector();
        final int size = hashMapVector.size();
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) hashMapVector.get(index);
            if(hashMap != null)
            {
                vector.add((AdvertisementCampaignInterface) new AdvertisementCampaign(hashMap));
            }
        }

        return (AdvertisementCampaignsInterface) new AdvertisementCampaigns(vector);
    }

    public AdvertisementCampaignInterface get(String storeName, String name)
    {
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
        keysAndValues.put(AdvertisementCampaignData.getInstance().NAME, name);
        HashMap hashMap = super.getRow(keysAndValues);

        if(hashMap != null)
        {
            return (AdvertisementCampaignInterface) new AdvertisementCampaign(hashMap);
        }else
        {
            return null;
        }
    }

    public void update(HashMap updatedValues)
    {
        super.updateWhere(EntryData.getInstance().getInstance().ID,
                (String) updatedValues.get(EntryData.getInstance().getInstance().ID),
                updatedValues);
    }

    public final String createTableStatement()
    {
        EntryData entryData = EntryData.getInstance().getInstance();

        AdvertisementCampaignData advertisementCampaignData
                = AdvertisementCampaignData.getInstance();

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);

        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.START);

       //Without compound keys I use compound data in the key colum. 
        //Usually StoreName and id/name of entity
        stringBuffer.append(entryData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(StoreFrontData.getInstance().NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(advertisementCampaignData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(advertisementCampaignData.DESCRIPTION)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(advertisementCampaignData.CONFIG)
                .append(this.sqlTypeStrings.BLOB_NOT_NULL)
                .append(TimeData.getInstance().START)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(TimeData.getInstance().END)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(entryData.TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(entryData.LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(entryData.ID)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        String returnStr = super.createTable(this.createTableStatement());
        return returnStr;
    }

    public String dropTable()
    {
        return super.dropTable();
    }
}
