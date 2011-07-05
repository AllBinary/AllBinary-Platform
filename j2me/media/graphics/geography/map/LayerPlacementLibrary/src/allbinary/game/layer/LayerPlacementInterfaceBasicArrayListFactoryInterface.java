/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.layer;

import org.allbinary.util.BasicArrayList;

public interface LayerPlacementInterfaceBasicArrayListFactoryInterface {

    BasicArrayList getInstance();
    
    int[] getOnDemandResources();
}
