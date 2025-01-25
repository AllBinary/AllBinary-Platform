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
package org.allbinary.android;

public class AndroidStrings
{
    private static final AndroidStrings instance = new AndroidStrings();

    public static AndroidStrings getInstance()
    {
        return instance;
    }
    
    public final String CONTEXT = "Context: ";
    
    public final String FINISH = "finish";
    public final String CREATE = "onCreate";
    public final String START = "onStart";
    public final String RESTART = "onRestart";
    public final String RESUME = "onResume";
    public final String PAUSE = "onPause";
    public final String STOP = "onStop";
    public final String DESTROY = "onDestroy";
    
    public final String BIND = "onBind";
    
    public final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    public final String ON_START_COMMAND = "onStartCommand";
}
