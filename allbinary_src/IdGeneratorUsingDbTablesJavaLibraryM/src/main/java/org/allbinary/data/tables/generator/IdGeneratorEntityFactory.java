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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class IdGeneratorEntityFactory
{
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
         final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "ServerLicenseRequestEntityFactory",commonStrings.GET_INSTANCE,e));
         return null;
      }
   }
}