package org.allbinary.media.audio.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStateStrings;
import org.allbinary.string.CommonStrings;

public class BaseMusicService extends Service
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonStateStrings commonStateStrings = CommonStateStrings.getInstance();
    
    private final String ALREADY_PLAYING = "This is one song per music service";
    private final String WAITING_FOR_MUSIC_TO_END = "Waiting for music to end";

    private MediaPlayer player;

    private int songId = -1;
    private int leftVolume = -1;
    private int rightVolume = -1;

    //@Override
    public IBinder onBind(final Intent intent)
    {
        //Toast.makeText(this, "Music Bind", Toast.LENGTH_LONG).show();
        //PreLogUtil.put(commonStrings.START, this, "onBind");
        logUtil.put(commonStrings.START, this, commonStateStrings.BIND);

        //songId = intent.getIntExtra("SONG", AndroidResources.raw.angels_we_have_heard);
        return null;
    }

    //@Override
    public void onCreate()
    {
		//Toast.makeText(this, "Music Service Created", Toast.LENGTH_LONG).show();

        logUtil.put(commonStrings.START, this, commonStateStrings.CREATE);
        //PreLogUtil.put(commonStrings.START, this, "onCreate");		
    }

    //@Override
    public void onDestroy()
    {
		//Toast.makeText(this, "Music Service Stopped", Toast.LENGTH_LONG).show();

        logUtil.put(commonStrings.START, this, commonStateStrings.DESTROY);
        //PreLogUtil.put(commonStrings.START, this, "onDestroy");

        if (player != null)
        {
            player.stop();
            player.reset();
            player.release();
        }
    }

    //@Override
    public void onStart(final Intent intent, final int startid)
    {
        //Toast.makeText(this, "Music Service Started", Toast.LENGTH_LONG).show();

        onStartCommand(intent);

        //PreLogUtil.put(commonStrings.START, this, "onStart");
        logUtil.put(commonStrings.START, this, commonStateStrings.START);
    }

    //@Override
    public int onStartCommand(final Intent intent, final int flags, final int startId)
    {
        onStartCommand(intent);
        return START_STICKY;
    }

    public void onStartCommand(final Intent intent)
    {
        //PreLogUtil.put(commonStrings.START, this, "onStartCommand");
        logUtil.put(commonStrings.START, this, commonStateStrings.ON_START_COMMAND);

        final MusicStrings musicStrings = MusicStrings.getInstance();
        if(intent != null) {
            songId = intent.getIntExtra(musicStrings.SONG_EXTRA, -1);
            leftVolume = intent.getIntExtra(musicStrings.LEFT_VOLUME, -1);
            rightVolume = intent.getIntExtra(musicStrings.RIGHT_VOLUME, -1);
        } else {
            throw new RuntimeException("Started service without intent");
        }

        if (songId != -1)
        {
            System.gc();

            if(player != null && player.isPlaying()) { 
                final MediaPlayer player = this.player;
                logUtil.put(ALREADY_PLAYING, this, commonStateStrings.ON_START_COMMAND);
                final Runnable runnable = new Runnable() {
                    public void run() {
                        try {
                            while(player.isPlaying()) {
                                logUtil.put(WAITING_FOR_MUSIC_TO_END, this, commonStateStrings.ON_START_COMMAND);
                                Thread.sleep(1200);
                            }
                            onStartCommand(intent);
                        } catch(Exception e) {
                            logUtil.put(commonStrings.EXCEPTION, this, commonStateStrings.ON_START_COMMAND, e);
                        }
                    }
                };
                final Thread thread = new Thread(runnable);
                thread.start();
                return; 
            }
            
            player = MediaPlayer.create(this, songId);
            player.setVolume(((float) leftVolume) / 100.0f, ((float) rightVolume) / 100.0f);
            player.setLooping(false);

            player.start();
        }
    }
}
