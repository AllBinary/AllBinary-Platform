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
package allbinary.data.tables.advertisement.sales;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class AffiliateSalesEntityFactory
{
   private static final String CLASSNAME = "allbinary.data.tables.AffiliatesSalesEntity";
   
   private AffiliateSalesEntityFactory()
   {
   }
   
   public static AffiliateSalesEntityInterface getInstance() //throws LicensingException
   {
      try
      {
         return (AffiliateSalesEntityInterface) new AffiliateSalesEntity();
      }
      catch(Exception e)
      {
         String error = "Failed get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "AffiliateEntityFactory", "getInstance", e));
         }
         return null;
      }   
   }
}
