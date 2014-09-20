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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.audio.AudioContentTypeDataFactory;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import java.io.BufferedInputStream;

public class AllBinaryMediaManager {

    private AllBinaryMediaManager() {
    }

    public static void init(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {

        LogUtil.put(LogFactory.getInstance("Start", "AllBinaryMediaManager PC", "init"));
        ProgressCanvasFactory.getInstance().addPortion(50, "Media Manager");

        new Sounds(soundsFactoryInterface).init();
    }

    public static void shutdown(SoundsFactoryInterface soundsFactoryInterface)
            throws Exception {
        
        new Sounds(soundsFactoryInterface).stopAll();

        new Sounds(soundsFactoryInterface).closeAll();

        System.gc();
    }
    
    public static Player createPlayer(String resource) throws Exception {
        if (resource.startsWith(Manager.TONE_DEVICE_LOCATOR)) {
            return createPlayer(null, AudioContentTypeDataFactory.getInstance().MIME_AUDIO_TONE.getName());
        } else
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().SOUND))
        {
            try
            {
                InputStream inputStream =
                    ResourceUtil.getInstance().getResourceAsStream(resource);

                BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(inputStream);

                return new PCClipVorbisPlayer(bufferedInputStream);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(
                    "Exception", "AllBinaryMediaManager", "createPlayer", e));
                return new NoPlayer();
            }
        }
        else
        {
            return new NoPlayer();
        }
    }

    public static Player createPlayer(InputStream stream, String type)
            throws IOException, MediaException {
       
       throw new MediaException("No Impl");
    }

    public synchronized static void playTone(int frequency, int time, int volume)
            throws MediaException {
       throw new MediaException("No Impl");
    }

    public static boolean isInitialized() {
        return true;
    }
}
