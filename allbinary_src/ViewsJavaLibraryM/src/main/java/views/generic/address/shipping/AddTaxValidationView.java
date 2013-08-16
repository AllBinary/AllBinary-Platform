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

import abcs.logic.communication.log.LogFactory;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;

import allbinary.business.user.address.StreetAddress;
import allbinary.business.user.address.ShippingAddressData;

import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontFactory;

import allbinary.business.user.commerce.money.tax.TaxFactory;
import allbinary.business.user.commerce.money.tax.modules.TaxModuleInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import abcs.logic.communication.log.LogUtil;

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
	 
	 TaxModuleInterface taxInterface = TaxFactory.getInstance(storeFrontInterface);
	 
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
	       return Boolean.FALSE;
	    }
	 }
	 
	 return Boolean.TRUE;
	 
      }
      catch(Exception e)
      {
	 if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
	 
	 TaxModuleInterface taxInterface = TaxFactory.getInstance(storeFrontInterface);
	 
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
	 Vector streetAddresses = billingAddressesEntity.get();
	 
	 Iterator iter = streetAddresses.iterator();
	 while(iter.hasNext())
	 {
	    StreetAddress aStreetAddress = (StreetAddress) iter.next();
	    
	    if(aStreetAddress.getName().compareTo(ShippingAddressData.TAX)==0)
	    {
	       stringBuffer.append("Not a valid tax location<br/>");
	    }
	 }

	 return stringBuffer.toString();
      }
      catch(Exception e)
      {
	 if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
