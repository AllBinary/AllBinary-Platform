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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class PlayerQueue
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final BasicArrayList list = new BasicArrayList();
    private int max;
    private static final String TOTAL = "Sounds In Queue: ";

    PlayerQueue(int max)
    {
        this.max = max;
    }

    public void add(Sound sound)
    {
        try
        {

            if (!list.contains(sound))
            {
                //final String message = "Adding: ").append(sound.getResource();
                
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding: ").append(sound.getResource()).toString(), this, commonStrings.ADD));

                list.add(sound);
            }
        }
        catch (Exception e)
        {
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.ADD);
        }
    }

    public boolean process()
    {
        Sound sound = null;
        try
        {
            boolean played = false;

            if (list.size() > 0)
            {
                sound = (Sound) list.remove(0);

                //Should be possible, but it still happens?
                if (sound != null)
                {
                    //final String message = "Playing: ").append(sound.getResource();
                    //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Playing: ").append(sound.getResource()).toString(), this, commonStrings.PROCESS));

                    //For BB only
                    //SoundThreadPool.getInstance().runTask(sound);
                    sound.getPlayer().start();
                }

                while (list.size() > max)
                {
                    list.remove(0);
                }

                played = true;
            }
            return played;
        }
        catch (Exception e)
        {
            String resource = StringUtil.getInstance().EMPTY_STRING;
            if (sound != null)
            {
                resource = sound.getResource();
            }
            //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION_LABEL).append(resource, this,
            //      commonStrings.PROCESS, e));

            /*
            try
            {
                final String message = CommonStrings.getInstance();
             
                GameNotificationEvent gameNotificationEvent = 
                    new GameNotificationEvent(
                            this, message,
                            SmallIntegerSingletonFactory.getInstance(1),
                            BasicColor.RED,
                            Boolean.FALSE);

                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);                    
            }
            catch(Exception e2)
            {
                
            }
            */
            
            PreLogUtil.put(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(resource).toString(), this, commonStrings.PROCESS);

            return false;
        }
    }

    public void clear()
    {
        this.list.clear();
    }

    public String toString()
    {
        return new StringMaker().append(TOTAL).append(this.list.size()).toString();
    }
}
