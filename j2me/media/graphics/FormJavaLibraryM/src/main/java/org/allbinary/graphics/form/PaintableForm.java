package org.allbinary.graphics.form;

import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.CanvasStrings;

public class PaintableForm extends CustomForm
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
    protected Rectangle rectangle;

    protected int x;
    protected int y;
    
    protected FormType formType;

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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
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
