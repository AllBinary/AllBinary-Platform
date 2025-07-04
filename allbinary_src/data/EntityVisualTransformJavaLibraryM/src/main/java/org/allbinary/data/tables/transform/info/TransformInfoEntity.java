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
package org.allbinary.data.tables.transform.info;

import java.util.HashMap;

import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.Encoder;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoFactoryInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactoryBase;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactoryInterface;

public class TransformInfoEntity 
extends AbSqlBean 
implements TransformInfoEntityInterface
{
    protected final String tableName = "transforminfo";
    
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();    

    private final TransformInfoFactoryInterface transformInfoFactoryInterface;
    private final TransformInfoObjectConfigAndManipulatorFactoryBase transformInfoObjectConfigAndManipulatorFactoryInterface;
    private final TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface;

    public TransformInfoEntity(
        TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface,
        TransformInfoObjectConfigAndManipulatorFactoryBase transformInfoObjectConfigAndManipulatorFactoryInterface,
        TransformInfoFactoryInterface transformInfoFactoryInterface)
    {
        super(new UserDbInitInfo());

        this.setTableName(tableName);

        this.transformInfoObjectConfigGeneratorFactoryInterface = transformInfoObjectConfigGeneratorFactoryInterface;
        this.transformInfoObjectConfigAndManipulatorFactoryInterface = transformInfoObjectConfigAndManipulatorFactoryInterface;
        this.transformInfoFactoryInterface = transformInfoFactoryInterface;
    }

    public void insert(Vector values)
    {
        try
        {
            super.insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, INSERT));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, INSERT, e));
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(TransformInfoData.getInstance().NAME, value);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, commonStrings.delete));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.delete, e));
            }
        }
    }

    public TransformInfoInterface get(
        String name, HashMap propertiesHashMap, PageContext pageContext)
        throws Exception
    {
    	TransformInfoData transformInfoData = TransformInfoData.getInstance();
    	
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(transformInfoData.NAME, name);
        HashMap hashMap = super.getRow(keysAndValues);

        if (hashMap != null)
        {
        	//TWB - Encoder for GAE for XML using JIQL
            Object object = hashMap.get(transformInfoData.OBJECTCONFIG);
            if(object != null)
            {
                String string = (String) object;
                hashMap.put(transformInfoData.OBJECTCONFIG, new String(Encoder.decode(string)));
            }

            Object objectData = hashMap.get(transformInfoData.DATA);
            if(objectData != null)
            {
                String string = (String) objectData;
                hashMap.put(transformInfoData.DATA, new String(Encoder.decode(string)));
            }
          //TWB - End - Encoder for GAE for XML using JIQL
            
            return this.transformInfoFactoryInterface.getInstance(
                hashMap, propertiesHashMap, pageContext);
        } else
        {
            return null;
        }
        //throw new Exception("TransformInfo Not Found: " + name);
    }

    public Vector getObjectConfigs(String storeName) throws Exception
    {
        Vector objectConfigVector = new Vector();
        Vector objectConfigColumnVector = this.getColumnWhere(
            TransformInfoData.getInstance().OBJECTCONFIG, StoreFrontData.getInstance().NAME, storeName);

        int size = objectConfigColumnVector.size();
        for (int i = 0; i < size; i++)
        {
            String objectConfigString = (String) objectConfigColumnVector.get(i);

          //TWB - Encoder for GAE for XML using JIQL
            objectConfigString = new String(Encoder.decode(objectConfigString));

            objectConfigVector.add(
                this.transformInfoObjectConfigAndManipulatorFactoryInterface.getInstance(
                    abeClientInformation, (TransformInfoInterface) this,
                DomDocumentHelper.create(objectConfigString)));
          //TWB - End Encoder for GAE for XML using JIQL
        }
        return objectConfigVector;
    }

    public Vector getNames(String storeName) throws Exception
    {
        Vector viewNameVector = new Vector();
        Vector columnVector = this.getColumnWhere(
            TransformInfoData.getInstance().NAME, StoreFrontData.getInstance().NAME, storeName);

        int size = columnVector.size();
        for (int i = 0; i < size; i++)
        {
            String viewNameString = (String) columnVector.get(i);
            viewNameVector.add(viewNameString);
        }
        return viewNameVector;
    }

    public final String createTableStatement()
    {
    	TransformInfoData transformInfoData = TransformInfoData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);
        stringBuffer.append(transformInfoData.NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        //store should be changed to view group for template update performance
        //they could still use multiple templates but it reduces the number of views to
        //go through when updating the views
        //transformInfoData.GROUP
        stringBuffer.append(StoreFrontData.getInstance().NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(transformInfoData.OBJECTFILENAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(transformInfoData.OBJECT);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);
        stringBuffer.append(transformInfoData.OBJECTCONFIGFILENAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(transformInfoData.OBJECTCONFIG);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);
        stringBuffer.append(transformInfoData.TEMPLATEFILENAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(transformInfoData.TEMPLATE);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);
        stringBuffer.append(transformInfoData.DATAFILENAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
        stringBuffer.append(transformInfoData.DATA);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);
        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);
        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(transformInfoData.NAME);
        stringBuffer.append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

    public void update(HashMap updatedValues)
    {
        super.updateWhere(TransformInfoData.getInstance().NAME, 
        		(String) updatedValues.get(TransformInfoData.getInstance().NAME), updatedValues);
    }

    public String dropTable()
    {
        return super.dropTable();
    }
    /*
    public String getTable()
    {
    return super.getTable();
    }
     */

}
