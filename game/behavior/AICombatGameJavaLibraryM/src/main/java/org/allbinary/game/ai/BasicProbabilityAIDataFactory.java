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
package org.allbinary.game.ai;

import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;

import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.time.NoTimeDelayHelper;

public class BasicProbabilityAIDataFactory
{
    public Hashtable getInstance()
    {        
        Integer[][] likelyhoodIntegerArray = new Integer[Canvas.KEY_NUM9 + 1][2];

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        likelyhoodIntegerArray[Canvas.UP][0] = smallIntegerSingletonFactory.getInstance(0);
        likelyhoodIntegerArray[Canvas.UP][1] = smallIntegerSingletonFactory.getInstance(10);

        likelyhoodIntegerArray[Canvas.DOWN][0] = smallIntegerSingletonFactory.getInstance(10);
        likelyhoodIntegerArray[Canvas.DOWN][1] = smallIntegerSingletonFactory.getInstance(14);
        
        likelyhoodIntegerArray[Canvas.LEFT][0] = smallIntegerSingletonFactory.getInstance(14);
        likelyhoodIntegerArray[Canvas.LEFT][1] = smallIntegerSingletonFactory.getInstance(18);
        likelyhoodIntegerArray[Canvas.RIGHT][0] = smallIntegerSingletonFactory.getInstance(18);
        likelyhoodIntegerArray[Canvas.RIGHT][1] = smallIntegerSingletonFactory.getInstance(22);

        likelyhoodIntegerArray[Canvas.KEY_NUM7][0] = smallIntegerSingletonFactory.getInstance(22);
        likelyhoodIntegerArray[Canvas.KEY_NUM7][1] = smallIntegerSingletonFactory.getInstance(26);

        likelyhoodIntegerArray[Canvas.KEY_NUM9][0] = smallIntegerSingletonFactory.getInstance(26);
        likelyhoodIntegerArray[Canvas.KEY_NUM9][1] = smallIntegerSingletonFactory.getInstance(30);
        
        likelyhoodIntegerArray[Canvas.KEY_NUM1][0] = smallIntegerSingletonFactory.getInstance(30);
        likelyhoodIntegerArray[Canvas.KEY_NUM1][1] = smallIntegerSingletonFactory.getInstance(40);

        Integer[] repeatLikelyhoodIntegerArray = new Integer[Canvas.KEY_NUM9 + 1];

        repeatLikelyhoodIntegerArray[Canvas.UP] = smallIntegerSingletonFactory.getInstance(0);

        repeatLikelyhoodIntegerArray[Canvas.DOWN] = smallIntegerSingletonFactory.getInstance(0);
        
        repeatLikelyhoodIntegerArray[Canvas.LEFT] = smallIntegerSingletonFactory.getInstance(0);

        repeatLikelyhoodIntegerArray[Canvas.RIGHT] = smallIntegerSingletonFactory.getInstance(0);
        
        repeatLikelyhoodIntegerArray[Canvas.KEY_NUM7] = smallIntegerSingletonFactory.getInstance(0);

        repeatLikelyhoodIntegerArray[Canvas.KEY_NUM9] = smallIntegerSingletonFactory.getInstance(0);
        
        repeatLikelyhoodIntegerArray[Canvas.KEY_NUM1] = smallIntegerSingletonFactory.getInstance(10);

        InputProbability inputProbability = new InputProbability(
                likelyhoodIntegerArray, repeatLikelyhoodIntegerArray, 42, NoTimeDelayHelper.SINGLETON);

        return inputProbability.toHashtable();
    }
}
