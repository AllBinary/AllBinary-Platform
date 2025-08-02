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

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationListenerInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class GameNotificationHud extends BasicHud
implements GameNotificationListenerInterface
{
    public static final GameNotificationHud NULL_GAME_NOTIFICATION = new GameNotificationHud(
            		BasicHudFactory.getInstance().TOPCENTER, 
            		BasicHudFactory.getInstance().HORIZONTAL,
                    0, 0, 0, BasicColorFactory.getInstance().RED);

   public GameNotificationHud(int location, int direction,
       int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
   {
      super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);
   }

   @Override
   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
   }
   
   private final String METHOD_NAME = "onGameNotificationEvent";
  
   private GameNotificationEvent lastGameNotificationEvent = GameNotification.NULL_GAME_NOTIFICATION_EVENT;

   @Override
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
               gameNotificationEvent.getBasicColorP(), 
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