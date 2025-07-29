package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.graphics.color.BasicColor;

public class CustomImageItem extends CustomItem
{
    protected int yOffset = 0;
    
    public CustomImageItem(String label, Image image, int layout, String altText, BasicColor basicColor)
            throws Exception
    {
        super(label, basicColor, basicColor);
    }

    public CustomImageItem(String label, Image image, int layout, String altText, BasicColor basicColor, int yOffset) throws Exception
    {
        super(label, basicColor, basicColor);
    }
    
    public Image getImage()
    {
        return NullCanvas.NULL_IMAGE;
    }    
}
