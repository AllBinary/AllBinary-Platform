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
package org.allbinary.business.user.commerce.inventory.item;

import java.util.HashMap;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Vector;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.string.CommonStrings;

public class BasicItemView implements DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final ItemInterface itemInterface;
   private final Vector vector;

   public BasicItemView(ItemInterface itemInterface, Vector vector)
   {
      this.itemInterface = itemInterface;
      this.vector = vector;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
       if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
       {
          LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "toXmlNode"));
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
         
         Node node = document.createElement(BasicItemData.ITEM);

         final Object[] nameArray = keySet.toArray();
         final int size2 = nameArray.length;
         for (int index = 0; index < size2; index++)
         {
            String name = (String) nameArray[index];
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
