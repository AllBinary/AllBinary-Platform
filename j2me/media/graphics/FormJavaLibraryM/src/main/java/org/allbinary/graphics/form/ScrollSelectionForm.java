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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.string.CommonLabels;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.RectangleCollisionUtil;

public class ScrollSelectionForm extends PaintableForm
{
    private final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
    
    protected final int border;
    protected final int halfBorder;
    private BasicColor buttonBasicColor;
    
    protected ItemPaintable paintable = ItemPaintableFactory.getInstance();

    public ScrollSelectionForm(
            final String title, final CustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory, 
            final Rectangle rectangle, final FormType formType, final int border,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        this(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
        
        this.paintable = formPaintableFactory.getInstance(this);
    }
    
    public ScrollSelectionForm(
            final String title, final CustomItem[] items, final Rectangle rectangle, final FormType formType, final int border,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, items, rectangle, formType, backgroundBasicColor, foregroundBasicColor);

        this.buttonBasicColor = foregroundBasicColor;
        
//        final int size = items.length;
//        for (int index = 0; index < size; index++)
//        {
//            CustomItemInterface item = (CustomItemInterface) items[index];
//            //item.setOwner(this);
//        }
        this.border = border;
        this.halfBorder = (border >> 1);
    }

    public int append(final CustomItem item)
    {
        //((FormItemInterface) item).setOwner(this);
        return super.append(item);
    }

    public CustomItem getSelectedItem(final GPoint point)
        throws Exception
    {
        int index = this.getSelectedIndex(point);

        if (index != -1)
        {
            return this.get(index);
        }
        else
        {
            return null;
        }
    }

    public int getItemIndex(final CustomItem item)
        throws Exception
    {
        final int size = this.size();
        for (int index = 0; index < size; index++)
        {
            final CustomItemInterface nextItem = 
                (CustomItemInterface) this.get(index);

            if(nextItem == item)
            {
                return index;
            }
        }
        return  -1;
    }

    public int getStartIndex()
    {
        return 0;
    }

    private static final String GET_SELECTED_INDEX = "getSelectedIndex";
    
