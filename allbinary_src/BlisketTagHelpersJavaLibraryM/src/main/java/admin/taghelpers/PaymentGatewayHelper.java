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
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterfaceFactory;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import org.allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;

public class PaymentGatewayHelper implements BasicTableInterface
{
   private WeblisketSession weblisketSession;

   private final HttpServletRequest httpServletRequest;
   
   private final Portion portion;
   
   public PaymentGatewayHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      try
      {
         this.weblisketSession = new WeblisketSession(hashMap, pageContext);

         httpServletRequest =
            (HttpServletRequest) pageContext.getRequest();
         
         this.portion = new Portion(hashMap);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed", this, "Constructor()", e));
         }
         throw e;
      }
   }
   
   public String insert()
   {
      try
      {
         PaymentGatewayInterface paymentGatewayInterface = 
            new PaymentGatewayInterfaceFactory().getInstance(httpServletRequest);
         paymentGatewayInterface.setStoreName(
            this.weblisketSession.getStoreName());
         
         PaymentGatewayEntityFactory.getInstance().add(
            paymentGatewayInterface);

         String success = "Successfully Added New Payment Gateway";

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         
         return success + "<br/>";
      }
      catch(Exception e)
      {
         String error = "Failed to add";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "add()", e));
         }
         return error + "<br>" + "Exception: " + e + "<br>";
      }
   }
   
   public String update()
   {
      try
      {
         PaymentGatewayInterface paymentGatewayInterface = 
            new PaymentGatewayInterfaceFactory().getInstance(httpServletRequest);
         paymentGatewayInterface.setStoreName(
            this.weblisketSession.getStoreName());
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
        	 StringBuffer stringBuffer = new StringBuffer();
        	 
        	 stringBuffer.append("Gateway Name: ");
        	 stringBuffer.append(paymentGatewayInterface.getName());
        	 stringBuffer.append(" HashMap=");
        	 stringBuffer.append(paymentGatewayInterface.toHashMap(true));

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "update()"));
         }
         
         PaymentGatewayEntityFactory.getInstance().update(
            paymentGatewayInterface);
         
         String success = "Successfully Updated Payment Gateway information";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "update()"));
         }
         return success + "<br/>";
      }
      catch(Exception e)
      {
         String error = "Failed to update payment gateway information";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "update()", e));
         }
         return error + "<br/>" + "Exception: " + e + "<br/>";
      }
   }
   
   public String delete()
   {
      try
      {
         PaymentGatewayInterface paymentGatewayInterface = 
            new PaymentGatewayInterfaceFactory().getInstance(httpServletRequest);
         paymentGatewayInterface.setStoreName(
            this.weblisketSession.getStoreName());
         
         String storeName = paymentGatewayInterface.getStoreName();
         String gatewayName = paymentGatewayInterface.getName();
         
         PaymentGatewayEntityFactory.getInstance().remove(
            storeName, BasicPaymentTypeUtil.getInstance().get(gatewayName));

    	 StringBuffer stringBuffer = new StringBuffer();
    	 
    	 stringBuffer.append("Successfully Removed payment gateway where store name=");
    	 stringBuffer.append(storeName);
    	 stringBuffer.append(" and gateway name=");
    	 stringBuffer.append(gatewayName);

         String success = stringBuffer.toString();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         return success + "<br/>";
      }
      catch(Exception e)
      {
         String error = "Failed to remove payment gateway from table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"remove()",e));
         }
         return error + "<br/>" + "Exception: " + e + "<br>";
      }
   }
   
   public String drop()
   {
      try
      {
         String success = PaymentGatewayEntityFactory.getInstance().dropTable();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"drop()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop payment transaction result table";
         
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
         String success = PaymentGatewayEntityFactory.getInstance().createTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"create()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create payment transaction result table";
         
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
         String result = PaymentGatewayEntityFactory.getInstance().restoreTable(this.portion);
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
         String result = PaymentGatewayEntityFactory.getInstance().backupTable();
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
}
