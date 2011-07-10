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
package allbinary.weapon.media.audio;

import allbinary.media.audio.CompositeSound;
import allbinary.media.audio.Sound;

public class DropWeaponSound extends CompositeSound
{
   private static Sound soundInterface = new DropWeaponSound();
   
   private DropWeaponSound()
   {
       super("resource:/wav/mine.wav");
   }
   
   public static Sound getInstance()
   {
      return soundInterface;
   }
}
