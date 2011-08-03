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

import javax.microedition.media.Control;

public class NoPlayer extends BasicPlayer
{
   
   public NoPlayer()
   {
   }   
   
   public void close()
   {
   }
   
   public String getContentType()
   {
      return null;
   }
   
   public Control getControl(String controlType)
   {
      return null;
   }
   
   public Control[] getControls()
   {
      return new Control[0];
   }
}
