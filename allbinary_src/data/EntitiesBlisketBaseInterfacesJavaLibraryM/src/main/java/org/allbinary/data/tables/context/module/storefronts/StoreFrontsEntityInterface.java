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
package org.allbinary.data.tables.context.module.storefronts;

import java.util.HashMap;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface StoreFrontsEntityInterface 
   extends BasicDataTableInterface
{
   StoreFrontInterface getStoreFrontInterface(String name) throws Exception;
      
   BasicArrayList getStoreFrontNames();
   
   void delete(String value);

   void insert(BasicArrayList values);

   void update(HashMap updatedValues);
}
