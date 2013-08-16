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
package org.allbinary.animation.transition.shake;

public class ShakeEventFactory
{
    private static final ShakeEventFactory instance = new ShakeEventFactory();

    public static ShakeEventFactory getInstance()
    {
        return instance;
    }

    public ShakeEvent MICRO = new ShakeEvent();
    public ShakeEvent SMALL = new ShakeEvent();
    public ShakeEvent MEDIUM = new ShakeEvent();
    public ShakeEvent LARGE = new ShakeEvent();
    
    public void init()
    {
        
    }

}
