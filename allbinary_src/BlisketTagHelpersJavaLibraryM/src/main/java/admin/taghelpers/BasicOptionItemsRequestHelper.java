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

import org.allbinary.logic.communication.log.LogFactory;
import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.entry.EntryData;

import org.allbinary.business.user.commerce.inventory.item.option.BasicOptionItemData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;

import org.allbinary.data.tables.user.commerce.inventory.item.options.BasicOptionItemsEntityFactory;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class BasicOptionItemsRequestHelper extends ModifyTable
{
     
   private HttpServletRequest request;
     
   private String id;   
   
   private String optionOneTitle;
   private String defaultOptionItem;
   private String defaultOptionValue;
   
   private Vector optionItem;
   private Vector optionValue;
   
   private String timeEntered;
   private String lastModified;
   
   public BasicOptionItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   public void getFormData()
   {
      this.optionItem = new Vector();
      this.optionValue = new Vector();
   
      this.id = request.getParameter(BasicItemData.ID);
            
      this.optionOneTitle = request.getParameter(BasicOptionItemData.OPTION_ONE_TITLE);
      this.defaultOptionItem = request.getParameter(BasicOptionItemData.DEFAULT_OPTION_ITEM);
      this.defaultOptionValue = request.getParameter(BasicOptionItemData.DEFAULT_OPTION_VALUE);
           
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_ONE_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_ONE_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_TWO_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_TWO_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_THREE_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_THREE_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_FOUR_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_FOUR_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_FIVE_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_FIVE_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_SIX_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_SIX_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_SEVEN_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_SEVEN_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_EIGHT_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_EIGHT_VALUE));
      this.optionItem.add(request.getParameter(BasicOptionItemData.OPTION_ONE_NINE_ITEM));
      this.optionValue.add(request.getParameter(BasicOptionItemData.OPTION_ONE_NINE_VALUE));
      
      this.timeEntered = request.getParameter(EntryData.getInstance().TIMECREATED);
      this.lastModified = request.getParameter(EntryData.getInstance().LASTMODIFIED);
   }

   private HashMap getHashMap()
   {
      HashMap values = new HashMap();            
      
      values.put(BasicItemData.ID,id);
      
      values.put(BasicOptionItemData.OPTION_ONE_ONE_ITEM,this.optionItem.get(0));
      values.put(BasicOptionItemData.OPTION_ONE_ONE_VALUE,this.optionValue.get(0));
      values.put(BasicOptionItemData.OPTION_ONE_TWO_ITEM,this.optionItem.get(1));
      values.put(BasicOptionItemData.OPTION_ONE_TWO_VALUE,this.optionValue.get(1));
      values.put(BasicOptionItemData.OPTION_ONE_THREE_ITEM,this.optionItem.get(2));
      values.put(BasicOptionItemData.OPTION_ONE_THREE_VALUE,this.optionValue.get(2));
      values.put(BasicOptionItemData.OPTION_ONE_FOUR_ITEM,this.optionItem.get(3));
      values.put(BasicOptionItemData.OPTION_ONE_FOUR_VALUE,this.optionValue.get(3));
      values.put(BasicOptionItemData.OPTION_ONE_FIVE_ITEM,this.optionItem.get(4));
      values.put(BasicOptionItemData.OPTION_ONE_FIVE_VALUE,this.optionValue.get(4));
      values.put(BasicOptionItemData.OPTION_ONE_SIX_ITEM,this.optionItem.get(5));
      values.put(BasicOptionItemData.OPTION_ONE_SIX_VALUE,this.optionValue.get(5));
      values.put(BasicOptionItemData.OPTION_ONE_SEVEN_ITEM,this.optionItem.get(6));
      values.put(BasicOptionItemData.OPTION_ONE_SEVEN_VALUE,this.optionValue.get(6));
      values.put(BasicOptionItemData.OPTION_ONE_EIGHT_ITEM,this.optionItem.get(7));
      values.put(BasicOptionItemData.OPTION_ONE_EIGHT_VALUE,this.optionValue.get(7));
      values.put(BasicOptionItemData.OPTION_ONE_NINE_ITEM,this.optionItem.get(8));
      values.put(BasicOptionItemData.OPTION_ONE_NINE_VALUE,this.optionValue.get(8));
      
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
                  
         values.add(id);
      
         for(int index = 0; index < this.optionValue.size(); index++)
         {
            values.add(this.optionValue.get(index));
            values.add(this.optionItem.get(index));
         }

         values.add(time);
         values.add(time);
         
         BasicOptionItemsEntityFactory.getInstance().getBasicOptionItemsEntityInstance().insert(values);
         
         String success = "Successfully inserted " + id + " into items table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to insert " + id + " into items table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"inserts()",e));
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         BasicOptionItemsEntityFactory.getInstance().getBasicOptionItemsEntityInstance().delete(id);
         
         String success = "Successfully deleted";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"delete()",e));
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         String success = "Update Pricing Successful";
         
         HashMap values = this.getHashMap();
         BasicOptionItemsEntityFactory.getInstance().getBasicOptionItemsEntityInstance().update(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(id + " " + success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update: " + id;
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"update()",e));
         }
         return error;
      }
   }
   
}
