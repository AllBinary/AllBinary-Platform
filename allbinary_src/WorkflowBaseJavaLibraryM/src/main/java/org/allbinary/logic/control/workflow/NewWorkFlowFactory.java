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

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class NewWorkFlowFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final NewWorkFlowFactory instance = new NewWorkFlowFactory();

    public static NewWorkFlowFactory getInstance() {
        return instance;
    }

   private NewWorkFlowFactory()
   {
   }
   
   //Assumes store name in session - for store admin
   //Used for creating and/or updating a workflow
   //hashMap is for Tag properties that may override request data
   public WorkFlowInterface getInstance(final AbeClientInformationInterface abeClientInformation, 
       final HashMap hashMap, final PageContext pageContext) throws Exception, LicensingException
   {
      try
      {
         final String className = (String) hashMap.get(DynamicObjectData.NAME);
         //final String workFlowName = (String) hashMap.get(WorkFlowData.getInstance().NAME);
         
         final Object params[] = new Object[2];
         final Class classes[] = new Class[2];
         
         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = AbeFactory.getInstance().getClass(abeClientInformation, "javax.servlet.jsp.PageContext");
         //pageContext.getClass();
         
         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) pageContext;
         
         final Object object = AbeFactory.getInstance().getInstance(abeClientInformation, className, classes, params);
         
         return (WorkFlowInterface) new WorkFlowWrapper(object);
      }
      catch(LicensingException e)
      {
         //String error = "Failed To Get Instance Args: HashMap=" + hashMap.toString();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(HashMap)",e);
         }
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(HashMap)", e);
         }
         throw e;
      }
   }
 
   //For creating a new empty workflow
   public WorkFlowInterface getInstance(final AbeClientInformationInterface abeClientInformation, final String className) throws Exception, LicensingException
   {
      try
      {
         final StringUtil stringUtil = StringUtil.getInstance();
         if(className != null && className.compareTo(stringUtil.EMPTY_STRING)!=0)
         {
            final Object object = AbeFactory.getInstance().getInstance(abeClientInformation, className, null, null);
            return (WorkFlowInterface) new WorkFlowWrapper(object);
         }
         else throw new Exception("No WorkFlow ClassName");
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         throw e;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
         }
         throw e;
      }
   }
}
