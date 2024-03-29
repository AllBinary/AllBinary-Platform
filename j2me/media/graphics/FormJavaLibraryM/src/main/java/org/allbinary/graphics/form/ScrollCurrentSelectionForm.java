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

import org.allbinary.logic.string.CommonStrings;
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

    public ScrollCurrentSelectionForm(String title, CustomItem[] items,
            ItemPaintableFactory formPaintableFactory, Rectangle rectangle,
            FormType formType, int border, boolean moveForSmallScreen,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border,
                backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
        
        this.init();
    }
    
    public ScrollCurrentSelectionForm(String title, CustomItem[] items,
            Rectangle rectangle, FormType formType, 
            int border, boolean moveForSmallScreen,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor) 
        throws Exception
    {
        super(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);

        this.moveForSmallScreen = moveForSmallScreen;
        
        this.init();
    }

    public void init(Rectangle rectangle, FormType formType)
    throws Exception
    {
        super.init(rectangle, formType);
        this.init();
    }

    public void init()
    {
        FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

        if (this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            dx = x - 30 + (this.getRectangle().getWidth() >> 1);
            dy = y;
        } else if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
        {
            int size = this.size();

            int totalWidth = 0;

            for (int index = 0; index < size; index++)
            {
                CustomItemInterface item = (CustomItemInterface) this.get(index);

                totalWidth += item.getMinimumWidth() + border;
            }

            dx = x + (this.getRectangle().getWidth() >> 1) - (totalWidth >> 1);

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

            dy = y + (this.getRectangle().getHeight() >> 1);

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
        } else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
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

            dx = ((this.getRectangle().getWidth() - maxWidth) / 2);
            // dx = this.getRectangle().getWidth() - maxWidth;

            if (this.size() > 0)
            {
                // FormItemInterface item = (FormItemInterface) this.get(0);
                // dy = y - 30 + ((this.getRectangle().getHeight() -
                // item.getMinimumHeight()) / 2);
                dy = y + ((this.getRectangle().getHeight() - totalHeight) / 2);

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
                // dy = y - 30 + ((this.getRectangle().getHeight()) >> 1);
                dy = y;
            }
        } else
        {
            LogUtil.put(LogFactory.getInstance(formTypeFactory.UNK, this, CommonStrings.getInstance().INIT));
        }
    }

    public void paint(Graphics graphics)
    {
        try
        {
            int delta = 0;
            int deltaX = getDx();
            int deltaY = getDy();
            int size = this.size();
            
            FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
            
            for (int index = 0; index < size; index++)
            {
                CustomItemInterface item = (CustomItemInterface) this.get(index);

                int diffX = 0;
                if (this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
                {
                    diffX = this.getDiffX(item);
                }
                else
                if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
                {
                    //diffX = this.halfBorder;
                }
                else 
                    if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
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
                    this.getPaintable().paint(graphics, index, deltaX + diffX, deltaY);
                }
                else
                {
                    delta = this.paintUnselectedItem(graphics, index, item, deltaX + diffX, deltaY);
                    this.getPaintable().paint(graphics, index, deltaX + diffX, deltaY);
                }

                if (this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
                {
                }
                else
                if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
                {
                    deltaX = delta;
                }
                else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    deltaY = delta;
                }
                else
                {
                    throw new Exception(formTypeFactory.UNK);
                }

            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "paint", e));
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
