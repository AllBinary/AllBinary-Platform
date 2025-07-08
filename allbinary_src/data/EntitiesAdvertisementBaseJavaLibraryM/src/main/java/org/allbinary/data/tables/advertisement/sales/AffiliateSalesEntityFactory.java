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
package org.allbinary.data.tables.advertisement.sales;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AffiliateSalesEntityFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private static final String CLASSNAME = "org.allbinary.data.tables.AffiliatesSalesEntity";
   
   private AffiliateSalesEntityFactory()
   {
   }
   
   public static AffiliateSalesEntityInterface getInstance() //throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         return (AffiliateSalesEntityInterface) new AffiliateSalesEntity();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, "AffiliateEntityFactory", commonStrings.GET_INSTANCE, e);
         }
         return null;
      }   
   }
}
