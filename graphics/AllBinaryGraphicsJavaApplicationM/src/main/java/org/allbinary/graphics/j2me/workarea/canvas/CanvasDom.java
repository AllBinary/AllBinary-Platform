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

import org.allbinary.animation.vector.VectorCenterGenerator;
import org.allbinary.dom.DomHelper;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.j2me.workarea.WorkAreaJPanel;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemFactory;
import org.allbinary.graphics.j2me.workarea.tools.GraphicItemInterface;
import org.allbinary.graphics.j2me.workarea.tools.LinesGraphicItem;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Node;

public class CanvasDom
{
    //Dom Nodes

    public static final String FRAME = "frame";
    public static final String ROTATE = "rotate";
    public static final String SIZE = "size";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String ZOOM = "zoom";
    public static final String GRID = "grid";
    //size
    //x
    //y
    public static final String ENABLED = "enabled";
    public static final String POSSIBLE = "possible";
    public static final String GRAPHICITEMS = "graphicItems";
    public static final String REAL_SIZE = "realSize";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    // public static final String WORKAREA = "workArea";
    private HashMap graphicItemHashMap;
    private double angle;
    private IntegerDimension dimension;
    private final Grid grid;

//   private int xPixelsPerWorkArea;
//   private int yPixelsPerWorkArea;
    //private String frameName;
    //Node nameNode = DomHelper.getInstance()searchNodeList(this.NAME,workAreaNodeList);
    //For loading a canvas from a file
    public CanvasDom(Node canvasNode) throws Exception
    {
        this.grid = new Grid();

        this.grid.grid = PointFactory.getInstance().getInstance(0, 0);
        this.dimension = new IntegerDimension(0, 0);

        String name = canvasNode.getNodeName();
        if (name.compareTo(FRAME) == 0)
        {
            this.graphicItemHashMap = new HashMap();

            Node angleNode = DomHelper.getInstance().searchNodeList(this.ROTATE, canvasNode.getChildNodes());
            Node angleTextNode = angleNode.getFirstChild();
            double angle = new Double(angleTextNode.getNodeValue()).doubleValue();
            this.setAngle(angle);

            Node sizeNode = DomHelper.getInstance().searchNodeList(this.SIZE, canvasNode.getChildNodes());

            Node xNode = DomHelper.getInstance().searchNodeList(this.X, sizeNode.getChildNodes());
            Node xTextNode = xNode.getFirstChild();
            int x = new Integer(xTextNode.getNodeValue()).intValue();

            Node yNode = DomHelper.getInstance().searchNodeList(this.Y, sizeNode.getChildNodes());
            Node yTextNode = yNode.getFirstChild();
            int y = new Integer(yTextNode.getNodeValue()).intValue();
            this.setWorkArea(x, y);

            Node zoomNode = DomHelper.getInstance().searchNodeList(this.ZOOM, canvasNode.getChildNodes());
            Node zoomTextNode = zoomNode.getFirstChild();
            this.grid.setZoom(new Integer(zoomTextNode.getNodeValue()).intValue());

            Node gridNode = DomHelper.getInstance().searchNodeList(this.GRID, canvasNode.getChildNodes());
            Node gridSizeNode = DomHelper.getInstance().searchNodeList(this.SIZE, gridNode.getChildNodes());

            Node gridXNode = DomHelper.getInstance().searchNodeList(this.X, gridSizeNode.getChildNodes());
            Node gridXTextNode = gridXNode.getFirstChild();
            int gridX = new Integer(gridXTextNode.getNodeValue()).intValue();

            Node gridYNode = DomHelper.getInstance().searchNodeList(this.Y, gridSizeNode.getChildNodes());
            Node gridYTextNode = gridYNode.getFirstChild();
            int gridY = new Integer(gridYTextNode.getNodeValue()).intValue();
            this.setGrid(gridX, gridY);

            Node enableNode = DomHelper.getInstance().searchNodeList(this.ENABLED, gridNode.getChildNodes());
            Node enableTextNode = enableNode.getFirstChild();
            this.grid.showGrid(new Boolean(enableTextNode.getNodeValue()).booleanValue());

            Node possibleNode = DomHelper.getInstance().searchNodeList(this.POSSIBLE, gridNode.getChildNodes());
            Node possibleTextNode = possibleNode.getFirstChild();
            this.grid.isGridPossible = new Boolean(possibleTextNode.getNodeValue()).booleanValue();

            BasicArrayList graphicItemNodeList = DomHelper.getInstance().getChildrenWithoutTextNodes(this.GRAPHICITEMS, canvasNode.getChildNodes());
            if (graphicItemNodeList != null)
            {
                this.graphicItemHashMap = GraphicItemFactory.getInstance().getInstance(graphicItemNodeList);
            }
        } else
        {
            throw new Exception("Frame Element Not Found but Found: " + name);
        }
    }

    //for exporting or saving a canvas to a file
    public CanvasDom(CanvasJPanel canvasJPanel) throws Exception
    {
        this.angle = canvasJPanel.getAngle();

        this.graphicItemHashMap = canvasJPanel.getGraphicItemHashMap();
        this.dimension = canvasJPanel.getCanvasDimension();

        this.grid = new Grid(canvasJPanel.getGrid());

        this.angle = canvasJPanel.getAngle();
        //   this.xPixelsPerWorkArea = canvasJPanel.getXPixelsPerWorkAreaPixel();
//      this.yPixelsPerWorkArea = canvasJPanel.getYPixelsPerWorkAreaPixel();
    }

