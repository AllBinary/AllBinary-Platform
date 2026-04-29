/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.graphics;


public class Line
{
    private GPoint p1;
    private GPoint p2;

    private double deltaX;
    private double deltaY;
    
    public Line(GPoint p1, GPoint p2) //throws Exception
    {
        this.p1 = PointFactory.getInstance().ZERO_ZERO;
        this.p2 = this.p1;
        
        this.setP1(p1);
        this.setP2(p2);
    }

    public double getGradient()
    {
        return this.getDeltaY() / this.getDeltaX();
    }

    private void update()
    {
        this.deltaX = (double) this.getP1().getX() - this.getP2().getX();
        this.deltaY = (double) this.getP1().getY() - this.getP2().getY();
    }
    
    public double getDeltaX()
    {
        return this.deltaX;
    }

    public double getDeltaY()
    {
        return this.deltaY;
    }

    public GPoint getP1()
    {
        return this.p1;
    }

    public GPoint getP2()
    {
        return this.p2;
    }

    public void setP1(GPoint p1)
    {
        this.p1 = p1;
        this.update();
    }

    public void setP2(GPoint p2)
    {
        this.p2 = p2;
        this.update();
    }
}
