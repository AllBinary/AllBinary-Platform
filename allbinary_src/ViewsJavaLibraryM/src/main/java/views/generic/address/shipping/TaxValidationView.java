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


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.string.StringUtil;


import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.ShippingAddressData;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

public class TaxValidationView extends ShippingAddressView implements ValidationComponentInterface
{
   public TaxValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      ShippingAddressesEntity billingAddressesEntity =
         new ShippingAddressesEntity(this.getWeblisketSession().getUserName());
      
      Vector streetAddressList = billingAddressesEntity.get();
      
      if(streetAddressList == null)
      {
         return Boolean.FALSE;
      }
      
      final int size = streetAddressList.size();
      for(int index = 0; index < size; index++)
      {
         StreetAddress aStreetAddress = (StreetAddress) streetAddressList.get(index);

         if(aStreetAddress.getName().compareTo(ShippingAddressData.TAX)==0)
         {
            this.streetAddress = aStreetAddress;
            return Boolean.TRUE;
         }
      }
      
      return Boolean.FALSE;
   }
   
   public String validationInfo()
   {
      return StringUtil.getInstance().EMPTY_STRING;
      //"No Shipping Addresses to display.<br />";
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
