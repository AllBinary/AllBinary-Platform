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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
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

public class PCClipWavPlayer extends BasicPlayer implements LineListener
{
    private AudioInputStream audioInputStream;
    private Clip clip;

    public PCClipWavPlayer(InputStream inputStream)
    {
        try
        {
            this.audioInputStream =
                AudioSystem.getAudioInputStream(inputStream);

            this.clip = this.create();

            //this.setTimeBase(new PCTimeBase());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                "Exception", this, "Constructor", e));
        }
    }

    public void close()
    {
        try
        {
            this.clip.drain();
            this.clip.flush();
            this.clip.close();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                "Exception", this, "close", e));
        }
    }

    public String getContentType()
    {
        return null;
    }

    public synchronized void start() throws MediaException
    {
        try
        {
            this.clip.setFramePosition(0);
            this.clip.loop(this.getLoopCount());
            this.clip.start();
            super.start();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                "Exception", this, "start", e));
        }
    }

    private final Clip create()
        throws Exception
    {
        Clip clip = AudioSystem.getClip();

        clip.addLineListener(this);
        clip.open(this.audioInputStream);

        return clip;
    }

    public synchronized void stop() throws MediaException
    {
        try
        {
            clip.drain();
            clip.stop();

            ////clip.close();

            super.stop();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                "Exception", this, "stop", e));
        }
    }

    public Control getControl(String controlType)
    {
        return null;
    }

    public Control[] getControls()
    {
        return null;
    }

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

    public long getDuration() {
        return this.clip.getMicrosecondLength() / 1000;
    }

    public void update(LineEvent event)
    {
        //LogUtil.put(LogFactory.getInstance("LineEvent: " + event.getType(),  this, "update"));
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
