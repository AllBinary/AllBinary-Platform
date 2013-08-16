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
package org.allbinary.game.testgamedemo.canvas;

import allbinary.animation.ColorLessVectorAnimation;
import allbinary.animation.IndexedAnimation;
import allbinary.animation.VectorExplosionGenerator;
import allbinary.animation.special.TitleAnimation;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;

public class TitleAnimationFactory
{
    public static final TitleAnimation getInstance() throws Exception
    {
        final IndexedAnimation[] animationInterfaceArray = new IndexedAnimation[2];
        
        final BasicColor[] basicColorArray = new BasicColor[2];
        basicColorArray[0] = BasicColorFactory.getInstance().GREEN;
        basicColorArray[1] = BasicColorFactory.getInstance().GREY;
        
        final int[] deltaXArray = new int[2];
        deltaXArray[0] = 0;
        deltaXArray[1] = 52;

        final int[] deltaYArray = new int[2];
        deltaYArray[0] = 0;
        deltaYArray[1] = 30;

        final TitleVectorData titleVectorData = new TitleVectorData();

        final VectorExplosionGenerator vectorExplosionGenerator = 
                VectorExplosionGenerator.getInstance();
        
        int[][][] points = vectorExplosionGenerator.getInstance(
                titleVectorData.zeptoPoints, 6, 
                vectorExplosionGenerator.RANDOM);

        animationInterfaceArray[0] = new ColorLessVectorAnimation(points);

        points = vectorExplosionGenerator.getInstance(
                titleVectorData.racerPoints, 6, 
                vectorExplosionGenerator.RANDOM);

        animationInterfaceArray[1] = new ColorLessVectorAnimation(points);
        
        return new TitleAnimation(animationInterfaceArray, 
                basicColorArray, deltaXArray, deltaYArray, 15, 120);
    }
}
