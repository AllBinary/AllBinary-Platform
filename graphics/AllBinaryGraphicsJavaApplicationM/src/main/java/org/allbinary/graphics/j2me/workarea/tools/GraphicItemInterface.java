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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
   void mouseClicked(MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseEntered(MouseEvent mouseEvent);
   void mouseExited(MouseEvent mouseEvent);
   void mousePressed(MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseReleased(MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseDragged(MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void mouseMoved(MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell);
   void keyPressed(KeyEvent keyEvent);
   void keyReleased(KeyEvent keyEvent);   
   void keyTyped(KeyEvent keyEvent);
   
   GraphicItemInterface duplicate()throws Exception;
}
