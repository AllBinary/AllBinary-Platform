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
package org.allbinary.business.category.store.theme;

import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class StoreThemeCategoryValidationView extends StoreThemeCategoryView implements ValidationInterface
{
   public StoreThemeCategoryValidationView(StoreThemeCategoryInterface categoryInterface)
   {
      super((StoreThemeCategoryInterface) categoryInterface);
   }

   public Boolean isValid() throws Exception
   {
      return Boolean.TRUE;
   }
   
   public Document toValidationInfoDoc() throws Exception
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document) throws Exception
   {
      return null;
   }
   
   public String validationInfo() throws Exception
   {
      return "Error";
   }
   
}
