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
package allbinary.game.layer.hud.basic.notification;

import javax.microedition.lcdui.Graphics;

import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.graphics.hud.BasicHud;
import allbinary.game.layer.hud.basic.event.GameNotificationEvent;
import allbinary.game.layer.hud.basic.event.GameNotificationListenerInterface;
import allbinary.graphics.color.BasicColor;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameNotificationHud extends BasicHud
implements GameNotificationListenerInterface
{
   protected GameNotificationHud(int location, int direction,
       int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
       throws Exception
   {
      super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);
   }

   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
   }
   
   private final String METHOD_NAME = "onGameNotificationEvent";
   
   public void onGameNotificationEvent(
           GameNotificationEvent gameNotificationEvent) 
   throws Exception
   {
       LogUtil.put(LogFactory.getInstance(
               //CommonStrings.getInstance().START_LABEL + 
               gameNotificationEvent.getString(),
               this, METHOD_NAME));

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