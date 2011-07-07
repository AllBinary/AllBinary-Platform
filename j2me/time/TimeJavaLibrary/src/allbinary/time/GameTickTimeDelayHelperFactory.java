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
package allbinary.time;


public class GameTickTimeDelayHelperFactory
{
    private static final GameTickTimeDelayHelperFactory instance = new GameTickTimeDelayHelperFactory();
    
//  private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
    //      GameTickTimeDelayHelperFactory.getInstance();
    
    public static GameTickTimeDelayHelperFactory getInstance()
    {
        return instance;
    }
    
    private GameTickTimeDelayHelperFactory()
    {
    }
    
    private long startTime = -1;
    
    public long getStartTime() 
    {
        return startTime;
    }

    public long setStartTime()
    {
        this.startTime = System.currentTimeMillis();
        return this.startTime;
    }
    
}
