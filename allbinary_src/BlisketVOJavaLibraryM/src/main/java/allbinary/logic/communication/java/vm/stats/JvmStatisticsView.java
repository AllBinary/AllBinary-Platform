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
package allbinary.logic.communication.java.vm.stats;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.logic.communication.java.vm.stats.JvmStatisticsData;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class JvmStatisticsView extends JvmStatisticsMapping implements DomNodeInterface
{
   public JvmStatisticsView()
   {
      super();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.toHashMap();
      
      Node node = 
         ModDomHelper.createNameValueNodes(
            document, JvmStatisticsData.NAME, hashMap);
      
      return node;
   }
}
