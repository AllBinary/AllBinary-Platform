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
package allbinary.media.image.comparison.pixel;

import java.awt.Point;

import allbinary.media.image.comparison.color.ColorDelta;
import com.abcs.logic.util.cache.CacheableInterface;

public class PixelDelta implements CacheableInterface
{
    private Point point;
    private ColorDelta colorDelta;
    
    private Object key;
    
    public PixelDelta(Point point, ColorDelta colorDelta)
    {
        this.setPoint(point);
        this.setColorDelta(colorDelta);
        this.key = PixelDelta.getKey(this.getPoint(), this.getColorDelta());
    }
    
    public Object getKey()
    {
        return key;
    }
    
    public static Object getKey(Point point, ColorDelta colorDelta)
    {
        return point.hashCode() + "_" + colorDelta.getKey().toString();
    }
    
    public Point getPoint()
    {
        return point;
    }
    
    public void setPoint(Point point)
    {
        this.point = point;
    }
    
    public ColorDelta getColorDelta()
    {
        return colorDelta;
    }
    
    public void setColorDelta(ColorDelta colorDelta)
    {
        this.colorDelta = colorDelta;
    }
    
    public String toString()
    {
        return "PixelDelta: Point: " + point.toString() + " " + colorDelta.toString();
    }
}
