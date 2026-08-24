package org.allbinary.graphics.form;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ABCustomForm extends CustomScreen
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    @JsConstructor
    public ABCustomForm(String title, ABCustomItem[] items,
                        BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {

    }
    
    @JsMethod
    public ABCustomItem[] getAllitems() {
        return new ABCustomItem[0];
    }
    
    @JsMethod
    public int size()
    {
        return 0;
    }
    
    @JsMethod
    public void setSelectedIndex(int index)
    {
        
    }

    @JsMethod
    public int getSelectedIndex()
    {
        return 0;
    }
    
    @JsMethod
    public void deleteAll()
    {
        
    }

    @JsMethod
    public void delete(int itemNum)
    {
    }
    
    @JsMethod
    public int append(ABCustomItem item)
    {
        return -1;
    }
    
    @JsMethod
    public void insert(int itemNum, ABCustomItem item)
    {
    }

    @JsMethod
    public void set(int itemNum, ABCustomItem item)
    {
    }
    
    @JsMethod
    public ABCustomItem get(int itemNum) 
    {
        return ABCustomItem.getNullInstance();
    }

    @JsMethod
    public int getWidth()
    {
        throw new RuntimeException();
    }
    
    @JsMethod
    public void paint(Graphics graphics)
    {
    }
    
    @JsMethod
    public String getTitle()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
