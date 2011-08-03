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
package allbinary.media.audio;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureFactory;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;

public class AllBinaryMediaManager
{
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

    public static void init(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "AllBinaryMediaManager", CommonStrings.getInstance().INIT));

        AllBinaryMediaManager.shutdown(soundsFactoryInterface);

        ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        System.gc();

        new Sounds(soundsFactoryInterface).init();

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, "AllBinaryMediaManager", CommonStrings.getInstance().INIT));
    }

    static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "AllBinaryMediaManager", "shutdown"));

        if (soundsFactoryInterface.isInitialized())
        {
            new Sounds(soundsFactoryInterface).stopAll();

            Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

            for (int i = 0; i < soundInterfaceArray.length; i++)
            {
                if (soundInterfaceArray[i] != null)
                {
                    Player player = soundInterfaceArray[i].getPlayer();

                    if (player != null)
                    {
                        //if (player instanceof PlayerComposite)
                        //{
                            Player player2 = ((PlayerComposite) player).getPlayer();

                            if (player2 instanceof AndroidMediaPlayerWrapper)
                            {
                                AndroidMediaPlayerWrapper androidMediaPlayerWrapper = 
                                    (AndroidMediaPlayerWrapper) player2;

                                MediaPlayerUtil.wait(androidMediaPlayerWrapper.getMediaPlayer());
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
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "AllBinaryMediaManager", "shutdown"));
    }

    public static Player createPlayer(String resource) throws Exception
    {
        mostUsedTotal++;
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            try
            {
                return new AndroidMediaPlayerWrapper(resource);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(
                        "Could not create AndroidMediaPlayerWrapper using NoPlayer at " + 
                        CommonStrings.getInstance().TOTAL_LABEL
                                + mostUsedTotal, "AllBinaryMediaManager",
                        "createPlayer", e));
                return new NoPlayer();
            }
        }
        else
        {
            return new NoPlayer();
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
