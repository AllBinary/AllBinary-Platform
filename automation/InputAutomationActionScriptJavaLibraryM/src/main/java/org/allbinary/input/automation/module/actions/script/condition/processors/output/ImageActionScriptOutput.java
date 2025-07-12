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
package org.allbinary.input.automation.module.actions.script.condition.processors.output;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImageActionScriptOutput
    extends BasicProfileActionScriptOutput
    implements ImageActionScriptOutputInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final static String NAME = "Captures";
    
    private boolean saved = false;
    private boolean display = false;
    
    private ImageTypes imageTypes;
    
    private ImageActionScriptOutputJPanel actionScriptOutputJPanel;
    
    public ImageActionScriptOutput(Node node)
        throws Exception
    {
        super(NAME, node);

        logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

        Node actionNode = DomSearchHelper.getNode(
            ImageActionScriptOutputData.NAME,
            node.getChildNodes());

        if(actionNode != null)
        {
            NodeList nodeList = actionNode.getChildNodes();
            
            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node childNode = nodeList.item(index);

                if(childNode.getNodeName().compareTo(ImageActionScriptOutputData.SAVE) == 0)
                {
                    String booleanString = DomNodeHelper.getTextNodeValue(childNode);
                    this.setSaved(new Boolean(booleanString).booleanValue());
                }
                else
                if(childNode.getNodeName().compareTo(ImageActionScriptOutputData.DISPLAY) == 0)
                {
                    String booleanString = DomNodeHelper.getTextNodeValue(childNode);
                    this.setSaved(new Boolean(booleanString).booleanValue());
                }
                else
                if(childNode.getNodeName().compareTo(ImageActionScriptOutputData.TYPES) == 0)
                {
                    this.setImageTypes(new ImageTypes(childNode));
                }
                else
                {
                    throw new Exception("Action Script Output Unknown Node");
                }
            }
        }
        else
        {
            throw new Exception("Action Script Output Node Null");
        }

        this.setAllowsChildren(false);
        
        this.actionScriptOutputJPanel = new ImageActionScriptOutputJPanel(this);
    }
    
    public ImageActionScriptOutput()
        throws Exception
    {
        super(NAME);

        this.setImageTypes(new ImageTypes());

        this.setAllowsChildren(false);
        
        this.actionScriptOutputJPanel = new ImageActionScriptOutputJPanel(this);
    }

    public ImageActionScriptOutputJPanel getActionScriptOutputJPanel()
    {
        return actionScriptOutputJPanel;
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        hashMap.put(ImageActionScriptOutputData.DISPLAY, 
            Boolean.toString(this.isDisplay()));

        hashMap.put(ImageActionScriptOutputData.SAVE, 
            Boolean.toString(this.isSaved()));
        
        logUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = super.toXmlNode(document);

        Node newNode = ModDomHelper.createNodeWithValueNodes(
            document, ImageActionScriptOutputData.NAME, this.toHashMap());

        newNode.appendChild(
            this.getImageTypes().toXmlNode(document));

        node.appendChild(newNode);
        //return newNode;
        return node;
    }
    
    public void process(Long frame) 
        throws Exception
    {
        ImageActionScriptOutputProcessor.process(this, frame);
    }

    public void showDialog()
    {
        this.actionScriptOutputJPanel.getCapturedImageActionJDialog().setVisible(true);
    }

    public boolean isSaved()
    {
        return saved;
    }

    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }

    public boolean isDisplay()
    {
        return display;
    }

    public void setDisplay(boolean display)
    {
        this.display = display;
    }
    
    public String future_toString()
    {
        StringMaker stringBuffer = new StringMaker(); 
        stringBuffer.append(" Is Save: ");
        stringBuffer.append(this.isSaved());
        stringBuffer.append(" Is Display: ");
        stringBuffer.append(this.isDisplay());
        stringBuffer.append(" ");
        stringBuffer.append(this.getImageTypes().toString());
        
        return stringBuffer.toString();
    }

    public void log()
    {
        logUtil.put(this.future_toString(), this, "log");
    }
    
    public ImageTypes getImageTypes()
    {
        return imageTypes;
    }

    public void setImageTypes(ImageTypes imageTypes)
    {
        this.imageTypes = imageTypes;
    }
}
