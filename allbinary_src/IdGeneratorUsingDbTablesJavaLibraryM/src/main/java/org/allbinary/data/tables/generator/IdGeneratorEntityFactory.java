/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
package org.allbinary.data.tables.generator;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

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
         String error = "Failed to get instance";
         LogUtil.put(LogFactory.getInstance(error,"ServerLicenseRequestEntityFactory","getInstance()",e));
         return null;
      }
   }
}