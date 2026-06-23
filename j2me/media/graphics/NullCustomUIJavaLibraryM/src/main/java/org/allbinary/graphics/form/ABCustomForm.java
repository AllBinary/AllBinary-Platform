package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class ABCustomForm extends CustomScreen
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    public ABCustomForm(String title, ABCustomItem[] items,
                        BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {

    }
    
    public ABCustomItem[] getAllitems() {
        return null;
    }
    
    public int size()
    {
        return 0;
    }
    
    public void setSelectedIndex(int index)
    {
        
    }

    public int getSelectedIndex()
    {
        return 0;
    }
    
    public void deleteAll()
    {
        
    }

    public void delete(int itemNum)
    {
    }
    
    public int append(ABCustomItem item)
    {
        return -1;
    }
    
    public void insert(int itemNum, ABCustomItem item)
    {
    }

    public void set(int itemNum, ABCustomItem item)
    {
    }
    
    public ABCustomItem get(int itemNum) 
    {
        return ABCustomItem.NULL_CUSTOM_ITEM;
    }

    public int getWidth()
    {
        throw new RuntimeException();
    }
    
    public void paint(Graphics graphics)
    {
    }
    
    public String getTitle()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
