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
package org.allbinary.graphics.pipeline;


import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.math.AngleFactory;
import org.allbinary.util.BasicArrayList;

public class RandomRotationFactory
{
    private static final RandomRotationFactory instance = new RandomRotationFactory();
    
    public static RandomRotationFactory getInstance()
    {
        return instance;
    }

    private final MyRandomFactory myRandomFactory = MyRandomFactory.getInstance();

    private final AngleFactory angleFactory = AngleFactory.getInstance();

    public BasicArrayList getInstance(BasicArrayList vector, int howMuch)
            throws Exception
    {
        BasicGraphicsPipeline graphicsPipe = new BasicGraphicsPipeline(vector);
        graphicsPipe.createMatrix();
        graphicsPipe.rotate(angleFactory.getInstance(getNextRandomAngle(howMuch)));

        return graphicsPipe.getMatrix();
    }

    public int getNextRandomAngle(int howMuch)
    {
        return myRandomFactory.getNextInt(howMuch * 10);// / (Integer.MAX_VALUE / ());
    }
}
