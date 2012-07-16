package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Image;

import allbinary.graphics.color.BasicColor;

public class CustomImageItem extends CustomItem
{
    public CustomImageItem(String label, Image image, int layout, String altText, BasicColor basicColor)
            throws Exception
    {
        super(label, basicColor, basicColor);
    }

    public CustomImageItem(String label, Image image, int layout, String altText, BasicColor basicColor, int yOffset) throws Exception
    {
        super(label, basicColor, basicColor);
    }
}
