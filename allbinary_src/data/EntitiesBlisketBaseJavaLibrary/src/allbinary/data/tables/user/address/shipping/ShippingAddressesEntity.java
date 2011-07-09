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
package allbinary.data.tables.user.address.shipping;

import allbinary.data.tables.user.address.StreetAddressesEntity;

public class ShippingAddressesEntity extends StreetAddressesEntity
{     
   private final String TABLENAME = "shipping";

   public ShippingAddressesEntity(String userName)
   {
      super(userName);
      setTableName(TABLENAME);
   }   
}
