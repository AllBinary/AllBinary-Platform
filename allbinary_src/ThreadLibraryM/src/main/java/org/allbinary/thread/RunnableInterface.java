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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;


@JsType
public interface RunnableInterface extends Runnable
{
    @JsMethod
    boolean isRunning();
    @JsMethod
    void setRunning(boolean isRunning);
    //Thread getThread();
    @JsMethod
    void setThread(Thread thread)throws Exception;
}
