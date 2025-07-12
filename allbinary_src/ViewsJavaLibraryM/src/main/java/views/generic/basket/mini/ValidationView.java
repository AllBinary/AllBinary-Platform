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
package views.generic.basket.mini;

import org.allbinary.globals.MESSAGES;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class ValidationView extends MiniBasketView implements ValidationComponentInterface
{
   public ValidationView(TransformInfoInterface transformInfoInterface)  throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      return Boolean.TRUE;
   }
   
   public org.w3c.dom.Document toValidationInfoDoc()
   {
      return null;
   }
   
   public org.w3c.dom.Node toValidationInfoNode(org.w3c.dom.Document document)
   {
      return null;      
   }
      
   public String validationInfo()
   {
      return MESSAGES.CONTACTADMIN;
   }
}
