package allbinary.media.audio;

import javax.microedition.media.PlayerListener;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import android.media.MediaPlayer;

public class AndroidMediaPlayerWrapperListener
{
    private AndroidMediaPlayerWrapper androidMediaPlayerWrapper;
    
    public AndroidMediaPlayerWrapperListener(
            AndroidMediaPlayerWrapper androidMediaPlayerWrapper, int listeningLevel)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
            
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }

    public AndroidMediaPlayerWrapperListener(
            AndroidMediaPlayerWrapper androidMediaPlayerWrapper)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
            
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }
    
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener()
    {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i)
        {
            LogUtil.put(LogFactory.getInstance("Update buffer: " + i + "%", this,
                    "onBufferingUpdate("));
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_UNAVAILABLE);
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener()
    {
        public void onPrepared(MediaPlayer mp)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onPrepare()"));
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_AVAILABLE);
        }
    };

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener()
    {
        public boolean onError(MediaPlayer mp, int what, int extra)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + "What: " + what + " Extra: " + extra,
                    this, "onError()"));
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.ERROR);
            return true;
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        public void onCompletion(MediaPlayer mp)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onComplete())"));
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.END_OF_MEDIA);
        }
    };
}
