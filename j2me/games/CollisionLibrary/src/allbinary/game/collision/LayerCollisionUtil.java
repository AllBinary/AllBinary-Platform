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

import allbinary.layer.AllBinaryLayer;

public class LayerCollisionUtil
{

    private LayerCollisionUtil()
    {
    }

    public static boolean isCollision(AllBinaryLayer myLayer,
            AllBinaryLayer myLayer2)
    {
        if (myLayer2.getX() >= myLayer.getX2() || myLayer2.getY() >= myLayer.getY2() || myLayer2.getX2() <= myLayer.getX() || myLayer2.getY2() <= myLayer.getY())
        {
            return false;
        }
        else
        {
            return true;
        }

        /*
         * return RectangleCollisionUtil.isCollision( myLayer.getX(),
         * myLayer.getY(), myLayer.getX2(), myLayer.getY2(), myLayer2.getX(),
         * myLayer2.getY(), myLayer2.getX2(), myLayer2.getY2() );
         */
    }
}
