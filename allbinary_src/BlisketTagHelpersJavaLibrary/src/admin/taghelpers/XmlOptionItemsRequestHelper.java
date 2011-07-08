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

import abcs.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.communication.log.LogUtil;

import allbinary.business.entry.EntryData;

import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.business.user.commerce.inventory.item.option.XmlOptionItemData;

import allbinary.data.tables.user.commerce.inventory.item.options.XmlOptionItemsEntityFactory;

public class XmlOptionItemsRequestHelper implements ModifyTableInterface
{
   private HttpServletRequest request;
     
   private String id;
   private String xmlFileStatus;
   private String optionXmlFile;
      
   private String timeEntered;
   private String lastModified;   
   
   public XmlOptionItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   public void getFormData()
   {
      this.id = request.getParameter(BasicItemData.ID);
      
      this.xmlFileStatus = request.getParameter(XmlOptionItemData.XML_FILE_STATUS);
      this.optionXmlFile = request.getParameter(XmlOptionItemData.OPTION_XML_FILE);
      
      this.timeEntered = request.getParameter(EntryData.getInstance().TIMECREATED);
      this.lastModified = request.getParameter(EntryData.getInstance().LASTMODIFIED);
   }

   private HashMap getHashMap()
   {
      HashMap values = new HashMap();            
      
      values.put(BasicItemData.ID,this.id);
      
      values.put(XmlOptionItemData.XML_FILE_STATUS, this.xmlFileStatus);
      values.put(XmlOptionItemData.OPTION_XML_FILE, this.optionXmlFile);
      
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
         values.add(this.xmlFileStatus);
         values.add(this.optionXmlFile);
         
         values.add(time);
         values.add(time);
         
         XmlOptionItemsEntityFactory.getInstance().getXmlOptionItemsEntityInstance().insert(values);
         
         String success = "Successfully inserted " + id + " into items table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to insert " + id + " into items table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"inserts()",e));
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         XmlOptionItemsEntityFactory.getInstance().getXmlOptionItemsEntityInstance().delete(id);
         
         String success = "Successfully deleted";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
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
         XmlOptionItemsEntityFactory.getInstance().getXmlOptionItemsEntityInstance().update(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(id + " " + success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update: " + id;
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
   
}
