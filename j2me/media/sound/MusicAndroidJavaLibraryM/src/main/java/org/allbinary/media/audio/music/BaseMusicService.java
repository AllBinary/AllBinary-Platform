package org.allbinary.media.audio.music;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import org.allbinary.android.AndroidStrings;

public class BaseMusicService extends Service
{

    private MediaPlayer player;

    private int songId = -1;
    private int leftVolume = -1;
    private int rightVolume = -1;

    private AndroidStrings androidStrings = AndroidStrings.getInstance();

    //@Override
    public IBinder onBind(Intent intent)
    {
        //Toast.makeText(this, "Music Bind", Toast.LENGTH_LONG).show();
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onBind");
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, androidStrings.BIND));

        //songId = intent.getIntExtra("SONG", AndroidResources.raw.angels_we_have_heard);
        return null;
    }

    //@Override
    public void onCreate()
    {
		//Toast.makeText(this, "Music Service Created", Toast.LENGTH_LONG).show();

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, androidStrings.CREATE));
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onCreate");		
    }

    //@Override
    public void onDestroy()
    {
		//Toast.makeText(this, "Music Service Stopped", Toast.LENGTH_LONG).show();

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, androidStrings.DESTROY));
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onDestroy");

        if (player != null)
        {
            player.stop();
            player.reset();
            player.release();
        }
    }

    //@Override
    public void onStart(Intent intent, int startid)
    {
        //Toast.makeText(this, "Music Service Started", Toast.LENGTH_LONG).show();

        onStartCommand(intent);

        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onStart");
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, androidStrings.START));
    }

    //@Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        onStartCommand(intent);
        return START_STICKY;
    }

    public void onStartCommand(Intent intent)
    {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onStartCommand");
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onStartCommand"));

        final MusicStrings musicStrings = MusicStrings.getInstance();
        songId = intent.getIntExtra(musicStrings.SONG_EXTRA, -1);
        leftVolume = intent.getIntExtra(musicStrings.LEFT_VOLUME, -1);
        rightVolume = intent.getIntExtra(musicStrings.RIGHT_VOLUME, -1);

        if (songId != -1)
        {
            System.gc();

            if(player != null && player.isPlaying()) { 
                LogUtil.put(LogFactory.getInstance("This is one song per service", this, "onStartCommand"));
                return; 
            }
            
            player = MediaPlayer.create(this, songId);
            player.setVolume(((float) leftVolume) / 100.0f, ((float) rightVolume) / 100.0f);
            player.setLooping(false);

            player.start();
        }
    }
}
