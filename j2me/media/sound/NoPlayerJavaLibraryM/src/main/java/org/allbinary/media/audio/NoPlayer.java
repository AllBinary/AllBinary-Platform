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
package org.allbinary.media.audio;

import javax.microedition.media.Control;
import org.allbinary.logic.string.StringUtil;

public class NoPlayer extends BasicPlayer
{
   public static final NoPlayer NO_PLAYER = new NoPlayer();
   
   public NoPlayer()
   {
   }   
   
   @Override
   public void close()
   {
   }
   
   @Override
   public String getContentType()
   {
      return StringUtil.getInstance().NULL_STRING;
   }
   
   @Override
   public Control getControl(String controlType)
   {
      return new NullControl();
   }
   
   @Override
   public Control[] getControls()
   {
      return new Control[0];
   }
}
