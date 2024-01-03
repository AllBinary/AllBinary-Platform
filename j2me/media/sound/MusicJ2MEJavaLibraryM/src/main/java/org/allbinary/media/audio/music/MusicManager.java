package org.allbinary.media.audio.music;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.audio.Sound;
import org.allbinary.thread.MusicThreadPool;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class MusicManager {

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = GameTickTimeDelayHelperFactory.getInstance();

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);

    //Handle HTML5 duration with media not playing
    private final PlayerListener playerListener = new PlayerListener() {

        public void playerUpdate(final Player player, final String event, final Object eventData) {

            PreLogUtil.put(event, this, commonStrings.PROCESS);
            if (event == PlayerListener.END_OF_MEDIA
                    || event == PlayerListener.STOPPED
                    || event == PlayerListener.CLOSED) {
                reset();
            }
        }
    };
    
    private final String PLAY = "Play ";
    private final String SONG = " for: ";
    private final String NEXT_SONG = "Next Song: ";
    private final String STOPPING = "Stopping Current Song: ";

    private final BasicArrayList songList;

    private Sound currentSongSound;
    private Sound nextSongSound;

    private boolean reset;
    private boolean noDuration;
    
    public MusicManager(BasicArrayList songList) {
        this.songList = songList;
                
    }

    public void nextSong(final Sound nextSongSound) {
        PreLogUtil.put(new StringMaker().append(NEXT_SONG).append(nextSongSound).toString(), this, commonStrings.PROCESS);
        this.nextSongSound = nextSongSound;
        this.reset();
    }
    
    public void reset() {
        this.reset = true;
    }

    public void process() {
        try {
            if(this.songList.size() == 0) {
                return;
            }

            if ((this.timeDelayHelper.isTime(gameTickTimeDelayHelperFactory.getStartTime()) && !this.noDuration) || this.reset) {
                this.reset = false;
                this.noDuration = false;
                final Sound currentSongSound = this.currentSongSound;
                if (currentSongSound != null) {
                    PreLogUtil.put(new StringMaker().append(STOPPING).append(currentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                    MusicThreadPool.getInstance().runTask(new Runnable() {
                        public void run() {
                            try {
                                currentSongSound.getPlayer().stop();
                                //currentSongSound.getPlayer().close();
                            } catch (Exception e) {
                                String resource = StringUtil.getInstance().EMPTY_STRING;
                                if (currentSongSound != null) {
                                    resource = currentSongSound.getResource();
                                }

                                PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
                            }
                        }
                    }
                    );

                }

                final Sound nextSongSound = this.nextSongSound;
                this.nextSongSound = null;
                if (nextSongSound == null) {
                    this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);
                } else {
                    this.currentSongSound = nextSongSound;
                }

                //this.currentSongSound.init();
                final long duration = this.currentSongSound.getPlayer().getDuration();

                PreLogUtil.put(new StringMaker().append(PLAY).append(this.currentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);

                this.timeDelayHelper.delay = (int) duration;

                if(duration <= 0) {
                    //Handle HTML5 duration with media not playing
                    final String NO_DURATION_FOR = "No Duration for: ";
                    //PreLogUtil.put(new StringMaker().append("nextSongSound duration: ").append(nextSongSound.getDuration()).toString(), this, commonStrings.PROCESS);
                    PreLogUtil.put(new StringMaker().append(NO_DURATION_FOR).append(this.currentSongSound).toString(), this, commonStrings.PROCESS);
                    this.currentSongSound.getPlayer().addPlayerListener(playerListener);
                    this.noDuration = true;
                }
                                
                MusicThreadPool.getInstance().runTask(currentSongSound);
                //this.currentSongSound.getPlayer().start();
            }
        } catch (Exception e) {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
        }
    }

    public void stop()
            throws Exception {
        try {
            final Sound currentSongSound = this.currentSongSound;
            if (currentSongSound != null) {

                MusicThreadPool.getInstance().runTask(new Runnable() {
                    public void run() {
                        try {
                            currentSongSound.getPlayer().stop();
                            currentSongSound.getPlayer().close();
                            //MusicManager.this.currentSongSound = null;
                        } catch (Exception e) {
                            String resource = StringUtil.getInstance().EMPTY_STRING;
                            if (currentSongSound != null) {
                                resource = currentSongSound.getResource();
                            }

                            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
                        }
                    }
                }
                );

            }
            this.timeDelayHelper.setStartTime(0);
        } catch (Exception e) {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.END, e);
        }
    }
}
