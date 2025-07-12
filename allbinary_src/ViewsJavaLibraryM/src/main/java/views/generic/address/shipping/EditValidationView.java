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

import org.allbinary.business.user.address.StreetAddressData;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EditValidationView extends ShippingAddressView implements ValidationComponentInterface
{
   private String value;
   
   public EditValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();
      
      this.value = 
         httpTransformInfoInterface.getPageContext().getRequest().getParameter(
         StreetAddressData.ID);
   }
   
   public Boolean isValid()
   {      
      if(this.getWeblisketSession().getUserName()!=null)
      {
         return Boolean.FALSE;
      }
      
      ShippingAddressesEntity billingAddressesEntity =
      new ShippingAddressesEntity(this.getWeblisketSession().getUserName());
      
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
