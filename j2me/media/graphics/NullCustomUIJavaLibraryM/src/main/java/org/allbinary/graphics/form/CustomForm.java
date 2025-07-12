package org.allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.logic.string.StringUtil;

public class CustomForm extends CustomScreen
{    
    public CustomForm(String title, BasicColor backgroundBasicColor,
            BasicColor foregroundBasicColor)
    {
    }

    public CustomForm(String title, CustomItem[] items,
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {

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
    
    public int append(CustomItem item)
    {
        return -1;
    }
    
    public void insert(int itemNum, CustomItem item)
    {
    }

    public void set(int itemNum, CustomItem item)
    {
    }
    
    public CustomItem get(int itemNum) 
    {
        return null;
    }

    public void paint(Graphics graphics)
    {
    }
    
    public String getTitle()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
