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
package org.allbinary.graphics.j2me.workarea.tools;

import java.awt.*;

import javax.swing.tree.MutableTreeNode;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasDom;
import org.allbinary.graphics.j2me.workarea.canvas.IntegerDimension;
import org.w3c.dom.Node;

public interface GraphicItemInterface
{
    String getName();
    
   void setColor(Color color);
   
   MutableTreeNode getTreeNode();

   Points getPointsInterface();
   void setPointsInterface(Points points);
   
   //int getSize();
   
   boolean isValid();
   
   boolean isActive();
   void deactivate();   
   void activate();
   
   void setFulcrumPoint(GPoint point);   
   void translate(int x, int y)throws Exception;
   
   void setRotate(double theta);
   void addRotate(double theta);
   
   void setAngle(double angle);
   void addAngle(double angle);
   double getAngle();
   
   void paint(Graphics g,Double canvasAngle, IntegerDimension dimension,int x,int y);
         
   Node toDom(CanvasDom canvasDom) throws Exception;

   GPoint removePoint();
   void addPoint(GPoint point);

   //mouse and key events occur on the component the tool 
   //is used and not by the tool itself
   void mouseClicked(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseEntered(java.awt.event.MouseEvent mouseEvent);
   void mouseExited(java.awt.event.MouseEvent mouseEvent);
   void mousePressed(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseReleased(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseDragged(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseMoved(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void keyPressed(java.awt.event.KeyEvent keyEvent);
   void keyReleased(java.awt.event.KeyEvent keyEvent);   
   void keyTyped(java.awt.event.KeyEvent keyEvent);
   
   GraphicItemInterface duplicate()throws Exception;
}
