package org.allbinary.graphics.form.item;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

public class StringComponent
{
    public static final StringComponent NULL_STRING_COMPONENT = new StringComponent();
    
    public BasicColor getBackgroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    public void setBackgroundBasicColor(BasicColor backgroundBasicColor) {
    }

    public BasicColor getForegroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    public void setForegroundBasicColor(BasicColor foregroundBasicColor) {
    }
}
