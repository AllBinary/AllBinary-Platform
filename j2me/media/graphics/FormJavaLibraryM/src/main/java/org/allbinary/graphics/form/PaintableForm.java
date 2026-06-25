package org.allbinary.graphics.form;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

public class PaintableForm extends ABCustomForm
{
    private static Object NULL_PAINTABLE_FORM = NullUtil.getInstance().NULL_OBJECT;
    public static PaintableForm getNullPaintableForm() {
        
        if(PaintableForm.NULL_PAINTABLE_FORM == NullUtil.getInstance().NULL_OBJECT) {
            PaintableForm.NULL_PAINTABLE_FORM = new PaintableForm(
                StringUtil.getInstance().EMPTY_STRING, 
                new ABCustomItem[0], 
                BasicColorFactory.getInstance().BLACK, 
                BasicColorFactory.getInstance().WHITE);
        }
        
        return (PaintableForm) PaintableForm.NULL_PAINTABLE_FORM;
    }

    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
    protected Rectangle rectangle = RectangleFactory.SINGLETON;

    protected int x;
    protected int y;
    
    protected FormType formType = FormTypeFactory.getInstance().NULL_FORM_TYPE;

    public PaintableForm(final String title, final ABCustomItem[] items, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, items, backgroundBasicColor, foregroundBasicColor);
        
        this.setSelectedIndex(0);
    }

    public void init(final Rectangle rectangle, final FormType formType)
    throws Exception
    {
        //this.logUtil.putF("PaintableForm formType: " + formType.toString(), this, this.commonStrings.INIT);
        
        this.rectangle = rectangle;

        final GPoint point = this.rectangle.getPoint();
        
        this.x = point.getX();
        this.y = point.getY();
        
        this.formType = formType;
    }

    @Override
    public int getWidth()
    {
        return this.rectangle.getWidth();
    }
    

}
