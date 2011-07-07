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
package allbinary.graphics.paint;

public class NullPaintable extends Paintable
{
   private static final NullPaintable SINGLETON = new NullPaintable();
   
   private NullPaintable()
   {
      
   }
   
   public static Paintable getInstance()
   {
      return SINGLETON;
   }
}
