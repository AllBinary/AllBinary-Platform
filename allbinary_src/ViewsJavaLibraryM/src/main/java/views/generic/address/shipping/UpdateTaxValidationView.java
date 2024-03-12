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

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.ShippingAddressData;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.business.user.commerce.money.tax.TaxFactory;

import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;


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
      
      if(TaxFactory.getInstance(this.abeClientInformation, storeFrontInterface).isValid(this.streetAddress, storeFrontInterface) == Boolean.FALSE)
      {
         return Boolean.FALSE;
      }

      ShippingAddressesEntity billingAddressesEntity =
      new ShippingAddressesEntity(
      this.getWeblisketSession().getUserName());

      Vector streetAddresses = billingAddressesEntity.get();
      
      if(streetAddresses == null)
      {
         return Boolean.FALSE;
      }
      
      Iterator iter = streetAddresses.iterator();
      int count = 0;
      while(iter.hasNext())
      {
         StreetAddress aStreetAddress = (StreetAddress) iter.next();        

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
