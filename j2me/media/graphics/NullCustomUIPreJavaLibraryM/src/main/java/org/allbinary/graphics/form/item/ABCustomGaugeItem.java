package org.allbinary.graphics.form.item;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.string.StringUtil;

public class ABCustomGaugeItem
extends ABCustomItem
{
    public static final ABCustomGaugeItem NULL_GAUGE_ITEM = new ABCustomGaugeItem(
    StringUtil.getInstance().EMPTY_STRING, 1, 0, 
        BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);

    public ABCustomGaugeItem(String label, int maxValue, int initialValue,
                             BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }
}
