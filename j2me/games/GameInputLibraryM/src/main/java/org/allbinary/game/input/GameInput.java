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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.event.GameKeyEvent;

public class GameInput {

   private final BasicArrayList gameKeyEventList;
   private final BasicArrayList removalGameKeyEventList;

   private final boolean isRemoveDuplicateKeyPresses;
   
   public GameInput(BasicArrayList gameKeyEventList) {
      this.gameKeyEventList = gameKeyEventList;
      this.removalGameKeyEventList = new BasicArrayList();
      
      this.isRemoveDuplicateKeyPresses = Features.getInstance().isFeature(
              InputFeatureFactory.getInstance().REMOVE_DUPLICATE_KEY_PRESSES);
   }

   public synchronized void add(GameKeyEvent gameKeyEvent) {

      //TODO TWB - fix all null and duplicate issue for better performance
      if(this.isRemoveDuplicateKeyPresses && this.gameKeyEventList.contains(gameKeyEvent))
      {
          //LogUtil.put(LogFactory.getInstance("Danger Danger Danger: Duplicate GameKeyEvent", this, CommonStrings.getInstance().ADD));
          return;
      }
       
      if (gameKeyEvent != null) {
          this.gameKeyEventList.add(gameKeyEvent);
      } else {
          LogUtil.put(LogFactory.getInstance("Danger Passed Null GameKeyEvent", this, CommonStrings.getInstance().ADD));
      }
   }

   public synchronized void addForRemoval(GameKeyEvent gameKeyEvent) {
      this.removalGameKeyEventList.add(gameKeyEvent);
   }

   public synchronized boolean isForRemoval(GameKeyEvent gameKeyEvent) {
      return this.removalGameKeyEventList.contains(gameKeyEvent);
   }

   public synchronized void clear() {
      this.gameKeyEventList.clear();
   }

   public synchronized void removeNonAIInputGameKeyEvents()
   {
       BasicArrayList list = this.gameKeyEventList;
       
       //System.out.println("List Size Before: " + list.size());
       
       GameKeyEvent gameKeyEvent;
       for (int index = list.size(); --index >= 0;) {
          gameKeyEvent = (GameKeyEvent) list.objectArray[index];
          //if (((GameKeyEventSourceInterface) gameKeyEvent.getSource()).getSourceId() != 1) {
          if (gameKeyEvent.getSourceId() != 1) {
             list.remove(index);
          }
       }
       //System.out.println("List Size After: " + list.size());
   }
   
   /*
   public synchronized void removeDuplicates(GameKeyEvent gameKeyEvent) {
      BasicArrayList list = this.gameKeyEventList;
      Object object = gameKeyEvent;
      for (int index = 0; index < list.size(); index++) {
         if (list.get(index) == object) {
            list.remove(index);
         }
      }
   }
   */

   public synchronized void update() {

      BasicArrayList removeList = this.removalGameKeyEventList;
      BasicArrayList list = this.gameKeyEventList;
      int size = removeList.size();
      for (int index = 0; index < size; index++) {
         Object object = removeList.objectArray[index];
         for (int index2 = list.size(); --index2 >= 0;) {
            if (list.objectArray[index2] == object) {
               list.remove(index2);
            }
         }
      }

      //GameKeyEvent GAMEKEYEVENT_NONE = GameKeyEventFactory.getInstance(this, gameKeyFactory.NONE);
      //this.gameKeyEventList.remove();
      
      removeList.clear();
   }

    /**
     * @return the gameKeyEventList
     */
    protected BasicArrayList getGameKeyEventList()
    {
        return gameKeyEventList;
    }
}
