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
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.logic.basic.string.StringUtil;


import allbinary.business.user.address.StreetAddress;
import allbinary.business.user.address.ShippingAddressData;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

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
      
      Vector streetAddresses = billingAddressesEntity.get();
      
      if(streetAddresses == null)
      {
         return Boolean.FALSE;
      }
      
      Iterator iter = streetAddresses.iterator();
      while(iter.hasNext())
      {
         StreetAddress aStreetAddress = (StreetAddress) iter.next();

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
