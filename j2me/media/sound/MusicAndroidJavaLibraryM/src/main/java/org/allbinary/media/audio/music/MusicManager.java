package org.allbinary.media.audio.music;

import android.content.Intent;
import org.allbinary.android.AndroidServicesUtil;
import org.allbinary.android.AndroidStrings;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class MusicManager
{
    private final String PLAY = "Play ";
    private final String FOR = " for: ";

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final MusicStrings musicStrings = MusicStrings.getInstance();
    private final AndroidStrings androidStrings = AndroidStrings.getInstance();
    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    private final ResourceUtil resourceUtil = ResourceUtil.getInstance();
    
    private final AndroidServicesUtil androidServicesUtil = AndroidServicesUtil.getInstance();
    
    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);
    private final TimeDelayHelper timeDelayHelper2 = new TimeDelayHelper(1200);

    //private final String STOP_MUSIC_SERVICE = "stop - Stop MusicService";
    //private final String NEXT_SONG = "nextSong - this does not change the MusicService";
    
    private final BasicArrayList songList;

    private Sound currentSongSound;
    private Sound nextSongSound;
    private int leftVolume = 100;
    private int rightVolume = 100;

    //MusicService.class
    private final Class musicServiceClass;
    private final Intent currentIntent;

    public MusicManager(final Class musicServiceClass, final BasicArrayList songList)
    {
        PreLogUtil.put(androidStrings.CONTEXT + resourceUtil.getContext(), this, commonStrings.CONSTRUCTOR);
        
        this.musicServiceClass = musicServiceClass;
        currentIntent = new Intent(resourceUtil.getContext(), musicServiceClass);

        this.songList = songList;
    }

    public void nextSong(final Sound nextSongSound, final int leftVolume, final int rightVolume) {
        
        //PreLogUtil.put(NEXT_SONG, this, commonStrings.PROCESS);
        
        this.nextSongSound = nextSongSound;
        this.leftVolume = leftVolume;
        this.rightVolume = rightVolume;
        this.reset();
    }
    
    public void reset() {
        this.timeDelayHelper.delay = 0;
    }
    
    public void process()
    {
        if (this.songList.size() == 0) {
            return;
        }
        
        if (this.timeDelayHelper.isTime(gameTickTimeDelayHelper.startTime))
        {
            this.startNewSong();
            return;
        }
        
        if(timeDelayHelper2.isTime(this.gameTickTimeDelayHelper.startTime)) {
            if (androidServicesUtil.isServiceRunning(this.musicServiceClass.getName())) {

            } else {
                this.startNewSong();
            }
        }
    }

    public void show()
    {
        for (int index = this.songList.size(); --index >= 0;)
        {
            final Sound sound = (Sound) this.songList.get(index);

            final long duration = sound.getDuration();

            PreLogUtil.put(new StringBuilder().append(PLAY).append(sound.getResource()).append(FOR).append(duration).toString(), this, commonStrings.PROCESS);
        }
    }

    public void startNewSong()
    {
        try
        {
            //PreLogUtil.put("startNewSong - Stop MusicService", this, commonStrings.PROCESS);
            //PreLogUtil.put(Memory.getInfo(), this, commonStrings.PROCESS);

            this.resourceUtil.getContext().stopService(this.currentIntent);

            if(this.nextSongSound == null) {
                this.currentSongSound = (Sound) basicArrayListUtil.getRandom(this.songList);
            } else {
                this.currentSongSound = this.nextSongSound;
                this.nextSongSound = null;
            }

            final long duration = this.currentSongSound.getDuration();
					//18000;

            PreLogUtil.put(new StringBuilder().append(PLAY).append(this.currentSongSound.getResource()).append(FOR).append(duration).toString(), this, commonStrings.PROCESS);

            this.timeDelayHelper.delay = (int) duration;

            this.currentIntent.putExtra(musicStrings.SONG_EXTRA, this.resourceUtil.getResourceId(this.currentSongSound.getResource()).intValue());
            this.currentIntent.putExtra(musicStrings.LEFT_VOLUME, leftVolume);
            this.currentIntent.putExtra(musicStrings.RIGHT_VOLUME, rightVolume);

            //PreLogUtil.put("startNewSong - Start MusicService", this, commonStrings.PROCESS);
            this.resourceUtil.getContext().startService(this.currentIntent);
        } catch (Exception e)
        {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null)
            {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
        }
    }

    public void stop()
            throws Exception
    {
        //PreLogUtil.put(STOP_MUSIC_SERVICE, this, commonStrings.PROCESS);
        this.resourceUtil.getContext().stopService(this.currentIntent);
    }
}
