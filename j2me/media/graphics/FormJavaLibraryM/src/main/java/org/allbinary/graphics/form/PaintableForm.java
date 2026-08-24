package org.allbinary.graphics.form;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class PaintableForm extends ABCustomForm
{
    private static Object NULL_PAINTABLE_FORM = NullUtil.getInstance().NULL_OBJECT;
    @JsMethod
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

    @JsProperty
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
    @JsProperty
    protected Rectangle rectangle = RectangleFactory.SINGLETON;

    @JsProperty
    protected int x;
    @JsProperty
    protected int y;
    
    @JsProperty
    protected FormType formType = FormTypeFactory.getInstance().NULL_FORM_TYPE;

    @JsConstructor
    public PaintableForm(final String title, final ABCustomItem[] items, 
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, items, backgroundBasicColor, foregroundBasicColor);
        
        this.setSelectedIndex(0);
    }

    @JsMethod
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
    @JsMethod
    public int getWidth()
    {
        return this.rectangle.getWidth();
    }
    

}
