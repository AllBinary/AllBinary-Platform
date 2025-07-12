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

import org.allbinary.data.tables.user.address.billing.BillingAddressesEntity;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ValidationView extends BillingAddressesView implements ValidationComponentInterface
{   
   public ValidationView(TransformInfoInterface transformInfoInterface)  throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      BillingAddressesEntity billingAddressesEntity = 
         new BillingAddressesEntity(         
         this.getWeblisketSession().getUserName());

      this.streetAddresses = billingAddressesEntity.get();
      
      if(this.streetAddresses == null)
      {
         return Boolean.FALSE;
      }

      if(this.streetAddresses.size()<1)
      {
         return Boolean.FALSE;
      }
      
      return Boolean.TRUE;
   }

   public String validationInfo()
   {
      return "No Billing Addresses to display.<br />";
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
