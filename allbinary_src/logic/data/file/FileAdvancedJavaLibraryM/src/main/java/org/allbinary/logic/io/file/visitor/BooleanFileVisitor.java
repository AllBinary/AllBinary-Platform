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
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.util.visitor.VisitorInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class BooleanFileVisitor
   implements VisitorInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   private BasicArrayList filterStringBasicArrayList;
   
   public BooleanFileVisitor(BasicArrayList filterStringBasicArrayList)
   {
      this.setFilterStringBasicArrayList(filterStringBasicArrayList);
      PreLogUtil.put("Filter BasicArrayList: " + this.getFilterStringBasicArrayList().toString(), this, this.commonStrings.CONSTRUCTOR);
   }

   public Object visit(Object object)
   {
      return (Object) this.visit((AbFile) object);
   }
   
   public Boolean visit(AbFile file)
   {
      final BasicArrayList list = this.getFilterStringBasicArrayList();
      final int size = list.size();
      
      String nextFileFilterString;
      for(int index = 0; index < size; index++)
      {
         nextFileFilterString = (String) list.get(index);
         
         if(this.visit(file, nextFileFilterString).booleanValue())
         {
            return Boolean.TRUE;
         }
      }
      return Boolean.FALSE;
   }

   public BasicArrayList getFilterStringBasicArrayList()
   {
      return filterStringBasicArrayList;
   }

   public void setFilterStringBasicArrayList(BasicArrayList filterStringBasicArrayList)
   {
      this.filterStringBasicArrayList = filterStringBasicArrayList;
   }
   
   public Boolean visit(AbFile file, String fileNameString) {
       return Boolean.FALSE;
   }
}
