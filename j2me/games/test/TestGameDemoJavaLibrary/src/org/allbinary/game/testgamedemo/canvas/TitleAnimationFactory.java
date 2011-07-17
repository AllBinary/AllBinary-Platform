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
import allbinary.animation.TitleAnimation;
import allbinary.animation.VectorExplosionGenerator;
import allbinary.graphics.color.BasicColor;

public class TitleAnimationFactory
{
    public static final TitleAnimation getInstance() throws Exception
    {
        IndexedAnimation[] animationInterfaceArray = new IndexedAnimation[2];
        
        BasicColor[] basicColorArray = new BasicColor[2];
        basicColorArray[0] = BasicColor.GREEN;
        basicColorArray[1] = BasicColor.GREY;
        
        int[] deltaXArray = new int[2];
        deltaXArray[0] = 0;
        deltaXArray[1] = 52;

        int[] deltaYArray = new int[2];
        deltaYArray[0] = 0;
        deltaYArray[1] = 30;

        TitleVectorData titleVectorData = new TitleVectorData();

        int[][][] points = VectorExplosionGenerator.getInstance(
                titleVectorData.zeptoPoints, 6, VectorExplosionGenerator.RANDOM);

        animationInterfaceArray[0] = new ColorLessVectorAnimation(points);

        points = VectorExplosionGenerator.getInstance(
                titleVectorData.racerPoints, 6, VectorExplosionGenerator.RANDOM);

        animationInterfaceArray[1] = new ColorLessVectorAnimation(points);
        
        return new TitleAnimation(animationInterfaceArray, 
                basicColorArray, deltaXArray, deltaYArray, 15, 120);
    }
}
