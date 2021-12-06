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
package org.allbinary.data.tables.user.commerce.inventory.item.options;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class XmlOptionItemsEntityFactory
{
    private static final XmlOptionItemsEntityFactory instance =
            new XmlOptionItemsEntityFactory();

    /**
     * @return the instance
     */
    public static XmlOptionItemsEntityFactory getInstance() {
        return instance;
    }

   //private static final String CLASSNAME = "org.allbinary.data.tables.InventoryEntity";
   
   private XmlOptionItemsEntityFactory()
   {
   }
   
   public XmlOptionItemsEntity getXmlOptionItemsEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (InventoryEntityInterface) InterfaceCastProxy.newInstance(object);
         return new org.allbinary.data.tables.user.commerce.inventory.item.options.XmlOptionItemsEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"InventoryEntityFactory","getInstance()",e);
         }
         throw e;
      }
      */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"InventoryEntityFactory","getInstance()",e));
         }
         return null;
      }
   }
   
}
