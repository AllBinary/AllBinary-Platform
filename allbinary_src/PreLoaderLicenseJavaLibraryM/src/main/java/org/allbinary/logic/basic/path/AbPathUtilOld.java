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
package org.allbinary.logic.basic.path;

import org.allbinary.logic.basic.io.file.FilePathData;
import org.allbinary.logic.basic.string.StringValidationUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

//Used to correct user input for paths
public class AbPathUtilOld
{  
	private static final AbPathUtilOld instance = new AbPathUtilOld();
	
   private static final int MIN = 4;
   
   private AbPathUtilOld()
   {
   }

   public static String getExtension(AbFilePath abFilePath) 
      throws Exception
   {
      return AbPathUtilOld.getExtension(abFilePath.toString());
   }

   public static String getExtension(AbPath abPath) 
      throws Exception
   {
      return AbPathUtilOld.getExtension(abPath.toString());
   }

   public static String getExtension(String filePath) throws Exception
   {
      if(filePath.length() < MIN)
      {
         //assuming the extension is 3 characters with a seperator
         throw new Exception("Could not be a file path since its less that 4 characters");
      }

      int beginIndex = filePath.length() - MIN;

      String extension = filePath.substring(beginIndex + 1);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("FileExtension: " + extension, instance, "getExtension()"));
      }

      return extension;
   }

   public static String getWithoutExtension(AbFilePath abFilePath) 
      throws Exception
   {
      return AbPathUtilOld.getWithoutExtension(abFilePath.toString());
   }
   
   public static String getWithoutExtension(AbPath abPath) 
      throws Exception
   {
      return AbPathUtilOld.getWithoutExtension(abPath.toString());
   }
   
   public static String getWithoutExtension(String filePath) throws Exception
   {
      if(filePath.length() < MIN)
      {
         //assuming the extension is 3 characters with a seperator
         throw new Exception("Could not be a file path since its less that 4 characters");
      }

      int endIndex = filePath.length() - MIN;

      String pathWithoutExtension = filePath.substring(0, endIndex);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("FileWithoutExtension: " + pathWithoutExtension, instance, "getWithoutExtension()"));
      }
      return pathWithoutExtension;
   }

   public static boolean isValid(String path)
   {
      if(AbPathUtilOld.isValidStart(path))
      {
         if(AbPathUtilOld.isValidEnd(path))
         {
            return true;
         }
      }
      return false;
   }

   private static boolean isValidStart(String path)
   {
      if(path.charAt(0) == AbPathData.getInstance().SEPARATORCHAR)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   private static boolean isValidEnd(String path)
   {
      if(StringValidationUtil.getInstance().isEmpty(path))
      {
         return false;
      }

      if(path.charAt(path.length()-1) == AbPathData.getInstance().SEPARATORCHAR)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public static String adjustStart(String path)
   {
      if(!AbPathUtilOld.isValidStart(path))
      {
         path = AbPathData.getInstance().SEPARATOR + path;
      }
      return path;
   }

   public static String adjustEnd(String path)
   {
      if(!AbPathUtilOld.isValidEnd(path))
      {
         path = path + AbPathData.getInstance().SEPARATOR;
      }
      return path;
   }
   
   public static String adjust(String path)
   {
      if(StringValidationUtil.getInstance().isEmpty(path))
      {
         return AbPathData.getInstance().SEPARATOR;
      }

      //Convert \ to / I.E. from dos/windows file path to uri/unix path
      if(path.indexOf("\\") >= 0)
      {
         path = path.replace('\\',AbPathData.getInstance().SEPARATORCHAR);
      }
      
      /*
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.FILE))
      {
         LogUtil.put(LogFactory.getInstance("Path: " + path, instance, "adjust"));
      }
       */
      return path;
   }
   
   public static synchronized String getNameFromPath(String categoryPath)
   {
      int endIndex = categoryPath.lastIndexOf(AbPathData.getInstance().SEPARATOR);
      
      if(endIndex < 0)
      {
         endIndex = categoryPath.lastIndexOf(FilePathData.SEPARATORCHAR);
      }

      if(endIndex < 0) return categoryPath;

      //if the last char is sep then use the last token as the name
      if(categoryPath.length() == endIndex + 1)
      {
         String categoryName = categoryPath.substring(0, endIndex);
         return AbPathUtilOld.getNameFromPath(categoryName);
      }
      else
      {
         String categoryName = categoryPath.substring(endIndex + 1);
         return categoryName;
      }
   }

   public static synchronized AbPath removeNameFromPath(String categoryPath) throws Exception
   {   
      int endIndex = categoryPath.lastIndexOf(AbPathData.getInstance().SEPARATOR);

      if(endIndex < 0)
      {
         endIndex = categoryPath.lastIndexOf(FilePathData.SEPARATORCHAR);
      }

      if(endIndex < 0)
      {
         return new AbPath();
      }

      //if the last char is sep then use the last token as the name
      if(categoryPath.length() == endIndex + 1)
      {
         return AbPathUtilOld.removeNameFromPath(categoryPath.substring(0, endIndex - 1));
      }
      else
      {
         String categoryName = categoryPath.substring(0, endIndex);
         return new AbPath(categoryName);
      }
   }
}
