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
public class GeographicPathFinderBase {
   
   public BasicArrayList search(
      BasicArrayList startPathFindingNodeList, 
      BasicArrayList endPathFindingNodeList, int totalPaths)
      throws Exception {
       throw new RuntimeException();
   }

   public BasicArrayList searchN(
      BasicArrayList startPathFindingNodeList, 
      BasicArrayList endPathFindingNodeList, int totalPaths, final MultipassState multipassState)
      throws Exception {
       throw new RuntimeException();
   }

}