    /*
    public int getXPixelsPerWorkAreaPixel()
    {
    return this.xPixelsPerWorkArea;
    }

    public int getYPixelsPerWorkAreaPixel()
    {
    return this.yPixelsPerWorkArea;
    }
     */
    public double getAngle()
    {
        return this.angle;
    }

    public HashMap getGraphicItemHashMap()
    {
        return this.graphicItemHashMap;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public void setWorkArea(int x, int y)
            throws Exception
    {
        this.setDimension(new IntegerDimension(x, y));
    }

    public void setGrid(int gridX, int gridY)
            throws Exception
    {
        this.grid.grid = PointFactory.getInstance().getInstance(gridX, gridY);
    }

    private Node getPointNode(GPoint point, String pointName)
    {
        org.w3c.dom.Document document = WorkAreaJPanel.getDocument();

        Node pointNode = (Node) document.createElement(pointName);

        Node xNode = (Node) document.createElement(this.X);
        Node xTextNode = (Node) document.createTextNode(Integer.toString(point.getX()));
        xNode.appendChild(xTextNode);

        Node yNode = (Node) document.createElement(this.Y);
        Node yTextNode = (Node) document.createTextNode(Integer.toString(point.getY()));
        yNode.appendChild(yTextNode);

        pointNode.appendChild(xNode);
        pointNode.appendChild(yNode);

        return pointNode;
    }

    //used for saving and exporting a canvas in a workarea
    public Node toDom() throws Exception
    {
        org.w3c.dom.Document document = WorkAreaJPanel.getDocument();
        Node frameNode = (Node) document.createElement(this.FRAME);

        Node angleNode = (Node) document.createElement(this.ROTATE);
        Node angleTextNode = (Node) document.createTextNode(new Double(this.getAngle()).toString());
        angleNode.appendChild(angleTextNode);

        Node sizeNode = (Node) this.getPointNode(PointFactory.getInstance().getInstance(this.getDimension().getWidth(), this.getDimension().getHeight()), this.SIZE);

        Node zoomNode = (Node) document.createElement(this.ZOOM);
        Node zoomTextNode = (Node) document.createTextNode(new Integer(this.getGrid().getZoom()).toString());
        zoomNode.appendChild(zoomTextNode);

        Node gridNode = (Node) document.createElement(this.GRID);

        Node gridSizeNode = (Node) this.getPointNode(this.getGrid().grid, this.SIZE);
        gridNode.appendChild(gridSizeNode);

        Node enableNode = (Node) document.createElement(this.ENABLED);
        Node enableTextNode = (Node) document.createTextNode(new Boolean(this.getGrid().isGridOn()).toString());
        enableNode.appendChild(enableTextNode);
        gridNode.appendChild(enableNode);

        Node possibleNode = (Node) document.createElement(this.POSSIBLE);
        Node possibleTextNode = (Node) document.createTextNode(new Boolean(this.getGrid().isGridPossible()).toString());
        possibleNode.appendChild(possibleTextNode);
        gridNode.appendChild(possibleNode);

        Node realSizeNode = (Node) document.createElement(REAL_SIZE);

        VectorCenterGenerator vectorCenterGenerator = new VectorCenterGenerator();
        vectorCenterGenerator.calculate(this.getGraphicItemHashMap());

        Node widthNode = (Node) document.createElement(WIDTH);
        Node widthTextNode = (Node) document.createTextNode(
                Integer.toString(vectorCenterGenerator.getWidth()));
        widthNode.appendChild(widthTextNode);
        realSizeNode.appendChild(widthNode);

        Node heightNode = (Node) document.createElement(HEIGHT);
        Node heightTextNode = (Node) document.createTextNode(
                Integer.toString(vectorCenterGenerator.getHeight()));
        heightNode.appendChild(heightTextNode);
        realSizeNode.appendChild(heightNode);

        frameNode.appendChild(angleNode);
        frameNode.appendChild(sizeNode);
        frameNode.appendChild(zoomNode);
        frameNode.appendChild(gridNode);
        frameNode.appendChild(realSizeNode);

        Node graphicItemNode = (Node) document.createElement(this.GRAPHICITEMS);

        final Object[] graphicItemArray = this.getGraphicItemHashMap().keySet().toArray();
        final int size = graphicItemArray.length;
        for(int index = 0; index < size; index++)
        {
            GraphicItemInterface item = (GraphicItemInterface) this.graphicItemHashMap.get(graphicItemArray[index]);

            if (item.getName() == LinesGraphicItem.getStaticName())
            {
                if (item.isValid())
                {
                    graphicItemNode.appendChild(item.toDom(this));
                }
            }
        }

        frameNode.appendChild(graphicItemNode);

        return frameNode;
    }

    public IntegerDimension getDimension()
    {
        return dimension;
    }

    public void setDimension(IntegerDimension dimension)
    {
        this.dimension = dimension;
    }

    /**
     * @return the grid
     */
    public Grid getGrid()
    {
        return grid;
    }
}
