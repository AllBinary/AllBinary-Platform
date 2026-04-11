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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonStrings;

public class HelperFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   public HelperFactory()
   {
   }

   public static Object getInstance(
       final AbeClientInformationInterface abeClientInformation,
       final String factoryName, final String className,
       final HashMap hashMap, final HttpServletRequest httpServletRequest)
      throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         Object[] params = new Object[2];
         Class[] classes = new Class[2];

         final AbeFactory abeFactory = AbeFactory.getInstance();

         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = abeFactory.getClass(abeClientInformation, "javax.servlet.http.HttpServletRequest");
         //pageContext.getClass();

         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) httpServletRequest;

         Object object = abeFactory.getInstance(abeClientInformation, className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {
         String error = "Failed To Get Instance Args: HashMap=" +
            hashMap.toString() + " HttpServletRequest=" + httpServletRequest;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            logUtil.put(error, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e);
         }
         throw e;
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            logUtil.put(error,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e);
         }
         return null;
      }
   }

   public static Object getInstance(
       final AbeClientInformationInterface abeClientInformation,
       final String factoryName, final String className, 
       final HashMap hashMap, final PageContext pageContext) 
      throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         Object[] params = new Object[2];
         Class[] classes = new Class[2];

         final AbeFactory abeFactory = AbeFactory.getInstance();
         
         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = abeFactory.getClass(abeClientInformation, "javax.servlet.jsp.PageContext");
         //pageContext.getClass();         
         
         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) pageContext;
                           
         Object object = abeFactory.getInstance(abeClientInformation, className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             String error = "Failed To Get Instance Args: HashMap=" +
                 hashMap.toString() + " PageContext=" + pageContext;

            logUtil.put(error, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e);
         }
         throw e;
      }      
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
             logUtil.put(commonStrings.EXCEPTION,factoryName + "->HelperFactory",
                 "getInstance(String, String, HashMap, PageContext)", e);
         }
         return null;
      }
   }     

   public static Object getInstance(
       final AbeClientInformationInterface abeClientInformation,
       final String factoryName, final String className, 
       final HashMap hashMap, final HashMap specialhashMap, final PageContext pageContext) 
      throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         Object[] params = new Object[3];
         Class[] classes = new Class[3];

         final AbeFactory abeFactory = AbeFactory.getInstance();
         
         //Add param types
         classes[0] = hashMap.getClass();
         classes[1] = specialhashMap.getClass();
         classes[2] = abeFactory.getClass(abeClientInformation, "javax.servlet.jsp.PageContext");
         //pageContext.getClass();         
         
         //Add arguments
         params[0] = (Object) hashMap;
         params[1] = (Object) specialhashMap;
         params[2] = (Object) pageContext;

         Object object = abeFactory.getInstance(abeClientInformation, className, classes, params);
         return object;
      }
      catch(LicensingException e)
      {         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             String error = "Failed To Get Instance Args: HashMap=" + 
                 hashMap.toString() + " PageContext=" + pageContext;

             logUtil.put(error, factoryName + "->HelperFactory",
                 "getInstance(String, String, HashMap, PageContext)",e);
         }
         throw e;
      }      
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)",e);
         }
         return null;
      }
   }     

   public static Object getInstance(final AbeClientInformationInterface abeClientInformation, 
       final String factoryName, final String className)
      throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         Object object = AbeFactory.getInstance().getInstance(abeClientInformation, className);
         return object;
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e);
         }
         throw e;
      }      
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION,factoryName + "->HelperFactory",
            "getInstance(String, String, HashMap, PageContext)", e);
         }
         return null;
      }
   }        
}
