package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;

public class CustomCustomItem extends ABCustomItem implements UpdateMyFontInterface
{

    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    protected CustomCustomItem(String label, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor) {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
    }
    
}
