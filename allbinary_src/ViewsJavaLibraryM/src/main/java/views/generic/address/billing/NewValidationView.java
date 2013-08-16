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
package views.generic.address.billing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import allbinary.business.user.address.StreetAddress;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;


public class NewValidationView extends BillingAddressView implements ValidationComponentInterface
{   
   public NewValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {      
      this.streetAddress = new StreetAddress();
      return Boolean.TRUE;
   }

   public String validationInfo()
   {
      return "Please contact the administrator.";
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
