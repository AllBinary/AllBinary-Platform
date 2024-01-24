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

import javax.microedition.media.Control;
import javax.microedition.media.Controllable;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

import javax.microedition.media.TimeBase;
import javax.microedition.media.MediaException;

public class PlayerComposite implements Controllable, Player
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Player player;
    
    private final TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(0);
    
    public PlayerComposite(Player player)
    {
        this.player = player;
        this.timeElapsedHelper.delay = 570;
    }

    public PlayerComposite(Player player, int repeatTime)
    {
        this.player = player;
        this.timeElapsedHelper.delay = repeatTime;
    }

    /*
     * public PlayerComposite(Player player, boolean allowConcurrent) {
     * this.player = player;
     * 
     * if(!allowConcurrent) { this.repeatTime = this.getMediaTime(); } }
     */

    /*
     * public PlayerComposite(Player player, long repeatTime) { this.player =
     * player; this.repeatTime = repeatTime; timeElapsedHelper.setStartTime(); }
     */

    public synchronized void addPlayerListener(PlayerListener playerListener)
    {
        this.player.addPlayerListener(playerListener);
    }

    public void removePlayerListener(PlayerListener playerListener)
    {
        this.player.removePlayerListener(playerListener);
    }

    public void close()
    {
        this.player.close();
        //this.player = null;
    }

    public void deallocate()
    {
        this.player.deallocate();
    }

    public String getContentType()
    {
        return this.player.getContentType();
    }

    public long getDuration()
    {
        return this.player.getDuration();
    }

    public long getMediaTime()
    {
        return this.player.getMediaTime();
    }

   public TimeBase getTimeBase()
   {
      return ((TimeBaseInterface) this.player).getTimeBase();
   }

   public synchronized void setTimeBase(TimeBase timeBase)
       throws MediaException
   {
      ((TimeBaseInterface) this.player).setTimeBase(timeBase);
   }

    public void prefetch()
    {
        try
        {
            this.player.prefetch();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "prefetch", e));
        }
    }

    public void realize()
    {
        try
        {
            this.player.realize();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "realize", e));
        }
    }

    public int getState()
    {
        return this.player.getState();
    }

    public void setLoopCount(int count)
    {
        this.player.setLoopCount(count);
    }

    public long setMediaTime(long now)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings));
            return this.player.setMediaTime(now);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "setMediaTime", e));
            return -1;
        }
    }

    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
        GameTickTimeDelayHelperFactory.getInstance();
    
    public void start()
    {
        try
        {
            // this.getMediaTime()
            if (timeElapsedHelper.isTime(gameTickTimeDelayHelperFactory.getStartTime()))
            {
                this.player.start();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.START_METHOD_NAME, e));
        }
    }

    public void stop()
    {
        try
        {
            this.player.stop();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "stop", e));
        }
    }

    public Control getControl(String controlType)
    {
        return this.player.getControl(controlType);
    }

    public Control[] getControls()
    {
        return this.getControls();
    }

    public void setVolume(final int leftVolume, final int rightVolume) {
        ((Controllable2) this.player).setVolume(leftVolume, rightVolume);
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
}