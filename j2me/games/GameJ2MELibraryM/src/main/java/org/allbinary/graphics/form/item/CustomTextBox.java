/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.J2MEUtil;
import org.allbinary.game.displayable.canvas.GameCommandCanvas;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.PlatformKeyFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.validation.TextItemVisitor;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class CustomTextBox extends GameCommandCanvas
{    
    private final ABTextFieldItem textFieldItem;

    public CustomTextBox(final CommandListener cmdListener, final String label, final String text, 
        final int maxSize, final int constraints,
        final Font font, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        super(cmdListener, label, backgroundBasicColor, foregroundBasicColor);

        final StringUtil stringUtil = StringUtil.getInstance();
        
        final ABTextFieldItem textFieldItem = new ABTextFieldItem(
            this, new TextItemVisitor(), stringUtil.EMPTY_STRING, 
            stringUtil.EMPTY_STRING, maxSize, 0, 
                stringUtil.EMPTY_STRING,  font,
                backgroundBasicColor, foregroundBasicColor);

        textFieldItem.setString(text);
        
        this.textFieldItem = textFieldItem;

        //this.logUtil.putF(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        this.setTitle(label);
        
    }
    
    public void submit()
    {
        
    }

    private final InputFactory inputFactory = InputFactory.getInstance();

    public void onEvent(final int keyCode, final int deviceId, final boolean repeated) {
        this.logUtil.putF(new StringMaker().append(commonStrings.START).appendint(keyCode).toString(), this, "onEvent");
        this.keyPressedByDevice(keyCode, deviceId);
    }
    
    @Override
    public void keyPressed(final int keyCode)
    {
        this.keyPressedByDevice(keyCode, 0);
    }
    
    @Override
    public void keyReleased(final int keyCode)
    {
        this.keyReleasedByDevice(keyCode, 0);
    }

    @Override
    public void keyRepeated(final int keyCode)
    {
        this.keyRepeatedByDevice(keyCode, 0);
    }
    
    @Override
    public void keyPressedByDevice(final int keyCode, final int deviceId)
    {
        try {

            this.logUtil.putF(new StringMaker().append(CommonSeps.getInstance().SPACE).appendint(keyCode).toString(), this, gameInputStrings.KEY_PRESSED);

            final PlatformKeyFactory platformKeyFactory = PlatformKeyFactory.getInstance();

            final Input input = this.inputFactory.getInstanceById(keyCode);

            if (platformKeyFactory.isSubmission(input)) {
                this.submit();
                //PreLogUtil.put("Should Delete", this, gameInputStrings.KEY_PRESSED);
                //this.deleteAtText();
            } else {
                this.textFieldItem.keyPressed(keyCode);
                //if(!)
                //{
                //super.keyPressed(keyCode);
                //}
            }
            
        } catch(Exception e) {
            this.logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.KEY_PRESSED, e);
        }
    }

    @Override
    public void keyReleasedByDevice(final int keyCode, final int deviceId)
    {
        // this.logUtil.putF(commonStrings.START, this, gameInputStrings.KEY_RELEASED);
    }

    @Override
    public void paint(final Graphics graphics)
    {
        graphics.setColor(this.backgroundColor);
        //graphics.fillRect(0, 0, graphics.getClipWidth(), graphics.getClipHeight());
        graphics.fillRect(0, 0, this.displayInfoSingleton.getLastWidth(), this.displayInfoSingleton.getLastHeight());
     
        //g.translate(0, viewPortY);
        graphics.setColor(this.foregroundColor);

        if(!J2MEUtil.isJ2ME())
        {
            graphics.drawString(this.getTitle(), 8, 1, 0);
        }
        
        //final int viewPortHeight = myFont.MYFONT.DEFAULT_CHAR_HEIGHT;
        //graphics.drawRect(1, 14, getWidth() - 3, viewPortHeight);
        //g.setClip(3, 3, getWidth() - 6, viewPortHeight - 6);
        //g.translate(3, 3);
        //g.translate(0, -viewPortY);
        this.paintXY(graphics, 8, graphics.getFont().getHeight() + 2);
        
        super.paint(graphics);
    }
    
    public void paintXY(final Graphics graphics, final int x, final int y)
    {   
        this.textFieldItem.paintXY(graphics, x, y);
    }
    
    public ABTextFieldItem getTextFieldItem()
    {
        return this.textFieldItem;
    }

}
