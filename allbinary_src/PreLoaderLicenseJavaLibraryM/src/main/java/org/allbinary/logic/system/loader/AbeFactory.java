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
package org.allbinary.logic.system.loader;

import java.lang.reflect.Constructor;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.object.ConstructorUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.AbKeys;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonSeps;
        
public class AbeFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

	private static final AbeFactory instance = new AbeFactory();
	
        public static AbeFactory getInstance() {
            return instance;
        }
        
   private boolean useCustomLoader = false;
   
   private AbeFactory()
   {
   }
   
   public synchronized Object getInstance(final AbeClientInformationInterface abeClientInformation, final String className) 
      throws LicensingException
   {
      try
      {
          /*
         AbKeysInterface abKeysInterface = 
            AbeFactory.getInstance().getNoLicenseInstance(SECURITYKEYSCLASSPATH);
         String key = abKeysInterface.getKey(className);
         */
          
         return this.getClass(abeClientInformation, className).newInstance();
      }
      catch (LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getInstance(classname)", e);
         }
         throw e;         
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getInstance(classname)", e);
         }
         return null;         
      }
   }
   
   public synchronized Object getInstance(
      final AbeClientInformationInterface abeClientInformation, String className, Class classes[], Object params[]) 
      throws LicensingException
   {
      Constructor constructor = null;
      try
      {
          /*
         AbKeysInterface abKeysInterface = 
            AbeFactory.getInstance().getNoLicenseInstance(SECURITYKEYSCLASSPATH);
         String key = abKeysInterface.getKey(className);
         */
         
         ClassLoader parent = WebappClassLoaderInfo.getLoader();
         //ClassLoader parent = new Object().getClass().getClassLoader();

         if(useCustomLoader)
         {
            ClassLoader loader = new AbeClassLoader(parent, AbKeys.getInstance().getKey(abeClientInformation, className));
            Class myClass = loader.loadClass(className);
            constructor = myClass.getConstructor(classes);
            return constructor.newInstance(params);
         }
         else
         {
            Class myClass = parent.loadClass(className);
            constructor = myClass.getConstructor(classes);
            return constructor.newInstance(params);
         }
      }
      catch (LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
        	 StringMaker stringBuffer = new StringMaker();
        	 
        	 stringBuffer.append("Failure for: ");
        	 stringBuffer.append(className);
        	 stringBuffer.append(CommonSeps.getInstance().SPACE);
        	 stringBuffer.append(ConstructorUtil.view(constructor, "\n"));
             
        	 logUtil.put(stringBuffer.toString(), this, "getInstance(className,params)", e);
         }
         throw e;
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
        	 StringMaker stringBuffer = new StringMaker();
        	 
        	 stringBuffer.append("Failure for: ");
        	 stringBuffer.append(className);
        	 stringBuffer.append(CommonSeps.getInstance().SPACE);
        	 stringBuffer.append(ConstructorUtil.view(constructor, "\n"));
             
        	 logUtil.put(stringBuffer.toString(), this, "getInstance(className,params)", e);
         }
         return null;
      }
   }

   //Use one time for custom loader to set URLGLOBALS for this class 
   //loader before license is retrieved for the license installer
   /*
   public synchronized static Object getNoLicenseInstance(String className)
   {
      try
      {         
         ClassLoader parent = WebappClassLoaderInfo.getLoader();
         ClassLoader loader = new AbeClassLoader(parent,null);
         Class c = abcs.logic.system.loader.loadClass(className);
         return c.newInstance();
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getInstance(classname)", e);
         }
         return null;         
      }
   }
*/
   /*
   public synchronized static Object getNoLicenseInstance(String className, Class classes[], Object params[]) throws LicensingException
   {
      Constructor constructor = null;
      try
      {
         ClassLoader parent = WebappClassLoaderInfo.getLoader();
         //ClassLoader parent = new Object().getClass().getClassLoader();
         ClassLoader loader = new AbeClassLoader(parent,null);
         Class myClass = abcs.logic.system.loader.loadClass(className);
         constructor = myClass.getConstructor(classes);
         return constructor.newInstance(params);
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
        	 StringMaker stringBuffer = new StringMaker();
        	 
        	 stringBuffer.append("Failure for: ");
        	 stringBuffer.append(className);
        	 stringBuffer.append(commonStrings.SPACE);
        	 stringBuffer.append(ConstructorUtil.view(constructor, "\n"));
             
        	 logUtil.put(stringBuffer.toString(), this, "getNoLicenseInstance(String className, Class classes[], Object params[])", e);
         }
         return null;
      }
   }
   */
   
   /*
   public synchronized static Class loadClassesFromJar(JarFile jarFile) 
      throws LicensingException
   {
      try
      {
         ClassLoader parent = WebappClassLoaderInfo.getLoader();
         //ClassLoader parent = new Object().getClass().getClassLoader();
         
         if(useCustomLoader)
         {
            AbeClassLoader abeClassLoader = 
               new AbeClassLoader(parent, AbKeys.getKey(className));
            Class c = loaderloadClass(className);
            return c;
         }
         else
         {
            Class c = parent.loadClass(className);
            return c;
         }
      }
      catch (LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getClass(className)", e);
         }
         throw e;
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getClass(className)", e);
         }
         return null;
      }
   }
     */
   
   public synchronized Class getClass(final AbeClientInformationInterface abeClientInformation, final String className) 
      throws LicensingException
   {
      try
      {
         ClassLoader parent = WebappClassLoaderInfo.getLoader();
         //ClassLoader parent = new Object().getClass().getClassLoader();
         
         if(useCustomLoader)
         {
            ClassLoader loader = new AbeClassLoader(parent, AbKeys.getInstance().getKey(abeClientInformation, className));
            Class c = loader.loadClass(className);
            return c;
         }
         else
         {
            Class c = parent.loadClass(className);
            return c;
         }
      }
      catch (LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getClass(className)", e);
         }
         throw e;
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getClass(className)", e);
         }
         return null;
      }
   }
   
         /*public synchronized static Object getInstance(String className)
   {
      try
      {
          
         if(AbKeys.isTimeToGetKey())
         {
            AbeLicenseInterface license = AbKeys.getLicense();
            if(license != null)
            {
               AbKeys.key = license.getKey("AbeClassLoader");
            }
         }
          
         if(AbKeys.isKeyValid())
         {
          */
         /*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               logUtil.put("Getting Instance", this,commonStrings.GET_INSTANCE);
            }
          */
   //    ClassLoader loader = new AbeClassLoader(AbKeys.key);
