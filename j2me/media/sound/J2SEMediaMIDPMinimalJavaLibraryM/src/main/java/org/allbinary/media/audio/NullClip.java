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

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author User
 */
public class NullClip implements Clip {

    public static final NullJ2SEControl NULL_CONTROL = new NullJ2SEControl(BooleanControl.Type.MUTE);

    @Override    
    public void open(AudioFormat format, byte[] data, int offset, int bufferSize)
            throws LineUnavailableException {
        
    }

    @Override
    public void open(AudioInputStream stream)
            throws LineUnavailableException, IOException {
        
    }

    @Override
    public int getFrameLength() {
        return 0;
    }

    @Override
    public long getMicrosecondLength() {
        return 0;
    }

    @Override
    public void setFramePosition(int frames) {
        
    }

    @Override
    public void setMicrosecondPosition(long microseconds) {
        
    }

    @Override
    public void setLoopPoints(int start, int end) {
        
    }

    @Override
    public void loop(int count) {
        
    }

    @Override
    public void drain() {
        
    }

    @Override
    public void flush() {
        
    }

    @Override
    public void start() {
        
    }

    @Override
    public void stop() {
        
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public AudioFormat getFormat() {
        return NullAudioFormat.NULL_AUDIO_FORMAT;
    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public int available() {
        return 0;
    }

    @Override
    public int getFramePosition() {
        return 0;
    }

    @Override
    public long getLongFramePosition() {
        return 0L;
    }

    @Override
    public long getMicrosecondPosition() {
        return 0L;
    }

    @Override
    public float getLevel() {
        return 0.0f;
    }

    @Override
    public Info getLineInfo() {
        return new Info(this.getClass(), NullAudioFormat.NULL_AUDIO_FORMAT);
    }

    @Override
    public void open() throws LineUnavailableException {
        
    }

    @Override
    public void close() {
        
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Control[] getControls() {
        return new Control[0];
    }

    @Override
    public boolean isControlSupported(Type control) {
        return false;
    }

    @Override
    public Control getControl(Type control) {
        return NullClip.NULL_CONTROL;
    }

    @Override
    public void addLineListener(LineListener listener) {
    }

    @Override
    public void removeLineListener(LineListener listener) {
    }
    
}
