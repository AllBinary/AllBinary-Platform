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
package org.allbinary.graphics.j2me.workarea.canvas.event;

public interface MyCanvasEventListener
{
   public void delete(MyCanvasEvent evt);
   public void duplicate(MyCanvasEvent evt)throws Exception;
   public void select(MyCanvasEvent evt);
   public void rotate(MyCanvasEvent evt);
   public void explode(MyCanvasEvent evt);
   public void autoExplode(MyCanvasEvent evt)throws Exception;
   public void center(MyCanvasEvent evt)throws Exception;
}
