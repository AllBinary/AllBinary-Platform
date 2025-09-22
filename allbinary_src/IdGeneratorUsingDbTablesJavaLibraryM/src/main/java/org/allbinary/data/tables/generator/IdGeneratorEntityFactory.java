/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.data.tables.generator;


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class IdGeneratorEntityFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private IdGeneratorEntityFactory()
   {
   }
   
   public static IdGeneratorEntityInterface getInstance()
   {
      try
      {
         return (IdGeneratorEntityInterface) new org.allbinary.data.tables.generator.IdGeneratorEntity();
      }
      catch(Exception e)
      {
          final LogUtil logUtil = LogUtil.getInstance();
         final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.EXCEPTION, "ServerLicenseRequestEntityFactory",commonStrings.GET_INSTANCE,e);
         return null;
      }
   }
}