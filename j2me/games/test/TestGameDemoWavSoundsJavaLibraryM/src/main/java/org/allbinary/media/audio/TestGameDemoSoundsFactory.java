package org.allbinary.media.audio;


import allbinary.media.audio.BaseSoundsFactory;
import allbinary.media.audio.Sound;

public class TestGameDemoSoundsFactory
extends BaseSoundsFactory
{
    public Sound[] getSoundInterfaceArray() throws Exception
    {
        Sound[] soundInterfaceArray = new Sound[1];
        int index = 0;

        soundInterfaceArray[index++] = TestSound.getInstance();

        return soundInterfaceArray;
    }
}
