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
package views.generic.inventory;


import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.HttpStoreComponentView;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.user.commerce.inventory.InventoryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemView;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.control.search.SearchData;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class InventoryView extends HttpStoreComponentView 
   implements DomNodeInterface
{   
   public InventoryView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Starting",this,"toXmlNode"));
         }
         
         InventoryEntity inventoryEntityInterface = 
            InventoryEntityFactory.getInstance().getInventoryEntityInstance();
         
         Node inventoryNode = document.createElement(InventoryData.INVENTORY);
         
         Vector itemVector = inventoryEntityInterface.getItems(
            StoreFrontFactory.getInstance(this.getTransformInfoInterface().getStoreName()));
         
         inventoryNode.appendChild(
         ModDomHelper.createNameValueNodes(document,
         SearchData.TOTAL_NUMBER_ITEMS_ON_THIS_PAGE,
         new Integer(itemVector.size()).toString()));
         
         final int size = itemVector.size();
         for (int index = 0; index < size; index++)
         {
            //String product = new String((String) iter.next());
            //InventoryEntityFactory.getInstance().getItem(product);
            ItemInterface itemInterface = (ItemInterface) itemVector.get(index);
            if(itemInterface!=null)
            {
               Node node = new BasicItemView(itemInterface, new Vector()).toXmlNode(document);
               inventoryNode.appendChild(node);
            }
            else
            {
               LogUtil.put(LogFactory.getInstance("Inventory",this,"toXmlNode"));
            }
         }
         
         return inventoryNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"toXmlNode",e));
         }
         throw e;
      }
   }
   
   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view()
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view Inventory";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"view()",e));
         }
         return error;
      }
   }
}
