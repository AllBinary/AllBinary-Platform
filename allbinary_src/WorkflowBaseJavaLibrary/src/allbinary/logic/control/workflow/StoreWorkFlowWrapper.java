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
package allbinary.logic.control.workflow;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import java.lang.reflect.Method;

public class StoreWorkFlowWrapper extends WorkFlowWrapper
{
   public StoreWorkFlowWrapper(Object object)
   {
      super(object);
   }
   
   public String getStoreName() throws Exception
   {
      try
      {
         Class helperClass = this.getWorkFlowObject().getClass();
         Method method = helperClass.getMethod("getStoreName",null);
         String result = (String) method.invoke(this.getWorkFlowObject(),null);
         return result;
      }
      catch(Exception e)
      {
         String error = "Reflection Exception";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"getStoreName()",e));
         }
         throw new Exception(error);
      }
   }
}
