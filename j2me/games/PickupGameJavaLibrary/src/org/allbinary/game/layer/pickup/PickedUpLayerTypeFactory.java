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
package org.allbinary.game.layer.pickup;

public class PickedUpLayerTypeFactory
{
    private static final PickedUpLayerTypeFactory instance = 
        new PickedUpLayerTypeFactory();
    
    public static PickedUpLayerTypeFactory getInstance()
    {
        return instance;
    }

    public PickedUpLayerType PART = new PickedUpLayerType("Part");

    public PickedUpLayerType BEAM = new PickedUpLayerType("BEAM");
    public PickedUpLayerType MINE = new PickedUpLayerType("MINE");
    public PickedUpLayerType PROJECTILE = new PickedUpLayerType("PROJECTILE");

    public PickedUpLayerType ENHANCEMENT = new PickedUpLayerType("Enhancement");
    public PickedUpLayerType HEAL = new PickedUpLayerType("Heal");
    public PickedUpLayerType LIFE = new PickedUpLayerType("Life");
    public PickedUpLayerType POINTS = new PickedUpLayerType("Points");
}
