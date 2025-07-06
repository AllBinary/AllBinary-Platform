/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */
package org.allbinary.media.audio;

import org.allbinary.media.audio.CompositeSound;
import org.allbinary.media.audio.Sound;

public class SelectBuildingSound extends CompositeSound
{
   private static Sound soundInterface = new SelectBuildingSound();
   
   private SelectBuildingSound()
   {
       super("resource:/wav/select_building.wav");
   }
   
   public static Sound getInstance()
   {
      return soundInterface;
   }   
}
