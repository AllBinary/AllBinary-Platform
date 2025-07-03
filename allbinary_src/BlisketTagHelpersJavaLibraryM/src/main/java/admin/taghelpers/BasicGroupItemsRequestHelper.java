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

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.group.BasicGroupItemData;

import org.allbinary.data.tables.user.commerce.inventory.item.groups.BasicGroupItemsEntityFactory;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class BasicGroupItemsRequestHelper extends ModifyTable
{
     
   private HttpServletRequest request;
        
   private String id;
   
   private String itemOne;
   private String itemTwo;
   private String itemThree;
   private String itemFour;
   private String itemFive;
   private String itemSix;
   private String itemSeven;
   private String itemEight;
   private String itemNine;
   private String itemTen;
   
   private String timeEntered;
   private String lastModified;
   
   public BasicGroupItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   public void getFormData()
   {                  
      this.id = request.getParameter(BasicItemData.ID);
      
      this.itemOne = request.getParameter(BasicGroupItemData.ITEM_ONE);
      this.itemTwo = request.getParameter(BasicGroupItemData.ITEM_TWO);
      this.itemThree = request.getParameter(BasicGroupItemData.ITEM_THREE);
      this.itemFour = request.getParameter(BasicGroupItemData.ITEM_FOUR);
      this.itemFive = request.getParameter(BasicGroupItemData.ITEM_FIVE);
      this.itemSix = request.getParameter(BasicGroupItemData.ITEM_SIX);
      this.itemSeven = request.getParameter(BasicGroupItemData.ITEM_SEVEN);
      this.itemEight = request.getParameter(BasicGroupItemData.ITEM_EIGHT);
      this.itemNine = request.getParameter(BasicGroupItemData.ITEM_NINE);
      this.itemTen = request.getParameter(BasicGroupItemData.ITEM_TEN);
            
      this.timeEntered = request.getParameter(EntryData.getInstance().TIMECREATED);
      this.lastModified = request.getParameter(EntryData.getInstance().LASTMODIFIED);
   }

   private HashMap getHashMap()
   {
      HashMap values = new HashMap();            
      
      values.put(BasicItemData.ID,id);
      
      values.put(BasicGroupItemData.ITEM_ONE,this.itemOne);
      values.put(BasicGroupItemData.ITEM_TWO,this.itemTwo);
      values.put(BasicGroupItemData.ITEM_THREE,this.itemThree);
      values.put(BasicGroupItemData.ITEM_FOUR,this.itemFour);
      values.put(BasicGroupItemData.ITEM_FIVE,this.itemFive);
      values.put(BasicGroupItemData.ITEM_SIX,this.itemSix);
      values.put(BasicGroupItemData.ITEM_SEVEN,this.itemSeven);
      values.put(BasicGroupItemData.ITEM_EIGHT,this.itemEight);
      values.put(BasicGroupItemData.ITEM_NINE,this.itemNine);
      values.put(BasicGroupItemData.ITEM_TEN,this.itemTen);      
      
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
         
         values.add(this.itemOne);
         values.add(this.itemTwo);
         values.add(this.itemThree);
         values.add(this.itemFour);
         values.add(this.itemFive);
         values.add(this.itemSix);
         values.add(this.itemSeven);         
         values.add(this.itemEight);
         values.add(this.itemNine);
         values.add(this.itemTen);
         
         values.add(time);
         values.add(time);
         
         BasicGroupItemsEntityFactory.getInstance().getBasicGroupItemsEntityInstance().insert(values);
         
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
         BasicGroupItemsEntityFactory.getInstance().getBasicGroupItemsEntityInstance().delete(id);
         
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
         BasicGroupItemsEntityFactory.getInstance().getBasicGroupItemsEntityInstance().update(values);
         
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
