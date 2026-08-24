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

import jsinterop.annotations.JsType;

import javax.microedition.media.Control;
import javax.microedition.media.Controllable;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.TimeBase;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

//BasicPlayerMIDP2

@JsType
public class PlayerComposite implements Controllable, Player
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Player player;
    
    private final TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(0);

    @JsConstructor
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

    @JsMethod
    public synchronized void addPlayerListener(PlayerListener playerListener)
    {
        this.player.addPlayerListener(playerListener);
    }

    @JsMethod
    public void removePlayerListener(PlayerListener playerListener)
    {
        this.player.removePlayerListener(playerListener);
    }

    @JsMethod
    public void close()
    {
        this.player.close();
        //this.player = null;
    }

    @JsMethod
    public void deallocate()
    {
        this.player.deallocate();
    }

    @JsMethod
    public String getContentType()
    {
        return this.player.getContentType();
    }

    @JsMethod
    public long getDuration()
    {
        return this.player.getDuration();
    }

    @JsMethod
    public long getMediaTime()
    {
        return this.player.getMediaTime();
    }

   @JsMethod
   public TimeBase getTimeBase()
   {
      return ((TimeBaseInterface) this.player).getTimeBase();
   }

   @JsMethod
   public synchronized void setTimeBase(TimeBase timeBase)
       throws MediaException
   {
      ((TimeBaseInterface) this.player).setTimeBase(timeBase);
   }

    @JsMethod
    public void prefetch()
    {
        try
        {
            this.player.prefetch();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "prefetch", e);
        }
    }

    @JsMethod
    public void realize()
    {
        try
        {
            this.player.realize();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "realize", e);
        }
    }

    @JsMethod
    public int getState()
    {
        return this.player.getState();
    }

    @JsMethod
    public void setLoopCount(int count)
    {
        this.player.setLoopCount(count);
    }

    @JsMethod
    public long setMediaTime(long now)
    {
        try
        {
            // this.logUtil.putF(this.commonStrings.START, this, this.commonStrings);
            return this.player.setMediaTime(now);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "setMediaTime", e);
            return -1;
        }
    }

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    
    @JsMethod
    public void start()
    {
        try
        {
            // this.getMediaTime()
            if (this.timeElapsedHelper.isTime(this.gameTickTimeDelayHelper.startTime))
            {
                this.player.start();
            }
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.START_METHOD_NAME, e);
        }
    }

    @JsMethod
    public void stop()
    {
        try
        {
            this.player.stop();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "stop", e);
        }
    }

    @JsMethod
    public Control getControl(String controlType)
    {
        return this.player.getControl(controlType);
    }

    @JsMethod
    public Control[] getControls()
    {
        throw new RuntimeException();
        //return ;
    }

    @JsMethod
    public void setVolume(final int leftVolume, final int rightVolume) {
        final Controllable2 controllable2 = ((Controllable2) /*TS as unknown*/ this.player);
        controllable2.setVolume(leftVolume, rightVolume);
    }
    
    @JsMethod
    public Player getPlayerP()
    {
        return this.player;
    }
}