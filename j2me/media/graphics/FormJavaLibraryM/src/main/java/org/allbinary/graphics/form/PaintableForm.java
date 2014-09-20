package org.allbinary.graphics.form;

import org.allbinary.graphics.form.CustomForm;
import org.allbinary.graphics.form.item.CustomItem;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;

public class PaintableForm extends CustomForm
{
    protected Rectangle rectangle;

    protected int x;
    protected int y;
    
    private FormType formType;    

    public PaintableForm(String title, CustomItem[] items, 
            Rectangle rectangle, FormType formType,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(title, items, backgroundBasicColor, foregroundBasicColor);

        this.setSelectedIndex(0);

        try
        {
            this.init(rectangle, formType);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }

    public void init(Rectangle rectangle, FormType formType)
    throws Exception
    {
        this.rectangle = rectangle;

        GPoint point = this.rectangle.getPoint();
        
        this.x = point.getX();
        this.y = point.getY();
        
        this.formType = formType;
    }
    
    public int getWidth()
    {
        return this.rectangle.getWidth();
    }
    
    /**
     * @return the rectangle
     */
    public Rectangle getRectangle()
    {
        return rectangle;
    }

    public void setFormType(FormType formType)
    {
        this.formType = formType;
    }

    public FormType getFormType()
    {
        return formType;
    }    
}
