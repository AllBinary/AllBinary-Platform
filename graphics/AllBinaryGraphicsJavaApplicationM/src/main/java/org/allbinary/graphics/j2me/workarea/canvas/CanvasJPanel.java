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
package org.allbinary.graphics.j2me.workarea.canvas;

import java.util.HashMap;

import java.util.Vector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.w3c.dom.Node;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.graphics.j2me.GraphicsException;
import org.allbinary.graphics.j2me.workarea.WorkAreaJTreeJPanel;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemFactory;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemInterface;
import org.allbinary.graphics.j2me.workarea.tools.LinesGraphicItem;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEvent;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEventListener;
import org.allbinary.graphics.j2me.workarea.tools.event.MyGraphicItemEventService;
import org.allbinary.logic.communication.log.GuiLog;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.vector.VectorCenterGenerator;
import org.allbinary.animation.VectorExplosionGenerator;
import org.allbinary.animation.VectorMirrorGenerator;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.j2me.StatusFactory;
import org.allbinary.graphics.j2me.workarea.tools.MouseStrings;
import org.allbinary.graphics.j2me.workarea.tools.Points;
import org.allbinary.graphics.j2me.workarea.tools.ToolFactory;
import org.allbinary.graphics.pipeline.RandomRotationFactory;
import org.allbinary.log.LOGGING;
import org.allbinary.math.PositionStrings;
import org.allbinary.util.BasicArrayList;

