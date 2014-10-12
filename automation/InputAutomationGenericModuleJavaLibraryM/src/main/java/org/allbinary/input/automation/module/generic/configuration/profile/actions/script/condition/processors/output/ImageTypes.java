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
package org.allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.output;

import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.media.image.InputImageType;

public class ImageTypes
{
    private Vector vector = new Vector();

    public ImageTypes()
    {
    }
    
    public ImageTypes(Node node)
    throws Exception
    {
        NodeList nodeList = node.getChildNodes();
        
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node childNode = nodeList.item(index);
            
            if(childNode.getNodeName().compareTo(ImageActionScriptOutputData.TYPE) == 0)
            {
                String nextImageTypeString = DomNodeHelper.getTextNodeValue(childNode);
                this.getVector().add(InputImageType.getInstance(nextImageTypeString));
            }
            else
            {
                throw new Exception("ImageTypes Unknown Node");
            }
        }
    }
    
    //String selectedImageTypeStringArray[] = 
      //      (String[]) 
    //String selectedImageTypeStringArray[]
    public static ImageTypes valueOf(Object object[])
    {
        ImageTypes imageTypes = new ImageTypes();

        for(int index = 0; index < object.length; index++)
        {
            InputImageType imageType = InputImageType.getInstance((String) object[index]);
            imageTypes.getVector().add(imageType);
        }
        return imageTypes;
    }

    public Vector getVector()
    {
        return vector;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node newNode = document.createElement(
            ImageActionScriptOutputData.TYPES);
        
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            InputImageType imageType = (InputImageType) iterator.next();
            
            newNode.appendChild(ModDomHelper.createTextNode(document,
                ImageActionScriptOutputData.TYPE, imageType.getName()));
        }
        return newNode;
    }
    
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append("ImageTypes: ");
        
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            InputImageType imageType = (InputImageType) iterator.next();
            stringBuffer.append(imageType.getName());
            if(iterator.hasNext()) stringBuffer.append(", ");
        }
        
        return stringBuffer.toString();
    }
}
