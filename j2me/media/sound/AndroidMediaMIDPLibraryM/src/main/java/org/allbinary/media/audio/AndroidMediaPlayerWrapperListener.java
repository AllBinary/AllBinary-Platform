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

//    public AndroidMediaPlayerWrapperListener(
//        final AndroidMediaPlayerWrapper androidMediaPlayerWrapper, final int listeningLevel) {
//        try {
//            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
//
//            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;
//
//            final MediaPlayer mediaPlayer = androidMediaPlayerWrapper.getMediaPlayer();
//
//            if (listeningLevel == 1) {
//                mediaPlayer.setOnCompletionListener(mOnCompletionListener);
//            } else {
//                throw new Exception("Unknow Listening Leve");
//            }
//            //mediaPlayer
//            //      .setOnBufferingUpdateListener(mOnBufferingUpdateListener);
//            //mediaPlayer.setOnPreparedListener(mOnPreparedListener);
//            //mediaPlayer.setOnErrorListener(mOnErrorListener);
//        } catch (Exception e) {
//            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
//        }
//    }

    public AndroidMediaPlayerWrapperListener(final AndroidMediaPlayerWrapper androidMediaPlayerWrapper) {
        try {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

            this.androidMediaPlayerWrapper = androidMediaPlayerWrapper;

            final MediaPlayer mediaPlayer = androidMediaPlayerWrapper.getMediaPlayer();

            mediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
            mediaPlayer.setOnBufferingUpdateListener(this.mOnBufferingUpdateListener);
            mediaPlayer.setOnPreparedListener(this.mOnPreparedListener);
            mediaPlayer.setOnErrorListener(this.mOnErrorListener);
        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
        }
    }

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
    
    private class AndroidMediaPlayerOnBufferingUpdateListener extends MediaPlayerOnBufferingUpdateListener {
        
        private final String ON_BUFFERING_UPDATE = "onBufferingUpdate()";
        
        private final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener;
        
        public AndroidMediaPlayerOnBufferingUpdateListener(final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener) {
            this.androidMediaPlayerWrapperListener = androidMediaPlayerWrapperListener;
        }
        
        @Override
        public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int i) {
            final LogUtil logUtil = LogUtil.getInstance();
            logUtil.putF(new StringMaker().append("Update buffer: ").appendint(i).append("%").toString(), this, this.ON_BUFFERING_UPDATE);
            this.androidMediaPlayerWrapperListener.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_UNAVAILABLE);
        }
    };

    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new AndroidMediaPlayerOnBufferingUpdateListener(this);

    private class AndroidMediaPlayerOnPreparedListener extends MediaPlayerOnPreparedListener {
        
        private final String ON_PREPARE = "onPrepare()";
        
        private final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener;
        
        public AndroidMediaPlayerOnPreparedListener(final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener) {
            this.androidMediaPlayerWrapperListener = androidMediaPlayerWrapperListener;
        }
        
        @Override
        public void onPrepared(final MediaPlayer mp) {
            final LogUtil logUtil = LogUtil.getInstance();
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.putF(commonStrings.START, this, this.ON_PREPARE);
            this.androidMediaPlayerWrapperListener.androidMediaPlayerWrapper.update(PlayerListener.DEVICE_AVAILABLE);
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new AndroidMediaPlayerOnPreparedListener(this);

    private class AndroidMediaPlayerOnErrorListener extends MediaPlayerOnErrorListener{
        
        private final String ON_ERROR = "onError()";

        private final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener;
        
        public AndroidMediaPlayerOnErrorListener(final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener) {
            this.androidMediaPlayerWrapperListener = androidMediaPlayerWrapperListener;
        }
        
        @Override
        public boolean onError(final MediaPlayer mp, final int what, final int extra) {
            final LogUtil logUtil = LogUtil.getInstance();
            logUtil.putF(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append("What: ").appendint(what).append(" Extra: ").appendint(extra).toString(), this, this.ON_ERROR);
            this.androidMediaPlayerWrapperListener.androidMediaPlayerWrapper.update(PlayerListener.ERROR);
            return true;
        }
    };

    private MediaPlayer.OnErrorListener mOnErrorListener = new AndroidMediaPlayerOnErrorListener(this);

    private class AndroidMediaPlayerOnCompletionListener extends MediaPlayerOnCompletionListener {
        
        private final String ON_COMPLETE = "onComplete()";

        private final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener;
        
        public AndroidMediaPlayerOnCompletionListener(final AndroidMediaPlayerWrapperListener androidMediaPlayerWrapperListener) {
            this.androidMediaPlayerWrapperListener = androidMediaPlayerWrapperListener;
        }

        @Override        
        public void onCompletion(final MediaPlayer mp) {
            final LogUtil logUtil = LogUtil.getInstance();
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.putF(commonStrings.START, this, this.ON_COMPLETE);
            this.androidMediaPlayerWrapperListener.androidMediaPlayerWrapper.update(PlayerListener.END_OF_MEDIA);
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new AndroidMediaPlayerOnCompletionListener(this);
}
