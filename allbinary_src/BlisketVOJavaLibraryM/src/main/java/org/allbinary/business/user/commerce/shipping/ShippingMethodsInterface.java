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

import org.allbinary.business.user.commerce.shipping.modules.ShippingInterface;

public interface ShippingMethodsInterface
{         
   public Vector get();

   public ShippingInterface getShippingInterface(String name) throws Exception;

   public ShippingInterface getDefault() throws Exception;
}
