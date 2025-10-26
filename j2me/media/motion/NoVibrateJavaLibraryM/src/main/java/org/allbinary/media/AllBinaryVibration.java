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
package org.allbinary.media;

import org.allbinary.game.configuration.GameConfigurationCentral;

public class AllBinaryVibration extends AllBinaryVibrationME
{
    
   private static AllBinaryVibrationME VIBRATION = AllBinaryNoVibration.NO_VIBRATION;
   
   private AllBinaryVibration()
   {
   }
   
   public static AllBinaryVibrationME getInstance()
   {
      return VIBRATION;
   }
   
   public static void init()
   {
        if (GameConfigurationCentral.getInstance().VIBRATION.getValue().intValue() == 0)
        {
            VIBRATION = AllBinaryNoVibration.NO_VIBRATION;
        }
        else
        {
            VIBRATION = new AllBinaryVibration();
        }
   }
   
   @Override
   public void vibrate(int duration, int type, int volume)
   {

   }
}
