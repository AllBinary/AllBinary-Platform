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
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntity;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntityFactory;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;

public class BillingAddressHelper implements TableInterface
{
   private WeblisketSession weblisketSession;
   private HttpServletRequest request;
   
   private String userName;   
   private StreetAddress streetAddress;
   
   private final Portion portion;
   
   public BillingAddressHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      
      this.weblisketSession = 
         new WeblisketSession(hashMap, pageContext);

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
            
      this.streetAddress = new StreetAddress(this.request);
   }
   
   public String drop()
   {
      try
      {
         String success = new BillingAddressesEntity("").drop();
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
         String success = new BillingAddressesEntity("").createTable();
         
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
         final String success = "Restore Successful";
         
         final BillingAddressesEntity billingAddressesEntity = 
        	 new BillingAddressesEntity(StringUtil.getInstance().EMPTY_STRING);

         final String result = AbSqlTableUtil.getInstance().restoreTable(billingAddressesEntity, this.portion);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "restore()", e));
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         final String success = "Backup Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(new BillingAddressesEntity(StringUtil.getInstance().EMPTY_STRING));
         
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
         String success = "Successfully Added Billing Address";
         BillingAddressesEntityFactory.getInstance().getInstance(
         this.weblisketSession.getUserName()).add(this.streetAddress);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"add()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"add()",e));
         }
         return error;
      }
      
   }
   
   public String update()
   {
      try
      {
         String success = "Successfully Updated Billing Address";
         BillingAddressesEntityFactory.getInstance().getInstance(
         this.weblisketSession.getUserName()).update(this.streetAddress);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successfull update of a user Billing Address table",this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed update of a User Billing Address Table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
   
   private String setToShippingAddress()
   {
      try
      {
          ShippingAddressesEntityFactory shippingAddressesEntityFactory = 
              ShippingAddressesEntityFactory.getInstance();

         StreetAddress streetAddress =
         shippingAddressesEntityFactory.getInstance(
         this.weblisketSession.getUserName()).getDefault();
         
         if(streetAddress!=null)
         {
            shippingAddressesEntityFactory.getInstance(
            this.weblisketSession.getUserName()).add(streetAddress);
         }
         else
         {
            return "No Shipping Address";
         }
         return StringUtil.getInstance().EMPTY_STRING;
         
      }
      catch(Exception e)
      {
         String error = "Failed to Set Billing address to Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setToShippingAddress()",e));
         }
         return error;
      }
      
   }

   public String delete()
   {
      try
      {
         String success = "Successfully Removed Billing Address";
         
         BillingAddressesEntityFactory.getInstance().getInstance(
            this.weblisketSession.getUserName()
               ).remove(new Integer(this.streetAddress.getId()));
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove Billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
        	 LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
         }
         return error;
      }
      
   }
 
   public String set()
   {
      try
      {
         String success = "Successfully Set Billing Address";
         BillingAddressesEntityFactory.getInstance().getInstance(
            this.weblisketSession.getUserName()).setDefault(this.streetAddress.getId());
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {            
            LogUtil.put(LogFactory.getInstance(success,this,"set()"));
         }
         return success;         
      }
      catch(Exception e)
      {
         String error = "Failed to set billing Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"set()",e));
         }
         return error;
      }
   }

}
