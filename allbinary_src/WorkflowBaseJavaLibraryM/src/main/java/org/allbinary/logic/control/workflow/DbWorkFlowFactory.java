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
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.DynamicObjectData;

import java.util.HashMap;

public class DbWorkFlowFactory
{
    private static final DbWorkFlowFactory instance = new DbWorkFlowFactory();

   private DbWorkFlowFactory()
   {
   }
   
   //Created from DB data
   public static WorkFlowInterface getInstance(HashMap hashMap) throws Exception, LicensingException
   {
      try
      {
         String className = (String) hashMap.get(DynamicObjectData.NAME);
         String workFlowName = (String) hashMap.get(WorkFlowData.getInstance().NAME);
         
         Object params[] = new Object[1];
         Class classes[] = new Class[1];
         
         //Add param types
         classes[0] = hashMap.getClass();
         //pageContext.getClass();
         
         //Add arguments
         params[0] = (Object) hashMap;

         Object object = AbeFactory.getInstance(className, classes, params);
         return (WorkFlowInterface) new WorkFlowWrapper(object);
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
        	 String error = "Failed To Get Instance Args: HashMap=" + hashMap.toString();
            LogUtil.put(LogFactory.getInstance(error, instance, "getInstance(HashMap)",e));
         }
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
        	 String error = "Failed To Get Instance";
        	 
            LogUtil.put(LogFactory.getInstance(error, instance, "getInstance(HashMap)",e));
         }
         throw e;
      }
   }
}
