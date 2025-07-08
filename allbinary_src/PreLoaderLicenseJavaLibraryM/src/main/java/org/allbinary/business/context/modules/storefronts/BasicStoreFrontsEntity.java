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
package org.allbinary.business.context.modules.storefronts;

import java.util.HashMap;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.init.InitSql;
import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.business.context.modules.storefront.StoreFrontData;

public class BasicStoreFrontsEntity extends InitSql
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected final String tableName = "storefronts";

   public BasicStoreFrontsEntity()
   {
      super(new UserDbInitInfo());
      this.setTable(tableName);
   }

   public BasicStoreFrontInterface getStoreFrontInterface(String name) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, name);
      HashMap storeHashMap = super.getRow(keysAndValues);
      if(storeHashMap==null) throw new Exception("No Such Store: " + name);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
      {
         logUtil.put("StoreFront: " + storeHashMap.toString(), this, "getStore()");
      }
      
      return (BasicStoreFrontInterface) new BasicStoreFront(storeHashMap);
   }      
}
