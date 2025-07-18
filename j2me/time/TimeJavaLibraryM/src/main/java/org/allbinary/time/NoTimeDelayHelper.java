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
package org.allbinary.time;

/**
 *
 * @author user
 */
public class NoTimeDelayHelper extends TimeDelayHelper {

    public static final NoTimeDelayHelper SINGLETON = new NoTimeDelayHelper();

    public NoTimeDelayHelper()
    {
        super(0);
    }

    @Override
    public boolean isTime()
    {
        return true;
    }
    
    @Override
    public boolean isTimeSince(int delay)
    {
        return true;
    }
    
    @Override
    public boolean isTime(long currentTime)
    {
        return true;
    }

    @Override
    public boolean isTimeSince(int delay, long currentTime)
    {
        return true;
    }
    
}
