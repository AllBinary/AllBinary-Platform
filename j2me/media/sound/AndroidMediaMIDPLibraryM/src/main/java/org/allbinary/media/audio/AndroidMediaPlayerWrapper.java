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

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

import android.media.MediaPlayer;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.communication.log.LogUtil;

public class AndroidMediaPlayerWrapper extends BasicPlayer 
   //implements LineListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private MediaPlayer mediaPlayer;

    //private AndroidMediaPlayerWrapperListener mediaPlayerHelper;

    public AndroidMediaPlayerWrapper(String resource)
    throws Exception
    {
        try
        {
            ResourceUtil resourceUtil = ResourceUtil.getInstance();
            
            this.setMediaPlayer(
                    MediaPlayer.create(resourceUtil.getContext(),
                    resourceUtil.getResourceId(resource).intValue())
                    );

            if(this.getMediaPlayer() == null)
            {
                throw new Exception(
                        "Failed to create media player for: " + resource + 
                        " with id: " +
                        resourceUtil.getResourceId(resource));
            }
            
            this.mediaPlayer.setLooping(false);
            
            // TWB- M5 does the prepare in the create method now
            // this.mediaPlayer.prepare();

            //this.setTimeBase((TimeBase) new PCTimeBase());
            
            //mediaPlayerHelper = new AndroidMediaPlayerWrapperListener(this);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.CONSTRUCTOR, e);
            throw e;
        }
    }

    public void setLoopCount(int count)
    {
        super.setLoopCount(count);
        
        if(this.mediaPlayer != null)
        {
            if(count == 0)
            {
                this.mediaPlayer.setLooping(false);
            }
            else
            {
                //??
                //this.mediaPlayer.setLooping(true);
            }
        }
    }
    
    public synchronized void addPlayerListener(PlayerListener playerListener)
    {
        //mediaPlayerHelper = new AndroidMediaPlayerWrapperListener(this, 1);
        super.addPlayerListener(playerListener);
    }

    public void removePlayerListener(PlayerListener playerListener)
    {
        super.removePlayerListener(playerListener);
        //mediaPlayerHelper = null;
    }

    public int getState()
    {
       if(this.mediaPlayer.isPlaying())
       {
           return Player.STARTED;
       }
       else
       {
           return Player.PREFETCHED;
       }
    }
    
    public void close()
    {
        try
        {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CLOSE, e);
        }
    }

    public synchronized void start() throws MediaException
    {
        try
        {
            // logUtil.put(commonStrings.START, this, CommonStrings.getInstance());

            if(this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.pause();
                this.mediaPlayer.seekTo(0);
            }

            this.mediaPlayer.start();

            /*
            this.mediaPlayer.setOnCompletionListener(
                    new MediaPlayer.OnCompletionListener(){
                        public void onCompletion(MediaPlayer mp)
                        {
                            logUtil.put(commonStrings.START, this, "onComplete())");
                            AndroidMediaPlayerWrapper.this.update(PlayerListener.END_OF_MEDIA);
                        }
                    });
            */
            
            super.start();
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.START_METHOD_NAME, e);
        }
    }

    public synchronized void stop() throws MediaException
    {
        try
        {
            this.mediaPlayer.stop();
            this.mediaPlayer.prepare();
            super.stop();
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "stop", e);
        }
    }

    public void update(String event)
    {
        logUtil.put("LineEvent: " + event, this, commonStrings.UPDATE);

        int size = this.listenersList.size();
        for (int index = 0; index < size; index++)
        {
            PlayerListener listener = (PlayerListener) this.listenersList.objectArray[index];
            listener.playerUpdate(this, event, null);
        }
    }

    public void setVolume(final int leftVolume, final int rightVolume) {
        this.mediaPlayer.setVolume(((float) leftVolume) / 100.0f, ((float) rightVolume) / 100.0f);
    }
    
    public long getDuration()
    {
        return mediaPlayer.getDuration();
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer)
    {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer()
    {
        return mediaPlayer;
    }
}
