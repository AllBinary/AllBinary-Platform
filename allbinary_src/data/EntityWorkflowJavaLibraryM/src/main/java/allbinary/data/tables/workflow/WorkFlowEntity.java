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
package allbinary.data.tables.workflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import abcs.business.init.db.UserDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.business.DynamicObjectData;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.entry.EntryData;
import allbinary.logic.communication.sql.AbSqlBean;
import allbinary.logic.control.workflow.DbWorkFlowFactory;
import allbinary.logic.control.workflow.WorkFlowData;
import allbinary.logic.control.workflow.WorkFlowInterface;

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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success", this, "insert"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"insert",e));
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"delete"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"delete",e));
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
         }
         throw e;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"get",e));
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"update",e));
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

           stringBuffer.append(   workFlowData.NAME + " VARCHAR(255) NOT NULL," +
   StoreFrontData.getInstance().NAME + " VARCHAR(255) NOT NULL," +
   DynamicObjectData.NAME + " VARCHAR(255) NOT NULL," +
   workFlowData.DATA + " BLOB NOT NULL," +
   EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
   EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, ");

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
