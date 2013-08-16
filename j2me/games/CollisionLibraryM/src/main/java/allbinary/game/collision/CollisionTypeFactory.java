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
package allbinary.game.collision;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class CollisionTypeFactory {

    private static CollisionTypeFactory SINGLETON = new CollisionTypeFactory();

    public CollisionType NONE = new CollisionType("None");
    public CollisionType DAMAGE = new CollisionType("Damage");
    public CollisionType PICKUP = new CollisionType("Pickup");
    public CollisionType COLLISION = new CollisionType("Collision");
    
    public static CollisionTypeFactory getInstance()
    {
        return SINGLETON;
    }
}
