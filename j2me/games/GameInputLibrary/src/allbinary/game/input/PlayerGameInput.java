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
package allbinary.game.input;

import org.allbinary.util.BasicArrayList;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventListenerInterface;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class PlayerGameInput extends GameInput 
      implements GameKeyEventListenerInterface
{
   public PlayerGameInput(BasicArrayList gameKeyEventList)
   {
      super(gameKeyEventList);      
   }

   /*
   public synchronized void add(GameKeyEvent gameKeyEvent)
   {
       LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + gameKeyEvent.getSource() + " " + ((GameKeyEventSourceInterface)gameKeyEvent.getSource()).getId(), this, CommonStrings.getInstance().ADD));
       super.add(gameKeyEvent);
   }
   */

   public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + gameKeyEvent.toString(), this, "onPressGameKeyEvent");
       super.add(gameKeyEvent);
       this.addForRemoval(gameKeyEvent);
   }
   
   public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + gameKeyEvent.toString(), this, "onDownGameKeyEvent");
       super.add(gameKeyEvent);
   }

   public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + gameKeyEvent.toString(), this, "onUpGameKeyEvent");
       this.addForRemoval(gameKeyEvent);
   }
   
   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);

       /*
      if (eventObject instance of GameKeyEvent)
      {
         this.onDownGameKeyEvent((GameKeyEvent) eventObject);
      }
      */
   }
}
