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
package org.allbinary.game.ai;

import org.allbinary.game.ai.BasicAI;
import java.util.Hashtable;

import org.allbinary.util.HashtableUtil;

import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class KeyPressesAI extends BasicAI {

   private boolean on = true;
   protected Integer[] keys;

   public KeyPressesAI(Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput) {
      super(ownerLayerInterface, gameInput);

      this.keys = new Integer[hashtable.size()];

      Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
      int size = objectArray.length;
      
      for (int index = 0; index < size; index++) {
         keys[index] = (Integer) hashtable.get(objectArray[index]);
      }
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception {
      if (on) {
         //logUtil.put(commonStrings.START, this, commonStrings.PROCESS);
         for (int index = 0; index < keys.length; index++) {
            super.processAI(keys[index].intValue());
         }
      }
   }

   public void toggle() throws Exception {
      if (isOn()) {
         setOn(false);
      } else {
         setOn(true);
      }
   }

   public void disable() throws Exception {
       setOn(false);
    }

   public void enable() throws Exception {
       setOn(true);
   }
   
   private void setOn(boolean on) {
      this.on = on;
   }

   protected boolean isOn() {
      return on;
   }
}