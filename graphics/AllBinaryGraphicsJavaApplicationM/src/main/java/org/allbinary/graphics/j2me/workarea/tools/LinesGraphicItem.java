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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.allbinary.dom.DomHelper;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.j2me.StatusFactory;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasDom;
import org.allbinary.graphics.j2me.workarea.canvas.IntegerDimension;
import org.allbinary.graphics.pipeline.BasicGraphicsPipeline;
import org.allbinary.math.PositionStrings;
import java.awt.Color;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.util.BasicArrayList;

public class LinesGraphicItem implements GraphicItemInterface
{
    private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
   private DefaultMutableTreeNode treeNode;
   private Vector pointTreeNodeVector;

   private GPoint currentMousePoint;
   private GPoint fulcrumPoint;
   private boolean active = false;
   private double theta = 0;
   //Dom node element names
   
   private static final String RECT = "rect";
   private static final String ARC = "arc";
   private static final String STRING = "string";
   private static final String CHAR = "char";
   private static final String IMAGE = "image";
   private static int item = 0;
   //private int tempAngle = 0;
   private Color color = new Color(BasicColorFactory.getInstance().WHITE.intValue());

   private Points points;

   public LinesGraphicItem() //throws Exception
   {   
       this.points = new Points();
      try
      {
      init();
      this.active = true;
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
      }            
      
   }

   public LinesGraphicItem(Node linesNode) throws Exception
   {
       this.points = new Points();
      init();
      this.active = false;

      BasicArrayList lineNodes = DomHelper.getInstance().getWithoutTextNodes(linesNode.getChildNodes());

         int numberOfLines = lineNodes.size();

         for (int index = 0; index < numberOfLines; index++)
         {
            Node lineNode = (Node) lineNodes.get(index);

            BasicArrayList pointNodes = DomHelper.getInstance().getWithoutTextNodes(lineNode.getChildNodes());

            Node pointOneNode = DomHelper.getInstance().searchNodeList(
                    PointsDomUtil.getInstance().POINTONE, pointNodes);

            this.addPoint(pointOneNode.getChildNodes());

            if (index == numberOfLines - 1)
            {
               Node pointTwoNode = DomHelper.getInstance().searchNodeList(
                       PointsDomUtil.getInstance().POINTTWO, pointNodes);

               this.addPoint(pointTwoNode.getChildNodes());
            }
         }

      if(numberOfLines == 0)
      {
         throw new Exception("Lines node does not contain a line");
      }
   }

   public void init() throws Exception
   {
      this.treeNode = new DefaultMutableTreeNode(PointsDomUtil.getInstance().LINES + item);
      item++;
      this.points.init();
      this.pointTreeNodeVector = new Vector();
      this.fulcrumPoint = PointFactory.getInstance().getInstance(0, 0);
   }

   public void translate(int x, int y) throws Exception
   {
       StatusFactory.getInstance().setStatus("Translating: " + this.points.getPoints());
       
      BasicGraphicsPipeline basicGraphicsPipeline = 
              new BasicGraphicsPipeline(this.points.getPoints());
      basicGraphicsPipeline.translate(x, y);
      this.points = new Points();
      this.points.addPoints(basicGraphicsPipeline.getMatrix());
   }

   public void setRotate(double theta)
   {
      while (theta > 2 * Math.PI)
      {
         theta -= 2 * Math.PI;
      }
      while (theta < 0)
      {
         theta += 2 * Math.PI;
      }
      this.theta = theta;
   }

   public void addRotate(double theta)
   {
      this.setRotate(this.theta + theta);
   }

   public void setAngle(double angle)
   {
      this.setRotate(Math.toRadians(angle));
   }

   public void addAngle(double angle)
   {
      this.setRotate(this.theta + Math.toRadians(angle));
   }

   public MutableTreeNode getTreeNode()
   {
      return (MutableTreeNode) this.treeNode;
   }

