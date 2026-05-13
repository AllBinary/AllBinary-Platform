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
package org.allbinary.game.collision;

public class CollidableAlwaysPickupNeverCollideBehaviorFactory extends CollidableBaseBehaviorFactory
{
    private static final CollidableAlwaysPickupNeverCollideBehaviorFactory instance2 = new CollidableAlwaysPickupNeverCollideBehaviorFactory();

    /**
     * @return the instance2
     */
    public static CollidableAlwaysPickupNeverCollideBehaviorFactory getInstance() {
        return CollidableAlwaysPickupNeverCollideBehaviorFactory.instance2;
    }
    
    private final CollidableAlwaysPickupNeverCollideBehavior instance = new CollidableAlwaysPickupNeverCollideBehavior();

    @Override
    public CollidableBaseBehavior createBehavior()
    {
        return this.instance;
    }
}
