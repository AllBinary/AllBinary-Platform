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
package org.allbinary.input.automation.actions.script.condition.processors.input;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MouseActionScriptInput
    extends BasicProfileActionScriptInput
    implements MouseActionScriptInputInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private Point point;
    private int buttons;
    
    private final static String NAME = "Mouse";
    
    private MouseActionScriptInputJPanel mouseActionScriptInputJPanel;
    
    public MouseActionScriptInput(Node node)
        throws Exception
    {
        super(NAME, node);

        logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        
        this.point = new Point();
        
        Node actionNode = DomSearchHelper.getNode(
            MouseActionScriptInputData.NAME,
            node.getChildNodes());

        if(actionNode != null)
        {
            NodeList nodeList = actionNode.getChildNodes();
            
            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node childNode = nodeList.item(index);

                if(childNode.getNodeName().compareTo(MouseActionScriptInputData.BUTTONS) == 0)
                {
                    String buttons = DomNodeHelper.getTextNodeValue(childNode);
                    this.setButtonClicks(Integer.valueOf(buttons).intValue());
                }
                else
                if(childNode.getNodeName().compareTo(MouseActionScriptInputData.MOVE) == 0)
                {
                    Node mouseXNode = DomSearchHelper.getNode(
                        MouseActionScriptInputData.MOVE_X, 
                        childNode.getChildNodes());

                    String mouseXString = 
                        DomNodeHelper.getTextNodeValue(mouseXNode);

                    Integer mouseXInteger = 
                        Integer.valueOf(mouseXString);

                    Node mouseYNode = DomSearchHelper.getNode(
                        MouseActionScriptInputData.MOVE_Y, 
                        childNode.getChildNodes());
                    
                    String mouseYString = 
                        DomNodeHelper.getTextNodeValue(mouseYNode);

                    Integer mouseYInteger = 
                        Integer.valueOf(mouseYString);
                    
                    Point newPoint = new Point(
                        mouseXInteger.intValue(), 
                        mouseYInteger.intValue());
                    this.setPoint(newPoint);
                }
                else
                {
                    throw new Exception("Action Script Input Unknown Node");
                }
            }
        }
        else
        {
            throw new Exception("Action Script Input Node Null");
        }

        this.setAllowsChildren(false);
        
        this.mouseActionScriptInputJPanel = new MouseActionScriptInputJPanel(this);
    }
    
    public MouseActionScriptInput()
        throws Exception
    {
        super(NAME);
        
        this.point = new Point();
        
        this.setAllowsChildren(false);
        
        this.mouseActionScriptInputJPanel = new MouseActionScriptInputJPanel(this);
    }

    public void setPoint(Point point)
    {
        this.point = point;
    }
    
    public Point getPoint()
    {
        return this.point;
    }
    
    public int getButtonClicks()
    {
        return this.buttons;
    }
    
    public void setButtonClicks(int buttons)
    {
        this.buttons = buttons;
    }

    public void showDialog()
    {
        this.mouseActionScriptInputJPanel.getMouseActionJDialog().setVisible(true);
    }

    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        //super.toHashMap().put(MouseActionScriptInputData.NAME, hashMap);
        
        hashMap.put(MouseActionScriptInputData.BUTTONS, Integer.toString(this.getButtonClicks()));

        logUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = super.toXmlNode(document);

        Node mouseNode = ModDomHelper.createNodeWithValueNodes(
            document, MouseActionScriptInputData.NAME, this.toHashMap());
        node.appendChild(mouseNode);

        HashMap hashMap = new HashMap();

        hashMap.put(MouseActionScriptInputData.MOVE_X, 
            Integer.toString(this.getPoint().x));
        hashMap.put(MouseActionScriptInputData.MOVE_Y, 
            Integer.toString(this.getPoint().y));

        mouseNode.appendChild(
            ModDomHelper.createNodeWithValueNodes(
            document, MouseActionScriptInputData.MOVE, hashMap));

        return node;
    }

    public void process(Long frame)
        throws Exception
    {
        MouseInputAutomationProcessor.process(this);
    }

    public void log()
    {
        StringMaker buttonStringBuffer = new StringMaker();

        buttonStringBuffer.append("1: ");
        
        if((this.getButtonClicks() & InputEvent.BUTTON1_MASK) != 0)
        {
            buttonStringBuffer.append(Boolean.TRUE);
        }
        else
        {
            buttonStringBuffer.append(Boolean.FALSE);
        }

        buttonStringBuffer.append(" 2: ");

        if((this.getButtonClicks() & InputEvent.BUTTON2_MASK) != 0)
        {
            buttonStringBuffer.append(Boolean.TRUE);
        }
        else
        {
            buttonStringBuffer.append(Boolean.FALSE);
        }

        buttonStringBuffer.append(" 3: ");

        if((this.getButtonClicks() & InputEvent.BUTTON3_MASK) != 0)
        {
            buttonStringBuffer.append(Boolean.TRUE);
        }
        else
        {
            buttonStringBuffer.append(Boolean.FALSE);
        }
        
        logUtil.put(
            "Input Type: " + this.getInputRobotInterface().getName() + 
            " Point: " + this.getPoint() + " Buttons Clicked: " + 
            buttonStringBuffer.toString(), this, "log");
    }
}
