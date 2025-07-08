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
package org.allbinary.game.layer.hud.basic.notification;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationListenerInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class GameNotificationHud extends BasicHud
implements GameNotificationListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected GameNotificationHud(int location, int direction,
       int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
       throws Exception
   {
      super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);
   }

   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
   }
   
   private final String METHOD_NAME = "onGameNotificationEvent";
  
   private GameNotificationEvent lastGameNotificationEvent = null;

   public void onGameNotificationEvent(
           final GameNotificationEvent gameNotificationEvent) 
   throws Exception
   {
       if(lastGameNotificationEvent != gameNotificationEvent) {
           lastGameNotificationEvent = gameNotificationEvent;
           logUtil.put(
               //commonStrings.START_LABEL + 
               gameNotificationEvent.getString(),
               this, METHOD_NAME);
       }

       this.add(
               gameNotificationEvent.getString(),
               gameNotificationEvent.getSeconds(), 
               gameNotificationEvent.getBasicColor(), 
               gameNotificationEvent.getPermanent());
   }
   
    protected void add(String string, Integer seconds, BasicColor basicColor, Boolean permanent)
    {
    }

    public void processTick() throws Exception
    {
    }

    public void paint(Graphics graphics)
    {
    }

    public void clear()
    {
    }
}