    public int getSelectedIndex(final GPoint point) throws Exception
    {
        final int start = this.getStartIndex();
        final int size = this.size();
        int dx = this.getDx();
        int dy = this.getDy();

        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();
        
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(commonLabels.START_LABEL);
        stringBuffer.append(start);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(commonLabels.TOTAL_LABEL);
        stringBuffer.append(size);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, GET_SELECTED_INDEX));

        CustomItemInterface item;
        int width;
        int height;
        for (int index = start; index < size; index++)
        {
            item = (CustomItemInterface) this.get(index);

            width = item.getMinimumWidth();
            height = item.getMinimumHeight();

            //originally for both formtypes
            //int diffX = dx + this.getDiffX(item) - this.halfBorder;
            int diffX = 0;
            if (this.formType == formTypeFactory.HORIZONTAL_FORM)
            {
                diffX = dx - this.halfBorder;
            }
            else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM ||
                    this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
            {
                diffX = dx + this.getDiffX(item);
            }
            else
            {
                throw new Exception(formTypeFactory.UNK);
            }
            
//            LogUtil.put(LogFactory.getInstance(new StringBuilder().append("Checking: ")
//                    .append(diffX).append(CommonSeps.getInstance().COMMA)
//                    .append((dy - this.halfBorder)).append(CommonSeps.getInstance().COMMA)
//                    .append((diffX + width + this.border)).append(CommonSeps.getInstance().COMMA)
//                    .append((dy + height + this.halfBorder + 1))
//                    .append(" with ").append(point.toString()).toString(), this, GET_SELECTED_INDEX));

            //if (RectangleCollisionUtil.isInside(dx, dy, dx + width, this.rectangle.getMaxY(),
            
            if (rectangleCollisionUtil.isInside(
                diffX, dy - this.halfBorder, diffX + width + this.border, dy + height + this.halfBorder + 1, 
                point.getX(), point.getY()))
            {
                stringBuffer.delete(0, stringBuffer.length());
                
                stringBuffer.append(commonLabels.ITEM_LABEL);
                stringBuffer.append(item.getLabel());
                stringBuffer.append(CommonSeps.getInstance().SPACE);
                stringBuffer.append(commonLabels.INDEX_LABEL);
                stringBuffer.append(index);
                
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, GET_SELECTED_INDEX));

                return index;
            }
            
            
            if (this.formType == formTypeFactory.HORIZONTAL_FORM)
            {
                dx = dx + width + border;
                // dx = dx + width;
                if (dx > this.rectangle.getMaxX())
                {
                    break;
                }
            } else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
            {
                dy = dy + height + border;
                if (dy > this.rectangle.getMaxY())
                {
                    break;
                }
            } else
            {
                throw new Exception(formTypeFactory.UNK);
            }

        }

        return -1;
    }

    public int processInput(final int gameKeyCode) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Start - Selected ").append(commonLabels.INDEX_LABEL).append(this.getSelectedIndex()).append(" of: ").append(this.size(), this, GameInputStrings.getInstance()));
        //PreLogUtil.put("Start - Selected " commonLabels.INDEX_LABEL).append(this.getSelectedIndex()).append(" of: ").append(this.size(), this, GameInputStrings.getInstance());

        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
        if (this.size() == 0)
        {
            return -1;
        }

        int index = this.getSelectedIndex();

        if (this.formType == formTypeFactory.HORIZONTAL_FORM ||
            this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            if (gameKeyCode == Canvas.RIGHT)
            {
                index++;
            }
            else if (gameKeyCode == Canvas.LEFT)
            {
                index--;
            }
        }
        else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            if (gameKeyCode == Canvas.DOWN)
            {
                index++;
            }
            else if (gameKeyCode == Canvas.UP)
            {
                index--;
            }
        }
        else
        {
            throw new Exception(formTypeFactory.UNK);
        }

        final int max = this.size() - 1;

        if (index < 0)
        {
            this.setSelectedIndex(0);
        }
        else
        if (index > max)
        {
            this.setSelectedIndex(max);
        }
        else
        {
            this.setSelectedIndex(index);
        }

        //PreLogUtil.put("End - Selected ").append(commonLabels.INDEX_LABEL).append(this.getSelectedIndex(), this, GameInputStrings.getInstance());
        //LogUtil.put(LogFactory.getInstance("End - Selected ").append(commonLabels.INDEX_LABEL).append(this.getSelectedIndex(), this, GameInputStrings.getInstance()));
        return -1;
    }

    private static final String INSIDE_FORM = " inside form";
    private static final String IS_IN_FORM = "isInForm";
    
    public boolean isInForm(final GPoint point)
    {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Checking: Rectangle: ").append(this.rectangle).append(" to ").append(point).toString(), this, IS_IN_FORM));

        //- halfBorder
        if (rectangleCollisionUtil.isInside(x, y - halfBorder, this.rectangle.getMaxX() + border, this.rectangle.getMaxY() + border,point.getX(), point.getY()))
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append(StringUtil.getInstance().toString(point)).append(INSIDE_FORM).toString(), this, IS_IN_FORM));
            return true;
        }
        return false;
    }

    public int paintItem(final Graphics graphics, final int index, final CustomItemInterface item, final int x, final int y)
        throws Exception
    {
        final int width = item.getMinimumWidth();
        final int height = item.getMinimumHeight();
        
        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
        item.paint(graphics, x, y);
        
        graphics.setColor(this.getButtonBasicColor().intValue());
        
        //graphics.drawRect(x - border, y - border_y, width + border, height + border);
        graphics.drawRect(x - halfBorder, y - halfBorder, width + border, height + border);

        if (this.formType == formTypeFactory.HORIZONTAL_FORM)
        {
            return x + width + border;
        }
        else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            return y + height + border;
        }
        else if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            return 0;
        }
        else
        {
            throw new Exception(formTypeFactory.UNK);
        }

    }

    public int paintUnselectedItem(final Graphics graphics, final int index, final CustomItemInterface item, final int x, final int y)
        throws Exception
    {
        final int width = item.getMinimumWidth();
        final int height = item.getMinimumHeight();

        //graphics.setColor(BasicColor.GREY.intValue());
        graphics.setColor(this.getButtonBasicColor().intValue());
        item.paintUnselected(graphics, x, y);
        
        final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
        if (this.formType == formTypeFactory.HORIZONTAL_FORM)
        {
            return x + width + border;
        }
        else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            return y + height + border;
        }
        else if (this.formType == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            return 0;
        }
        else
        {
            throw new Exception(formTypeFactory.UNK);
        }

    }

    protected int getDiffX(final CustomItemInterface item)
    {
        return 0;
    }

    /**
     * @return the dx
     */
    public int getDx()
    {
        return x;
    }

    /**
     * @return the dy
     */
    public int getDy()
    {
        return y;
    }

    public void setButtonBasicColor(final BasicColor buttonBasicColor)
    {
        this.buttonBasicColor = buttonBasicColor;
    }

    private BasicColor getButtonBasicColor()
    {
        return buttonBasicColor;
    }

}
