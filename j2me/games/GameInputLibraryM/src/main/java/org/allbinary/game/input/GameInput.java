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

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class GameInput {
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   private final BasicArrayList gameKeyEventList;
   private final BasicArrayList removalGameKeyEventList;

   protected final boolean isRemoveDuplicateKeyPresses;
   
   public GameInput(final BasicArrayList gameKeyEventList) {
       this(gameKeyEventList, new BasicArrayList());
   }
   
   public GameInput(final BasicArrayList gameKeyEventList, final BasicArrayList removalGameKeyEventList) {
      this.gameKeyEventList = gameKeyEventList;
      this.removalGameKeyEventList = removalGameKeyEventList;
      
      this.isRemoveDuplicateKeyPresses = Features.getInstance().isFeature(
              InputFeatureFactory.getInstance().REMOVE_DUPLICATE_KEY_PRESSES);
   }

   public synchronized void add(GameKeyEvent gameKeyEvent) {

      //TODO TWB - fix all null and duplicate issue for better performance
      if(this.isRemoveDuplicateKeyPresses && this.gameKeyEventList.contains(gameKeyEvent))
      {
          //logUtil.put("Danger Danger Danger: Duplicate GameKeyEvent", this, commonStrings.ADD);
          return;
      }
       
      if (gameKeyEvent != null) {
          this.gameKeyEventList.add(gameKeyEvent);
      } else {
          logUtil.put("Danger Passed Null GameKeyEvent", this, commonStrings.ADD);
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
      final int size = list.size();
      for (int index = 0; index < size; index++) {
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
    protected BasicArrayList getRemovalGameKeyEventList()
    {
        return this.removalGameKeyEventList;
    }
   
    /**
     * @return the gameKeyEventList
     */
    protected BasicArrayList getGameKeyEventList()
    {
        return gameKeyEventList;
    }
}
