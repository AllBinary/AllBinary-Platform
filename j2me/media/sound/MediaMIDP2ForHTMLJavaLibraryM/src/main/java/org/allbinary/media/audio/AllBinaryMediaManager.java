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

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class AllBinaryMediaManager
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final String THIS = "AllBinaryMediaManagerHTML5";

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
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonString = CommonStrings.getInstance();
        logUtil.put(commonString.START, THIS, commonString.INIT);
        ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        new Sounds(soundsFactoryInterface).init();
    }

    public static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception
    {

        new Sounds(soundsFactoryInterface).stopAll();

        new Sounds(soundsFactoryInterface).closeAll();

        System.gc();
    }

    public static Player createPlayer(String resource) throws Exception
    {
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            if (resource.compareTo(Manager.TONE_DEVICE_LOCATOR) == 0)
            {
                return Manager.createPlayer(resource);
            } else
            {
                //return Manager.createPlayer(resource);
                return Manager.createPlayer(resource.substring(0, resource.length() - 4));
            }
        } else
        {
            return new NoPlayer();
        }
    }

    public synchronized static void playTone(int frequency, int time, int volume)
            throws MediaException
    {
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            Manager.playTone(frequency, time, volume);
        }
    }
}
