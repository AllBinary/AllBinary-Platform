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

import allbinary.direction.DirectionFactory;

public class LayerPlacementTypeFactory
{
    private static final LayerPlacementTypeFactory instance = new LayerPlacementTypeFactory();
    
    public static LayerPlacementTypeFactory getInstance()
    {
        return instance;
    }    
    
    public final LayerPlacementType MAP =
        new LayerPlacementType(Integer.MAX_VALUE);
     public final LayerPlacementType LEFT =
        new LayerPlacementType(DirectionFactory.getInstance().LEFT.getValue());
     public final LayerPlacementType DOWN =
        new LayerPlacementType(DirectionFactory.getInstance().DOWN.getValue());
     public final LayerPlacementType UP =
        new LayerPlacementType(DirectionFactory.getInstance().UP.getValue());
     public final LayerPlacementType RIGHT =
        new LayerPlacementType(DirectionFactory.getInstance().RIGHT.getValue());    
}
