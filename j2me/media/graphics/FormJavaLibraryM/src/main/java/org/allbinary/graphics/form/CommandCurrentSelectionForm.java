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
import org.allbinary.graphics.form.item.CommandTextItem;
import org.allbinary.graphics.form.item.ABCustomItem;

public class CommandCurrentSelectionForm extends ScrollCurrentSelectionForm
{
    protected final Animation[] selectedAnimationArray = new Animation[16];
    protected final Animation[] unSelectedAnimationArray = new Animation[16];

    public CommandCurrentSelectionForm(
            final String title, final ABCustomItem[] items,
            final Rectangle rectangle, final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
            throws Exception
    {
        super(title, items, ItemPaintableFactory.getInstance(), rectangle, formType, border, moveForSmallScreen, backgroundBasicColor, foregroundBasicColor);

        this.initAnimations();
        this.update(items);
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

    private void update(final ABCustomItem[] items)
    {        
        for(int index = items.length; --index >= 0;)
        {
            this.updateAt(index, items[index]);
        }
    }
    
    private void updateAt(final int index, final ABCustomItem item)
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

    public Command getSelectedCommand()
    {
        final int index = super.getSelectedIndex();
        final CommandTextItem commandTextItem = (CommandTextItem) this.get(index);
        return commandTextItem.getCommand();
    }

    @Override
    public int append(final ABCustomItem item)
    {
        int result = super.append(item);

        this.updateAt(result, item);

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
