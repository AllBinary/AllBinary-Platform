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
package org.allbinary.game.layer.pickup.health;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class HealthResources {

    private static final HealthResources SINGLETON = new HealthResources();
    
    public String RESOURCE = "health_drop_20_by_20.png";

    private HealthResources()
    {
    }

    public static HealthResources getInstance()
    {
        return SINGLETON;
    }
}
