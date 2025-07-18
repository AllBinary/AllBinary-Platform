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
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.PlatformKeyFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.form.item.validation.TextItemVisitor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class CustomTextBox extends GameCommandCanvas
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    private final TextFieldItem textFieldItem;
    
    public CustomTextBox(final CommandListener cmdListener, final String label, final String text, 
        final int maxSize, final int constraints,
        final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) 
        throws Exception 
    {
        this(cmdListener, label, text, maxSize, constraints, Font.getDefaultFont(), backgroundBasicColor, foregroundBasicColor);
    }

    public CustomTextBox(final CommandListener cmdListener, final String label, final String text, 
        final int maxSize, final int constraints,
        final Font font, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        super(cmdListener, label, backgroundBasicColor, foregroundBasicColor);

        final StringUtil stringUtil = StringUtil.getInstance();
        
        final TextFieldItem textFieldItem = new TextFieldItem(
            this, new TextItemVisitor(), stringUtil.EMPTY_STRING, 
            stringUtil.EMPTY_STRING, maxSize, 0, 
                stringUtil.EMPTY_STRING,  font,
                backgroundBasicColor, foregroundBasicColor);

        textFieldItem.setString(text);
        
        this.textFieldItem = textFieldItem;

        //logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        this.setTitle(label);
        
    }
    
    public void submit()
    {
        
    }

    private final InputFactory inputFactory = InputFactory.getInstance();

    public void onEvent(final int keyCode, final int deviceId, final boolean repeated) {
        logUtil.put(new StringMaker().append(commonStrings.START).append(keyCode).toString(), this, "onEvent");
        this.keyPressed(keyCode, deviceId);
    }
    
    public void keyPressed(final int keyCode)
    {
        this.keyPressed(keyCode, 0);
    }
    
    public void keyReleased(final int keyCode)
    {
        this.keyReleased(keyCode, 0);
    }

    public void keyRepeated(final int keyCode)
    {
        this.keyRepeated(keyCode, 0);
    }
    
    public void keyPressed(final int keyCode, final int deviceId)
    {
        try {

            logUtil.put(new StringMaker().append(CommonSeps.getInstance().SPACE).append(keyCode).toString(), this, gameInputStrings.KEY_PRESSED);

            final PlatformKeyFactory platformKeyFactory = PlatformKeyFactory.getInstance();

            final Input input = inputFactory.getInstance(keyCode);

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
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.KEY_PRESSED, e);
        }
    }

    public void keyReleased(final int keyCode, final int deviceId)
    {
        // logUtil.put(commonStrings.START, this, gameInputStrings.KEY_RELEASED);
    }

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
        this.paint(graphics, 8, graphics.getFont().getHeight() + 2);
        
        super.paint(graphics);
    }
    
    public void paint(final Graphics graphics, final int x, final int y)
    {   
        textFieldItem.paint(graphics, x, y);
    }
    
    public TextFieldItem getTextFieldItem()
    {
        return textFieldItem;
    }

}
