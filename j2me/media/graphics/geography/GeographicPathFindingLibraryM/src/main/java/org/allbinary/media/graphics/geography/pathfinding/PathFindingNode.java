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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public class PathFindingNode
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   public PathFindingNode parent;

   public GeographicMapCellPosition geographicMapCellPosition;
   
   public PathFindingNode(PathFindingNode parent,
      GeographicMapCellPosition geographicMapCellPosition) 
      throws Exception
   {
      this.setParent(parent);
      this.setGeographicMapCellPosition(geographicMapCellPosition);

      /*
      if(this.getParent() == null)
      {
         logUtil.put("No Parent", this, commonStrings.CONSTRUCTOR);
      }
      */

      if(this.geographicMapCellPosition == null)
      {
         throw new Exception("No GeographicMapCellPosition");
      }

   }

   private void setParent(PathFindingNode parent)
   {
      this.parent = parent;
   }

   public void setGeographicMapCellPosition(GeographicMapCellPosition geographicMapCellPosition)
   {
      this.geographicMapCellPosition = geographicMapCellPosition;
   }
   
   public String toString()
   {
       StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append(this.getClass().getName());
      stringBuffer.append(CommonLabels.getInstance().COLON_SEP);
      stringBuffer.append(" Path: ");
      stringBuffer.append(this.geographicMapCellPosition.toString());
     
      PathFindingNode pathFindingNode = this.parent;
      while(pathFindingNode != null)
      {
         stringBuffer.append(pathFindingNode.geographicMapCellPosition.toString());
         stringBuffer.append(CommonSeps.getInstance().SPACE);
         pathFindingNode = pathFindingNode.parent;
      }
      
      return stringBuffer.toString();
   }
}
