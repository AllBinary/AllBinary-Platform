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
package allbinary.game.score.displayable;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.TextFieldItem;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.J2MEUtil;
import allbinary.game.displayable.canvas.GameCommandCanvas;
import allbinary.game.input.Input;
import allbinary.game.input.InputFactory;
import allbinary.game.input.PlatformKeyFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.form.item.validation.TextItemVisitor;

public class CustomTextBox extends GameCommandCanvas
{
    private final TextFieldItem textFieldItem;
    //private final int viewPortHeight = MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;
    
    public CustomTextBox(CommandListener cmdListener, String label, String text, int maxSize, int constraints,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(cmdListener, backgroundBasicColor, foregroundBasicColor);

        final StringUtil stringUtil = StringUtil.getInstance();
        
        this.textFieldItem = new TextFieldItem(this, new TextItemVisitor(), 
                stringUtil.EMPTY_STRING, stringUtil.EMPTY_STRING, maxSize, 0, 
                stringUtil.EMPTY_STRING,  
                backgroundBasicColor, foregroundBasicColor);

        this.textFieldItem.setString(text);

        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
        this.setTitle(label);
    }
    
    public void submit()
    {
        
    }

    private final InputFactory inputFactory = InputFactory.getInstance();

    public void keyPressed(int keyCode)
    {
        this.keyPressed(keyCode, 0);
    }
    
    public void keyReleased(int keyCode)
    {
        this.keyReleased(keyCode, 0);
    }

    public void keyRepeated(int keyCode)
    {
        this.keyRepeated(keyCode, 0);
    }
    
    public void keyPressed(int keyCode, int deviceId)
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + keyCode, this, "keyPressed"));
        PreLogUtil.put(CommonStrings.getInstance().START_LABEL + keyCode, this, "keyPressed");
        
        PlatformKeyFactory platformKeyFactory =
            PlatformKeyFactory.getInstance();
        
        Input input = inputFactory.getInstance(keyCode);
        
        if (platformKeyFactory.isSubmission(input))
        {
            this.submit();
            //PreLogUtil.put("Should Delete", this, "keyPressed");
            //this.deleteAtText();
        }
        else
        {
            this.textFieldItem.keyPressed(keyCode);
            //if(!)
            //{
                //super.keyPressed(keyCode);
            //}
        }
    }

    public void keyReleased(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyReleased"));
    }
        
    public void paint(Graphics graphics)
    {
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(0, 0, graphics.getClipWidth(), graphics.getClipHeight());

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
        textFieldItem.paint(graphics, 1, 14);

        super.paint(graphics);
    }

    public TextFieldItem getTextFieldItem()
    {
        return textFieldItem;
    }    
}
