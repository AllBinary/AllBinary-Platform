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
package tags.generic.inventory;

import java.lang.reflect.Method;

import abcs.logic.communication.log.LogUtil;

import abcs.logic.system.security.licensing.LicensingException;

import taghelpers.InventoryHelperFactory;

import tags.StoreValidationTransformTag;

import abcs.logic.communication.http.request.AbResponseHandler;
import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.JspTagException;

public class InventoryTag extends StoreValidationTransformTag
{
   //private String id;
   
   public InventoryTag()
   {
       super();
   }
   
   private void search() throws LicensingException
   {
      try
      {
         Object object =
         new InventoryHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class addressHelperClass = object.getClass();
         
         Method method = addressHelperClass.getMethod("search", null);
         String result = (String) method.invoke(object, null);
         pageContext.getOut().print(result);
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to search Inventory by keyword";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"search()",e));
         }
         try
         {
            pageContext.getOut().print(error + "<br>");
            pageContext.getOut().print("Exception: " + e + "<br>");
         }
         catch(Exception e2)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            {
               LogUtil.put(LogFactory.getInstance("Exception in Exception Handling",this,"search()",e));
            }
         }
      }
   }
   
   private void viewSummary() throws LicensingException
   {
      try
      {
         Object object =
         new InventoryHelperFactory().getInstance(
         this.getPropertiesHashMap(), this.pageContext);
         
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("viewSummary", null);
         String result = (String) method.invoke(object, null);
         pageContext.getOut().print(result);
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to View Summary";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"viewSummary()",e));
         }
         try
         {
            
            pageContext.getOut().print(error + "<br>");
            pageContext.getOut().print("Exception: " + e + "<br>");
         }
         catch(Exception e2)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            {
               LogUtil.put(LogFactory.getInstance("Exception in Exception Handling",this,"viewSummary())",e));
            }
         }
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            //this.propertiesHashMap.put(BasicItemData.ID, this.id);
            
            if (this.getCommand().compareTo(allbinary.globals.GLOBALS.SEARCH)==0)
            {
               this.setName("Basic Search Inventory View");
               this.setObjectFile("views.generic.inventory.AutoSearchValidationInventoryView");
               this.search();
               return EVAL_BODY_INCLUDE;
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEWSUMMARY)==0)
               {
                  this.setName("Basic Inventory View");
                  this.setObjectFile("views.generic.inventory.AutoValidationInventoryView");

                  this.viewSummary();
                  return EVAL_BODY_INCLUDE;
               }
               else
                  if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
                  {
                     this.setName("Basic Inventory View");
                     this.setObjectFile("views.generic.inventory.AutoValidationInventoryView");

                     return super.doStartTag();
                  }
                  else
                  {
                     throw new Exception("No Such View Command: " + this.getCommand());
                  }
         }
         throw new Exception("Command Null");
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
   
}
