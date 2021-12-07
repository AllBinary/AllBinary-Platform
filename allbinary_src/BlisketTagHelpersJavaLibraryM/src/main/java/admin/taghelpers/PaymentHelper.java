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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.user.commerce.money.payment.PaymentFactory;
import org.allbinary.business.user.commerce.money.payment.PaymentInterface;
import org.allbinary.data.tables.user.commerce.money.payment.PaymentEntity;
import org.allbinary.data.tables.user.commerce.money.payment.PaymentEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;

public class PaymentHelper implements TableInterface
{
   private WeblisketSession weblisketSession;
   
   private HttpServletRequest request;   
   private String value;
   
   private final Portion portion;
   
   public PaymentHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      
      this.weblisketSession = 
         new WeblisketSession(hashMap, pageContext);
      
      this.portion = new Portion(hashMap);
      
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.value = request.getParameter("VALUE"); 
   }      
      
   public String insert()
   {
      try
      {
         String success = "Successfully Added Payment";

         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();
         
         PaymentInterface paymentInterface = PaymentFactory.getInstance(request);
         paymentEntity.add(
            this.weblisketSession.getUserName(),paymentInterface);
         paymentEntity.setDefault(
            this.weblisketSession.getUserName(),
            new Integer(paymentEntity.getLastId(
            this.weblisketSession.getUserName())));
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"addPayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Payment";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"addPayment()",e));
         }
         return error;
      }
      
   }
   
   public String select()
   {
      try
      {
         String success = "Successfully Selected Payment";

         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         paymentEntity.setDefault(
            this.weblisketSession.getUserName(),new Integer(this.value));
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"selectPayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to select Payment";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"selectPayment()",e));
         }
         return error;
      }
   }
   
   public String delete()
   {
      try
      {
         String success = "Successfully Removed Payment";         

         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         paymentEntity.remove(
            this.weblisketSession.getUserName(),new Integer(this.value));
         PaymentInterface paymentInterface = 
           paymentEntity.getDefault(
              this.weblisketSession.getUserName());
         
         if(paymentInterface == null)
         {
            paymentEntity.setDefault(
            this.weblisketSession.getUserName(),
            new Integer(paymentEntity.getLastId(
            this.weblisketSession.getUserName())));
         }
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"removePayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove Payment";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"removePayment()",e));
         }         
         return error;
      }
   }

   public String drop()
   {
      try
      {
         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         String success = paymentEntity.dropTable();
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
         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         String success = paymentEntity.createTable();
         
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
         final String result = AbSqlTableUtil.getInstance().restoreTable(PaymentEntityFactory.getInstance().getPaymentEntityInstance(), portion);
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
         final String success = "Backup Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(PaymentEntityFactory.getInstance().getPaymentEntityInstance());
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
   
   public String update() throws LicensingException
   {
      return "Not Implemented";
   }
   
}
