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
package allbinary.business.user.commerce.inventory.item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import java.util.Vector;

public class BasicItemView implements DomNodeInterface
{
   private final ItemInterface itemInterface;
   private final Vector vector;

   public BasicItemView(ItemInterface itemInterface, Vector vector)
   {
      this.itemInterface = itemInterface;
      this.vector = vector;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
       if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRODUCTSEARCHLOGGING))
       {
          LogUtil.put(LogFactory.getInstance("Start", this, "toXmlNode"));
       }

      /*
      try
      {
       */
         HashMap hashMap = itemInterface.toHashMap();
         
         final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
         
         hashMap.put(BasicItemData.IMAGE, EMPTY_STRING);
         
         StringUtil stringUtil = StringUtil.getInstance();
         
         Set keySet = hashMap.keySet();
         Iterator iterSet = keySet.iterator();
         
         Node node = document.createElement(BasicItemData.ITEM);
         
         while(iterSet.hasNext())
         {
            String name = (String) iterSet.next();
            String value = (String) hashMap.get(name);
            
            value = stringUtil.getInstance(value);
            
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }

         Node totalNode = ModDomHelper.createNameValueNodes(document,
             BasicItemData.TOTAL, this.itemInterface.getTotal().toString());

         node.appendChild(totalNode);
         
         final int size = this.vector.size();
         for(int index = 0; index < size; index++)
         {
             DomNodeInterface domNodeInterface =
                 (DomNodeInterface) this.vector.get(index);

             Node customNode = domNodeInterface.toXmlNode(document);

             node.appendChild(customNode);
         }
         
         return node;
         /*
      }
      catch(Exception e)
      {
         throw e;
      }
          */
   }
   
   public Document toXmlDoc()
   {
      return null;
   }
   
   public String view()
   {
      return null;
   }
   
}
