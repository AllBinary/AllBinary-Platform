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

import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

public class ScrollCurrentSelectionForm 
extends ScrollSelectionForm
{

    private int dx;
    private int dy;

    private int maxWidth = 0;

    private final boolean moveForSmallScreen;

    public ScrollCurrentSelectionForm(final String title, final ABCustomItem[] items,
            final ItemPaintableFactory formPaintableFactory, final Rectangle rectangle,
            final FormType formType, final int border, final boolean moveForSmallScreen,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border,
                backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
        
        this.initForm();
    }

    @Override
    public void init(final Rectangle rectangle, final FormType formType)
    throws Exception
    {
        super.init(rectangle, formType);
        this.initForm();
    }

    public void initForm()
    {
        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

        if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            this.dx = x - 30 + (this.rectangle.getWidth() >> 1);
            this.dy = y;
        } else if (this.formType == formTypeFactory.HORIZONTAL_FORM)
        {
            int size = this.size();

            int totalWidth = 0;

            CustomItemInterface item;
            for (int index = 0; index < size; index++)
            {
                item = (CustomItemInterface) this.get(index);

                totalWidth += item.getMinimumWidth() + border;
            }

            this.dx = x + (this.rectangle.getWidth() >> 1) - (totalWidth >> 1);

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

            this.dy = y + (this.rectangle.getHeight() >> 1);

            // Special handling for small screens to keep menu out of title
            // animation
            if (this.moveForSmallScreen)
            {
                int maxTitleHeight = 175;
                if (this.dy < maxTitleHeight)
                {
                    this.dy = maxTitleHeight;
                }
            }
        } else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            int totalHeight = 0;
            int size = this.size();
            ABCustomItem item2;
            for (int index = 0; index < size; index++)
            {
                item2 = this.get(index);
                if (this.maxWidth < item2.getMinimumWidth())
                {
                    this.maxWidth = item2.getMinimumWidth();
                }

                totalHeight += item2.getMinimumHeight() + border;
            }

            this.dx = ((this.rectangle.getWidth() - this.maxWidth) / 2);
            // dx = this.rectangle.getWidth() - maxWidth;

            if (this.size() > 0)
            {
                // FormItemInterface item = (FormItemInterface) this.get(0);
                // dy = y - 30 + ((this.rectangle.getHeight() -
                // item.getMinimumHeight()) / 2);
                this.dy = y + ((this.rectangle.getHeight() - totalHeight) / 2);

                // Special handling for small screens to keep menu out of title
                // animation
                if (this.moveForSmallScreen)
                {
                    int maxTitleHeight = 175;
                    if (this.dy < maxTitleHeight)
                    {
                        this.dy = maxTitleHeight;
                    }
                }
            } else
            {
                // dy = y - 30 + ((this.rectangle.getHeight()) >> 1);
                this.dy = y;
            }
        } else
        {
            this.logUtil.putF(formTypeFactory.UNK, this, commonStrings.INIT);
        }
    }

    @Override
    public void paint(final Graphics graphics)
    {
        try
        {
            int delta = 0;
            int deltaX = this.getDx();
            int deltaY = this.getDy();
            int size = this.size();
            
            final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

            CustomItemInterface item;
            for (int index = 0; index < size; index++)
            {
                item = (CustomItemInterface) this.get(index);

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
            this.logUtil.put(commonStrings.EXCEPTION, this, canvasStrings.PAINT, e);
        }
    }

    @Override
    protected int getDiffX(CustomItemInterface item)
    {
        return ((this.maxWidth - item.getMinimumWidth()) >> 1);
    }

    /**
     * @return the dx
     */
    @Override
    public int getDx()
    {
        return this.dx;
    }

    /**
     * @return the dy
     */
    @Override
    public int getDy()
    {
        return this.dy;
    }
}
