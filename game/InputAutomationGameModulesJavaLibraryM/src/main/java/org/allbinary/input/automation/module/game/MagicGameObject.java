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
package org.allbinary.input.automation.module.game;

import java.awt.image.BufferedImage;
import org.allbinary.logic.string.CommonSeps;

public class MagicGameObject extends MeteredGameObject
      implements MagicGameObjectInterface
{
   private final String MP_LABEL = "MP:";
   
   public MagicGameObject(BufferedImage[] bufferedImage, Integer minX, Integer maxX, Integer y)
   throws Exception
   {
      super(bufferedImage, minX, maxX, y);
   }

   public String toString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append(CommonSeps.getInstance().NEW_LINE);
      stringBuffer.append(MP_LABEL);
      stringBuffer.append(super.toString());
      
      return stringBuffer.toString();
   }
   
}
