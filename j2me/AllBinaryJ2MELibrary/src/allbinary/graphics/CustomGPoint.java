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
package allbinary.graphics;

public class CustomGPoint extends GPoint
{
    private int x;
    private int y;
    
    private CustomGPoint(GPoint point)
    {
        super(point);
    }
    
    public CustomGPoint(int x, int y)
    {
        super(x, y);
        
        this.setX(this.getX());
        this.setY(this.getY());
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getY()
    {
        return y;
    }
}
