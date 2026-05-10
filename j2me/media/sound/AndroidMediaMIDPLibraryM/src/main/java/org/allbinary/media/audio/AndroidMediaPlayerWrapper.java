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
import org.allbinary.android.NullAndroidCanvas;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class AndroidMediaPlayerWrapper extends BasicPlayer 
   //implements LineListener
{

    public static AndroidMediaPlayerWrapper createPlayerWrapper() {
        try {
            return new AndroidMediaPlayerWrapper(StringUtil.getInstance().EMPTY_STRING);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    public static final AndroidMediaPlayerWrapper NULL_ANDROID_MEDIA_PLAYER_WRAPPER = AndroidMediaPlayerWrapper.createPlayerWrapper();
    
    protected final LogUtil logUtil = LogUtil.getInstance();
    private MediaPlayer mediaPlayer = NullAndroidCanvas.NULL_MEDIA_PLAYER;

    //private AndroidMediaPlayerWrapperListener mediaPlayerHelper;

    public AndroidMediaPlayerWrapper(String resource)
    throws Exception
    {
        try
        {
            //TWB - replace with MediaPlayer create method to remove if
            if(resource == StringUtil.getInstance().EMPTY_STRING) {

            } else {
                final ResourceUtil resourceUtil = ResourceUtil.getInstance();

                final MediaPlayer mediaPlayer = MediaPlayer.create(resourceUtil.getContext(),resourceUtil.getResourceId(resource).intValue());

                if(mediaPlayer == null)
                {
                    throw new Exception(
                            new StringMaker().append("Failed to create media player for: ")
                                    .append(resource).append(" with id: ")
                                    .append(resourceUtil.getResourceId(resource).toString()).toString());
                }

                this.setMediaPlayer(mediaPlayer);

                this.mediaPlayer.setLooping(false);

                // TWB- M5 does the prepare in the create method now
                // this.mediaPlayer.prepare();

                //this.setTimeBase((TimeBase) new PCTimeBase());

                //mediaPlayerHelper = new AndroidMediaPlayerWrapperListener(this);
            }
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION_LABEL + resource, this, this.commonStrings.CONSTRUCTOR, e);
            throw e;
        }
    }

    @Override
    public void setLoopCount(int count)
    {
        super.setLoopCount(count);
        
        if(this.mediaPlayer != NullAndroidCanvas.NULL_MEDIA_PLAYER && this.mediaPlayer != null)
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
    
    @Override
    public synchronized void addPlayerListener(PlayerListener playerListener)
    {
        //mediaPlayerHelper = new AndroidMediaPlayerWrapperListener(this, 1);
        super.addPlayerListener(playerListener);
    }

    @Override
    public void removePlayerListener(PlayerListener playerListener)
    {
        super.removePlayerListener(playerListener);
        //mediaPlayerHelper = AndroidMediaPlayerWrapper.NULL_MEDIA_PLAYER;
    }

    @Override
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
    
    @Override
    public void close()
    {
        try
        {
            this.mediaPlayer.release();
            this.mediaPlayer = NullAndroidCanvas.NULL_MEDIA_PLAYER;
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CLOSE, e);
        }
    }

    @Override
    public synchronized void start() throws MediaException
    {
        try
        {
            // this.logUtil.putF(this.commonStrings.START, this, CommonStrings.getInstance());

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
                            this.logUtil.putF(this.commonStrings.START, this, "onComplete())");
                            AndroidMediaPlayerWrapper.this.update(PlayerListener.END_OF_MEDIA);
                        }
                    });
            */
            
            super.start();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.START_METHOD_NAME, e);
        }
    }

    @Override
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
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "stop", e);
        }
    }

    public void update(String event)
    {
        this.logUtil.putF("LineEvent: " + event, this, this.commonStrings.UPDATE);

        int size = this.listenersList.size();
        for (int index = 0; index < size; index++)
        {
            PlayerListener listener = (PlayerListener) this.listenersList.objectArray[index];
            listener.playerUpdate(this, event, NullUtil.getInstance().NULL_OBJECT);
        }
    }

    @Override
    public void setVolume(final int leftVolume, final int rightVolume) {
        this.mediaPlayer.setVolume(((float) leftVolume) / 100.0f, ((float) rightVolume) / 100.0f);
    }
    
    @Override
    public long getDuration()
    {
        return (long) this.mediaPlayer.getDuration();
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer)
    {
        
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer()
    {
        return this.mediaPlayer;
    }
}
