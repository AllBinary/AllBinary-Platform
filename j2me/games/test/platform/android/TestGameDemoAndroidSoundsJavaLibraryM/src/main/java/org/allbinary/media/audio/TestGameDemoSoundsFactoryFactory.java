package org.allbinary.media.audio;

import allbinary.media.audio.SoundsFactoryInterface;

public class TestGameDemoSoundsFactoryFactory
{
    private static SoundsFactoryInterface STATIC = new TestGameDemoSoundsFactory();
    
    public static SoundsFactoryInterface getInstance()
    {
        return STATIC;
    }
}