/*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               if(loader==null)
               {
                  logUtil.put("Loader Null", this,commonStrings.GET_INSTANCE);
               }
               else
               {
                  logUtil.put("Loader Not Null", this,commonStrings.GET_INSTANCE);
               }
            }
 */
   //Class c = Class.forName(className);
   //Class c = Class.forName(className, true, loader);
   
   //      Class c = abcs.logic.system.loader.loadClass(className);
  /*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               if(c==null)
               {
                  logUtil.put("Class Null", this,commonStrings.GET_INSTANCE);
               }
               else
               {
                  //logUtil.put("isInstance: " + myClass.isInstance(), this,commonStrings.GET_INSTANCE);
                  logUtil.put("Class Not Null", this,commonStrings.GET_INSTANCE);
               }
            }
   */
   //         return c.newInstance();
            /*
         }
         else
            return null;
             */
/*      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getInstance(classname)", e);
         }
         return null;
      }
   }*/
   
   /*
   public synchronized static Object getDefaultInstance(String className)
   {
      try
      {
         Class c = Class.forName(className);
         return c.newInstance();
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className, this,"getInstance(classname)", e);
         }
         return null;
      }
   }
   
   public synchronized static Object getDefaultInstance(String className, Class classes[], Object params[])
   {
      Constructor constructor = null;
      try
      {
         Class myClass = Class.forName(className);
         constructor = myClass.getConstructor(classes);
         return constructor.newInstance(params);
      }
      catch (Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
         {
            logUtil.put("Failure for: " + className + " " + allbinary.java.object.ObjectInfo.viewConstructor(constructor, "\n"), this,"getInstance(className,params)", e);
         }
         return null;
      }
   }
   */
}
