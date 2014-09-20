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
package org.allbinary.business.user.commerce.money.tax.modules;

//import org.allbinary.business.user.commerce.money.Money;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.address.StreetAddress;

public interface TaxModuleInterface
{
   public Float getTaxRate(StreetAddress streetAddress, StoreFrontInterface storeFrontInterface) throws Exception;
   
   public Boolean isValid(StreetAddress streetAddress, StoreFrontInterface storeFrontInterface) throws Exception;
}
