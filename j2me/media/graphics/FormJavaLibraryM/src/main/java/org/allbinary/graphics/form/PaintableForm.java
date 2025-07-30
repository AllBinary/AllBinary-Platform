package org.allbinary.graphics.form;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class PaintableForm extends CustomForm
{
    public static final PaintableForm NULL_PAINTABLE_FORM = new PaintableForm(
    StringUtil.getInstance().EMPTY_STRING, new CustomItem[0], RectangleFactory.SINGLETON, 
        FormTypeFactory.getInstance().NULL_FORM_TYPE, 
        BasicColorFactory.getInstance().BLACK, 
        BasicColorFactory.getInstance().WHITE);

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
    protected Rectangle rectangle = RectangleFactory.SINGLETON;

    protected int x;
    protected int y;
    
    protected FormType formType = FormTypeFactory.getInstance().NULL_FORM_TYPE;

    public PaintableForm(final String title, final CustomItem[] items, 
            final Rectangle rectangle, final FormType formType,
            final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, items, backgroundBasicColor, foregroundBasicColor);

        this.setSelectedIndex(0);

        try
        {
            this.init(rectangle, formType);
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    public void init(final Rectangle rectangle, final FormType formType)
    throws Exception
    {
        this.rectangle = rectangle;

        final GPoint point = this.rectangle.getPoint();
        
        this.x = point.getX();
        this.y = point.getY();
        
        this.formType = formType;
    }
    
    public int getWidth()
    {
        return this.rectangle.getWidth();
    }
    

}
