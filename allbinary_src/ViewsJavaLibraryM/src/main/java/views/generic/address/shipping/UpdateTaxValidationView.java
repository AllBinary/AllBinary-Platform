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


import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.money.tax.TaxFactory;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class UpdateTaxValidationView extends ShippingAddressView implements ValidationComponentInterface
{   
    
   public UpdateTaxValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid() throws Exception
   {
      this.streetAddress = new StreetAddress(this.getRequest());

      StoreFrontInterface storeFrontInterface = 
         StoreFrontFactory.getInstance(this.getTransformInfoInterface().getStoreName());
      
      if(TaxFactory.getInstance().getInstance(this.abeClientInformation, storeFrontInterface).isValid(this.streetAddress, storeFrontInterface) == Boolean.FALSE)
      {
         return Boolean.FALSE;
      }

      ShippingAddressesEntity billingAddressesEntity =
      new ShippingAddressesEntity(
      this.getWeblisketSession().getUserName());

      Vector streetAddressList = billingAddressesEntity.get();
      
      if(streetAddressList == null)
      {
         return Boolean.FALSE;
      }
      
      int count = 0;
      final int size = streetAddressList.size();
      for(int index = 0; index < size; index++)
      {
         StreetAddress aStreetAddress = (StreetAddress) streetAddressList.get(index);

         if(aStreetAddress.getName().compareTo(ShippingAddressData.TAX)==0)
         {
            count++;            
         }
      }
      
      if(count!=1) return Boolean.FALSE;
      
      return Boolean.TRUE;
   }

   public String validationInfo()
   {
      return "Unable to update tax location.";
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
