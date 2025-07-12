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
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

public class CommandCurrentSelectionForm extends ScrollCurrentSelectionForm
{
    protected final Animation[] selectedAnimationArray = new Animation[16];
    protected final Animation[] unSelectedAnimationArray = new Animation[16];

    public CommandCurrentSelectionForm(
            final String title, final CustomItem[] items,
            final Rectangle rectangle, final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
            throws Exception
    {
        super(title, items, rectangle, formType, border, moveForSmallScreen,
                backgroundBasicColor, foregroundBasicColor);

        this.initAnimations();
        this.update(items);
    }

    private void initAnimations()
    {        
        final Animation nullAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);

        for(int index = selectedAnimationArray.length; --index >= 0;)
        {
            this.selectedAnimationArray[index] = nullAnimation;
        }

        for(int index = unSelectedAnimationArray.length; --index >= 0;)
        {
            this.unSelectedAnimationArray[index] = nullAnimation;
        }
    }

    private void update(final CustomItem[] items)
    {        
        for(int index = items.length; --index >= 0;)
        {
            this.update(index, items[index]);
        }
    }
    
    private void update(final int index, final CustomItem item)
    {
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        final BasicColor buttonColor = basicColorFactory.TRANSPARENT_GREY;
        final BasicColor selectedButtonColor = basicColorFactory.TRANSPARENT_RED;
            
        int width = item.getMinimumWidth();
        int height = item.getMinimumHeight();

        int adjustedBorder = 1;
            
        int offset = -(halfBorder + adjustedBorder);

        if (J2MEUtil.isJ2ME())
        {
            this.selectedAnimationArray[index] = new RectangleAdjustedAnimation(
                    width + border - adjustedBorder,
                    height + border - adjustedBorder, 
                    offset, offset, selectedButtonColor);
        } else
        {
            this.selectedAnimationArray[index] = new RectangleFilledAdjustedAnimation(
                    width + border - adjustedBorder,
                    height + border - adjustedBorder, 
                    offset, offset, selectedButtonColor);
        }

        adjustedBorder = 2;

        if (J2MEUtil.isJ2ME())
        {
            this.unSelectedAnimationArray[index] = new RectangleAdjustedAnimation(
                    width + border - adjustedBorder,
                    height + border - adjustedBorder, 
                    offset, offset, buttonColor);
        } else
        {
            this.unSelectedAnimationArray[index] = new RectangleFilledAdjustedAnimation(
                    width + border - adjustedBorder,
                    height + border - adjustedBorder, 
                    offset, offset, buttonColor);
        }
    }

    public Command getSelectedCommand()
    {
        final int index = super.getSelectedIndex();
        return ((CommandTextItem) this.get(index)).getCommand();
    }

    public int append(final CustomItem item)
    {
        int result = super.append(item);

        this.update(result, item);

        return result;
    }

    public void delete(final int itemNum)
    {
        super.delete(itemNum);
    }

    public void deleteAll()
    {
        this.initAnimations();
        super.deleteAll();
    }

    public void insert(final int itemNum, final CustomItem item)
    {
        super.insert(itemNum, item);
    }

    public void set(final int itemNum, final CustomItem item)
    {
        super.set(itemNum, item);
    }
    
    public int paintItem(final Graphics graphics, final int index, final CustomItemInterface item, final int x, final int y)
        throws Exception
    {
        this.selectedAnimationArray[index].paint(graphics, x, y);
        return super.paintItem(graphics, index, item, x, y);
    }

    public int paintUnselectedItem(final Graphics graphics, final int index, final CustomItemInterface item, final int x, final int y)
        throws Exception
    {
        this.unSelectedAnimationArray[index].paint(graphics, x, y);
        return super.paintUnselectedItem(graphics, index, item, x, y);
    }
    
}