   private static final String NAME = "Line Tool";

   public String getName()
   {
      return NAME;
   }

   public static String getStaticName()
   {
      return NAME;
   }

   private synchronized void addPoint(NodeList pointNodes) throws Exception
   {
       BasicArrayList list = DomHelper.getInstance().getWithoutTextNodes(pointNodes);

       Node xNode = DomHelper.getInstance().searchNodeList(PositionStrings.getInstance().X, list);
       Node xTextNode = xNode.getFirstChild();
       Integer xInteger = new Integer(xTextNode.getNodeValue());

       Node yNode = DomHelper.getInstance().searchNodeList(PositionStrings.getInstance().Y, list);
       Node yTextNode = yNode.getFirstChild();
       Integer yInteger = new Integer(yTextNode.getNodeValue());

       GPoint point = PointFactory.getInstance().getInstance(xInteger.intValue(), yInteger.intValue());

       this.addPoint(point);
   }

   public synchronized void addPoint(GPoint point)
   {
      this.points.getPoints().add(point);
      
      LogUtil.put(LogFactory.getInstance(point.toString(), this, "addPoint"));
      
      this.pointTreeNodeVector.add(new DefaultMutableTreeNode(point.toString()));
      this.treeNode.add((DefaultMutableTreeNode) this.pointTreeNodeVector.get(this.pointTreeNodeVector.size()-1));
   }

   public synchronized GPoint removePoint()
   {
      GPoint point = null;
      if (this.points.getSize() > 0)
      {
         int lastPoint = this.points.getSize() - 1;
         point = (GPoint) this.points.getPoints().remove(lastPoint);

         int index = this.pointTreeNodeVector.size() - 1;
         if (index > 0)
         {
            this.treeNode.remove((DefaultMutableTreeNode) this.pointTreeNodeVector.get(index));
            this.pointTreeNodeVector.remove(index);
         }
      }
      return point;
   }

   public boolean isActive()
   {
      return this.active;
   }

   public void deactivate()
   {
      this.active = false;
   }

   public void activate()
   {
      this.active = true;
   }

   public void duplicatePoints(BasicArrayList list)
           throws Exception
   {
      int size = list.size();
      for(int index = 0; index < size; index++)
      {
         GPoint point = (GPoint) list.get(index);
         if (point != null)
         {
            this.points.getPoints().add(PointFactory.getInstance().getInstance(point.getX(), point.getY()));
            this.pointTreeNodeVector.add(new DefaultMutableTreeNode(point.toString()));
            this.treeNode.add((DefaultMutableTreeNode) this.pointTreeNodeVector.get(this.pointTreeNodeVector.size()-1));
         }
      }
   }

   public GraphicItemInterface duplicate() throws Exception
   {
      LinesGraphicItem linesGraphicItem = new LinesGraphicItem();
      linesGraphicItem.duplicatePoints(this.points.getPoints());
      linesGraphicItem.deactivate();
      return linesGraphicItem;
   }

   public double getAngle()
   {
      return Math.toDegrees(this.theta);
   }

   public void setFulcrumPoint(GPoint point)
   {
      this.fulcrumPoint = point;
   }

