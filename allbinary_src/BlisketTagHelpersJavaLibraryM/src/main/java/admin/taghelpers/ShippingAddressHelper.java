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

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntityFactory;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;

public class ShippingAddressHelper implements BasicTableInterface
{
   private WeblisketSession weblisketSession;
   private HttpServletRequest request;

   private String userName;   
   private StreetAddress streetAddress;      
   
   private final Portion portion;
   
   public ShippingAddressHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      
      this.weblisketSession = new WeblisketSession(hashMap, pageContext);
   
      this.portion = new Portion(hashMap);
      
      this.getAddressForm();            
   }
   
   private void getAddressForm()
   {
      this.userName = request.getParameter(UserData.USERNAME);
      
      if(this.userName==null)
      {
         this.userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
      }
      this.streetAddress = new StreetAddress(request);
   }
   
   public String drop()
   {
      try
      {
         String success = new ShippingAddressesEntity("").drop();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"drop()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop Admin table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"drop()",e));
         }
         return error;
      }
   }
   
   public String create()
   {
      try
      {
         String success = new ShippingAddressesEntity("").createTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"create()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create user table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"create()",e));
         }
         return error;
      }
   }
   
   public String restore()
   {
      try
      {
         String success = "Restore Successful";
         String result = new ShippingAddressesEntity(StringUtil.getInstance().EMPTY_STRING).restoreTable(this.portion);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"restore()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"restore()",e));
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         String success = "Restore Successful";
         String result = new ShippingAddressesEntity("").backupTable();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"backup()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"backup()",e));
         }
         return error;
      }
   }
   
   public String insert()
   {
      try
      {
         String success = "Successfully Added Shipping Address";
         ShippingAddressesEntityFactory.getInstance().getInstance(
         this.weblisketSession.getUserName()).add(this.streetAddress);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Shipping streetAddress";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"insert",e));
         }
         return error;
      }
      
   }
   
   public String update()
   {
      try
      {
         String success = "Successfully Updated Shipping Address";
         
         ShippingAddressesEntityFactory.getInstance().getInstance(
         this.weblisketSession.getUserName()).update(this.streetAddress);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successfull update of a Users Shipping Address table",this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed update of a Users Shipping Address Table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update",e));
         }
         return error;
      }
   }
   
   private String setToBillingAddress()
   {
      try
      {      
         StreetAddress streetAddress =
         BillingAddressesEntityFactory.getInstance().getInstance(
         this.weblisketSession.getUserName()).getDefault();
         if(streetAddress!=null)
         {
            ShippingAddressesEntityFactory.getInstance().getInstance(
            this.weblisketSession.getUserName()).add(streetAddress);
         }
         else
         {
            return "No Billing Address";
         }
         return StringUtil.getInstance().EMPTY_STRING;
      }
      catch(Exception e)
      {
         String error = "Failed Setting Shipping address to Billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setToBillingAddress()",e));
         }
         return error;
      }
   }

   public String delete()
   {
      try
      {
         String success = "Successfully Removed Shipping Address";
         ShippingAddressesEntityFactory.getInstance().getInstance(
            this.weblisketSession.getUserName()
               ).remove(new Integer(this.streetAddress.getId()));
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"remove()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"remove()",e));
         }
         return error;
      }
      
   }
   
   public String set()
   {
      try
      {
         String success = "Successfully Set Shipping Address";
         ShippingAddressesEntityFactory.getInstance().getInstance(
            this.weblisketSession.getUserName()
               ).setDefault(this.streetAddress.getId());
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {            
            LogUtil.put(LogFactory.getInstance(success,this,"set()"));
         }
         return success;         
      }
      catch(Exception e)
      {
         String error = "Failed to set Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"set()",e));
         }
         return error;
      }
   }
}
