package org.allbinary.media.audio.music;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import org.allbinary.AvianUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.audio.PlayerStateUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.time.GameTickTimeDelayHelper;
//import org.allbinary.thread.MusicThreadPool;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class MusicManager {

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    private final PlayerStateUtil playerStateUtil = PlayerStateUtil.getInstance();

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);

    //Handle HTML5 duration with media not playing
    //Handle Media ending for Avian
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
    private final String ENDING = "Ending Current Song: ";
    
    private final String WAITING_FOR_MEDIA_TO_END = "Waiting for media to end";
    private final String ALREADY_PLAYING = "Already Playing: ";
    private final String ALREADY_ENDED = "Last Song already ended: ";

    private final BasicArrayList songList;

    private Sound currentSongSound;
    private Sound nextSongSound;

    private boolean reset;
    private boolean noDuration;
    private boolean stopped = true;
    
    public MusicManager(BasicArrayList songList) {
        this.songList = songList;
                
    }

    public void nextSong(final Sound nextSongSound, final int leftVolume, final int rightVolume) {
        PreLogUtil.put(new StringMaker().append(NEXT_SONG).append(nextSongSound.getResource()).toString(), this, commonStrings.PROCESS);
        this.nextSongSound = nextSongSound;
        this.reset();
        this.stopped = false;
    }
    
    public void reset() {
        this.reset = true;
    }

    public void process() {
        try {
            if(stopped) {
               return; 
            }

            if(this.songList.size() == 0) {
                return;
            }

            if ((this.timeDelayHelper.isTime(gameTickTimeDelayHelper.startTime) && !this.noDuration) || this.reset) {
                this.reset = false;
                this.noDuration = false;

                final Sound endingCurrentSongSound = this.currentSongSound;
                
                final Sound nextSongSound = this.nextSongSound;
                this.nextSongSound = null;
                if (nextSongSound == null) {
                    this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);
                } else {
                    this.currentSongSound = nextSongSound;
                }

                final Sound startingCurrentSongSound = this.currentSongSound;

                //this.currentSongSound.init();
                final long duration = this.currentSongSound.getDuration();

                this.timeDelayHelper.delay = (int) duration;

                if(duration <= 0) {
                    //Handle HTML5 duration with media not playing
                    final String NO_DURATION_FOR = "No Duration for: ";
                    //PreLogUtil.put(new StringMaker().append("nextSongSound duration: ").append(nextSongSound.getDuration()).toString(), this, commonStrings.PROCESS);
                    PreLogUtil.put(new StringMaker().append(NO_DURATION_FOR).append(this.currentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                    this.currentSongSound.getPlayer().addPlayerListener(playerListener);
                    this.noDuration = true;
                }

                if (endingCurrentSongSound != null) {
//                    MusicThreadPool.getInstance().runTask(new Runnable() {
//                        public void run() {
//                            try {

                                if(endingCurrentSongSound == startingCurrentSongSound && endingCurrentSongSound.getPlayer().getState() == Player.STARTED) {

                                    PreLogUtil.put(new StringMaker().append(ALREADY_PLAYING).append(endingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);

                                    //this.addPlayNextOnEnd(endingCurrentSongSound, startingCurrentSongSound);

                                    PreLogUtil.put(new StringMaker().append(STOPPING).append(endingCurrentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);
                                    endingCurrentSongSound.getPlayer().stop();
                                    //currentSongSound.getPlayer().close();
                                    
                                    this.waitForStateChange(endingCurrentSongSound, startingCurrentSongSound);

                                } else {
                                    if(endingCurrentSongSound.getPlayer().getState() == Player.STARTED) {
                                        PreLogUtil.put(new StringMaker().append(STOPPING).append(endingCurrentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);
                                        //this.addPlayNextOnEnd(endingCurrentSongSound, startingCurrentSongSound);
                                        endingCurrentSongSound.getPlayer().stop();
                                        
                                        this.waitForStateChange(endingCurrentSongSound, startingCurrentSongSound);
                                    } else {
                                        PreLogUtil.put(new StringMaker().append(ALREADY_ENDED).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                                        if(AvianUtil.isAvian()) {
                                            endingCurrentSongSound.getPlayer().stop();
                                            startingCurrentSongSound.getPlayer().stop();
                                        }
                                        startingCurrentSongSound.getPlayer().start();
                                    }
                                }

//                            } catch (Exception e) {
//                                String resource = StringUtil.getInstance().EMPTY_STRING;
//                                if (endingCurrentSongSound != null) {
//                                    resource = endingCurrentSongSound.getResource();
//                                }
//
//                                PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
//                            }
//                        }
//                    }
//                    );

                } else {
                    PreLogUtil.put(new StringMaker().append(PLAY).append(this.currentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);
                    //MusicThreadPool.getInstance().runTask(this.currentSongSound);
                    this.currentSongSound.getPlayer().start();
                }
            }
        } catch (Exception e) {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
        }
    }

//    public void addPlayNextOnEnd(final Sound endingCurrentSongSound, final Sound startingCurrentSongSound) {
//        endingCurrentSongSound.getPlayer().addPlayerListener(new PlayerListener() {
//            public void playerUpdate(Player player, String event, Object eventData) {
//                try {
//                    PreLogUtil.put(new StringMaker().append(event).append(commonSeps.SPACE).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
//                    startingCurrentSongSound.getPlayer().start();
//                    endingCurrentSongSound.getPlayer().removePlayerListener(this);
//                } catch (Exception e) {
//                    PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
//                }
//            }
//        });
//    }

    private void waitForStateChange(final Sound endingCurrentSongSound, final Sound startingCurrentSongSound) throws Exception {
        while (endingCurrentSongSound.getPlayer().getState() == Player.STARTED) {
            PreLogUtil.put(WAITING_FOR_MEDIA_TO_END, this, commonStrings.PROCESS);
            Thread.sleep(100);
        }
        
        PreLogUtil.put(new StringMaker().append(playerStateUtil.convert(endingCurrentSongSound.getPlayer().getState())).append(commonSeps.SPACE).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
        startingCurrentSongSound.getPlayer().start();
    }
    
    public void stop()
            throws Exception {
        try {
            final Sound currentSongSound = this.currentSongSound;
            if (currentSongSound != null) {
                stopped = true;
//                MusicThreadPool.getInstance().runTask(new Runnable() {
//                    public void run() {
//                        try {
                            PreLogUtil.put(new StringMaker().append(ENDING).append(currentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                            currentSongSound.getPlayer().stop();
//                            //currentSongSound.getPlayer().close();
//                            //MusicManager.this.currentSongSound = null;
//                        } catch (Exception e) {
//                            String resource = StringUtil.getInstance().EMPTY_STRING;
//                            if (currentSongSound != null) {
//                                resource = currentSongSound.getResource();
//                            }
//
//                            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
//                        }
//                    }
//                }
//                );

            }
            this.timeDelayHelper.setStartTime(0);
            
            PreLogUtil.put(new StringMaker().append(commonStrings.END).append(StringUtil.getInstance().toString(currentSongSound)).toString(), this, commonStrings.END);

        } catch (Exception e) {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != null) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.END, e);
        }
    }
}
