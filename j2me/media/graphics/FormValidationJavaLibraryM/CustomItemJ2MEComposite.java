package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Screen;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.graphics.color.BasicColor;

public class CustomItemJ2MEComposite extends CustomItem implements CustomItemInterface
{
    private final Item item;
    
    public CustomItemJ2MEComposite(Item item, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(item.getLabel(), backgroundBasicColor, foregroundBasicColor);
        
        //Cast test
        CustomItemInterface formItemInterface = (CustomItemInterface) item;
        
        this.item = item;
    }

    public void addCommand(Command cmd)
    {
        this.item.addCommand(cmd);
    }

    public String getLabel()
    {
        return this.item.getLabel();
    }

    public int getLayout()
    {
        return this.item.getLayout();
    }

    public int getMinimumHeight()
    {
        return this.item.getMinimumHeight();
    }

    public int getMinimumWidth()
    {
        return this.item.getMinimumWidth();
    }

    public int getPreferredHeight()
    {
        return this.item.getPreferredHeight();
    }

    public int getPreferredWidth()
    {
        return this.item.getPreferredWidth();
    }

    public void removeCommand(Command cmd)
    {
        this.item.removeCommand(cmd);
    }

    public void setDefaultCommand(Command cmd)
    {
        this.item.setDefaultCommand(cmd);
    }

    public void setItemCommandListener(CustomItemCommandListener l)
    {
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }

    public void setLabel(String label)
    {
        this.item.setLabel(label);
    }

    public void setLayout(int layout)
    {
        this.item.setLayout(layout);
    }

    public void setPreferredSize(int width, int height)
    {
        this.item.setPreferredSize(width, height);
    }

    public int getHeight()
    {
        return this.item.getHeight();
    }

    public boolean isFocusable()
    {
        return this.item.isFocusable();
    }

    public void keyPressed(int keyCode)
    {
        this.item.keyPressed(keyCode);
    }

    public int paint(Graphics graphics)
    {
        return this.item.paint(graphics);
    }

    public boolean hasFocus()
    {
        return this.item.hasFocus();
    }

    public void setFocus(boolean state)
    {
        this.item.setFocus(state);
    }

    public boolean select()
    {
        return this.item.select();
    }

    public int traverse(int gameKeyCode, int top, int bottom, boolean action)
    {
        return this.item.traverse(gameKeyCode, top, bottom, action);
    }

    public void setOwner(Screen owner)
    {
        this.item.setOwner(owner);
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        ((CustomItemInterface) getItem()).paint(graphics, x, y);
    }
    
    public void paintUnselected(Graphics graphics, int x, int y)
    {
        ((CustomItemInterface) getItem()).paintUnselected(graphics, x, y);
    }

    public Item getItem()
    {
        return item;
    }
    
}
