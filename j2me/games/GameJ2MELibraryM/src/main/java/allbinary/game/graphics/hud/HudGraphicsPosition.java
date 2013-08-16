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
package allbinary.game.graphics.hud;

import allbinary.graphics.GPoint;

public class HudGraphicsPosition
{
    private int anchor;
    private GPoint point;
    
    public HudGraphicsPosition(GPoint point, int anchor)
    {
       this.setAnchor(anchor);
       this.setPoint(point);
    }

    public int getAnchor()
    {
       return anchor;
    }

    private void setAnchor(int anchor)
    {
       this.anchor = anchor;
    }

    public GPoint getPoint()
    {
        return point;
    }

    public void setPoint(GPoint point)
    {
        this.point = point;
    }
}