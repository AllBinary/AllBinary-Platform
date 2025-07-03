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
package org.allbinary.logic.control.workflow;

import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import java.lang.reflect.Method;
import org.allbinary.string.CommonStrings;

public class WorkFlowWrapper implements WorkFlowInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private Object object;
   
   public WorkFlowWrapper(Object object)
   {
      this.object = object;
   }

   protected Object getWorkFlowObject()
   {
      return this.object;
   }
   
   public String getName() throws Exception
   {
      try
      {
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("getName",null);
         String result = (String) method.invoke(this.object,null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Reflection Exception";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getName()", e));
         }
         throw new Exception(error);
      }
   }
   
   public String getStoreName() throws Exception
   {
      try
      {
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod("getStoreName",null);
         String result = (String) method.invoke(this.object,null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Reflection Exception";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getStoreName()", e));
         }
         throw new Exception(error);
      }
   }
      
   public Integer process() throws Exception
   {
      try
      {
         Class helperClass = object.getClass();
         Method method = helperClass.getMethod(commonStrings.PROCESS,null);
         Integer result = (Integer) method.invoke(this.object,null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Reflection Exception";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e));
         }
         throw new Exception(error);
      }
   }
}
