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
package allbinary.media.audio;

//Use 8hz16bitmono for most J2ME
public class GameBeginSound extends CompositeSound
{
   private static Sound soundInterface = new GameBeginSound();
   
   private GameBeginSound()
   {
       super("resource:/wav/begin.wav");
   }
   
   public static Sound getInstance()
   {
      return soundInterface;
   }
}
