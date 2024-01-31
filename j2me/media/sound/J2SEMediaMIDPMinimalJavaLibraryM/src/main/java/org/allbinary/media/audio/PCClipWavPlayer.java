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
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.thread.MusicThreadPool;

public class PCClipWavPlayer extends BasicPlayer implements LineListener
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private AudioInputStream audioInputStream;
    private Clip clip;

    public PCClipWavPlayer(InputStream inputStream)
    {
        try
        {
            this.audioInputStream =
                AudioSystem.getAudioInputStream(inputStream);

            this.clip = this.create();

            if(this.clip == null) {
                throw new RuntimeException();
            }

            //this.setTimeBase(new PCTimeBase());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }

    public void close()
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
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
    
    public String getContentType()
    {
        return null;
    }

    public synchronized void start() throws MediaException
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
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

    private final Clip create()
        throws Exception
    {
        final Clip clip = AudioSystem.getClip();

        clip.addLineListener(this);
        clip.open(this.audioInputStream);

        //PreLogUtil.put(clip.getFormat().toString(), this, commonStrings.PROCESS);
        
        return clip;
    }

    public synchronized void stop() throws MediaException
    {
        MusicThreadPool.getInstance().runTask(new Runnable() {
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
