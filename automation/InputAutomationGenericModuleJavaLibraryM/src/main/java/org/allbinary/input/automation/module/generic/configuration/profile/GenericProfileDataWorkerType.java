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
package org.allbinary.input.automation.module.generic.configuration.profile;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.media.image.InputImageType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GenericProfileDataWorkerType 
    implements GenericProfileDataWorkerTypeInterface
{
    private static HashMap hashMap = new HashMap();
    
    public static GenericProfileDataWorkerType SAVED_CAPTURE = 
        SavedCaptureGenericProfileDataWorkerType.SAVED_CAPTURE;

    public static GenericProfileDataWorkerType SCREEN_CAPTURE = 
        new GenericProfileDataWorkerType("Screen " + InputImageType.CAPTURE.getName());

    public static GenericProfileDataWorkerType COMPARISON = 
        new GenericProfileDataWorkerType(InputImageType.COMPARISON.getName());
    public static GenericProfileDataWorkerType MOTION = 
        new GenericProfileDataWorkerType(InputImageType.MOTION.getName());

    private String name;
    
    public GenericProfileDataWorkerType(String name)
    {
        this.setName(name);
        hashMap.put(this.getName(), this);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = ModDomHelper.createTextNode(
            document, GenericProfileDataWorkerData.NAME, name);
        return node;
    }
    
    public String toString()
    {
        return "GenericProfileDataWorkerType: " + this.getName();
    }
    
    public static GenericProfileDataWorkerType getInstance(Node node)
    {
        String name = DomNodeHelper.getTextNodeValue(node);
        return (GenericProfileDataWorkerType) hashMap.get(name);
    }
}
