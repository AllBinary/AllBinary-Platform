package org.allbinary.graphics.form.item;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

public class ABStringComponent
{
    public static final ABStringComponent NULL_STRING_COMPONENT = new ABStringComponent(BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);
    
    public BasicColor getBackgroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    public ABStringComponent(final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) {
    }

    public BasicColor getForegroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    public void setForegroundBasicColor(BasicColor foregroundBasicColor) {
    }
}
