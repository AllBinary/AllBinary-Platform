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
package org.allbinary.data.tables.advertisement.sales;

import java.util.HashMap;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.group.GroupData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class AffiliateSalesEntity extends AbSqlBean implements AffiliateSalesEntityInterface
{

    protected final String tableName = "affiliate";

    public AffiliateSalesEntity()
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
     LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS,this,INSERT);
     }
     }
     catch(Exception e)
     {
     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
     {
     LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,INSERT,e);
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
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, commonStrings.delete));
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.delete, e));
            }
        }
    }

    /*
     public AffiliatesInterface get(String name)
     {
     HashMap keysAndValues = new HashMap();
     keysAndValues.put(StoreFrontData.NAME, name);
     Vector hashMapVector = super.getRows(keysAndValues);
      
     Vector affiliateVector = new Vector();
     iter = hashMapVector;
     while(iter.hasNext())
     {
     HashMap hashMap = (HashMap) iter.next();
     if(hashMap!=null)
     affiliateVector.add((AffiliateInterface) new Affiliate(hashMap));
     }

     return (AffiliatesInterface) new Affiliates(affiliateVector);
     }

     public AffiliateInterface get(String name, String affiliateName)
     {
     HashMap keysAndValues = new HashMap();
     keysAndValues.put(StoreFrontData.NAME, name);
     keysAndValues.put(AffiliateData.NAME, name);
     HashMap hashMap = super.getRow(keysAndValues);
      
     if(hashMap!=null) 
     return (AffiliateInterface) new Affiliate(hashMap);
     else 
     return null;
     }
     */
    public final String createTableStatement()
    {
        EntryData entryData = EntryData.getInstance();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);

        stringBuffer.append(this.getTableName());
        stringBuffer.append(this.sqlStrings.START);

       //Without compound keys I use compound data in the key colum. 
        //Usually StoreName and id/name of entity
        stringBuffer.append(entryData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(StoreFrontData.getInstance().NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                //AffiliateData.NAME + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
                //AffiliateData.DESCRIPTION + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
                .append(GroupData.NAME).append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DynamicObjectData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
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

    public void update(HashMap updatedValues)
    {
        super.updateWhere(EntryData.getInstance().ID,
                (String) updatedValues.get(EntryData.getInstance().ID),
                updatedValues);
    }

    public String dropTable()
    {
        return super.dropTable();
    }
}
