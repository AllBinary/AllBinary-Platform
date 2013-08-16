package allbinary.game.health;

import javax.microedition.lcdui.Graphics;

import allbinary.layer.AllBinaryLayer;

public class HealthBarTwodAnimation
extends HealthBarAnimation
{
    public HealthBarTwodAnimation(AllBinaryLayer layerInterface, int location)
    throws Exception
    {
        super(layerInterface, location);
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        int ax = this.allbinaryLayer.getViewPosition().getX();
        //int y = this.getY() - 1;

        this.basicColorUtil.setBasicColor(
                graphics, this.basicColor, this.color);

        graphics.fillRect(ax, this.getY() - 1, x2, this.thickness);
        
        /*
        for (int index = 0; index < this.thickness; index++)
        {
            graphics.drawLine(x, y - index, x + x2, y - index);
        }
        */
    }
}