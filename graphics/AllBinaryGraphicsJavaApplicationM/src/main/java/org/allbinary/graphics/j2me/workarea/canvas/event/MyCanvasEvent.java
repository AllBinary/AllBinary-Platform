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

import javax.swing.tree.MutableTreeNode;

public class MyCanvasEvent extends java.util.EventObject
{      
   public MyCanvasEvent(Object source) 
   {
      super(source);
   }
   
   public Object getSource()
   {
      return super.getSource();
   }
   
   public String toString()
   {
      return super.toString();
   }
   
   public String getCommand()
   {      
      MyCanvasEventSource myEventSource = (MyCanvasEventSource) this.getSource();
      return myEventSource.getCommand();
   }
   
   public MutableTreeNode getTreeNode()
   {
      MyCanvasEventSource myEventSource = (MyCanvasEventSource) this.getSource();
      return myEventSource.getTreeNode();      
   }

   public double getAngle()
   {      
      MyCanvasEventSource myEventSource = (MyCanvasEventSource) this.getSource();
      return myEventSource.getAngle();
   }
}
