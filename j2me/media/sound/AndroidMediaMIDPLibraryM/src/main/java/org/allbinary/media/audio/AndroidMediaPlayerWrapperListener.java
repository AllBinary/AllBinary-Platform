package org.allbinary.media.audio;

import javax.microedition.media.PlayerListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import android.media.MediaPlayer;
import org.allbinary.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;

public class AndroidMediaPlayerWrapperListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private AndroidMediaPlayerWrapper androidMediaPlayerWrapper;
    
    public AndroidMediaPlayerWrapperListener(
            AndroidMediaPlayerWrapper androidMediaPlayerWrapper, int listeningLevel)
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
            
            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;
            
            MediaPlayer mediaPlayer = androidMediaPlayerWrapper
                    .getMediaPlayer();
            
            if(listeningLevel == 1)
            {
                mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
            else
            {
                throw new Exception("Unknow Listening Leve");
            }
            //mediaPlayer
              //      .setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            //mediaPlayer.setOnPreparedListener(mOnPreparedListener);
            //mediaPlayer.setOnErrorListener(mOnErrorListener);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    public AndroidMediaPlayerWrapperListener(
            AndroidMediaPlayerWrapper androidMediaPlayerWrapper)
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
            
            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;
            
            MediaPlayer mediaPlayer = androidMediaPlayerWrapper
                    .getMediaPlayer();
            
            mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            mediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mediaPlayer.setOnErrorListener(mOnErrorListener);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }
    
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener()
    {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i)
        {
            logUtil.put("Update buffer: " + i + "%", this,
                    "onBufferingUpdate(");
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_UNAVAILABLE);
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener()
    {
        public void onPrepared(MediaPlayer mp)
        {
            logUtil.put(commonStrings.START, this, "onPrepare()");
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_AVAILABLE);
        }
    };

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener()
    {
        public boolean onError(MediaPlayer mp, int what, int extra)
        {
            logUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("What: ").append(what).append(" Extra: ").append(extra).toString(), this, "onError()");
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.ERROR);
            return true;
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        public void onCompletion(MediaPlayer mp)
        {
            logUtil.put(commonStrings.START, this, "onComplete())");
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.END_OF_MEDIA);
        }
    };
}
