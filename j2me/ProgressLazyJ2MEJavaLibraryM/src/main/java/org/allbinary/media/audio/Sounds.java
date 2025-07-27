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
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final SoundStrings soundStrings = SoundStrings.getInstance();
    
    private final SoundsFactoryInterface soundsFactoryInterface;

    public Sounds(SoundsFactoryInterface soundsFactoryInterface)
    {
        this.soundsFactoryInterface = soundsFactoryInterface;
    }

    public void init() throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.INIT);

        final CommonLabels commonLabels = CommonLabels.getInstance();        
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        
        soundsFactoryInterface.init();

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

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
                stringBuffer.append(commonLabels.INDEX_LABEL);
                stringBuffer.append(indexString);
                stringBuffer.append(this.soundStrings.SOUND);
                stringBuffer.append(soundInterface.getClass().getName());

                logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
                
                soundInterface.init();
                
                progressCanvas.addPortion(100, new StringMaker().append(this.soundStrings.INIT_SOUND).append(indexString).toString());
            }
        }

        soundsFactoryInterface.setInitialized(true);
        
        logUtil.put(commonStrings.END, this, commonStrings.INIT);
    }

    public void stopAll() throws Exception
    {
        logUtil.put(commonStrings.START, this, this.soundStrings.STOP_ALL);

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        
        Player player;
        for (int index = 0; index < soundInterfaceArray.length; index++)
        {
            if (soundInterfaceArray[index] != null)
            {
                player = soundInterfaceArray[index].getPlayerP();

                if (player != null)
                {
                    player.stop();

                    progressCanvas.addPortion(100, this.soundStrings.STOPPING_SOUND, index);
                }
            }
        }

    }

    public void closeAll() throws Exception
    {
        logUtil.put(commonStrings.START, this, this.soundStrings.CLOSE_ALL);

        final Sound[] soundInterfaceArray = soundsFactoryInterface.getSoundInterfaceArray();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        Player player;
        for (int index = 0; index < soundInterfaceArray.length; index++)
        {
            if (soundInterfaceArray[index] != null)
            {
                player = soundInterfaceArray[index].getPlayerP();
                if(player != null)
                {
                    player.close();

                    progressCanvas.addPortion(100, this.soundStrings.CLOSING_SOUND, index);
                }
            }
        }
    }
}
