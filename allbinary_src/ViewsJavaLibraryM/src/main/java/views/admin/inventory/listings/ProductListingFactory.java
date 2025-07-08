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
import org.allbinary.string.CommonStrings;

public class ProductListingFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   //private static final String CLASSNAME = "views.admin.inventory.listings.ProductListing";
   
   private ProductListingFactory()
   {
   }
   
   public static ProductListingInterface getInstance(SearchRequest searchRequest)// throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //return (ProductListingInterface) InterfaceCastProxy.newInstance(object);
         return (ProductListingInterface) new views.admin.inventory.listings.ProductListing(searchRequest);
      }
      /*
      catch(LicensingException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         throw e;
      }*/
      catch(Exception e)
      {
          
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, "ProductListingFactory", commonStrings.GET_INSTANCE, e);
         }
         return null;
      }
   }
   
}
