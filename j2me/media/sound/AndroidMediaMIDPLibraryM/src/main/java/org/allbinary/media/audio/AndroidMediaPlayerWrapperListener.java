package org.allbinary.media.audio;

import javax.microedition.media.PlayerListener;

import android.media.MediaPlayer;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;

public class AndroidMediaPlayerWrapperListener {

    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private AndroidMediaPlayerWrapper androidMediaPlayerWrapper = AndroidMediaPlayerWrapper.NULL_ANDROID_MEDIA_PLAYER_WRAPPER;

    public AndroidMediaPlayerWrapperListener(
        final AndroidMediaPlayerWrapper androidMediaPlayerWrapper, final int listeningLevel) {
        try {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;

            final MediaPlayer mediaPlayer = androidMediaPlayerWrapper.getMediaPlayer();

            if (listeningLevel == 1) {
                mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            } else {
                throw new Exception("Unknow Listening Leve");
            }
            //mediaPlayer
            //      .setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            //mediaPlayer.setOnPreparedListener(mOnPreparedListener);
            //mediaPlayer.setOnErrorListener(mOnErrorListener);
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    public AndroidMediaPlayerWrapperListener(final AndroidMediaPlayerWrapper androidMediaPlayerWrapper) {
        try {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;

            final MediaPlayer mediaPlayer = androidMediaPlayerWrapper.getMediaPlayer();

            mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            mediaPlayer.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
            mediaPlayer.setOnPreparedListener(mOnPreparedListener);
            mediaPlayer.setOnErrorListener(mOnErrorListener);
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    private static final String ON_BUFFERING_UPDATE = "onBufferingUpdate()";
    private static final String ON_PREPARE = "onPrepare()";
    private static final String ON_ERROR = "onError()";
    private static final String ON_COMPLETE = "onComplete()";

    private class MediaPlayerOnBufferingUpdateListener implements MediaPlayer.OnBufferingUpdateListener {

        @Override
        public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int i) {

        }
    }

    private class MediaPlayerOnPreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(final MediaPlayer mp) {

        }

    }

    private class MediaPlayerOnErrorListener implements MediaPlayer.OnErrorListener {
        
        @Override
        public boolean onError(final MediaPlayer mp, final int what, final int extra) {
            return false;
        }

    }
    
    private class MediaPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        
        @Override
        public void onCompletion(final MediaPlayer mp) {
            
        }

    }
        
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new MediaPlayerOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int i) {
            logUtil.put(new StringMaker().append("Update buffer: ").append(i).append("%").toString(), this, AndroidMediaPlayerWrapperListener.ON_BUFFERING_UPDATE);
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_UNAVAILABLE);
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayerOnPreparedListener() {
        @Override
        public void onPrepared(final MediaPlayer mp) {
            logUtil.put(commonStrings.START, this, AndroidMediaPlayerWrapperListener.ON_PREPARE);
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_AVAILABLE);
        }
    };

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayerOnErrorListener() {
        public boolean onError(final MediaPlayer mp, final int what, final int extra) {
            logUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("What: ").append(what).append(" Extra: ").append(extra).toString(), this, AndroidMediaPlayerWrapperListener.ON_ERROR);
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.ERROR);
            return true;
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayerOnCompletionListener() {
        public void onCompletion(final MediaPlayer mp) {
            logUtil.put(commonStrings.START, this, AndroidMediaPlayerWrapperListener.ON_COMPLETE);
            AndroidMediaPlayerWrapperListener.this.androidMediaPlayerWrapper.update(PlayerListener.END_OF_MEDIA);
        }
    };
}
