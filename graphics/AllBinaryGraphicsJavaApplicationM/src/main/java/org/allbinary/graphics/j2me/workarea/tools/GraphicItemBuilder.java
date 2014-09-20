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

import org.w3c.dom.Node;

public class GraphicItemBuilder
{
   private GraphicItemBuilder()
   {
   }
   
   public static GraphicItemInterface getInstance(Node node) throws Exception
   {
      String graphicItemName = node.getNodeName();      

      if(graphicItemName.compareTo(PointsDomUtil.getInstance().LINES) == 0)
      {
          return new LinesGraphicItem(node);
      }
      else
      {
          throw new Exception("Graphic Item Does Not Exist: " + graphicItemName);
      }
   }
}
