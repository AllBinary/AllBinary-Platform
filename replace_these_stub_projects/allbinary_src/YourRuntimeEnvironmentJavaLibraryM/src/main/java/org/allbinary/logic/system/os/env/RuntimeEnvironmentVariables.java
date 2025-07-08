
package org.allbinary.logic.system.os.env;

import org.allbinary.logic.communication.log.LogFactory;
import java.util.Properties;

import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.GenericOperatingSystem;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class RuntimeEnvironmentVariables
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public RuntimeEnvironmentVariables()
   {
   }
   /*
   public String getTest() throws Exception
   {
      return System.getenv("JAVA_HOME");
   }
*/
   public String getTest1() //throws Exception
   {
      return System.getProperty("java.java.home");
   }
   
   public String getVariable(String var)
   {
      Properties environmentVariables = this.get();
      return environmentVariables.getProperty(var);      
   }
   
   public Properties get() //throws Exception
   {
      try
      {
         Process process = null;
         Properties environmentVariables = new Properties();
         
         GenericOperatingSystem operatingSystemInterface
            = OperatingSystemFactory.getInstance().getOperatingSystemInstance();
         
         String osName = operatingSystemInterface.getName();
         
         String linuxCommand = "env";
         String windowsCommand = "cmd.exe /c set";
         String solarisCommand = linuxCommand;         

         OperatingSystems operatingSystems =
             OperatingSystems.getInstance();

         if(osName.compareTo(operatingSystems.LINUX)==0)
         {
            process = Runtime.getRuntime().exec(linuxCommand);
         }
         else if(osName.indexOf(operatingSystems.WINDOWS)>=0)
         {
            process = Runtime.getRuntime().exec(windowsCommand);
         }
         else if(osName.compareTo(operatingSystems.SOLARIS)==0)
         {
            process = Runtime.getRuntime().exec(solarisCommand);
         }
         else
         {
            throw new Exception();
         }
         
         InputStreamReader processInputStreamReader 
            = new InputStreamReader(process.getInputStream());
         
         BufferedReader bufferedReader 
            = new BufferedReader(processInputStreamReader);
         
         String keyValuePair;
         while((keyValuePair = bufferedReader.readLine()) != null)
         {
            int index = keyValuePair.indexOf('=');
            String key = keyValuePair.substring(0,index);
            String value = keyValuePair.substring(index+1);
            environmentVariables.put(key,value);
         }
         return environmentVariables;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "get()", e);
         }
         return null;
      }
   }

   public String getTempDir() ///throws Exception
   {
      try
      {                  
         return System.getProperty("java.io.tmpdir");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getTempDir()",e);
         }
         return null;         
      }
   }

   public String getLibraryPath() //throws Exception
   {
      try
      {                  
         return System.getProperty("java.library.path");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getLibraryPath()",e);
         }         
         return null;
      }
   }

   public String getClassPath() //throws Exception
   {
      try
      {                  
         return System.getProperty("java.class.path");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getClassPath()",e);
         }
         return null;
      }
   }
   
   public void updateLibraryPath(String libPath) //throws Exception
   {
      try
      {                  
         String existingPath = this.getLibraryPath();
         System.setProperty("java.library.path",libPath + ":" + existingPath);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"updateLibraryPath()",e);
         }      
      }
   }   

   public String getUserHome()// throws Exception
   {
      try
      {                  
         return System.getProperty("user.home");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getUserHome()",e);
         }
         return null;
      }
   }

   public String getUserDir()// throws Exception
   {
      try
      {                  
         return System.getProperty("user.dir");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"getUserDir()",e);
         }
         return null;
      }
   }
   
   public void set()// throws Exception
   {
      try
      {
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"get()",e);
         }       
      }
   }

   public void setVariable(String var, String val)// throws Exception
   {
      try
      {
         Process process = null;
         GenericOperatingSystem operatingSystemInterface
            = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

         OperatingSystems operatingSystems =
             OperatingSystems.getInstance();
         
         String osName = operatingSystemInterface.getName();
         
         String windowsCommand = "cmd.exe /c set" + var + "=" + val;
                  
         if(osName.compareTo(operatingSystems.LINUX)==0)
         {
            //RuntimeEnvironmentVariablesJni runtimeEnvironmentVariablesJni = new RuntimeEnvironmentVariablesJni();
            //runtimeEnvironmentVariablesJni.setVariable(var,val);
         }
         else if(osName.indexOf(operatingSystems.WINDOWS)>=0)
         {
            process = Runtime.getRuntime().exec(windowsCommand);
         }
         else if(osName.compareTo(operatingSystems.SOLARIS)==0)
         {
            //RuntimeEnvironmentVariablesJni runtimeEnvironmentVariablesJni = new RuntimeEnvironmentVariablesJni();
            //runtimeEnvironmentVariablesJni.setVariable(var,val);            
         }
         else
         {
            throw new Exception();
         }         
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().OSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"setVariable()",e);
         }      
         
      }
   }   
      
}
