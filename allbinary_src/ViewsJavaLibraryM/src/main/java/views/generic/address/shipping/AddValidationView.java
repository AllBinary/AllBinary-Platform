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
package views.generic.address.shipping;

import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class AddValidationView extends ShippingAddressView implements ValidationComponentInterface
{
   public AddValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid() throws Exception
   {
      this.streetAddress = new StreetAddress(this.getRequest());
      
      if(this.streetAddress.isValid() == Boolean.FALSE)
      {
         return Boolean.FALSE;
      }
      
      return Boolean.TRUE;
   }
   
   public String validationInfo()
   {
      return this.streetAddress.validationInfo();
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
}
