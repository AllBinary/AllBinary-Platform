/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/29/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.media;

import allbinary.game.configuration.GameConfigurationCentral;

public class AllBinaryVibration extends AllBinaryVibrationME
{
   private static AllBinaryVibrationME VIBRATION;
   
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
            VIBRATION = new AllBinaryNoVibration();
        }
        else
        {
            VIBRATION = new AllBinaryVibration();
        }
   }
   
   public void vibrate(int duration, int type, int volume)
   {

   }
}
