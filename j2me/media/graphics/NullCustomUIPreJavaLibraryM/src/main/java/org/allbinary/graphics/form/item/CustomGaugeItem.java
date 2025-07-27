package org.allbinary.graphics.form.item;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.string.StringUtil;

public class CustomGaugeItem 
extends CustomItem
{
    public static final CustomGaugeItem NULL_GAUGE_ITEM = new CustomGaugeItem(
    StringUtil.getInstance().EMPTY_STRING, 1, 0, 
        BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);

    public CustomGaugeItem(String label, int maxValue, int initialValue, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }
}
