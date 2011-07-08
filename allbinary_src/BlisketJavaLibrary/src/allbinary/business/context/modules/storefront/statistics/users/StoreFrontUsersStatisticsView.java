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
package allbinary.business.context.modules.storefront.statistics.users;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class StoreFrontUsersStatisticsView implements DomNodeInterface
{
   private StoreFrontUsersStatisticsInterface storeFrontUsersStatisticsInterface;
   
   public StoreFrontUsersStatisticsView(StoreFrontUsersStatisticsInterface storeFrontUsersStatisticsInterface) throws Exception
   {
       this.storeFrontUsersStatisticsInterface = storeFrontUsersStatisticsInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.storeFrontUsersStatisticsInterface.toHashMap();

      Node node = ModDomHelper.createNameValueNodes(
            document, StoreFrontUsersStatisticsData.getInstance().NAME, hashMap);

      return node;
   }
}
