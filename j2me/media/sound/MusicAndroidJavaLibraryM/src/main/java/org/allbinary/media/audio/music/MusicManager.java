package org.allbinary.media.audio.music;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import android.content.Intent;

public class MusicManager
{
    private final String PLAY = "Play ";
    private final String FOR = " for: ";

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);

    private final BasicArrayList songList;

    private Sound currentSongSound;
    private Sound nextSongSound;
    private int leftVolume;
    private int rightVolume;

    //MusicService.class
    private final Intent currentIntent;

    public MusicManager(Class musicServiceClass, BasicArrayList songList)
    {
        currentIntent = new Intent(ResourceUtil.getInstance().getContext(), musicServiceClass);

        this.songList = songList;
    }

    public void nextSong(final Sound nextSongSound, final int leftVolume, final int rightVolume) {
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
        
        if (this.timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
        {
            this.startNewSong();
        }
    }

    public void show()
    {
        for (int index = this.songList.size(); --index >= 0;)
        {
            final Sound sound = (Sound) this.songList.get(index);

            final long duration = sound.getDuration();

            PreLogUtil.put(new StringBuilder().append(PLAY).append(sound.getResource()).append(FOR).append(duration).toString(), this, CommonStrings.getInstance().PROCESS);
        }
    }

    public void startNewSong()
    {
        try
        {
            //PreLogUtil.put(Memory.getInfo(), this, CommonStrings.getInstance().PROCESS);

            ResourceUtil.getInstance().getContext().stopService(this.currentIntent);

            if(this.nextSongSound == null) {
                this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);
            } else {
                this.currentSongSound = this.nextSongSound;
                this.nextSongSound = null;
            }

            long duration = this.currentSongSound.getDuration();
					//18000;

            PreLogUtil.put(new StringBuilder().append(PLAY).append(this.currentSongSound.getResource()).append(FOR).append(duration).toString(), this, CommonStrings.getInstance().PROCESS);

            this.timeDelayHelper.delay = (int) duration;

            final MusicStrings musicStrings = MusicStrings.getInstance();
            this.currentIntent.putExtra(musicStrings.SONG_EXTRA, ResourceUtil.getInstance().getResourceId(this.currentSongSound.getResource()).intValue());
            this.currentIntent.putExtra(musicStrings.LEFT_VOLUME, leftVolume);
            this.currentIntent.putExtra(musicStrings.RIGHT_VOLUME, rightVolume);

            ResourceUtil.getInstance().getContext().startService(this.currentIntent);
        } catch (Exception e)
        {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null)
            {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION_LABEL + resource, this, CommonStrings.getInstance().PROCESS, e);
        }
    }

    public void stop()
            throws Exception
    {
        ResourceUtil.getInstance().getContext().stopService(this.currentIntent);
    }
}
