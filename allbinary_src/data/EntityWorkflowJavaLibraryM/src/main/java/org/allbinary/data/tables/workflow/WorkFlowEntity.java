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
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
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

public class WorkFlowEntity extends AbSqlBean implements WorkFlowEntityInterface
{
   protected final String tableName = "workflows";
      
   public WorkFlowEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
   
   public void insert(Vector values)
   {
      try
      {
         super.insert(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, INSERT));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,INSERT,e));
         }
      }
   }
   
   public void delete(String name, String storeName)
   {
      try
      {
         HashMap keysAndValues = new HashMap();

         keysAndValues.put(WorkFlowData.getInstance().NAME, name);
         keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
         
         super.deleteWhere(keysAndValues);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS,this,DELETE));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,DELETE,e));
         }
      }
   }
   
   public WorkFlowInterface get(String name, String storeName) throws Exception, LicensingException
   {
      try
      {
         HashMap keysAndValues = new HashMap();
         keysAndValues.put(WorkFlowData.getInstance().NAME, name);
         keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
         HashMap hashMap = super.getRow(keysAndValues);
         
         return (WorkFlowInterface) DbWorkFlowFactory.getInstance(hashMap);
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,"get",e));
         }
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,"get",e));
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
         
         Iterator iter = hashMapVector.iterator();
         while(iter.hasNext())
         {
            HashMap workFlowHashMap = (HashMap) iter.next();
            if(workFlowHashMap!=null)
               workFlowsVector.add(DbWorkFlowFactory.getInstance(workFlowHashMap));
         }
         
         return workFlowsVector;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,"get",e));
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
         
         super.updateWhere(wherekeysAndValues,updatedValues);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,"update",e));
         }
      }
   }

   public final String createTableStatement()
   {
	   WorkFlowData workFlowData = WorkFlowData.getInstance();
	   
	   StringBuffer stringBuffer = new StringBuffer();

	   stringBuffer.append("CREATE TABLE ");

	   stringBuffer.append(tableName);
	   stringBuffer.append(" (");

           stringBuffer.append(   workFlowData.NAME + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
   StoreFrontData.getInstance().NAME + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
   DynamicObjectData.NAME + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
   workFlowData.DATA + this.sqlTypeStrings.BLOB_NOT_NULL +
   EntryData.getInstance().TIMECREATED + this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL +
   EntryData.getInstance().LASTMODIFIED + this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

	   stringBuffer.append("PRIMARY KEY(");
	   stringBuffer.append(workFlowData.NAME);
	   stringBuffer.append(") )");

	  return stringBuffer.toString();
   }

   public String createTable()
   {
      return super.createTable(this.createTableStatement());
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
