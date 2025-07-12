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
package views.generic.inventory;

import org.allbinary.logic.control.search.SearchRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Node;




public class AutoSearchValidationInventoryView 
  extends InventorySearch
{
   
   public AutoSearchValidationInventoryView(SearchRequest searchRequest)
   {
      super(searchRequest);
   }

   public Boolean isValid()
   {
      return Boolean.TRUE;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo()
   {
      return null;
   }
}
