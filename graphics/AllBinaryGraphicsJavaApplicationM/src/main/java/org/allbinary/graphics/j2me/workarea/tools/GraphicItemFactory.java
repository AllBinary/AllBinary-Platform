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
package org.allbinary.graphics.j2me.workarea.tools;

import java.util.HashMap;

import org.allbinary.log.LOGGING;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Node;

public class GraphicItemFactory
{
    private static final GraphicItemFactory instance = new GraphicItemFactory();

    /**
     * @return the instance
     */
    public static GraphicItemFactory getInstance()
    {
        return instance;
    }

   private final HashMap graphicItems;
   private final GraphicsItemInterfaceFactoryInterface DEFAULT =
           new SelectionToolFactory();

   private GraphicItemFactory()
   {
      graphicItems = new HashMap();
      graphicItems.put(LinesGraphicItem.getStaticName(), new LinesGraphicItemFactory());
   }
      
   public GraphicsItemInterfaceFactoryInterface getInstance(String itemName)
   {
       GraphicsItemInterfaceFactoryInterface graphicsItemInterfaceFactoryInterface =
               (GraphicsItemInterfaceFactoryInterface) graphicItems.get(itemName);

       if(graphicsItemInterfaceFactoryInterface == null)
       {
           graphicsItemInterfaceFactoryInterface = DEFAULT;
       }

      return graphicsItemInterfaceFactoryInterface;
   }

   public HashMap getInstance(BasicArrayList graphicItemNodeList) throws Exception
   {
      int numberOfItems = graphicItemNodeList.size();
      
      HashMap graphicItemHashMap = new HashMap();
            
      if(LOGGING.contains(LOGGING.MENUEVENT))
      {
         //GuiLog.getInstance().put("Number of Graphic Items Loaded: " + numberOfItems,"GraphicItemFactory","getInstance");
      }

      for(int index =0; index < numberOfItems; index++)
      {
         GraphicItemInterface graphicItem = GraphicItemBuilder.getInstance((Node) graphicItemNodeList.get(index));
         graphicItemHashMap.put(graphicItem.getTreeNode(),graphicItem);
      }            
      return graphicItemHashMap;
   }      
}
