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

import jsinterop.annotations.JsType;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventListenerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonLabels;
import org.allbinary.util.BasicArrayList;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class PlayerGameInput extends GameInput 
      implements GameKeyEventListenerInterface
{

    @JsProperty
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private final int playerInputId;

   @JsConstructor
   public PlayerGameInput(final BasicArrayList gameKeyEventList, final BasicArrayList removalGameKeyEventList, final int playerInputId)
   {
      super(gameKeyEventList, removalGameKeyEventList);
      
      this.playerInputId = playerInputId;
   }
   
   /*
   public synchronized void add(GameKeyEvent gameKeyEvent)
   {
       this.logUtil.putF(this.commonStrings.START_LABEL).append(gameKeyEvent.getSource()).append(" ").append(((GameKeyEventSourceInterface)gameKeyEvent.getSource()).getId(), this, this.commonStrings.ADD);
       super.add(gameKeyEvent);
   }
   */

   @Override
   @JsMethod
   public synchronized void onPressGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_PRESS_GAME_KEY);
       this.add(gameKeyEvent);
       this.addForRemoval(gameKeyEvent);
   }
   
   @Override
   @JsMethod
   public synchronized void onDownGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_DOWN_GAME_KEY);
       this.add(gameKeyEvent);
   }

   @JsMethod
   public synchronized void onDownKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_DOWN_GAME_KEY);
       this.add(gameKeyEvent);
       this.addForRemoval(gameKeyEvent);
   }
   
   @JsMethod
   public synchronized void onDownKey(final Integer gameKeyEvent)
   {
       PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, this.gameInputStrings.ON_DOWN_GAME_KEY);
   }
   
   @Override
   @JsMethod
   public synchronized void onUpGameKeyEvent(final GameKeyEvent gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_UP_GAME_KEY);
       this.addForRemoval(gameKeyEvent);
   }

   @JsMethod
   public synchronized void onUpKeyEvent(final Integer gameKeyEvent)
   {
       //PreLogUtil.put(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameKeyEvent.toString()).toString(), this, gameInputStrings.ON_UP_GAME_KEY);
   }
   
   @Override
   @JsMethod
   public void onEvent(final AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);

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
    @JsMethod
    public int getPlayerInputId() {
        return this.playerInputId;
    }
}
