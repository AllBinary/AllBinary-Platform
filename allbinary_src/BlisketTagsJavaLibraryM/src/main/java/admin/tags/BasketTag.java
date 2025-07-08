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
package admin.tags;

import java.lang.reflect.Method;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.business.user.commerce.inventory.basket.BasketData;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

import org.allbinary.logic.communication.log.LogUtil;

import java.util.HashMap;
import javax.servlet.jsp.JspTagException;

import admin.taghelpers.BasketHelperFactory;
import admin.taghelpers.BasketRequestHelperFactory;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.communication.log.LogFactory;

import tags.CustomTagSupport;

public class BasketTag extends CustomTagSupport
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   private String command;
   private String storeName;   
   
   private HashMap propertiesHashMap;
      
   public BasketTag()
   {
   }
   
   public void setCommand(String command)
   {
      this.command=command;
   }
   
   public void setStoreName(String value)
   {
      this.storeName=value;
   }
   
   private boolean isBasketEmpty() throws LicensingException
   {
      try
      {
         Object object = new BasketHelperFactory().getInstance(
            this.propertiesHashMap, this.pageContext);
         
         Method method = object.getClass().getMethod("isBasketEmpty", null);
         Boolean emptyBoolean = (Boolean) method.invoke(object, null);         
         return emptyBoolean.booleanValue();
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"isBasketEmpty()",e);
         }
         return true;
      }
   }

   private boolean addItemToBasket() throws LicensingException
   {
      try
      {
         Object object = new BasketRequestHelperFactory().getInstance(
            this.propertiesHashMap, this.pageContext);

         Method method = object.getClass().getMethod("addItemToBasket", null);
         Boolean emptyBoolean = (Boolean) method.invoke(object, null);         
         return emptyBoolean.booleanValue();
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to add item from Basket";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"addItemToBasket()",e);
         }
         return false;
      }
   }
   
   private boolean removeItemFromBasket() throws LicensingException
   {
      try
      {
         Object object = new BasketRequestHelperFactory().getInstance(
            this.propertiesHashMap, this.pageContext);

         Method method = object.getClass().getMethod("removeItemFromBasket", null);
         Boolean emptyBoolean = (Boolean) method.invoke(object, null);         
         return emptyBoolean.booleanValue();
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to remove item from Basket";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"removeItemFromBasket()",e);
         }
         return false;
      }
   }
   
   private boolean adjustBasket() throws LicensingException
   {
      try
      {
         Object object = new BasketRequestHelperFactory().getInstance(
            this.propertiesHashMap, this.pageContext);
         
         Method method = object.getClass().getMethod("adjustBasket", null);
         Boolean emptyBoolean = (Boolean) method.invoke(object, null);         
         return emptyBoolean.booleanValue();
      }
      catch(LicensingException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed to Adjust Basket";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"adjustBasket()",e);
         }
         return false;
      }
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {       
         if(command!=null)
         {
            this.propertiesHashMap = new HashMap();
            this.propertiesHashMap.put(StoreFrontData.getInstance().NAME,this.storeName);
            
            if (command.compareTo(BasketData.INSERT)==0)
            {
               if(!this.addItemToBasket())
               {
                  this.pageContext.getOut().println("Item is not currently being sold.<p/>");
               }
            }
            else
               if (command.compareTo(BasketData.DELETE)==0)
               {
                  this.removeItemFromBasket();
               }
               else
                  if (command.compareTo(BasketData.ADJUST)==0)
                  {
                     this.adjustBasket();
                  }
               else
                  if (command.compareTo(BasketData.ISEMPTY)==0)
                  {
                     if(this.isBasketEmpty())
                        return this.EVAL_BODY_INCLUDE;
                     else return SKIP_BODY;
                  }
         }
         return SKIP_BODY;
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
