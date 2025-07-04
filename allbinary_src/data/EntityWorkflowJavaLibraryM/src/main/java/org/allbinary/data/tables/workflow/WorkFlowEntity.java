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
package org.allbinary.data.tables.workflow;

import java.util.HashMap;

import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.workflow.DbWorkFlowFactory;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.control.workflow.WorkFlowData;
import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class WorkFlowEntity extends AbSqlBean implements WorkFlowEntityInterface
{
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();

    protected final String tableName = "workflows";

    private final String METHOD_GET = "get()";
    private final String METHOD_UPDATE = "update";

    public WorkFlowEntity()
    {
        super(new UserDbInitInfo());
        this.setTableName(tableName);
    }

    public void insert(final Vector values)
    {
        try
        {
            super.insert(values);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, INSERT));
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, INSERT, e));
            }
        }
    }

    public void delete(final String name, final String storeName)
    {
        try
        {
            final HashMap keysAndValues = new HashMap();

            keysAndValues.put(WorkFlowData.getInstance().NAME, name);
            keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);

            super.deleteWhere(keysAndValues);

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

    public WorkFlowInterface get(final String name, final String storeName) throws Exception, LicensingException
    {
        try
        {
            final HashMap keysAndValues = new HashMap();
            keysAndValues.put(WorkFlowData.getInstance().NAME, name);
            keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
            final HashMap hashMap = super.getRow(keysAndValues);

            return (WorkFlowInterface) DbWorkFlowFactory.getInstance().getInstance(abeClientInformation, hashMap);
        }catch(LicensingException e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, METHOD_GET, e));
            }
            throw e;
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, METHOD_GET, e));
            }
            throw e;
        }
    }

    public Vector get(String storeName)
    {
        try
        {
            Vector workFlowsVector = new Vector();
            HashMap keysAndValues = new HashMap();

            keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);

            Vector hashMapVector = super.getRows(keysAndValues);

            int size = hashMapVector.size();
            for (int i = 0; i < size; i++)
            {
                HashMap workFlowHashMap = (HashMap) hashMapVector.get(i);
                if(workFlowHashMap != null)
                {
                    workFlowsVector.add(DbWorkFlowFactory.getInstance().getInstance(abeClientInformation, workFlowHashMap));
                }
            }

            return workFlowsVector;
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, this.METHOD_GET, e));
            }
            return null;
        }
    }

    public void update(HashMap updatedValues)
    {
        try
        {
            HashMap wherekeysAndValues = new HashMap();
            wherekeysAndValues.put(WorkFlowData.getInstance().NAME,
                    (String) updatedValues.get(WorkFlowData.getInstance().NAME));
            wherekeysAndValues.put(StoreFrontData.getInstance().NAME,
                    (String) updatedValues.get(StoreFrontData.getInstance().NAME));

            super.updateWhere(wherekeysAndValues, updatedValues);
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, METHOD_UPDATE, e));
            }
        }
    }

    public final String createTableStatement()
    {
        final WorkFlowData workFlowData = WorkFlowData.getInstance();

        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(workFlowData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(StoreFrontData.getInstance().NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DynamicObjectData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(workFlowData.DATA)
                .append(this.sqlTypeStrings.BLOB_NOT_NULL)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(workFlowData.NAME)
                .append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

    public String dropTable()
    {
        return super.dropTable();
    }

}
