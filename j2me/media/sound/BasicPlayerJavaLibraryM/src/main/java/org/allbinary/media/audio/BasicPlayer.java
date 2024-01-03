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

import org.allbinary.util.BasicArrayList;

public class BasicPlayer implements Player, Controllable2
{
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

   public String getContentType()
   {
       return null;
   }

   public Control getControl(String controlType)
   {
       return null;
   }

   public Control[] getControls()
   {
       return new Control[0];
   }

   public void close()
   {
   }
   
   public synchronized void addPlayerListener(PlayerListener playerListener)
   {
      if(!this.listenersList.contains(playerListener)) {
          this.listenersList.add(playerListener);
      }
   }

   public void removePlayerListener(PlayerListener playerListener)
   {
       this.listenersList.remove(playerListener);
   }

   public int getState()
   {
      return this.state;
   }

   public synchronized void setState(int state)
   {
      this.state = state;
   }

   public long getDuration()
   {
      return 0;
   }
   
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
   
   public void deallocate()
   {
   }
   
   public void prefetch() throws MediaException
   {
   }
   
   public void realize() throws MediaException
   {
   }
      
   public synchronized void setLoopCount(int count)
   {
      this.loopCount = count;
   }
   
   protected int getLoopCount()
   {
      return this.loopCount;
   }

   public synchronized long setMediaTime(long now) throws MediaException
   {
      return 0;
   }
   
   public synchronized void start() throws MediaException
   {
      this.setState(Player.STARTED);
   }
   
   public synchronized void stop() throws MediaException
   {
      this.setState(Player.PREFETCHED);
   }
   
   public void setVolume(final int leftVolume, final int rightVolume) {
       
   }
}
