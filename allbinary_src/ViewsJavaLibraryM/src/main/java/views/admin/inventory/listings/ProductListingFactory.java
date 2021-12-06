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
package views.admin.inventory.listings;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;




import org.allbinary.logic.control.search.SearchRequest;

public class ProductListingFactory
{
   //private static final String CLASSNAME = "views.admin.inventory.listings.ProductListing";
   
   private ProductListingFactory()
   {
   }
   
   public static ProductListingInterface getInstance(SearchRequest searchRequest)// throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (ProductListingInterface) InterfaceCastProxy.newInstance(object);
         return (ProductListingInterface) new views.admin.inventory.listings.ProductListing(searchRequest);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(error,"ProductListingFactory","getInstance()",e);
         }
         throw e;
      }*/
      catch(Exception e)
      {
         String error = "Failed get Instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "ProductListingFactory", "getInstance()", e));
         }
         return null;
      }
   }
   
}
