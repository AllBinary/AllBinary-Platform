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
package allbinary.game.ai;

import java.util.Hashtable;

import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.time.TimeDelayHelper;

public class InputProbability
{
    public static Integer INPUT_PROBABILITY = SmallIntegerSingletonFactory.getInstance().getInstance(0);
    
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
        return likelyhoodIntegerArray;
    }

    public Integer[] getRepeatLikelyhoodIntegerArray()
    {
        return repeatLikelyhoodIntegerArray;
    }

    public int getMax()
    {
        return max;
    }

    public TimeDelayHelper getTimeDelayHelper()
    {
        return timeDelayHelper;
    }
    
    public Hashtable toHashtable()
    {
        Hashtable hashtable = new Hashtable();
        
        hashtable.put(INPUT_PROBABILITY, this);
        
        return hashtable;
    }
}
