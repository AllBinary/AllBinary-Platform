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

import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
//1.4.2
public class PathFinderGraphVisitorBase {

   public void visit(final Object graph, //SimpleWeightedGraph
      final BasicArrayList startPathFindingNodeList,
      final BasicArrayList endPathFindingNodeList)
      throws Exception {
       throw new RuntimeException();
   }
   
   public void fixPath(final BasicArrayList startPathFindingNodeList,
      final BasicArrayList endPathFindingNodeList, final BasicArrayList pathList)
      throws Exception {
       throw new RuntimeException();
   }
   
   public boolean isValid(final Object graphPath) { //GraphPath
       throw new RuntimeException();
   }

   public String getInvalidReason(final Object graphPath) { //GraphPath
       throw new RuntimeException();
   }
   
}
