/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.media.audio.music.MusicManager;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class MusicManagerFactory {
    
    public static MusicManager create(final BasicArrayList soundList) {
        return new MusicManager(MusicService.class, soundList);
        //return new MusicManager(soundList);
    }
}
