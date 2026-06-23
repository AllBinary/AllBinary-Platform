/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.layer;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.input.motion.button.CommonButtons;
import org.allbinary.input.motion.button.TouchButtonLocationHelper;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class SelectionHudPaintable extends InitUpdatePaintable implements UpdateMyFontInterface
{
    
    private final CommonButtons commonButtons = CommonButtons.getInstance();
    
    protected final int y = CommonButtons.getInstance().STANDARD_BUTTON_SIZE + 17;

    private final PrimitiveLongUtil primitiveLongUtil;    

    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = updateMyFontProcessor;
    
    private int x;

    protected int textX;
    protected int imageX;
        
    // private String percentComplete = StringUtil.getInstance();
    private BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private int color;
    
    private int width;
    private int height;

    private String name = StringUtil.getInstance().EMPTY_STRING;
    
    // private final int ANIMATION_WIDTH_MAGIC = 70;
    private Animation animationInterface = NullAnimationFactory.getFactoryInstance().getInstance(0);

    private final int backgroundColor = BasicColorFactory.getInstance().GREY.intValue();
        //BasicColor.TRANSPARENT_GREY.intValue();

    protected SelectionHudPaintable()
    {
        this.update();

        this.primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(10000);
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        // 3 for 2 extra lines for upgrade and downgrade
        // this.height = (weaponProperties.length + 2) *
        // MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;
        this.setHeight(commonButtons.STANDARD_BUTTON_SIZE + font.getHeight());
        
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void update()
    {
        final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

        final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
        
        this.x = this.commonButtons.STANDARD_BUTTON_SIZE + touchButtonLocationHelper.getColumnsRemainderHalf();

        this.textX = this.getX() + 4;

        this.width = displayInfoSingleton.getLastWidth() - this.getX() * 2;

        this.imageX = this.getWidth() + touchButtonLocationHelper.getColumnsRemainderHalf() - 10;
        
        this.myFontProcessor = this.updateMyFontProcessor;
        
        //this.costY = y + TouchButtonInput.STANDARD_BUTTON_SIZE;
        //this.costY1 = this.costY;
    }

    public void updateSelectionInfo()
    {
        
    }
    
    public void updateInfo()
    {
    }

    protected PrimitiveLongUtil getPrimitiveLongUtil()
    {
        return this.primitiveLongUtil;
    }

    protected void setAnimationInterface(Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }
    
    protected Animation getAnimationInterface()
    {
        return this.animationInterface;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    protected String getName()
    {
        return this.name;
    }

    @Override
    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        this.setColor(basicColor.intValue());
    }
    
    protected BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getColor()
    {
        return this.color;
    }

    protected int getWidth()
    {
        return this.width;
    }

    protected void setHeight(int height)
    {
        this.height = height;
    }

    protected int getHeight()
    {
        return this.height;
    }

    protected int getX()
    {
        return this.x;
    }
        
    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        graphics.setColor(this.backgroundColor);
        //graphics.fillRect(this.getX(), y, this.getWidth(), this.getHeight());
        graphics.drawRect(this.getX(), this.y, this.getWidth(), this.getHeight());

        graphics.setColor(this.getColor());
        graphics.drawString(this.getName(), this.textX, this.y, 0);
    }
    
}
