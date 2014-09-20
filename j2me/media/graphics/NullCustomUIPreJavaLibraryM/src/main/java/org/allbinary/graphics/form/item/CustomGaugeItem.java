package org.allbinary.graphics.form.item;

import org.allbinary.graphics.color.BasicColor;

public class CustomGaugeItem 
extends CustomItem
{
    public CustomGaugeItem(String label, int maxValue, int initialValue, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }
}
