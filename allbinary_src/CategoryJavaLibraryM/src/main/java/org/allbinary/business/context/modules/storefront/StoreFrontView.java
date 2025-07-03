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
package org.allbinary.business.context.modules.storefront;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.inventory.basket.BasketData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class StoreFrontView implements DomNodeInterface
{
   private StoreFrontInterface storeFrontInterface;
   
   public StoreFrontView(StoreFrontInterface storeFrontInterface)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
          final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance("Constructing: StoreFrontView for: " + storeFrontInterface.getName(), this, commonStrings.GET_INSTANCE));
      }

      this.storeFrontInterface = storeFrontInterface;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.storeFrontInterface.toHashMap();
      
      StoreFrontData storeFrontData = StoreFrontData.getInstance();
      
      hashMap.put(storeFrontData.CURRENTHOMEHOSTNAME, this.storeFrontInterface.getCurrentHomeHostName());
      hashMap.put(storeFrontData.CURRENTHOMEHOSTNAMEPATH, this.storeFrontInterface.getCurrentHomeHostNamePath());
      hashMap.put(storeFrontData.CURRENTHOSTNAME, this.storeFrontInterface.getCurrentHostName());
      hashMap.put(storeFrontData.CURRENTHOSTNAMEPATH, this.storeFrontInterface.getCurrentHostNamePath());
      hashMap.put(BasketData.NAME, this.storeFrontInterface.getBasketName());
            
      Node node = 
         ModDomHelper.createNameValueNodes(
            document, storeFrontData.NAME, hashMap);

      return node;
   }
}