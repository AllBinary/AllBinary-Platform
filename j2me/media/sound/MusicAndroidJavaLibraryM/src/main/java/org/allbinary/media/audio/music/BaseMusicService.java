package org.allbinary.media.audio.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import org.allbinary.android.NullAndroidCanvas;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStateStrings;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.ARunnable;

public class BaseMusicService extends Service
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonStateStrings commonStateStrings = CommonStateStrings.getInstance();

    private final String ALREADY_PLAYING = "This is one song per music service";
    private final String WAITING_FOR_MUSIC_TO_END = "Waiting for music to end";

    private MediaPlayer player = NullAndroidCanvas.NULL_MEDIA_PLAYER;

    private int songId = -1;
    private int leftVolume = -1;
    private int rightVolume = -1;

    @Override
    public IBinder onBind(final Intent intent)
    {
        //Toast.makeText(this, "Music Bind", Toast.LENGTH_LONG).show();
        //PrelogUtil.putF(commonStrings.START, this, "onBind");
        this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.BIND);

        //songId = intent.getIntExtra("SONG", AndroidResources.raw.angels_we_have_heard);
        return null;
    }

    @Override
    public void onCreate()
    {
        //Toast.makeText(this, "Music Service Created", Toast.LENGTH_LONG).show();

        this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.CREATE);
        //PrelogUtil.putF(commonStrings.START, this, "onCreate");		
    }

    @Override
    public void onDestroy()
    {
        //Toast.makeText(this, "Music Service Stopped", Toast.LENGTH_LONG).show();

        this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.DESTROY);
        //PrelogUtil.putF(commonStrings.START, this, "onDestroy");

        if (this.player != NullAndroidCanvas.NULL_MEDIA_PLAYER)
        {
            this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.PAUSE);
            this.player.stop();
            this.player.reset();
            this.player.release();
        }
    }

    public void pause()
    {
        if (this.player != NullAndroidCanvas.NULL_MEDIA_PLAYER)
        {
            this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.PAUSE);
            this.player.pause();
        }
    }

    public void resume() {

        if (this.player != NullAndroidCanvas.NULL_MEDIA_PLAYER && !this.player.isPlaying()) {
            this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.RESUME);
            this.player.start();
        }
    }

    public void start() {
        player = MediaPlayer.create(this, songId);
        this.player.setVolume(((float) this.leftVolume) / 100.0f, ((float) this.rightVolume) / 100.0f);
        this.player.setLooping(false);

        this.player.start();
    }

    @Override
    public void onStart(final Intent intent, final int startid)
    {
        //Toast.makeText(this, "Music Service Started", Toast.LENGTH_LONG).show();

        onStartCommand(intent);

        //PrelogUtil.putF(commonStrings.START, this, "onStart");
        this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.START);
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId)
    {
        onStartCommand(intent);
        return START_STICKY;
    }

    public void onStartCommand(final Intent intent)
    {
        //PrelogUtil.putF(commonStrings.START, this, "onStartCommand");
        this.logUtil.putF(this.commonStrings.START, this, commonStateStrings.ON_START_COMMAND);

        final MusicStrings musicStrings = MusicStrings.getInstance();
        if(intent != null) {
            final int command = intent.getIntExtra(commonStateStrings.ON_START_COMMAND, -1);
            this.logUtil.putF(CommonLabels.getInstance().COMMAND_LABEL + command, this, commonStateStrings.ON_START_COMMAND);
            if(command == 1) {
                this.pause();
                return;
            } else if(command == 2) {
                this.resume();
                return;
            } else {
                this.songId = intent.getIntExtra(musicStrings.SONG_EXTRA, -1);
                this.leftVolume = intent.getIntExtra(musicStrings.LEFT_VOLUME, -1);
                this.rightVolume = intent.getIntExtra(musicStrings.RIGHT_VOLUME, -1);
            }
        } else {
            throw new RuntimeException("Started service without intent");
        }

        if (this.songId != -1)
        {
            System.gc();

            if(this.player != NullAndroidCanvas.NULL_MEDIA_PLAYER && this.player.isPlaying()) {
                final MediaPlayer player = this.player;
                this.logUtil.putF(this.ALREADY_PLAYING, this, commonStateStrings.ON_START_COMMAND);
                final Runnable runnable = new ARunnable() {

                    @Override
                    public void run() {
                        final LogUtil logUtil = LogUtil.getInstance();
                        try {
                            while(player.isPlaying()) {
                                logUtil.putF(WAITING_FOR_MUSIC_TO_END, this, commonStateStrings.ON_START_COMMAND);
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

            this.start();
        }
    }
}
