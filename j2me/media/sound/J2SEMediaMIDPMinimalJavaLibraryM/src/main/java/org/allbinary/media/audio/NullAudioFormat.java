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

import javax.sound.sampled.AudioFormat;

/**
 *
 * @author User
 */
public class NullAudioFormat {
    
    public static final AudioFormat NULL_AUDIO_FORMAT = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, 0.0f, 0, 0, 0, 0.0f, false);
}
