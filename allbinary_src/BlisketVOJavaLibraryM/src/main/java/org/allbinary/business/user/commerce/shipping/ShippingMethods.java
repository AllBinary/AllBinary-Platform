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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.shipping.modules.ShippingInterface;

import java.util.Iterator;
import java.util.Vector;


public class ShippingMethods implements ShippingMethodsInterface
{
   private Vector shippingVector;

   private ShippingInterface defaultShippingMethodInterface;
      
   public ShippingMethods(StoreFrontInterface storeFrontInterface) throws Exception
   {
      this.defaultShippingMethodInterface = 
         new ShippingMethodsFactory(storeFrontInterface).getDefaultInstance();
      this.shippingVector =
         new ShippingMethodsFactory(storeFrontInterface).getInstance();
   }
   
   public Vector get()
   {
      return shippingVector;
   }
   
   public ShippingInterface getShippingInterface(String name) throws Exception
   {          
      Iterator iter = shippingVector.iterator();            
      while(iter.hasNext())
      {  
         ShippingInterface shipping = (ShippingInterface) iter.next();
         if(name.compareTo(shipping.getName())==0) return shipping;
      }

      String error = "Error Finding Shipping: " + name;
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SHIPPINGERROR))
      {
         LogUtil.put(LogFactory.getInstance(error,this,"getShippingInterface(String name)"));
      }

      throw new Exception(error);
   }
   
   public ShippingInterface getDefault() throws Exception
   {
      return defaultShippingMethodInterface;
   }
}
