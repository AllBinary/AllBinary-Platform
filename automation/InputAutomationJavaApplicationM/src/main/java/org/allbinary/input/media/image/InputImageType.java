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
package org.allbinary.input.media.image;

import java.util.HashMap;
import java.util.Vector;

public class InputImageType
{
    private static HashMap hashMap = new HashMap();

    private final static Vector TYPE_VECTOR = new Vector();
    
    public static InputImageType CAPTURE = new InputImageType("Capture", 0);
    public static InputImageType COMPARISON = new InputImageType("Comparison", 1);
    public static InputImageType MOTION = new InputImageType("Motion", 2);
    
    private final String name;
    private final int index;
    
    private InputImageType(String name, int index)
    {
        this.name = name;
        this.index = index;
        TYPE_VECTOR.add(this);
        this.hashMap.put(this.getName(), this);
    }
    
    public String toString()
    {
        return "ImageType: " + getName();
    }

    public String getName()
    {
        return name;
    }
    
    public static InputImageType getInstance(String imageTypeString)
    {
        return (InputImageType) hashMap.get(imageTypeString);
    }
    
    public static Vector getAllAsVector()
    {
        return TYPE_VECTOR;
    }

    public int getIndex()
    {
        return index;
    }
}
