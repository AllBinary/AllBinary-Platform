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
package allbinary.graphics.form;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.graphics.GPoint;
import allbinary.graphics.Rectangle;
import allbinary.graphics.color.BasicColor;
import allbinary.math.RectangleCollisionUtil;

public class ScrollSelectionForm extends PaintableForm
{
    protected final int border;
    protected final int halfBorder;
    private BasicColor buttonBasicColor;
    
    private ItemPaintable paintable = ItemPaintableFactory.getInstance();

    public ScrollSelectionForm(
            String title, CustomItem[] items, 
            ItemPaintableFactory formPaintableFactory, 
            Rectangle rectangle, FormType formType, int border,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        this(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
        
        this.setPaintable(formPaintableFactory.getInstance(this));
    }
    
    public ScrollSelectionForm(
            String title, CustomItem[] items, Rectangle rectangle, FormType formType, int border,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
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
        int size = this.size();
        for (int index = 0; index < size; index++)
        {
            CustomItemInterface nextItem = 
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
    
    public int getSelectedIndex(GPoint point) throws Exception
    {
        int start = this.getStartIndex();
        int size = this.size();
        int dx = this.getDx();
        int dy = this.getDy();

        FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(CommonStrings.getInstance().START_LABEL);
        stringBuffer.append(start);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(CommonStrings.getInstance().TOTAL_LABEL);
        stringBuffer.append(size);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, GET_SELECTED_INDEX));

        for (int index = start; index < size; index++)
        {
            CustomItemInterface item = (CustomItemInterface) this.get(index);

            int width = item.getMinimumWidth();
            int height = item.getMinimumHeight();

            //LogUtil.put(LogFactory.getInstance(
            //"Checking: " + PositionStrings + " + dx + PositionStrings + dy + " x2: " + (dx + width) +
            //" y2: " + (dy + height) + " with " + point, this, "getSelectedIndex"));

            //if (RectangleCollisionUtil.isInside(dx, dy, dx + width, this.getRectangle().getMaxY(),

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
            
            
            if (RectangleCollisionUtil.isInside(
                diffX, dy - this.halfBorder, diffX + width + this.border, dy + height + this.halfBorder + 1,
                    point.getX(), point.getY()))
            {
                stringBuffer.delete(0, stringBuffer.length());
                
                stringBuffer.append(ITEM_LABEL);
                stringBuffer.append(item.getLabel());
                stringBuffer.append(CommonSeps.getInstance().SPACE);
                stringBuffer.append(CommonStrings.getInstance().INDEX_LABEL);
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
        //LogUtil.put(LogFactory.getInstance("Start - Selected " + CommonStrings.getInstance().INDEX_LABEL + this.getSelectedIndex() + " of: " + this.size(), this, GameInputStrings.getInstance()));
        //PreLogUtil.put("Start - Selected " CommonStrings.getInstance().INDEX_LABEL + this.getSelectedIndex() + " of: " + this.size(), this, GameInputStrings.getInstance());

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

        //PreLogUtil.put("End - Selected " + CommonStrings.getInstance().INDEX_LABEL + this.getSelectedIndex(), this, GameInputStrings.getInstance());
        //LogUtil.put(LogFactory.getInstance("End - Selected " + CommonStrings.getInstance().INDEX_LABEL + this.getSelectedIndex(), this, GameInputStrings.getInstance()));
        return -1;
    }

    private static final String INSIDE_FORM = " inside form";
    private static final String IS_IN_FORM = "isInForm";
    
    public boolean isInForm(GPoint point)
    {
        //LogUtil.put(LogFactory.getInstance(
        //"Checking: Rectangle: " + this.rectangle + " to " + point, this, "isInForm"));

        //- halfBorder
        if (RectangleCollisionUtil.isInside(
            x, y - halfBorder, this.rectangle.getMaxX() + border, this.rectangle.getMaxY() + border,
            point.getX(), point.getY()))
        {
            LogUtil.put(LogFactory.getInstance(point + INSIDE_FORM, this, IS_IN_FORM));
            return true;
        }
        return false;
    }

    public int paintItem(Graphics graphics, int index, CustomItemInterface item, int x, int y)
        throws Exception
    {
        int width = item.getMinimumWidth();
        int height = item.getMinimumHeight();
        
        FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
        item.paint(graphics, x, y);
        
        /*
        if (index == this.selectedIndex)
        {
        graphics.setColor(WHITE.intValue());
        }
        else
        {
        graphics.setColor(GREY.intValue());
        }
         */

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

    public int paintUnselectedItem(Graphics graphics, int index, CustomItemInterface item, int x, int y)
        throws Exception
    {
        int width = item.getMinimumWidth();
        int height = item.getMinimumHeight();

        //graphics.setColor(BasicColor.GREY.intValue());
        graphics.setColor(this.getButtonBasicColor().intValue());
        item.paintUnselected(graphics, x, y);
        
        FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
        
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
