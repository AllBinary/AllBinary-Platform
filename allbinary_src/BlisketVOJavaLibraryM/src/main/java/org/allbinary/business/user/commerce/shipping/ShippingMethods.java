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
package org.allbinary.business.user.commerce.shipping;

import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.shipping.modules.ShippingInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class ShippingMethods implements ShippingMethodsInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private Vector shippingVector;

   private ShippingInterface defaultShippingMethodInterface;
      
   public ShippingMethods(final AbeClientInformationInterface abeClientInformation, final StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.defaultShippingMethodInterface = 
         new ShippingMethodsFactory(abeClientInformation, storeFrontInterface).getDefaultInstance();
      this.shippingVector =
         new ShippingMethodsFactory(abeClientInformation, storeFrontInterface).getInstance();
   }
   
   public Vector get()
   {
      return shippingVector;
   }
   
   public ShippingInterface getShippingInterface(String name) throws Exception
   {
       ShippingInterface shipping;
       
      final int size = shippingVector.size();
      for (int index = 0; index < size; index++)
      {  
         shipping = (ShippingInterface) shippingVector.get(index);
         if(name.compareTo(shipping.getName())==0) return shipping;
      }

      String error = "Error Finding Shipping: " + name;
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SHIPPINGERROR))
      {
         logUtil.put(commonStrings.EXCEPTION,this,"getShippingInterface(String name)");
      }

      throw new Exception(error);
   }
   
   public ShippingInterface getDefault() throws Exception
   {
      return defaultShippingMethodInterface;
   }
}