   public void paint(Graphics g, Double canvasAngle, IntegerDimension dimension, int x, int y)
   {
      try
      {
         Graphics2D graphics = (Graphics2D) g;

         graphics.setColor(getColor());
         graphics.setStroke(new BasicStroke(x));

         if (this.isActive() && this.currentMousePoint != null)
         {
            this.points.getPoints().add(this.currentMousePoint);
         }

         BasicArrayList tempPointVector = PointsUtil.getInstance().doTransforms(
                 this.points.getPoints(),
                 canvasAngle, PointFactory.getInstance().getInstance(dimension.getWidth(),
                 dimension.getHeight()));

         if (this.isActive() && this.currentMousePoint != null)
         {
            this.currentMousePoint = (GPoint) this.points.getPoints().remove(this.points.getSize() - 1);
         }

         int size = tempPointVector.size();
         GPoint firstPoint = null;
         if (size > 0)
         {
            firstPoint = (GPoint) tempPointVector.get(0);
         }

         for(int index = 1; index < size; index++)
         {
            GPoint secondPoint = (GPoint) tempPointVector.get(index);
            graphics.drawLine((firstPoint.getX() * x) - (x / 4), (firstPoint.getY() * y) - (y / 4), (secondPoint.getX() * x) - (x / 4), (secondPoint.getY() * y) - (y / 4));
            firstPoint = secondPoint;
         }
         
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "mouseMoved", e));
      }
   }

   public Node toDom(CanvasDom canvasDom) throws Exception
   {
       return PointsDomUtil.getInstance().toDom(canvasDom, this.points.getPoints());
   }

   public boolean isValid()
   {
       return this.points.isValid();
   }

   public Points getPointsInterface()
   {
       return this.points;
   }

   public void setPointsInterface(Points points)
   {
       this.points = points;
   }

   public Color getColor()
   {
      return color;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }

   //event methods

   public void mouseClicked(java.awt.event.MouseEvent mouseEvent, int x, int y)
   {
      try
      {
         GPoint mousePoint = PointFactory.getInstance().getInstance(mouseEvent.getPoint().x, mouseEvent.getPoint().y);

         if ((mouseEvent.getModifiers() & mouseEvent.BUTTON1_MASK) == mouseEvent.BUTTON1_MASK)
         {
            GPoint point = PointFactory.getInstance().getInstance(mousePoint.getX() / x, mousePoint.getY() / y);
            StatusFactory.getInstance().setStatus("Line Point Added: " + point.toString());
            this.addPoint(point);
         } else if ((mouseEvent.getModifiers() & mouseEvent.BUTTON3_MASK) == mouseEvent.BUTTON3_MASK)
         {
            StatusFactory.getInstance().setStatus("Line Point Removed");
            this.removePoint();
         }
         this.currentMousePoint = mousePoint;
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "mouseMoved", e));
      }
   }

   public void mouseEntered(java.awt.event.MouseEvent mouseEvent)
   {
   }

   public void mouseExited(java.awt.event.MouseEvent mouseEvent)
   {
   }

   public void mousePressed(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
   {
   }

   public void mouseReleased(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
   {
   }

   public void mouseDragged(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
   {
   }

   public void mouseMoved(java.awt.event.MouseEvent mouseEvent, int x, int y)
   {
      try
      {
         GPoint mousePoint = PointFactory.getInstance().getInstance(mouseEvent.getPoint().x, mouseEvent.getPoint().y);
         GPoint point = PointFactory.getInstance().getInstance(mousePoint.getX() / x, mousePoint.getY() / y);
         this.currentMousePoint = point;
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "mouseMoved", e));
      }
   }

   public void keyPressed(java.awt.event.KeyEvent keyEvent)
   {
       try
       {
       int keyCode = keyEvent.getKeyCode();

      if (keyCode == keyEvent.VK_ESCAPE)
      {
         StatusFactory.getInstance().setStatus("Deactivated");
         this.deactivate();
      }
      else
          if (keyCode == keyEvent.VK_UP)
          {
              this.translate(0, -1);
          }
      else
          if (keyCode == keyEvent.VK_DOWN)
          {
              this.translate(0, 1);
          }
      else
          if (keyCode == keyEvent.VK_LEFT)
          {
              this.translate(-1, 0);
          }
      else
          if (keyCode == keyEvent.VK_RIGHT)
          {
              this.translate(1, 0);
          }
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, gameInputStrings.KEY_PRESSED, e));
      }
   }

   public void keyReleased(java.awt.event.KeyEvent keyEvent)
   {
   }

   public void keyTyped(java.awt.event.KeyEvent keyEvent)
   {
   }
}