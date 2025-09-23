package org.allbinary.media.audio.music;
import org.allbinary.thread.ARunnable;


import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

import org.allbinary.AvianUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.audio.NoSound;
import org.allbinary.media.audio.PlayerStateUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class MusicManager {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
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

    private Sound currentSongSound = NoSound.getInstance();
    private Sound nextSongSound = NoSound.getInstance();

    private boolean reset;
    private boolean noDuration;
    private boolean stopped = true;
    
    public MusicManager(final Class musicServiceClass, final BasicArrayList songList) {
        this.songList = songList;
                
    }

    public void startNewSong()
    {
        //PreLogUtil.put("startNewSong - Stop MusicService", this, commonStrings.PROCESS);

        if (this.nextSongSound == NoSound.getInstance()) {
            final Sound randomSongSound = (Sound) basicArrayListUtil.getRandom(this.songList);
            this.nextSong(randomSongSound, 0, 0);
        }

        this.process();
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
                this.nextSongSound = NoSound.getInstance();
                if (nextSongSound == NoSound.getInstance()) {
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
                    this.currentSongSound.getPlayerP().addPlayerListener(playerListener);
                    this.noDuration = true;
                }

                if (endingCurrentSongSound != NoSound.getInstance()) {
//                    MusicThreadPool.getInstance().runTask(new ARunnable() {
//                        public void run() {
//                            try {

                                if(endingCurrentSongSound == startingCurrentSongSound && endingCurrentSongSound.getPlayerP().getState() == Player.STARTED) {

                                    PreLogUtil.put(new StringMaker().append(ALREADY_PLAYING).append(endingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);

                                    //this.addPlayNextOnEnd(endingCurrentSongSound, startingCurrentSongSound);

                                    PreLogUtil.put(new StringMaker().append(STOPPING).append(endingCurrentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);
                                    endingCurrentSongSound.getPlayerP().stop();
                                    //currentSongSound.getPlayerP().close();
                                    
                                    this.waitForStateChange(endingCurrentSongSound, startingCurrentSongSound);

                                } else {
                                    if(endingCurrentSongSound.getPlayerP().getState() == Player.STARTED) {
                                        PreLogUtil.put(new StringMaker().append(STOPPING).append(endingCurrentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);
                                        //this.addPlayNextOnEnd(endingCurrentSongSound, startingCurrentSongSound);
                                        endingCurrentSongSound.getPlayerP().stop();
                                        
                                        this.waitForStateChange(endingCurrentSongSound, startingCurrentSongSound);
                                    } else {
                                        PreLogUtil.put(new StringMaker().append(ALREADY_ENDED).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                                        if(AvianUtil.isAvian()) {
                                            endingCurrentSongSound.getPlayerP().stop();
                                            startingCurrentSongSound.getPlayerP().stop();
                                        }
                                        startingCurrentSongSound.getPlayerP().start();
                                    }
                                }

//                            } catch (Exception e) {
//                                String resource = StringUtil.getInstance().EMPTY_STRING;
//                                if (endingCurrentSongSound != NoSound.getInstance()) {
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
                    this.currentSongSound.getPlayerP().start();
                }
            }
        } catch (Exception e) {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (currentSongSound != NoSound.getInstance()) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.PROCESS, e);
        }
    }

//    public void addPlayNextOnEnd(final Sound endingCurrentSongSound, final Sound startingCurrentSongSound) {
//        endingCurrentSongSound.getPlayerP().addPlayerListener(new PlayerListener() {
//            public void playerUpdate(Player player, String event, Object eventData) {
//                try {
//                    PreLogUtil.put(new StringMaker().append(event).append(commonSeps.SPACE).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
//                    startingCurrentSongSound.getPlayerP().start();
//                    endingCurrentSongSound.getPlayerP().removePlayerListener(this);
//                } catch (Exception e) {
//                    PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
//                }
//            }
//        });
//    }

    private void waitForStateChange(final Sound endingCurrentSongSound, final Sound startingCurrentSongSound) throws Exception {
        while (endingCurrentSongSound.getPlayerP().getState() == Player.STARTED) {
            PreLogUtil.put(WAITING_FOR_MEDIA_TO_END, this, commonStrings.PROCESS);
            Thread.sleep(100);
        }
        
        PreLogUtil.put(new StringMaker().append(playerStateUtil.convert(endingCurrentSongSound.getPlayerP().getState())).append(commonSeps.SPACE).append(PLAY).append(startingCurrentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
        startingCurrentSongSound.getPlayerP().start();
    }
    
    public void stop()
            throws Exception {
        try {
            final Sound currentSongSound = this.currentSongSound;
            if (currentSongSound != NoSound.getInstance()) {
                stopped = true;
//                MusicThreadPool.getInstance().runTask(new ARunnable() {
//                    public void run() {
//                        try {
                            PreLogUtil.put(new StringMaker().append(ENDING).append(currentSongSound.getResource()).toString(), this, commonStrings.PROCESS);
                            currentSongSound.getPlayerP().stop();
//                            //currentSongSound.getPlayerP().close();
//                            //MusicManager.this.currentSongSound = NoSound.getInstance();
//                        } catch (Exception e) {
//                            String resource = StringUtil.getInstance().EMPTY_STRING;
//                            if (currentSongSound != NoSound.getInstance()) {
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
            if (currentSongSound != NoSound.getInstance()) {
                resource = currentSongSound.getResource();
            }

            PreLogUtil.put(commonStrings.EXCEPTION_LABEL + resource, this, commonStrings.END, e);
        }
    }
}
