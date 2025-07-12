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
package org.allbinary.logic.io.file;

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringValidationUtil;

//Used to correct user input for paths
public class FilePathUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
  
   private FilePathUtil()
   {
   }

   /*
   public static boolean isValid(String path)
   {
      if(FilePathUtil.isValidStart(path))
      {
         if(FilePathUtil.isValidEnd(path))
         {
            return true;
         }
      }
      return false;
   }

   private static boolean isValidStart(String path)
   {
      //FilePathData.SEPARATORCHAR
      if(path.charAt(0) == FilePathData.SEPARATORCHAR)
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
      if(StringValidationUtil.isEmpty(path))
      {
         return false;
      }

      //FilePathData.SEPARATORCHAR
      if(path.charAt(path.length()-1) == FilePathData.SEPARATORCHAR)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   */

   public static String adjust(String path)
   {
       final AbPathData abPathData = AbPathData.getInstance();
       final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
       final FilePathData filePathData = FilePathData.getInstance();
       
      if(stringValidationUtil.isEmpty(path))
      {
         //FilePathData.
         return filePathData.SEPARATOR;
      }

      //Replace Duplicate Path Separators - to deal with database insertion issue
      /*
      if(path.indexOf("\\\\") >= 0)
      {
         path = new Replace("\\\\","\\").all(path);
      }
      */

      //Convert \ to / I.E. from uri/unix path to dos/windows file path
      //PreLogUtil.put("Adjusting Path: " + path, "FilePathUtil", "adjust");
      path = path.replace(abPathData.SEPARATORCHAR,filePathData.SEPARATORCHAR);
      //PreLogUtil.put("Adjusted Path: " + path, "FilePathUtil", "adjust");
      /*
      if(path.indexOf(abPathData.SEPARATOR) >= 0)
      {
         
         //Causes recursive loop on log initialization
         //path = new Replace(abPathData.SEPARATOR,"\\\\").all(path);
      }
      */
      
      /*
      if(!FilePathUtil.isValidStart(path))
      {
         //FilePathData.
         path = filePathData.SEPARATOR + path;
      }

      if(!FilePathUtil.isValidEnd(path))
      {
         //FilePathData.
         path = path + filePathData.SEPARATOR;
      }
       */
      
      //Causes recursive loop on log initialization
      /*
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
      {
         PreLogUtil.put("Path: " + path, "PathUtil", "adjust");
      }
      */

      return path;
   }
}
