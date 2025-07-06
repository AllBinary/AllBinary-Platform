/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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

package org.allbinary.game.layer.capital;

public class Capital
{
    private int value;

    public Capital()
    {
    }

    public int getTotalMoney()
    {
        return value;
    }

    public void removeMoney(int points)
    {
        this.value -= points;
    }

    public void addMoney(int points)
    {
        this.value += points;
    }

}
