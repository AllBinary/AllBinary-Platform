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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.math.RectangleCollisionUtil;

public class ScrollSelectionForm extends PaintableForm
{
    private final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
    
    protected final int border;
    protected final int halfBorder;
    private BasicColor buttonBasicColor;
    
    private ItemPaintable paintable = ItemPaintableFactory.getInstance();

    public ScrollSelectionForm(
            final String title, final CustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory, 
            final Rectangle rectangle, final FormType formType, final int border,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        this(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
        
        this.setPaintable(formPaintableFactory.getInstance(this));
    }
    
    public ScrollSelectionForm(
            final String title, final CustomItem[] items, final Rectangle rectangle, final FormType formType, final int border,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, items, rectangle, formType, backgroundBasicColor, foregroundBasicColor);

        this.buttonBasicColor = foregroundBasicColor;
        
        for (int index = 0; index < items.length; index++)
        {
            CustomItemInterface item = (CustomItemInterface) items[index];
            //item.setOwner(this);
        }
        this.border = border;
        this.halfBorder = (border >> 1);
    }

    public int append(CustomItem item)
    {
        //((FormItemInterface) item).setOwner(this);
        return super.append(item);
    }

    public CustomItem getSelectedItem(GPoint point)
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

    public int getItemIndex(CustomItem item)
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
    private static final String ITEM_LABEL = "Item: ";
    
    public int getSelectedIndex(final GPoint point) throws Exception
    {
        int start = this.getStartIndex();
        int size = this.size();
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

        for (int index = start; index < size; index++)
        {
            final CustomItemInterface item = (CustomItemInterface) this.get(index);

            final int width = item.getMinimumWidth();
            final int height = item.getMinimumHeight();

            //originally for both formtypes
            //int diffX = dx + this.getDiffX(item) - this.halfBorder;
            int diffX = 0;
            if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
            {
                diffX = dx - this.halfBorder;
            }
            else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM ||
                    this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
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
//                    .append(" with ").append(point.toString()).toString(), this, "getSelectedIndex"));

            //if (RectangleCollisionUtil.isInside(dx, dy, dx + width, this.getRectangle().getMaxY(),
            
            if (rectangleCollisionUtil.isInside(
                diffX, dy - this.halfBorder, diffX + width + this.border, dy + height + this.halfBorder + 1,
                    point.getX(), point.getY()))
            {
                stringBuffer.delete(0, stringBuffer.length());
                
                stringBuffer.append(ITEM_LABEL);
                stringBuffer.append(item.getLabel());
                stringBuffer.append(CommonSeps.getInstance().SPACE);
                stringBuffer.append(commonLabels.INDEX_LABEL);
                stringBuffer.append(index);
                
                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, GET_SELECTED_INDEX));

                return index;
            }
            
            
            if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
            {
                dx = dx + width + border;
                // dx = dx + width;
                if (dx > this.getRectangle().getMaxX())
                {
                    break;
                }
            } else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
            {
                dy = dy + height + border;
                if (dy > this.getRectangle().getMaxY())
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

    public int processInput(int gameKeyCode) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Start - Selected ").append(CommonStrings.getInstance().INDEX_LABEL).append(this.getSelectedIndex()).append(" of: ").append(this.size(), this, GameInputStrings.getInstance()));
        //PreLogUtil.put("Start - Selected " CommonStrings.getInstance().INDEX_LABEL).append(this.getSelectedIndex()).append(" of: ").append(this.size(), this, GameInputStrings.getInstance());

        FormTypeFactory formTypeFactory = 
            FormTypeFactory.getInstance();
        
        if (this.size() == 0)
        {
            return -1;
        }

        int index = this.getSelectedIndex();

        if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM ||
            this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
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
        else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
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

        int max = this.size() - 1;

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

        //PreLogUtil.put("End - Selected ").append(CommonStrings.getInstance().INDEX_LABEL).append(this.getSelectedIndex(), this, GameInputStrings.getInstance());
        //LogUtil.put(LogFactory.getInstance("End - Selected ").append(CommonStrings.getInstance().INDEX_LABEL).append(this.getSelectedIndex(), this, GameInputStrings.getInstance()));
        return -1;
    }

    private static final String INSIDE_FORM = " inside form";
    private static final String IS_IN_FORM = "isInForm";
    
    public boolean isInForm(GPoint point)
    {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Checking: Rectangle: ").append(this.rectangle).append(" to ").append(point).toString(), this, IS_IN_FORM));

        //- halfBorder
        if (rectangleCollisionUtil.isInside(x, y - halfBorder, this.rectangle.getMaxX() + border, this.rectangle.getMaxY() + border,point.getX(), point.getY()))
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append(point).append(INSIDE_FORM).toString(), this, IS_IN_FORM));
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

        if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
        {
            return x + width + border;
        }
        else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            return y + height + border;
        }
        else if (this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
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
        
        if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
        {
            return x + width + border;
        }
        else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
        {
            return y + height + border;
        }
        else if (this.getFormType() == formTypeFactory.TEMP_HORIZONTAL_FORM)
        {
            return 0;
        }
        else
        {
            throw new Exception(formTypeFactory.UNK);
        }

    }

    protected int getDiffX(CustomItemInterface item)
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

    public void setButtonBasicColor(BasicColor buttonBasicColor)
    {
        this.buttonBasicColor = buttonBasicColor;
    }

    private BasicColor getButtonBasicColor()
    {
        return buttonBasicColor;
    }

    private void setPaintable(ItemPaintable paintable)
    {
        this.paintable = paintable;
    }

    protected ItemPaintable getPaintable()
    {
        return paintable;
    }
}
