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
package org.allbinary.thread;

public interface RunnableInterface extends Runnable
{
    public boolean isRunning();
    public void setRunning(boolean isRunning);
    //public Thread getThread();
    public void setThread(Thread thread)throws Exception;
}
