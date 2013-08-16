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

import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.business.user.commerce.money.payment.PaymentFactory;
import allbinary.business.user.commerce.money.payment.PaymentInterface;
import allbinary.data.tables.user.commerce.money.payment.PaymentEntity;
import allbinary.data.tables.user.commerce.money.payment.PaymentEntityFactory;
import allbinary.logic.communication.http.request.session.WeblisketSession;

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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"addPayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add Payment";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"selectPayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to select Payment";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"removePayment()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to remove Payment";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"drop()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop Admin table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"create()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create user table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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

         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         String result = paymentEntity.restoreTable(this.portion);
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"restore()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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

         PaymentEntity paymentEntity =
             PaymentEntityFactory.getInstance().getPaymentEntityInstance();

         String result = paymentEntity.backupTable();
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"backup()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
