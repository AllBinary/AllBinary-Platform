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

import jsinterop.annotations.JsType;

import java.util.Hashtable;

import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.util.HashtableUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class KeyPressesAI extends BasicAI {

   private boolean on = true;
   @JsProperty
   protected Integer[] keys;

   @JsConstructor
   public KeyPressesAI(Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput) {
      super(ownerLayerInterface, gameInput);

      this.keys = new Integer[hashtable.size()];

      Object[] objectArray = HashtableUtil.getInstance().getKeysAsArray(hashtable);
      int size = objectArray.length;
      
      for (int index = 0; index < size; index++) {
         this.keys[index] = (Integer) hashtable.get((Object) objectArray[index]);
      }
   }

   @Override
   @JsMethod
   public void processAI(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception {
      if (this.on) {
         //this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.PROCESS);
         for (int index = 0; index < this.keys.length; index++) {
            super.processKeyAI(this.keys[index].intValue());
         }
      }
   }

   @JsMethod
   public void toggle() throws Exception {
      if (this.isOn()) {
         this.setOn(false);
      } else {
         this.setOn(true);
      }
   }

   @JsMethod
   public void disable() throws Exception {
       this.setOn(false);
    }

   @JsMethod
   public void enable() throws Exception {
       this.setOn(true);
   }
   
   @JsMethod
   private void setOn(boolean on) {
      this.on = on;
   }

   @JsMethod
   protected boolean isOn() {
      return this.on;
   }
}