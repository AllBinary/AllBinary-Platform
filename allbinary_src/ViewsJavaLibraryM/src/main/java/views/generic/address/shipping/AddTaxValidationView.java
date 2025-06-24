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

import org.allbinary.logic.communication.log.LogFactory;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.address.ShippingAddressData;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.business.user.commerce.money.tax.TaxFactory;
import org.allbinary.business.user.commerce.money.tax.modules.TaxModuleInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;

public class AddTaxValidationView extends ShippingAddressView implements ValidationComponentInterface
{
   public AddTaxValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid() throws Exception
   {
      try
      {
	 this.streetAddress = new StreetAddress(this.getRequest());
	 
	 StoreFrontInterface storeFrontInterface =
	    StoreFrontFactory.getInstance(
	       this.getTransformInfoInterface().getStoreName());
	 
	 TaxModuleInterface taxInterface = TaxFactory.getInstance().getInstance(this.abeClientInformation, storeFrontInterface);
	 
	 if(taxInterface == null)
	 {
	    return Boolean.FALSE;
	 }
	 
	 if(taxInterface.isValid(this.streetAddress, storeFrontInterface) == Boolean.FALSE)
	 {
	    return Boolean.FALSE;
	 }
	 
	 ShippingAddressesEntity billingAddressesEntity =
	    new ShippingAddressesEntity(this.getWeblisketSession().getUserName());
	 
	 //make sure a tax address does not exist
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
	       return Boolean.FALSE;
	    }
	 }
	 
	 return Boolean.TRUE;
	 
      }
      catch(Exception e)
      {
	 if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
	 {
	    LogUtil.put(LogFactory.getInstance("Failed to validate",this,"isValid()",e));
	 }
	 return Boolean.FALSE;
      }
   }
   
   public String validationInfo() throws Exception
   {
      try
      {
	 StringBuffer stringBuffer = new StringBuffer();
	 
	 StoreFrontInterface storeFrontInterface =
	    StoreFrontFactory.getInstance(this.getTransformInfoInterface().getStoreName());
	 
	 TaxModuleInterface taxInterface = TaxFactory.getInstance().getInstance(this.abeClientInformation, storeFrontInterface);
	 
	 if(taxInterface == null)
	 {
	    stringBuffer.append("Unable to Load Tax Component<br/>");
	 }
	 else
	 {
	    if(taxInterface.isValid(this.streetAddress, storeFrontInterface) == Boolean.FALSE)
	    {
	       stringBuffer.append("Unable to validate address with Tax Component<br/>");
	    }
	 }
	 
	 ShippingAddressesEntity billingAddressesEntity =
	    new ShippingAddressesEntity(this.getWeblisketSession().getUserName());
	 
	 //make sure a tax address does not exist
	 Vector streetAddressList = billingAddressesEntity.get();
	 
         final int size = streetAddressList.size();
         for(int index = 0; index < size; index++)
	 {
	    StreetAddress aStreetAddress = (StreetAddress) streetAddressList.get(index);
	    
	    if(aStreetAddress.getName().compareTo(ShippingAddressData.TAX)==0)
	    {
	       stringBuffer.append("Not a valid tax location<br/>");
	    }
	 }

	 return stringBuffer.toString();
      }
      catch(Exception e)
      {
	 if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
	 {
	    LogUtil.put(LogFactory.getInstance("Failed to create validateInfo",this,"validationInfo()",e));
	 }
	 return "Error Creating ValidationInfo";
      }
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
