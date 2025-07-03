/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

/**
 *
 * @author User
 */
public class SoundStrings {
    
    private static final SoundStrings instance = new SoundStrings();
    
    public static SoundStrings getInstance() {
        return instance;
    }
    
    public final String STOP_ALL = "stopAll";
    public final String CLOSE_ALL = "closeAll";
    
    public final String SOUND = " Sound: ";
    public final String INIT_SOUND = "Initializing Sound: ";
    
    public final String STOPPING_SOUND = "Stopping Sound: ";
    public final String CLOSING_SOUND = "Closing Sound: ";
    
}
