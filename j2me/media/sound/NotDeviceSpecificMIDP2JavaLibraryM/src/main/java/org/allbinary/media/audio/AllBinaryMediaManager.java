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

import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.audio.AudioContentTypeDataFactory;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;


public class AllBinaryMediaManager
{

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
        LogUtil.put(LogFactory.getInstance("Start", "AllBinaryMediaManager None", "init"));
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
                InputStream inputStream = ResourceUtil.getInstance().getResourceAsStream(resource);
                return Manager.createPlayer(inputStream,
                        AudioContentTypeDataFactory.getInstance().MIME_AUDIO_WAV.getName());
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
