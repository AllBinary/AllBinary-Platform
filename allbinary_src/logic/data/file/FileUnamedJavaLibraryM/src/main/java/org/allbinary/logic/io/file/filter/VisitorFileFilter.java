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
package org.allbinary.logic.io.file.filter;

import java.io.File;
import java.io.FileFilter;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.logic.util.visitor.VisitorInterface;

public class VisitorFileFilter
   implements FileFilter
{
   private VisitorInterface booleanVisitorInterface;
   
   public VisitorFileFilter(
      VisitorInterface booleanVisitorInterface)
   {
      this.booleanVisitorInterface = booleanVisitorInterface;
   }

   public boolean accept(File file)
   {
      //System.out.println("Start Accept");
      //System.out.println("File Path: " + file.getPath());
       
       AbFile abFile = FileWrapperUtil.wrapFile(file);
       
      boolean isFileAcceptable =
         ((Boolean) this.booleanVisitorInterface.visit(abFile)).booleanValue();
      //System.out.println("isFileAcceptable: " + isFileAcceptable);
      //System.out.println("End Accept");
      return isFileAcceptable || file.isDirectory();
       
      //return this.accept(new AbFile(file));
   }

   public boolean accept(AbFile file)
   {
      //System.out.println("Start Accept");
      //System.out.println("File Path: " + file.getPath());
      boolean isFileAcceptable =
         ((Boolean) this.booleanVisitorInterface.visit(file)).booleanValue();
      //System.out.println("isFileAcceptable: " + isFileAcceptable);
      //System.out.println("End Accept");
      return isFileAcceptable || file.isDirectory();
   }
}
