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
package org.allbinary.data.tables.user.address.shipping;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.user.address.StreetAddressesEntity;

public class ShippingAddressesEntityFactory
{
    private static final ShippingAddressesEntityFactory instance =
            new ShippingAddressesEntityFactory();

    /**
     * @return the instance
     */
    public static ShippingAddressesEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "org.allbinary.data.tables.ShippingAddressesEntity";
   
   private ShippingAddressesEntityFactory()
   {
   }
   
   public StreetAddressesEntity getInstance(String userName)// throws LicensingException
   {
      try
      {
      /*
      Object[] params = new Object[1];
       
      Class[] classes = new Class[1];
      classes[0] = userName.getClass();
       
      params[0] = (Object) userName;
      return (StreetAddressesEntityInterface) AbeFactory.getInstance().getInstance(CLASSNAME, classes, params);
       */
         return new ShippingAddressesEntity(userName);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"ShippingAddressesEntityFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "ShippingAddressesEntityFactory", "getShippingAddressesEntityInstance()", e));
         }
         return null;
      }
   }
}
