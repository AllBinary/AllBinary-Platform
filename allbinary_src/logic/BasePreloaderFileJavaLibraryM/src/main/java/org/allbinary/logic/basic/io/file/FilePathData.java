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
package org.allbinary.logic.basic.io.file;

import java.io.File;

public class FilePathData
{
   private FilePathData()
   {
   }
   
   //final
   public static final char SEPARATORCHAR = File.separatorChar;
   
   public static String SEPARATOR = File.separator;
   
   static
   {
      if(File.separatorChar == '\\')
      {
         SEPARATOR = File.separator + File.separator;
      }
   }
   
}
