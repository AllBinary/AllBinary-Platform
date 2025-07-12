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
package org.allbinary.data.tables.user.address;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.StreetAddressData;
import org.allbinary.data.tables.TableDataFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class StreetAddressesEntity extends AbSqlBean implements StreetAddressesEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private String userName;

    public StreetAddressesEntity()
    {
        super(new UserDbInitInfo());
    }

    public StreetAddressesEntity(String userName)
    {
        super(new UserDbInitInfo());
        this.userName = userName;
    }

    public void remove(Integer index)
    {
        try
        {
            HashMap whereHashMap = new HashMap();
            whereHashMap.put(UserData.USERNAME, userName);
            whereHashMap.put(StreetAddressData.ID, (String) index.toString());
            super.deleteWhere(whereHashMap);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "remove");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "remove", e);
            }
        }
    }

    public void add(StreetAddress address)
    {
        this.add(address, TableDataFactory.getInstance().INTEGER_MAX_VALUE_STRING);
    }

    private void add(StreetAddress address, String index)
    {
        try
        {
            Vector values = new Vector();

            values.add(index);
            values.add(userName);
            values.add(StringUtil.getInstance().EMPTY_STRING);
            values.add(address.getName());
            values.add(address.getStreet());
            values.add(address.getCity());
            values.add(address.getState());
            values.add(address.getCode());
            values.add(address.getCountry());

            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            values.add(time);

            super.insert(values);

            this.setDefault(getLastId());

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "add");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "add", e);
            }
        }
    }

    public void update(StreetAddress address)
    {
        try
        {
            HashMap addressHashMap = address.toHashMap();

         //Calendar calendar=Calendar.getInstance();
            //String time = new String(new Long(calendar.getTimeInMillis()).toString());
            HashMap whereKeyValuePairs = new HashMap();
            whereKeyValuePairs.put(StreetAddressData.ID, address.getId());
            whereKeyValuePairs.put(UserData.USERNAME, this.userName);

            super.updateWhere(whereKeyValuePairs, addressHashMap);

            this.setDefault(address.getId());

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "update");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "add", e);
            }
        }
    }

    public String getLastId()
    {
        return super.getLargestIntegerInColumnWhere(StreetAddressData.ID, UserData.USERNAME, userName);
    }

    //retrieve one users addresses
    public Vector get()
    {
        try
        {
            Vector streetAddressVector = new Vector();
            HashMap keyAndValue = new HashMap();
            keyAndValue.put(UserData.USERNAME, userName);
            Vector addressList = super.getRows(keyAndValue);

            final int size = addressList.size();
            for (int index = 0; index < size; index++)
            {
                HashMap addressHashMap = (HashMap) addressList.get(index);

                StreetAddress streetAddress = new StreetAddress(addressHashMap);
                if(streetAddress != null)
                {
                    streetAddressVector.add(streetAddress);
                }
            }

            return streetAddressVector;

        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, commonStrings.GET, e);
            }
            return null;
        }
    }

    public StreetAddress get(Integer index)
    {
        try
        {
            //Vector streetAddressVector = new Vector();
            HashMap keyAndValue = new HashMap();
            keyAndValue.put(UserData.USERNAME, userName);
            keyAndValue.put(StreetAddressData.ID, index.toString());
            HashMap addressHashMap = super.getRow(keyAndValue);

            if(addressHashMap != null)
            {
                return new StreetAddress(addressHashMap);
            }else
            {
                return null;
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, commonStrings.GET, e);
            }
            return null;
        }
    }

    public StreetAddress getDefault()
    {
        try
        {
            HashMap addressHashMap = new HashMap();
            HashMap updateKeyAndValue = new HashMap();
            updateKeyAndValue.put(StreetAddressData.DEFAULT, StreetAddressData.DEFAULT);
            updateKeyAndValue.put(UserData.USERNAME, userName);

            addressHashMap = super.getRow(updateKeyAndValue);
            if(addressHashMap != null)
            {
                StreetAddress streetAddress = new StreetAddress(addressHashMap);
                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put(this.commonStrings.SUCCESS, this, "getDefault");
                }
                return streetAddress;
            }else
            {
                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                {
                    logUtil.put("No Default Address Found", this, "getDefault");
                }
                return null;
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "getDefault", e);
            }
            return null;
        }
    }

    public void setDefault(String value)
    {
        try
        {
            HashMap updateKeyAndValue = new HashMap();
            HashMap whereKeyAndValue = new HashMap();

            whereKeyAndValue.put(UserData.USERNAME, userName);

            //remove old default value if it exist
            StreetAddress streetAddress = getDefault();
            if(streetAddress != null)
            {
                updateKeyAndValue.put(StreetAddressData.DEFAULT, StringUtil.getInstance().EMPTY_STRING);
                whereKeyAndValue.put(StreetAddressData.ID, streetAddress.getId());
                super.updateWhere(whereKeyAndValue, updateKeyAndValue);
            }

            //now update new default         
            whereKeyAndValue.put(StreetAddressData.ID, value);
            updateKeyAndValue.put(StreetAddressData.DEFAULT, StreetAddressData.DEFAULT);

            super.updateWhere(whereKeyAndValue, updateKeyAndValue);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, "setDefault");
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, "setShippingAddress", e);
            }
        }
    }

    protected String getUserName()
    {
        return this.userName;
    }

    public final String createTableStatement()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(this.getTableName())
                .append(this.sqlStrings.START)
                .append(StreetAddressData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_AUTO_INCREMENT_NOT_NULL)
                .append(UserData.USERNAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(StreetAddressData.DEFAULT)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(StreetAddressData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(StreetAddressData.STREET)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(StreetAddressData.CITY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(StreetAddressData.STATE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(StreetAddressData.CODE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(StreetAddressData.COUNTRY)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(StreetAddressData.ID)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    //return super.createTable("CREATE TABLE " +  + tableData);
    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

    public String drop()
    {
        return super.dropTable();
    }

    /*
     public String getForm()
     {
     return super.getInputWhere(UserData.USERNAME,userName);
     }
     */
    /*
     public String getTable()
     {
     return super.getTable();
     }*/
}
