package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;

public interface CustomItemInterface
{
    int getMinimumWidth();
    int getMinimumHeight();
    String getLabel();
    void paint(Graphics graphics, int x, int y);
    void paintUnselected(Graphics graphics, int x, int y);
}
