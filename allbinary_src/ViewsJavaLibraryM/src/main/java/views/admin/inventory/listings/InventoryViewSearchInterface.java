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


public interface InventoryViewSearchInterface
{
   public String searchSingleStaticPage() throws Exception;
   public String searchSingleDynamicPage() throws Exception;
   
   public String[] search() throws Exception;
      
   public String getProduct(String product) throws Exception;
   
   //public String remove(String keywords);
   //public String update(String keywords);
}
