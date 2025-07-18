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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.system.security.licensing.LicensingException;
import org.allbinary.string.CommonStrings;
import views.generic.inventory.InventorySearch;

public class InventoryViewFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   //private static final String CLASSNAME = "views.generic.inventory.InventorySearch";
   
   private InventoryViewFactory()
   {
   }

   public static InventoryViewSearchInterface getInstance(SearchRequest searchRequest)
      throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
    	  /*
         String className = CLASSNAME;
         
         Object params[] = new Object[1];
         Class classes[] = new Class[1];
                  
         //Add param types
         classes[0] = searchRequest.getClass();
         
         //Add arguments
         params[0] = (Object) searchRequest;
         
         return (InventoryViewSearchInterface) AbeFactory.getInstance().getInstance(className, classes, params);
         */
    	 return new InventorySearch(searchRequest);
      }
      /*
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(SearchRequest)", e);
         }
         throw e;
      } 
      */     
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, "InventoryViewFactory", "getInstance(SearchRequest)",e);
         }
         return null;
      }         
   }   
}
