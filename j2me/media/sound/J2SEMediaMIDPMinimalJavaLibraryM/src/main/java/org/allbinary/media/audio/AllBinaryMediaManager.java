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
import java.io.BufferedInputStream;

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
import org.allbinary.string.CommonStrings;

public class AllBinaryMediaManager {

    private static final String THIS = "AllBinaryMediaManagerPC";
    
    private AllBinaryMediaManager() {
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
    public static void setMuted(final boolean aMuted)
    {

    }

    public static boolean update()
    {
        return true;
    }

    public static void init(final SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {

        final CommonStrings commonString = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(commonString.START, THIS, commonString.INIT));
        ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        new Sounds(soundsFactoryInterface).init();
    }

    public static void shutdown(final SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {
        
        new Sounds(soundsFactoryInterface).stopAll();

        new Sounds(soundsFactoryInterface).closeAll();

        System.gc();
    }
    
    private static final String CREATE_PLAYER = "createPlayer";
    
    public static Player createPlayer(final String resource) throws Exception {
        if (resource.startsWith(Manager.TONE_DEVICE_LOCATOR)) {
            return createPlayer(null, AudioContentTypeDataFactory.getInstance().MIME_AUDIO_TONE.getName());
        } else
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            try
            {
                LogUtil.put(LogFactory.getInstance(resource, THIS, CREATE_PLAYER));
                
                final InputStream inputStream =
                    ResourceUtil.getInstance().getResourceAsStream(resource);

                final BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(inputStream);

                return new PCClipWavPlayer(bufferedInputStream);
            }
            catch (Exception e)
            {
                final CommonStrings commonString = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonString.EXCEPTION, THIS, CREATE_PLAYER, e));
                return new NoPlayer();
            }
        }
        else
        {
            return new NoPlayer();
        }
    }

    public static Player createPlayer(final InputStream stream, final String type)
            throws IOException, MediaException {
       
       throw new MediaException("No Impl");
    }

    public synchronized static void playTone(final int frequency, final int time, final int volume)
            throws MediaException {
       throw new MediaException("No Impl");
    }

    public static boolean isInitialized() {
        return true;
    }
}
