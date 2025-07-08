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
package org.allbinary.input.automation.actions.script.condition;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.Color;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.graphics.color.ColorCacheFactory;
import org.allbinary.graphics.color.ColorCacheable;
import org.allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.media.image.analysis.ColorRange;
import org.allbinary.media.image.analysis.ColorRangeInterface;
import org.allbinary.media.image.cache.BufferedImageFrameCacheable;

public class ColorAtActionScriptCondition
    extends BasicProfileActionScriptCondition
    implements ColorAtActionScriptConditionInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private ColorAtActionScriptConditionJPanel colorAtActionScriptConditionJPanel;
    
    private Point point;

    private ColorRangeInterface colorRangeInterface = 
        (ColorRangeInterface) new ColorRange();

    private final static String NAME = "If Color At";
    
    public ColorAtActionScriptCondition(Node node)
        throws Exception
    {
        super(ColorAtActionScriptCondition.NAME, node);
        
        this.point = new Point(0,0);

        Node actionNode = DomSearchHelper.getNode(
            ColorAtActionScriptConditionData.NAME,
            node.getChildNodes());

        if(actionNode != null)
        {
            NodeList nodeList = actionNode.getChildNodes();
            
            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node childNode = nodeList.item(index);

                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.LOCATION_X) == 0)
                {
                    String locationX = DomNodeHelper.getTextNodeValue(childNode);
                    this.point.x = Integer.valueOf(locationX);
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.LOCATION_Y) == 0)
                {
                    String locationY = DomNodeHelper.getTextNodeValue(childNode);
                    this.point.y = Integer.valueOf(locationY);
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MIN_RED) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMinRed(Integer.valueOf(string));
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MAX_RED) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMaxRed(Integer.valueOf(string));
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MIN_GREEN) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMinGreen(Integer.valueOf(string));
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MAX_GREEN) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMaxGreen(Integer.valueOf(string));
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MIN_BLUE) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMinBlue(Integer.valueOf(string));
                }
                else
                if(childNode.getNodeName().compareTo(ColorAtActionScriptConditionData.MAX_BLUE) == 0)
                {
                    String string = DomNodeHelper.getTextNodeValue(childNode);
                    this.getColorRangeInterface().setMaxBlue(Integer.valueOf(string));
                }
            }
        }
        else
        {
            throw new Exception("Color At Action Script Condition Node Null");
        }
        this.init();
    }
    
    public ColorAtActionScriptCondition()
    {
        super(ColorAtActionScriptCondition.NAME);
        
        this.point = new Point(0,0);

        this.init();
    }

    private void init()
    {
        this.colorAtActionScriptConditionJPanel = 
            new ColorAtActionScriptConditionJPanel(this);
    }
    
    public Point getPoint()
    {
        return point;
    }

    public void setPoint(Point point)
    {
        this.point = point;
    }
 
    public void showDialog()
    {
        this.colorAtActionScriptConditionJPanel.getColorAtActionJDialog().setVisible(true);
    }

    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        hashMap.put(ColorAtActionScriptConditionData.LOCATION_X, Integer.toString(this.getPoint().x));
        hashMap.put(ColorAtActionScriptConditionData.LOCATION_Y, Integer.toString(this.getPoint().y));

        hashMap.put(ColorAtActionScriptConditionData.MIN_RED, Integer.toString(this.getColorRangeInterface().getMinRed()));
        hashMap.put(ColorAtActionScriptConditionData.MAX_RED, Integer.toString(this.getColorRangeInterface().getMaxRed()));

        hashMap.put(ColorAtActionScriptConditionData.MIN_GREEN, Integer.toString(this.getColorRangeInterface().getMinGreen()));
        hashMap.put(ColorAtActionScriptConditionData.MAX_GREEN, Integer.toString(this.getColorRangeInterface().getMaxGreen()));

        hashMap.put(ColorAtActionScriptConditionData.MIN_BLUE, Integer.toString(this.getColorRangeInterface().getMinBlue()));
        hashMap.put(ColorAtActionScriptConditionData.MAX_BLUE, Integer.toString(this.getColorRangeInterface().getMaxBlue()));

        logUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = super.toXmlNode(document);
        
        node.appendChild(ModDomHelper.createNodeWithValueNodes(
            document, ColorAtActionScriptConditionData.NAME, this.toHashMap()));
        
        return node;
    }
    
    public boolean shouldProcess(long frame)
    throws Exception
    {
        BufferedImageFrameCacheable capturedBufferedImageCacheable =
            (BufferedImageFrameCacheable)
            ((AutomaticCacheInterface) CapturedBufferedImagesCacheSingleton.getInstance()).get(Long.valueOf(frame));
        
        BufferedImage bufferedImage = capturedBufferedImageCacheable.getBufferedImage();
           
        AutomaticCacheInterface automaticCacheInterface = 
              ColorCacheFactory.getInstance();

        Integer colorInteger = Integer.valueOf(
            bufferedImage.getRGB(this.getPoint().x, this.getPoint().y));
        ColorCacheable colorCacheable = (ColorCacheable)
            automaticCacheInterface.get(colorInteger);
        Color color = colorCacheable.getColor();
           
        //If you find a valid color then process the child
        //automation inputs
        if(this.colorRangeInterface.isInRange(color))
        {
            return super.shouldProcess(frame);
        }
        return false;
    }
    
    public void log()
    {
        String message = 
            "getPoint(): " + this.getPoint() +
            "\n" + this.getColorRangeInterface().toString();

        logUtil.put(message, this, "log");
    }

    public ColorRangeInterface getColorRangeInterface()
    {
        return colorRangeInterface;
    }

    public void setColorRangeInterface(ColorRangeInterface colorRangeInterface)
    {
        this.colorRangeInterface = colorRangeInterface;
    }
}
