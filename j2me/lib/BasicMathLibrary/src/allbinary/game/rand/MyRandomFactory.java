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
package allbinary.game.rand;

import java.util.Random;

public class MyRandomFactory
{
    private static final MyRandomFactory instance = new MyRandomFactory();

    public static MyRandomFactory getInstance()
    {
        return instance;
    }

    private Random rand;

    private MyRandomFactory()
    {
        rand = new Random(System.currentTimeMillis());
    }

    public void setSeed(long seed)
    {
        rand = new Random(seed);
    }

    public int getNextInt(int range)
    {
        int div = (Integer.MAX_VALUE / range) + 1;
        return rand.nextInt() / div;
    }

    public int getAbsoluteNextInt(int range)
    {
        return Math.abs(this.getNextInt(range));
    }

    public int getAbsoluteNextIntAllowZero(int range)
    {
        if (range == 0)
        {
            return 0;
        }
        else
        {
            return Math.abs(this.getNextInt(range));
        }
    }
}