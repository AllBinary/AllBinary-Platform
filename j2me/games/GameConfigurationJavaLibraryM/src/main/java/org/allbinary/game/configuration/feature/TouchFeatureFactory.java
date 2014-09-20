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
package org.allbinary.game.configuration.feature;

public class TouchFeatureFactory
{
    private static final TouchFeatureFactory instance = 
        new TouchFeatureFactory();

    public static TouchFeatureFactory getInstance()
    {
        return instance;
    }
    
    private TouchFeatureFactory()
    {
        
    }

    public final TouchFeature TOUCH_ENABLED = new TouchFeature("Touch Enabled");
    
    public final TouchFeature SHOW_SCREEN_BUTTONS = 
        new TouchFeature("Show");
    public final TouchFeature AUTO_HIDE_SHOW_SCREEN_BUTTONS = 
        new TouchFeature("Auto Hide (One Level)");
    public final TouchFeature HIDE_SCREEN_BUTTONS = 
        new TouchFeature("Hide");
}
