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
package org.allbinary.image.gui;

import java.awt.image.BufferedImage;

import javax.swing.*;

public class ImageIconUnique extends ImageIcon
{
    private int id;

    public ImageIconUnique(BufferedImage bufferedImage, int index)
    {
        super(bufferedImage);
        
        this.setId(index);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
}
