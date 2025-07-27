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
package org.allbinary.layer;

import javax.microedition.lcdui.Graphics;

public class NullLayer extends Layer
{
    private static final NullLayer instance = new NullLayer();
    
    public static NullLayer getInstance()
    {
        return instance;
    }

    private NullLayer()
    {
        super(0, 0);
    }

    @Override    
    public void paint(Graphics graphics)
    {
    }
}
