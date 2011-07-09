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
package allbinary.data.tables.user.address.billing;

import allbinary.data.tables.user.address.StreetAddressesEntity;

public class BillingAddressesEntity extends StreetAddressesEntity
{
   private final String TABLENAME="billing";

   public BillingAddressesEntity(String userName)
   {
      super(userName);      
      setTableName(TABLENAME);
   }   
}
