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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.microedition.media.Control;

import javax.microedition.media.MediaException;
import javax.microedition.media.PlayerListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.thread.MusicThreadPool;

public class PCClipWavPlayer extends BasicPlayer implements LineListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AudioInputStream audioInputStream;
    private final Clip clip;

    public PCClipWavPlayer(InputStream inputStream)
    {
        AudioInputStream audioInputStream = new AudioInputStream(new ByteArrayInputStream(NullUtil.getInstance().NULL_BYTE_ARRAY), NullAudioFormat.NULL_AUDIO_FORMAT, 0);
        Clip clip = new NullClip();
        try
        {
            audioInputStream =
                AudioSystem.getAudioInputStream(inputStream);

            clip = this.create(audioInputStream);

            if(clip == null) {
                logUtil.put("Clip was null", this, commonStrings.CONSTRUCTOR, new Exception());
                throw new RuntimeException();
            }

            //this.setTimeBase(new PCTimeBase());
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        
        this.audioInputStream = audioInputStream;
        this.clip = clip;
    }

    @Override
    public void close()
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
            @Override
            public void run() {
                try {
                    close2();
                } catch (Exception e) {
                    PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
                }
            }
        }
        );
    }

    private void close2() throws Exception {
        this.clip.drain();
        this.clip.flush();
        this.clip.close();
    }
    
    @Override
    public String getContentType()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    public synchronized void start() throws MediaException
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
            @Override
            public void run() {
                try {
                    start2();
                } catch (Exception e) {
                    PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
                }
            }
        }
        );
    }

    private void start2() throws Exception {
        this.clip.setFramePosition(0);
        this.clip.loop(this.getLoopCount());
        this.clip.start();
        super.start();
    }

    private final Clip create(AudioInputStream audioInputStream)
        throws Exception
    {
        final Clip clip = AudioSystem.getClip();

        clip.addLineListener(this);
        clip.open(audioInputStream);

        //PreLogUtil.put(clip.getFormat().toString(), this, commonStrings.PROCESS);
        
        return clip;
    }

    @Override
    public synchronized void stop() throws MediaException
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
            @Override
            public void run() {
                try {
                    stop2();
                } catch (Exception e) {
                    PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
                }
            }
        }
        );
    }

    private void stop2() throws Exception {
        //clip.drain();
        clip.stop();
        //clip.flush();

        ////clip.close();
        super.stop();
    }

    @Override
    public Control getControl(String controlType)
    {
        return new NullControl();
    }

    @Override
    public Control[] getControls()
    {
        return new Control[0];
    }

    @Override
    public void setVolume(final int leftVolume, final int rightVolume) {
        this.setVolume(((float) leftVolume) / 100.0f);
    }

    private float getVolume() {
        final FloatControl masterGainFloatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, masterGainFloatControl.getValue() / 20f);
    }

    private void setVolume(final float volume) {
        if (volume < 0f || volume > 1f) {
            throw new IllegalArgumentException("Volume: " + volume);
        }
        final FloatControl masterGainFloatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        masterGainFloatControl.setValue(20f * (float) Math.log10(volume));
    }

    @Override
    public long getDuration() {
        return this.clip.getMicrosecondLength() / 1000;
    }

    @Override
    public void update(LineEvent event)
    {
        //logUtil.put("LineEvent: " + event.getType(),  this, "update");
        if (event.getType().equals(LineEvent.Type.STOP))
        {
            //this.close();

            final int size = this.listenersList.size();
            for (int index = 0; index < size; index++)
            {
                PlayerListener listener = (PlayerListener) this.listenersList.get(size);
                listener.playerUpdate(this, PlayerListener.END_OF_MEDIA, null);
            }

        }
    }
}
