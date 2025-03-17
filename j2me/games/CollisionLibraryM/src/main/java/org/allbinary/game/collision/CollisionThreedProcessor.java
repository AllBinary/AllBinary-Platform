package org.allbinary.game.collision;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;

public class CollisionThreedProcessor
extends CollisionProcessor
{
    public boolean isCollision(final AllBinaryLayer myLayer, final AllBinaryLayer myLayer2)
    {
//        if(myLayer.getName().startsWith("TempExplosionWeaponLayer") || myLayer2.getName().startsWith("TempExplosionWeaponLayer")) {
//            PreLogUtil.put(new StringMaker().append(myLayer.getName()).append(':').append(myLayer.getX()).append(';').append(myLayer.getX2())
//                .append(',').append(myLayer.getY()).append(';').append(myLayer.getY2()).append(',').append(myLayer.getZ()).append(';').append(myLayer.getZ2())
//                .append('?').append(myLayer2.getName()).append(':').append(myLayer2.getX()).append(';').append(myLayer2.getX2())
//                .append(',').append(myLayer2.getY()).append(';').append(myLayer2.getY2()).append(',').append(myLayer2.getZ()).append(';').append(myLayer2.getZ2()).toString(), this, "isCollision");
//        }

        if (myLayer2.getX() >= myLayer.getX2() || myLayer2.getY() >= myLayer.getY2() || 
                myLayer2.getX2() <= myLayer.getX() || myLayer2.getY2() <= myLayer.getY() ||
                myLayer2.getZ() < myLayer.getZ2() || myLayer2.getZ2() > myLayer.getZ())
        {
                return false;
        }
        else
        {
                //PreLogUtil.put(myLayer2.getZ() + " > " + myLayer.getZ2() + " || " + myLayer2.getZ2() + " < " + myLayer.getZ(), this, "isCollision");
                return true;
        }
    }
}
