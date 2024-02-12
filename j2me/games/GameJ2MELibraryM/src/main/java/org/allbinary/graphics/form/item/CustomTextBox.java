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


import org.allbinary.logic.string.StringUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.game.displayable.canvas.GameCommandCanvas;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.PlatformKeyFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.form.item.validation.TextItemVisitor;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;

public class CustomTextBox extends GameCommandCanvas
{
    private final TextFieldItem textFieldItem;
    //private final int viewPortHeight = MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;

    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
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
        super(cmdListener, backgroundBasicColor, foregroundBasicColor);

        final StringUtil stringUtil = StringUtil.getInstance();
        
        final TextFieldItem textFieldItem = new TextFieldItem(
            this, new TextItemVisitor(), stringUtil.EMPTY_STRING, 
            stringUtil.EMPTY_STRING, maxSize, 0, 
                stringUtil.EMPTY_STRING,  font,
                backgroundBasicColor, foregroundBasicColor);

        textFieldItem.setString(text);
        
        this.textFieldItem = textFieldItem;

        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));
        this.setTitle(label);
        
    }
    
    public void submit()
    {
        
    }

    private final InputFactory inputFactory = InputFactory.getInstance();

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
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.START).append(keyCode).toString(), this, "keyPressed"));
        PreLogUtil.put(new StringMaker().append(commonStrings.START).append(keyCode).toString(), this, "keyPressed");
        
        final PlatformKeyFactory platformKeyFactory = PlatformKeyFactory.getInstance();
        
        final Input input = inputFactory.getInstance(keyCode);
        
        if (platformKeyFactory.isSubmission(input))
        {
            this.submit();
            //PreLogUtil.put("Should Delete", this, "keyPressed");
            //this.deleteAtText();
        } else {
            this.textFieldItem.keyPressed(keyCode);
            //if(!)
            //{
                //super.keyPressed(keyCode);
            //}
        }
    }

    public void keyReleased(final int keyCode, final int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "keyReleased"));
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
            graphics.drawString(this.getTitle(), 1, 1, 0);
        }
        
        //graphics.drawRect(1, 14, getWidth() - 3, viewPortHeight);
        //g.setClip(3, 3, getWidth() - 6, viewPortHeight - 6);
        //g.translate(3, 3);
        //g.translate(0, -viewPortY);
        this.paint(graphics, 1, 14);
        
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
