/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.animation.text;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.game.input.event.RawKeyEventListener;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.form.item.CustomTextBox;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class CustomTextBoxIndexedAnimation extends IndexedAnimation 
    implements RawKeyEventListener, UpdateMyFontInterface, TextInterface
               //GameKeyEventListenerInterface 
{
    
    private final CustomTextBox customTextBox;

    //private final int WIDTH = 13;
    //private final int HEIGHT = 8;

    private String lastText = StringUtil.getInstance().INIT_STRING;
    protected boolean hasChanged = true;

    private TextChangeListener textChangeListener = TextChangeListener.getInstance();
    
    public CustomTextBoxIndexedAnimation(final CustomTextBox customTextBox) {
        super(AnimationBehavior.getInstance());
        
        this.customTextBox = customTextBox;
    }

    public void setBackgroundBasicColorP(final BasicColor basicColor)
    {
        this.customTextBox.getTextFieldItem().setBackgroundBasicColorP(basicColor);
    }
    
    public void setTextWithOnMeasure(final String text, final TextChangeListener textChangeListener) {
        this.setText(text);
        this.textChangeListener = textChangeListener;
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        //this.fontHeight = font.getHeight();
        //this.offsetY = SWTJOGLProcessor.getInstance().isJOGL() ? (int) -(this.fontHeight * 1.33) : 0;
        this.textChangeListener.onMeasure();
        this.textChangeListener = TextChangeListener.getInstance();
        //this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    public void setText(final String text)
    {
        //this.logUtil.putF(new StringMaker().append(" text: ").append(text).toString(), this, this.commonStrings.PROCESS);        
        if(this.lastText != text) {
            this.hasChanged = true;
            this.lastText = text;
            this.customTextBox.getTextFieldItem().setString(text);
        }

//        if(font != null) {
//            final CommonLabels commonLabels = CommonLabels.getInstance();
//            this.logUtil.putF(new StringMaker().append("setText - font: ").appendint(font.getSize()).append(commonLabels.WIDTH_LABEL).appendint(font.stringWidth(text)).append(" text: ").append(text).toString(), this, this.commonStrings.PROCESS);
//        }
    }

    @Override
    public String getText() {
        return this.customTextBox.getTextFieldItem().getString();
    }

    @Override
    public void onEventRaw(final int keyCode, final int deviceId, final boolean repeated) {
        this.customTextBox.onEventRaw(keyCode, deviceId, repeated);
    }

    public int getFontHeight() {
        return this.customTextBox.getTextFieldItem().getFontHeight();
    }

    @Override    
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.customTextBox.paintXY(graphics, x, y);
        //this.customTextBox.paint(graphics, x + WIDTH, y + HEIGHT);
    }

    @Override
    public void paintThreedXYZ(Graphics graphics, int x, int y, int z)
    {

    }
    
}
