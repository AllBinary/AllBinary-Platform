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
package org.allbinary.business.context.modules.storefront.statistics.advertisements;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class StoreFrontAdvertisementsStatisticsView implements DomNodeInterface
{
   private StoreFrontAdvertisementsStatisticsInterface storeFrontAdvertisementsStatisticsInterface;
   
   public StoreFrontAdvertisementsStatisticsView(StoreFrontAdvertisementsStatisticsInterface storeFrontAdvertisementsStatisticsInterface) throws Exception
   {
       this.storeFrontAdvertisementsStatisticsInterface = storeFrontAdvertisementsStatisticsInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.storeFrontAdvertisementsStatisticsInterface.toHashMap();

      Node node = 
         ModDomHelper.createNameValueNodes(
            document, StoreFrontAdvertisementsStatisticsData.getInstance().NAME, hashMap);

      return node;
   }
}
