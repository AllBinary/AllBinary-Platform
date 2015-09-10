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
package org.allbinary.logic.system.hardware.components;

import org.allbinary.logic.basic.Unknown;

public class Component
{
    private String name;
    
    public Component(String componentName)
    {
        this.name = componentName;
    }
        
    public String toString()
    {
        return name;
    }
}
