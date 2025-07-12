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
package org.allbinary.media.audio;

public class EarlySoundsFactory
    extends BaseSoundsFactory 
{
    private static final EarlySoundsFactory instance = 
        new EarlySoundsFactory();

    public Sound[] getSoundInterfaceArray() throws Exception {
        Sound[] soundInterfaceArray = new Sound[2];

        int index = 0;
        soundInterfaceArray[index++] = SelectSound.getInstance();
        soundInterfaceArray[index++] = ErrorSound.getInstance();
        //soundInterfaceArray[1] = FireworksSound.getInstance();

        return soundInterfaceArray;
    }

    public static EarlySoundsFactory getInstance()
    {
        return instance;
    }
}
