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
package org.allbinary.input.motion.button;

public class TouchScreenTypeFactory
{
    private static final TouchScreenTypeFactory instance = new TouchScreenTypeFactory();

    public static TouchScreenTypeFactory getInstance()
    {
        return instance;
    }
    
    public final TouchScreenType FINGER = new TouchScreenType("Finger");
    
    public final TouchScreenType NOTOUCH = new TouchScreenType("No Touch");
    
    public final TouchScreenType STYLUS = new TouchScreenType("Stylus");

    public final TouchScreenType UNDEFINED = new TouchScreenType("Undefined");
    
    public void TouchScreenTypeFactory()
    {
        final TouchScreenFactory touchScreenFactory = 
                TouchScreenFactory.getInstance();
        touchScreenFactory.setTouch(true);
    }
}
