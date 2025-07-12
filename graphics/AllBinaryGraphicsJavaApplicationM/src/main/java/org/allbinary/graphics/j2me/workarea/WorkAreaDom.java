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
package org.allbinary.graphics.j2me.workarea;

import org.allbinary.dom.DomHelper;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorkAreaDom
{
   public static final String WORKAREA = "workArea";
   public static final String NAME = "name";
   public static final String FRAMES = "frames";
   
   private String name;
   private BasicArrayList canvasNodeList;
   
   public WorkAreaDom(Document document) throws Exception
   {
      NodeList workAreaNodeList = document.getElementsByTagName(this.WORKAREA);
      
      if(workAreaNodeList!=null)
      {
         int numberOfworkAreas = workAreaNodeList.getLength();
         
         if(numberOfworkAreas == 1)
         {            
            int numberOfNodes = workAreaNodeList.getLength();
            
            //find name node
            Node nameNode = DomHelper.getInstance().searchNodeList(this.NAME,workAreaNodeList.item(0).getChildNodes());
            Node nameTextNode = nameNode.getFirstChild();
            this.name = nameTextNode.getNodeValue();
         }
         else 
         {
            throw new Exception("Wrong Number of WorkAreas: " + numberOfworkAreas);
         }
         
         this.canvasNodeList = DomHelper.getInstance().getChildrenWithoutTextNodes(this.FRAMES,workAreaNodeList.item(0).getChildNodes());
      }
      else
      {
         throw new Exception("workArea Node Not Found");
      }
   }
   
   public String getName()
   {
      return this.name;
   }
   
   public BasicArrayList getCanvasNodes()
   {
      return this.canvasNodeList;
   }
}
