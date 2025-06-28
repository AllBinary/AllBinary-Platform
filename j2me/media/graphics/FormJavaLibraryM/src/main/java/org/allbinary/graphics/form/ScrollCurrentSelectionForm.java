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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;

public class ScrollCurrentSelectionForm 
extends ScrollSelectionForm
{
    private int dx;
    private int dy;

    private int maxWidth = 0;

    private final boolean moveForSmallScreen;

    public ScrollCurrentSelectionForm(final String title, final CustomItem[] items,
            final ItemPaintableFactory formPaintableFactory, final Rectangle rectangle,
            final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border,
                backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
        
        this.init();
    }
    
    public ScrollCurrentSelectionForm(final String title, final CustomItem[] items,
            final Rectangle rectangle, final FormType formType, 
            final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
        
        this.init();
    }

    public void init(final Rectangle rectangle, final FormType formType)
    throws Exception
    {
        super.init(rectangle, formType);
        this.init();
    }

    public void init()
    {
        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

        if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            dx = x - 30 + (this.rectangle.getWidth() >> 1);
            dy = y;
        } else if (this.formType == formTypeFactory.HORIZONTAL_FORM)
        {
            int size = this.size();

            int totalWidth = 0;

            for (int index = 0; index < size; index++)
            {
                CustomItemInterface item = (CustomItemInterface) this.get(index);

                totalWidth += item.getMinimumWidth() + border;
            }

            dx = x + (this.rectangle.getWidth() >> 1) - (totalWidth >> 1);

            /*
            int maxHeight = 0;

            for (int index = 0; index < size; index++)
            {
                FormItemInterface item = (FormItemInterface) this.get(index);

                if(item.getMinimumHeight() > maxHeight)
                {
                    maxHeight = item.getMinimumHeight();
                }
            }
            */

            dy = y + (this.rectangle.getHeight() >> 1);

            // Special handling for small screens to keep menu out of title
            // animation
            if (this.moveForSmallScreen)
            {
                int maxTitleHeight = 175;
                if (dy < maxTitleHeight)
                {
                    dy = maxTitleHeight;
                }
            }
        } else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            int totalHeight = 0;
            int size = this.size();
            for (int index = 0; index < size; index++)
            {
                CustomItem item2 = this.get(index);
                if (maxWidth < item2.getMinimumWidth())
                {
                    maxWidth = item2.getMinimumWidth();
                }

                totalHeight += item2.getMinimumHeight() + border;
            }

            dx = ((this.rectangle.getWidth() - maxWidth) / 2);
            // dx = this.rectangle.getWidth() - maxWidth;

            if (this.size() > 0)
            {
                // FormItemInterface item = (FormItemInterface) this.get(0);
                // dy = y - 30 + ((this.rectangle.getHeight() -
                // item.getMinimumHeight()) / 2);
                dy = y + ((this.rectangle.getHeight() - totalHeight) / 2);

                // Special handling for small screens to keep menu out of title
                // animation
                if (this.moveForSmallScreen)
                {
                    int maxTitleHeight = 175;
                    if (dy < maxTitleHeight)
                    {
                        dy = maxTitleHeight;
                    }
                }
            } else
            {
                // dy = y - 30 + ((this.rectangle.getHeight()) >> 1);
                dy = y;
            }
        } else
        {
            LogUtil.put(LogFactory.getInstance(formTypeFactory.UNK, this, commonStrings.INIT));
        }
    }

    public void paint(final Graphics graphics)
    {
        try
        {
            int delta = 0;
            int deltaX = getDx();
            int deltaY = getDy();
            int size = this.size();
            
            final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
            
            for (int index = 0; index < size; index++)
            {
                final CustomItemInterface item = (CustomItemInterface) this.get(index);

                int diffX = 0;
                if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
                {
                    diffX = this.getDiffX(item);
                }
                else
                if (this.formType == formTypeFactory.HORIZONTAL_FORM)
                {
                    //diffX = this.halfBorder;
                }
                else 
                    if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    diffX = this.getDiffX(item) + this.halfBorder;
                }
                else
                {
                    throw new Exception(formTypeFactory.UNK);
                }

                if (index == this.getSelectedIndex())
                {
                    delta = this.paintItem(graphics, index, item, deltaX + diffX, deltaY);
                    this.paintable.paint(graphics, index, deltaX + diffX, deltaY);
                }
                else
                {
                    delta = this.paintUnselectedItem(graphics, index, item, deltaX + diffX, deltaY);
                    this.paintable.paint(graphics, index, deltaX + diffX, deltaY);
                }

                if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
                {
                }
                else
                if (this.formType == formTypeFactory.HORIZONTAL_FORM)
                {
                    deltaX = delta;
                }
                else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    deltaY = delta;
                }
                else
                {
                    throw new Exception(formTypeFactory.UNK);
                }

                //graphics.drawRect(x, y - halfBorder, this.rectangle.getMaxX() + border, this.rectangle.getMaxY() + border);
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, canvasStrings.PAINT, e));
        }
    }

    protected int getDiffX(CustomItemInterface item)
    {
        return ((maxWidth - item.getMinimumWidth()) >> 1);
    }

    /**
     * @return the dx
     */
    public int getDx()
    {
        return dx;
    }

    /**
     * @return the dy
     */
    public int getDy()
    {
        return dy;
    }
}
