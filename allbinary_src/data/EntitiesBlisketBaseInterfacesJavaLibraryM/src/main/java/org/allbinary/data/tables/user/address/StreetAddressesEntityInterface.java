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
package org.allbinary.data.tables.user.address;

import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface StreetAddressesEntityInterface extends BasicDataTableInterface
{      
   void remove(Integer index);
   
   void add(StreetAddress address);   
   
   //public void add(StreetAddress address,String index);
   
   void update(StreetAddress address);
   
   String getLastId();
   
   StreetAddress getDefault();
      
   void setDefault(String value);
}
