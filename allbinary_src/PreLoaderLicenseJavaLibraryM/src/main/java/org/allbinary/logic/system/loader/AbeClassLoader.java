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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.allbinary.logic.communication.log.LogBuffer;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.AbCryptUtil;

public class AbeClassLoader extends ClassLoader
//extends WebappClassLoader
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AbCryptUtil abCryptUtil = AbCryptUtil.getInstance();

    private final String LOAD_CLASS = "loadClass";
    
    private static Map classes = new HashMap();
    private final String ENCRYPTED_EXTENSION = AbPathData.getInstance().EXTENSION_SEP + "abc";
    private static String PATH;
    
    private String key;
    
    //private ClassLoader parent;
    public AbeClassLoader(ClassLoader parent, String key)
    {
        super(parent);
        this.key = key;
        //this.parent = parent;
        
        //super();
        
        AbeClassLoader.PATH = org.allbinary.globals.URLGLOBALS.getWebappPath() + "WEB-INF/classes/";
        
      /*
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
      {
         this.logUtil.putF("Path: " + AbeClassLoader.PATH, this,"AbeClassLoader(key)");
      }
       */
    }
    
   /*
   public AbeClassLoader(String key)
   {
      super(null);
    
      //super();
      this.key = key;
    
      AbeClassLoader.PATH = allbinary.globals.URLGLOBALS.getWebappPath() + "WEB-INF/classes/";
    
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
      {
         this.logUtil.putF("Path: " + AbeClassLoader.PATH, this,"AbeClassLoader(key)");
      }
   }
    */
    
    protected Class findLoadedClass1(String name)
    {
        return (Class)AbeClassLoader.classes.get(name);
    }
    
    public synchronized Class loadClass(String name) throws ClassNotFoundException
    {
        return this.loadClass(name,false);
    }

    /*
    public synchronized void loadClassesFromJar(JarFile jarFile) 
        throws Exception
    {
        return jarClass;
    }
    
    public synchronized Class loadClass(InputStream inputStream,
        String className, boolean resolve) throws Exception
    {
        byte[] data = this.getByteArrayOutputStream(inputStream).toByteArray();
        
        Class jarClass = defineClass(className, data, 0, data.length);
        
        //if (resolve)
        resolveClass(jarClass);
        
        return jarClass;
    }
    */
    
    public synchronized Class loadClass(
        String name, boolean resolve) throws ClassNotFoundException
    {
        String loadedWith = "findLoadedClass1";
        
        LogBuffer logBuffer = new LogBuffer();
        
        try
        {
         /*
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         {
            this.logUtil.putF("Loading CustomClass: " + name, this, this.LOAD_CLASS);
         }*/
            
            //Step 1 = Check to see if encrypted class is already loaded
            Class myClass = this.findLoadedClass1(name);
            
            if (myClass == null)
            {
                //Step 3 = Try to load class with normal classloader
                try
                {
                    loadedWith = "super.loadClass";
                    
                    Class normalClass = super.loadClass(name,resolve);
                    
               /*
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
               {
                  this.logUtil.putF("Loaded Class: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
               }
                */
                    
                    if(normalClass != null)
                    {
                        return normalClass;
                    }
                }
                catch (ClassNotFoundException e)
                {
                    logBuffer.add(LogFactory.getInstance("Failed to Load Class: " + name + "\nwith: " + loadedWith, this, this.LOAD_CLASS, e));
                }
                catch (Exception e)
                {
                    logBuffer.add(LogFactory.getInstance("Failed to Load Class: " + name + "\nwith: " + loadedWith, this, this.LOAD_CLASS, e));
                }
                catch (NoClassDefFoundError e)
                {
                    logBuffer.add(LogFactory.getInstanceF("NoClassDefFoundError Failed Loaded Class: " + name + "\nwith: " + loadedWith, this,this.LOAD_CLASS));
                }
                
                //Step 4 = Try to load an encrypted class
                byte[] classBytes = this.loadClassBytesFromFile(name);
                
                if(classBytes == null)
                {
                    throw new ClassNotFoundException("My Bytes not loaded for: " + name + "\nwith: " + "loadClassBytes");
                }
                
                myClass = defineClass(name, classBytes, 0, classBytes.length);
                
                if(myClass == null)
                {
                    throw new ClassNotFoundException("My Class Not Defineable for: " + name + "\nwith: " + "loadClassBytes");
                }
                
                AbeClassLoader.classes.put(name, myClass);
                
            /*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               this.logUtil.putF("Loaded CustomClass: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
            }
             */
            }
            else
            {
                logBuffer.add(LogFactory.getInstanceF("Already Loaded: " + name + "\nwith: " + loadedWith, this, this.LOAD_CLASS));
            }
            
            //if (resolve)
            resolveClass(myClass);
            
            return myClass;
        }
        catch (Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
            {
                logBuffer.logAll();
                this.logUtil.put("Failure loading: " + name + "\nwith: " + loadedWith, this, this.LOAD_CLASS, e);
            }
            return null;
        }
    }
        
    private byte[] loadClassBytesFromFile(String name)
    {
        FileInputStream in = null;
        String cname = StringUtil.getInstance().EMPTY_STRING;
        try
        {
            cname = AbeClassLoader.PATH + name.replace('.', AbPathData.getInstance().SEPARATORCHAR) + this.ENCRYPTED_EXTENSION;
            
         /*
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         {
            this.logUtil.putF("Crypted Class name: " + name + " path: " + cname , this, "loadClassBytes");
         }
          */
            
            in = new FileInputStream(cname);
            
            final byte[] decrypted = this.abCryptUtil.decrypt(in, this.key);
                        
         /*
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         {
            this.logUtil.putF("Decrypted with: " + this.key, this, "loadClassBytes");
         }
          */
            
            return decrypted;
        }
        catch (IOException e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
            {
                this.logUtil.put("(Before LogBuffer Output) Failure loading Encrypted: " + name + " File: " + cname, "AbeClassLoader","loadClassBytes", e);
            }
            
            StreamUtil.getInstance().close(in);
        }
        catch (Exception e)
        {
            final String loadedWith = "loadClassBytes";
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
            {
                this.logUtil.put("(Before LogBuffer Output) Failed to Loaded Class: " + name  + " File: " + cname + "\nwith: " + loadedWith, this,"loadClassBytes", e);
            }
        }
        catch (NoClassDefFoundError e)
        {
            final String loadedWith = "loadClassBytes";
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
            {
                this.logUtil.putF("(Before LogBuffer Output) NoClassDefFoundError Failed Loaded Class: " + name  + " File: " + cname + "\nwith: " + loadedWith, this,"loadClassBytes");
            }
        }
        return null;
    }
    
   /*
   private byte[] loadClassBytesNotCrypted(String name)
   {
      FileInputStream in = null;
      try
      {
         String cname = AbeClassLoader.PATH + name.replace('.', AbPathData.getInstance().SEPARATORCHAR) + ".class";
         in = new FileInputStream(cname);
       
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();

         outputStream = (ByteArrayOutputStream) 
             StreamUtil.get(inputStream, outputStream);  
         
         return buffer.toByteArray();
      }
      catch (IOException e)
      {
         return null;
      }
      finally
      {
      StreamUtil.getInstance().close(in);
      }
   }
    */
    
    public synchronized Class oldLoadClass(
        String name, boolean resolve) throws ClassNotFoundException
    {
        String loadedWith = "findLoadedClass1";
        
        try
        {
         /*
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         {
            this.logUtil.putF("Loading CustomClass: " + name, this, this.LOAD_CLASS);
         }*/
            
         /*
         //Step 0 = Try Loading from webappclassloader - includes Java system
         //classes and tomcat/common/lib and classes classes
         try
         {
            loadedWith = "super.loadClass";
          
            Class webappClass = super.loadClass(name,resolve);
          
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               this.logUtil.putF("Loaded Class: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
            }
          
            if(webappClass!=null)
            {
               return webappClass;
            }
         }
         catch (ClassNotFoundException e)
         {}
         catch (NoClassDefFoundError e)
         {}*/
            
            //Step 1 = Check to see if encrypted class is already loaded
            Class myClass = this.findLoadedClass1(name);
            
            if (myClass == null)
            {
            /*
            //Step 2 = Try to find system class
            try
            {
               loadedWith = "findSystemClass";
               Class systemClass = super.findSystemClass(name);
             
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
               {
                  this.logUtil.putF("Loaded SystemClass: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
               }
             
               if(systemClass!=null)
               {
                  return systemClass;
               }
            }
            catch (ClassNotFoundException e)
            {}
            catch (NoClassDefFoundError e)
            {}
             
            //Step 2 = Try to load previously loaded common class
            try
            {
               loadedWith = "findLoadedClass";
               Class loadedClass = super.findLoadedClass(name);
             
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
               {
                  this.logUtil.putF("Loaded SystemClass: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
               }
             
               if(loadedClass!=null)
               {
                  return loadedClass;
               }
            }
            catch (Exception e)
            {}
             */
                
                //Step 3 = Try to load class with normal classloader
                try
                {
                    loadedWith = "super.loadClass";
                    
                    Class normalClass = super.loadClass(name,resolve);
                    
               /*
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
               {
                  this.logUtil.putF("Loaded Class: " + name + " with: " + loadedWith , this, this.LOAD_CLASS);
               }
                */
                    
                    if(normalClass!=null)
                    {
                        return normalClass;
                    }
                }
                catch (ClassNotFoundException e)
                {
                    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
                    {
                        this.logUtil.putF("Failed Loaded Class: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
                    }
                }
                catch (Exception e)
                {
                    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
                    {
                        this.logUtil.putF("Failed Loaded Class: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
                    }
                }
                catch (NoClassDefFoundError e)
                {
                    if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
                    {
                        this.logUtil.putF("Failed Loaded Class: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
                    }
                }
                
                //Step 4 = Try to load an encrypted class
                loadedWith = "loadClassBytes";
                byte[] classBytes = this.loadClassBytesFromFile(name);
                
                //if (classBytes == null)
                //c1 = super.loadClass(name,resolve);
                //throw new ClassNotFoundException(name);
                
            /*
            if (classBytes == null)
            {
               //Step 4 = Try to load an nonencrypted class - This should really occur at step 2
               loadedWith = "loadClassBytesNotCrypted";
               classBytes = this.loadClassBytesNotCrypted(name);
            }
             */
                
                if(classBytes == null)
                {
                    throw new ClassNotFoundException("Bytes not loaded for: " + name);
                }
                
                myClass = defineClass(name, classBytes, 0, classBytes.length);
                
                //if (myClass == null)
                //c1 = super.loadClass(name,resolve);
                
            /*
            if (myClass == null)
            {
               loadedWith = "super.loadClass";
             
               Class normalClass = super.loadClass(name,resolve);
             
               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
               {
                  this.logUtil.putF("Loaded Class: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
               }
             
               return normalClass;
            }
             */
                
                if(myClass == null)
                {
                    throw new ClassNotFoundException("Class Not Defineable for: " + name);
                }
                
                AbeClassLoader.classes.put(name, myClass);
                
            /*
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
            {
               this.logUtil.putF("Loaded CustomClass: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
            }
             */
            }
            else
            {
                if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
                {
                    this.logUtil.putF("Already Loaded: " + name + " with: " + loadedWith , this,this.LOAD_CLASS);
                }
            }
            
            //if (resolve)
            resolveClass(myClass);
            
            return myClass;
            
        }
        catch (Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADERERROR))
            {
                this.logUtil.put("Failure loading: " + name, "AbeClassLoader",this.LOAD_CLASS, e);
            }
            
            return null;
        }
    }
    
}
