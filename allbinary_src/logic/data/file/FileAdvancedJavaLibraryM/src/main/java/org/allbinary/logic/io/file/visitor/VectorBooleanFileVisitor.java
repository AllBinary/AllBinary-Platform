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
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.util.visitor.VisitorInterface;

public class VectorBooleanFileVisitor
   implements VisitorInterface
{
   private Vector filterStringVector;
   
   public VectorBooleanFileVisitor(Vector filterStringVector)
   {
      this.setFilterStringVector(filterStringVector);
      PreLogUtil.put("Filter Vector: " + this.getFilterStringVector().toString(), this, "Constructor");
   }

   public Object visit(Object object)
   {
      return (Object) this.visit((AbFile) object);
   }
   
   public Boolean visit(AbFile file)
   {
      Iterator iter = this.getFilterStringVector().iterator();
      while(iter.hasNext())
      {
         String nextFileFilterString = (String) iter.next();
         
         if(this.visit(file, nextFileFilterString).booleanValue())
         {
            return Boolean.TRUE;
         }
      }
      return Boolean.FALSE;
   }

   public Vector getFilterStringVector()
   {
      return filterStringVector;
   }

   public void setFilterStringVector(Vector filterStringVector)
   {
      this.filterStringVector = filterStringVector;
   }
   
   public Boolean visit(AbFile file, String fileNameString) {
       return Boolean.FALSE;
   }
}
