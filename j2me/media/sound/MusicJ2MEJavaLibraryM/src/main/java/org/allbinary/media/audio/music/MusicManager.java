package org.allbinary.media.audio.music;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class MusicManager 
{
	private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);
	
	private final BasicArrayList songList;

	private Sound currentSongSound;
	
	public MusicManager(BasicArrayList songList)
	{
		this.songList = songList;
	}
	
	private final String PLAY = "Play ";
	private final String SONG = " for: ";

	public void process()
	{
		try
		{
			if(this.timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
			{
				final Sound currentSongSound = this.currentSongSound;
				
				if(currentSongSound != null)
				{
					Thread soundThread = new Thread(
							new Runnable()
							{
								public void run() 
								{
									try
									{
										currentSongSound.getPlayer().stop();
										//currentSongSound.getPlayer().close();
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
							}
							);

					soundThread.start();
				}

				this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);

				//this.currentSongSound.init();

				long duration = this.currentSongSound.getPlayer().getDuration();

				PreLogUtil.put(PLAY + this.currentSongSound.getResource() + SONG + duration, this, CommonStrings.getInstance().PROCESS);

				this.timeDelayHelper.delay = (int) duration;

				this.currentSongSound.getPlayer().start();
			}
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
		try
		{
			if(currentSongSound != null)
			{
				currentSongSound.getPlayer().stop();
				currentSongSound.getPlayer().close();
				currentSongSound = null;
			}
			this.timeDelayHelper.setStartTime(0);
	    }
	    catch (Exception e)
	    {
	        String resource = StringUtil.getInstance().EMPTY_STRING;
	        if (currentSongSound != null)
	        {
	            resource = currentSongSound.getResource();
	        }
	        
	        PreLogUtil.put(CommonStrings.getInstance().EXCEPTION_LABEL + resource, this, "stop", e);
	    }
	}
}
