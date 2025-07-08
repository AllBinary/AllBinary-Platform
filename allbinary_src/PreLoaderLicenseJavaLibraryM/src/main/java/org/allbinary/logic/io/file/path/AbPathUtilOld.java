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
package org.allbinary.logic.io.file.path;

import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringValidationUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

//Used to correct user input for paths
public class AbPathUtilOld
{
    protected final LogUtil logUtil = LogUtil.getInstance();
  
    private static final AbPathUtilOld instance = new AbPathUtilOld();

    public static final AbPathUtilOld getInstance() {
        return instance;
    }

    private final AbPathData abPathData = AbPathData.getInstance();    
    private final FilePathData filePathData = FilePathData.getInstance();
	
   private static final int MIN = 4;
   
   private AbPathUtilOld()
   {
   }

   public String getExtension(AbFilePath abFilePath) 
      throws Exception
   {
      return this.getExtension(abFilePath.toString());
   }

   public String getExtension(AbPath abPath) 
      throws Exception
   {
      return this.getExtension(abPath.toString());
   }

   public String getExtension(String filePath) throws Exception
   {
      if(filePath.length() < MIN)
      {
         //assuming the extension is 3 characters with a seperator
         throw new Exception("Could not be a file path since its less that 4 characters");
      }

      int beginIndex = filePath.length() - MIN;

      String extension = filePath.substring(beginIndex + 1);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("FileExtension: " + extension, this, "getExtension()");
      }

      return extension;
   }

   public String getWithoutExtension(AbFilePath abFilePath) 
      throws Exception
   {
      return this.getWithoutExtension(abFilePath.toString());
   }
   
   public String getWithoutExtension(AbPath abPath) 
      throws Exception
   {
      return this.getWithoutExtension(abPath.toString());
   }
   
   public String getWithoutExtension(String filePath) throws Exception
   {
      if(filePath.length() < MIN)
      {
         //assuming the extension is 3 characters with a seperator
         throw new Exception("Could not be a file path since its less that 4 characters");
      }

      int endIndex = filePath.length() - MIN;

      String pathWithoutExtension = filePath.substring(0, endIndex);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("FileWithoutExtension: " + pathWithoutExtension, this, "getWithoutExtension()");
      }
      return pathWithoutExtension;
   }

   public boolean isValid(String path)
   {
      if(this.isValidStart(path))
      {
         if(this.isValidEnd(path))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isValidStart(String path)
   {
      if(path.charAt(0) == abPathData.SEPARATORCHAR)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   private boolean isValidEnd(String path)
   {
      if(StringValidationUtil.getInstance().isEmpty(path))
      {
         return false;
      }

      if(path.charAt(path.length()-1) == abPathData.SEPARATORCHAR)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public String adjustStart(String path)
   {
      if(!this.isValidStart(path))
      {
         path = abPathData.SEPARATOR + path;
      }
      return path;
   }

   public String adjustEnd(String path)
   {
      if(!this.isValidEnd(path))
      {
         path = path + abPathData.SEPARATOR;
      }
      return path;
   }
   
   public String adjust(String path)
   {
      if(StringValidationUtil.getInstance().isEmpty(path))
      {
         return abPathData.SEPARATOR;
      }

      //Convert \ to / I.E. from dos/windows file path to uri/unix path
      if(path.indexOf("\\") >= 0)
      {
         path = path.replace('\\',abPathData.SEPARATORCHAR);
      }
      
      /*
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
      {
         logUtil.put("Path: " + path, this, "adjust");
      }
       */
      return path;
   }
   
   public synchronized String getNameFromPath(String categoryPath)
   {
      int endIndex = categoryPath.lastIndexOf(abPathData.SEPARATOR);
      
      if(endIndex < 0)
      {
         endIndex = categoryPath.lastIndexOf(filePathData.SEPARATORCHAR);
      }

      if(endIndex < 0) return categoryPath;

      //if the last char is sep then use the last token as the name
      if(categoryPath.length() == endIndex + 1)
      {
         String categoryName = categoryPath.substring(0, endIndex);
         return this.getNameFromPath(categoryName);
      }
      else
      {
         String categoryName = categoryPath.substring(endIndex + 1);
         return categoryName;
      }
   }

   public synchronized AbPath removeNameFromPath(String categoryPath) throws Exception
   {   
      int endIndex = categoryPath.lastIndexOf(abPathData.SEPARATOR);

      if(endIndex < 0)
      {
         endIndex = categoryPath.lastIndexOf(filePathData.SEPARATORCHAR);
      }

      if(endIndex < 0)
      {
         return new AbPath();
      }

      //if the last char is sep then use the last token as the name
      if(categoryPath.length() == endIndex + 1)
      {
         return this.removeNameFromPath(categoryPath.substring(0, endIndex - 1));
      }
      else
      {
         String categoryName = categoryPath.substring(0, endIndex);
         return new AbPath(categoryName);
      }
   }
}
