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

import org.allbinary.business.user.address.StreetAddressData;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntity;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class EditValidationView extends BillingAddressView implements ValidationComponentInterface
{
   private String value;
   
   public EditValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.value = this.getPageContext().getRequest().getParameter(StreetAddressData.ID);
   }
   
   public Boolean isValid()
   {      
      if(this.getWeblisketSession().getUserName()!=null)
      {
         return Boolean.FALSE;
      }
      
      BillingAddressesEntity billingAddressesEntity =
      new BillingAddressesEntity(this.getWeblisketSession().getUserName());
      
      this.streetAddress = billingAddressesEntity.get(new Integer(value));
      
      if(this.streetAddress==null)
      {
         return Boolean.FALSE;
      }

      return this.streetAddress.isValid();
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
