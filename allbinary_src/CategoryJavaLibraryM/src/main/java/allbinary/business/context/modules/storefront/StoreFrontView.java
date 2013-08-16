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
package allbinary.business.context.modules.storefront;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.inventory.basket.BasketData;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class StoreFrontView implements DomNodeInterface
{
   private StoreFrontInterface storeFrontInterface;
   
   public StoreFrontView(StoreFrontInterface storeFrontInterface)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Constructing: StoreFrontView for: " + storeFrontInterface.getName(), this, "getInstance()"));
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