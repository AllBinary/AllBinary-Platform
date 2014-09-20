/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;

/**
 * 
 * @author user
 */
public class MultipleScrollSelectionHorizontalForm 
extends ScrollSelectionForm
{
    public MultipleScrollSelectionHorizontalForm(String title, CustomItem[] items, 
            ItemPaintableFactory formPaintableFactory, 
            Rectangle rectangle, FormType formType, int border, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }

    public MultipleScrollSelectionHorizontalForm(String title, CustomItem[] items, 
            Rectangle rectangle, FormType formType, int border, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    throws Exception
    {
        super(title, items, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }
    
    public int getStartIndex()
    {
        return this.getSelectedIndex();
    }

    private final int backgroundColor = BasicColorFactory.getInstance().TRANSPARENT_GREY.intValue();

    private final int fontHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;

    public void paint(Graphics graphics)
    {
        try
        {
            FormTypeFactory formTypeFactory = 
                FormTypeFactory.getInstance();
            
            int start = this.getStartIndex();
            int size = this.size();
            int dx = x;
            int dy = y;

            if(J2MEUtil.isJ2ME())
            {

            }
            else
            {
                graphics.setColor(backgroundColor);

                graphics.fillRect(x, y,
                    this.getRectangle().getWidth(),
                    this.getRectangle().getHeight());
            }
            
            graphics.drawString(this.getTitle(), x, y - fontHeight, 0);

            for (int index = start; index < size; index++)
            {
                CustomItemInterface item = (CustomItemInterface) this.get(index);

                if (dx >= this.getRectangle().getMaxX())
                {
                    break;
                }
                
                if (this.getFormType() == formTypeFactory.HORIZONTAL_FORM)
                {
                    int dx2 = this.paintItem(graphics, index, item, dx, dy) + border;
                    this.getPaintable().paint(graphics, index, dx, dy);
                    dx = dx2;
                } else if (this.getFormType() == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    //int dy2 = 
                        this.paintItem(graphics, index, item, dx, dy)
                        //+ border
                        ;
                    this.getPaintable().paint(graphics, index, dx, dy);
                    //dy2 = dy;
                }
                else
                {
                    throw new Exception(formTypeFactory.UNK);
                }
            }
        } catch (Exception e)
        {
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, "paint");
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "paint", e));
        }
    }

}