public class CanvasJPanel extends javax.swing.JPanel
        implements java.awt.event.MouseListener,
        java.awt.event.MouseMotionListener,
        java.awt.event.KeyListener,
        MyGraphicItemEventListener
{
    private static int frame = 0;
 
    private final GuiLog guiLog = GuiLog.getInstance();
    
    private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();

    private GraphicItemInterface selectedTool = null;
    private HashMap graphicItemHashMap;
    
    private IntegerDimension canvasDimension;
    private double angle = 0;
    private DefaultMutableTreeNode canvasTreeNode;
    private CanvasTreeLabel frameLabel;
    private WorkAreaJTreeJPanel workAreaJTreeJPanel;
    private static final Color gridColor = new Color(BasicColorFactory.getInstance().WHITE.intValue());
    private static final Color backgroundColor = new Color(BasicColorFactory.getInstance().CLEAR_COLOR.intValue());

    private final Grid grid;

    public CanvasJPanel(WorkAreaJTreeJPanel workAreaJTreeJPanel, Dimension dimension, int x, int y)
    {
        this.grid = new Grid();

        try
        {
            this.workAreaJTreeJPanel = workAreaJTreeJPanel;
            initComponents();
            initMyComponents();
            this.setSize(dimension);

            this.setCanvasSize(x, y);
        } catch (Exception e)
        {
            if (LOGGING.contains(LOGGING.GRAPHICSCREATION))
            {
                guiLog.put("Constructor Error", this, "contructor", e);
            }
        }
    }

    public CanvasJPanel(WorkAreaJTreeJPanel workAreaJTreeJPanel, Dimension dimension, CanvasDom canvasDom)
    {
        this.grid = new Grid(canvasDom.getGrid());
        try
        {
            this.workAreaJTreeJPanel = workAreaJTreeJPanel;
            initComponents();
            initMyComponents();
            this.setSize(dimension);

            this.setCanvasSize(canvasDom.getDimension().getWidth(), canvasDom.getDimension().getHeight());
            this.graphicItemHashMap = canvasDom.getGraphicItemHashMap();

            final Object[] graphicItemArray = graphicItemHashMap.keySet().toArray();
            final int size = graphicItemArray.length;
            for(int index = 0; index < size; index++)
            {
                MutableTreeNode graphicItemTreeNode = (MutableTreeNode) graphicItemArray[index];
                canvasTreeNode.add(graphicItemTreeNode);
            }

            this.repaint();

            this.setAngle(canvasDom.getAngle());
            this.workAreaJTreeJPanel.updateTree();
            this.workAreaJTreeJPanel.expand();
            this.workAreaJTreeJPanel.repaint();
        } catch (Exception e)
        {
            if (LOGGING.contains(LOGGING.GRAPHICSCREATION))
            {
                guiLog.put("Constructor Error", this, "contructor", e);
            }
        }
    }

    private void initMyComponents()
            throws Exception
    {
        this.frameLabel = new CanvasTreeLabel("FrameLabel" + new Integer(frame).toString());
        this.canvasTreeNode = new DefaultMutableTreeNode(this.frameLabel);
        frame++;

        this.workAreaJTreeJPanel.add(this.getTreeNode());

        this.graphicItemHashMap = new HashMap();
        this.grid.grid = PointFactory.getInstance().getInstance(0, 0);
        this.setCanvasDimension(new IntegerDimension(0, 0));
        this.selectedTool = null;

        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        MyGraphicItemEventService.addListener(this);
    }

    public MutableTreeNode getTreeNode()
    {
        return this.canvasTreeNode;
    }

   private void initComponents()//GEN-BEGIN:initComponents
   {

      setLayout(new java.awt.GridLayout(1, 1));

   }//GEN-END:initComponents

    public void setCanvasSize(int x, int y) throws Exception
    {
        this.grid.isGridPossible = true;

        if (x <= 0)
        {
            this.grid.isGridPossible = false;
            throw new GraphicsException("X Size Error: " + x, this, "setWorkAreaSize");
        }

        if (y <= 0)
        {
            this.grid.isGridPossible = false;
            throw new GraphicsException("Y Size Error: " + y, this, "setWorkAreaSize");
        }

        if (x > this.getWidth())
        {
            this.grid.isGridPossible = false;
        }

        if (y > this.getHeight())
        {
            this.grid.isGridPossible = false;
        }

        this.setCanvasDimension(new IntegerDimension(x, y));
        this.grid.isChanged = true;
    }

    public void setGrid(GPoint point)
    {
        this.setGrid(point.getX(), point.getY());
    }
    //xSize number of pixels between gridlines
    public void setGrid(int xSize, int ySize)
    {
        try
        {
            if (xSize >= getCanvasDimension().getWidth() / 2 || xSize < 1)
            {
                throw new GraphicsException("X Size Error: " + xSize + " WorkArea X: " + getCanvasDimension().getWidth(), this, "setGrid");
            }
            if (ySize >= getCanvasDimension().getHeight() / 2 || ySize < 1)
            {
                throw new GraphicsException("Y Size Error" + ySize + " WorkArea Y: " + getCanvasDimension().getHeight(), this, "setGrid");
            }

            this.grid.grid = PointFactory.getInstance().getInstance(xSize, ySize);
            this.grid.isChanged = true;
        } catch (Exception e)
        {
        }
    }

    public void setDefaultGrid() throws Exception
    {
        int xPixelsPerWorkAreaPixel = this.getWidth() / this.getCanvasDimension().getWidth();
        int yPixelsPerWorkAreaPixel = this.getHeight() / this.getCanvasDimension().getHeight();

        /*
        if(xPixelsPerWorkAreaPixel<9)
        {
        this.setGrid(6,6);
        }
        else
        if(xPixelsPerWorkAreaPixel<7)
        {
        this.setGrid(12,12);
        }
        else
        if(xPixelsPerWorkAreaPixel<5)
        {
        this.setGrid(24,24);
        }
        else
        if(xPixelsPerWorkAreaPixel<3)
        {
        this.setGrid(48,48);
        }
        else
        {
        this.setGrid(1,1);
        }*/
        this.setGrid(1, 1);
        this.grid.isChanged = true;
    }

    public double getAngle()
    {
        return this.angle;
    }

    public void setAngle(double angle)
    {
        //while(angle > 360) angle -= 360;
        //while(angle < 0) angle += 360;
        this.angle = angle;
    }

    public void addAngle(double angle)
    {
        this.setAngle(this.angle + angle);
    }

    //This should be a tool
    public void explodeAll()
    {
        Vector newPoints = new Vector();
        
        final Object[] graphicItemArray = this.getGraphicItemHashMap().keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            MutableTreeNode graphicItemNode = (MutableTreeNode) graphicItemArray[index];

            GraphicItemInterface graphicItem = (GraphicItemInterface)
                    this.getGraphicItemHashMap().get(graphicItemNode);

            if (graphicItem.getName() == LinesGraphicItem.getStaticName())
            {
                while (graphicItem.getPointsInterface().getSize() > 2)
                {
                    GraphicItemInterface newGraphicItem =
                            GraphicItemFactory.getInstance().getInstance(
                            graphicItem.getName()).getInstance(this);

                    GPoint pointOne = new GPoint(graphicItem.removePoint());
                    GPoint pointTwo = new GPoint(graphicItem.removePoint());
                    //Point pointOne = new Point((Point) points.remove(points.size()-1));
                    //Point pointTwo = new Point((Point) points.remove(points.size()-1));
                    if (pointOne != null && pointTwo != null)
                    {
                        newGraphicItem.addPoint(pointOne);
                        newGraphicItem.addPoint(pointTwo);
                    }
                    newPoints.add(newGraphicItem);
                }
            }
        }

        final int size2 = newPoints.size();
        for (int index = 0; index < size2; index++)
        {
            GraphicItemInterface newGraphicItem = (GraphicItemInterface) newPoints.get(index);
            canvasTreeNode.add(newGraphicItem.getTreeNode());
            this.getGraphicItemHashMap().put(newGraphicItem.getTreeNode(), newGraphicItem);
        }

        this.workAreaJTreeJPanel.updateTree();
        this.workAreaJTreeJPanel.expand();
        this.workAreaJTreeJPanel.repaint();
    }

    public void explode(int howMuch, int explosionType)
            throws Exception
    {
        //move and rotate each line a random amount
        final Object[] graphicItemArray = this.getGraphicItemHashMap().keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) this.getGraphicItemHashMap().get(graphicItemArray[index]);
            BasicArrayList basicArrayList = VectorExplosionGenerator.getInstance().getInstance(item.getPointsInterface().getPoints(), howMuch, VectorExplosionGenerator.getInstance().RANDOM);

            Points newPoints = new Points();

            newPoints.addPoints(basicArrayList);

            item.setPointsInterface(newPoints);

            int angleDelta = RandomRotationFactory.getInstance().getNextRandomAngle(howMuch);
            //double angle = item.getAngle();
            //angle +
            item.setAngle(angleDelta);
        }

        this.repaint();
    }

    public void mirror()
            throws Exception
    {
        int width = this.getCanvasDimension().getWidth();
        //move and rotate each line a random amount
        final Object[] graphicItemArray = this.getGraphicItemHashMap().keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) this.getGraphicItemHashMap().get(graphicItemArray[index]);
            BasicArrayList basicArrayList = VectorMirrorGenerator.getInstance().getInstance(item.getPointsInterface().getPoints(), width);

            Points newPoints = new Points();

            newPoints.addPoints(basicArrayList);

            item.setPointsInterface(newPoints);
        }

        this.repaint();
    }

    public void center()
            throws Exception
    {
        new VectorCenterGenerator().transform(this.getGraphicItemHashMap());

        this.repaint();
    }

    //add a new graphic item from the currently selected graphic tool
    public void setNewTool()
    {
        try
        {
            StatusFactory.getInstance().setStatus("Tool Selected");
            //assign new tool
            this.selectedTool = ToolFactory.getInstance().getSelectedToolFactory().getInstance(this);

            //move new graphic item to list
            MutableTreeNode newNode = this.selectedTool.getTreeNode();
            this.getGraphicItemHashMap().put(newNode, this.selectedTool);
            this.canvasTreeNode.add(newNode);

            //add mouse click
            if (this.getSelectedTool() == null)
            {
                throw new GraphicsException("No Tool Selected", this, "setSelectedTool");
            }
        } catch (Exception e)
        {
        }
    }

    public void duplicateGraphicItem(GraphicItemInterface graphicItem)
            throws Exception
    {
        GraphicItemInterface graphicItemClone = graphicItem.duplicate();
        this.getGraphicItemHashMap().put(graphicItemClone.getTreeNode(), graphicItemClone);
        canvasTreeNode.add(graphicItemClone.getTreeNode());
    }

    public void duplicateGraphicItemHashMap(HashMap hashMap)
            throws Exception
    {

        final Object[] mutableTreeNodeArray = hashMap.keySet().toArray();
        final int size = mutableTreeNodeArray.length;
        for(int index = 0; index < size; index++)
        {
            MutableTreeNode treeNode = (MutableTreeNode) mutableTreeNodeArray[index];
            GraphicItemInterface graphicItem = (GraphicItemInterface) hashMap.get(treeNode);
            this.duplicateGraphicItem(graphicItem);
        }
    }

    public HashMap getGraphicItemHashMap()
    {
        return this.graphicItemHashMap;
    }

    public int getXPixelsPerWorkAreaPixel()
    {
        //int xWorkArea = ;
        int x = this.getGrid().grid.getX() * (this.getWidth() - 10) / this.getCanvasDimension().getWidth();
        if (this.getGrid().getZoom() == 1)
        {
            return 1;
        } else
        {
            return x;
        }
    }

    public int getYPixelsPerWorkAreaPixel()
    {
        int y = this.getGrid().grid.getY() * (this.getHeight() - 10) / this.getCanvasDimension().getHeight();
        if (this.getGrid().getZoom() == 1)
        {
            return 1;
        } else
        {
            return y;
        }
    }

    public GraphicItemInterface getSelectedTool()
    {
        return this.selectedTool;
    }

    public void drawItems(Graphics graphics)
    {
        Double angleDouble = new Double(this.angle);

        //graphics.drawString(new Integer(this.graphicItemHashMap.keySet().size()).toString(),5,5);
        final Object[] graphicItemArray = this.getGraphicItemHashMap().keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) this.getGraphicItemHashMap().get(graphicItemArray[index]);
            item.paint(graphics, angleDouble, this.getCanvasDimension(), this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        }
    }

    public void drawGrid(Graphics graphics)
    {
        int xAdjust = (this.getWidth() % this.getXPixelsPerWorkAreaPixel()) / 2;
        int yAdjust = (this.getHeight() % this.getYPixelsPerWorkAreaPixel()) / 2;

        int xLeft = xAdjust;
        int yUpper = yAdjust;

        int xRight = this.getWidth() - xAdjust;
        int yLower = this.getHeight() - yAdjust;

        graphics.setColor(gridColor);

        for (int value = yAdjust; value <= this.getHeight() - yAdjust; value += this.getYPixelsPerWorkAreaPixel())
        {
            graphics.drawLine(xLeft, value, xRight, value);
        }

        for (int value = xAdjust; value <= this.getWidth() - xAdjust; value += this.getYPixelsPerWorkAreaPixel())
        {
            graphics.drawLine(value, yUpper, value, yLower);
        }
    }

    public void paint(Graphics graphics)
    {
        try
        {
            if (this.getGrid().isChanged)
            {
                graphics.setColor(backgroundColor);
                graphics.fillRect(0, 0, getWidth(), getHeight());

                if (this.getGrid().getZoom() > 2 && this.getGrid().isGridOn && this.getGrid().isGridPossible)
                {
                    this.setDefaultGrid();
                    this.drawGrid(graphics);
                }

                //draw items
                this.drawItems(graphics);
                graphics.drawString(new Double(this.getAngle()).toString(), this.getWidth() - 75, this.getHeight() - 15);

                this.grid.isChanged = false;
            }
        } catch (Exception e)
        {
        }
    }

    public void repaint()
    {
        if(this.grid != null)
        {
            this.grid.isChanged = true;
        }
        
        super.repaint();
    }

    public Node toDom() throws Exception
    {
        return new CanvasDom(this).toDom();
    }

    public void changed()
    {
        this.grid.isChanged = true;
    }

    public void mouseClicked(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(MouseStrings.getInstance().MOUSE_CLICKED_LABEL + this.logMouseEvent(mouseEvent));

        if (this.getSelectedTool() != null && this.getSelectedTool().isActive())
        {
            GraphicItemInterface graphicItem = this.getSelectedTool();
            //add new point to graphic item if required
            graphicItem.mouseClicked(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        } else
        {
            this.setNewTool();
            //start using a new graphic item immediately
            GraphicItemInterface graphicItem = this.getSelectedTool();
            //add new point to graphic item if required
            if (graphicItem != null)
            {
                graphicItem.mouseClicked(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
            }
        }

        this.grid.isChanged = true;
        this.repaint();

        this.workAreaJTreeJPanel.updateTree();
        this.workAreaJTreeJPanel.expand();
        this.workAreaJTreeJPanel.repaint();
    }

    public void mouseEntered(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(this.logMouseEvent(mouseEvent));
    }

    public void mouseExited(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(this.logMouseEvent(mouseEvent));
    }

    public void mousePressed(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(MouseStrings.getInstance().MOUSE_PRESSED_LABEL + this.logMouseEvent(mouseEvent));

         //&& this.getSelectedTool().isActive()
        if (this.getSelectedTool() != null)
        {
            GraphicItemInterface graphicItem = this.getSelectedTool();
            graphicItem.mousePressed(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        }

        this.requestFocus();
        this.grid.isChanged = true;
        this.repaint();

    }

    public void mouseReleased(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(MouseStrings.getInstance().MOUSE_RELEASED_LABEL + this.logMouseEvent(mouseEvent));

        GraphicItemInterface graphicItem = this.getSelectedTool();

         //&& this.getSelectedTool().isActive()
        if (graphicItem != null)
        {
            graphicItem.mouseReleased(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        }

        this.requestFocus();
        this.grid.isChanged = true;
        this.repaint();
    }

    public void mouseDragged(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(MouseStrings.getInstance().MOUSE_DRAGGED_LABEL + this.logMouseEvent(mouseEvent));

        GraphicItemInterface graphicItem = this.getSelectedTool();

         //&& this.getSelectedTool().isActive()
        if (graphicItem != null)
        {
            graphicItem.mouseDragged(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        }

        this.requestFocus();
        this.grid.isChanged = true;
        this.repaint();
    }

    public void mouseMoved(java.awt.event.MouseEvent mouseEvent)
    {
        StatusFactory.getInstance().setStatusNoLog(MouseStrings.getInstance().MOUSE_MOVED_LABEL + this.logMouseEvent(mouseEvent));

        if (this.getSelectedTool() != null && this.getSelectedTool().isActive())
        {
            GraphicItemInterface graphicItem = this.getSelectedTool();
            graphicItem.mouseMoved(mouseEvent, this.getXPixelsPerWorkAreaPixel(), this.getYPixelsPerWorkAreaPixel());
        }

        this.requestFocus();
        this.grid.isChanged = true;
        this.repaint();
    }

    private String logMouseEvent(java.awt.event.MouseEvent mouseEvent)
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(mouseEvent.getX() / this.getXPixelsPerWorkAreaPixel());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(mouseEvent.getY() / this.getYPixelsPerWorkAreaPixel());

        return stringBuffer.toString();
    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent)
    {
        StatusFactory.getInstance().setStatus("Key Pressed");

        try
        {
            final Object[] graphicItemArray = this.graphicItemHashMap.keySet().toArray();
            final int size = graphicItemArray.length;
            for(int index = 0; index < size; index++)
            {
                GraphicItemInterface graphicItemInterface = (GraphicItemInterface) this.graphicItemHashMap.get(graphicItemArray[index]);

                int keyCode = keyEvent.getKeyCode();

                if(graphicItemInterface.isActive())
                {
                if (keyCode == keyEvent.VK_UP)
                {
                    graphicItemInterface.translate(0, -1);
                } else if (keyCode == keyEvent.VK_DOWN)
                {
                    graphicItemInterface.translate(0, 1);
                } else if (keyCode == keyEvent.VK_LEFT)
                {
                    graphicItemInterface.translate(-1, 0);
                } else if (keyCode == keyEvent.VK_RIGHT)
                {
                    graphicItemInterface.translate(1, 0);
                }
                }
            }
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, gameInputStrings.KEY_PRESSED, e));
        }

        if (this.getSelectedTool() != null && this.getSelectedTool().isActive())
        {
            StatusFactory.getInstance().setStatus("Key Pressed for Tool");
            GraphicItemInterface graphicItem = this.getSelectedTool();
            graphicItem.keyPressed(keyEvent);
        } else
        {
            this.setNewTool();
        }

        this.grid.isChanged = true;
        this.repaint();
    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void keyTyped(java.awt.event.KeyEvent keyEvent)
    {
    }

    public void delete(MyGraphicItemEvent evt)
    {
        this.getGraphicItemHashMap().remove(evt.getTreeNode());
        this.workAreaJTreeJPanel.updateTree();
        this.workAreaJTreeJPanel.expand();
        this.workAreaJTreeJPanel.repaint();
    }

    public void deselect(MyGraphicItemEvent evt)
    {
        //LogUtil.put("Deselect", this, "deselect");
        GraphicItemInterface graphicItemInterface = (GraphicItemInterface) this.getGraphicItemHashMap().get(evt.getTreeNode());

        if (graphicItemInterface != null)
        {
            graphicItemInterface.setColor(Color.WHITE);
            graphicItemInterface.deactivate();
            this.workAreaJTreeJPanel.repaint();
        }
    }

    public void highlight(MyGraphicItemEvent evt)
    {
        GraphicItemInterface graphicItemInterface =
                (GraphicItemInterface) this.getGraphicItemHashMap().get(evt.getTreeNode());

        if (graphicItemInterface != null)
        {
            graphicItemInterface.setColor(Color.YELLOW);
            graphicItemInterface.activate();
            this.workAreaJTreeJPanel.repaint();
        }
    }

    public CanvasJPanel duplicate()
            throws Exception
    {
        CanvasJPanel newCanvasJPanel = new CanvasJPanel(this.workAreaJTreeJPanel, this.getSize(), this.getCanvasDimension().getWidth(), this.getCanvasDimension().getHeight());
        newCanvasJPanel.setGrid(this.getGrid().getGrid());
        newCanvasJPanel.setAngle(this.getAngle());
        newCanvasJPanel.duplicateGraphicItemHashMap(this.getGraphicItemHashMap());

        this.workAreaJTreeJPanel.updateTree();
        this.workAreaJTreeJPanel.expand();
        this.workAreaJTreeJPanel.repaint();

        return newCanvasJPanel;
    }

    public void duplicate(MyGraphicItemEvent event)
            throws Exception
    {
        MutableTreeNode treeNode = event.getTreeNode();
        GraphicItemInterface graphicItem = (GraphicItemInterface) this.getGraphicItemHashMap().get(treeNode);

        if (graphicItem != null)
        {
            this.duplicateGraphicItem(graphicItem);

            this.workAreaJTreeJPanel.updateTree();
            this.workAreaJTreeJPanel.expand();
            this.workAreaJTreeJPanel.repaint();
        }
    }

    public void rotate(MyGraphicItemEvent event)
    {
        MutableTreeNode treeNode = event.getTreeNode();
        GraphicItemInterface graphicItem = (GraphicItemInterface) this.getGraphicItemHashMap().get(treeNode);

        if (graphicItem != null)
        {
            graphicItem.addAngle(event.getAngle());
        }
    }

    public IntegerDimension getCanvasDimension()
    {
        return canvasDimension;
    }

    public void setCanvasDimension(IntegerDimension canvasDimension)
    {
        this.canvasDimension = canvasDimension;
    }

    /**
     * @return the grid
     */
    public Grid getGrid()
    {
        return grid;
    }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   // End of variables declaration//GEN-END:variables
}
