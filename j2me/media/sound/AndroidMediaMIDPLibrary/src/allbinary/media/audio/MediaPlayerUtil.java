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

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import android.media.MediaPlayer;

public class MediaPlayerUtil
{
    private static final MediaPlayerUtil instance = new MediaPlayerUtil();
    
    public static void wait(MediaPlayer mediaPlayer) throws Exception
    {
        final String MESSAGE = "Not Stopped Waiting";
        final String METHOD_NAME = "wait";

        int index = 0;
        while (mediaPlayer.isPlaying() && index < 50)
        {
            LogUtil.put(LogFactory.getInstance(MESSAGE, instance, METHOD_NAME));
            Thread.sleep(100);
            index++;
        }
    }
}
