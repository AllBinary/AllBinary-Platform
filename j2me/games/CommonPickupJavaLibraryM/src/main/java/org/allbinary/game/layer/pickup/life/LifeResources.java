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
package org.allbinary.game.layer.pickup.life;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class LifeResources {

    private static final LifeResources SINGLETON = new LifeResources();
    
    public String RESOURCE = "life_drop_20_by_20.png";

    private LifeResources()
    {
    }

    public static LifeResources getInstance()
    {
        return SINGLETON;
    }
}
