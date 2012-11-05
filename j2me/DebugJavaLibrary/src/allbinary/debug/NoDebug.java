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
package allbinary.debug;

public class NoDebug implements DebugInterface
{
   private static DebugInterface SINGLETON = new NoDebug();
   
   public static DebugInterface getInstance()
   {
      return SINGLETON;
   }
   
    public void start()
    {
        
    }
    
    public void stop()
    {
        
    }

    public long getMaxTime()
    {
        return 10000;
    }
    
    public long getStartTime()
    {
        return 0;
    }
    
    public boolean isRunning()
    {
        return false;
    }
}
