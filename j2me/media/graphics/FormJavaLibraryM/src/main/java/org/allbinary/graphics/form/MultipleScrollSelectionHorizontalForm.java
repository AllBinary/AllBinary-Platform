package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;

/**
 * 
 * @author user
 */
public class MultipleScrollSelectionHorizontalForm 
extends ScrollSelectionForm
{
    public static MultipleScrollSelectionHorizontalForm createForm(final String title, final ABCustomItem[] items,
                                                                   final Rectangle rectangle, final FormType formType, final int border,
                                                                   final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
            throws Exception
    {
        return new MultipleScrollSelectionHorizontalForm(title, items, ItemPaintableFactory.getInstance(), rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }

    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final int backgroundColor = BasicColorFactory.getInstance().TRANSPARENT_GREY.intValue();
    
    public MultipleScrollSelectionHorizontalForm(final String title, final ABCustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory, 
            final Rectangle rectangle, final FormType formType, final int border, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }

    @Override
    public int getStartIndex()
    {
        return this.getSelectedIndex();
    }

    private boolean logged = false;
    
    @Override
    public void paint(final Graphics graphics)
    {
        try
        {
            final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();
            
            final int start = this.getStartIndex();
            final int size = this.size();
            int dx = this.x;
            final int dy = this.y;

            if(J2MEUtil.isJ2ME())
            {

            }
            else
            {
                graphics.setColor(this.backgroundColor);

                graphics.fillRect(x, y,
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
            }
            
            final MyFont myFont = MyFont.getInstance();
            graphics.drawString(this.getTitle(), x, y - myFont.DEFAULT_CHAR_HEIGHT, 0);

            ABCustomItem item;
            for (int index = start; index < size; index++)
            {
                item = this.get(index);

                if (dx >= this.rectangle.getMaxX())
                {
                    if(this.logged) {
                        
                    } else {
                        this.logged = true;
                        PreLogUtil.put(new StringMaker().append("painting beyond maxx: ").appendint(this.rectangle.getMaxX()).toString(), this, this.canvasStrings.PAINT);
                    }
                    //break;
                }
                
                if (this.formType == formTypeFactory.HORIZONTAL_FORM)
                {
                    int dx2 = this.paintItem(graphics, index, item, dx, dy) + this.border;
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
            PreLogUtil.put(this.commonStrings.EXCEPTION, this, this.canvasStrings.PAINT);
            //this.logUtil.put(commonStrings.EXCEPTION, this, canvasStrings.PAINT, e);
        }
    }

}
