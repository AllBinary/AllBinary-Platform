/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: August 2, 2006
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.layer;

import java.util.Hashtable;

public interface LayerPlacementInterface {

   int getWidth();
   int getHeight();
   LayerPlacementType getLayerType();
   Hashtable getInstance() throws Exception;
}
