package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.string.StringMaker;

/**
 * 
 * @author user
 */
public class MultipleScrollSelectionHorizontalForm 
extends ScrollSelectionForm
{
    public MultipleScrollSelectionHorizontalForm(final String title, final CustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory, 
            final Rectangle rectangle, final FormType formType, final int border, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }

    public MultipleScrollSelectionHorizontalForm(final String title, final CustomItem[] items, 
            final Rectangle rectangle, final FormType formType, final int border, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
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

    public void paint(final Graphics graphics)
    {
        try
        {
            final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
            
            final int start = this.getStartIndex();
            final int size = this.size();
            int dx = x;
            final int dy = y;

            if(J2MEUtil.isJ2ME())
            {

            }
            else
            {
                graphics.setColor(backgroundColor);

                graphics.fillRect(x, y,
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
            }
            
            graphics.drawString(this.getTitle(), x, y - fontHeight, 0);

            CustomItemInterface item;
            for (int index = start; index < size; index++)
            {
                item = (CustomItemInterface) this.get(index);

                if (dx >= this.rectangle.getMaxX())
                {
                    PreLogUtil.put(new StringMaker().append("painting beyond maxx: ").append(this.rectangle.getMaxX()).toString(), this, "paint");
                    //break;
                }
                
                if (this.formType == formTypeFactory.HORIZONTAL_FORM)
                {
                    int dx2 = this.paintItem(graphics, index, item, dx, dy) + border;
                    this.paintable.paint(graphics, index, dx, dy);
                    dx = dx2;
                } else if (this.formType == formTypeFactory.VERTICAL_CENTER_FORM)
                {
                    //int dy2 = 
                        this.paintItem(graphics, index, item, dx, dy)
                        //+ border
                        ;
                    this.paintable.paint(graphics, index, dx, dy);
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
