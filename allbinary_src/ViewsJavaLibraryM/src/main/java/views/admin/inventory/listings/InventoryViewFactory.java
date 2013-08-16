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



import views.generic.inventory.InventorySearch;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.logic.control.search.SearchRequest;

public class InventoryViewFactory
{
   //private static final String CLASSNAME = "views.generic.inventory.InventorySearch";
   
   private InventoryViewFactory()
   {
   }

   public static InventoryViewSearchInterface getInstance(SearchRequest searchRequest)
      throws LicensingException
   {
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
         
         return (InventoryViewSearchInterface) AbeFactory.getInstance(className, classes, params);
         */
    	 return new InventorySearch(searchRequest);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "InventoryViewFactory", "getInstance(SearchRequest)", e));
         }
         throw e;
      } 
      */     
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"InventoryViewFactory", "getInstance(SearchRequest)",e));
         }
         return null;
      }         
   }   
}
