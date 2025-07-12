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
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntityFactory;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.http.request.session.WeblisketSessionData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;
import org.allbinary.logic.string.StringUtil;

public class ShippingAddressHelper extends BasicTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final StringUtil stringUtil = StringUtil.getInstance();
    
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
         String success = new ShippingAddressesEntity(stringUtil.EMPTY_STRING).drop();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,commonStrings.DROP);
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop Admin table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.DROP,e);
         }
         return error;
      }
   }
   
   public String create()
   {
      try
      {
         String success = new ShippingAddressesEntity(stringUtil.EMPTY_STRING).createTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"create()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create user table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"create()",e);
         }
         return error;
      }
   }
   
   public String restore()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().restoreTable(new ShippingAddressesEntity(StringUtil.getInstance().EMPTY_STRING), portion);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"restore()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"restore()",e);
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         final String success = "Backup Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(new ShippingAddressesEntity(StringUtil.getInstance().EMPTY_STRING));
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"backup()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"backup()",e);
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"insert");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Shipping streetAddress";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"insert",e);
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("Successfull update of a Users Shipping Address table",this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed update of a Users Shipping Address Table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update",e);
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"setToBillingAddress()",e);
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"remove()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"remove()",e);
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {            
            logUtil.put(success,this,"set()");
         }
         return success;         
      }
      catch(Exception e)
      {
         String error = "Failed to set Shipping Address";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"set()",e);
         }
         return error;
      }
   }
}
