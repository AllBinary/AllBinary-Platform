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
package admin.taghelpers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.special.SpecialItemData;
import org.allbinary.data.tables.user.commerce.inventory.item.special.SpecialItemsEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class SpecialItemsRequestHelper extends ModifyTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HttpServletRequest request;
     
   private String id;   
   private String enabled;
   private String number;   
   
   private String startTime;
   private String endTime;
   
   private String price;
   
   private String timeEntered;
   private String lastModified;
   
   public SpecialItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   public void getFormData()
   {
      this.id = request.getParameter(BasicItemData.ID);
      this.number = request.getParameter(BasicItemData.NUMBER);
      this.enabled = request.getParameter(EntryData.getInstance().ENABLE);
      this.startTime = request.getParameter(SpecialItemData.START_TIME);
      this.endTime = request.getParameter(SpecialItemData.END_TIME);
      this.price = request.getParameter(BasicItemData.PRICE);         
      this.timeEntered = request.getParameter(EntryData.getInstance().TIMECREATED);
      this.lastModified = request.getParameter(EntryData.getInstance().LASTMODIFIED);
   }

   private HashMap getHashMap()
   {
      HashMap values = new HashMap();            
      
      values.put(BasicItemData.ID,this.id);
      values.put(BasicItemData.NUMBER,this.number);
      values.put(EntryData.getInstance().ENABLE,this.enabled);
      values.put(SpecialItemData.START_TIME,this.startTime);
      values.put(SpecialItemData.END_TIME,this.endTime);
      values.put(BasicItemData.PRICE,this.price);
      
      Calendar calendar=Calendar.getInstance();
      String time = new String(new Long(calendar.getTimeInMillis()).toString());
      
      values.put(EntryData.getInstance().LASTMODIFIED,time);
      
      return values;
   }

   public String insert()
   {
      try
      {
         Calendar calendar=Calendar.getInstance();
         String time = new String(new Long(calendar.getTimeInMillis()).toString());
         Vector values = new Vector();
         
         values.add(this.id);
         values.add(this.number);
         values.add(this.enabled);
         
         values.add(this.startTime);
         values.add(this.endTime);
         
         values.add(this.price);
         
         values.add(time);
         values.add(time);
         
         SpecialItemsEntityFactory.getInstance().getSpecialItemsEntityInstance().insert(values);
         
         String success = "Successfully inserted " + id + " into items table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"insert()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to insert " + id + " into items table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"inserts()",e);
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         SpecialItemsEntityFactory.getInstance().getSpecialItemsEntityInstance().delete(id);
         
         String success = "Successfully deleted";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"delete()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"delete()",e);
         }
         return error;
      }
   }
      
   public String update()
   {
      try
      {
         String success = "Update Successful";
         
         HashMap values = this.getHashMap();
         SpecialItemsEntityFactory.getInstance().getSpecialItemsEntityInstance().update(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(id + " " + success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update: " + id;
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }

}
