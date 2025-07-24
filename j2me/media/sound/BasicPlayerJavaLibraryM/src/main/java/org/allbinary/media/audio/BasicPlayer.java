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
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import org.allbinary.logic.string.StringUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class BasicPlayer implements Player, Controllable2
{
   protected final CommonStrings commonStrings = CommonStrings.getInstance();

   public static String CONTROL_TYPE = "ToneControl";
   
   private int state;

   private int loopCount;
   //private TimeBase timeBase;

   protected final BasicArrayList listenersList = new BasicArrayList();

   public BasicPlayer()
   {
      this.setLoopCount(0);
      this.setState(Player.UNREALIZED);
   }

   @Override
   public String getContentType()
   {
       return StringUtil.getInstance().EMPTY_STRING;
   }

   @Override
   public Control getControl(String controlType)
   {
       return new NullControl();
   }

   @Override
   public Control[] getControls()
   {
       return new Control[0];
   }

   @Override
   public void close()
   {
   }
   
   @Override
   public synchronized void addPlayerListener(PlayerListener playerListener)
   {
      if(!this.listenersList.contains(playerListener)) {
          this.listenersList.add(playerListener);
      }
   }

   @Override
   public void removePlayerListener(PlayerListener playerListener)
   {
       this.listenersList.remove(playerListener);
   }

   @Override
   public int getState()
   {
      return this.state;
   }

   public synchronized void setState(int state)
   {
      this.state = state;
   }

   @Override
   public long getDuration()
   {
      return 0;
   }
   
   @Override
   public long getMediaTime()
   {
      return 0;
   }
   
   /*
   public TimeBase getTimeBase()
   {
      return this.timeBase;
   }
   
   public synchronized void setTimeBase(TimeBase timeBase)
   {
      this.timeBase = timeBase;
   }
   */
   
   @Override
   public void deallocate()
   {
   }
   
   @Override
   public void prefetch() throws MediaException
   {
   }
   
   @Override
   public void realize() throws MediaException
   {
   }

   @Override
   public synchronized void setLoopCount(int count)
   {
      this.loopCount = count;
   }

   protected int getLoopCount()
   {
      return this.loopCount;
   }

   @Override
   public synchronized long setMediaTime(long now) throws MediaException
   {
      return 0;
   }
   
   @Override
   public synchronized void start() throws MediaException
   {
      this.setState(Player.STARTED);
   }
   
   @Override
   public synchronized void stop() throws MediaException
   {
      this.setState(Player.PREFETCHED);
   }
   
   @Override
   public void setVolume(final int leftVolume, final int rightVolume) {
       
   }
}
