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
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.EntryData;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.Encoder;
import org.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoFactoryInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactoryInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactoryInterface;

public class TransformInfoEntity 
extends AbSqlBean 
implements TransformInfoEntityInterface
{
    protected final String tableName = "transforminfo";

    private final TransformInfoFactoryInterface transformInfoFactoryInterface;
    private final TransformInfoObjectConfigAndManipulatorFactoryInterface transformInfoObjectConfigAndManipulatorFactoryInterface;
    private final TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface;

    public TransformInfoEntity(
        TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface,
        TransformInfoObjectConfigAndManipulatorFactoryInterface transformInfoObjectConfigAndManipulatorFactoryInterface,
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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "insert"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "insert", e));
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(TransformInfoData.getInstance().NAME, value);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "delete"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "delete", e));
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

        Iterator iter = objectConfigColumnVector.iterator();
        while (iter.hasNext())
        {
            String objectConfigString = (String) iter.next();

          //TWB - Encoder for GAE for XML using JIQL
            objectConfigString = new String(Encoder.decode(objectConfigString));

            objectConfigVector.add(
                this.transformInfoObjectConfigAndManipulatorFactoryInterface.getInstance(
                (TransformInfoInterface) this,
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

        Iterator iter = columnVector.iterator();
        while (iter.hasNext())
        {
            String viewNameString = (String) iter.next();
            viewNameVector.add(viewNameString);
        }
        return viewNameVector;
    }

    public final String createTableStatement()
    {
    	TransformInfoData transformInfoData = TransformInfoData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");
        stringBuffer.append(transformInfoData.NAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        //store should be changed to view group for template update performance
        //they could still use multiple templates but it reduces the number of views to
        //go through when updating the views
        //transformInfoData.GROUP
        stringBuffer.append(StoreFrontData.getInstance().NAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(transformInfoData.OBJECTFILENAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(transformInfoData.OBJECT);
        stringBuffer.append(" BLOB NOT NULL,");
        stringBuffer.append(transformInfoData.OBJECTCONFIGFILENAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(transformInfoData.OBJECTCONFIG);
        stringBuffer.append(" BLOB NOT NULL,");
        stringBuffer.append(transformInfoData.TEMPLATEFILENAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(transformInfoData.TEMPLATE);
        stringBuffer.append(" BLOB NOT NULL,");
        stringBuffer.append(transformInfoData.DATAFILENAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        stringBuffer.append(transformInfoData.DATA);
        stringBuffer.append(" BLOB NOT NULL,");
        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(EntryData.getInstance().getInstance().getInstance().TIMECREATED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");
        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(transformInfoData.NAME);
        stringBuffer.append(") )");

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

    public String backupTable()
    {
        return super.backupTable();
    }

    public String restoreTable(Portion portion)
    {
       return super.restoreTable(portion);
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
