package org.allbinary.game.collision;

import org.allbinary.layer.AllBinaryLayer;

public class Collision2DProcessor
extends CollisionProcessor
{
    public boolean isCollision(final AllBinaryLayer myLayer, final AllBinaryLayer myLayer2)
    {
        if (myLayer2.getXP() >= myLayer.getX2() || myLayer2.getY2() >= myLayer.getY2() || 
                myLayer2.getX2() <= myLayer.getXP() || myLayer2.getY2() <= myLayer.getYP())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
