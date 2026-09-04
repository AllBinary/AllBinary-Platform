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
package org.allbinary.game.ag.ai;

import org.allbinary.logic.StdUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.ABHashtable;

public class InputProbability
{
    public static Integer INPUT_PROBABILITY = SmallIntegerSingletonFactory.getInstance().getAt(0);
    
    private final Integer[][] likelyhoodIntegerArray;
    private final Integer[] repeatLikelyhoodIntegerArray;
    private final int max;
    private final TimeDelayHelper timeDelayHelper;
    
    public InputProbability(Integer[][] likelyhoodIntegerArray, 
            Integer[] repeatLikelyhoodIntegerArray, 
            int max, TimeDelayHelper timeDelayHelper)
    {
        this.likelyhoodIntegerArray = likelyhoodIntegerArray;
        this.repeatLikelyhoodIntegerArray = repeatLikelyhoodIntegerArray;
        this.max = max;
        this.timeDelayHelper = timeDelayHelper;
    }
    
    public Integer[][] getLikelyhoodIntegerArray()
    {
        return this.likelyhoodIntegerArray;
    }

    public Integer[] getRepeatLikelyhoodIntegerArray()
    {
        return this.repeatLikelyhoodIntegerArray;
    }

    public int getMax()
    {
        return this.max;
    }

    public TimeDelayHelper getTimeDelayHelper()
    {
        return this.timeDelayHelper;
    }
    
    public ABHashtable toHashtable()
    {
        ABHashtable hashtable = StdUtil.getInstance().createHashtable();
        
        hashtable.put(InputProbability.INPUT_PROBABILITY, this);
        
        return hashtable;
    }
}
