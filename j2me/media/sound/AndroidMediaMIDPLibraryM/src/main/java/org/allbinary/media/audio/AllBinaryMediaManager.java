/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.media.audio;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;

public class AllBinaryMediaManager
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final String THIS = "AllBinaryMediaManagerAndroid";
    
    private static int mostUsedTotal = 0;

    private AllBinaryMediaManager()
    {
    }

    /**
     * @return the muted
     */
    public static boolean isMuted()
    {
        return false;
    }

    /**
     * @param aMuted the muted to set
     */
    public static void setMuted(boolean aMuted)
    {

    }

    public static boolean update()
    {
        return true;
    }

    public static void init(final SoundsFactoryInterface soundsFactoryInterface)
            throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonString = CommonStrings.getInstance();
        logUtil.put(commonString.START, THIS, commonString.INIT);

        AllBinaryMediaManager.shutdown(soundsFactoryInterface);

        ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        System.gc();

        new Sounds(soundsFactoryInterface).init();

        logUtil.put(commonString.END, THIS, commonString.INIT);
    }

    static void shutdown(final SoundsFactoryInterface soundsFactoryInterface)
            throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonString = CommonStrings.getInstance();
        logUtil.put(commonString.START, THIS, "shutdown");

        if (soundsFactoryInterface.isInitialized())
        {
            new Sounds(soundsFactoryInterface).stopAll();

            Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

            Player player;
            Player player2;
            AndroidMediaPlayerWrapper androidMediaPlayerWrapper;
            for (int i = 0; i < soundInterfaceArray.length; i++)
            {
                if (soundInterfaceArray[i] != null)
                {
                    player = soundInterfaceArray[i].getPlayer();

                    if (player != null)
                    {
                        //if (player instanceof PlayerComposite)
                        //{
                            player2 = ((PlayerComposite) player).getPlayer();

                            if (player2 instanceof AndroidMediaPlayerWrapper)
                            {
                                androidMediaPlayerWrapper = (AndroidMediaPlayerWrapper) player2;

                                MediaPlayerUtil.getInstance().wait(androidMediaPlayerWrapper.getMediaPlayer());
                            }
                            else
                            {
                                throw new Exception("Unknown Property Player: " + player.getClass().getName());
                            }
                        //}
                        //else
                        //{
                            //throw new Exception("Unknown Player: " + player.getClass().getName());
                        //}
                    }
                }
            }

            new Sounds(soundsFactoryInterface).closeAll();

            System.gc();

            soundsFactoryInterface.setInitialized(false);
            mostUsedTotal = 0;
        }
        logUtil.put(commonString.START, THIS, "shutdown");
    }

    public static Player createPlayer(String resource) throws Exception
    {
        mostUsedTotal++;
        final LogUtil logUtil = LogUtil.getInstance();
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            try
            {
                return new AndroidMediaPlayerWrapper(resource);
            }
            catch (Exception e)
            {
                logUtil.put(
                        "Could not create AndroidMediaPlayerWrapper using NoPlayer at " + 
                        CommonLabels.getInstance().TOTAL_LABEL
                                + mostUsedTotal, THIS,
                        "createPlayer", e);
                return NoPlayer.NO_PLAYER;
            }
        }
        else
        {
            return NoPlayer.NO_PLAYER;
        }

    }

    public static Player createPlayer(InputStream stream, String type)
            throws IOException, MediaException
    {
        // TWB not in Android .9
        throw new MediaException("No Input Stream Player");
    }

    public synchronized static void playTone(int frequency, int time, int volume)
            throws MediaException
    {
        // TWB not in Android .9
        throw new MediaException("No Tone Player");
    }

    public static boolean isInitialized()
    {
        return true;
    }
}
