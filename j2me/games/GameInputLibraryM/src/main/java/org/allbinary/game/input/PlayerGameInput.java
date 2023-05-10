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
package org.allbinary.game.input;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventListenerInterface;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class PlayerGameInput extends GameInput 
      implements GameKeyEventListenerInterface
{
    private final int playerInputId;
    
   public PlayerGameInput(BasicArrayList gameKeyEventList, int playerInputId)
   {
      super(gameKeyEventList);
      
      this.playerInputId = playerInputId;
   }
   
   /*
   public synchronized void add(GameKeyEvent gameKeyEvent)
   {
       LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL).append(gameKeyEvent.getSource()).append(" ").append(((GameKeyEventSourceInterface)gameKeyEvent.getSource()).getId(), this, CommonStrings.getInstance().ADD));
       super.add(gameKeyEvent);
   }
   */

   public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL).append(gameKeyEvent.toString(), this, "onPressGameKeyEvent");
       super.add(gameKeyEvent);
       this.addForRemoval(gameKeyEvent);
   }
   
   public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL).append(gameKeyEvent.toString(), this, "onDownGameKeyEvent");
       super.add(gameKeyEvent);
   }

   public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(CommonStrings.getInstance().START_LABEL).append(gameKeyEvent.toString(), this, "onUpGameKeyEvent");
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

    /**
     * @return the playerInputId
     */
    public int getPlayerInputId() {
        return playerInputId;
    }
}
