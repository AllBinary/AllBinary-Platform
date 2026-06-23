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
package org.allbinary.graphics.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;

import org.allbinary.J2MEUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.vector.RectangleAdjustedAnimation;
import org.allbinary.animation.vector.RectangleFilledAdjustedAnimation;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.form.item.CommandTextItem;
import org.allbinary.graphics.form.item.ABCustomItem;

public class CommandCurrentSelectionForm extends ScrollCurrentSelectionForm
    implements UpdateMyFontInterface
{
    protected final Animation[] selectedAnimationArray = new Animation[16];
    protected final Animation[] unSelectedAnimationArray = new Animation[16];

    private final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    public CommandCurrentSelectionForm(
            final String title, final ABCustomItem[] items,
            final Rectangle rectangle, final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
            throws Exception
    {
        super(title, items, ItemPaintableFactory.getInstance(), rectangle, formType, border, moveForSmallScreen, backgroundBasicColor, foregroundBasicColor);

        this.initAnimations();
        this.addAll(items);
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        this.updateAll(graphics, getAllitems());
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    private void initAnimations()
    {        
        final Animation nullAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);

        for(int index = this.selectedAnimationArray.length; --index >= 0;)
        {
            this.selectedAnimationArray[index] = nullAnimation;
        }

        for(int index = this.unSelectedAnimationArray.length; --index >= 0;)
        {
            this.unSelectedAnimationArray[index] = nullAnimation;
        }
    }

    private void addAll(final ABCustomItem[] items)
    {        
        for(int index = items.length; --index >= 0;)
        {
            this.addAt(index, items[index]);
        }
    }
    
    private void addAt(final int index, final ABCustomItem item)
    {
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        final BasicColor buttonColor = basicColorFactory.TRANSPARENT_GREY;
        final BasicColor selectedButtonColor = basicColorFactory.TRANSPARENT_RED;
            
        int width = item.getMinimumWidth();
        int height = item.getMinimumHeight();

        //TWB - Adjust gap between menu items rectangles
        int adjustedBorder = 3;
            
        int offset = -(this.halfBorder + adjustedBorder);

        if (J2MEUtil.isJ2ME())
        {
            this.selectedAnimationArray[index] = new RectangleAdjustedAnimation(
                    width + this.border - adjustedBorder,
                    height + this.border - adjustedBorder, 
                    offset, offset, selectedButtonColor);
        } else
        {
            this.selectedAnimationArray[index] = new RectangleFilledAdjustedAnimation(
                    width + this.border - adjustedBorder,
                    height + this.border - adjustedBorder, 
                    offset, offset, selectedButtonColor);
        }

        //TWB - Adjust gap between menu items rectangles
        adjustedBorder = 4;

        if (J2MEUtil.isJ2ME())
        {
            this.unSelectedAnimationArray[index] = new RectangleAdjustedAnimation(
                    width + this.border - adjustedBorder,
                    height + this.border - adjustedBorder, 
                    offset, offset, buttonColor);
        } else
        {
            this.unSelectedAnimationArray[index] = new RectangleFilledAdjustedAnimation(
                    width + this.border - adjustedBorder,
                    height + this.border - adjustedBorder, 
                    offset, offset, buttonColor);
        }
    }

    private void updateAll(final Graphics graphics, final ABCustomItem[] items)
    {        
        for(int index = items.length; --index >= 0;)
        {
            items[index].preMeasurement(graphics);
            this.updateAt(index, items[index]);
        }
    }
    
    private void updateAt(final int index, final ABCustomItem item)
    {
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        final BasicColor buttonColor = basicColorFactory.TRANSPARENT_GREY;
        final BasicColor selectedButtonColor = basicColorFactory.TRANSPARENT_RED;
            
        final int width = item.getMinimumWidth();
        final int height = item.getMinimumHeight();

        //TWB - Adjust gap between menu items rectangles
        int adjustedBorder = 3;
            
        int offset = -(this.halfBorder + adjustedBorder);

        if (J2MEUtil.isJ2ME())
        {
            final RectangleAdjustedAnimation rectangleAdjustedAnimation = (RectangleAdjustedAnimation) this.selectedAnimationArray[index];
            
            rectangleAdjustedAnimation.setWidth(width + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setHeight(height + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setOffsetX(offset);
            rectangleAdjustedAnimation.setOffsetY(offset);
            rectangleAdjustedAnimation.setBasicColorP(selectedButtonColor);

        } else
        {
            final RectangleFilledAdjustedAnimation rectangleAdjustedAnimation = (RectangleFilledAdjustedAnimation) this.selectedAnimationArray[index];
            
            rectangleAdjustedAnimation.setWidth(width + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setHeight(height + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setOffsetX(offset);
            rectangleAdjustedAnimation.setOffsetY(offset);
            rectangleAdjustedAnimation.setBasicColorP(selectedButtonColor);

        }

        //TWB - Adjust gap between menu items rectangles
        adjustedBorder = 4;

        if (J2MEUtil.isJ2ME())
        {
            final RectangleAdjustedAnimation rectangleAdjustedAnimation = (RectangleAdjustedAnimation) this.unSelectedAnimationArray[index];
            
            rectangleAdjustedAnimation.setWidth(width + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setHeight(height + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setOffsetX(offset);
            rectangleAdjustedAnimation.setOffsetY(offset);
            rectangleAdjustedAnimation.setBasicColorP(buttonColor);

        } else
        {
            final RectangleFilledAdjustedAnimation rectangleAdjustedAnimation = (RectangleFilledAdjustedAnimation) this.unSelectedAnimationArray[index];
            
            rectangleAdjustedAnimation.setWidth(width + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setHeight(height + this.border - adjustedBorder);
            rectangleAdjustedAnimation.setOffsetX(offset);
            rectangleAdjustedAnimation.setOffsetY(offset);
            rectangleAdjustedAnimation.setBasicColorP(buttonColor);

        }
    }
    
    public Command getSelectedCommand()
    {
        final int index = super.getSelectedIndex();
        final CommandTextItem commandTextItem = (CommandTextItem) /*TS as unknown*/ this.get(index);
        return commandTextItem.getCommand();
    }

    @Override
    public int append(final ABCustomItem item)
    {
        int result = super.append(item);

        this.addAt(result, item);

        return result;
    }

    @Override
    public void delete(final int itemNum)
    {
        super.delete(itemNum);
    }

    @Override
    public void deleteAll()
    {
        this.initAnimations();
        super.deleteAll();
    }

    @Override
    public void insert(final int itemNum, final ABCustomItem item)
    {
        super.insert(itemNum, item);
    }

    @Override
    public void set(final int itemNum, final ABCustomItem item)
    {
        super.set(itemNum, item);
    }
    
    @Override
    public void paint(final Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        super.paint(graphics);
    }    
    
    
    @Override
    public int paintItem(final Graphics graphics, final int index, final ABCustomItem item, final int x, final int y)
        throws Exception
    {
        this.selectedAnimationArray[index].paintXY(graphics, x, y);
        return super.paintItem(graphics, index, item, x, y);
    }

    @Override
    public int paintUnselectedItem(final Graphics graphics, final int index, final ABCustomItem item, final int x, final int y)
        throws Exception
    {
        this.unSelectedAnimationArray[index].paintXY(graphics, x, y);
        return super.paintUnselectedItem(graphics, index, item, x, y);
    }
    
}
