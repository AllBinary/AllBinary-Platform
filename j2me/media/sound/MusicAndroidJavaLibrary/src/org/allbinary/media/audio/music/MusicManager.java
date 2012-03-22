package org.allbinary.media.audio.music;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.data.resource.ResourceUtil;
import allbinary.media.audio.Sound;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;
import android.content.Intent;

public class MusicManager 
{
	private final String PLAY = "Play ";
	private final String SONG = " for: ";

	private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);
	
	private final BasicArrayList songList;

	private Sound currentSongSound;
	
	//MusicService.class
	private final Intent currentIntent;
	
	public MusicManager(Class musicServiceClass, BasicArrayList songList)
	{
		currentIntent = new Intent(ResourceUtil.getInstance().getContext(), musicServiceClass);
		
		this.songList = songList;
	}

	public void process()
	{
		if(this.timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
		{
			this.startNewSong();
		}
	}

	public void show()
	{
		for(int index = this.songList.size(); --index >= 0;)
		{
			Sound sound = (Sound) this.songList.get(index);

			long duration = sound.getPlayer().getDuration();

			PreLogUtil.put(PLAY + sound.getResource() + SONG + duration, this, CommonStrings.getInstance().PROCESS);
		}
	}

	public void startNewSong()
	{
		try
		{
			//PreLogUtil.put(Memory.getInfo(), this, CommonStrings.getInstance().PROCESS);

			ResourceUtil.getInstance().getContext().stopService(this.currentIntent);
			
			this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);

			long duration = this.currentSongSound.getDuration();
					//18000;
			//this.currentSongSound.getPlayer().getDuration();

			PreLogUtil.put(PLAY + this.currentSongSound.getResource() + SONG + duration, this, CommonStrings.getInstance().PROCESS);

			this.timeDelayHelper.setDelay((int) duration);

			this.currentIntent.putExtra("SONG", ResourceUtil.getInstance().getResourceId(this.currentSongSound.getResource()).intValue());
			
			ResourceUtil.getInstance().getContext().startService(this.currentIntent);
        }
        catch (Exception e)
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
