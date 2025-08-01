package org.allbinary.game.collision;

import org.allbinary.layer.AllBinaryLayer;

public class CollisionThreedProcessor
extends CollisionProcessor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    @Override
    public boolean isCollision(final AllBinaryLayer myLayer, final AllBinaryLayer myLayer2)
    {
//        if(myLayer.getName().startsWith("TempExplosionWeaponLayer") || myLayer2.getName().startsWith("TempExplosionWeaponLayer")) {
//            PreLogUtil.put(new StringMaker().append(myLayer.getName()).append(':').append(myLayer.getXP()).append(';').append(myLayer.getX2())
//                .append(',').append(myLayer.getYP()).append(';').append(myLayer.getY2()).append(',').append(myLayer.getZP()).append(';').append(myLayer.getZ2())
//                .append('?').append(myLayer2.getName()).append(':').append(myLayer2.getX()).append(';').append(myLayer2.getX2())
//                .append(',').append(myLayer2.getY()).append(';').append(myLayer2.getY2()).append(',').append(myLayer2.getZ()).append(';').append(myLayer2.getZ2()).toString(), this, damageUtil.IS_COLLISION);
//        }

        if (myLayer2.getXP() >= myLayer.getX2() || myLayer2.getYP() >= myLayer.getY2() || 
                myLayer2.getX2() <= myLayer.getXP() || myLayer2.getY2() <= myLayer.getYP() ||
                myLayer2.getZP() < myLayer.getZ2() || myLayer2.getZ2() > myLayer.getZP())
        {
                return false;
        }
        else
        {
                //PreLogUtil.put(myLayer2.getZ() + " > " + myLayer.getZ2() + " || " + myLayer2.getZ2() + " < " + myLayer.getZP(), this, damageUtil.IS_COLLISION);
                return true;
        }
    }
}
