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



import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

import org.allbinary.business.user.address.StreetAddress;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;


public class ValidationView extends ShippingAddressesView implements ValidationComponentInterface
{   
   public ValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      ShippingAddressesEntity billingAddressesEntity = 
         new ShippingAddressesEntity(         
         this.getWeblisketSession().getUserName());

      this.streetAddressList = billingAddressesEntity.get();
      
      if(this.streetAddressList == null)
      {
         return Boolean.FALSE;
      }

      final int size = streetAddressList.size();
      for(int index = 0; index < size; index++)
      {
         StreetAddress streetAddress = (StreetAddress) streetAddressList.get(index);
         if(streetAddress.isValid() == Boolean.FALSE)
         {
            streetAddressList.remove(streetAddress);
         }
      }
      
      if(this.streetAddressList.size()<1)
      {
         return Boolean.FALSE;
      }
      
      return Boolean.TRUE;
   }

   public String validationInfo()
   {
      return "No Shipping Addresses to display.<br />";
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
