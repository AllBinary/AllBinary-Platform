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

import javax.microedition.media.Player;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class Sounds
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final SoundsFactoryInterface soundsFactoryInterface;

    public Sounds(SoundsFactoryInterface soundsFactoryInterface)
    {
        this.soundsFactoryInterface = soundsFactoryInterface;
    }

    public void init() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));

        final String SOUND = " Sound: ";
        
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        
        soundsFactoryInterface.init();

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

        final String INIT_SOUND = "Initializing Sound: ";

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        Integer indexInteger;
        String indexString;
        final StringMaker stringBuffer = new StringMaker();
        for (int i = 0; i < soundInterfaceArray.length; i++)
        {

            final Sound soundInterface = soundInterfaceArray[i];

            if (soundInterface != null)
            {
                indexInteger = smallIntegerSingletonFactory.getInstance(i);
                indexString = indexInteger.toString();
                
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(CommonLabels.getInstance().INDEX_LABEL);
                stringBuffer.append(indexString);
                stringBuffer.append(SOUND);
                stringBuffer.append(soundInterface.getClass().getName());

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, commonStrings.INIT));
                
                soundInterface.init();
                
                progressCanvas.addPortion(100, new StringMaker().append(INIT_SOUND).append(indexString).toString());
            }
        }

        soundsFactoryInterface.setInitialized(true);
        
        LogUtil.put(LogFactory.getInstance(commonStrings.END, this, commonStrings.INIT));
    }

    public void stopAll() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "stopAll"));

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        
        Player player;
        for (int index = 0; index < soundInterfaceArray.length; index++)
        {
            if (soundInterfaceArray[index] != null)
            {
                player = soundInterfaceArray[index].getPlayer();

                if (player != null)
                {
                    player.stop();

                    progressCanvas.addPortion(100, "Stopping Sound: ", index);
                }
            }
        }

    }

    public void closeAll() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "closeAll"));

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        Player player;
        for (int index = 0; index < soundInterfaceArray.length; index++)
        {
            if (soundInterfaceArray[index] != null)
            {
                player = soundInterfaceArray[index].getPlayer();
                if(player != null)
                {
                    player.close();

                    progressCanvas.addPortion(100, "Closing Sound: ", index);
                }
            }
        }
    }
}
