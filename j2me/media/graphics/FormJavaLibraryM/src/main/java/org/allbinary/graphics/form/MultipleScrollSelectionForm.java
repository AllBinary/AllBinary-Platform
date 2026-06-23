package org.allbinary.graphics.form;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;

/**
 * 
 * @author user
 */
public class MultipleScrollSelectionForm extends ScrollSelectionForm implements UpdateMyFontInterface
{

    public static MultipleScrollSelectionForm createForm(final String title, final ABCustomItem[] items,
                                                                   final Rectangle rectangle, final FormType formType, final int border,
                                                                   final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
            throws Exception
    {
        return new MultipleScrollSelectionForm(title, items, ItemPaintableFactory.getInstance(), rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
    }

    class MultipleScrollSelectionFormHorizontalPaintable extends ItemIndexPaintable {

        private final MultipleScrollSelectionForm multipleScrollSelectionForm;
        
        MultipleScrollSelectionFormHorizontalPaintable(final MultipleScrollSelectionForm multipleScrollSelectionForm) {
            this.multipleScrollSelectionForm = multipleScrollSelectionForm;
        }
        
        @Override
        public int paint(final Graphics graphics, final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
            return this.multipleScrollSelectionForm.paintHorizontal(graphics, index, item, dx, dy);
        }
        
    };
    
    class MultipleScrollSelectionFormVerticalPaintable extends ItemIndexPaintable {

        private final MultipleScrollSelectionForm multipleScrollSelectionForm;
        
        MultipleScrollSelectionFormVerticalPaintable(final MultipleScrollSelectionForm multipleScrollSelectionForm) {
            this.multipleScrollSelectionForm = multipleScrollSelectionForm;
        }
        
        @Override
        public int paint(final Graphics graphics, final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
            return this.multipleScrollSelectionForm.paintVertical(graphics, index, item, dx, dy);
        }
        
    };

    //protected final LogUtil logUtil = LogUtil.getInstance();
    private final FormTypeFactory formTypeFactory = FormTypeFactory.getInstance();

    private ItemIndexPaintable formTypeItemIndexPaintable = ItemIndexPaintable.getInstance();

    private final int backgroundColor = BasicColorFactory.getInstance().TRANSPARENT_GREY.intValue();

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);

    private boolean logged = false;
    
    private int fontHeight = 0;
    
    public MultipleScrollSelectionForm(final String title, final ABCustomItem[] items, 
            final ItemPaintableFactory formPaintableFactory, 
            final Rectangle rectangle, final FormType formType, final int border, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    throws Exception
    {
        super(title, items, formPaintableFactory, rectangle, formType, border, backgroundBasicColor, foregroundBasicColor);
        
        if (formType == this.formTypeFactory.HORIZONTAL_FORM) {
            this.formTypeItemIndexPaintable = new MultipleScrollSelectionFormHorizontalPaintable(this);
        } else if (formType == this.formTypeFactory.VERTICAL_CENTER_FORM) {
            this.formTypeItemIndexPaintable = new MultipleScrollSelectionFormVerticalPaintable(this);
        } else if (formType == this.formTypeFactory.NULL_FORM_TYPE) {
        } else {
            throw new Exception(this.formTypeFactory.UNK);
        }

    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }

    private int paintHorizontal(final Graphics graphics, final int index, final ABCustomItem item, int dx, final int dy) throws Exception {
        int dx2 = this.paintItem(graphics, index, item, dx, dy) + this.border;
        this.paintable.paint(graphics, index, dx, dy);
        dx = dx2;
        return dx;
    }

    private int paintVertical(final Graphics graphics, final int index, final ABCustomItem item, final int dx, final int dy) throws Exception {
        //int dy2 = 
        this.paintItem(graphics, index, item, dx, dy); //+ border
        this.paintable.paint(graphics, index, dx, dy);
        //dy2 = dy;
        return dx;
    }
    
    @Override
    public int getStartIndex()
    {
        return this.getSelectedIndex();
    }

    @Override
    public void paint(final Graphics graphics)
    {
        try
        {
            this.myFontProcessor.process(graphics);

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

                graphics.fillRect(this.x, this.y,
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
            }

            graphics.drawString(this.getTitle(), this.x, this.y - this.fontHeight, 0);

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
                
                dx = this.formTypeItemIndexPaintable.paint(graphics, index, item, dx, dy);
            }
        } catch (Exception e)
        {
            PreLogUtil.put(this.commonStrings.EXCEPTION, this, this.canvasStrings.PAINT);
            //this.logUtil.put(commonStrings.EXCEPTION, this, canvasStrings.PAINT, e);
        }
    }

}
