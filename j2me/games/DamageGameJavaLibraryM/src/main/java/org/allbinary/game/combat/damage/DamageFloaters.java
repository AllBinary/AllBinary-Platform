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
package org.allbinary.game.combat.damage;

import org.allbinary.graphics.paint.Paintable;

public class DamageFloaters extends Paintable
{
    private static final DamageFloaters SINGLETON = new DamageFloaters();
    
    public static DamageFloaters getInstance()
    {
        return SINGLETON;
    }
    
    public void add(int damage)
    {
    }
}
