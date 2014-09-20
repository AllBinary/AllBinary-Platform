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

package org.allbinary.game.layer;

import org.allbinary.direction.DirectionFactory;

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
