package org.allbinary.media.audio.music;

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

    private final BasicArrayList songList;

    private Sound currentSongSound;
    private Sound nextSongSound;

    public MusicManager(BasicArrayList songList) {
        this.songList = songList;
    }

    private final String PLAY = "Play ";
    private final String SONG = " for: ";

    public void nextSong(final Sound nextSongSound) {
        this.nextSongSound = nextSongSound;
        this.reset();
    }
    
    public void reset() {
        this.timeDelayHelper.delay = 0;
    }

    public void process() {
        try {
            if(this.songList.size() == 0) {
                return;
            }

            if (this.timeDelayHelper.isTime(gameTickTimeDelayHelperFactory.getStartTime())) {
                final Sound currentSongSound = this.currentSongSound;

                if (currentSongSound != null) {
                    
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

                if (this.nextSongSound == null) {
                    this.currentSongSound = (Sound) BasicArrayListUtil.getInstance().getRandom(this.songList);
                } else {
                    this.currentSongSound = this.nextSongSound;
                    this.nextSongSound = null;
                }

                //this.currentSongSound.init();
                final long duration = this.currentSongSound.getPlayer().getDuration();
                PreLogUtil.put(new StringMaker().append(PLAY).append(this.currentSongSound.getResource()).append(SONG).append(duration).toString(), this, commonStrings.PROCESS);

                this.timeDelayHelper.delay = (int) duration;

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
