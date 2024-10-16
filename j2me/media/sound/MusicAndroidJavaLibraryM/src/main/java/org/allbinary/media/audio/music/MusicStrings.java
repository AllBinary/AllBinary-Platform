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
package org.allbinary.media.audio.music;

/**
 *
 * @author Travis Berthelot
 */
public class MusicStrings {

    private static final MusicStrings instance = new MusicStrings();

    /**
     * @return the instance
     */
    public static MusicStrings getInstance()
    {
        return instance;
    }
    
    
    public final String SONG_EXTRA = "SONG";
    public final String LEFT_VOLUME = "LEFT_VOLUME";
    public final String RIGHT_VOLUME = "RIGHT_VOLUME";
}
