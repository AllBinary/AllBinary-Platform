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

//Use 8hz16bitmono for most J2ME

public class GameYouWinSound extends CompositeSound
{
    private static Sound soundInterface = new GameYouWinSound();

    private GameYouWinSound()
    {
        super("resource:/wav/youwin.wav");
    }

    public static Sound getInstance()
    {
        return soundInterface;
    }
}
