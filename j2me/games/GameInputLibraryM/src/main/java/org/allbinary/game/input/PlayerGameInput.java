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
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class PlayerGameInput extends GameInput 
      implements GameKeyEventListenerInterface
{
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private final int playerInputId;
    
    public PlayerGameInput(final BasicArrayList gameKeyEventList, final int playerInputId) {
        this(gameKeyEventList, new BasicArrayList(), playerInputId);
    }
    
   public PlayerGameInput(final BasicArrayList gameKeyEventList, final BasicArrayList removalGameKeyEventList, final int playerInputId)
   {
      super(gameKeyEventList, removalGameKeyEventList);
      
      this.playerInputId = playerInputId;
   }
   
   /*
   public synchronized void add(GameKeyEvent gameKeyEvent)
   {
       LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL).append(gameKeyEvent.getSource()).append(" ").append(((GameKeyEventSourceInterface)gameKeyEvent.getSource()).getId(), this, commonStrings.ADD));
       super.add(gameKeyEvent);
   }
   */

   public synchronized void onPressGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_PRESS_GAME_KEY);
       super.add(gameKeyEvent);
       this.addForRemoval(gameKeyEvent);
   }
   
   public synchronized void onDownGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_DOWN_GAME_KEY);
       super.add(gameKeyEvent);
   }

   public synchronized void onDownKeyEvent(final Integer gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_DOWN_GAME_KEY);
   }
   
   public synchronized void onUpGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_UP_GAME_KEY);
       this.addForRemoval(gameKeyEvent);
   }

   public synchronized void onUpKeyEvent(final Integer gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_UP_GAME_KEY);
   }
   
   public void onEvent(final AllBinaryEventObject eventObject)
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
