package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullImage;

import org.allbinary.graphics.color.BasicColor;

public class ABCustomImageItem extends ABCustomItem
{
    protected int yOffset = 0;

    public ABCustomImageItem(String label, Image image, int layout, String altText, BasicColor basicColor, int yOffset) throws Exception
    {
        super(label, basicColor, basicColor);
    }
    
    public Image getImage()
    {
        return NullImage.NULL_IMAGE;
    }    
}
