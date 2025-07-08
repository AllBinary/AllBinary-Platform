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
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasDom;
import org.allbinary.graphics.j2me.workarea.canvas.CanvasJPanel;
import org.allbinary.graphics.j2me.workarea.canvas.IntegerDimension;
import org.allbinary.math.RectangleCollisionPointUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.tree.MutableTreeNode;
import org.w3c.dom.Node;

/**
 *
 * @author user
 */
public class SelectionTool implements GraphicItemInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final CanvasJPanel canvasJPanel;
    private boolean active = true;

    public SelectionTool(CanvasJPanel canvasJPanel)
    {
        this.canvasJPanel = canvasJPanel;
    }

    public void setColor(Color color)
    {
    }

    public MutableTreeNode getTreeNode()
    {
        return null;
    }

    public Points getPointsInterface()
    {
        return null;
    }

    public void setPointsInterface(Points points)
    {
    }

    //int getSize();
    public boolean isValid()
    {
        return true;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void deactivate()
    {
    }

    public void activate()
    {
    }

    public void setFulcrumPoint(GPoint point)
    {
    }

    public void translate(int x, int y) throws Exception
    {
    }

    public void setRotate(double theta)
    {
    }

    public void addRotate(double theta)
    {
    }

    public void setAngle(double angle)
    {
    }

    public void addAngle(double angle)
    {
    }

    public double getAngle()
    {
        return 0;
    }

    private boolean controlIdDown;

    private void makeSelection()
    {
        int xRect = this.getXRect();
        int xRect2 = Math.abs(this.endPoint.getX() - this.startPoint.getX()) + xRect;

        int yRect = this.getYRect();
        int yRect2 = Math.abs(this.endPoint.getY() - this.startPoint.getY()) + yRect;

        HashMap hashMap = this.canvasJPanel.getGraphicItemHashMap();

        final Object[] graphicItemArray = hashMap.keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) hashMap.get(graphicItemArray[index]);

            if (item.getName() == LinesGraphicItem.getStaticName())
            {
                if (RectangleCollisionPointUtil.allPointsInside(
                        xRect, yRect, xRect2, yRect2,
                        item.getPointsInterface().getPoints(),
                        this.canvasJPanel.getXPixelsPerWorkAreaPixel(),
                        this.canvasJPanel.getYPixelsPerWorkAreaPixel()))
                {
                    item.setColor(Color.YELLOW);
                    item.activate();
                }
                else
                    if(!this.controlIdDown)
                {
                    item.setColor(Color.WHITE);
                    item.deactivate();
                }
            }
        }
    }
    private final Color rectColor = new Color(BasicColorFactory.getInstance().RED.intValue());

    public int getXRect()
    {
        int xDiff = this.endPoint.getX() - this.startPoint.getX();

        int xRect = this.startPoint.getX();
        if (xDiff < 0)
        {
            xRect = this.endPoint.getX();
        }
        return xRect;
    }

    public int getYRect()
    {
        int yDiff = this.endPoint.getY() - this.startPoint.getY();

        int yRect = this.startPoint.getY();
        if (yDiff < 0)
        {
            yRect = this.endPoint.getY();
        }
        return yRect;
    }

    public void paint(Graphics g, Double canvasAngle, IntegerDimension dimension, int x, int y)
    {
        //logUtil.put(commonStrings.START, this, canvasStrings.PAINT);

        g.setColor(this.rectColor);

        int xRect = this.getXRect();
        int width = Math.abs(this.endPoint.getX() - this.startPoint.getX());

        int yRect = this.getYRect();
        int height = Math.abs(this.endPoint.getY() - this.startPoint.getY());

        g.drawRect(xRect, yRect, width, height);
    }

    public Node toDom(CanvasDom canvasDom) throws Exception
    {
        return null;
    }

    public GPoint removePoint()
    {
        return null;
    }

    public void addPoint(GPoint point)
    {
    }
    private static final String NAME = "Selection Tool";

    public String getName()
    {
        return NAME;
    }

    public static String getStaticName()
    {
        return NAME;
    }
    private GPoint startPoint = PointFactory.getInstance().ZERO_ZERO;
    private GPoint endPoint = startPoint;

    //mouse and key events occur on the component the tool
    //is used and not by the tool itself
    public void mouseClicked(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
    {
        //logUtil.put(commonStrings.START, this, MOUSE_CLICKED);
        //this.startPoint  = PointFactory.getInstance(mouseEvent.getX(), mouseEvent.getY());
        //this.endPoint = this.startPoint;
    }

    public void mouseMoved(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
    {
        //logUtil.put(commonStrings.START, this, MOUSE_MOVED);
        //this.endPoint = PointFactory.getInstance(mouseEvent.getX(), mouseEvent.getY());
    }

    public void mouseEntered(java.awt.event.MouseEvent mouseEvent)
    {
    }

    public void mouseExited(java.awt.event.MouseEvent mouseEvent)
    {
    }

    public void mousePressed(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
    {
        logUtil.put(commonStrings.START, this, MouseStrings.getInstance().MOUSE_PRESSED);

        this.startPoint = PointFactory.getInstance().getInstance(mouseEvent.getX(), mouseEvent.getY());
        this.endPoint = this.startPoint;
    }

    public void mouseReleased(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
    {
        logUtil.put(commonStrings.START, this, MouseStrings.getInstance().MOUSE_RELEASED);

        this.endPoint = PointFactory.getInstance().getInstance(mouseEvent.getX(), mouseEvent.getY());
        this.active = false;
        this.makeSelection();
    }

    public void mouseDragged(java.awt.event.MouseEvent mouseEvent, int xPixelsPerCell, int yPixelsPerCell)
    {
        //logUtil.put(commonStrings.START, this, MouseStrings.getInstance().MOUSE_DRAGGED);

        this.endPoint = PointFactory.getInstance().getInstance(mouseEvent.getX(), mouseEvent.getY());
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
        if(keyEvent.getKeyCode() == keyEvent.VK_CONTROL)
        {
            this.controlIdDown = true;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
        if(keyEvent.getKeyCode() == keyEvent.VK_CONTROL)
        {
            this.controlIdDown = false;
        }
    }

    public void keyTyped(java.awt.event.KeyEvent keyEvent)
    {
    }

    public GraphicItemInterface duplicate() throws Exception
    {
        return null;
    }
}
