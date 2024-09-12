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
package org.allbinary.logic.io.file.visitor;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.util.BasicArrayList;

public class ExcludeFileExtensionsBooleanFileVisitor extends BooleanFileVisitor
{
   public ExcludeFileExtensionsBooleanFileVisitor(BasicArrayList filterStringBasicArrayList)
   {
      super(filterStringBasicArrayList);
   }
   
   public Boolean visit(AbFile file)
   {
      if(this.getFilterStringBasicArrayList().size() == 0)
      {
         return Boolean.TRUE;
      }
      
      return super.visit(file);
   }

   public Boolean visit(AbFile file, String fileNameString)
   {
      String fileNameAndExtension = file.getPath();

      if(fileNameAndExtension.length() > fileNameString.length())
      {
         int startIndex = fileNameAndExtension.length() - fileNameString.length();
         String fileName = fileNameAndExtension.substring(startIndex);
         if(fileName.compareTo(fileNameString) == 0)
         {
            return Boolean.FALSE;
         }
      }
      return Boolean.TRUE;
   }
}
