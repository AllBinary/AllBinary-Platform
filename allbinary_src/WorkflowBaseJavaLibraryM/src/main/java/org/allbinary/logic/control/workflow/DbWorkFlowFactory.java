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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.DynamicObjectData;

import java.util.HashMap;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class DbWorkFlowFactory
{
    private static final DbWorkFlowFactory instance = new DbWorkFlowFactory();

    public static DbWorkFlowFactory getInstance() {
        return instance;
    }
    
   private DbWorkFlowFactory()
   {
   }
   
   //Created from DB data
   public WorkFlowInterface getInstance(final AbeClientInformationInterface abeClientInformation, final HashMap hashMap) throws Exception, LicensingException
   {
      try
      {
         final String className = (String) hashMap.get(DynamicObjectData.NAME);
         //final String workFlowName = (String) hashMap.get(WorkFlowData.getInstance().NAME);
         
         final Object params[] = new Object[1];
         final Class classes[] = new Class[1];
         
         //Add param types
         classes[0] = hashMap.getClass();
         //pageContext.getClass();
         
         //Add arguments
         params[0] = (Object) hashMap;

         final Object object = AbeFactory.getInstance().getInstance(abeClientInformation, className, classes, params);
         return (WorkFlowInterface) new WorkFlowWrapper(object);
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
        	 String error = "Failed To Get Instance Args: HashMap=" + hashMap.toString();
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance(HashMap)",e));
         }
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
        	 String error = "Failed To Get Instance";
        	 
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance(HashMap)",e));
         }
         throw e;
      }
   }
}
