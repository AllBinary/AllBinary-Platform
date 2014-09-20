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
package org.allbinary.graphics.j2me.workarea.tools.event;

import javax.swing.tree.MutableTreeNode;

public class MyGraphicItemEventSource
{
   private String command;
   private MutableTreeNode treeNode;
   private double angle = 0;
   
   public MyGraphicItemEventSource(String command, MutableTreeNode treeNode)
   {
      this.command = command;
      this.treeNode = treeNode;
   }

   public MyGraphicItemEventSource(String command, double angle, MutableTreeNode treeNode)
   {
      this.command = command;
      this.treeNode = treeNode;
      this.setAngle(angle);
   }
   
   public String getCommand()
   {
      return this.command;
   }
   
   public MutableTreeNode getTreeNode()
   {
      return this.treeNode;
   }
   
   public double getAngle()
   {      
      return this.angle;
   }

   public void setAngle(double angle)
   {
      this.angle = angle;
   }
}
