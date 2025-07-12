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
package org.allbinary.business.context.modules.storefront.statistics.inventory;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class StoreFrontInventoryStatisticsView implements DomNodeInterface
{
   private StoreFrontInventoryStatisticsInterface storeFrontInventoryStatisticsInterface;
   
   public StoreFrontInventoryStatisticsView(StoreFrontInventoryStatisticsInterface storeFrontInventoryStatisticsInterface) throws Exception
   {
       this.storeFrontInventoryStatisticsInterface = storeFrontInventoryStatisticsInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.storeFrontInventoryStatisticsInterface.toHashMap();

      Node node = 
         ModDomHelper.createNameValueNodes(
            document, StoreFrontInventoryStatisticsData.getInstance().NAME, hashMap);

      return node;
   }
}
